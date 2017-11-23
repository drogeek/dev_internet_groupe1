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
    @Produces
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

    //    Ajout un pokemon à un dresseur
    @Path("/addPokemon")
    @POST
    public void addPokemon(Pokemon pokemon, Trainer trainer){
        EntityManager em = emf.createEntityManager();
        trainer.addPokemon(pokemon);
        em.persist(trainer);
        em.flush();
        em.refresh(trainer);
    }

    //    Retourne la liste des items d'un dresseur
    @Path("/items")
    @POST
    @Produces
    public List<Item> listItem(Trainer trainer){
        return trainer.getItems();
    }

    //    Créer un item pour un dresseur
//    Retourne l'item afin de pouvoir récupérer son id par exemple
    @Path("/createItem")
    @POST
    @Produces
    public Item getItem(Trainer trainer){
        EntityManager em = emf.createEntityManager();
        Item item = factoryItem.createItem();
        item.setTrainer(trainer);
        em.find(Trainer.class, trainer.getId());
        trainer.addItem(item);
        em.flush();
        return item;
    }

//    Suppresion d'un item
//    @DELETE
//    @Path("/removeItem{idItem}")
//    public javax.ws.rs.core.Response removeItem(@PathParam("idItem") int itemId){
//        EntityManager em = emf.createEntityManager();
//        em.remove(em.find(Item.class, itemId));
//        return javax.ws.rs.core.Response.noContent().build();
//    }

}
