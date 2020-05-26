package vue;

import control.KeyControleur;
import modele.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Vue extends JFrame implements Observer{
    private final Grille grille;
    public Commandes commandes;
    private final Info info;

    public Vue(Modele modele) throws IOException {
        modele.addObserver(this);
        this.setTitle("L'île interdite");
        this.setLayout(new BorderLayout());

        KeyControleur kc = new KeyControleur(modele);

        grille = new Grille(modele);
        this.add(grille,BorderLayout.CENTER);

        commandes = new Commandes(modele);
        this.add(commandes,BorderLayout.NORTH);

        info = new Info(modele);
        this.add(info,BorderLayout.EAST);
        this.addKeyListener(kc);


        this.setPreferredSize(new Dimension(1000,850));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public void endGameScreen() {
        this.remove(grille);
        this.remove(info);
        this.remove(commandes);
        JLabel label = new JLabel("GAME OVER");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        this.getContentPane().add(label);
        this.repaint();
        try {
            Clip clip_dead = AudioSystem.getClip();
            AudioInputStream ais_dead = AudioSystem.getAudioInputStream(new File("materials/dead.wav"));
            clip_dead.open(ais_dead);
            clip_dead.loop(0);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void end() {
        endGameScreen();
    }
}


class Grille extends JPanel implements Observer {
    private final Modele modele;
    private final static int TAILLE = 30;

    private BufferedImage terre;
    private BufferedImage eau;
    private BufferedImage heli;
    private BufferedImage air;
    private BufferedImage feu;
    private BufferedImage innonde;
    private BufferedImage submerge;

    private BufferedImage joueur1;
    private BufferedImage joueur2;
    private BufferedImage joueur3;
    private BufferedImage joueur4;

    private BufferedImage artefact1;
    private BufferedImage artefact2;
    private BufferedImage artefact3;
    private BufferedImage artefact4;


    Grille(Modele m) throws IOException {
        modele = m;
        Dimension dimension = new Dimension(
                TAILLE * Modele.LARGEUR, TAILLE * (Modele.HAUTEUR+5));
        this.setPreferredSize(dimension);
        modele.addObserver(this);
        init();
    }

    private void init() throws IOException {
        terre = ImageIO.read(new File("materials/terre.png"));
        eau = ImageIO.read(new File("materials/water.png"));
        heli = ImageIO.read(new File("materials/heli.png"));
        air = ImageIO.read(new File("materials/air.png"));
        feu = ImageIO.read(new File("materials/feu.png"));
        joueur1 = ImageIO.read(new File("materials/joueur1.png"));
        joueur2 = ImageIO.read(new File("materials/joueur2.png"));
        joueur3 = ImageIO.read(new File("materials/joueur3.png"));
        joueur4 = ImageIO.read(new File("materials/joueur4.png"));
        innonde = ImageIO.read(new File("materials/innonde.png"));
        submerge = ImageIO.read(new File("materials/submerge.png"));
        artefact1 =ImageIO.read(new File("materials/artefact1.png"));
        artefact2 =ImageIO.read(new File("materials/artefact2.png"));
        artefact3 =ImageIO.read(new File("materials/artefact3.png"));
        artefact4 =ImageIO.read(new File("materials/artefact4.png"));
    }

    public void update() {
        repaint();
    }

    @Override
    public void end() {

    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for (int i = 1; i <= Modele.LARGEUR; i++) {
            for (int j = 1; j <= Modele.HAUTEUR; j++) {
                paint(g, modele.getZone(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
            }
        }

        ArrayList<Joueur> list_joueur = modele.getJoueurs();
        for (int i = 0; i < list_joueur.size(); i++) {
            Zone pos = list_joueur.get(i).getPosition();
            paintJoueur(g, i, (pos.getX() - 1) * TAILLE + 1, (pos.getY() - 1) * TAILLE + 1);
        }

        Zone pos = modele.getJoueurCourant().getPosition();
        paintJoueurCourant(g, (pos.getX() - 1) * TAILLE + 2, (pos.getY() - 1) * TAILLE + 2);

        g.drawImage(heli,(Modele.heli -1)*TAILLE, (Modele.heli -1)*TAILLE,this);
        paintAnnexe(g);
    }

    private void paintAnnexe(Graphics g){
        String text;
        BufferedImage[] zones = {air, eau,terre,feu};
        g.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            g.drawImage(zones[i],TAILLE*(1+i*4),(Modele.HAUTEUR+1)*TAILLE,this);
            text = "Zone " + Modele.Artefact.values()[i].toString();
            g.drawString(text,TAILLE*(2+i*4)+10,(Modele.HAUTEUR+1)*TAILLE+20);
        }

        BufferedImage[] joueurs = {joueur1,joueur2,joueur3,joueur4};
        for (int i = 0; i < 4; i++) {
            g.drawImage(joueurs[i],TAILLE*(1+i*4),(Modele.HAUTEUR+2)*TAILLE+5,this);
            text = "Joueur " + i;
            g.drawString(text,TAILLE*(2+i*4)+10,(Modele.HAUTEUR+2)*TAILLE+25);
        }

        g.drawImage(innonde,TAILLE,(Modele.HAUTEUR+3)*TAILLE+10,this);
        text = "Innonde";
        g.drawString(text,TAILLE*2+10,(Modele.HAUTEUR+3)*TAILLE+30);

        g.drawImage(submerge,TAILLE*5,(Modele.HAUTEUR+3)*TAILLE+10,this);
        text = "Submerge";
        g.drawString(text,TAILLE*6+10,(Modele.HAUTEUR+3)*TAILLE+30);

        BufferedImage[] artefacts = {artefact1,artefact2,artefact3,artefact4};

        for (int i = 0; i < 4; i++) {
            g.drawImage(artefacts[i],TAILLE*(1+i*4),(Modele.HAUTEUR+4)*TAILLE+15,this);
            text = "Artf " + Modele.Artefact.values()[i].toString();
            g.drawString(text,TAILLE*(2+i*4)+10,(Modele.HAUTEUR+4)*TAILLE+35);
        }
    }

    private void paintJoueurCourant(Graphics g,int x,int y){
        g.setColor(Color.MAGENTA);
        g.fillRect(x+1, y+1, 8, 8);
    }

    private void paintJoueur(Graphics g, int i, int x, int y) {
        switch (i) {
            case 0:
                g.drawImage(joueur1,x,y,this);
                return;
            case 1:
                g.drawImage(joueur2,x,y,this);
                return;
            case 2:
                g.drawImage(joueur3,x,y,this);
                return;
            case 3:
                g.drawImage(joueur4,x,y,this);
                return;
            default:
        }
    }

    private void paint(Graphics g, Zone c, int x, int y) {
        switch (c.getType()){
            case normal:
                break;
            case air:
                g.drawImage(air,x, y,this);
                break;
            case eau:
                g.drawImage(eau,x, y,this);
                break;
            case feu:
                g.drawImage(feu,x, y,this);
                break;
            case terre:
                g.drawImage(terre,x, y,this);
                break;
            default:
                break;
        }
        switch (c.getstatus()) {
            case 1:
                g.drawImage(innonde,x, y,this);
                break;
            case 2:
                g.drawImage(submerge,x, y,this);
                break;
            default:
        }
    }
}





