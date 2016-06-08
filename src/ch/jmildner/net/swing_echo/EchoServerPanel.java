package ch.jmildner.net.swing_echo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;

import ch.java_akademie.tools.MyPanel;

public class EchoServerPanel extends MyPanel
{
	class EchoServer extends Thread
	{
		boolean ende;
		final int PORT = 5555;


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
					if (ende)
						break;

					try
					{
						Socket socket = serverSocket.accept();
						new EchoService(socket, instance).start();
					}
					catch (Exception e)
					{
						// ta.append("timeout\n");
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
	private EchoServerPanel instance;
	private static final long serialVersionUID = 1L;
	private JButton start = new JButton("START");
	private JButton stopp = new JButton("STOPP");
	private JButton clear = new JButton("CLEAR");


	private JTextArea ta = new JTextArea("", 20, 20);


	EchoServer es;


	public EchoServerPanel()
	{
		super(4, 10, true);
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
				start();
			}
		});
		stopp.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				stopp();

			}
		});
		clear.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				clear();

			}
		});
	}


	private void clear()
	{
		ta.setText("");
	}


	public JTextArea getTa()
	{
		return ta;
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


	private void start()
	{
		es = new EchoServer();
		es.start();
	}


	private void stopp()
	{
		System.exit(0);
	}
}
