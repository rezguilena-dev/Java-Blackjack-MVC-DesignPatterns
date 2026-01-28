package cartes.test;
import cartes.test.*;

public class Test {
    public static void main(String[] args) {
        boolean ok = true;
        CarteTests carteTester = new CarteTests();
        ok = ok && carteTester.testInitialisationCarte();
        ok = ok && carteTester.testToString();
        PaquetTests paquetTester = new PaquetTests();
        ok = ok && paquetTester.testAjoutCarteDessus();
        ok = ok && paquetTester.testAjoutCarteDessous();
        ok = ok && paquetTester.testRetirerCarte();
        ok = ok && paquetTester.testTrierParHauteur();
        ok = ok && paquetTester.testMelanger();
        PaquetFactoryTests factoryTester = new PaquetFactoryTests();
        ok = ok && factoryTester.testCreerPaquet52();
        System.out.println(ok ? "All tests OK" : "At least one test KO");
    }
}