package blackjack.modele.main;

/**
 * Représente une main pour laquelle le joueur a décidé de "Rester" (Stay).
 * Cette classe décore une main existante pour signifier la fin du tour du joueur pour cette main spécifique.
 * Une fois cet état atteint, aucune autre action n'est possible sur la main.
 */
public class MainStay extends AbstractMain {

    /**
     * Construit une main en état "Rester".
     *
     * @param mainDecore La main sur laquelle le joueur a décidé de s'arrêter.
     */
    public MainStay(Main mainDecore) {
        super(mainDecore);
    }

    /**
     * Indique si la main peut encore être jouée.
     * Retourne toujours false car l'action "Rester" met fin au tour pour cette main.
     *
     * @return false, car le joueur a terminé ses actions sur cette main.
     */
    @Override
    public boolean peutJouer(){
        return false;
    }
    
}