package blackjack.modele.main;

import cartes.modele.Carte;

/**
 * Représente une main résultant de l'action "Séparer" (Split).
 * Cette classe gère la division d'une main initiale en deux mains distinctes,
 * en créant une seconde main et en redistribuant les cartes nécessaires pour que chaque main
 * possède deux cartes au départ.
 */
public class MainSplite extends AbstractMain {

    /**
     * La seconde main créée suite à la séparation.
     */
    private final MainBasic secondeMain;

    /**
     * Construit une main séparée en réorganisant les cartes entre la main d'origine et la nouvelle main.
     * Une carte est retirée de la main décorée pour être placée dans la seconde main.
     * Ensuite, deux nouvelles cartes sont distribuées : l'une complète la main d'origine,
     * et l'autre complète la seconde main.
     *
     * @param mainDecore La main d'origine qui est séparée.
     * @param carte1 La nouvelle carte ajoutée à la main d'origine.
     * @param carte2 La nouvelle carte ajoutée à la seconde main.
     */
    public MainSplite(Main mainDecore, Carte carte1,Carte carte2) {
        super(mainDecore);
        Carte carte = mainDecore.getPaquet().retirerPremiereCarte();
        secondeMain = new MainBasic(mainDecore.getJoueur(), mainDecore.getMise());
        secondeMain.premierDeal(carte, carte2);
        mainDecore.getPaquet().ajouterCarteDessous(carte1);
    }

    /**
     * Retourne la seconde main générée par la séparation.
     *
     * @return L'instance de la nouvelle main créée.
     */
    public Main getSecondeMain(){
        return secondeMain;
    }
    
}