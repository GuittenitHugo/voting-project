import java.util.ArrayList;

import modele.Item;
import modele.Tirage;

public class TestVotingProject {

	public static void main(String[] args) {
		ArrayList<Item> listeItemsDeVote= new ArrayList<>();

		listeItemsDeVote.add(new Item("item1", 10));
		listeItemsDeVote.add(new Item("item2", 20));
		listeItemsDeVote.add(new Item("item3", 15));
		listeItemsDeVote.add(new Item("item4", 30));
		listeItemsDeVote.add(new Item("item5", 50));
		listeItemsDeVote.add(new Item("item6", 35));
		listeItemsDeVote.add(new Item("item7", 40));
		listeItemsDeVote.add(new Item("item8", 5));
		listeItemsDeVote.add(new Item("item9", 25));
		
		Tirage tirage = new Tirage(listeItemsDeVote);
		//System.out.println(tirage);
		
		System.out.println(tirage.getResultatDernierTirage());
		tirage.effectuerTirages(1500);
		System.out.println(tirage.getResultatDernierTirage());
		
	}
}
