package modele;

abstract class Etat{
    abstract public int get_status();

}

class Etat_Normal extends Etat{
    @Override
    public int get_status() {
        return 0;
    }
}

class Etat_Inondee extends Etat{
    @Override
    public int get_status() {
        return 1;
    }
}

class Etat_Submerge extends Etat{
    @Override
    public int get_status() {
        return 2;
    }
}