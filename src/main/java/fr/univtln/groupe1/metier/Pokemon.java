package fr.univtln.groupe1.metier;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.Random;


// TODO
// Intégrer le type du pokemon
public class Pokemon {

    private static final int PV_MIN = 10;
    private static final int PV_MAX = 100;
    private static final int WEIGHT_MIN = 10;
    private static final int WEIGHT_MAX = 200;
    private static final int SIZE_MIN = 1;
    private static final int SIZE_MAX = 300;


    @Min(0)
    private int id;

    private Image image;

    @Size(min = 2, max=15)
    private String species;

    @Min(PV_MIN)
    @Max(PV_MAX)
    private int pv;

    @Min(WEIGHT_MIN)
    @Max(WEIGHT_MAX)
    private int weight;

    @Min(SIZE_MIN)
    @Max(SIZE_MAX)
    private int size;

    public Pokemon(int id, Image image, String species) {
        Random r = new Random();
        this.id = id;
        this.image = image;
        this.species = species;

//        Initialisation aléatoire des différentes caractéristiques
        this.pv = r.nextInt(PV_MAX-PV_MIN)+PV_MIN;
        this.weight = r.nextInt(WEIGHT_MAX-WEIGHT_MIN)+WEIGHT_MIN;
        this.size = r.nextInt(SIZE_MAX-SIZE_MIN)+SIZE_MIN;
    }

//    Getter and Setter

    public int getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    //    Fonction à redéfinir systématiquement

    @Override
    public int hashCode() {
//        TODO
        return super.hashCode();
    }

    //    On compare juste l'espèce (car une seule espèce par pokedex)
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Pokemon))
            return false;
        Pokemon o = (Pokemon) obj;
        return o.species.equals(species);
    }

    @Override
    public String toString() {
        return ("Le pokemon est de l'espèce " + species  +
                ". Son id est " + String.valueOf(id))+". \n PV: " + pv
                + " \n Poids: " + weight
                + " \n taille: " + size;
    }
}