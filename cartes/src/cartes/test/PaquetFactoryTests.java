package cartes.test;

import cartes.modele.Paquet;
import cartes.modele.PaquetFactory;

public class PaquetFactoryTests {

    public boolean testCreerPaquet52() {
        Paquet p = PaquetFactory.creerPaquet52();
        return p.getCartes().size() == 52;
    }
}