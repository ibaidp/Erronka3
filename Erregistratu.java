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
 * Erregistratu klasea erabiltzaileak erregistratzeko erabiltzen den klasea da.
 * Erabiltzailearen informazioa jaso eta datu basean gordeko du.
 * Sortutako erabiltzailea Login klasean erabili ahal izango da sartu nahi duen orduan.
 */
public class Erregistratu extends JFrame{
    private JTextField jtfe1 = new JTextField(""); // Erabiltzailearen izena sartzeko
    private JTextField jtfe2 = new JTextField(""); // Erabiltzailearen pasahitza sartzeko
    private JTextField jtfe3 = new JTextField(""); // Erabiltzailearen helbidea sartzeko
    private JTextField jtfe4 = new JTextField(""); // Erabiltzailearen emaila sartzeko
    private JTextField jtfe5 = new JTextField(""); // Erabiltzailearen identifikadorea erakusteko
    private JFrame currentFrame; // Jokalariaren pantaila
    private String id; // Erabiltzaile identifikadorea
    private BD bd; // Datu-basearekin konektatzen da

    /**
     * Erregistratu klasearen konstruktorea.
     * Sortu klase berria.
     */
    public Erregistratu(){
        this.bd = new BD(); // Sortu datu-basearen objektua
        
        this.setTitle("Erregistratu"); // Pantailaren titulua ezarri
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pantaila itxi
        this.setSize(400,400); // Pantailaren neurriak ezarri
        
        // Erabiltzailearen informazioa sartuko diren testu eremuak eta botoiak sortu
        JLabel label = new JLabel("ID:");
        JLabel label1 = new JLabel("ERABILTZAILEA:");
        JLabel label2 = new JLabel("PASAHITZA:");
        JLabel label3 = new JLabel("HELBIDEA:");
        JLabel label4 = new JLabel("EMAILA:");
        JButton botoi1 = new JButton("UTZI");
        JButton botoi2 = new JButton("SORTU ERABILTZAILEA");
        jtfe5 = new JTextField(25);
        jtfe5.setEditable(false);
        JPanel mainPanel = new JPanel(new GridLayout(6, 10, 10, 10)); // Main panela sortu eta eremuak kokatzeko

        currentFrame = this; // Framea gordetzeko
        setLocationRelativeTo(null); // Framea pantailaren erdian kokatu
        
        // "UTZI" botoia sakatzean Login klasea irekitzeko
        botoi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {     
                Login login = new Login();
                login.setVisible(true);
                currentFrame.dispose();
            }
        });
        
        // "SORTU ERABILTZAILEA" botoia sakatzean erabiltzailea datu-basean sartzea
        botoi2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = jtfe1.getText();
                String abizena = jtfe2.getText();
                String helbidea = jtfe3.getText();
                String emaila = jtfe4.getText();
                
                // Eremu guztiak bete badira
                if (usuario.isEmpty() || abizena.isEmpty() || helbidea.isEmpty() || emaila.isEmpty()) {
                    JOptionPane.showMessageDialog(Erregistratu.this, "Mesedez, bete eremu guztiak.", "Abisua", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                try {
                    bd.konektatu(); // Datu-basearekin konektatu
                    bd.erabiltzaileaGorde(usuario, abizena, helbidea, emaila); // Erabiltzailea datu-basean gorde
                    JOptionPane.showMessageDialog(Erregistratu.this, "Erabiltzailea sortu da", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
                    currentFrame.dispose(); // Pantaila itxi
                    Login login = new Login();
                    login.setVisible(true); // Login klasea irekitu
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Erregistratu.this, "Errorea erabiltzailea sortzerakoan: " + ex.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
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
            id = bd.getMaxId(); // Erabiltzaile identifikadorea hartu
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        jtfe5.setText(id); // Erabiltzaile identifikadorea erakutsi
        
        // Eremuak eta botoiak panel nagusian kokatu
        mainPanel.add(label);
        mainPanel.add(jtfe5);
        mainPanel.add(label1);
        mainPanel.add(jtfe1);
        mainPanel.add(label2);
        mainPanel.add(jtfe2);
        mainPanel.add(label3);
        mainPanel.add(jtfe3);
        mainPanel.add(label4);
        mainPanel.add(jtfe4);
        mainPanel.add(botoi1);
        mainPanel.add(botoi2);
        
        // Panelaren itxura eta koloreak ezarri
        mainPanel.setBackground(Color.black);
        jtfe1.setBorder(new LineBorder(Color.red));
        jtfe2.setBorder(new LineBorder(Color.red));
        jtfe3.setBorder(new LineBorder(Color.red));
        jtfe4.setBorder(new LineBorder(Color.red));
        jtfe5.setBorder(new LineBorder(Color.red));
        
        label.setForeground(Color.white);
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        label4.setForeground(Color.white);
        
        botoi1.setForeground(Color.white);
        botoi1.setBackground(Color.red);
        botoi2.setForeground(Color.white);
        botoi2.setBackground(Color.red);
        
        this.add(mainPanel); // Pantailari main panela gehitu
    }
}