package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import ch.jmb.tools.NetTools;
import ch.jmildner.tools.MyTools;

public class RechnerClient
{

	public static void main(String[] args) throws Exception
	{
		final String prompt = "bitte eingeben 'w1 op w2' oder ende > ";

		String buff = MyTools.getString(prompt);

		while (!buff.equals("ende"))
		{
			Socket socket = new Socket("localhost", 4711);

			BufferedReader br = NetTools.getBufferedReader(socket);
			PrintWriter pw = NetTools.getPrintWriter(socket);

			pw.println(buff);
			pw.flush();
			buff = br.readLine();
			System.out.println(buff);

			br.close();
			pw.close();
			socket.close();
			buff = MyTools.getString(prompt);
		}
	}
}
