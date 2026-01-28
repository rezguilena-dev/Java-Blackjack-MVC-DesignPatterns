package blackjack.test;

import blackjack.modele.joueur.AbstractJoueur;
import blackjack.modele.joueur.Joueur;
import blackjack.modele.main.MainBasic;
import blackjack.modele.coup.Coup;
import cartes.modele.Carte;
import java.util.List;
import java.util.function.Consumer;

public class MainTests {

    class JoueurMock extends AbstractJoueur {
        public JoueurMock(String nom, int solde) { super(nom, solde); }
        
        @Override public void choisirCoup(blackjack.modele.main.Main main, List<Coup> coups, Consumer<Coup> callback) {}
        @Override public void choisirMise(Consumer<Integer> callback) {}
    }

    public boolean testCalculValeurSimple() {
        Joueur j = new JoueurMock("Test", 100);
        MainBasic main = new MainBasic(j, 10);
        
        main.getPaquet().getCartes().clear();
        
        main.getPaquet().ajouterCarteDessous(new Carte("10", "Pique"));
        main.getPaquet().ajouterCarteDessous(new Carte("5", "Cœur"));
        
        if (main.getValeur() != 15) return false;

        main.getPaquet().ajouterCarteDessous(new Carte("Roi", "Trèfle"));
        
        return main.getValeur() == 25;
    }

    public boolean testCalculValeurAs() {
        Joueur j = new JoueurMock("Test", 100);
        MainBasic main = new MainBasic(j, 10);
        main.getPaquet().getCartes().clear();

        main.getPaquet().ajouterCarteDessous(new Carte("As", "Pique"));
        main.getPaquet().ajouterCarteDessous(new Carte("9", "Cœur"));
        
        if (main.getValeur() != 20) return false;

        main.getPaquet().ajouterCarteDessous(new Carte("5", "Carreau"));
        
        return main.getValeur() == 15;
    }

    public boolean testResoudre() {
        Joueur j = new JoueurMock("Joueur", 1000);
        MainBasic mainJoueur = new MainBasic(j, 100);         
        mainJoueur.getPaquet().getCartes().clear();
        mainJoueur.getPaquet().ajouterCarteDessous(new Carte("Roi", "Pique")); 
        mainJoueur.getPaquet().ajouterCarteDessous(new Carte("Dame", "Pique")); 
        MainBasic mainCroupier = new MainBasic(null, 0); 
        mainCroupier.getPaquet().getCartes().clear();
        mainCroupier.getPaquet().ajouterCarteDessous(new Carte("10", "Cœur")); 
        mainCroupier.getPaquet().ajouterCarteDessous(new Carte("8", "Cœur"));  
        j.setSolde(900); 
        mainJoueur.resoudre(mainCroupier);
        return j.getSolde() == 1100;
    }
}