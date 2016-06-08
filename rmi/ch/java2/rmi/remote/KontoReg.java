package ch.java2.rmi.remote;

import java.rmi.Naming;

public class KontoReg
{
	public static void main(String[] args)
	{
		KontoImpl jk;
		try
		{
			System.out.println("kontoReg");
			jk = new KontoImpl("jimKnopf");
			Naming.rebind("jimKnopf", jk);
			System.out.println("Konto registriert");
		}
		catch (Exception e)
		{
			System.out.println("Konto konnte nicht registriert werden: "+e.getMessage());
		}
	}
}
