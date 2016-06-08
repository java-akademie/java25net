package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ch.java_akademie.tools.NetTools;

public class RechnerServer
{
	static class EchoService extends Thread
	{
		Socket socket;


		EchoService(Socket s)
		{
			socket = s;
		}


		private String rechne(String buffer)
		{
			String w1, w2;
			char op;
			int ia = buffer.indexOf("+");
			int is = buffer.indexOf("-");
			int im = buffer.indexOf("*");
			int id = buffer.indexOf("/");
			int i = 0;
			i = Math.max(i, ia);
			i = Math.max(i, is);
			i = Math.max(i, im);
			i = Math.max(i, id);

			w1 = buffer.substring(0, i);
			w1 = w1.trim();
			w2 = buffer.substring(i + 1);
			w2 = w2.trim();
			op = buffer.charAt(i);

			double d1 = 0.0, d2 = 0.0;

			try
			{
				d1 = Double.parseDouble(w1);
				d2 = Double.parseDouble(w2);
			}
			catch (Exception e)
			{
				return "daten sind falsch: " + buffer;
			}

			double ergebnis = 0.0;

			switch (op)
			{
				case '+':
					ergebnis = d1 + d2;
					break;
				case '-':
					ergebnis = d1 - d2;
					break;
				case '*':
					ergebnis = d1 * d2;
					break;
				case '/':
					ergebnis = d1 / d2;
					break;
				default:
					return "daten sind falsch: " + buffer;
			}

			return "" + w1 + op + w2 + '=' + ergebnis;
		}


		@Override
		public void run()
		{
			try
			{
				PrintWriter pw = NetTools.getPrintWriter(socket);
				BufferedReader br = NetTools.getBufferedReader(socket);
				String buffer = br.readLine();
				pw.println(rechne(buffer));
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
		System.out.println("start RechnerServer");

		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(4711);

		while (true)
		{
			Socket socket = serverSocket.accept();
			new EchoService(socket).start();
		}
	}

}
