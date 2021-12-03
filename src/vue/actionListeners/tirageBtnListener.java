package vue.actionListeners;

import modele.Item;
import modele.Tirage;
import vue.VotingProjectWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class tirageBtnListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        Tirage t = Tirage.getInstance();
        VotingProjectWindow window = VotingProjectWindow.getInstance();
        String textField = window.getTextField();
        int res = Integer.parseInt(textField);
        if (res>0 && t.getItems().size()>0){
            t.effectuerTirages(res);
            window.updateList(window.convertTirageToArray());
        }
    }
}
