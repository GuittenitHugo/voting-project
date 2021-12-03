package modele.gestionFichiers.visiteur.sauvegarde;

import modele.Tirage;
import modele.gestionFichiers.metier.gestionFichierCSV;
import modele.gestionFichiers.visiteur.Visiteur;

public class VisiteurSauvegardeCSV extends Visiteur {

    String path;

    public VisiteurSauvegardeCSV(String path){
        super();
        this.path = path;
    }
    @Override
    public void visite(Tirage t) { new gestionFichierCSV().sauvegarder(path); };
}
