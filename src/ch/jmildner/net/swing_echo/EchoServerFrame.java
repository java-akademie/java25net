package ch.jmildner.net.swing_echo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class EchoServerFrame extends JFrame
{
	private static final long serialVersionUID = 1L;


	public EchoServerFrame()
	{
		super("EchoServer");
		add(new EchoServerPanel());

		setLocation(100, 200);
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
