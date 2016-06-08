package ch.jmildner.net.eswing;

import javax.swing.JFrame;

public class EClientFrame extends JFrame
{
	private static final long serialVersionUID = 1L;


	public EClientFrame()
	{
		super("EchoClient");
		add(new EClientPanel());

		setLocation(500, 200);
		pack();
		setVisible(true);
	}


	public static void main(String[] args)
	{
		new EClientFrame();
	}
}
