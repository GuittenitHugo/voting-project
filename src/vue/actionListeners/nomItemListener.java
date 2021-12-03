package vue.actionListeners;

        import vue.VotingProjectWindow;

        import javax.swing.event.DocumentEvent;
        import javax.swing.event.DocumentListener;

public class nomItemListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update();
    }

    private void update(){
        VotingProjectWindow window = VotingProjectWindow.getInstance();
        window.updateAjouterBtn();
    }
}
