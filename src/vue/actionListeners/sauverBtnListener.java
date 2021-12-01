package vue.actionListeners;

import modele.Item;
import modele.Tirage;
import modele.gestionFichiers.experts.Expert;
import modele.gestionFichiers.experts.chargement.ExpertChargementCSV;
import modele.gestionFichiers.visiteur.sauvegarde.VisiteurSauvegardeCSV;
import vue.VotingProjectWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class sauverBtnListener implements ActionListener {

    public sauverBtnListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VotingProjectWindow window = VotingProjectWindow.getInstance();
        ArrayList<String> list = new ArrayList<>();
        Expert expertChargement = new ExpertChargementCSV(null);

        FileDialog fDialog = new FileDialog(window, "");
        fDialog.setTitle("Sauvegarder");
        fDialog.setMode(FileDialog.SAVE);
        fDialog.setVisible(true);

        Tirage.getInstance().accepteSauvegarde(new VisiteurSauvegardeCSV(fDialog.getDirectory()+fDialog.getFile()));

    }
}
