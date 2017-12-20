package fr.univtln.groupe1.ejb;

import fr.univtln.groupe1.metier.Item;
import fr.univtln.groupe1.metier.Pokemon;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Stateless
@Path("/pokemon")
public class PokemonEJB {
    private final int DECREASE_AMOUNT=1;

    @PersistenceUnit(unitName = "db1")
    private EntityManagerFactory emf;

    public void applyItem(int pokeId, int itemId){
        EntityManager em = emf.createEntityManager();
        Item item = em.find(Item.class, itemId);
        Pokemon pokemon = em.find(Pokemon.class, pokeId);

        if(item == null || pokemon == null)
            throw new NotFoundException();

        if(item.getType() == Item.Type.FOOD){
            pokemon.setLevelHunger((pokemon.getLevelHunger()+item.getValue())%Pokemon.VALUE_MAX);
        }
    }

    @Path("update")
    @PUT
    public void recomputeStats(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Pokemon> query = em.createNamedQuery("FIND_ALL", Pokemon.class );
        List<Pokemon> pokemons = query.getResultList();
        pokemons.stream().forEach(this::decreaseStatsPokemon);
        pokemons.stream().forEach(p-> System.out.println(p));
        em.flush();
    }

    //TODO : verify that every necessary fields have been decreased
    private void decreaseStatsPokemon(Pokemon pokemon){
        if (pokemon.getLevelHunger()>0)
            pokemon.setLevelHunger(pokemon.getLevelHunger()-DECREASE_AMOUNT);
        if (pokemon.getLevelAffection()>0)
            pokemon.setLevelAffection(pokemon.getLevelAffection()-DECREASE_AMOUNT);
        if (pokemon.getLevelFun()>0)
            pokemon.setLevelFun(pokemon.getLevelFun()-DECREASE_AMOUNT);
        if ((pokemon.getLevelHunger()==0) | (pokemon.getLevelAffection()==0) | (pokemon.getLevelFun()==0))
            pokemon.setLevelLife(pokemon.getLevelLife()-DECREASE_AMOUNT);
    }
}
