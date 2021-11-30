package modele;

public class Item {

	private String nom;
	private double probabilite;
	private int quantite;
	
	public Item(String nom, int quantite,double probabilite) {
		this.nom = nom;
		this.probabilite = probabilite;
		this.quantite = quantite;
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
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	@Override
	public String toString() {
		return "Item [nom=" + nom +", quantite="+ quantite + ", probabilite=" + probabilite + "]";
	}
	
	
}
