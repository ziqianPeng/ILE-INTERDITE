package Modele;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Joueur {
    private int nbrAction = 0;
    private Zone position;
    private Modele modele;

    public Joueur(Modele m,int x,int y){
        this.modele = m;
        position = modele.getZone(x,y);
    }

    public boolean TestAction(){
        return this.nbrAction<3;
    }

    protected void resetNbr(){this.nbrAction = 0;}

    public int restNbr(){
        System.out.println(this.nbrAction);
        return 3-this.nbrAction;
    }

    public int nbrAction(){return nbrAction;}

    public void move(KeyEvent e){
        if(this.TestAction()) {
            this.nbrAction++;
            int new_Y = this.position.getY();
            int new_X = this.position.getX();
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    new_Y += 1;
                    break;
                case KeyEvent.VK_DOWN:
                    new_Y -= 1;
                    break;
                case KeyEvent.VK_LEFT:
                    new_X += 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    new_X -= 1;
                    break;
                default:
                    break;
            }
            if (new_Y <= Modele.HAUTEUR && new_Y > 0 && new_X <= Modele.LARGEUR && new_X > 0) {
                this.position = modele.getZone(new_X,new_Y);
            }
        }

    }

    public Zone getPosition(){
        return position;
    }
}
