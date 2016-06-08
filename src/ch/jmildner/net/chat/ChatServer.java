package ch.jmildner.net.chat;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer
{

	public static void main(String args[]) throws Exception
	{
		System.out.println("Start Chat Server - Port 8889");

		int PORT = 8889;
		int i = 0;

		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT);

		while (true)
		{
			Socket socket = serverSocket.accept();
			DataInputStream in;
			in = new DataInputStream(socket.getInputStream());
			String name = in.readUTF() + "-" + ++i;
			System.out.println("" + "new client '" + name + "' from '"
					+ socket.getInetAddress() + "'");

			new ChatHandler(name, socket, null).start();
		}
	}
}
