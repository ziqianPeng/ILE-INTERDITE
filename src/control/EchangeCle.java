package control;

import modele.Modele;
import vue.Commandes;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class EchangeCle extends Controleur {
    private Commandes commandes;

    public EchangeCle(Modele modele,Commandes commandes) {
        super(modele);
        this.commandes = commandes;
    }

    public void mouseClicked(MouseEvent e) {
        JButton b = (JButton)e.getSource();
       // System.out.println("echange");
        if(commandes.getEchange()){
           // System.out.println("echange");
            commandes.addCleEchange();
            modele.notifyObservers();
        }else {
            switch (b.getText()) {
                case "air":
                    this.modele.cleEchange(Modele.Artefact.air);
                    break;
                case "eau":
                    this.modele.cleEchange(Modele.Artefact.eau);
                    break;
                case "terre":
                    this.modele.cleEchange(Modele.Artefact.terre);
                case "feu":
                    this.modele.cleEchange(Modele.Artefact.feu);
                    break;
                default:
                    break;
            }
            commandes.resetEchange();
            modele.notifyObservers();
        }
    }


}
