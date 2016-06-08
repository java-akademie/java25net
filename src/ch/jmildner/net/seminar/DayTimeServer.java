package ch.jmildner.net.seminar;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import ch.java_akademie.tools.NetTools;

public class DayTimeServer
{
	static class DayTimeService extends Thread
	{
		Socket socket;


		DayTimeService(Socket s)
		{
			System.out.println("DayTimeService gestartet");
			socket = s;
		}


		@Override
		public void run()
		{
			PrintWriter pw;

			try
			{
				pw = NetTools.getPrintWriter(socket);

				pw.println(new Date().toString());
				pw.flush();
				pw.close();
				socket.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) throws Exception
	{
		System.out.println("start DayTimeServer");

		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(13);

		while (true)
		{
			Socket socket = serverSocket.accept();
			new DayTimeService(socket).start();
		}
	}
}
