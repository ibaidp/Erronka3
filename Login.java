package Erronka3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Klase hau saioa hasi eta bezero edo saltzaile bezala saioa hasteko leihoa erakusten du.
 */
public class Login extends JFrame {
    private JTextField jtf1 = new JTextField("");
    private JPasswordField jtf2 = new JPasswordField("");  
    private String erabiltzaile; // Erabiltzailearen izena
    private BD bd; // Datu-basea
    private JFrame currentFrame; // Uneko Frame-a gordetzeko atributua
    private static String bezero; // Bezeroaren izena

    /**
     * Login klasearen konstruktorea.
     */
    public Login() {
        bd = new BD(); // Datu-basea hasieratzen da
        try {
            bd.bezeroakKargatu(); // Bezeroak kargatzen dira
            bd.saltzaileakKargatu(); // Saltzaileak kargatzen dira
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.setTitle("Saioa hasi"); // Titulua ezartzen du
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Frame-a ixteko operazioa ezartzen du
        this.setSize(400,400); // Tamaina ezartzen du

        JPanel mainPanel = new JPanel(new GridLayout(6, 10, 10, 10)); // Panel nagusia sortzen da
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Panelaren ertzak ezartzen dira

        // Testu etiketak eta botoiak sortzen dira
        JLabel label1 = new JLabel("ERABILTZAILE:");
        JLabel label2 = new JLabel("PASAHITZA:");
        JButton botoi1 = new JButton("ERREGISTRATU"); 
        JButton botoi2 = new JButton("SAIOA HASI");

        currentFrame = this; // Uneko Frame-a gordetzen da
        setLocationRelativeTo(null); // Frame-a erdian kokatzen da

        // Botoien ekintzak gehitzen dira
        botoi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {     
                Erregistratu erregistratu = new Erregistratu();
                erregistratu.setVisible(true);
                currentFrame.dispose();
            }
        });

        botoi2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = jtf1.getText(); // Erabiltzailearen sarrera lortzen da
                char[] passChars = jtf2.getPassword(); // Pasahitzaren karaktereak lortzen dira
                String passInput = new String(passChars);
                if (userInput.isEmpty() || passInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mesedez, bete eremu guztiak.", "Abisua",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Saltzaileak begiratzen dira
                for (Saltzaileak saltzaile : bd.getSaltzaileak()) {
                    if (jtf1.getText().equals(saltzaile.getErabiltzaileSaltzaile()) && jtf2.getText().equals(saltzaile.getPasahitzaSaltzaile())) {
                        setErabiltzaile(jtf1.getText()); 
                        Menusaltzaile menu = new Menusaltzaile(); 
                        menu.setVisible(true); 
                        currentFrame.dispose(); 
                        return;
                    }
                }

                // Bezeroak begiratzen dira
                for (Bezeroak bezero : bd.getBezeroak()) {
                    if (jtf1.getText().equals(bezero.getIzenaBezeroak()) && jtf2.getText().equals(bezero.getAbizenaBezeroak())) {
                        setBezero(userInput); 
                        Menubezero menu1 = new Menubezero(); 
                        menu1.setVisible(true); 
                        currentFrame.dispose(); 
                        return;
                    }
                }

                // Arazoa badago, mezu bat erakusten da
                JOptionPane.showMessageDialog(null, "Erabiltzailea edo pasahitza txarto daude", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Elementuak panelera gehitzen dira
        mainPanel.add(label1); 
        mainPanel.add(jtf1);
        mainPanel.add(label2);
        mainPanel.add(jtf2); 
        mainPanel.add(botoi2);
        mainPanel.add(botoi1); 

        this.add(mainPanel); // Panela Frame-an gehitzen da
        mainPanel.setBackground(Color.black); // Panelaren atzeko kolorea ezartzen da

        jtf1.setBorder(new LineBorder(Color.red)); // Sarrera eremuaren ertz kolorea ezartzen da
        jtf2.setBorder(new LineBorder(Color.red));
        label2.setForeground(Color.white); // Etiketen koloreak ezartzen dira
        label1.setForeground(Color.white); 
        botoi1.setForeground(Color.white); // Botoien koloreak ezartzen dira
        botoi2.setForeground(Color.white); 
        botoi1.setBackground(Color.red); // Botoien atzeko koloreak ezartzen dira
        botoi2.setBackground(Color.red);
    }

    /**
     * Erabiltzailearen izena itzultzen du.
     * @return Erabiltzailearen izena
     */
    public String getErabiltzaile() {
        return erabiltzaile;
    }

    /**
     * Erabiltzailearen izena ezartzen du.
     * @param erabiltzaile Erabiltzailearen izena
     */
    public void setErabiltzaile(String erabiltzaile) {
        this.erabiltzaile = erabiltzaile;
    }

    /**
     * Bezeroaren izena itzultzen du.
     * @return Bezeroaren izena
     */
    public static String getBezero() {
        return bezero;
    }

    /**
     * Bezeroaren izena ezartzen du.
     * @param bezeroa Bezeroaren izena
     */
    public static void setBezero(String bezeroa) {
        bezero = bezeroa;
    }
}