package ch.java2.rmi.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Konto extends Remote
{
	public double getKontostand() throws RemoteException;


	public String getName() throws RemoteException;


	public void verbuchen(double betrag) throws RemoteException;
}
