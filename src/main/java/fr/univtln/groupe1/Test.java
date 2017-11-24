package fr.univtln.groupe1;

import fr.univtln.groupe1.ejb.ItemEJB;
import fr.univtln.groupe1.ejb.PokemonEJB;
import fr.univtln.groupe1.ejb.Qualifiers.RandomItem;
import fr.univtln.groupe1.ejb.TrainerEJB;
import fr.univtln.groupe1.metier.Item;
import fr.univtln.groupe1.metier.Pokemon;
import fr.univtln.groupe1.metier.Trainer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class Test {
    //    private static final Class[] shadeHack = {org.apache.log4j.RollingFileAppender.class,
//            org.apache.log4j.ConsoleAppender.class,
//            PatternLayout.class};

//    Set the logger with the real class name.
//    private static Logger logger= Logger.getLogger(App.class.getName());
//
//  Log pour tester le deploiement payara
//  Fonctionnement a verifier

    @Inject
    TrainerEJB trainerEJB;

    @Inject
    ItemEJB itemEJB;

    @Inject
    PokemonEJB pokemonEJB;

    @Inject @RandomItem
    Item item;

    @PostConstruct
    public void test2(){
        trainerEJB.newTrainer("Sasha");
        trainerEJB.newTrainer("Pierre");
        trainerEJB.addPokemonTrainer(1, "pikachu");
        trainerEJB.createItemTrainer(1);
//        trainerEJB.delTrainer(1);
//        Pokemon pokemon1 = new Pokemon("Yoshi");
//        trainerEJB.removeItem(item2.getId());

//        List<Pokemon> pokemons = trainerEJB.listPokemon(trainer);
//        trainerEJB.getItem(trainer);
//        trainerEJB.getItem(trainer);
    }
}
