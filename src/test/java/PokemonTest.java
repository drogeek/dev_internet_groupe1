import fr.univtln.groupe1.metier.Pokemon;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// LE nom des fonctions de test doit commencer par test!
// BeforeEach pour initialiser
// AfterEach pour remettre les valeurs initialisées par BéforeEach par null
class PokemonTest extends TestCase{

    private Pokemon pokemon;
    private String valeur_espece = "Pikachu";
    private String valeur_nom = "Pika";
    private int valeur_debutXP;

    private int valeur_level = 5;
    private int valeur_ajoutXP = 100;



    @BeforeEach
    public void init(){
        pokemon = new Pokemon(valeur_espece, valeur_nom, valeur_level);
//        En cas d'initialisation avec une xp aléatoire
        valeur_debutXP = pokemon.getXp();
    }

    @AfterEach
    public void tearDown(){
        pokemon = null;
    }

    @Test
    void testNiveauSupp(){
        pokemon.levelSup();
        assertEquals(valeur_level+1, pokemon.getLevel());
    }

    @Test
    void testExpSup(){
        pokemon.progessionXp(valeur_ajoutXP);
        assertEquals(valeur_ajoutXP+1, pokemon.getXp());
    }
}
