package vue;

import modele.Item;
import modele.Tirage;
import vue.actionListeners.chargerBtnListener;
import vue.actionListeners.retirerBtnListener;
import vue.actionListeners.sauverBtnListener;
import vue.actionListeners.tirageBtnListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class VotingProjectWindow extends JFrame {

    private static VotingProjectWindow instance;

    private JTable itemList;
    private JSpinner nbTiragesField;

    public static VotingProjectWindow getInstance(){
        if(instance==null)
            instance=new VotingProjectWindow();
        return instance;
    }

    private VotingProjectWindow() {
        super();

        setTitle("Gestion des items de vote");
        
        ArrayList<String> list = new ArrayList<>();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        setBounds((int)(screenWidth/100),(int)(screenHeight/100),(int)(screenWidth/2), (int)(screenHeight/1.5));
        setResizable(false);
        setLayout (null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar barreOutils = new JMenuBar();
        JMenu fichier = new JMenu("Fichier");
        JMenuItem charger = new JMenuItem("Charger fichier (.csv)");
        JMenuItem sauver = new JMenuItem("Sauvegarder");

        fichier.add(charger);
        fichier.add(sauver);
        barreOutils.add(fichier);
        setJMenuBar(barreOutils);

        String[] colonnes = {"Item","Quantité","Probabilité","Résultat dernier tirage"};
        String[][] lignes = new String[0][];
        DefaultTableModel TblMdl = new DefaultTableModel(convertTirageToArray(),colonnes);
        itemList = new JTable(TblMdl);
        itemList.getTableHeader().setReorderingAllowed(false);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane itemListScroll = new JScrollPane(itemList);

        JButton ajouterBtn = new JButton("Ajouter item");
        JButton retirerBtn = new JButton("Retirer item");
        JButton tirageBtn = new JButton("Faire tirage(s)");

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        nbTiragesField = new JSpinner();
        SpinnerModel model = new SpinnerNumberModel(1, 1, 999999999, 1);
        nbTiragesField.setModel(model);
        JFormattedTextField txt = ((JSpinner.NumberEditor) nbTiragesField.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);

        JLabel nbTiragesText = new JLabel("Nombre de votes :");
        
        add(itemListScroll);
        add(ajouterBtn);
        add(retirerBtn);
        add(tirageBtn);
        add(nbTiragesField);
        add(nbTiragesText);
        

        itemListScroll.setBounds((int)(getWidth()*0.02), (int)(getHeight()*0.02), (int)(getWidth()*0.7), (int)(getHeight()*0.7));
        ajouterBtn.setBounds((int)((itemListScroll.getX()+itemListScroll.getWidth()*1.08)),(int)(getHeight()*.6),150,20);
        retirerBtn.setBounds(ajouterBtn.getX(), ajouterBtn.getY()+(int)(35*(screenHeight/1080)), 150,20);
        nbTiragesField.setBounds((int)(getWidth()*.3),(int)((itemListScroll.getY()+itemListScroll.getHeight()*1.1)),150,20);
        tirageBtn.setBounds((int)((nbTiragesField.getX()+nbTiragesField.getWidth())*1.1),nbTiragesField.getY(),150,20);
        nbTiragesText.setSize((int)(6.2*nbTiragesText.getText().length()), nbTiragesField.getHeight());
        nbTiragesText.setLocation(nbTiragesField.getX()-nbTiragesText.getWidth(), nbTiragesField.getY());


        charger.addActionListener(new chargerBtnListener());
        sauver.addActionListener(new sauverBtnListener());
        retirerBtn.addActionListener(new retirerBtnListener());
        tirageBtn.addActionListener(new tirageBtnListener());

    }

    public void updateList(String[][] list){
        DefaultTableModel dTM = (DefaultTableModel) itemList.getModel();
        dTM.setDataVector(list, new String[]{"Item", "Quantité", "Probabilité", "Résultat dernier tirage"});
        itemList.setModel(dTM);
    }

    public String getTextField(){
        return nbTiragesField.getValue().toString();
    }
    
    public int getSelectedItem() {
    	return itemList.getSelectedRow();
    }


    public String[][] convertTirageToArray(){
    	DecimalFormat df = new DecimalFormat("0.00");
        Tirage t = Tirage.getInstance();
        String[][] tirage = new String[t.getItems().size()][4];
        Item item;
        System.out.println(t.getProbaGlobale());
        if(t.getItems() != null){
            for(int i=0;i<t.getItems().size();i++){
                item = t.getItems().get(i);

                tirage[i][0] = item.getNom();
                tirage[i][1] = ""+item.getQuantite();
                tirage[i][2] = "~"+df.format((item.getProbabilite()/t.getProbaGlobale())*100)+"%";
                if(t.getNbItems()!=null && t.getNbItems().size()>0)
                    tirage[i][3] = ""+t.getNbItems().get(i)*item.getQuantite();
                else
                    tirage[i][3] = "-";
            }
        }
        return tirage;
    }
}
