package ch.jmildner.telematik.socket.simple;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ch.java_akademie.tools.MyTools;

public class SimpleSocket
{
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Start TestSocket");

		Thread t1 = startServer();
		Thread t2 = startClient(5, 100);

		// t1.setDaemon(true);
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("\nProgrammende! ");
	}


	static Thread startClient(final int durchlaeufe, final int dauer)
	{
		return new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					new SocketClient(durchlaeufe, dauer);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};
	}


	static Thread startServer()
	{
		return new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					new SocketServer();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};
	}

}



class SocketClient
{
	Socket socket;
	BufferedReader bufferedReader;
	PrintWriter printWriter;


	SocketClient(int maxDurchlaeufe, int sleepDauer) throws Exception
	{
		socket = new Socket("localhost", 4711);

		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		bufferedReader = new BufferedReader(isr);

		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		printWriter = new PrintWriter(osw);

		for (int i = 1; i <= maxDurchlaeufe; i++)
		{
			MyTools.sleep(sleepDauer);

			long m = MyTools.getRandomPrime(1, 1000000000);

			printWriter.println("" + m);
			printWriter.flush();

			String buff = bufferedReader.readLine();

			System.out.println("der Server antwortete: " + buff + "\n");
		}

		printWriter.println("ende");
		printWriter.flush();
	}
}



class SocketServer
{
	ServerSocket serverSocket;
	Socket socket;

	BufferedReader bufferedReader;
	PrintWriter printWriter;


	SocketServer() throws Exception
	{
		serverSocket = new ServerSocket(4711);
		socket = serverSocket.accept();

		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		bufferedReader = new BufferedReader(isr);

		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		printWriter = new PrintWriter(osw);

		System.out.println("socket-Server wartet auf client ...\n");

		while (true)
		{
			String buff = bufferedReader.readLine();
			System.out.println("der Client sagte     : " + buff);
			printWriter.println(buff + "...");
			printWriter.flush();
			if (buff.equals("ende"))
				break;
		}
	}
}
