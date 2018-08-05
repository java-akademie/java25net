package ch.java2.rmi.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KontoImpl extends UnicastRemoteObject implements Konto
{
	private static final long serialVersionUID = 1L;

	private double kontostand = 0;
	private String name = "";


	public KontoImpl(String name) throws RemoteException
	{
		this.name = name;
	}


	@Override
	public double getKontostand() throws RemoteException
	{
		return kontostand;
	}


	@Override
	public String getName() throws RemoteException
	{
		return name;
	}


	@Override
	public void verbuchen(double betrag) throws RemoteException
	{
		kontostand = kontostand + betrag;
	}
}
