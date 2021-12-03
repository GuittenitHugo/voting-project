package vue.actionListeners;

import modele.Item;
import modele.Tirage;
import modele.gestionFichiers.experts.Expert;
import modele.gestionFichiers.experts.chargement.ExpertChargementCSV;
import modele.gestionFichiers.metier.Extension;
import vue.VotingProjectWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class chargerBtnListener implements ActionListener {

    private Expert expertChargement;

    public chargerBtnListener() {
        expertChargement = new ExpertChargementCSV(null);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void actionPerformed(ActionEvent e) {
        Tirage t = Tirage.getInstance();
        VotingProjectWindow window = VotingProjectWindow.getInstance();
        ArrayList<String> list = new ArrayList<>();
        expertChargement = new ExpertChargementCSV(null);
        String extensions = "";

        for (Extension extension: Extension.values()) {
            extensions+="*"+extension.getExtension()+";";
        }

        FileDialog fDialog = new FileDialog(window, "");
        fDialog.setTitle("Ouvrir");
        fDialog.setFile(extensions);
        fDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                for (Extension extension: Extension.values()) {
                    if(name.endsWith(extension.getExtension()))
                        return true;
                }
                return false;
            }
        });
        fDialog.setMode(FileDialog.LOAD);
        fDialog.setVisible(true);

        if(fDialog.getFile()!=null) {
        	t.setItems((ArrayList<Item>) expertChargement.resoudre(fDialog.getDirectory()+fDialog.getFile()));
            window.updateList(window.convertTirageToArray());
        }
    }
}
