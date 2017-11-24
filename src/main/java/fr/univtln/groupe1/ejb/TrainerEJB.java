package fr.univtln.groupe1.ejb;

import fr.univtln.groupe1.metier.Item;
import fr.univtln.groupe1.metier.Pokemon;
import fr.univtln.groupe1.metier.Trainer;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@javax.persistence.Table(name = "trainer", catalog = "db1", schema = "public")
@Stateless
@Path("/trainer")
@javax.ws.rs.Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@javax.ws.rs.Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//Une seul instance pour l'instant :)
@Singleton
public class TrainerEJB {

    @PersistenceUnit(unitName = "db1")
    private EntityManagerFactory emf;

    @Inject
    ItemEJB factoryItem;

    //    Pour tester
    @Path("/test")
    @GET
    @Produces (MediaType.TEXT_PLAIN)
    public String test(){
        return "Ca fonctionne";
    }

    //    Retourne la liste des pokemons d'un dresseur
    @Path("/{idTrainer}/pokemons")
    @GET
    public List<Pokemon> listPokemon(@PathParam("idTrainer") int idTrainer){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Pokemon> query = em.createNamedQuery("FIND POKEMONS_TRAINER", Pokemon.class)
                .setParameter("idTrainer", idTrainer);
        return query.getResultList();
    }

//    Creation d'un nouvel entraineur
    @Path("/newTrainer/{name}")
    @POST
    public Trainer newTrainer(@PathParam("name") String nom){
        Trainer trainer = new Trainer(nom);
        EntityManager em = emf.createEntityManager();
        em.persist(trainer);
        em.flush();
        em.refresh(trainer);
        return trainer;
    }


// AJout d'un item à un dresseur
    @Path("/{idTrainer}/createItem")
    @PUT
    public Item createItemTrainer(@PathParam("idTrainer") int idTrainer){
        EntityManager em = emf.createEntityManager();
        Item item = factoryItem.createItem();
        Trainer trainer = em.find(Trainer.class, idTrainer);
        item.setTrainer(trainer);
        trainer.addItem(item);
        em.flush();
        return item;
    }

//    Ajout d'un pokemon à un dresseur
    @Path("/{idTrainer}/addPokemon/{namePokemon}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon addPokemonTrainer(@PathParam("idTrainer") int idTrainer, @PathParam("namePokemon") String namePokemon){
        EntityManager em = emf.createEntityManager();
        Pokemon pokemon = new Pokemon(namePokemon);
//        Query q = em.createQuery("SELECT p FROM Trainer p where p.id=:valeur").setParameter("valeur", idTrainer);
//        Trainer trainer = (Trainer) q.getResultList().get(0);
        Trainer trainer = em.find(Trainer.class, idTrainer);
        pokemon.setTrainer(trainer);
        trainer.addPokemon(pokemon);
        em.flush();
        return pokemon;
    }

//    Pret d'un pokemon entre dresseurs
//    Le nom du pokemon est unique dans la base de données

    @Path("/lend/{idTrainerDst}/{idPokemon}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon lendPokemon( @PathParam("idTrainerDst") int idTrainerDst, @PathParam("idPokemon")int idPokemon){
        EntityManager em = emf.createEntityManager();
        Pokemon pokemon = em.find(Pokemon.class, idPokemon);
        Trainer trainerDst = em.find(Trainer.class, idTrainerDst);
//        Véification si il s'agit d'un retour de pret
//        Suppression du pokemon préter de la liste des pokemon de l'emprunteur
        if(pokemon.getTrainer()==trainerDst){
            Trainer oldLendTrainer = em.find(Trainer.class, pokemon.getTrainerLend().getId());
            oldLendTrainer.delPokemon(pokemon);
            pokemon.setTrainerLend(null);

        }
        else if (pokemon.getTrainerLend()!=null){
//            On n'a pas le droit de préter un pokémon déjà préter
//            On générera une erreur
            System.err.println("Erreur, ce pokemon ne vous appartient pas");
        }
        else {
            pokemon.setTrainerLend(trainerDst);
            trainerDst.addPokemon(pokemon);
        }
        return pokemon;
    }

//    Supression d'un trainer
    @Path("/delTrainer/{idTrainer}")
    @DELETE
    public void delTrainer(@PathParam("idTrainer") int idTrainer){
        EntityManager em = emf.createEntityManager();
        em.remove(em.find(Trainer.class, idTrainer));
    }

}
