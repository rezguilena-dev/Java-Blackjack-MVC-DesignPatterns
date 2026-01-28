package cartes.test;

import cartes.modele.Carte;

public class CarteTests {

    public boolean testInitialisationCarte() {
        Carte c = new Carte("As", "Piques");
        return c.getHauteur().equals("As") && c.getCouleur().equals("Piques");
    }

    public boolean testToString() {
        Carte c = new Carte("Roi", "Cœurs");
        return c.toString().equals("Roi de Cœurs");
    }
}