package ch.jmildner.sockets.verschiedenes;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DomainNameServerAnfrage // Anfrage an Domain Name Server
{
	public static void main(String args[])
	{
		String hostname;
		if (args.length != 1)
		{
			System.out.println("parameter fehlt");
			hostname = "www.java-akademie.ch";
			System.out.println("<" + hostname + "> angenommen");
		}
		else
		{
			hostname = args[0];
		}
		try
		{
			InetAddress ip;
			ip = InetAddress.getByName(hostname);
			System.out.println("ip=" + ip);
		}
		catch (UnknownHostException ex)
		{
			System.out.println(ex);
		}
	}
}
