package modele;

import  modele.Modele.Artefact;

abstract class Cle {
    protected final int nbrMax = 4 ;
    abstract boolean addCle();
    abstract Artefact allKey();
}

class CleAir extends Cle{
    private static int nbr = 0;

    public boolean addCle(){
        if(nbr <nbrMax){
            nbr ++;
            System.out.println("cle"+ nbr);
            return true;
        }
        return false;
    }

    public Modele.Artefact allKey() {
        if (nbr == this.nbrMax) {
            return Modele.Artefact.air;
        }
        return Modele.Artefact.normal;
    }

}

class CleEau extends Cle{
    private Artefact cle = Artefact.eau;
    private static int nbr = 0;

    public boolean addCle(){
        if(nbr <nbrMax){
            nbr++;
            System.out.println("cle shui"+ nbr);
            return true;
        }
        return false;
    }

    public Modele.Artefact allKey() {
        if (nbr == this.nbrMax) {
            return Modele.Artefact.eau;
        }
        return Modele.Artefact.normal;
    }
}

class CleTerre extends Cle{
    private Artefact cle = Artefact.terre;
    private static int nbr = 0;

    public boolean addCle(){
        if(nbr <nbrMax){
            nbr++;
            System.out.println("cle terre"+ nbr);
            return true;
        }
        return false;
    }
    public Modele.Artefact allKey() {
        if (nbr == this.nbrMax) {
            return Modele.Artefact.terre;
        }
        return Modele.Artefact.normal;
    }
}

class CleFeu extends Cle{
    private Artefact cle = Artefact.feu;
    private static int nbr = 0;

    public boolean addCle(){
        if(nbr <nbrMax){
            nbr++;
            System.out.println("cle feu"+ nbr);
            return true;
        }
        return false;
    }

    public Modele.Artefact allKey() {
        if (nbr == this.nbrMax) {
            return Modele.Artefact.feu;
        }
        return Modele.Artefact.normal;
    }
}

