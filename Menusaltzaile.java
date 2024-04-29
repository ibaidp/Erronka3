package Erronka3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Klase hau saltzaileen menua erakusten duen Frame-a da.
 */
public class Menusaltzaile extends JFrame {
    private JFrame currentFrame; // Uneko Frame-a gordetzeko atributua

    /**
     * Menusaltzaile klasearen konstruktorea.
     */
    public Menusaltzaile() {
        this.setTitle("Menu saltzaile"); // Titulua ezartzen da
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Frame-a ixteko operazioa ezartzen da
        this.setSize(400, 400); // Tamaina ezartzen da

        JPanel panel1 = new JPanel(new GridBagLayout()); // Panela sortzen da
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints objetua sortzen da
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5); // Elementuen arteko distantziak ezartzen dira

        // Botoiak sortzen dira
        JButton botoi1 = new JButton("PRODUKTUAK GEHITU");
        JButton botoi2 = new JButton("PRODUKTUAK EGUNERATU");
        JButton botoi3 = new JButton("BEZERO LISTA");
        JButton botoi4 = new JButton("LANGILEAK");
        JButton botoi5 = new JButton("ITXI SAIOA");

        // Botoien tamaina ezartzen da
        Dimension buttonSize = new Dimension(200, 30);
        botoi1.setPreferredSize(buttonSize);
        botoi2.setPreferredSize(buttonSize);
        botoi3.setPreferredSize(buttonSize);
        botoi4.setPreferredSize(buttonSize);
        botoi5.setPreferredSize(buttonSize);

        // Botoiak panelera gehitzen dira
        panel1.add(botoi1, gbc);
        panel1.add(botoi2, gbc);
        panel1.add(botoi3, gbc);
        panel1.add(botoi4, gbc);
        panel1.add(botoi5, gbc);

        setLocationRelativeTo(null); // Frame-a erdian kokatzen da

        this.add(panel1, BorderLayout.CENTER); // Panela Frame-an gehitzen da

        currentFrame = this; // Uneko Frame-a gordetzen da

        // Elementuen koloreak ezartzen dira
        panel1.setBackground(Color.black);
        botoi1.setBackground(Color.red);
        botoi2.setBackground(Color.red);
        botoi3.setBackground(Color.red);
        botoi4.setBackground(Color.red);
        botoi5.setBackground(Color.red);
        botoi1.setForeground(Color.white);
        botoi2.setForeground(Color.white);
        botoi3.setForeground(Color.white);
        botoi4.setForeground(Color.white);
        botoi5.setForeground(Color.white);

        // Botoien ekintzak gehitzen dira
        botoi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Biltegia biltegi = new Biltegia();
                biltegi.setVisible(true);
                currentFrame.dispose();
            }
        });
        botoi2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ProduktuakEguneratu prod = new ProduktuakEguneratu();
                prod.setVisible(true);
                currentFrame.dispose();
            }
        });
        botoi3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bezerolista lista = new Bezerolista();
                lista.setVisible(true);
                currentFrame.dispose();
            }
        });
        botoi4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Langileak langile = new Langileak();
                langile.setVisible(true);
                currentFrame.dispose();
            }
        });
        botoi5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                currentFrame.dispose();
            }
        });
    }
}