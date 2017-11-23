package fr.univtln.groupe1.ejb;

import fr.univtln.groupe1.metier.Item;
import fr.univtln.groupe1.metier.Pokemon;
import fr.univtln.groupe1.metier.Trainer;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@javax.persistence.Table(name = "trainer", catalog = "db1", schema = "public")
@Stateless
@Path("/trainer")
//Une seul instance pour l'instant :)
@Singleton
public class TrainerEJB {

    @PersistenceUnit(unitName = "db1")
    private EntityManagerFactory emf;

    FactoryItem factoryItem = new FactoryItem();

    //    Pour tester
    @Path("/test")
    @GET
    @Produces (MediaType.TEXT_PLAIN)
    public String test(){
        return "Ca fonctionne";
    }

    //    Retourne la liste des pokemons d'un dresseur
    @Path("/pokemon")
    @POST
    @Produces
    public List<Pokemon> listPokemon(Trainer trainer){
        return trainer.getPokemons();
    }

//    Creation d'un nouvel entraineur
    @Path("/newTrainer/{name}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Trainer newTrainer(@PathParam("name") String nom){
        Trainer trainer = new Trainer(nom);
        EntityManager em = emf.createEntityManager();
        em.persist(trainer);
        em.flush();
        em.refresh(trainer);
        return trainer;
    }


// AJout d'un item à un dresseur
    @Path("/createItem/{idTrainer}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("/addPokemon/{namePokemon}/{idTrainer}")
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


}
