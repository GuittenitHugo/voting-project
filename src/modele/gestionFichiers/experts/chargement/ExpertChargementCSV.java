package modele.gestionFichiers.experts.chargement;

import modele.Item;
import modele.gestionFichiers.experts.ExpertCOR;

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
                String v = (String) o;
                if (v.endsWith(".csv"))
                    return chargerFichier(v);
                return null;
            }
            else
                return null;
        }catch (IllegalArgumentException e) {
            return null;
        }
    }

    private ArrayList<Item> chargerFichier(String path){
        try{
            Scanner reader = new Scanner(new File(path));
            ArrayList<Item> items = new ArrayList<>();
            if(reader.hasNextLine()){
                reader.nextLine();
                ArrayList<String> res = new ArrayList<>();
                String line;
                String[] resList;
                while(reader.hasNextLine()){
                    line = reader.nextLine();
                    resList = line.contains(";") ? line.split(";") : line.split(",");
                    res = new ArrayList<>(Arrays.asList(resList));
                    items.add(
                            new Item(
                                    res.get(0),
                                    Integer.parseInt(res.get(1)),
                                    Double.parseDouble(res.get(2).replaceFirst(",","."))
                            )
                    );
                }
                reader.close();
                return items;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
