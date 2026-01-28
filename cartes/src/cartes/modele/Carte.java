package cartes.modele;

/**
 * Représente une carte à jouer définie par sa hauteur (rang) et sa couleur (enseigne).
 * Cette classe permet de stocker les informations d'une carte et de fournir une représentation textuelle.
 */
public class Carte {
    
    /**
     * La hauteur de la carte (par exemple : Roi, As, 10).
     */
    private final String hauteur;
    
    /**
     * La couleur de la carte (par exemple : Cœur, Pique).
     */
    private final String couleur;

    /**
     * Construit une nouvelle carte avec la hauteur et la couleur spécifiées.
     * * @param hauteur La hauteur de la carte.
     * @param couleur La couleur de la carte.
     */
    public Carte(String hauteur, String couleur) {
        this.hauteur = hauteur;
        this.couleur = couleur;
    }

    /**
     * Retourne la hauteur de la carte.
     * * @return La hauteur de la carte.
     */
    public String getHauteur() {
        return this.hauteur;
    }

    /**
     * Retourne la couleur de la carte.
     * * @return La couleur de la carte.
     */
    public String getCouleur() {
        return this.couleur;
    }

    /**
     * Retourne une représentation textuelle de la carte sous la forme "Hauteur de Couleur".
     * * @return Une chaîne de caractères représentant la carte.
     */
    @Override
    public String toString() {
        return this.hauteur + " de " + this.couleur;
    }
}