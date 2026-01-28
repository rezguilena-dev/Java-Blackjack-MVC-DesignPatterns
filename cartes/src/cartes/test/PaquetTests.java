package cartes.test;

import cartes.modele.Carte;
import cartes.modele.Paquet;
import java.util.List;

public class PaquetTests {

    public boolean testAjoutCarteDessus() {
        Paquet p = new Paquet();
        Carte c = new Carte("As", "Trèfles");
        p.ajouterCarteDessus(c);
        
        
        return p.getCartes().size() == 1 && p.getCartes().get(0) == c;
    }

    public boolean testAjoutCarteDessous() {
        Paquet p = new Paquet();
        Carte c1 = new Carte("As", "Trèfles");
        Carte c2 = new Carte("10", "Piques");
        
        p.ajouterCarteDessus(c1);
        p.ajouterCarteDessous(c2);
        
        
        return p.getCartes().size() == 2 && p.getCartes().get(1) == c2;
    }

    public boolean testRetirerCarte() {
        Paquet p = new Paquet();
        Carte c = new Carte("Dame", "Carreaux");
        p.ajouterCarteDessus(c);
        
        Carte retire = p.retirerPremiereCarte();
        
        return retire == c && p.getCartes().isEmpty();
    }

    public boolean testTrierParHauteur() {
        Paquet p = new Paquet();
        p.ajouterCarteDessous(new Carte("Roi", "Piques")); 
        p.ajouterCarteDessous(new Carte("As", "Cœurs"));   
        p.ajouterCarteDessous(new Carte("2", "Carreaux")); 

        p.trierParHauteur();
        List<Carte> cartes = p.getCartes();


        return cartes.get(0).getHauteur().equals("As") 
            && cartes.get(1).getHauteur().equals("2") 
            && cartes.get(2).getHauteur().equals("Roi");
    }

    public boolean testMelanger() {
        Paquet p = new Paquet();
        for(int i=0; i<10; i++) {
            p.ajouterCarteDessus(new Carte("As", "Piques"));
        }
        p.melangerJeu();
        return p.getCartes().size() == 10;
    }
}