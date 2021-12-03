package vue.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modele.Item;
import modele.Tirage;
import vue.VotingProjectWindow;

public class ajouterBtnListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Tirage t = Tirage.getInstance();
		VotingProjectWindow window = VotingProjectWindow.getInstance();
		ArrayList<String> array = window.getItemInfosToAdd();
		if(array.size()>0) {
			double probability = Double.parseDouble(array.get(2))/100;
			probability = t.getProbaGlobale()!=0 ? (probability * t.getProbaGlobale())/(1-probability) : 1;
			t.AjouterItem(new Item(array.get(0), Integer.parseInt(array.get(1)), probability));
			window.updateList(window.convertTirageToArray());
		}
	}

	

}
