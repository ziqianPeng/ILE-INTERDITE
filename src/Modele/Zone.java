package Modele;

public class Zone {
    private Modele modele;
    private int x;
    private int y;
    protected Etat etat;
    public Zone(Modele m, int x, int y){
        this.modele=m;
        this.x=x;
        this.y=y;
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

    public int getstatus() {
        return this.etat.get_status();
    }

    public int getjoueur() {
        // TODO
        // 有没有玩家 返回1表示有人
        return 0;
    }
}
