package ch.jmildner.rmi.waehrungsrechner;

import java.rmi.Naming;

public class WaehReg
{
	public static void main(String[] args)
	{
		try
		{
			WaehImpl wr = new WaehImpl();
			Naming.rebind("waeh", wr);
			System.out.println("Waehrungsrechner registriert");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
