package Modele;

public class Modele {
    public int LARGEUR;
    public int HAUTEUR;
    public Modele(){}

    public void inontationAlea(int nbr){}
    public String toString(){
        return "je suis le debut";
    }


    public Zone getCellule(int i, int j) {
        // TODO
        return null;
    }
}




abstract class Etat{}

class Normale extends Etat{}
class Inondee extends Etat{}
class Submerge extends Etat{}