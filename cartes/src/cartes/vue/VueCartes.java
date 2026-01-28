package cartes.vue;

import cartes.modele.*;
import java.awt.*;
import javax.swing.*;

/**
 * Fenêtre principale de l'application de jeu de cartes.
 * Cette classe hérite de JFrame et organise l'affichage des trois zones principales :
 * la pioche (cachée), la main du joueur (visible) et la défausse (visible).
 */
public class VueCartes extends JFrame{

    VueCache vuePioche;
    VueVisible vueMain ;
    VueVisible vueDefausse;

    /**
     * Construit la fenêtre principale et initialise les composants graphiques.
     * Configure la mise en page en grille et instancie les vues pour la pioche, la main et la défausse
     * en les liant aux modèles de données correspondants.
     *
     * @param pioche Le paquet représentant la pioche.
     * @param main Le paquet représentant la main du joueur.
     * @param defausse Le paquet représentant la défausse.
     */
    public VueCartes(Paquet pioche ,Paquet main , Paquet defausse ){
        this.setTitle("Jeu de 52 cartes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));
        vuePioche = new VueCache("pioche",pioche);
        vueMain = new VueVisible("main",main);
        vueDefausse = new VueVisible("defausse",defausse);



        this.add(vuePioche);
        this.add(vueMain);
        this.add(vueDefausse);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Retourne la vue correspondant à la pioche.
     *
     * @return La vue cachée de la pioche.
     */
    public VueCache getVuePioche(){
        return this.vuePioche;
    }

    /**
     * Retourne la vue correspondant à la main du joueur.
     *
     * @return La vue visible de la main.
     */
    public VueVisible getVueMain(){
        return this.vueMain;
    }

    /**
     * Retourne la vue correspondant à la défausse.
     *
     * @return La vue visible de la défausse.
     */
    public VueVisible getVueDefausse(){
        return this.vueDefausse;
    }
    
}