package blackjack.vue.vuesoldes;

import blackjack.modele.joueur.Joueur;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModeleSolde extends AbstractTableModel {
    
    private final List<Joueur> joueurs;
    private final String[] colonnes = {"Joueur", "Solde"};

    public ModeleSolde(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public int getRowCount() {
        return joueurs.size();
    }

    @Override
    public int getColumnCount() {
        return colonnes.length;
    }

    @Override
    public String getColumnName(int column) {
        return colonnes[column];
    }

    @Override
    public Object getValueAt(int ligne, int colonne) {
        Joueur j = joueurs.get(ligne);
        if (colonne == 0) {
           
            return j.getNom(); 
        } else {
            return j.getSolde() + " $";
        }
    }
}