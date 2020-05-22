package Modele;

import Control.ZoneCliquable;

public class Zone extends ZoneCliquable {
    private Modele modele;
    private final int x;
    private final int y;
    protected Etat etat;


    public Zone(Modele m, int x, int y){
        super(x,y);
        this.x = x;
        this.y = y;
        this.modele=m;
    }

    public String toString() {
        switch (this.etat.get_status()){
            case 0:
                return "|" + "N";
            case 1:
                return "|"+"I";
            case 2:
                return "|"+"S";
            default:
                return "|  ";
        }
    }

    public void evolue(){
        if(etat instanceof Etat_Normal){
            etat = new Etat_Inondee();
        } else if (etat instanceof Etat_Inondee){
            etat = new Etat_Submerge();
        }
    }

    public void assecher(){
        if(etat instanceof Etat_Inondee){
            etat = new Etat_Normal();
        }
    }

    public boolean nonSubmerge(){
        return !(this.etat instanceof Etat_Submerge);
    }

    public int getstatus() {
        return this.etat.get_status();
    }

    @Override
    public void clicGauche() {}
    @Override
    public void clicDroit() {}

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
