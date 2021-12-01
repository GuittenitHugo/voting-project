package modele.gestionFichiers.metier;

import modele.Item;
import modele.Tirage;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class gestionFichierCSV extends gestionFichier{

    @Override
    public Tirage charger(String path){
        try{
            Scanner reader = new Scanner(new File(path));
            Tirage t = new Tirage(new ArrayList<>());
            if(reader.hasNextLine()){
                String line = reader.nextLine();
                if(line.matches(".*[0-9]+.*")) stringIntoTirage(line, t);
                while(reader.hasNextLine()){
                    line = reader.nextLine();
                    stringIntoTirage(line, t);
                }
                reader.close();
                return t;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void stringIntoTirage(String line, Tirage t){
        ArrayList<String> res = new ArrayList<>();
        String[] resList;
        resList = line.contains(";") ? line.split(";") : line.split(",");
        res = new ArrayList<>(Arrays.asList(resList));
        t.AjouterItem(
                new Item(
                        res.get(0),
                        Integer.parseInt(res.get(1)),
                        Double.parseDouble(res.get(2).replaceFirst(",",".").replaceAll("\"",""))
                )
        );
    }

    @Override
    public boolean sauvegarder(Tirage t, String path) {
        if(path.isBlank())
        try{
            FileWriter writer = new FileWriter(path);
            boolean hasDernierTirage = t.getResultatDernierTirage() != "Aucun tirage effectu� derni�rement";

            ArrayList<Item> tItems = t.getItems();
            ArrayList<Integer> tDernierTirage = t.getNbItems();
            double probaGlobale = t.getProbaGlobale();
            Item item;

            writer.write("Nom,Quantit�,Probabilit�");
            if(hasDernierTirage)
                writer.write(",R�sultats dernier tirage");
            writer.write("\n");
            for(int i = 0; i < t.getItems().size(); i++){
                item = tItems.get(i);
                writer.write(
                        item.getNom()+","+
                                item.getQuantite()+",\""+
                                new BigDecimal(item.getProbabilite()/probaGlobale).toPlainString().replaceFirst("\\.", ",") +"\""
                );
                if(hasDernierTirage)
                    writer.write(","+tDernierTirage.get(i));
                writer.write("\n");
            }
            writer.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
