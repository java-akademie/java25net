package ch.java2.rmi.local;

public class KontoClient
{

    static KontoImpl jk;

    public static void main(String[] args) throws Exception
    {
        jk = new KontoImpl("Jim Knopf");

        verbuchen(+12000);
        verbuchen(-500);
        verbuchen(+2300);
        verbuchen(-355);
    }

    private static void verbuchen(double betrag)
    {
        System.out.println("\n\n");
        System.out.println("------------------------------------");
        System.out.printf("konto                   : %10s   \n", jk.getName());
        System.out.printf("kontostand vor  buchung : %+10.2f \n", jk.getKontostand());
        System.out.printf("buchung                 : %+10.2f \n", betrag);

        jk.verbuchen(betrag);

        System.out.printf("kontostand nach buchung : %+10.2f \n", jk.getKontostand());
        System.out.println("------------------------------------");
    }

}
