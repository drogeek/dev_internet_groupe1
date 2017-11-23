package fr.univtln.groupe1.ejb;

import fr.univtln.groupe1.metier.Item;
import fr.univtln.groupe1.ejb.Qualifiers.RandomItem;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.Random;


// A voir si on garde une seule classe Item ou 2 classes Objet et Nourriture

@Stateless
@Path("/item")
public class FactoryItem {

//    @Inject @RandomItem
//    Item item;

    @PersistenceUnit(unitName = "db1")
    private EntityManagerFactory emf;

//    public void createAndPersistItem(){
//    }

    @Path("/create")
    @POST
    @javax.ws.rs.Produces(MediaType.APPLICATION_XML)
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

        EntityManager em = emf.createEntityManager();
        em.persist(item);
        em.flush();
        em.refresh(item);
        return item;
    }
}
