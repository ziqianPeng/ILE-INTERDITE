package control;

import Modele.Modele;
import control.ZoneCliquable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Color;


public class Controleur implements MouseListener {
    private Modele modele;
    public Controleur(Modele modele) {
        this.modele = modele;
    }


    public void clicGauche(ZoneCliquable z) {
        if(z instanceof FindeTour){
            z.clicGauche();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            this.clicGauche();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}

class FindeTour extends ZoneCliquable {
    private Modele modele;

    public FindeTour(int x, int y, Modele m){
        super("Fin de Tour",x,y);
        this.modele = m;
    }

    @Override
    public void clicGauche() {
        this.modele.inondationAlea(3);
    }

    @Override
    public void clicDroit() {}
}

