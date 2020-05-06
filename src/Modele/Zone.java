package Modele;

public class Zone {
    private Etat etat;
    public Zone(Etat e){
        this.etat = e;
    }

    public int getstatus() {
        // TODO
        // 地面的情况 返回0或1或2表示三种情况
        return 0;
    }

    public int getjoueur() {
        // TODO
        // 有没有玩家 返回1表示有人
        return 0;
    }
}
