package ch.jmildner.rmi.myRmiDB;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyDb extends Remote
{
	String dbClose() throws RemoteException;


	String dbMetadaten() throws RemoteException;


	String dbOpen(String dbase) throws RemoteException;


	String kCreate() throws RemoteException;


	String kDelete() throws RemoteException;


	String kDrop() throws RemoteException;


	String kInsert() throws RemoteException;


	String kInsert(String na, int nr) throws RemoteException;


	String kSelect() throws RemoteException;
}
