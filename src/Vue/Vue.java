package Vue;

import Control.Controleur;
import Control.FindeTour;
import Control.Move;
import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Vue {
    private JFrame frame;
    private Grille grille;
    private Commandes commandes;

    public Vue(Modele modele){
        frame = new JFrame();
        frame.setTitle("L'île interdite");
        frame.setLayout(new FlowLayout());

        grille = new Grille(modele);
        frame.add(grille,BorderLayout.NORTH);

        commandes = new Commandes(modele);
        frame.add(commandes,BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

   // public void init(){ grille.paintComponent();}

}

class Grille extends JPanel implements Observer {
    private final Modele modele;
    private final static int TAILLE = 12;


    Grille(Modele m) {
        modele = m;
        Dimension dimension = new Dimension(
                TAILLE * Modele.LARGEUR, TAILLE * Modele.HAUTEUR);
        this.setPreferredSize(dimension);
        modele.addObserver(this);
    }

    public void update() {
        repaint();

    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i = 1; i <= Modele.LARGEUR; i++) {
            for(int j = 1; j <= Modele.HAUTEUR; j++) {
                paint(g, modele.getZone(i, j), (i-1)*TAILLE, (j-1)*TAILLE);
            }
        }
        ArrayList<Joueur> list_joueur = modele.getJoueurs();

        for (int i = 0; i < list_joueur.size() ; i++) {
            Zone pos = list_joueur.get(i).getPosition();
            paint_joueur(g,i,(pos.getX()-1)*TAILLE+1,(pos.getY()-1)*TAILLE+1);
        }
    }

    private void paint_joueur(Graphics g, int i, int x, int y){
        switch (i){
            case 0:
                g.setColor(Color.RED);
                break;
            case 1:
                g.setColor(Color.YELLOW);
                break;
            case 2:
                g.setColor(Color.GREEN);
                break;
            case 3:
                g.setColor(Color.PINK);
                break;
            default:
                g.setColor(Color.BLACK);
                break;
        }
        g.fillRect(x, y, TAILLE-2, TAILLE-2);
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
    }
}

class Commandes extends JPanel{
    private Modele modele;

    JButton next_tour = new JButton("FIN DU TOUR");
    JButton clean = new JButton("ASSECHER");
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

        Controleur mouse = new FindeTour(modele);
        next_tour.addMouseListener(mouse);
        clean.addMouseListener(mouse);

        Move move = new Move(modele);
        this.addKeyListener(move);
        up.addMouseListener(move);


    }
}

