package cartes.modele;
import java.util.Arrays;
import java.util.List;

/**
 * Classe Factory (Fabrique) fournissant des méthodes statiques pour 
 * créer différents types de paquets de cartes.
 * Cette classe ne peut pas être instanciée.
 */
public class PaquetFactory {
    
    public final static List<String> COULEURS = Arrays.asList("Piques", "Cœurs", "Carreaux", "Trèfles");
    public final static List<String> HAUTEURS = Arrays.asList("As", "2", "3", "4","5","6","7","8","9","10","Valet", "Dame", "Roi");
    /**
     * Constructeur privé pour empêcher l'instanciation de cette classe utilitaire.
     */
    private PaquetFactory(){
    }

    /**
     * Crée un paquet de 52 cartes standard.
     *
     * Le paquet contient les hauteurs :
     * As, 2, 3, 4, 5, 6, 7, 8, 9, 10, Valet, Dame, Roi.
     *
     * Chaque hauteur est présente dans les 4 couleurs (Piques, Cœurs, Carreaux, Trèfles).
     * Les cartes sont ajoutées au paquet en utilisant la méthode ajoutCarteDessus.
     *
     * @return Un nouvel objet Paquet contenant les 52 cartes.
     */
    public static Paquet creerPaquet52(){
        Paquet paquet = new Paquet();
        for (String hauteur : HAUTEURS) {            
            for (String couleur : COULEURS) {
                paquet.ajouterCarteDessus(new Carte(hauteur, couleur));
            }    
        }     
        return paquet;
    }  
}