package ch.jmildner.telematik.dbServer;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class DbClientFrame extends Frame
{
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) throws Exception
	{
		new DbClientFrame("DbFrame");
	}


	public DbClientFrame(String titel) throws Exception
	{
		super(titel);
		final DbClientPanel p = new DbClientPanel();
		add(p);
		setLocation(100, 200);
		pack();
		setVisible(true);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				try
				{
					p.dbZugriff(0);
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
				finally
				{
					System.exit(0);
				}
			}
		});
	}
}
