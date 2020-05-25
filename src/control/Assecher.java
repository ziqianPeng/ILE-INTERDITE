package control;

import modele.Modele;
import vue.Commandes;
import javax.swing.*;
import java.awt.event.MouseEvent;

public class Assecher extends Controleur {
    private final Commandes commandes;

    public Assecher(Modele m,Commandes c){
        super(m);
        this.commandes = c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(commandes.getAss()){
            commandes.addAssecher();
            modele.notifyObservers();
        }else {
            JButton b = (JButton)e.getSource();
            if("UP".equals(b.getText())){
                this.modele.assecher(KeyControleur.Direction.up);
            }else if("DOWN".equals(b.getText())) {
                this.modele.assecher(KeyControleur.Direction.down);
            }else if("LEFT".equals(b.getText())) {
                this.modele.assecher(KeyControleur.Direction.left);
            }else{
                this.modele.assecher(KeyControleur.Direction.right);
            }
            commandes.reset();
            modele.notifyObservers();
        }
    }
    
}

