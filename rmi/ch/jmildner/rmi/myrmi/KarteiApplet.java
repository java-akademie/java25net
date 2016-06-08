package ch.jmildner.rmi.myrmi;

import java.applet.Applet;

public class KarteiApplet extends Applet
{
	private static final long serialVersionUID = 1L;


	@Override
	public void init()
	{
		String url = getCodeBase().getHost();
		System.out.println("url=" + url + "/");
		if (url.equals(""))
		{
			url = "localhost";
		}
		String server = "rmi://" + url + ":11299/Kartei";
		System.out.println("Konstruktor: KarteiApplet neu");
		add(new KarteiPanel(server).getKarteiPanel());
	}
}
