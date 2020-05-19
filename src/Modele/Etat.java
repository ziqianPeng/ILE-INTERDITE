package Modele;

abstract class Etat{
    private static int status;
    abstract public int get_status();

}

class Etat_Normal extends Etat{
    private static final int status = 0;
    @Override
    public int get_status() {
        return status;
    }
}

class Etat_Inondee extends Etat{
    private static final int status = 1;
    @Override
    public int get_status() {
        return status;
    }
}

class Etat_Submerge extends Etat{
    private static final int status = 2;
    @Override
    public int get_status() {
        return status;
    }
}