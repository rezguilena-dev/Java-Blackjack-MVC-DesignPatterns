package blackjack.test;
import blackjack.modele.joueur.AbstractJoueur;
import blackjack.modele.main.Main;
import blackjack.modele.main.MainBasic;
import blackjack.modele.coup.Coup;
import blackjack.modele.coup.AbstractCoup;
import java.util.List;
import java.util.function.Consumer;
public class JoueurTests {
    class JoueurMock extends AbstractJoueur {
        public JoueurMock(String nom, int solde) {
            super(nom, solde);
        }
        @Override public void choisirCoup(Main main, List<Coup> coups, Consumer<Coup> callback) {}
        @Override public void choisirMise(Consumer<Integer> callback) {}
    }
    class CoupMock extends AbstractCoup {
        @Override
        public Main executer(Main main) {
            return new MainBasic(main.getJoueur(), main.getMise() + 1); 
        }
        @Override
        public boolean conditionSpecifique(Main main) { return true; }
    }
    public boolean testCreationEtSolde() {
        JoueurMock j = new JoueurMock("Testeur", 500);
        boolean nomOk = j.getNom().equals("Testeur");
        boolean soldeOk = j.getSolde() == 500;
        boolean peutMiserOk = j.peutMiser(); 
        j.setSolde(0);
        boolean nePeutPasMiser = !j.peutMiser(); 
        return nomOk && soldeOk && peutMiserOk && nePeutPasMiser;
    }
    public boolean testGestionDesMains() {
        JoueurMock j = new JoueurMock("Testeur", 500);
        if (j.peutJouer()) return false; 
        MainBasic main = new MainBasic(j, 10);
        j.getMains().add(main);
        return j.peutJouer() && j.getMains().size() == 1;
    }
    public boolean testAppliquerCoup() {
        JoueurMock j = new JoueurMock("Testeur", 1000);
        MainBasic mainInitiale = new MainBasic(j, 50);
        j.getMains().add(mainInitiale);
        CoupMock coup = new CoupMock();
        j.appliquerCoup(mainInitiale, coup);
        List<Main> mains = j.getMains();
        boolean tailleOk = mains.size() == 1;
        boolean mainChangee = mains.get(0) != mainInitiale;
        boolean miseModifiee = mains.get(0).getMise() == 51;
        return tailleOk && mainChangee && miseModifiee;
    }
}