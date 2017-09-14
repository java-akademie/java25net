package ch.jmildner.net.eswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;

import ch.jmb.tools.MyPanel;

public class EServerPanel extends MyPanel
{
	private static final long serialVersionUID = 1L;

	private JButton start = new JButton("START");
	private JButton stopp = new JButton("STOPP");
	private JButton clear = new JButton("CLEAR");

	private JTextArea ta = new JTextArea("", 20, 20);


	public JTextArea getTa()
	{
		return ta;
	}


	public void setTa(JTextArea ta)
	{
		this.ta = ta;
	}



	EServerPanel instance;

	EServer es;


	public EServerPanel()
	{
		super(4, 10, false);

		makeTheLayout();
		addTheListener();

		instance = this;
	}


	private void addTheListener()
	{
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				start.setEnabled(false);
				es = new EServer();
				es.start();
			}
		});
		stopp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		clear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ta.setText("");

			}
		});
	}



	private void makeTheLayout()
	{
		addCaptionCenter("ECHO_SERVER", 10, 1, 3);

		add(start, 20, 1, 1);
		add(stopp, 20, 2, 1);
		add(clear, 20, 3, 1);

		ta.setEditable(false);

		addTextArea(ta, 50, 1, 4, 1);
	}



	class EServer extends Thread
	{
		private final int PORT = 5555;


		@Override
		public void run()
		{
			ta.append("Server gestartet ... \n");

			try
			{
				@SuppressWarnings("resource")
				ServerSocket serverSocket = new ServerSocket(PORT);
				serverSocket.setSoTimeout(1000);
				while (true)
				{
					try
					{
						Socket socket = serverSocket.accept();
						new EService(socket, instance).start();
					}
					catch (Exception e)
					{
					}
				}
			}
			catch (Exception e)
			{
				ta.append("" + PORT + e + "\n");
			}

			ta.append("Server gestoppt ... \n");
		}
	}
}
