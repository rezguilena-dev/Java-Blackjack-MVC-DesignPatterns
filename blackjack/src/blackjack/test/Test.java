package blackjack.test;
import blackjack.test.CoupTests;
import blackjack.test.JoueurTests;
import blackjack.test.MainTests;

public class Test {
    public static void main(String[] args) {
        System.out.println("=== Lancement des tests du Blackjack ===");
        boolean ok = true;
        MainTests mainTester = new MainTests();
        ok = ok && mainTester.testCalculValeurSimple();
        ok = ok && mainTester.testCalculValeurAs();
        ok = ok && mainTester.testResoudre();
        CoupTests coupTester = new CoupTests();
        ok = ok && coupTester.testHitCoup();
        ok = ok && coupTester.testDoubleCoup();
        ok = ok && coupTester.testStayCoup();
        ok = ok && coupTester.testSplitCoup();
        ok = ok && coupTester.testAssuranceCoup();
        JoueurTests joueurTester = new JoueurTests();
        ok = ok && joueurTester.testCreationEtSolde();
        ok = ok && joueurTester.testGestionDesMains();
        ok = ok && joueurTester.testAppliquerCoup();
        
        System.out.println(ok ? "All tests OK" : "At least one test KO");
    }
}