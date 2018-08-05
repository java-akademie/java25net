package ch.jmildner.rmi.kontoRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Konto extends Remote
{
	void einzahlen(float menge) throws RemoteException;


	float getKontostand() throws RemoteException;


	String getName() throws RemoteException;


	String getRecord() throws RemoteException;
}
