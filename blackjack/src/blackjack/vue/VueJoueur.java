package blackjack.vue;

import blackjack.modele.joueur.Joueur;
import blackjack.modele.main.Main;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


/**
 * Représente la vue graphique d'un joueur dans l'interface de Blackjack.
 * Ce panneau affiche le nom du joueur et contient les vues de toutes les mains qu'il possède.
 */
public class VueJoueur extends JPanel {
    
    /**
     * Le panneau interne qui contient et organise toutes les vues des mains (VueMain) du joueur.
     */
    private final JPanel panneauDesMains;
    
    /**
     * La liste des vues des mains (VueMain) actuellement affichées pour ce joueur.
     */
    private final List<VueMain> vueMains;
    
    /**
     * Le modèle de données du joueur associé à cette vue.
     */
    private final Joueur joueur;

    /**
     * Construit un panneau de vue pour le joueur spécifié.
     * Initialise la mise en page, ajoute le nom du joueur en haut et prépare le panneau pour l'affichage des mains.
     * * @param joueur Le modèle de joueur à afficher.
     */
    public VueJoueur(Joueur joueur) {
        super();
        this.joueur = joueur;
        vueMains =new ArrayList<>();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setPreferredSize(new Dimension(100,100));
        JLabel nomJoueurLabel = new JLabel(joueur.getNom());
        nomJoueurLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nomJoueurLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomJoueurLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(nomJoueurLabel, BorderLayout.NORTH);
        this.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
        panneauDesMains = new JPanel();
        panneauDesMains.setLayout(new BoxLayout(panneauDesMains, BoxLayout.X_AXIS));
        this.add(panneauDesMains, BorderLayout.CENTER);
    }
    
    /**
     * Met à jour l'affichage des mains du joueur. 
     * Cette méthode nettoie le panneau des mains et le reconstruit en créant une VueMain pour chaque objet Main dans la liste du joueur.
     */
    public void mettreAJourMains(){
        if(panneauDesMains != null) {
            panneauDesMains.removeAll();
        }
        
        vueMains.clear(); 
        
        if( this.joueur != null ){
            for (Main main : joueur.getMains()) {
                VueMain vMain = new VueMain(main);
                vueMains.add(vMain);
                panneauDesMains.add(vMain);
                panneauDesMains.add(Box.createHorizontalStrut(5));
            }
        }
        this.revalidate();
    }

    /**
     * Retourne la liste des vues de mains actuellement affichées pour ce joueur.
     * * @return La liste des objets VueMain.
     */
    public List<VueMain> getVueMains(){
        return vueMains;
    }

    /**
     * Retourne le modèle de données du joueur associé à cette vue.
     * * @return Le joueur modèle.
     */
    public Joueur getJoueur(){
        return joueur;
    }
}