package ch.jmildner.net.swing_echo;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import ch.java_akademie.tools.NetTools;

public class EchoService extends Thread
{
	Socket socket;
	EchoServerPanel panel;


	EchoService(Socket socket, EchoServerPanel panel)
	{
		this.socket = socket;
		this.panel = panel;
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
				panel.getTa().append(buffer + " ... empfangen \n");
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
