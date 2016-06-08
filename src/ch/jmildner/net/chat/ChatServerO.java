package ch.jmildner.net.chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerO extends Frame implements ActionListener
{
	private static final long serialVersionUID = 1L;


	public static void main(String args[]) throws Exception
	{
		new ChatServerO();
	}
	TextArea ta = new TextArea(20, 75);
	Button start = new Button("Start");

	Button stopp = new Button("Stopp");
	static final int PORT = 8889;
	static String quit = "";
	static int i = 0;


	ServerSocket serverSocket;


	public ChatServerO()
	{
		setLocation(200, 200);
		makeThePanel();
		pack();
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == start)
		{
			start();
			return;
		}

		if (e.getSource() == stopp)
		{
			stopp();
			return;
		}
	}


	private void addTheListener()
	{
		start.addActionListener(this);
		stopp.addActionListener(this);
	}


	private void makeTheLayout()
	{
		setLayout(new BorderLayout());
		Panel ss = new Panel();
		ss.add(start);
		ss.add(stopp);
		add(ss, BorderLayout.NORTH);
		add(ta, BorderLayout.CENTER);
	}


	private void makeThePanel()
	{
		makeTheLayout();
		addTheListener();
	}


	private void start()
	{
		Thread th = new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					serverSocket = new ServerSocket(PORT);

					while (true)
					{
						ta.append("vor accept\n");
						Socket socket = serverSocket.accept();
						if ("quit".equals(quit))
						{
							new ChatHandler("quit", socket, ta).start();
							break;
						}
						DataInputStream in;
						in = new DataInputStream(
								socket.getInputStream());
						String name = in.readUTF() + "-" + ++i;
						String zw = "" + "new client '" + name
								+ "' from '" + socket.getInetAddress()
								+ "'" + "\n";
						ta.append(zw);
						new ChatHandler(name, socket, ta).start();
					}
					serverSocket.close();
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
		};
		th.setDaemon(true);
		th.start();
	}



	@SuppressWarnings("resource")
	private void stopp()
	{
		System.out.println("stopp");
		quit = "quit";
		try
		{
			new Socket("localhost", PORT);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				Thread.sleep(5000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.exit(1);
		}
	}
}
