package Modele;

public class Celules {
    private Modele modele;
    private int x;
    private int y;
    protected Etat etat;
    public Celules(Modele m,int x,int y){
        this.modele=m;
        this.x=x;
        this.y=y;
    }

    public String toString() {
        if(this.etat== Etat.Inondee){
            return "|"+"I";
        }
        else if (this.etat== Etat.Submergee){
            return "|"+"S";
        }
        else if(this.etat== Etat.Normale) {
            return "|" + "N";
        }
        else{
            return "|  ";
        }
    }
}
