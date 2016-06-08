package ch.jmildner.sockets.einfuehrung;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import ch.java_akademie.tools.MyTools;
import ch.java_akademie.tools.NetTools;


public class Client
{
	public static void main(String[] args) 
	{
		try{
		MyTools.sleep(10);
		Socket socket = new Socket("localhost", 4711);

		BufferedReader br = NetTools.getBufferedReader(socket);
		PrintWriter pw = NetTools.getPrintWriter(socket);

		System.out.println("BufferedReader und PrintWriter erstellt");

		pw.println("hallo");
		pw.flush();

		String info = br.readLine();

		System.out.println("gelesen ... " + info);

		System.out.println("beendet");}
		catch(Exception e){
			System.out.println("client exception");
		}
	}

}
