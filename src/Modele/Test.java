package Modele;

public class Test {
    public static void main(String[] args) {
        Modele modele=new Modele();
        for(Celules[] cl:modele.cellules){
            for(Celules c:cl){
                System.out.print(c);
            }
            System.out.println();
        }
    }

}
