package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ch.java_akademie.tools.NetTools;

public class EchoServer
{
	static class EchoService extends Thread
	{
		Socket socket;


		EchoService(Socket s)
		{
			socket = s;
		}


		@Override
		public void run()
		{
			System.out.println("EchoService gestartet ...");
			try
			{
				PrintWriter pw = NetTools.getPrintWriter(socket);
				BufferedReader br = NetTools.getBufferedReader(socket);
				String buffer = "";
				while (!(buffer = br.readLine()).equals("$$$"))
				{
					pw.println("..." + buffer);
					pw.flush();
				}

				pw.close();
				socket.close();
				System.out.println("EchoService beendet");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) throws Exception
	{
		System.out.println("start EchoServer");

		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(7000);

		while (true)
		{
			Socket socket = serverSocket.accept();
			new EchoService(socket).start();
		}
	}
}
