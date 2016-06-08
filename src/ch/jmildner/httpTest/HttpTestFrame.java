package ch.jmildner.httpTest;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class HttpTestFrame extends JFrame
{
	private static final long serialVersionUID = 1L;


	public static void main(String[] args)
	{
		new HttpTestFrame();
	}


	public HttpTestFrame()
	{
		super("httpTest");
		Container cont = getContentPane();
		cont.add(new HttpTestPanel());
		setLocation(200, 100);
		pack();
		setVisible(true);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.out.println("ende");
				System.exit(0);
			}
		});
	}
}
