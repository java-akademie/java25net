package ch.jmildner.rmi.myrmi;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KarteiClient
{
	static String server = null;


	public static void main(String args[])
	{
		if (args.length < 1)
		{
			System.out.println("aufruf: java KarteiClient url name");
			System.out
					.println("  z.B.: java KarteiClient 127.0.0.1 0815");
			server = "//localhost:1299/Kartei";
		}
		else
		{
			server = "//" + args[0] + "/Kartei";
		}
		new KarteiClient("KarteiClient");
	}


	KarteiClient(String xxx)
	{
		Frame frame = new Frame(xxx);
		frame.add(new KarteiPanel(server).getKarteiPanel());
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.out.println("ereignis eingetreten:\n" + e);
				System.exit(0);
			}
		});
	}
}
