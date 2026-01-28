package blackjack.modele.main;

/**
 * Représente une main de Blackjack protégée par une assurance.
 * Cette classe utilise le pattern Décorateur pour ajouter la logique de gain de l'assurance
 * à une main existante lorsque le croupier obtient un Blackjack.
 */
public class MainAssure extends AbstractMain {

    /**
     * Le montant de l'assurance placée sur cette main.
     */
    private final int assurance;

    /**
     * Construit une nouvelle main assurée.
     *
     * @param mainDecore La main d'origine sur laquelle l'assurance est appliquée.
     * @param assurance Le montant de la mise d'assurance.
     */
    public MainAssure(Main mainDecore, int assurance) {
        super(mainDecore);
        this.assurance=assurance;
    }

    /**
     * Résout la main à la fin de la partie en tenant compte de l'assurance.
     * Cette méthode appelle d'abord la résolution de la main décorée, puis vérifie si le croupier a un Blackjack naturel.
     * Si le croupier a Blackjack, le joueur récupère le montant de son assurance.
     *
     * @param mainCroupier La main du croupier utilisée pour la comparaison.
     */
    @Override
    public void resoudre(Main mainCroupier){
        mainDecore.resoudre(mainCroupier);
        if( mainCroupier.getValeur() == 21 && mainCroupier.getNbrTours() == 0 ){
            mainDecore.getJoueur().setSolde(mainDecore.getJoueur().getSolde() + assurance);
        }
    }
    
}