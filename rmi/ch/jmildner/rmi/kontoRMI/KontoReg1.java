package ch.jmildner.rmi.kontoRMI;

import java.rmi.Naming;

public class KontoReg1
{
	public static void main(String[] args)
	{
		try
		{
			KontoImpl acct = new KontoImpl("Jim Knopf");
			Naming.rebind("jimKnopf", acct);
			System.out.println("Konto Jim Knopf registriert");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
