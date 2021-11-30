package modele;

import java.util.ArrayList;

public class Tirage {

	private ArrayList<Item> items;
	private double probaTotale;
	private ArrayList<Integer> nbItems;
	
	public Tirage(ArrayList<Item> items) {
		super();
		setItems(items);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
		calculProbaTotaleTirage(items);
	}
	
	public void AjouterItem(Item item) {
		this.items.add(item);
		probaTotale+=item.getProbabilite();
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
		double resTirage = Math.random()*probaTotale;
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
		
		int resTirage=0;
		for(int i=0; i<nbTirages; i++) {
			resTirage = faireTirage();
			nbItems.set(resTirage, nbItems.get(resTirage)+1);
		}
	}
	
	public String getResultatDernierTirage(){
		String resultatString;
		if(nbItems == null) resultatString = "Aucun tirage effectué dernièrement";
		else {
			int nbTirages;
			resultatString = "Résultats du dernier tirage";
			for(int i = 0; i < items.size(); i++) {
				nbTirages = nbItems.get(i);
				if (nbTirages>0)resultatString+="\n\""+items.get(i).getNom() + "\" : tiré " + nbTirages+" fois";
			}
		}
		return resultatString;
	}

	@Override
	public String toString() {
		return "Tirage [items=" + items + ", probaTotale=" + probaTotale + "]";
	}
	
	
	
}
