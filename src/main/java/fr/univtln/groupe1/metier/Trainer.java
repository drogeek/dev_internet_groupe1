package fr.univtln.groupe1.metier;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@NamedQuery(name="FIND POKEMONS_TRAINER", query="select p from Trainer t , t.pokemons p where t.id = :idTrainer")
@NamedQuery(name="FIND ITEMS_TRAINER", query="select i from Trainer t , t.items i where t.id = :idTrainer")
@NamedQuery(name="DEL_TRAINER", query="DELETE from Trainer t where t.id=:valeur")
@NamedQuery(name="FIND_ALL_TRAINER", query="select t from Trainer t")
@NoArgsConstructor
public class Trainer {

    @Id @GeneratedValue
    @XmlElement
    private int id;

    @XmlElement
    private String name;

    @OneToMany (cascade = CascadeType.ALL)
    @Singular private List<Pokemon> pokemons;

//    Représente le "coffre" du dresseur
    @OneToMany (cascade = CascadeType.ALL)
    @Singular private List<Item> items;

    @Builder
    public Trainer(String name){
        this.name = name;
        this.pokemons = new ArrayList<Pokemon>(5);
        this.items = new ArrayList<>(10);
    }

    public void addPokemon(Pokemon pokemon)
    {
        this.pokemons.add(pokemon);
    }

    public void delPokemon(Pokemon pokemon) {this.pokemons.remove(pokemon);}

    public void addItem(Item item) { this.items.add(item);}

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
