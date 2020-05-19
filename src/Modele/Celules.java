
public class Celules {
    private Modele modele;
    private int x;
    private int y;
    protected Type etat;
    public Celules(Modele m,int x,int y){
        this.modele=m;
        this.x=x;
        this.y=y;
    }

    public String toString() {
        if(this.etat==Type.Inondee){
            return "|"+"I";
        }
        else if (this.etat==Type.Submergee){
            return "|"+"S";
        }
        else if(this.etat==Type.Normale) {
            return "|" + "N";
        }
        else{
            return "|  ";
        }
    }
}
