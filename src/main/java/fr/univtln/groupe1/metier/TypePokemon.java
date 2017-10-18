package fr.univtln.groupe1.metier;

public class TypePokemon {

    public enum Type_name {ACIER, COMBAT, DRAGON,
        EAU, ELECTRICITE, FEE, FEU, GLACE,
        INSECTER, NORMAL, PLANTE, POISON, PSY, ROCHE, SOL, SPECTRE,
        TENEBRE, VOL
    }

    private Type_name type_name;
//    Description avantage
    private String Advantage;

    public TypePokemon(Type_name type_name, String advantage) {
        this.type_name = type_name;
        Advantage = advantage;
    }

    public Type_name getType() {
        return type_name;
    }

    public void setType(Type_name type) {
        this.type_name = type;
    }

    public String getAdvantage() {
        return Advantage;
    }

    public void setAdvantage(String advantage) {
        Advantage = advantage;
    }

    @Override
    public String toString() {
        return "Species{" +
                "type_name=" + type_name +
                ", Advantage='" + Advantage + '\'' +
                '}';
    }

    //    A vérifier
//    TODO

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypePokemon species = (TypePokemon) o;

        if (type_name != species.type_name) return false;
        return Advantage != null ? Advantage.equals(species.Advantage) : species.Advantage == null;
    }

    @Override
    public int hashCode() {
        int result = type_name != null ? type_name.hashCode() : 0;
        result = 31 * result + (Advantage != null ? Advantage.hashCode() : 0);
        return result;
    }
}
