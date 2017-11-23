package fr.univtln.groupe1.metier;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class Trainer {

    @Id @GeneratedValue
    private int id;

    private String name;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Pokemon> pokemons;

//    Représente le "coffre" du dresseur
    @OneToMany (cascade = CascadeType.ALL)
    private List<Item> items;

    public Trainer(String name) {
        this.name = name;
        this.pokemons = new ArrayList<Pokemon>(5);
        this.items = new ArrayList<>(10);
    }

    public Trainer(){}

    public void addPokemon(Pokemon pokemon)
    {
        this.pokemons.add(pokemon);
    }

    public void addItem(Item item) { this.items.add(item);}

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id='" + id + '\'' +
                ", pokemons=" + pokemons +
                ", items=" + items +
                '}';
    }

//    Egaux si 2 entraineneus ont les memes pokemons et items (id compris)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainer trainer = (Trainer) o;

        if (pokemons != null ? !pokemons.equals(trainer.pokemons) : trainer.pokemons != null) return false;
        return items != null ? items.equals(trainer.items) : trainer.items == null;
    }

    @Override
    public int hashCode() {
        int result = pokemons != null ? pokemons.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
