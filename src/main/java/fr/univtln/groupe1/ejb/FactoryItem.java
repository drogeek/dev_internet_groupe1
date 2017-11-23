package fr.univtln.groupe1.metier;

import javax.enterprise.inject.Produces;
import java.util.Random;


// A voir si on garde une seule classe Item ou 2 classes Objet et Nourriture

public class FactoryItem {

    @Produces @Item
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
        return item;
    }

    public Item Item(){
        return new Item();
    }

}
