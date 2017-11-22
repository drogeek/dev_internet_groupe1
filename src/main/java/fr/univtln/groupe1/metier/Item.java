package fr.univtln.groupe1.metier;


import javax.persistence.*;
import java.util.Random;

@Entity
public class Item {

    public enum Type {FOOD, OBJECT}

    public enum Name{Salad, Toy}

    @GeneratedValue
    @Id
    private int id;

    @ManyToOne
    private Trainer trainer;

    private Type type;

    private Name name;

//    Level représente le niveau de l'amélioration
//    Compris entre 1 et 4
    private int level;

    private int value;

    public Item(Type type, Name name, int level) {
        this.type = type;
        this.name = name;
        this.level = level;
    }

    public Item(){}

//    Le niveau est généré aléatoirement
    public int getLevel() {
        return level;
    }

    public void setLevel() {
        Random r = new Random();
        int levelRand = r.nextInt(4)+1;
        this.level = levelRand;
    }

    public int getValue() {
        return value;
    }

//    Niveau 1 -> 10*1
//    Niveau 3 -> 10*3 (10 est la valeur de base maximale)
    public void setValue(int level) {
        Random r = new Random();
        int valueBase = r.nextInt(10)+1;
        this.value = valueBase*level;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "type=" + type +
                ", name=" + name +
                ", level=" + level +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (level != item.level) return false;
        if (value != item.value) return false;
        if (trainer != null ? !trainer.equals(item.trainer) : item.trainer != null) return false;
        if (type != item.type) return false;
        return name == item.name;
    }

    @Override
    public int hashCode() {
        int result = trainer != null ? trainer.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + value;
        return result;
    }


}
