package ch.jmildner.telematik.dbServer;

import ch.jmildner.tools.MyTools;
import ch.jmildner.tools.MyTools2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class DbClientBatch
{
	static final int PORT = 8088;


	public static void main(String[] args) throws Exception
	{
		System.out.println("start des dbClients");
		new DbClientBatch();
		System.out.println("stopp des dbClients");
	}
	Socket socket;
	BufferedReader br;
	PrintWriter pw;


	String buff;


	public DbClientBatch() throws UnknownHostException, IOException
	{
		socket = MyTools2.getSocket("localhost", PORT);
		br = MyTools2.getBufferedReader(socket);
		pw = MyTools2.getPrintWriter(socket);
		int id;
		do
		{
			id = MyTools.getInteger("bitte id eingeben > ");
			MyTools2.writeLine(pw, id + "");
			MyTools2.flush(pw);
			buff = MyTools2.readLine(br);
			System.out.println(buff);
		}
		while (id > 0);
	}
}
