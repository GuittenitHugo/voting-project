package modele.gestionFichiers.experts.chargement;

import modele.Item;
import modele.gestionFichiers.experts.ExpertCOR;
import modele.gestionFichiers.metier.gestionFichierCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ExpertChargementCSV extends ExpertCOR {
    public ExpertChargementCSV(ExpertCOR suivant) {
        super(suivant);
    }

    @Override
    protected ArrayList<Item> resoudre1(Object o) {
        try {
            if (o instanceof String) {
                String path = (String) o;
                if (path.endsWith(".csv"))
                    return new gestionFichierCSV().charger(path).getItems();
                return null;
            }
            else
                return null;
        }catch (IllegalArgumentException e) {
            return null;
        }
    }
}
