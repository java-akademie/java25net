package ch.jmildner.rmi.kontoRMI;

import java.rmi.Naming;

public class KontoClient1
{
	public static void main(String[] args)
	{
		String ip = args[0];
		try
		{
			Konto jimKnopf = (Konto) Naming.lookup("rmi://" + ip
					+ "/jimKnopf");
			for (int i = 1; i <= 10; i++)
			{
				jimKnopf.einzahlen(i * 100);
				System.out.println(jimKnopf.getRecord());
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
