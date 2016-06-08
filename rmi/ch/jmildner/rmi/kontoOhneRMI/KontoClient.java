package ch.jmildner.rmi.kontoOhneRMI;

public class KontoClient
{
	public static void main(String[] args)
	{
		KontoImpl jk = new KontoImpl("jimKnopf", 10000.0);
		
		for (int i = 1; i <= 10; i++)
		{
			jk.einzahlen(i * 100);
			System.out.println(jk);
		}
		for (int i = 1; i <= 10; i++)
		{
			jk.einzahlen(i * -100);
			System.out.println(jk);
		}
	}
}
