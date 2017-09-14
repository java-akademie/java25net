package ch.jmildner.sockets.verschiedenes;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import ch.jmb.tools.MyTools;
import ch.jmb.tools.MyTools2;

class PortScan extends Thread
{
	static final int MIN = 1;
	static final int MAX = 1000;

	static boolean[] frei = new boolean[MAX + 1];


	public static void main(String[] args)
	{
		for (int i = MIN; i <= MAX; i++)
		{
			PortScan ps = new PortScan(i);
			ps.start();
		}

		MyTools.pause();

		for (int i = MIN; i <= MAX; i++)
		{
			if (frei[i] == false)
			{
				System.out
						.println("port " + i + " ist noch nicht frei");
			}
		}
		System.out.println("ende");
	}
	int port;

	Socket socket;
	BufferedReader br;
	PrintWriter pw;


	String buff;


	PortScan(int port)
	{
		this.port = port;
	}


	@Override
	public void run()
	{
		try
		{
			// socket = MyTools2.getSocket("192.168.1.101", port);
			socket = MyTools2.getSocket("127.0.0.1", port);
			br = MyTools2.getBufferedReader(socket);
			pw = MyTools2.getPrintWriter(socket);

			MyTools2.writeLine(pw, "HEAD / HTTP/1.1\n");
			MyTools2.writeLine(pw, "Host: localhost:" + port + "\n");
			MyTools2.flush(pw);

			do
			{
				buff = MyTools2.readLine(br);

				System.out.println("Port: " + port + " " + buff);

				if (buff.equals("IOException bei readLine()"))
				{
					buff = null;
				}
			}
			while (buff != null);
		}
		catch (Exception e)
		{
		}

		frei[port] = true;
	}
}
