package modele;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import modele.Modele.Artefact;

public class Joueur {
    private int nbrAction = 0;
    protected Zone position;
    private Modele modele;
    protected HashMap<Artefact,Integer> cles = new HashMap<Artefact,Integer>();
    protected ArrayList<Modele.Artefact> artefacts = new ArrayList<>();


    public Joueur(Modele m,int x,int y){
        this.modele = m;
        while(modele.zones[x][y].getstatus()!=0){
            x=+1;
        }
        position = modele.getZone(x,y);
        this.cles.putIfAbsent(Artefact.air,0);
        this.cles.putIfAbsent(Artefact.eau,0);
        this.cles.putIfAbsent(Artefact.terre,0);
        this.cles.putIfAbsent(Artefact.feu,0);
    }


    public HashMap<Artefact,Integer> getCles(){
        return cles;
    }

    public boolean TestAction(){
        return this.nbrAction<3;
    }

    protected void resetNbr(){this.nbrAction = 0;}

    public int restNbr(){
        System.out.println(this.nbrAction);
        return 3-this.nbrAction;
    }

    public void addAction(){
        nbrAction++;
    }

    public Zone getPosition(){
        return position;
    }

    //part3

    public void getcle(int randomNbr){
        Random type = new Random();
        if(randomNbr%6 <3){
            Artefact acte = this.getPosition().getType();
            switch (acte) {
                case air:
                    Cle air = new CleAir();
                    if (air.addCle()) {
                        int n = this.cles.get(Artefact.air);
                        this.cles.replace(Artefact.air, n + 1);
                    }
                    break;
                case eau:
                    Cle eau = new CleEau();
                    if (eau.addCle()) {
                        int n = this.cles.get(Artefact.eau);
                        this.cles.replace(Artefact.eau, n + 1);
                    }
                    break;
                case terre:
                    Cle terre = new CleTerre();
                    if (terre.addCle()) {
                        int n = this.cles.get(Artefact.terre);
                        this.cles.replace(Artefact.terre, n + 1);
                    }
                    break;
                case feu:
                    Cle feu = new CleFeu();
                    if (feu.addCle()) {
                        int n = this.cles.get(Artefact.feu);
                        this.cles.replace(Artefact.feu, n + 1);
                    }
                    break;
                }
            }
    }

    public boolean getArteFact( Modele.Artefact artefact) {
        if (this.cles.get(artefact) == 4 && (!artefacts.contains(artefact))) {
            artefacts.add(artefact);
            return true;
        }
        return false;
    }

    public String getHasArt(Modele.Artefact artefact){
        if(artefacts.contains(artefact)){
            return "Oui";
        } else {
            return "Non";
        }
    }
}

