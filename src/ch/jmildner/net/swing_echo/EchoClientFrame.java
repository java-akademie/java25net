package ch.jmildner.net.swing_echo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class EchoClientFrame extends JFrame
{
	private static final long serialVersionUID = 1L;


	public EchoClientFrame()
	{
		super("EchoClient");
		add(new EchoClientPanel((int)(Math.random()*1000)));

		setLocation(500, 200);
		pack();
		setVisible(true);

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
