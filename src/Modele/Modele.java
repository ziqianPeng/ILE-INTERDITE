package Modele;

public class Modele {
    public static final int HAUTEUR = 40, LARGEUR = 60;
    protected Celules[][] cellules;
    public static final int Heli_X=36, Heli_Y=5;//la coordonne de la helipot

    public Modele() {
        cellules = new Celules[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                cellules[i][j]=new Celules(this,i,j);
            }
        }
        Set_type();
    }
    public void Set_type() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                if (Math.random() < .3) {
                    cellules[i][j].etat = Etat.Normale;
                }else if (Math.random()<.6)
                    cellules[i][j].etat = Etat.Inondee;
                else
                    cellules[i][j].etat = Etat.Submergee;
            }
        }
    }
}
