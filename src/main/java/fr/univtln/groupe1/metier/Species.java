package fr.univtln.groupe1.metier;

import java.util.List;

public class Species {

    private String name;
    private List <TypePokemon> typePokemon;

    public Species(String name, List<TypePokemon> typePokemons) {
        this.name = name;
        this.typePokemon = typePokemons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TypePokemon> getTypePokemon() {
        return typePokemon;
    }

    public void setTypePokemon(List<TypePokemon> typePokemon) {
        this.typePokemon = typePokemon;
    }

    @Override
    public String toString() {
        return "Species{" +
                "name='" + name + '\'' +
                ", typePokemon=" + typePokemon +
                '}';
    }
}
