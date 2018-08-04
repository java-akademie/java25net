package ch.jmildner.net.eswing;

import javax.swing.JFrame;

public class EServerFrame extends JFrame
{
	private static final long serialVersionUID = 1L;


	public EServerFrame()
	{
		super("EchoServer");

		add(new EServerPanel());

		setLocation(100, 200);
		pack();
		setVisible(true);

	}


	public static void main(String[] args)
	{
		new EServerFrame();
	}
}
