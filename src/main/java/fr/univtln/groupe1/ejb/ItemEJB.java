package fr.univtln.groupe1.ejb;

import fr.univtln.groupe1.metier.Item;
import fr.univtln.groupe1.ejb.Qualifiers.RandomItem;
import fr.univtln.groupe1.metier.Trainer;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;


// A voir si on garde une seule classe Item ou 2 classes Objet et Nourriture

@Stateless
@Path("/item")
@javax.ws.rs.Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class ItemEJB {

//    @Inject @RandomItem
//    Item item;

    @PersistenceUnit(unitName = "db1")
    private EntityManagerFactory emf;

//    public void createAndPersistItem(){
//    }
//    @Path("{id}")
//    @GET
//    public List<Item> getItems(int idTrainer){
//
//    }

    @Path("{id}")
    @GET
    public Response getItem( @PathParam("id") int id){
        EntityManager em = emf.createEntityManager();
        Item item = em.find(Item.class, id);
        if(item == null)
            throw new NotFoundException();
        return Response.ok(item).build();
    }

    @Produces @RandomItem
    public Item createItem(){
//        Choix aléatoire du type
//        TODO
//        Le nom sera choisi aléatoirement

        Random r = new Random();
        int n = r.nextInt(2)+1;
        Item item = new Item();
        if (n==1) {
            item.setType(Item.Type.FOOD);
            item.setName(Item.Name.Salad);
        }
        else if(n==2) {
            item.setType(Item.Type.OBJECT);
            item.setName(Item.Name.Toy);
        }
        item.setLevel();
        item.setValue(item.getLevel());

//        EntityManager em = emf.createEntityManager();
//        em.persist(item);
//        em.flush();
//        em.refresh(item);
        return item;
    }
}
