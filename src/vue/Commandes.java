package vue;

import control.*;
import modele.Modele;
import control.GetArteFact;

import javax.swing.*;
import java.util.ArrayList;

public class Commandes extends JPanel {

    private Modele modele;
    private boolean ass = true;
    private boolean ecg = true;
    private final ArrayList<String> artefactes = new ArrayList<>();

    JButton next_tour = new JButton("FIN DU TOUR");

    JButton assecher = new JButton("ASSECHER");
    JButton up = new JButton("UP");
    JButton down = new JButton("DOWN");
    JButton left = new JButton("LEFT");
    JButton right = new JButton("RIGHT");
    JButton mid = new JButton("MID");
    JButton[] directions = {up,down,left,right,mid};

    JButton echange = new JButton("ECHANGE");
    JButton air = new JButton("AIR");
    JButton eau = new JButton("EAU");
    JButton feu = new JButton("FEU");
    JButton terre = new JButton("TERRE");
    JButton[] artefacts = {air,eau,feu,terre};


    Controleur AssListener;
    KeyControleur kc;
    Controleur move;


    Commandes(Modele m) {
        modele = m;

        this.add(next_tour);
        this.add(assecher);
        this.add(echange);

        AssListener = new Assecher(m,this);
        for (JButton b: directions) {
            b.addMouseListener(AssListener);
        }
        assecher.addMouseListener(AssListener);

        move = new FindeTour(modele,this);
        next_tour.addMouseListener(move);

        kc = new KeyControleur(modele);
        next_tour.addKeyListener(kc);
        assecher.addKeyListener(kc);

        this.addArtefacts();
    }

    public boolean getAss(){
        return ass;
    }

    public void addAssecher(){
        for (JButton b: directions) {
            this.add(b);
        }

        this.remove(assecher);
        this.remove(echange);

        ass = false;
        repaint();
        this.doLayout();
    }

    public void addEchange(){
        this.remove(echange);
        this.remove(assecher);

        for (JButton b: artefacts) {
            this.add(b);
        }

        ass = false;
        repaint();
        this.doLayout();
    }

    public void reset(){
        for (JButton b: directions) {
            this.remove(b);
        }

        this.add(assecher);
        this.add(echange);

        ass = true;
        repaint();
        this.setPreferredSize(getPreferredSize());
    }

    private void addArte(String artefact){
        if(!this.artefactes.contains(artefact)){
            JButton arte = new JButton(artefact);
            GetArteFact getArt = new GetArteFact(modele,this);
            arte.addMouseListener(getArt);
            this.artefactes.add(artefact);
            this.add(arte);
        }
    }

    public void addArtefacts(){
        ArrayList<Modele.Artefact> artfacts = this.modele.arteApparu();
        if(artfacts.contains(Modele.Artefact.air)){
            this.addArte("getAIR");
        }
        if(artfacts.contains(Modele.Artefact.eau)){
            this.addArte("getEAU");
        }
        if(artfacts.contains(Modele.Artefact.terre)){
            this.addArte("getTerre");
        }
        if(artfacts.contains(Modele.Artefact.feu)){
            this.addArte("getFEU");
        }
    }

}