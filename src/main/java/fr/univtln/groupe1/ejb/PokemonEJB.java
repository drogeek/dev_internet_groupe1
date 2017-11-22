package fr.univtln.groupe1.metier;


import fr.univtln.groupe1.metier.Pokemon;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Path;


@javax.persistence.Table(name = "Pokemon", catalog = "db1", schema = "public")
@Stateless
@Path("/resources")
public class PokemonEJB {

    @PersistenceUnit(unitName = "db1")
    private EntityManagerFactory emf;

    @Path("ejb")
    public Pokemon addPokemon(Pokemon pokemon){
        EntityManager em = emf.createEntityManager();
        em.persist(pokemon);
        em.flush();
        em.refresh(pokemon);
        return pokemon;
    }
}
