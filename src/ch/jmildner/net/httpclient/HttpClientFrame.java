package ch.jmildner.net.httpclient;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class HttpClientFrame extends JFrame
{
	private static final long serialVersionUID = 1L;


	public HttpClientFrame()
	{
		super("HttpClient");
		add(new HttpClientPanel());

		setLocation(500, 100);
		setVisible(true);
		pack();

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
	}
}
