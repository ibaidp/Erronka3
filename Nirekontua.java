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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Klase hau bezeroaren kontuaren informazioa erakusten duen Frame-a da.
 */
public class Nirekontua extends JFrame {
    // Atributuak
    private JTextField bezeroId;
    private JTextField bezeroIzena;
    private JTextField bezeroAbizena;
    private JTextField bezeroHelbidea;
    private JTextField bezeroEmaila;
    private BD bd;
    private JFrame currentFrame;
    private String loggedInUser;

    /**
     * Nirekontua klasearen konstruktorea.
     */
    public Nirekontua() {
       
        bd = new BD();
        
        bezeroId = new JTextField(25);
        bezeroId.setEditable(false);
        bezeroIzena = new JTextField(25);
        bezeroAbizena = new JTextField(25);
        bezeroHelbidea = new JTextField(25);
        bezeroEmaila = new JTextField(25);
        bezeroId.setText("Ez dago eskuragarri");
        bezeroIzena.setText("Ez dago eskuragarri");
        bezeroAbizena.setText("Ez dago eskuragarri");
        bezeroHelbidea.setText("Ez dago eskuragarri");
        bezeroEmaila.setText("Ez dago eskuragarri");
        
        try {
            bd.bezeroakKargatu();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loggedInUser = Login.getBezero();

        for (Bezeroak bezero : bd.getBezeroak()) {
            if (loggedInUser != null && loggedInUser.equals(bezero.getIzenaBezeroak())) {
                bezeroId.setText(Integer.toString(bezero.getIdBezeroak()));
                bezeroIzena.setText(bezero.getIzenaBezeroak());
                bezeroAbizena.setText(bezero.getAbizenaBezeroak());
                bezeroHelbidea.setText(bezero.getHelbideaBezeroak());
                bezeroEmaila.setText(bezero.getEmailaBezeroak());
                break;
            }
        }
        
        this.setTitle("Nire kontua "); // Titulua ezartzen da
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Frame-a ixteko operazioa ezartzen da
        this.setSize(400, 400); // Tamaina ezartzen da
        
        JPanel mainPanel = new JPanel(new GridLayout(6, 10, 10, 10)); // Panela sortzen da
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Panelaren ertzean espazio bat ezartzen da
        
        // JLabel-ak sortzen dira
        JLabel id = new JLabel("ID: ");
        JLabel izena = new JLabel("IZENA: ");
        JLabel abizena = new JLabel("ABIZENA: ");
        JLabel helbidea = new JLabel("HELBIDEA: ");
        JLabel emaila = new JLabel("EMAILA: ");
        currentFrame = this; // Uneko Frame-a gordetzen da
        
        // JButton-ak sortzen dira
        JButton utzi = new JButton("UTZI");
        JButton gorde = new JButton("ALDAKETAK GORDE");

        // Elementuak panela gehitzen dira
        mainPanel.add(id);
        mainPanel.add(bezeroId);
        mainPanel.add(izena);
        mainPanel.add(bezeroIzena);
        mainPanel.add(abizena);
        mainPanel.add(bezeroAbizena);
        mainPanel.add(helbidea);
        mainPanel.add(bezeroHelbidea);
        mainPanel.add(emaila);
        mainPanel.add(bezeroEmaila);
        mainPanel.add(utzi);
        mainPanel.add(gorde);
        
        this.add(mainPanel); // Panela Frame-an gehitzen da
        setLocationRelativeTo(null); // Frame-a erdian kokatzen da
        
        // Utzi botoiaren ekintza gehitzen da
        utzi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menubezero menu = new Menubezero(); // Menubezero klasearen instantzia sortzen da
                menu.setVisible(true); // Menua bistaratzen da
                currentFrame.dispose(); // Uneko Frame-a itxi
            }
        });
        
        // Gorde botoiaren ekintza gehitzen da
        gorde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                String newIzena = bezeroIzena.getText();
                String newAbizena = bezeroAbizena.getText();
                String newHelbidea = bezeroHelbidea.getText();
                String newEmaila = bezeroEmaila.getText();
                String id = bezeroId.getText();
                
                try {
                    bd.konektatu();
                    
                    bd.bezeroaEguneratu(newIzena, newAbizena, newHelbidea, newEmaila, id);
                    
                    JOptionPane.showMessageDialog(null, "Aldaketak ondo gorde dira.", "Oharra",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Errorea aldaketak gordetzean: " + ex.getMessage(),
                            "Errorea", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        bd.deskonektatu();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        
        // Elementuen koloreak ezartzen dira
        bezeroId.setBorder(new LineBorder(Color.red));
        bezeroIzena.setBorder(new LineBorder(Color.red));
        bezeroAbizena.setBorder(new LineBorder(Color.red));
        bezeroHelbidea.setBorder(new LineBorder(Color.red));
        bezeroEmaila.setBorder(new LineBorder(Color.red));
        
        utzi.setForeground(Color.white);
        gorde.setForeground(Color.white);
        id.setForeground(Color.white);
        izena.setForeground(Color.white);
        abizena.setForeground(Color.white);
        emaila.setForeground(Color.white);
        helbidea.setForeground(Color.white);
        
        mainPanel.setBackground(Color.black);
        utzi.setBackground(Color.red);
        gorde.setBackground(Color.red);
    }
    
    /**
     * Logeatutako erabiltzailearen izena itzultzen duen metodoa.
     * 
     * @return Logeatutako erabiltzailearen izena.
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Logeatutako erabiltzailearen izena ezartzen duen metodoa.
     * 
     * @param loggedInUser Logeatutako erabiltzailearen izena.
     */
    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}