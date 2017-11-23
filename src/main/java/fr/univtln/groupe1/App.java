package fr.univtln.groupe1;

import fr.univtln.groupe1.ejb.ItemEJB;
import fr.univtln.groupe1.ejb.Qualifiers.RandomItem;
import fr.univtln.groupe1.ejb.TrainerEJB;
import fr.univtln.groupe1.metier.Item;
import fr.univtln.groupe1.metier.Pokemon;
import fr.univtln.groupe1.metier.Trainer;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationPath("/")
public class App extends Application{

    private final Set<Class<?>> classes;

    public App(){
        HashSet<Class<?>> c = new HashSet<>();
        c.add(ItemEJB.class);
        c.add(TrainerEJB.class);
        classes = Collections.unmodifiableSet(c);
    }
//    private static final Class[] shadeHack = {org.apache.log4j.RollingFileAppender.class,
//            org.apache.log4j.ConsoleAppender.class,
//            PatternLayout.class};

    //Set the logger with the real class name.
//    private static Logger logger= Logger.getLogger(App.class.getName());

//  Log pour tester le deploiement payara
//  Fonctionnement a verifier
//
//    @Inject
//    TrainerEJB trainerEJB;
//
//    @Inject @RandomItem
//    Item item;
//
//    @PostConstruct
//    public void test2(){
//        Trainer trainer = new Trainer("Mario");
//        Pokemon pokemon = new Pokemon("Kirby");
//        Trainer trainer1 = new Trainer("Luigi");
//        Pokemon pokemon1 = new Pokemon("Yoshi");
////        Pokemon pokemon1 = new Pokemon("Yoshi");
//        trainerEJB.addPokemon(pokemon,trainer);
//        trainerEJB.addPokemon(pokemon1, trainer1);
//        Item item1 = trainerEJB.getItem(trainer1);
//        Item item2 = trainerEJB.getItem(trainer1);
////        trainerEJB.removeItem(item2.getId());
//        System.out.println(item.toString());
//
////        List<Pokemon> pokemons = trainerEJB.listPokemon(trainer);
////        trainerEJB.getItem(trainer);
////        trainerEJB.getItem(trainer);
//    }
}