package ch.jmildner.rmi.kontoRMI;

import java.rmi.Naming;

public class KontoReg2
{
	public static void main(String[] args)
	{
		try
		{
			KontoImpl acct = new KontoImpl("Max Meier");
			Naming.rebind("maxMeier", acct);
			System.out.println("Konto Max Meier registriert");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
