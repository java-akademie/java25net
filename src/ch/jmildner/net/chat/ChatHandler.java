package ch.jmildner.net.chat;

import java.awt.TextArea;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class ChatHandler extends Thread
{
	static int zaehler = 0;


	protected static void broadcast(String message)
	{
		synchronized (handlers)
		{
			System.out.println(message);
			if (ta != null)
				ta.append(message + "\n");

			Enumeration<ChatHandler> e = handlers.elements();

			while (e.hasMoreElements())
			{
				ChatHandler handler = e.nextElement();
				try
				{
					handler.out.writeUTF(message);
					handler.out.flush();
				}
				catch (IOException ex)
				{
				}
			}
		}
	}


	protected static void whoIsThere(ChatHandler h)
	{
		synchronized (handlers)
		{
			Enumeration<ChatHandler> e = handlers.elements();

			if (e.hasMoreElements())
			{
				while (e.hasMoreElements())
				{
					ChatHandler handler = e.nextElement();
					try
					{
						h.out.writeUTF(handler.name
								+ " is in Chat-Room");
						h.out.flush();
					}
					catch (IOException ex)
					{
					}
				}
			}
			else
			{
				try
				{
					h.out.writeUTF("der Chat-Room ist leer");
					h.out.flush();
				}
				catch (IOException ex)
				{
				}
			}
		}
	}
	Socket socket = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	String name = null;


	static TextArea ta;


	protected static Vector<ChatHandler> handlers = new Vector<ChatHandler>();


	public ChatHandler(String n, Socket s, TextArea ta)
			throws IOException
	{
		this.name = n;
		this.socket = s;
		in = new DataInputStream(new BufferedInputStream(
				socket.getInputStream()));
		out = new DataOutputStream(new BufferedOutputStream(
				socket.getOutputStream()));
		ChatHandler.ta = ta;
	}


	@Override
	public void run()
	{
		try
		{
			whoIsThere(this);
			handlers.addElement(this);
			if (name.equals("quit"))
				broadcast("Server wird beendet ");
			else
				broadcast(name + " entered ");
			while (true)
			{
				String message = in.readUTF();
				zaehler++;
				broadcast(name + ": " + zaehler + ". " + message);
			}
		}
		catch (Exception e)
		{
			System.out
					.println("Connection to user '" + name + "' lost");
		}
		finally
		{
			handlers.removeElement(this);

			broadcast("user '" + name + "' left");

			try
			{
				socket.close();
			}
			catch (Exception e)
			{
				System.out.println("--Socket to user already closed ?");
				e.printStackTrace();
			}
		}
	}
}
