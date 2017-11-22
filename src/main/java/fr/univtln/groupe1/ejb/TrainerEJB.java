package fr.univtln.groupe1.ejb;


import fr.univtln.groupe1.metier.FactoryItem;
import fr.univtln.groupe1.metier.Item;
import fr.univtln.groupe1.metier.Pokemon;
import fr.univtln.groupe1.metier.Trainer;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collections;
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
    @Path("/createItem")
    @POST
    public void getItem(Trainer trainer){
        EntityManager em = emf.createEntityManager();
        Item item = factoryItem.createItem();
        item.setTrainer(trainer);
        em.find(Trainer.class, trainer.getId());
        trainer.addItem(item);
        em.flush();
    }

}
