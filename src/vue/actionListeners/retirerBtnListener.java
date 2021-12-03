package vue.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modele.Item;
import modele.Tirage;
import vue.VotingProjectWindow;

public class retirerBtnListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Tirage t = Tirage.getInstance();
		VotingProjectWindow window = VotingProjectWindow.getInstance();
		int itemRange = window.getSelectedItem();
		ArrayList<Item> items = t.getItems();
		
		if(itemRange>=0&&itemRange<items.size()) {
			Item toRemove = items.get(window.getSelectedItem());
			t.RetirerItem(toRemove);
			window.updateList(window.convertTirageToArray());
		}
	}

	

}
