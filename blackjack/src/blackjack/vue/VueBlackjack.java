package blackjack.vue;
import blackjack.controleur.joueur.JoueurGraphique;
import blackjack.modele.Blackjack;
import blackjack.modele.coup.Coup;
import blackjack.modele.joueur.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;
import util.Ecouteur;

/**
 * Fenêtre principale de l'application Blackjack.
 * Cette classe hérite de JFrame et assemble les différentes vues partielles (informations jeu, joueurs, contrôles)
 * pour former l'interface graphique complète. Elle implémente l'interface Ecouteur pour réagir aux changements du modèle.
 */
public class VueBlackjack extends JFrame implements Ecouteur{

    private Blackjack blackjack;
    private JPanel southControlsPanel;
    private VueInfosJeu vueInfo;
    private VueJoueurs vueDuJeu;
    private VueMise vueMise ;
    private VueCoup vueCoup ;

    /**
     * Construit la fenêtre principale du jeu.
     * Initialise les composants graphiques, configure la mise en page et rend la fenêtre visible.
     *
     * @param blackjack Le modèle du jeu de Blackjack à afficher.
     */
    public VueBlackjack(Blackjack blackjack) {
        super("Blackjack");
        this.blackjack = blackjack; 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(34, 139, 34)); 

        List<Joueur> listeJoueurs = blackjack.getJoueurs();
        this.vueInfo = new VueInfosJeu(listeJoueurs, blackjack.getMainCroupier());
        this.add(vueInfo, BorderLayout.NORTH);
        vueDuJeu = new VueJoueurs(listeJoueurs); 
        vueMise = new VueMise();
        vueCoup = new VueCoup();
        southControlsPanel = new JPanel();
        southControlsPanel.setLayout(new BoxLayout(southControlsPanel, BoxLayout.X_AXIS));

        this.add(vueDuJeu, BorderLayout.CENTER);
        this.add(southControlsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    /**
     * Affiche une boîte de dialogue modale contenant un message pour l'utilisateur.
     *
     * @param message Le contenu du message à afficher.
     * @param titre Le titre de la fenêtre de dialogue.
     * @param typeMessage Le type de message (informatif, avertissement, erreur, etc.), défini par les constantes de JOptionPane.
     */
    public void afficherMessage(String message, String titre, int typeMessage) {
        JOptionPane.showMessageDialog(this, message, titre, typeMessage);
    }

    /**
     * Met à jour le panneau de commandes pour afficher les boutons correspondant aux coups possibles.
     *
     * @param coups La liste des coups disponibles pour le joueur.
     * @param callback La fonction à exécuter lorsque le joueur clique sur un bouton de coup.
     */
    public void afficherCoups(List<Coup> coups, Consumer<Coup> callback){
        southControlsPanel.removeAll();
        vueCoup.initialiserComposant(coups, callback);
        southControlsPanel.add(vueCoup);   
        vueDuJeu.repaint();
        revalidate();
        repaint();  
    }
    
    /**
     * Met à jour le panneau de commandes pour afficher l'interface de saisie de la mise.
     *
     * @param callback La fonction à exécuter lorsque le joueur valide sa mise.
     */
    public void afficherMise(Consumer<Integer> callback){
        southControlsPanel.removeAll();
        vueMise.initialiserComposant(callback, blackjack.getJoueurs().get(0).getSolde());
        southControlsPanel.add(vueMise); 
        revalidate();
        repaint();  
    }

    /**
     * Modifie la visibilité des cartes du croupier.
     *
     * @param afficher true pour afficher les cartes face visible, false pour les cacher (ou n'en montrer qu'une selon la règle).
     */
    public void afficherMainCroupier(boolean afficher){
        vueInfo.getVueCroupier().setAfficheCartes(afficher);
    }
    
    /**
     * Méthode appelée lorsque le modèle notifie un changement.
     * Met à jour l'affichage des mains des joueurs, met en évidence la main courante,
     * et active ou désactive les contrôles selon si c'est au tour d'un joueur graphique de jouer.
     *
     * @param obj L'objet modèle ayant déclenché la mise à jour (instance de Blackjack).
     */
    @Override
    public void miseAjour(Object obj) {
        Blackjack jeu = (Blackjack)(obj);
        for(VueJoueur vueJoueur : vueDuJeu.getVueJoueurs()){
            vueJoueur.mettreAJourMains(); 
        }for(VueJoueur vueJoueur : vueDuJeu.getVueJoueurs()){
            for(VueMain vueMain : vueJoueur.getVueMains()){
                boolean estCourante = (vueMain.getMain() == jeu.getMainCourante());
                vueMain.highlightMain(estCourante);
            }
        }
        Joueur joueurCourant = jeu.getJoueurCourant();
        if (joueurCourant instanceof JoueurGraphique) {
            vueCoup.setBoutonsActifs(true);
        } 
        else {
            vueCoup.setBoutonsActifs(false);
        }
        revalidate();
        repaint();
        
    }

    /**
     * Affiche un message d'erreur à l'utilisateur.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void afficherErreur(String message){
        
    }

}