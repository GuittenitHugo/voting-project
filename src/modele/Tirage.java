package modele;

import modele.gestionFichiers.visiteur.Visiteur;

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
	
	public void RetirerItem(Item item) {
		if (this.items.remove(item))
			probaTotale-=item.getProbabilite();
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
		if(items.size() <= 0) throw new IllegalStateException("Aucun item d�tect� pour le tirage");
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
		if(nbItems == null) resultatString = "Aucun tirage effectu� derni�rement";
		else {
			int nbTirages;
			Item item;
			resultatString = "R�sultats du dernier tirage";
			for(int i = 0; i < items.size(); i++) {
				nbTirages = nbItems.get(i);
				item = items.get(i);
				if (nbTirages>0)
					resultatString+="\n\""+item.getQuantite() +"x " + item.getNom() + "\" : tir� " + nbTirages+" fois. Total = "+ item.getQuantite()*nbTirages;
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
