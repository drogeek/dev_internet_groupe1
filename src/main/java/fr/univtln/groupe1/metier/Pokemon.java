package fr.univtln.groupe1.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pokemon {

    private static final int VALUE_MAX  = 100;

    @GeneratedValue
    @Id
    @XmlElement
    private int id;
    @XmlElement
    private String nom;

    @ManyToOne
    private Trainer trainer;

//    TODO
//    Rajouter le type espece
//    Fonction ajoutant/enlevant pourcentage niveau

    private int levelLife;
    private int levelFun;
    private int levelAffection;
    private int levelHunger;

    public Pokemon(){}
    public Pokemon(String nom) {
        this.nom = nom;
        this.levelLife = VALUE_MAX;
        this.levelFun = VALUE_MAX;
        this.levelAffection = VALUE_MAX;
//        Pas d'affection au début
        this.levelHunger = 0;

    }



    public void setTrainer(Trainer trainer){
        this.trainer = trainer;
    }

//    Fonction de diminution des niveaux
//    Fonction a appliquer tous les x temps
//   A appliquer x fois, prendre la différence de temps entre derniere connexion et temps courant
    public void reduceLevel(){
//        TODO
//        Empecher de partir dans les négatifs
        this.levelHunger-=1;
        this.levelFun-=1;
        this.levelAffection-=1;
        if((levelFun==0) || (levelHunger==0) || (levelAffection==0))
            this.levelLife-=1;
        if(levelLife==0)
//            TODO
//            Prevoir une fonction "mort pokemon"
            System.out.println("Pokemon mort :'(");
    }

//    Fonction amélioration niveau pokemon
    public void increaseLevel(Item item){
        Item.Type typeItem = item.getType();
        if (typeItem==Item.Type.FOOD) {
            this.levelHunger += item.getValue();
            if (this.levelHunger>VALUE_MAX)
                this.levelHunger=VALUE_MAX;
        }
//        Si il s'agit d'un objet, alors on regarle le nom de l'objet
        else
        {
            Item.Name nameItem = item.getName();
            if (nameItem==Item.Name.Toy) {
                this.levelAffection += item.getValue();
                if (this.levelAffection > VALUE_MAX)
                    this.levelAffection = VALUE_MAX;
            }
//            TODO
//            Faire de meme pour les autres noms (au moins un par niveau)
        }

    }

    public int getId() {
        return id;
    }

    //    Egaux si un dresseur a 2 pokemons ayant le meme nom
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        if (nom != null ? !nom.equals(pokemon.nom) : pokemon.nom != null) return false;
        return trainer != null ? trainer.equals(pokemon.trainer) : pokemon.trainer == null;
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (trainer != null ? trainer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", trainer=" + trainer +
                ", levelLife=" + levelLife +
                ", levelFun=" + levelFun +
                ", levelAffection=" + levelAffection +
                ", levelHunger=" + levelHunger +
                '}';
    }
}

