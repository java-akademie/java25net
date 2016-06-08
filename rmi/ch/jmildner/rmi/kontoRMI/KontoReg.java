package ch.jmildner.rmi.kontoRMI;

import java.rmi.Naming;

public class KontoReg
{
	public static void main(String[] args)
	{
		registrieren("jimKnopf");
		registrieren("maxMeier");
	}


	public static void registrieren(String name)
	{
		try
		{
			KontoImpl acct = new KontoImpl(name);
			Naming.rebind(name, acct);
			System.out.println(name + " registriert");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
