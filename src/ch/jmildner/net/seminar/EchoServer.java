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
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			System.out.println("EchoService beendet");
		}
	}


	public static void main(String[] args) throws Exception
	{
		System.out.println("start EchoServer");

		ServerSocket serverSocket = new ServerSocket(7000);

		while (true)
		{
			Socket socket = serverSocket.accept();
			
			if (socket.getPort() == 64000)
			{
				break;
			}
			
			new EchoService(socket).start();
		}

		serverSocket.close();

		System.out.println("stopp EchoServer");
	}
}
