package modele.gestionFichiers.metier;

import modele.Tirage;

public abstract class gestionFichier {
    public abstract Tirage charger(String path);
    public abstract boolean sauvegarder(Tirage t, String path);
}
