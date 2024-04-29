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
 * Bezerolista klaseak bezeroen zerrenda erakusten duen JFrame klasea da.
 */
public class Bezerolista extends JFrame {
    // Atributuak
    private JComboBox<String> comboBox;
    private JTextField bezeroId;
    private JTextField bezeroIzena;
    private JTextField bezeroAbizena;
    private JTextArea bezeroHelbidea;
    private JTextArea bezeroEmaila;
    private BD bd;
    private JFrame currentFrame;

    /**
     * Bezerolista klasearen konstruktorea.
     * Sortu bezeroen zerrenda erakusten duen JFrame klasea.
     */
    public Bezerolista() {
        bd = new BD();
        try {
            bd.bezeroakKargatu();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.setTitle("Bezero lista");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);

        JPanel mainPanel = new JPanel(new GridLayout(7, 10, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel id = new JLabel("ID: ");
        JLabel izena = new JLabel("IZENA: ");
        JLabel abizena = new JLabel("ABIZENA: ");
        JLabel helbidea = new JLabel("HELBIDEA: ");
        JLabel emaila = new JLabel("EMAILA: ");
        currentFrame = this;

        JButton utzi = new JButton("UTZI");
        JLabel lblBezeroak = new JLabel("BEZEROAK: ");
        comboBox = new JComboBox<>();
        
        // JComboBox-ean bezeroak gehitu
        for (Bezeroak bezero : bd.getBezeroak()) {
            comboBox.addItem(bezero.getIzenaBezeroak() + " " + bezero.getAbizenaBezeroak());
        }

        // JComboBox-aren action listener-a
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bezeroa = (String) comboBox.getSelectedItem();
                for (Bezeroak bezero : bd.getBezeroak()) {
                    String izena = bezero.getIzenaBezeroak() + " " + bezero.getAbizenaBezeroak();
                    if (bezeroa.equals(izena)) {
                        bezeroId.setText(String.valueOf(bezero.getIdBezeroak()));
                        bezeroIzena.setText(bezero.getIzenaBezeroak());
                        bezeroAbizena.setText(bezero.getAbizenaBezeroak());
                        bezeroHelbidea.setText(bezero.getHelbideaBezeroak());
                        bezeroEmaila.setText(bezero.getEmailaBezeroak());
                        break;
                    }
                }
            }
        });

        bezeroId = new JTextField(25);
        bezeroId.setEditable(false);
        bezeroIzena = new JTextField(25);
        bezeroIzena.setEditable(false);
        bezeroAbizena = new JTextField(25);
        bezeroAbizena.setEditable(false);
        bezeroHelbidea = new JTextArea(1, 25);
        bezeroHelbidea.setLineWrap(false);
        bezeroHelbidea.setWrapStyleWord(false);
        bezeroHelbidea.setEditable(false);
        JScrollPane helbideaScrollPane = new JScrollPane(bezeroHelbidea);
        helbideaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        bezeroEmaila = new JTextArea(1, 25); 
        bezeroEmaila.setEditable(false);
        bezeroEmaila.setLineWrap(false);
        bezeroEmaila.setWrapStyleWord(false);
        JScrollPane emailaScrollPane = new JScrollPane(bezeroEmaila);
        emailaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Panelaren elementuak gehitu
        mainPanel.add(lblBezeroak);
        mainPanel.add(comboBox);
        mainPanel.add(id);
        mainPanel.add(bezeroId);
        mainPanel.add(izena);
        mainPanel.add(bezeroIzena);
        mainPanel.add(abizena);
        mainPanel.add(bezeroAbizena);
        mainPanel.add(helbidea);
        mainPanel.add(helbideaScrollPane); 
        mainPanel.add(emaila);
        mainPanel.add(emailaScrollPane); 
        mainPanel.add(utzi);
        this.add(mainPanel);

        // Framea erdian kokatu
        setLocationRelativeTo(null);

        // Utzi botoiaren action listener-a
        utzi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menusaltzaile menu = new Menusaltzaile();
                menu.setVisible(true);
                currentFrame.dispose();
            }
        });

        // Koloreak eta marrazkiak ezarri
        mainPanel.setBackground(Color.black);
        comboBox.setBorder(new LineBorder(Color.red));
        bezeroId.setBorder(new LineBorder(Color.red));
        bezeroIzena.setBorder(new LineBorder(Color.red));
        bezeroAbizena.setBorder(new LineBorder(Color.red));
        helbideaScrollPane.setBorder(new LineBorder(Color.red));
        emailaScrollPane.setBorder(new LineBorder(Color.red));
        lblBezeroak.setForeground(Color.white);
        id.setForeground(Color.white);
        izena.setForeground(Color.white);
        abizena.setForeground(Color.white);
        emaila.setForeground(Color.white);
        helbidea.setForeground(Color.white);
        utzi.setForeground(Color.white);
        utzi.setBackground(Color.red);
    }
}