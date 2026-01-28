package blackjack.vue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.*;

/**
 * Représente le panneau de l'interface utilisateur pour la saisie de la mise du joueur.
 * Cette vue permet au joueur d'entrer un montant de mise et de le valider via un bouton.
 */
public class VueMise extends VueCommande{
    
    /**
     * Le champ de texte où l'utilisateur saisit le montant de la mise.
     */
    private JTextField miseText;
    
    /**
     * Le bouton pour valider la mise saisie.
     */
    private JButton ajouterMiseButton;

    /**
     * Construit un nouveau panneau de saisie de mise.
     * Configure la mise en page pour centrer les éléments.
     */
    public VueMise(){
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    /**
     * Initialise et configure les composants du panneau de mise.
     * Crée le champ de texte et le bouton, et y attache l'écouteur d'événements
     * pour récupérer la mise saisie par l'utilisateur et l'envoyer via le callback.
     *
     * @param callback La fonction de rappel à exécuter avec la mise saisie.
     * @param solde Le solde actuel du joueur (utilisé pour contextualiser l'affichage).
     */
    protected void initialiserComposant(Consumer<Integer> callback ,int solde ){        
        this.removeAll();

        miseText = new JTextField(10);
        ajouterMiseButton = new JButton("Ajouter Mise");
        ajouterMiseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e ){
                int mise;
                try{
                    mise = Integer.parseInt(miseText.getText());
                }
                catch(Exception exc){
                    mise=-1;   
                }
                callback.accept(mise);    
            }
        });
        add(new JLabel("Veuillez saisir une mise:"));
        add(miseText);
        add(ajouterMiseButton);

    }
}