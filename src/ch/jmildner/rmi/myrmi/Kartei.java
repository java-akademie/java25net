package ch.jmildner.rmi.myrmi;

public interface Kartei extends java.rmi.Remote
{
	Datensatz getRecord(String theKey) throws java.rmi.RemoteException;


	String recordEintragen(String eineNummer, String einName,
			String einDatum) throws java.rmi.RemoteException;


	String sucheName(String eineNummer) throws java.rmi.RemoteException;


	String sucheNummer(String einName) throws java.rmi.RemoteException;
}
