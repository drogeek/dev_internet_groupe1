package fr.univtln.groupe1.metier;


import java.util.ArrayList;

public class Arche {
    private ArrayList<Dresseur> dresseurs;
    private ArrayList<Pokemon> pokemons;

    public Arche() {
        this.dresseurs= new ArrayList<>(5);
        this.pokemons = new ArrayList<>(5);
    }

//    Ajout/Suppression de dresseurs et de pokemon
    public void addDresseur(Dresseur dresseur){
        this.dresseurs.add(dresseur);
    }

    public void addPokemon(Pokemon pokemon){
        this.pokemons.add(pokemon);
    }

    public void suppDresseur(Dresseur dresseur){
        this.dresseurs.remove(dresseur);
    }

    public void suppPokemon(Pokemon pokemon){
        this.pokemons.remove(pokemon);
    }

//    Getter and Setter

    public ArrayList<Dresseur> getDresseurs() {
        return dresseurs;
    }

    public void setDresseurs(ArrayList<Dresseur> dresseurs) {
        this.dresseurs = dresseurs;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
