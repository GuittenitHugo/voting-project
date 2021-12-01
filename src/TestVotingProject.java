import java.util.ArrayList;

import modele.Item;
import modele.Tirage;
import vue.VotingProjectWindow;

public class TestVotingProject {

	public static void main(String[] args) {
		Tirage tirage = Tirage.getInstance();
		VotingProjectWindow.getInstance().show();

		//System.out.println(tirage);

	}
}
