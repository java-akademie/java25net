package ch.jmildner.rmi.kontoRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KontoImpl extends UnicastRemoteObject implements Konto
{
	private static final long serialVersionUID = 1L;

	private float mBalance = 0;
	private String mName = "";


	public KontoImpl(String name) throws RemoteException
	{
		mName = name;
	}


	@Override
	public void einzahlen(float menge) throws RemoteException
	{
		mBalance = mBalance + menge;
		System.out.println(this.getRecord());
	}


	@Override
	public float getKontostand() throws RemoteException
	{
		return mBalance;
	}


	@Override
	public String getName()
	{
		return mName;
	}


	@Override
	public String getRecord() throws RemoteException
	{
		return mName + "  " + mBalance;
	}
}
