package ch.jmildner.rmi.time;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RemoteTime extends Remote
{
	public Date getDate() throws RemoteException;
}
