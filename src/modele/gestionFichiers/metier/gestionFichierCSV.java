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
            Tirage t = Tirage.getInstance();
            if(reader.hasNextLine()){
                String line = reader.nextLine();
                if(line.matches(".*[0-9]+.*")) stringIntoTirage(line, t);
                while(reader.hasNextLine()){
                    line = reader.nextLine();
                    stringIntoTirage(line, t);
                }
                reader.close();
                System.out.println(t.getItems());
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
        resList = line.contains(";") ? line.split(";") : line.split(",",3);
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
    public boolean sauvegarder(String path) {
        if(path.isBlank())
            return false;
        try{
            Tirage t = Tirage.getInstance();
            FileWriter writer = new FileWriter(path);
            boolean hasDernierTirage = t.getResultatDernierTirage() != "Aucun tirage effectué dernièrement";

            ArrayList<Item> tItems = t.getItems();
            double probaGlobale = t.getProbaGlobale();
            Item item;

            writer.write("Nom,Quantité,Probabilité");
            if(hasDernierTirage)
                writer.write(",Résultats dernier tirage");
            writer.write("\n");
            for(int i = 0; i < t.getItems().size(); i++){
                item = tItems.get(i);
                writer.write(
                        item.getNom()+","+
                                item.getQuantite()+",\""+
                                new BigDecimal(item.getProbabilite()/probaGlobale).toPlainString().replaceFirst("\\.", ",") +"\""
                );
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
