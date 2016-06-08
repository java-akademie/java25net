package ch.jmb.miniwebservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class StartHalloWeltClient
{
	public static void main(String[] args) throws MalformedURLException
	{
		URL url = new URL("http://localhost:4434/miniwebservice?wsdl");

		QName qname = new QName("http://miniwebservice/",
				"HalloWeltImplService");

		Service service = Service.create(url, qname);

		HalloWelt halloWelt = service.getPort(HalloWelt.class);

		String hallo = halloWelt.hallo("hugo");

		System.out.println(hallo);
	}
}
