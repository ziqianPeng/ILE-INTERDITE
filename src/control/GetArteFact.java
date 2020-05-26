package control;

import modele.Modele;
import modele.Modele.Artefact;
import vue.Commandes;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class GetArteFact extends Controleur {
    private Commandes commandes;

        public GetArteFact(Modele modele,Commandes commandes) {
            super(modele);
            this.commandes = commandes;
        }

        public void mouseClicked(MouseEvent e) {
            JButton b = (JButton)e.getSource();
            if("getAIR".equals(b.getText())){
                boolean get = this.modele.getJoueurCourant().getArteFact(Artefact.air);
                if(get){this.commandes.remove(b);}
            }else if("getEAU".equals(b.getText())) {
                boolean get = this.modele.getJoueurCourant().getArteFact(Artefact.eau);
                if(get){this.commandes.remove(b);}
            }else if("getTERRE".equals(b.getText())) {
                boolean get = this.modele.getJoueurCourant().getArteFact(Artefact.terre);
                if(get){this.commandes.remove(b);}
            }else{
                boolean get = this.modele.getJoueurCourant().getArteFact(Artefact.feu);
                if(get){this.commandes.remove(b);}
            }
        }

}
