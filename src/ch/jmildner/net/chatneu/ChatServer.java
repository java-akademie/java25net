package ch.jmildner.net.chatneu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatServer
{
	static class ChatHandler extends Thread
	{
		int zaehler = 0;
		Socket socket = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		String name = null;
		List<ChatHandler> handlers = new ArrayList<ChatHandler>();


		ChatHandler(Socket socket) throws IOException
		{
			System.out.println(socket.getPort());
			this.socket = socket;
			in = new DataInputStream(new BufferedInputStream(
					socket.getInputStream()));
			out = new DataOutputStream(new BufferedOutputStream(
					socket.getOutputStream()));

			this.name = in.readUTF() + "-" + ++i;
			System.out.println("" + "new client '" + name + "' from '"
					+ socket.getInetAddress() + "'");
		}


		void broadcast(String message)
		{
			synchronized (handlers)
			{
				System.out.println(message);

				Iterator<ChatHandler> e = handlers.iterator();

				while (e.hasNext())
				{
					ChatHandler handler = e.next();
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


		@Override
		public void run()
		{
			try
			{
				whoIsThere(this);
				handlers.add(this);
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
				System.out.println("Connection to user '" + name
						+ "' lost");
			}
			finally
			{
				handlers.remove(this);

				broadcast("user '" + name + "' left");

				try
				{
					socket.close();
				}
				catch (Exception e)
				{
					System.out
							.println("--Socket to user already closed ?");
					e.printStackTrace();
				}
			}
		}


		void whoIsThere(ChatHandler h)
		{
			synchronized (handlers)
			{
				if (handlers.size() > 0)
				{
					Iterator<ChatHandler> e = handlers.iterator();

					while (e.hasNext())
					{
						ChatHandler handler = e.next();
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
	}


	static int i;


	public static void main(String args[]) throws Exception
	{
		System.out.println("Start Chat Server Neu - Port 8889");

		int PORT = 8889;

		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT);

		while (true)
		{
			Socket socket = serverSocket.accept();
			new ChatHandler(socket).start();
		}
	}
}
