package blackjack.vue.vuesoldes;

import blackjack.modele.joueur.Joueur;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class VueSolde extends JPanel {

    private JTable table;
    private ModeleSolde modele;

    public VueSolde(List<Joueur> joueurs) {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(200, 150));

        this.modele = new ModeleSolde(joueurs);
        this.table = new JTable(modele);
        
   
        table.setOpaque(false);
        table.setShowGrid(true);
        table.setRowHeight(30);
        
   
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        
       
        table.setFocusable(false);
        
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void mettreAJour() {
        this.modele.fireTableDataChanged();
    }
}