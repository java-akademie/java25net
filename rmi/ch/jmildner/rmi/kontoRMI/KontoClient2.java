package ch.jmildner.rmi.kontoRMI;

import java.rmi.Naming;

public class KontoClient2
{
	public static void main(String[] args)
	{
		String ip = args[0];
		try
		{
			Konto maxMeier = (Konto) Naming.lookup("rmi://" + ip
					+ "/maxMeier");
			for (int i = 1; i <= 10; i++)
			{
				maxMeier.einzahlen(i * 100);
				System.out.println(maxMeier.getRecord());
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
