package ch.jmildner.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class EchoServer2
{
	public static void main(String[] args)
	{
		System.out.println("\n\n... EchoServer gestartet \n\n");
		new EchoServer2();
	}
	int port = 8099, xmaxConnections = 0;


	String serverName = "EchoServer";


	public EchoServer2()
	{
		listen();
	}


	private int contentLength(String[] inputLines)
	{
		String input;
		for (int i = 0; i < inputLines.length; i++)
		{
			input = inputLines[i].toUpperCase();
			if (input.startsWith("CONTENT-LENGTH"))
			{
				return getLength(input);
			}
		}
		return 0;
	}


	private int getLength(String input)
	{
		StringTokenizer tok = new StringTokenizer(input);
		tok.nextToken();
		return Integer.parseInt(tok.nextToken());
	}


	private void handleConnection(Socket server) throws IOException
	{
		// System.out.println("Echo Server "
		// + ": got connection from "
		// + server.getInetAddress().getHostName());

		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		PrintWriter out = new PrintWriter(server.getOutputStream(),
				true);

		String[] inputLines = new String[50];

		int i;

		for (i = 0;; i++)
		{
			inputLines[i] = in.readLine();
			// out.println(inputLines[i]);
			System.out.println("i=" + i + " " + inputLines[i]);

			if (inputLines[i] == null) // client hat verbindung
			// geschlossen
			{
				out.println("verbindung geschlossen");
				break;
			}

			if (inputLines[i].length() == 0) // leere zeile
			{
				break;
			}
		}

		// was wenn post
		if (inputLines[0].toUpperCase().startsWith("POST"))
		{
			readPostData(inputLines, i, in);
			i += 2;
		}

		printHeader(out);

		for (int j = 0; j < i; j++)
		{
			out.println(inputLines[j]);
		}

		printTrailer(out);

		server.close();
	}


	public void listen()
	{
		try
		{	
			@SuppressWarnings("resource")
			ServerSocket listener = new ServerSocket(port);
			Socket server;
			while (true)
			{
				server = listener.accept();
				handleConnection(server);
			}
		}
		catch (IOException e)
		{
			System.out.println("IOException: " + e);
			e.printStackTrace();
		}
	}


	private void printHeader(PrintWriter out)
	{
		String CR = "\n";

		out.println("HTTP/1.0 200 OK ");
		out.println("Server: " + serverName + " ");
		out.println("Content-Type: text/html" + CR);
		out.println("<!DOCTYPE HTML PUBLIC"
				+ " \"-//W3C//DTD HTML 4.0 Transitional//EN\">" + CR);
		out.println("<html>" + CR);
		out.println("<head>" + CR);
		out.println("<title>results</title>" + CR);
		out.println("</head>" + CR);
		out.println("<body bgcolor=yellow>" + CR);
		out.println("<h1>Results</h1>" + CR);
		out.println("<pre>" + CR);
	}


	private void printTrailer(PrintWriter out)
	{
		String CR = "\n";
		out.println("</pre>" + CR);
		out.println("</body>" + CR);
		out.println("</html>" + CR);
	}


	private void readPostData(String[] inputLines, int i,
			BufferedReader in) throws IOException
	{
		int contentLength = contentLength(inputLines);
		char[] postData = new char[contentLength];
		in.read(postData, 0, contentLength);
		inputLines[++i] = new String(postData, 0, contentLength);
	}

}
