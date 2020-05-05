package Modele;

public class Modele {

    public Modele(){}

    public void inontationAlea(int nbr){}
    public String toString(){
        return "je suis le debut";
    }


}


class Zone {
    private Etat etat;
    public Zone(Etat e){
        this.etat = e;
    }
}

abstract class Etat{}

class Normale extends Etat{}
class Inondee extends Etat{}
class Submerge extends Etat{}