package ch.jmildner.rmi.myrmi;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile
{
	public static void main(String args[])
	{
		System.out.println("write File");
		schreiben("writeFile");
	}


	public static void schreiben(String s)
	{
		try
		{
			FileWriter resultsFile = new FileWriter("hugo.txt", true);
			PrintWriter toFile = new PrintWriter(resultsFile);
			toFile.println(s);
			toFile.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
