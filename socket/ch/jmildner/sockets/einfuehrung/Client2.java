package ch.jmildner.sockets.einfuehrung;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import ch.java_akademie.tools.MyTools;


public class Client2
{
	public static void main(String[] args) throws Exception
	{
		while (true)
		{
			MyTools.sleep(10);
			Socket socket = new Socket("localhost", 4711);

			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);

			System.out
					.println("Client: BufferedReader und PrintWriter erstellt ...");

			pw.println("hallo");
			pw.flush();

			String info = br.readLine();

			while (info.length() > 0)
			{
				System.out.println("Client: gelesen ... " + info);
				info = br.readLine();
			}
			socket.close();
			System.out.println("Client beendet");
		}
	}

}
