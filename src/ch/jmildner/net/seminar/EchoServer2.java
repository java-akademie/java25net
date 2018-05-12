package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ch.jmb.tools.NetTools;
import ch.jmildner.tools.MyTools;

public class EchoServer2
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("start EchoServer2");

		class MyThread extends Thread
		{
			boolean ende = false;
			ServerSocket serverSocket;


			MyThread()
			{
				try
				{
					serverSocket = new ServerSocket(7);
					serverSocket.setSoTimeout(1000);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

			}


			@Override
			public void run()
			{

				while (true)
				{
					try
					{
						Socket socket = serverSocket.accept();
						new EchoService(socket).start();
					}
					catch (Exception e)
					{
						System.out.println("timeout...");
					}
					if (ende)
						break;
				}
			}


			void setEnde(boolean ende)
			{
				this.ende = ende;
			}
		}
		;

		MyThread th = new MyThread();
		th.start();
		MyTools.pause();
		th.setEnde(true);
	}
}



class EchoService extends Thread
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
