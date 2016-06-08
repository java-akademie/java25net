package ch.jmildner.rmi.myRmiDB;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyDbFrame extends Frame
{
	private static final long serialVersionUID = 1L;


	public static void main(String[] args)
	{
		new MyDbFrame("localhost");
	}


	public MyDbFrame(String ip)
	{
		try
		{
			add(new MyDbPanel(ip));
		}
		catch (Exception e)
		{
			System.out.println("addPanel fehlerhaft: " + e);
		}

		setSize(600, 400);
		setLocation(100, 300);

		pack();

		setVisible(true);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
}
