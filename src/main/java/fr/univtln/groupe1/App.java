package fr.univtln.groupe1;

import fr.univtln.groupe1.metier.Pokemon;
import fr.univtln.groupe1.metier.Trainer;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Singleton
@Startup
@ApplicationPath("/")
public class App extends Application {


//    private static final Class[] shadeHack = {org.apache.log4j.RollingFileAppender.class,
//            org.apache.log4j.ConsoleAppender.class,
//            PatternLayout.class};

    //Set the logger with the real class name.
//    private static Logger logger= Logger.getLogger(App.class.getName());

//  Log pour tester le deploiement payara
//  Fonctionnement a verifier

    @PostConstruct
    public void test2(){
        Trainer trainer = new Trainer("Mario");
        Pokemon pokemon = new Pokemon("Kirby");
        Pokemon pokemon1 = new Pokemon("Yoshi");
    }
}