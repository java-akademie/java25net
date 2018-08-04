package ch.jmildner.net.chatneu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ch.jmildner.tools.MyPanel;

public class ChatClient extends JFrame
{
	class MyKeyListener extends KeyAdapter
	{
		ChatClient client;


		MyKeyListener(ChatClient client)
		{
			this.client = client;
		}


		@Override
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				String text = input.getText();
				text = text.trim();

				if (text.length() > 0)
				{
					sendTextToChat(text);
					input.setText("");
				}
			}
		}
	}

	class MyWindowListener extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}

	// static String ipaddr="10.0.4.98";
	static String ipaddr = "localhost";
	private static final long serialVersionUID = 1L;


	public static void main(String[] args)
	{
		String name;
		String url;
		int port;

		if (args.length != 3)
		{
			name = "noName";
			url = ipaddr;
			port = 8889;
		}
		else
		{
			name = args[0];
			url = args[1];
			port = Integer.parseInt(args[2]);
		}
		new ChatClient(name, url, port);
	}

	String name;
	String url;
	int port;

	Socket socket;
	DataInputStream in;


	DataOutputStream out;

	JTextArea output = new JTextArea(20, 50);

	JTextField input = new JTextField(50);


	public ChatClient(String name, String url, int port)
	{
		super(name + "'s Chat");
		this.name = name;
		this.url = url;
		this.port = port;

		MyPanel mp = new MyPanel();
		mp.addTextArea(output, 10, 1, 1, 1);
		mp.add(input, 20, 1, 1);
		add(mp);
		pack();
		setVisible(true);
		output.setEditable(false);
		input.requestFocus();

		input.addKeyListener(new MyKeyListener(this));
		addWindowListener(new MyWindowListener());

		try
		{
			socket = new Socket(url, port);
			System.out.println(socket.getLocalPort());
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			out.writeUTF(name);

			while (true)
			{
				output.setText(in.readUTF() + "\n" + output.getText());
			}
		}
		catch (Exception e)
		{
			System.out.println("Server nicht (mehr) verfuegbar \n" + e);
		}
	}


	private void sendTextToChat(String text)
	{
		try
		{
			out.writeUTF(text);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
