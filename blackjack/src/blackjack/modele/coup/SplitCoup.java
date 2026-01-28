package blackjack.modele.coup;



import blackjack.modele.joueur.Joueur;
import blackjack.modele.main.*;
import cartes.modele.*;
import java.util.Iterator;
import java.util.List;

/**
 * Représente le coup "Split" (Séparer) au Blackjack.
 * Cette action permet au joueur de diviser une main composée de deux cartes identiques (ou de même valeur pour les figures)
 * en deux mains distinctes, ce qui implique de placer une mise supplémentaire équivalente à la première.
 */
public class SplitCoup extends AbstractCoup{
    
    /**
     * La pioche utilisée pour distribuer les nouvelles cartes nécessaires aux deux mains générées par la séparation.
     */
    private final Paquet pioche;

    /**
     * Construit un nouveau coup de type Split.
     * * @param pioche Le paquet de cartes depuis lequel les cartes supplémentaires seront tirées.
     */
    public SplitCoup(Paquet pioche){
        this.pioche = pioche;
    }

    /**
     * Exécute la séparation de la main.
     * Cette méthode déduit le montant de la mise du solde du joueur, crée une nouvelle structure de main séparée
     * en distribuant une nouvelle carte pour chaque sous-main, et ajoute la seconde main ainsi créée à la liste des mains du joueur.
     * * @param main La main courante à séparer.
     * @return La nouvelle main principale résultant de la séparation (instance de MainSplite).
     */
    @Override
    public Main executer(Main main){
        Joueur joueur = main.getJoueur();
        joueur.setSolde(joueur.getSolde()-main.getMise());
        main.incNbrTours();
        MainSplite nouvelleMain = new MainSplite(main,
                            pioche.retirerPremiereCarte(),
                            pioche.retirerPremiereCarte());
        Main secondeMain = nouvelleMain.getSecondeMain();
        main.getJoueur().getMains().add(secondeMain);
        return nouvelleMain;
    }

    /**
     * Vérifie si les conditions spécifiques pour effectuer un split sont réunies.
     * Le split est valide si :
     * - Le joueur ne joue que sur une seule main actuellement.
     * - Le solde du joueur permet de couvrir la mise supplémentaire.
     * - La main contient exactement deux cartes.
     * - Les deux cartes ont la même hauteur ou sont toutes deux des figures (Roi, Dame, Valet).
     * * @param main La main sur laquelle le coup est envisagé.
     * @return true si la main peut être séparée, sinon false.
     */
    @Override
    public boolean conditionSpecifique(Main main){
        if (main.getJoueur().getMains().size() != 1){
            return false;
        }
        if( main.getMise() > main.getJoueur().getSolde() )
        {
            return false;
        }
        List<Carte> cartes = main.getPaquet().getCartes();
        if(cartes.size() == 2){
            Iterator<Carte> cartesIterator  = cartes.iterator();
            Carte carte1 = cartesIterator.next();
            Carte carte2 = cartesIterator.next();
            if( carte1.getHauteur().equals(carte2.getHauteur())){
                return true;
            }  
            if( (carte1.getHauteur().equals("Roi") 
                || carte1.getHauteur().equals("Dame")
                || carte1.getHauteur().equals("Valet")) 
                &&
                (carte2.getHauteur().equals("Roi") 
                || carte2.getHauteur().equals("Dame")
                || carte2.getHauteur().equals("Valet"))
                ){
                    return true;
                }

        }
        return false;
    }
}