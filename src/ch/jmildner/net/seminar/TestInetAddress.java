package ch.jmildner.net.seminar;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress
{
	public static void main(String[] args) throws UnknownHostException
	{
		out("localhost");
		out("www.jmildner.ch");
		out("www.csbe.ch");
		out("www.mcdonalds.com");
	}



	private static void out(String url) throws UnknownHostException
	{
		System.out.println("----------------");
		InetAddress addr;
		addr = InetAddress.getByName(url);
		System.out.println(addr);
		System.out.println(addr.getHostAddress());
		System.out.println(addr.getHostName());
		System.out.println(addr.toString());
	}


}
