import java.util.ArrayList;

import modele.Item;
import modele.Tirage;

public class TestVotingProject {

	public static void main(String[] args) {
		ArrayList<Item> listeItemsDeVote= new ArrayList<>();

		listeItemsDeVote.add(new Item("item1",16, 10));
		listeItemsDeVote.add(new Item("item2",1, 20));
		listeItemsDeVote.add(new Item("item3",1, 15));
		listeItemsDeVote.add(new Item("item4",8, 30));
		listeItemsDeVote.add(new Item("item5",32, 50));
		listeItemsDeVote.add(new Item("item6",1, 35));
		listeItemsDeVote.add(new Item("item7",4, 40));
		listeItemsDeVote.add(new Item("item8",64, 5));
		listeItemsDeVote.add(new Item("item9",16, 25));
		
		Tirage tirage = Tirage.getInstance();
		tirage.setItems(listeItemsDeVote);
		//System.out.println(tirage);
		
		System.out.println(tirage.getResultatDernierTirage());
		Tirage.getInstance().effectuerTirages(1500);
		System.out.println(tirage.getResultatDernierTirage());
		
	}
}
