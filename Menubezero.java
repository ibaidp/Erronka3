package Erronka3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Klase hau bezeroen menura buruzko menu nagusia sortzen duen klasea da.
 */
public class Menubezero extends JFrame {
    private JFrame currentFrame; // Uneko Frame-a gordetzeko atributua

    /**
     * Menubezero klasearen konstruktorea.
     */
    public Menubezero() {
        this.setTitle("Menu bezero"); // Titulua ezartzen du
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Frame-a ixteko operazioa ezartzen du
        this.setSize(400, 400); // Tamaina ezartzen du

        JPanel panel1 = new JPanel(); // Panel bat sortzen du
        panel1.setBackground(Color.black); // Panelaren atzeko kolorea ezartzen du

        // Botoiak sortzen dira
        JButton botoi1 = new JButton("ITXI SAIOA");
        JButton botoi2 = new JButton("NIRE SASKIA");
        JButton botoi3 = new JButton("NIRE KONTUA");
        // Botoien koloreak eta marrazkiak ezartzen dira
        botoi1.setBackground(Color.black);
        botoi2.setBackground(Color.black);
        botoi3.setBackground(Color.black);
        botoi1.setForeground(Color.white);
        botoi2.setForeground(Color.white);
        botoi3.setForeground(Color.white);
        botoi1.setBorder(new LineBorder(Color.black));
        botoi2.setBorder(new LineBorder(Color.black));
        botoi3.setBorder(new LineBorder(Color.black));

        Dimension buttonSize = new Dimension(100, 50); // Botoien tamaina ezartzen du
        botoi1.setPreferredSize(buttonSize);
        botoi2.setPreferredSize(buttonSize);
        botoi3.setPreferredSize(buttonSize);

        // Botoiak panelera gehitzen dira
        panel1.add(botoi1);
        panel1.add(botoi2);
        panel1.add(botoi3);
        // Irudia kargatzen da
        ImageIcon imagenIcon = new ImageIcon("src/argazkia/GameStoplogo.jpg");
        Image imagenEscalada = imagenIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imagenEscaladaIcon = new ImageIcon(imagenEscalada);
        JLabel imagenLabel = new JLabel(imagenEscaladaIcon);
        panel1.add(imagenLabel);
        this.add(panel1); // Panela Frame-an gehitzen da

        setLocationRelativeTo(null); // Frame-a erdian kokatzen da

        currentFrame = this; // Uneko Frame-a gordetzen da
        // Botoiak aktibatzeko ekintza gehitzen dira
        botoi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                Login login = new Login();
                login.setVisible(true);
                currentFrame.dispose();
            }
        });
        botoi2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Niresaskia saski = new Niresaskia();
                saski.setVisible(true);
                currentFrame.dispose();
            }
        });
        botoi3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Nirekontua kontu = new Nirekontua();
                kontu.setVisible(true);
                currentFrame.dispose();
            }
        });
    }
}