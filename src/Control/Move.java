package Control;

import Modele.Modele;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Move extends Controleur implements KeyListener, MouseListener {


    public Move(Modele modele) {
        super(modele);
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("CHECK3");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        modele.getJoueurCourant().move(e);
        System.out.println("CHECK2");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        modele.getJoueurCourant().move(e);
        System.out.println("CHECK");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        modele.getJoueurCourant().move(e);
        System.out.println("CHECK3");
    }
}
