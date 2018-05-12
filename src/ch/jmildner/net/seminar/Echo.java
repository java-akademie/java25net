package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ch.jmb.tools.NetTools;
import ch.jmildner.tools.MyTools;

public class Echo
{
	public static void main(String[] args)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				System.out.println("startServer");
				try
				{
					startServer();
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}.start();

		MyTools.sleep(500);
		MyTools.pause("***");

		new Thread()
		{
			@Override
			public void run()
			{
				System.out.println("startClient");
				try
				{
					startClient();
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}.start();
	}


	public static void startClient() throws Exception
	{

		Socket socket = new Socket("localhost", 7010);

		BufferedReader br = NetTools.getBufferedReader(socket);
		PrintWriter pw = NetTools.getPrintWriter(socket);

		pw.println("anfrage");
		pw.flush();
		String buff = br.readLine();

		System.out.println("..." + buff);

		br.close();
		pw.close();
		socket.close();
	}


	static void startServer() throws Exception
	{
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(7010);

		Socket socket = serverSocket.accept();

		BufferedReader br = NetTools.getBufferedReader(socket);
		PrintWriter pw = NetTools.getPrintWriter(socket);

		String line = br.readLine();
		pw.println(line + "/" + line);
		pw.flush();

		br.close();
		pw.close();
		socket.close();
	}
}
