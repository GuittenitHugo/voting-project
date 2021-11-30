package modele;

public class Item {

	private String nom;
	private double probabilite;
	
	public Item(String nom, double probabilite) {
		this.nom = nom;
		this.probabilite = probabilite;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getProbabilite() {
		return probabilite;
	}
	public void setProbabilite(double probabilite) {
		this.probabilite = probabilite;
	}

	@Override
	public String toString() {
		return "Item [nom=" + nom + ", probabilite=" + probabilite + "]";
	}
	
	
}
