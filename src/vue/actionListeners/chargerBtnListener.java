package vue.actionListeners;

import modele.Item;
import modele.Tirage;
import modele.gestionFichiers.experts.Expert;
import modele.gestionFichiers.experts.chargement.ExpertChargementCSV;
import vue.VotingProjectWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class chargerBtnListener implements ActionListener {

    private Expert expertChargement;

    public chargerBtnListener() {
        expertChargement = new ExpertChargementCSV(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VotingProjectWindow window = VotingProjectWindow.getInstance();
        ArrayList<String> list = new ArrayList<>();
        Expert expertChargement = new ExpertChargementCSV(null);

        FileDialog fDialog = new FileDialog(window, "");
        fDialog.setTitle("Ouvrir");
        fDialog.setMode(FileDialog.LOAD);
        fDialog.setVisible(true);

        Tirage.getInstance().setItems((ArrayList<Item>) expertChargement.resoudre(fDialog.getDirectory()+fDialog.getFile()));
        for (Item item:Tirage.getInstance().getItems()) {
            list.add(item.getNom()+" "+item.getQuantite()+" "+item.getProbabilite());
        }
        window.updateList(list);
    }
}
