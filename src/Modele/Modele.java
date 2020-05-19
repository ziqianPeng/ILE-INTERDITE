package Modele;

import java.util.Random;

public class Modele extends Vue.Observable {
    public static final int HAUTEUR = 40, LARGEUR = 60;
    protected Zone[][] cellules;
    private final Random random = new Random();
    public static final int Heli_X=36, Heli_Y=5;//la coordonne de la helipot

    public Modele() {
        cellules = new Zone[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                cellules[i][j] = new Zone(this,i,j);
            }
        }
        init();
    }

    public void init() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                cellules[i][j].etat = new Etat_Normal();
            }
        }
    }

    public void avance(){
        for (int i = 0; i < 3 ; i++) {
            cellules[random.nextInt(40)][random.nextInt(40)].evolue();
        }
        notifyObservers();
    }

    public Zone getCellule(int i, int j) {
        return cellules[i][j];
    }
}
