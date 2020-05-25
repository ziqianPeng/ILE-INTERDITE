package modele;

public class Zone{
    private Modele modele;
    private final int x;
    private final int y;
    protected Etat etat;
    protected Modele.Artefact type;


    public Zone(Modele m, int x, int y){
        this.x = x;
        this.y = y;
        this.modele=m;
    }

    public void setType(Modele.Artefact type){
        this.type=type;
    }

    public Modele.Artefact getType(){
        return this.type;
    }

    public String toString(){return "posit: " + this.x+" " +this.y;}


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
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
}
