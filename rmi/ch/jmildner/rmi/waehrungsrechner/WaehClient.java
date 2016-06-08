package ch.jmildner.rmi.waehrungsrechner;

import java.rmi.Naming;

public class WaehClient
{
	public static void main(String[] args)
	{
		String ip = args[0];
		try
		{
			Waeh wr = (Waeh) Naming.lookup("rmi://" + ip + "/waeh");
			System.out.println("100 sfr=" + wr.gibEuro("sfr", 100));
			System.out.println("100 $  =" + wr.gibEuro("$", 100));
			System.out.println("100 yen=" + wr.gibEuro("yen", 100));
			System.out.println("100 £  =" + wr.gibEuro("£", 100));
			System.out.println("120 sfr=" + wr.gibEuro("sfr", 120));
			System.out.println("130 sfr=" + wr.gibEuro("sfr", 130));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
