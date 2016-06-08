package ch.jmildner.net.chat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient
{
	public class EnterListener extends KeyAdapter
	{
		ChatClient client = null;
		ChatClientFrame gui = null;


		public EnterListener(ChatClient client, ChatClientFrame gui)
		{
			this.client = client;
			this.gui = gui;
		}


		@Override
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				String text = gui.input.getText();
				if (text.length() > 0)
				{
					client.sendTextToChat(text);
					gui.input.setText("");
				}
			}
		}
	}

	public class ExitListener extends WindowAdapter
	{
		ChatClient client = null;


		public ExitListener(ChatClient client)
		{
			this.client = client;
		}


		@Override
		public void windowClosing(WindowEvent e)
		{
			// client.disconnect();
			System.exit(0);
		}
	}


	public static void main(String args[])
	{
		String name = null;
		String url = null;

		int port = 0;

		if (args.length != 3)
		{
			name = "noName";
			url = "localhost";
			port = 8889;
			// System.out.println("Fehler - user/localhost/8888");
		}
		else
		{
			name = args[0];
			url = args[1];
			port = Integer.parseInt(args[2]);
		}

		// System.out.println("Chat-Client gestartet");
		// System.out.println(" name:" + name + " url:" + url + " port:"
		// + port);

		new ChatClient(name, url, port);

		// System.out.println("Chat-Client gestoppt");
		System.exit(0);
	}
	public ChatClientFrame gui = null;

	private Socket socket = null;

	private DataInputStream in = null;


	private DataOutputStream out = null;


	public ChatClient(String name, String server, int port)
	{
		gui = new ChatClientFrame(name + "'s chat");

		gui.input.addKeyListener(new EnterListener(this, gui));
		gui.addWindowListener(new ExitListener(this));

		try
		{
			socket = new Socket(server, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(name);
			while (true)
			{
				gui.output.setText(in.readUTF() + "\n"
						+ gui.output.getText());
			}
		}
		catch (Exception e)
		{
			System.out.println("Server nicht (mehr) verfuegbar" + e);
		}
	}


	protected void disconnect()
	{
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	protected void sendTextToChat(String str)
	{
		try
		{
			out.writeUTF(str);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
