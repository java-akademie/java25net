package ch.jmb.miniwebservice;

import javax.xml.ws.Endpoint;

public class StartHalloWeltService
{
	public static void main(String[] args)
	{
		String url = ("http://localhost:4434/miniwebservice");
		Endpoint.publish(url, new HalloWeltImpl());
	}
}
