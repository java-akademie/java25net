package ch.jmildner.rmi.myrmi;

import java.util.Enumeration;
import java.util.Vector;

public class KarteiKasten
{
	public static Vector<Datensatz> kkv;
	public static Enumeration<Datensatz> e;


	public static Datensatz getRecord(String theKey)
	{
		e = kkv.elements();
		while (e.hasMoreElements())
		{
			Datensatz ds = e.nextElement();
			if ((ds.nummer).equals(theKey))
			{
				return ds;
			}
		}
		return (null);
	}


	public static void main(String args[])
	{
		System.out.println("start main");
		KarteiKasten kk = new KarteiKasten();
		kk.kkAusgeben();
		System.out.println("name zu " + "8077" + "="
				+ kk.nameErmitteln("8077"));
		System.out.println("name zu " + "4711" + "="
				+ kk.nameErmitteln("4711"));
		System.out.println("nummer zu " + "hugo" + "="
				+ kk.nummerErmitteln("hugo"));
		System.out.println("nummer zu " + "hugox" + "="
				+ kk.nummerErmitteln("hugox"));
		kk.recordEinfuegen("meier", "24543", "eingefuegter satz ");
		kk.recordEinfuegen("meier", "24543", "eingefuegter satz ");
		kk.recordEinfuegen("meier", "24543", "eingefuegter satz ");
		kk.recordEinfuegen("meier", "24543", "eingefuegter satz ");
		kk.recordEinfuegen("meier", "24543", "eingefuegter satz ");
		kk.recordEinfuegen("meier", "24543", "eingefuegter satz ");
		kk.recordEinfuegen("meier", "24543", "eingefuegter satz ");
		kk.recordEinfuegen("meier", "24543", "eingefuegter satz ");
		kk.kkAusgeben();
	}


	public KarteiKasten()
	{
		kkv = new Vector<Datensatz>();
		kkv.addElement(new Datensatz("thomas", "0815", "112"));
		kkv.addElement(new Datensatz("jirka", "0816", "212"));
		kkv.addElement(new Datensatz("hans", "0817", "312"));
		kkv.addElement(new Datensatz("gerald", "0818", "412"));
		kkv.addElement(new Datensatz("bernhard", "0819", "512"));
		kkv.addElement(new Datensatz("ernst", "0820", "612"));
		kkv.addElement(new Datensatz("sigi", "4711", "712"));
		kkv.addElement(new Datensatz("eva", "4712", "812"));
	}


	public void kkAusgeben()
	{
		System.out.println("Datensaetze:");
		e = kkv.elements();
		while (e.hasMoreElements())
		{
			Datensatz ds = e.nextElement();
			System.out.println(ds.name + "/" + ds.nummer + "/"
					+ ds.data);
		}
	}


	public String nameErmitteln(String nummer)
	{
		e = kkv.elements();
		while (e.hasMoreElements())
		{
			Datensatz ds = e.nextElement();
			if ((ds.nummer).equals(nummer))
			{
				return ds.name;
			}
		}
		return ("name zu '" + nummer + "' nicht gefunden");
	}


	public String nummerErmitteln(String name)
	{
		e = kkv.elements();
		while (e.hasMoreElements())
		{
			Datensatz ds = e.nextElement();
			if ((ds.name).equals(name))
			{
				return ds.nummer;
			}
		}
		return ("nummer zu '" + name + "' nicht gefunden");
	}


	public String recordEinfuegen(String name, String nummer,
			String data)
	{
		kkv.addElement(new Datensatz(name, nummer, data));
		return ("eingefuegt: " + name + "/" + nummer + "/" + data);
	}
}
