package Erronka3;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * Klase hau ProduktuakEguneratu izenekoa da, produktuak eguneratzeko interfaz grafikoa bereizteko erabiltzen da.
 */
public class ProduktuakEguneratu extends JFrame {
    private JComboBox<String> comboBox; // Produktu aukeratzeko JComboBox
    private JTextField prodId; // Produktuaren ID-a erakusteko JTextField
    private JTextField prodIzena; // Produktuaren izena sartu edo erakusteko JTextField
    private JTextArea prodDesk; // Produktuaren deskribapena erakusteko JTextArea
    private JTextField prodBalioa; // Produktuaren balioa sartu edo erakusteko JTextField
    private JTextField prodSalneurri; // Produktuaren salneurria sartu edo erakusteko JTextField
    private JTextField prodKategoria; // Produktuaren kategoria sartu edo erakusteko JTextField
    private BD bd; // Datu-basearekin komunikatzeko objektua
    private JFrame currentFrame; // Interfazea itxi ahal izateko JFrame
    
    /**
     * ProduktuakEguneratu klasearen eraikitzailea. ProduktuakEguneratu objetua sortzen du.
     */
    public ProduktuakEguneratu() {
        bd = new BD(); // BD objektua sortu
        try {
            bd.produktuakKargatu(); // Produktuak kargatu datu-baseatik
        } catch (SQLException e) {
            e.printStackTrace();
        }

        currentFrame = this; // JFrame erreferentzia gordetzeko

        // Interfazearen propietateak ezarri
        this.setTitle("Produktuak eguneratu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        
        // JPanel sortu eta diseinatu
        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLocationRelativeTo(null);

        // Interfazeko elementuak sortu
        JLabel produ = new JLabel("PRODUKTUA: ");
        JLabel id = new JLabel("ID: ");
        prodId = new JTextField();
        prodId.setEditable(false);
        JLabel izena = new JLabel("PRODUKTU IZENA: ");
        prodIzena = new JTextField();
        JLabel desk = new JLabel("DESKRIBAPENA: ");
        prodDesk = new JTextArea();
        prodDesk.setEditable(false);
        prodDesk.setLineWrap(false);
        prodDesk.setWrapStyleWord(false);
        JScrollPane scrollPane = new JScrollPane(prodDesk);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JLabel balio = new JLabel("BALIOA: ");
        prodBalioa = new JTextField();
        JLabel salneurri = new JLabel("SALNEURRIA: ");
        prodSalneurri = new JTextField();
        JLabel kat = new JLabel("KATEGORIA: ");
        prodKategoria = new JTextField();
        JButton gorde = new JButton("GORDE");
        JButton utzi = new JButton("UTZI");

        // JComboBox-ean produktuak sartu
        comboBox = new JComboBox<>();
        HashSet<String> duplikatuak = new HashSet<>();
        for (ProdEguneratu prod : bd.getProduktuak()) {
            String izenaProduktua = prod.getIzenaProd();
            if (!duplikatuak.contains(izenaProduktua)) {
                comboBox.addItem(izenaProduktua);
                duplikatuak.add(izenaProduktua);
            }
        }

        // JComboBox-en ekintza esleitu
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String produktu = (String) comboBox.getSelectedItem();
                for (ProdEguneratu prod : bd.getProduktuak()) {
                    String izena = prod.getIzenaProd();
                    if (produktu.equals(izena)) {
                        prodId.setText(prod.getiDProd());
                        prodIzena.setText(prod.getIzenaProd());
                        prodDesk.setText(prod.getDeskribapenaProd());
                        prodBalioa.setText(prod.getBalioaProd());
                        prodSalneurri.setText(prod.getSalneurriaProd());
                        prodKategoria.setText(prod.getKategoriaProd());
                        break;
                    }
                }
            }
        });

        // "GORDE" botoiaren ekintza esleitu
        gorde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = prodId.getText();
                String izena = prodIzena.getText();
                String deskribapena = prodDesk.getText();
                double balioa = Double.parseDouble(prodBalioa.getText());
                double salneurria = Double.parseDouble(prodSalneurri.getText());
                int kategoria = Integer.parseInt(prodKategoria.getText());
                try {
                    bd.konektatu();
                    bd.produktuakEguneratu(izena, deskribapena, balioa, salneurria, kategoria, id);
                    JOptionPane.showMessageDialog(ProduktuakEguneratu.this, "Produktua ongi eguneratu da.", "Oharra", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ProduktuakEguneratu.this, "Errorea produktua eguneratzekoan: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        bd.deskonektatu();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // "UTZI" botoiaren ekintza esleitu
        utzi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menusaltzaile menu = new Menusaltzaile();
                menu.setVisible(true);
                currentFrame.dispose();
            }
        });

        // Interfazeko elementuak JPanel-era gehitu
        mainPanel.add(produ);
        mainPanel.add(comboBox);
        mainPanel.add(id);
        mainPanel.add(prodId);
        mainPanel.add(izena);
        mainPanel.add(prodIzena);
        mainPanel.add(desk);
        mainPanel.add(scrollPane);
        mainPanel.add(balio);
        mainPanel.add(prodBalioa);
        mainPanel.add(salneurri);
        mainPanel.add(prodSalneurri);
        mainPanel.add(kat);
        mainPanel.add(prodKategoria);
        mainPanel.add(utzi);
        mainPanel.add(gorde);

        // JPanel-a JFrame-era gehitu
        this.add(mainPanel);

        // JPanel-aren kolorea ezarri
        mainPanel.setBackground(Color.black);

        // Elementuen ertzak kolorea ezarri
        comboBox.setBorder(new LineBorder(Color.red));
        prodId.setBorder(new LineBorder(Color.red));
        prodIzena.setBorder(new LineBorder(Color.red));
        prodBalioa.setBorder(new LineBorder(Color.red));
        prodSalneurri.setBorder(new LineBorder(Color.red));
        prodKategoria.setBorder(new LineBorder(Color.red));
        scrollPane.setBorder(new LineBorder(Color.red));

        // Etiketen kolorea eta testua ezarri
        produ.setForeground(Color.white);
        id.setForeground(Color.white);
        izena.setForeground(Color.white);
        desk.setForeground(Color.white);
        balio.setForeground(Color.white);
        salneurri.setForeground(Color.white);
        kat.setForeground(Color.white);
        utzi.setForeground(Color.white);
        utzi.setBackground(Color.red);
        gorde.setForeground(Color.white);
        gorde.setBackground(Color.red);
    }
}