import java.awt.*;
import java.util.ArrayList;

import modele.Item;
import modele.Tirage;
import modele.gestionFichiers.experts.Expert;
import modele.gestionFichiers.experts.chargement.ExpertChargementCSV;
import modele.gestionFichiers.visiteur.sauvegarde.VisiteurSauvegardeCSV;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TestVotingProject {

	public static void main(String[] args) {
		
		final Tirage tirage = new Tirage(new ArrayList<>());

		Expert expertChargement = new ExpertChargementCSV(null);

		FileDialog fDialog = new FileDialog((Frame)null, "");
		fDialog.setTitle("Choisir un fichier à charger");
		fDialog.setMode(FileDialog.LOAD);
		fDialog.setVisible(true);

		tirage.setItems((ArrayList<Item>) expertChargement.resoudre(fDialog.getDirectory()+fDialog.getFile()));

		System.out.println(tirage.getResultatDernierTirage());

		tirage.effectuerTirages(1500);
		System.out.println(tirage.getResultatDernierTirage());
		System.out.println(tirage);

		fDialog.setTitle("Choisir ou sauvegarder le fichier");
		fDialog.setMode(FileDialog.SAVE);
		fDialog.setVisible(true);

		tirage.accepteSauvegarde(new VisiteurSauvegardeCSV(fDialog.getDirectory()+fDialog.getFile()));

		System.exit(0);
	}
}
