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
		int itemRange = window.getSelectedItem();
		ArrayList<String> array = window.getItemInfosToAdd();
		if(array.size()>0) {
			t.AjouterItem(new Item(array.get(0), Integer.parseInt(array.get(1)), Double.parseDouble(array.get(2))));
			System.out.println(array);
			window.updateList(window.convertTirageToArray());
		}
	}

	

}
