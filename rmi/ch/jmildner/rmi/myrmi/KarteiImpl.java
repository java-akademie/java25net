package ch.jmildner.rmi.myrmi;

import java.awt.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KarteiImpl extends UnicastRemoteObject implements Kartei
{
	private static final long serialVersionUID = 1L;

	KarteiKasten kk;
	static int i = 0;


	public static void schreiben(String s)
	{
		try
		{
			FileWriter resultsFile = new FileWriter(
					"karteiProtokoll.txt", true);
			PrintWriter toFile = new PrintWriter(resultsFile);
			toFile.println(s);
			toFile.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	String serverStatus = "";


	TextField statusEnde;


	public KarteiImpl(String serverStatus, TextField statusEnde)
			throws RemoteException
	{
		System.out.println("Konstruktor: KarteiInplementation");
		this.serverStatus = serverStatus;
		if (serverStatus.equals("online"))
		{
			this.statusEnde = statusEnde;
			System.out.println("Online");
		}
		else
		// batch
		{
			System.out.println("Batch");
		}
		kk = new KarteiKasten();
	}


	@Override
	public Datensatz getRecord(String theKey)
	{
		Datensatz ds = KarteiKasten.getRecord(theKey);
		String text;
		if (ds == null)
		{
			text = "datensatz nicht gefunden";
		}
		else
		{
			text = "datensatz gefunden";
		}
		if (serverStatus.equals("online"))
		{
			statusEnde.setText(text);
		}
		System.out.println(text);
		return (ds);
	}


	@Override
	public String recordEintragen(String eineNummer, String einName,
			String einDatum)
	{
		String text = kk.recordEinfuegen(eineNummer, einName, einDatum);
		if (serverStatus.equals("online"))
		{
			statusEnde.setText(text);
		}
		kk.kkAusgeben();
		schreiben(text);
		System.out.println(text);
		return (text);
	}


	@Override
	public String sucheName(String eineNummer)
	{
		String text = kk.nameErmitteln(eineNummer);
		if (serverStatus.equals("online"))
		{
			statusEnde.setText(text);
		}
		System.out.println(text);
		return text;
	}


	@Override
	public String sucheNummer(String einName)
	{
		String text = kk.nummerErmitteln(einName);
		if (serverStatus.equals("online"))
		{
			statusEnde.setText(text);
		}
		System.out.println(text);
		return text;
	}
}
