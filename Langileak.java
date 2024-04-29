package Erronka3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Klase hau langileen zerrenda erakusten duen klasea da.
 */
public class Langileak extends JFrame {

    private JComboBox<String> comboBox; // Langileen izenak aukeratzeko JComboBox-a
    private JTextField langileId; // Langilearen ID-a erakusten duen testu eremua
    private JTextField langileIzena; // Langilearen izena erakusten duen testu eremua
    private JTextField langileAbizena; // Langilearen abizena erakusten duen testu eremua
    private JTextArea langileEmaila; // Langilearen emaila erakusten duen testu eremua
    private JTextField langileTelefonoa; // Langilearen telefonoa erakusten duen testu eremua
    private JTextField langileKontratu; // Langilearen kontratazio data erakusten duen testu eremua
    private BD bd; // Datu-basea
    private JFrame currentFrame; // Uneko Frame-a gordetzeko atributua

    /**
     * Langileak klasearen konstruktorea.
     */
    public Langileak() {
        bd = new BD(); // Datu-basea hasieratzen da
        try {
            bd.langileakKargatu(); // Langileak kargatzen dira
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        this.setTitle("Langile lista"); // Titulua ezartzen da
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Frame-a ixteko operazioa ezartzen da
        this.setSize(400, 400); // Tamaina ezartzen da        
        
        JPanel mainPanel = new JPanel(new GridLayout(8, 10, 10, 10)); // Panel nagusia sortzen da
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Panelaren ertzak ezartzen dira
        
        // Etiketak sortzen dira
        JLabel id = new JLabel("ID: ");
        JLabel izena = new JLabel("IZENA: ");
        JLabel abizena = new JLabel("ABIZENA: ");
        JLabel emaila = new JLabel("EMAILA: ");
        JLabel telefonoa = new JLabel("TELEFONOA: ");
        JLabel kontratazio = new JLabel("KONTRATAZIO DATA: ");
        JLabel lblBezeroak = new JLabel("LANGILEAK: ");
        currentFrame= this; // Uneko Frame-a gordetzen da
        
        JButton utzi = new JButton("UTZI"); // "UTZI" botoia sortzen da
        
        setLocationRelativeTo(null); // Frame-a erdian kokatzen da
        
        comboBox = new JComboBox<>(); // JComboBox-a sortzen da
        for (Langile langile : bd.getLangileak()) { // Langileen izenak JComboBox-era gehitzen dira
            comboBox.addItem(langile.getIzena() + " " + langile.getAbizena());
        }
        
        // JComboBox-aren ekintza gehitzen da
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String langilea = (String) comboBox.getSelectedItem();
                for (Langile langile : bd.getLangileak()) {
                    String izena = langile.getIzena() + " " + langile.getAbizena();
                    if (langilea.equals(izena)) {
                        langileId.setText(String.valueOf(langile.getId()));
                        langileIzena.setText(langile.getIzena());
                        langileAbizena.setText(langile.getAbizena());
                        langileEmaila.setText(langile.getEmaila());
                        langileTelefonoa.setText(langile.getTelefono());
                        langileKontratu.setText(langile.getKontratazioa());
                        break;
                    }
                }
            }
        });
        
        // Testu eremuak eta JTextArea sortzen dira
        langileId = new JTextField(25);
        langileId.setEditable(false);
        langileIzena = new JTextField(25);
        langileIzena.setEditable(false);
        langileAbizena = new JTextField(25);
        langileAbizena.setEditable(false);
        langileEmaila = new JTextArea(1, 25);
        langileEmaila.setEditable(false);
        langileEmaila.setLineWrap(false); 
        langileEmaila.setWrapStyleWord(false);
        JScrollPane emailaScrollPane = new JScrollPane(langileEmaila); 
        emailaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        langileEmaila.setEditable(false);
        langileTelefonoa = new JTextField(25);
        langileTelefonoa.setEditable(false);
        langileKontratu = new JTextField(20);
        langileKontratu.setEditable(false);
        
        // Elementuak panelera gehitzen dira
        mainPanel.add(lblBezeroak);
        mainPanel.add(comboBox);
        mainPanel.add(id);
        mainPanel.add(langileId);
        mainPanel.add(izena);
        mainPanel.add(langileIzena);
        mainPanel.add(abizena);
        mainPanel.add(langileAbizena);
        mainPanel.add(emaila);
        mainPanel.add(emailaScrollPane);
        mainPanel.add(telefonoa);
        mainPanel.add(langileTelefonoa);
        mainPanel.add(kontratazio);
        mainPanel.add(langileKontratu);
        mainPanel.add(utzi);
        
        // Panelaren atzeko kolorea ezartzen da
        mainPanel.setBackground(Color.black);
        // Elementuen ertz koloreak ezartzen dira
        comboBox.setBorder(new LineBorder(Color.red));
        langileId.setBorder(new LineBorder(Color.red));
        langileIzena.setBorder(new LineBorder(Color.red));
        langileAbizena.setBorder(new LineBorder(Color.red));
        emailaScrollPane .setBorder(new LineBorder(Color.red));
        langileTelefonoa.setBorder(new LineBorder(Color.red));
        langileKontratu.setBorder(new LineBorder(Color.red));
        // Etiketen koloreak ezartzen dira
        lblBezeroak.setForeground(Color.white);
        id.setForeground(Color.white);
        izena.setForeground(Color.white);
        abizena.setForeground(Color.white);
        emaila.setForeground(Color.white);
        telefonoa.setForeground(Color.white);
        kontratazio.setForeground(Color.white);
        // "UTZI" botoiaren kolorea eta testuaren kolorea ezartzen dira
        utzi.setForeground(Color.white);
        utzi.setBackground(Color.red);
        
        // "UTZI" botoiaren ekintza gehitzen da
        utzi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Menusaltzaile menu = new Menusaltzaile(); // Menusaltzaile klasearen instantzia sortzen da
                menu.setVisible(true); // Menua bistaratzen da
                currentFrame.dispose(); // Uneko Frame-a itxi
            }
        });
        
        this.add(mainPanel); // Panela Frame-an gehitzen da
    }
}