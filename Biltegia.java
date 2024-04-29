package Erronka3;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Biltegia klasea produktuak gehitzeko erabiltzen da.
 * Produktu berriaren informazioa jaso eta datu basean gordeko du.
 */
public class Biltegia extends JFrame {
    // Klasearen bariableak eta metadatuak...
    private BD bd; // Datu-basearekin konektatzen da
    private JTextField produktuIdField, produktuIzenaField, deskribapenaField, prezioaField, salneurriaField;
    private JComboBox<String> kategoriaComboBox; 
    private Map<String, Integer> kategoriaMap;
    private JFrame currentFrame; 
    private String pId; // Produktu identifikadorea

    /**
     * Biltegia klasearen konstruktorea.
     * Sortu klase berria.
     */
    public Biltegia() {
        this.bd = new BD(); // Sortu datu-basearen objektua
        this.setTitle("Produktua Gehitzea"); // Pantailaren titulua ezarri
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pantaila itxi
        this.setSize(400, 350); // Pantailaren neurriak ezarri

        kategoriaMap = new HashMap<>(); // Kategoria eta identifikadoreen map-a sortu
        kategoriaMap.put("CPU", 1);
        kategoriaMap.put("Video Card", 2);
        kategoriaMap.put("Mother Board", 4);
        kategoriaMap.put("Storage", 5);

        // Erabiltzaileak produktuaren informazioa sartuko dituen testu eremuak eta dropdown-ak sortu
        produktuIzenaField = new JTextField();
        produktuIdField = new JTextField();
        deskribapenaField = new JTextField();
        prezioaField = new JTextField();
        salneurriaField = new JTextField();
        produktuIdField.setEditable(false);
        kategoriaComboBox = new JComboBox<>(new String[]{"CPU", "Video Card", "Mother Board", "Storage"});

        // Main panela sortu eta eremuak eta botoiak kokatzeko
        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel produktuIzenaLabel = new JLabel("PRODUKTUAREN IZENA:");
        JLabel deskribapenaLabel = new JLabel("DESKRIBAPENA:");
        JLabel prezioaLabel = new JLabel("PREZIOA:");
        JLabel iDLabel = new JLabel("ID:");
        JLabel salneurriaLabel = new JLabel("SALNEURRIA:");
        JLabel kategoriaLabel = new JLabel("KATEGORIA:");

        JButton gehituButton = new JButton("PRODUKTUA GEHITU");
        JButton atzeraButton = new JButton("UTZI");

        setLocationRelativeTo(null); // Pantaila erdian kokatu
        currentFrame = this;

        // "UTZI" botoia sakatzean Menusaltzaile klasea irekitzeko
        atzeraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menusaltzaile menu= new Menusaltzaile();
                menu.setVisible(true);
                currentFrame.dispose();
            }
        });

        // "PRODUKTUA GEHITU" botoia sakatzean produktua datu-basean sartzea
        gehituButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String produktuIzena = produktuIzenaField.getText();
                String deskribapena = deskribapenaField.getText();
                String prezioa = prezioaField.getText();
                String salneurria = salneurriaField.getText();
                
                String kategoria = (String) kategoriaComboBox.getSelectedItem();
                String kategoriaId = String.valueOf(kategoriaMap.get(kategoria));

                // Eremu guztiak bete badira
                if (produktuIzena.isEmpty() || deskribapena.isEmpty() || prezioa.isEmpty() || salneurria.isEmpty()) {
                    JOptionPane.showMessageDialog(Biltegia.this, "Mesedez, bete eremu guztiak.", "Abisua", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    bd.konektatu(); // Datu-basearekin konektatu
                    bd.produktuaGehitu(produktuIzena, deskribapena, prezioa, salneurria, kategoriaId); // Produktua datu-basean gehitu
                    JOptionPane.showMessageDialog(Biltegia.this, "Produktua ongi gehitu da.", "Oharra", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Erremu guztiak garbitu eta produktuaren identifikadorea hartzeko prestatu
                    produktuIdField.setText(bd.getMaxIdprod());
                    produktuIzenaField.setText("");
                    deskribapenaField.setText("");
                    prezioaField.setText("");
                    salneurriaField.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Biltegia.this, "Errorea produktua gehitzerakoan: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        bd.deskonektatu(); // Datu-basearekin deskonektatu
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        try {
            bd.konektatu(); // Datu-basearekin konektatu
            pId = bd.getMaxIdprod(); // Produktu identifikadorea hartu
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        produktuIdField.setText(pId); // Produktu identifikadorea erakutsi

        // Main panelari eremuak eta botoiak gehitu
        mainPanel.add(iDLabel);
        mainPanel.add(produktuIdField);
        mainPanel.add(produktuIzenaLabel);
        mainPanel.add(produktuIzenaField);
        mainPanel.add(deskribapenaLabel);
        mainPanel.add(deskribapenaField);
        mainPanel.add(prezioaLabel);
        mainPanel.add(prezioaField);
        mainPanel.add(salneurriaLabel);
        mainPanel.add(salneurriaField);
        mainPanel.add(kategoriaLabel);
        mainPanel.add(kategoriaComboBox);
        mainPanel.add(atzeraButton);
        mainPanel.add(gehituButton);
        
        // Main panela frame-an gehitu
        this.add(mainPanel);
        
        // Framearen atzekaldearen kolorea zuri jarri
        mainPanel.setBackground(Color.black);
        
        // Eremuen ertza gorri jarri
        produktuIzenaField.setBorder(new LineBorder(Color.red));
        deskribapenaField.setBorder(new LineBorder(Color.red));
        prezioaField.setBorder(new LineBorder(Color.red));
        salneurriaField.setBorder(new LineBorder(Color.red));
        kategoriaComboBox.setBorder(new LineBorder(Color.red));
        produktuIdField.setBorder(new LineBorder(Color.red));
        
        // Etiketak zuri jarri
        produktuIzenaLabel.setForeground(Color.white);
        deskribapenaLabel.setForeground(Color.white);
        prezioaLabel.setForeground(Color.white);
        salneurriaLabel.setForeground(Color.white);
        kategoriaLabel.setForeground(Color.white);
        iDLabel.setForeground(Color.white);
        // Botoien kolorea eta letra kolorea ezarri
        atzeraButton.setForeground(Color.white);
        atzeraButton.setBackground(Color.red);
        gehituButton.setForeground(Color.white);
        gehituButton.setBackground(Color.red);
    }
}