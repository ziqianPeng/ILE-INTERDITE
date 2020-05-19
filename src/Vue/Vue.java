package Vue;

import Modele.*;
import Control.Controleur;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame frame;
    private Grille grille;
    private Commandes commandes;

    public Vue(Modele modele){
        frame = new JFrame();
        frame.setTitle("L'île interdite");
        frame.setLayout(new FlowLayout());

        grille = new Grille(modele);
        frame.add(grille);

        commandes = new Commandes(modele);
        frame.add(commandes);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }




}

class Grille extends JPanel implements Observer{
    private final Modele modele;
    private final static int TAILLE = 12;


    Grille(Modele m) {
        modele = m;
        Dimension dimension = new Dimension(
                TAILLE * Modele.LARGEUR, TAILLE * Modele.HAUTEUR);
        this.setPreferredSize(dimension);
        modele.addObserver(this);
    }

    public void update() { repaint(); }

    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i = 1; i <= Modele.LARGEUR; i++) {
            for(int j = 1; j <= Modele.HAUTEUR; j++) {
                paint(g, modele.getCellule(i, j), (i-1)*TAILLE, (j-1)*TAILLE);
            }
        }
    }

    private void paint(Graphics g, Zone c, int x, int y) {
        switch (c.getstatus()){
            case 0:
                g.setColor(Color.GRAY);
                break;
            case 1:
                g.setColor(Color.CYAN);
                break;
            case 2:
                g.setColor(Color.BLUE);
                break;
            default:
                g.setColor(Color.BLACK);
        }
        g.fillRect(x, y, TAILLE, TAILLE);
        if (c.getjoueur() == 1) {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, TAILLE-4, TAILLE-4);
        }
    }
}

class Commandes extends JPanel {
    private Modele modele;

    JButton next_tour = new JButton("Fin du tour");
    JButton clean = new JButton("CLEAN");
    JButton up = new JButton("UP");
    JButton down = new JButton("DOWN");
    JButton left = new JButton("LEFT");
    JButton right = new JButton("RIGHT");

    Commandes(Modele m) {
        modele = m;
        this.add(next_tour);
        this.add(clean);
        this.add(up);
        this.add(down);
        this.add(left);
        this.add(right);

        Controleur mouse = new Controleur(modele); //检测鼠标
        Controleur N = new Controleur(modele);
        Controleur M = new Controleur(modele);
        Controleur UP = new Controleur(modele);
        next_tour.addMouseListener(mouse);


    }
}

