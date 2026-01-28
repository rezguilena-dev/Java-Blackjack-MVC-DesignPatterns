package blackjack.modele.main;

import blackjack.modele.joueur.Joueur;
import cartes.modele.Paquet;

public interface Main {
    public int getMise();
    public void setMise(int mise);
    public Joueur getJoueur();
    public int getValeur();
    public int getNbrTours();
    public void incNbrTours();
    public boolean peutJouer();
    public Paquet getPaquet();
    public void resoudre(Main mainCroupier);
}
