package fr.univtln.groupe1.ejb;

import fr.univtln.groupe1.ejb.Qualifiers.RandomItem;
import fr.univtln.groupe1.metier.Item;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;


@Stateless
@Path("/item")
@javax.ws.rs.Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class ItemEJB {

    @PersistenceContext(unitName = "db1")
    private EntityManager em;

    @Path("{id}")
    @GET
    public Response getItem( @PathParam("id") int id){
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
        Item item;
        if (n==1) {
            item = Item.builder()
                    .type(Item.Type.FOOD)
                    .name(Item.Name.Salad).build();
        }
        else
            item = Item.builder().type(Item.Type.OBJECT).name(Item.Name.Toy).build();
        return item;
    }
}
