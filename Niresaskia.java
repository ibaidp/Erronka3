package Erronka3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * {@code Niresaskia} klaseak nire saskia erakusteko JFrame bat sortzen du.
 */
public class Niresaskia extends JFrame {
    private JComboBox<String> comboBox; // JComboBox objetua produktuak aukeratzeko
    private BD bd; // Datu-basea kudeatzeko objektua
    private JTextField prodBalioa; // Produktuaren prezioa erakusteko testu eremua
    private JTextField prodKant; // Produktu kopurua sartzeko eta erakusteko testu eremua
    private JTextField prodStock; // Produktuaren stock-aren kopurua erakusteko testu eremua
    
    /**
     * Niresaskia klasearen konstruktorea.
     * Sortu klase berria.
     */
    public Niresaskia() {
        this.setTitle("Nire saskia"); // JFrame titulua ezarri
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame itxi beharrean aplikazioa itxi
        this.setSize(400, 400); // JFrame-aren neurriak ezarri

        // Produktuaren prezioa erakusteko testu eremua sortu eta editagarri ezarri
        prodBalioa = new JTextField(25);
        prodBalioa.setEditable(false);

        // Produktuaren stock-aren kopurua erakusteko testu eremua sortu eta editagarri ezarri
        prodStock = new JTextField(25);
        prodStock.setEditable(false);

        // JLabel-ak sortu produktuaren prezioa eta stock-aren informazioa erakusteko
        JLabel prodB = new JLabel("BALIOA: ");
        JLabel prodI = new JLabel("PRODUKTUAK: ");
        JLabel prodK = new JLabel("KANTITATEA: ");
        JLabel prodS = new JLabel("STOCK KANTITATEA: ");
        
        bd = new BD(); // Datu-basearekin konexioa ezarri
        try {
            bd.konektatu(); // Datu-basearekin konexioa ezarri
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // JButton-ak sortu aplikazioa egiten duten funtzioak exekutatzeko
        JButton utzi = new JButton("UTZI"); // Aplikazioa itxi
        JButton erosi = new JButton("EROSI"); // Produktuak erosi
        JButton gehi = new JButton("+");  // Produktu kopurua gehitu
        JButton ken = new JButton("-"); // Produktu kopurua kendu

        // JPanel-ak sortu produktu kopurua gehitzeko eta kendeko botoiak eta testu eremuak txertatzeko
        JPanel panel1 = new JPanel(); 
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS)); 

        // Produktu kopurua sartzeko eta erakusteko testu eremua sortu eta editagarri ezarri
        prodKant = new JTextField("1", 10);
        panel1.add(prodKant); // JPanel-ean sartu testu eremua
        panel1.add(ken); // JPanel-ean sartu "-" botoia
        panel1.add(gehi); // JPanel-ean sartu "+" botoia

        // JPanel-ak sortu panel nagusia eta bertan erakusteko testu eremuak eta botoiak txertatzeko
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        setLocationRelativeTo(null); // JFrame-aren kokapena ezarri

        // JPanel-ean erakutsi JLabel-ak eta JComboBox-a
        mainPanel.add(prodI);
        comboBox = new JComboBox<>();
        mainPanel.add(comboBox);

        // JPanel-ean erakutsi JLabel-ak eta erakusteko testu eremuak
        mainPanel.add(prodB);
        mainPanel.add(prodBalioa);
        mainPanel.add(prodS);
        mainPanel.add(prodStock);

        // JPanel-ean erakutsi JLabel-ak eta produktu kopurua gehitzeko eta kendeko panela
        mainPanel.add(prodK);
        mainPanel.add(panel1);

        // JPanel-ean erakutsi JButton-ak aplikazioa egiten duten funtzioak exekutatzeko
        mainPanel.add(utzi);
        mainPanel.add(erosi);

        this.add(mainPanel); // JFrame-an sartu panel nagusia

        // Testu eremuak eta botoiak formateatu
        prodKant.setBorder(new LineBorder(Color.red));
        comboBox.setBorder(new LineBorder(Color.red));
        prodBalioa.setBorder(new LineBorder(Color.red));
        prodStock.setBorder(new LineBorder(Color.red));
        prodI.setForeground(Color.white);
        prodK.setForeground(Color.white);
        prodB.setForeground(Color.white);
        prodS.setForeground(Color.white);
        utzi.setForeground(Color.white);
        gehi.setForeground(Color.white);
        ken.setForeground(Color.white);
        erosi.setForeground(Color.white);
        mainPanel.setBackground(Color.black);
        panel1.setBackground(Color.black);
        gehi.setBackground(Color.red);
        ken.setBackground(Color.red);
        utzi.setBackground(Color.red);
        erosi.setBackground(Color.red);

        try {
            bd.saskiaKargatu(); // Datu-basearen informazioa kargatu
            HashSet<String> duplikatuak = new HashSet<>(); // Produktuak ez errepikatu
            for (Saskia saki : bd.getSaskia()) {
                String prodIzena = saki.getProdIzena();
                if (!duplikatuak.contains(prodIzena)) {
                    comboBox.addItem(prodIzena); // Produktuak JComboBox-an sartu
                    duplikatuak.add(prodIzena);                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // JComboBox-a eguneratuenean stock-aren informazioa eta produktuaren prezioa aktualizatu
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String produIzena = (String) comboBox.getSelectedItem();
                try {
                    int stock = bd.getProdStock(produIzena);
                    prodStock.setText(String.valueOf(stock)); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                for (Saskia saski : bd.getSaskia()) {
                    String izena = saski.getProdIzena();
                    if (izena.equals(produIzena)) {
                        double balioa = Double.parseDouble(saski.getProdBalioa());
                        int kant = Integer.parseInt(prodKant.getText());
                        prodKant.setText(String.valueOf(kant));
                        double prezioa = balioa * kant;
                        DecimalFormat df = new DecimalFormat("#.##"); 
                        prodBalioa.setText(df.format(prezioa));
                        break;
                    }
                }
            }
        });
        
        // "UTZI" botoia sakatzean aplikazioa itxi
        utzi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menubezero menu = new Menubezero();
                menu.setVisible(true);
                Niresaskia.this.dispose();
            }
        });

        // "+" botoia sakatzean produktu kopurua gehitu
        gehi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String produIzena = (String) comboBox.getSelectedItem();
                for (Saskia saski : bd.getSaskia()) {
                    String izena = saski.getProdIzena();
                    if (izena.equals(produIzena)) {
                        int kant = Integer.parseInt(prodKant.getText());
                        int stock = Integer.parseInt(prodStock.getText());
                        if (kant < stock) { 
                            kant++; 
                            prodKant.setText(String.valueOf(kant)); 
                            double balioa = Double.parseDouble(saski.getProdBalioa());
                            double prezioa = balioa * kant;
                            DecimalFormat df = new DecimalFormat("#.##"); 
                            prodBalioa.setText(df.format(prezioa));
                            if (Integer.parseInt(prodKant.getText()) < 0) {
                            	prodBalioa.setText("0");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ez dago stock nahikoa.", "Aviso", JOptionPane.WARNING_MESSAGE); 
                        }
                        break;
                    }
                }
            }
        });

        // "-" botoia sakatzean produktu kopurua kendu
        ken.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String produIzena = (String) comboBox.getSelectedItem();
                for (Saskia saski : bd.getSaskia()) {
                    String izena = saski.getProdIzena();
                    if (izena.equals(produIzena)) {
                        int kant = Integer.parseInt(prodKant.getText());
                        if (kant > 1) {
                            kant--; 
                            prodKant.setText(String.valueOf(kant));
                            double balioa = Double.parseDouble(saski.getProdBalioa());
                            double prezioa = balioa * kant;                           
                            DecimalFormat df = new DecimalFormat("#.##"); 
                            prodBalioa.setText(df.format(prezioa)); 
                            if (Integer.parseInt(prodKant.getText()) < 0) {
                            	prodBalioa.setText("0");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Kantitatea 1 edo gehiago izan behar da.", "Oharra",
                                    JOptionPane.INFORMATION_MESSAGE); 
                        }
                        break;
                    }
                }
            }
        });

        // "EROSI" botoia sakatzean produktuak erosi
        erosi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String produktuIzena = (String) comboBox.getSelectedItem();
                int kantitatea = Integer.parseInt(prodKant.getText());
                int stocka = Integer.parseInt(prodStock.getText());
                if (kantitatea <= 0 || kantitatea > stocka) {
                    JOptionPane.showMessageDialog(null, "Ez dago stock nahikoa edo kantitatea ez da baliozkoa.", "Oharra", JOptionPane.WARNING_MESSAGE);
                    return; 
                } else {
                	try {
                        int produktuId = bd.getProdId(produktuIzena);
                        bd.actualizarStock(kantitatea, String.valueOf(produktuId));
                        
                        JOptionPane.showMessageDialog(null, kantitatea + " " + produktuIzena + " erosi dira.", "Erosketa egina", JOptionPane.INFORMATION_MESSAGE);
                        
                        int stock = bd.getProdStock(produktuIzena);
                        prodStock.setText(String.valueOf(stock));
                        prodKant.setText("1"); 
                        
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Errorea gertatu da erosketaren prozesuan.", "Errorea", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            }
        });
    }
}