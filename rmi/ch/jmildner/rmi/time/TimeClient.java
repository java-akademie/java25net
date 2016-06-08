package ch.jmildner.rmi.time;

import java.rmi.Naming;
import java.util.Date;

public class TimeClient
{
	public static void main(String[] args)
	{
		String host = args[0];
		try
		{
			RemoteTime remoteTime = (RemoteTime) Naming.lookup("rmi://"
					+ host + ":1299/TimeServer");
			Date serverDate = remoteTime.getDate();
			System.out.println(".... Date from Server: " + serverDate);
		}
		catch (Exception e)
		{
			System.out.println("Fehler:" + e);
		}
	}
}
