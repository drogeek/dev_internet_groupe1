package fr.univtln.groupe1.metier;

import java.util.ArrayList;
import java.util.List;

public class Dresseur {
    private String nom;
    private ArrayList<Pokemon> pokemons;

//    Un dresseur ne possède pas de pokemon au début
    public Dresseur(String nom) {
        this.nom = nom;
        this.pokemons=new ArrayList<>(5);
    }

//    Ajout/suppresion de pokémon
    public void add(Pokemon pokemon){
        this.pokemons.add(pokemon);
    }

    public void del(Pokemon pokemon){
        this.pokemons.remove(pokemon);
    }

// Getter and Setter

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = (ArrayList<Pokemon>) pokemons;
    }
}
