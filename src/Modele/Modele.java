package Modele;

import java.util.ArrayList;
import java.util.Random;

public class Modele extends Vue.Observable {
    public static final int HAUTEUR = 40, LARGEUR = 60;
    protected Zone[][] zones;
    private final Random random = new Random();
    protected int joueurIdx = 0;
    protected ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

    public Modele() {
        zones = new Zone[LARGEUR+2][HAUTEUR+2];
        for (int i = 0; i < LARGEUR+2 ; i++) {
            for (int j = 0; j < HAUTEUR+2; j++) {
                zones[i][j] = new Zone(this,i,j);
            }
        }
        init();
        this.findeTour(100);
    }

    public void init() {
        for (int i = 0; i <= LARGEUR; i++) {
            for (int j = 0; j <= HAUTEUR; j++) {
                zones[i][j].etat = new Etat_Normal();
            }
        }
        for (int i = 0; i < 20 ; i++) {
            joueurs.add(new Joueur(this,random.nextInt(LARGEUR)+1,random.nextInt(HAUTEUR)+1));
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
            int x = random.nextInt(LARGEUR);
            int y = random.nextInt(HAUTEUR);
            if(zones[x][y].nonSubmerge()){
                zones[x][y].evolue();
                nbr -= 1;
            }
        }
        this.getJoueurCourant().resetNbr();
        notifyObservers();
    }
}





