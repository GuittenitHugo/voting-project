package vue;

import vue.actionListeners.chargerBtnListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class VotingProjectWindow extends JFrame {

    private static VotingProjectWindow instance;

    private JList itemList;
    private JTextField nbTiragesField;

    public static VotingProjectWindow getInstance(){
        if(instance==null)
            instance=new VotingProjectWindow();
        return instance;
    }

    private VotingProjectWindow() {
        super();

        ArrayList<String> list = new ArrayList<>();

        setLayout (null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        setBounds((int)(screenWidth/100),(int)(screenHeight/100),(int)(screenWidth/2), (int)(screenHeight/1.5));
        setResizable(false);

        JMenuBar barreOutils = new JMenuBar();
        JMenu fichier = new JMenu("Fichier");
        JMenuItem charger = new JMenuItem("Charger fichier (.csv)");
        JMenuItem sauver = new JMenuItem("Sauvegarder");

        fichier.add(charger);
        fichier.add(sauver);
        barreOutils.add(fichier);
        setJMenuBar(barreOutils);

        itemList = new JList ();
        JScrollPane itemListScroll = new JScrollPane(itemList);

        JButton ajouterBtn = new JButton("Ajouter item");
        JButton retirerBtn = new JButton("Retirer item");
        JButton tirageBtn = new JButton("Faire tirage(s)");

        nbTiragesField = new JTextField("1",100);

        add(itemListScroll);
        add(ajouterBtn);
        add(retirerBtn);
        add(tirageBtn);
        add(nbTiragesField);

        itemListScroll.setBounds((int)(getWidth()*0.02), (int)(getHeight()*0.02), (int)(getWidth()*0.7), (int)(getHeight()*0.7));
        ajouterBtn.setBounds((int)((itemListScroll.getX()+itemListScroll.getWidth()*1.08)),(int)(getHeight()*.3),150,20);
        retirerBtn.setBounds(ajouterBtn.getX(), ajouterBtn.getY()+(int)(35*(screenHeight/1080)), 150,20);
        nbTiragesField.setBounds((int)(getWidth()*.3),(int)((itemListScroll.getY()+itemListScroll.getHeight()*1.1)),150,20);
        tirageBtn.setBounds((int)((nbTiragesField.getX()+nbTiragesField.getWidth())*1.1),nbTiragesField.getY(),150,20);


        charger.addActionListener(new chargerBtnListener());

    }

    public void updateList(ArrayList<String> list){
        this.itemList.setListData(list.toArray());
    }
}
