package ch.jmildner.rmi.waehrungsrechner;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WaehImpl extends UnicastRemoteObject implements Waeh
{
	private static final long serialVersionUID = 1L;


	public WaehImpl() throws RemoteException
	{
	}


	@Override
	public double gibEuro(String waeh, double betr)
	{
		if (waeh.equals("sfr"))
			return betr * 1.5;
		if (waeh.equals("yen"))
			return betr * 2.5;
		if (waeh.equals("$"))
			return betr * 3.5;
		if (waeh.equals("£"))
			return betr * 4.5;
		return betr;
	}
}
