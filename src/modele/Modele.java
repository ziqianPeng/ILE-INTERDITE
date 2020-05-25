package modele;

import control.KeyControleur;

import java.util.ArrayList;
import java.util.Random;

public class Modele extends vue.Observable {
    public static final int HAUTEUR = 20, LARGEUR = 20;
    public static final int heli = HAUTEUR/2;
    private static final int nbJoueur = 4;
    protected Zone[][] zones;
    private final Random random = new Random();
    protected int joueurIdx = 0;
    protected ArrayList<Joueur> joueurs = new ArrayList<>();
    public enum Artefact{air,eau,terre,feu,normal}


    public Modele() {
        zones = new Zone[LARGEUR+2][HAUTEUR+2];
        for (int i = 0; i < LARGEUR+2 ; i++) {
            for (int j = 0; j < HAUTEUR+2; j++) {
                zones[i][j] = new Zone(this,i,j);
            }
        }
        init();
        this.findeTour(30);
    }

    public void init() {
        for (int i = 0; i <= LARGEUR; i++) {
            for (int j = 0; j <= HAUTEUR; j++) {
                zones[i][j].etat = new Etat_Normal();
                if(i <= LARGEUR/2){
                    if(j <= HAUTEUR/2){
                        zones[i][j].setType(Artefact.air);
                    } else {
                        zones[i][j].setType(Artefact.eau);
                    }
                } else {
                    if(j <= HAUTEUR/2){
                        zones[i][j].setType(Artefact.feu);
                    } else {
                        zones[i][j].setType(Artefact.terre);
                    }
                }
            }
        }
        for (int i = 0; i < nbJoueur ; i++) {
            joueurs.add(new Joueur(this,random.nextInt(LARGEUR)+1,random.nextInt(HAUTEUR)+1));
        }
    }

    public void move(KeyControleur.Direction e){
        Joueur courant = getJoueurCourant();
        if(courant.TestAction()) {
            Zone pos = courant.getPosition();
            int new_Y = pos.getY();
            int new_X = pos.getX();
            switch (e){
                case up:
                    if(zones[new_X][new_Y-1].getstatus()==3) {
                        return;
                    }
                    new_Y -= 1;
                    break;
                case down:
                    if(zones[new_X][new_Y+1].getstatus()==3) {
                        return;
                    }
                    new_Y += 1;
                    break;
                case left:
                    if(zones[new_X-1][new_Y].getstatus()==3) {
                        return;
                    }
                    new_X -= 1;
                    break;
                case right:
                    if(zones[new_X+1][new_Y].getstatus()==3) {
                        return;
                    }
                    new_X += 1;
                    break;
                default:
                    break;
            }
            if (new_Y <= Modele.HAUTEUR && new_Y > 0 && new_X <= Modele.LARGEUR && new_X > 0) {
                courant.position = this.getZone(new_X,new_Y);
            }else{
                return;
            }
            courant.addAction();
        }
        notifyObservers();
    }

    public void assecher(KeyControleur.Direction e){
        Joueur courant = getJoueurCourant();
        int x=courant.getPosition().getX();
        int y=courant.getPosition().getY();
        courant.addAction();
        switch (e){
            case up:
                if(zones[x][y-1].getstatus()!=1){
                    return;
                }
                zones[x][y-1].assecher();
            case down:
                if(zones[x][y+1].getstatus()!=1){
                    return;
                }
                zones[x][y+1].assecher();
            case right:
                if(zones[x+1][y].getstatus()!=1){
                    return;
                }
                zones[x+1][y].assecher();
            case left:
                if(zones[x-1][y].getstatus()!=1){
                    return;
                }
                zones[x-1][y].assecher();
        }
    }

    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }

    public Zone getZone(int x,int y){
        return zones[x][y];
    }

    public Joueur getJoueurCourant(){
        return this.joueurs.get(this.joueurIdx);
    }

    public void findeTour(int nbr){
        while (nbr>0){
            int x = random.nextInt(LARGEUR+1);
            int y = random.nextInt(HAUTEUR+1);
            if(zones[x][y].nonSubmerge() && (x != heli || y != heli)){
                zones[x][y].evolue();
                nbr -= 1;
            }
        }
        int bonneChance = random.nextInt(LARGEUR*HAUTEUR-2);
        this.getJoueurCourant().getcle(bonneChance);
        this.getJoueurCourant().resetNbr();

        joueurIdx += 1;
        if(joueurIdx == nbJoueur){
            joueurIdx = 0;
        }
        notifyObservers();
        End();
    }

    public ArrayList<Artefact> arteApparu(){
        ArrayList<Artefact> arts= new ArrayList<>();
        ArrayList<Cle> cles= new ArrayList<>();
        cles.add(new CleAir());
        cles.add(new CleEau());
        cles.add(new CleFeu());
        cles.add(new CleTerre());

        for(Cle c : cles){
            if(c.allKey()!= Artefact.normal){
                arts.add(c.allKey());
            }
        }
        return arts;
    }
     public boolean avoirTousArtefact(){
        int nbT=0;
        for (Joueur j:joueurs){
            nbT+=j.artefacts.size();
        }
         return nbT == 4;
     }

    public boolean gameEnd(){
        for(Joueur j:joueurs){
            if(j.position!=zones[heli][heli]){
                return false;
            }
        }
        return avoirTousArtefact();
    }

    public void End(){
        checkEnd(gameEnd());
    }
}





