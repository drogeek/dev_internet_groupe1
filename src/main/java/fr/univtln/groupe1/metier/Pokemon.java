package fr.univtln.groupe1.metier;

public class Pokemon{
    private String espece;
    private String name;
    private int xp;
    private int level;
    private Dresseur dresseur;

    public Pokemon(String espece, String name, int level) {
        this.espece = espece;
        this.name = name;
        this.level = level;
        this.xp=1;
    }

//    Ajout/Suppression dresseur

    public void add_Dresseur(Dresseur dresseur){
        this.dresseur = dresseur;
    }

    public void sup_Dresseur(){
        this.dresseur = null;
    }

//    Niveau supérieur
    public void progessionXp(int xp){
        this.xp+=xp;
    }

//    Niveau supérieur
    public void levelSup(){
        this.level+=1;
    }

//    Getter and Setter

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
