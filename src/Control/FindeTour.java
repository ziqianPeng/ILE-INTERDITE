package Control;

import Modele.Modele;

import java.awt.event.MouseEvent;

public class FindeTour extends Controleur  {

    public FindeTour(Modele m){
        super(m);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.modele.findeTour(3);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }

    public void print(){System.out.println("Fin de tour");}

}

class Assecher extends Controleur{

    public Assecher(Modele m){
        super(m);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.modele.findeTour(3);
    }
}