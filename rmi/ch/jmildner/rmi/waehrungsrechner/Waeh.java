package ch.jmildner.rmi.waehrungsrechner;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Waeh extends Remote
{
	double gibEuro(String waeh, double betr) throws RemoteException;
}
