package blackjack.test;

import blackjack.modele.coup.AssuranceCoup;
import blackjack.modele.coup.Coup;
import blackjack.modele.coup.DoubleCoup;
import blackjack.modele.coup.HitCoup;
import blackjack.modele.coup.SplitCoup;
import blackjack.modele.coup.StayCoup;
import blackjack.modele.joueur.AbstractJoueur;
import blackjack.modele.main.Main;
import blackjack.modele.main.MainAssure;
import blackjack.modele.main.MainBasic;
import blackjack.modele.main.MainSplite;
import cartes.modele.Carte;
import cartes.modele.Paquet;
import java.util.List;
import java.util.function.Consumer;
public class CoupTests {

    class JoueurMock extends AbstractJoueur {
        public JoueurMock(String nom, int solde) { super(nom, solde); }
        @Override public void choisirCoup(Main main, List<Coup> coups, Consumer<Coup> callback) {}
        @Override public void choisirMise(Consumer<Integer> callback) {}
    }

    public boolean testHitCoup() {
        JoueurMock j = new JoueurMock("Test", 100);
        MainBasic main = new MainBasic(j, 10);
        main.getPaquet().getCartes().clear();
        main.getPaquet().ajouterCarteDessous(new Carte("2", "Trèfle"));
        Paquet pioche = new Paquet();
        pioche.ajouterCarteDessus(new Carte("As", "Pique"));
        HitCoup hit = new HitCoup(pioche);
        if (!hit.conditionSpecifique(main)) return false;
        int toursAvant = main.getNbrTours();
        Main nouvelleMain = hit.executer(main);
        boolean nbCartesOk = nouvelleMain.getPaquet().getCartes().size() == 2;
        boolean toursOk = nouvelleMain.getNbrTours() > toursAvant; 
        return nbCartesOk && toursOk;
    }
    public boolean testStayCoup() {
        JoueurMock j = new JoueurMock("Test", 100);
        MainBasic main = new MainBasic(j, 10);
        StayCoup stay = new StayCoup();
        if (!stay.conditionSpecifique(main)) return false;
        int toursAvant = main.getNbrTours();
        Main nouvelleMain = stay.executer(main);
        boolean finTour = !nouvelleMain.peutJouer();
        boolean toursOk = nouvelleMain.getNbrTours() > toursAvant;
        return finTour && toursOk;
    }
    public boolean testDoubleCoup() {
        JoueurMock j = new JoueurMock("Test", 1000);
        MainBasic main = new MainBasic(j, 100); 
        main.getPaquet().getCartes().clear();
        main.getPaquet().ajouterCarteDessous(new Carte("5", "Trèfle"));
        main.getPaquet().ajouterCarteDessous(new Carte("6", "Trèfle")); 
        Paquet pioche = new Paquet();
        pioche.ajouterCarteDessus(new Carte("10", "Pique"));
        DoubleCoup doubleCoup = new DoubleCoup(pioche);
        j.setSolde(900); 
        if (!doubleCoup.conditionSpecifique(main)) return false;
        Main nouvelleMain = doubleCoup.executer(main);
        boolean miseDoublee = nouvelleMain.getMise() == 200;
        boolean soldeDebite = j.getSolde() == 800;
        boolean finTour = !nouvelleMain.peutJouer(); 
        return miseDoublee && soldeDebite && finTour;
    }
    public boolean testSplitCoup() {
        JoueurMock j = new JoueurMock("Test", 1000);
        MainBasic main = new MainBasic(j, 100);
        main.getPaquet().getCartes().clear();
        main.getPaquet().ajouterCarteDessous(new Carte("8", "Trèfle"));
        main.getPaquet().ajouterCarteDessous(new Carte("8", "Pique"));
        j.getMains().clear();
        j.getMains().add(main);
        Paquet pioche = new Paquet();
        pioche.ajouterCarteDessus(new Carte("Roi", "Cœur")); 
        pioche.ajouterCarteDessus(new Carte("Dame", "Cœur")); 
        SplitCoup split = new SplitCoup(pioche);
        if (!split.conditionSpecifique(main)) return false;
        Main resultat = split.executer(main);
        boolean estMainSplit = resultat instanceof MainSplite;
        boolean aDeuxMains = j.getMains().size() == 2;
        boolean soldeDebite = j.getSolde()== 900;
        return estMainSplit && aDeuxMains && soldeDebite;
    }
    public boolean testAssuranceCoup() {
        JoueurMock j = new JoueurMock("Test", 1000);
        MainBasic main = new MainBasic(j, 100);
        MainBasic mainCroupier = new MainBasic(null, 0);
        mainCroupier.getPaquet().getCartes().clear();
        mainCroupier.getPaquet().ajouterCarteDessous(new Carte("As", "Pique"));
        mainCroupier.getPaquet().ajouterCarteDessous(new Carte("Valet", "Cœur"));
        AssuranceCoup assurance = new AssuranceCoup(mainCroupier);
        if (!assurance.conditionSpecifique(main)) return false;
        Main resultat = assurance.executer(main);
        boolean estMainAssure = resultat instanceof MainAssure;
        boolean soldeDebite = j.getSolde() == 950; 
        return estMainAssure && soldeDebite;
    }
}