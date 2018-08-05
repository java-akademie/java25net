package ch.jmildner.rmi.time;

import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class TimeServer extends UnicastRemoteObject implements
		RemoteTime
{
	private static final long serialVersionUID = 1L;

	static int i = 0;


	public static void main(String[] args)
	{
		String urlServer = "rmi://localhost:1299/TimeServer";
		try
		{
			TimeServer timeServer = new TimeServer();
			try
			{
				Naming.rebind(urlServer, timeServer);
				System.out.println("1.Versuch: rebind");
			}
			catch (ConnectException e)
			{
				LocateRegistry.createRegistry(1299);
				Naming.rebind(urlServer, timeServer);
				System.out.println("2.Versuch: createRegistry/rebind");
			}
			System.out
					.println("\nTimeServer wurde gestartet und ist bereit ...");
		}
		catch (Exception e)
		{
			System.out
					.println("TimeServer konnte leider nicht gestartet werden");
			e.printStackTrace();
		}
	}


	public TimeServer() throws RemoteException
	{
		super();
	}


	@Override
	public Date getDate() throws RemoteException
	{
		System.out.println("getDate wurde aufgerufen" + ++i);
		return new Date();
	}
}
