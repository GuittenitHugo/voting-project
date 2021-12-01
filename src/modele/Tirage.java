package modele;

import modele.gestionFichiers.visiteur.Visiteur;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Tirage {

	private ArrayList<Item> items;
	private double probaTotale;
	private ArrayList<Integer> nbItems;

	private static Tirage instance;

	public static Tirage getInstance(){
		if (instance==null){
			instance=new Tirage();
		}
		return instance;
	}

	private Tirage(){
		items = new ArrayList<>();
		nbItems = new ArrayList<>();
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
		nbItems = new ArrayList<>();
		calculProbaTotaleTirage(items);
	}
	
	public void AjouterItem(Item item) {
		this.items.add(item);
		nbItems = new ArrayList<>();

		probaTotale+= item.getProbabilite();
	}
	
	public void RetirerItem(Item item) {
		if (this.items.remove(item)){
			probaTotale-=item.getProbabilite();
			nbItems = new ArrayList<>();
		}
	}

	public double getProbaGlobale() {
		return probaTotale;
	}

	private void calculProbaTotaleTirage(ArrayList<Item> items) {
		probaTotale = 0;
		for (Item item : items) {
			probaTotale+=item.getProbabilite();
		}
	}
	
	private int faireTirage() throws IllegalStateException {
		if(items.size() <= 0) throw new IllegalStateException("Aucun item détecté pour le tirage");
		double resTirage = probaTotale*Math.random();
		double probaCumulee = 0;
		for (Item item : items) {
			probaCumulee += item.getProbabilite();
			if (resTirage <= probaCumulee)
				return items.indexOf(item);
		}
		return -1;
	}
	
	public void effectuerTirages(int nbTirages) throws IllegalStateException,IllegalArgumentException{
		if(nbTirages<=0) throw new IllegalArgumentException("Nombre de tirages invalide, donnez une valeur positive");
		nbItems = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {nbItems.add(0);}
		
		int resTirage;
		for(int i=0; i<nbTirages; i++) {
			resTirage = faireTirage();
			nbItems.set(resTirage, nbItems.get(resTirage)+1);
		}
	}

	public ArrayList<Integer> getNbItems(){
		return nbItems;
	}
	
	public String getResultatDernierTirage(){
		String resultatString;
		if(nbItems == null) resultatString = "Aucun tirage effectué dernièrement";
		else {
			int nbTirages;
			Item item;
			resultatString = "Résultats du dernier tirage";
			for(int i = 0; i < items.size(); i++) {
				nbTirages = nbItems.get(i);
				item = items.get(i);
				if (nbTirages>0)
					resultatString+="\n\""+item.getQuantite() +"x " + item.getNom() + "\" : tiré " + nbTirages+" fois. Total = "+ item.getQuantite()*nbTirages;
			}
		}
		return resultatString;
	}

	@Override
	public String toString() {
		return "Tirage [items=" + items + ", probaTotale=" + probaTotale + "]";
	}
	
	public void accepteSauvegarde(Visiteur v){v.visite(this);}
	
}
