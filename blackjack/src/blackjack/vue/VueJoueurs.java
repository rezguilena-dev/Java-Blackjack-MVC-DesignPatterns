package blackjack.vue;
import blackjack.modele.joueur.Joueur;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Panneau de vue regroupant tous les joueurs participant à la partie de Blackjack.
 * Cette classe organise horizontalement les vues individuelles de chaque joueur (VueJoueur).
 */
public class VueJoueurs extends JPanel{
    /**
     * La liste des modèles de joueurs associés à cette vue.
     */
    private List<Joueur> joueurs;
    /**
     * La liste des vues individuelles des joueurs affichées sur ce panneau.
     */
    public List<VueJoueur> vueJoueurs;

    /**
     * Construit le panneau des joueurs en créant une VueJoueur pour chaque joueur de la liste.
     * Les vues sont disposées horizontalement à l'aide d'un BoxLayout.
     *
     * @param joueurs La liste des modèles de joueurs à afficher.
     */
    public VueJoueurs(List<Joueur> joueurs){
        super();
        this.joueurs = joueurs;
        this.vueJoueurs = new ArrayList<>();
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        for (Joueur joueur : joueurs) {
            VueJoueur vueJoueur =new VueJoueur(joueur); 
            add(vueJoueur);
            vueJoueurs.add(vueJoueur);
        }
    }

    /**
     * Redessine récursivement tous les composants enfants (VueJoueur).
     */
    @Override
    public void repaint() {
        if(vueJoueurs != null)
            for(VueJoueur vjoueur: vueJoueurs){
                vjoueur.repaint();
            }
    }
    
    /**
     * Retourne la liste des vues individuelles des joueurs.
     *
     * @return La liste des objets VueJoueur.
     */
    public List<VueJoueur> getVueJoueurs(){
        return vueJoueurs;
    }
}