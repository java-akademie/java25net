package ch.jmildner.telematik.socket.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * dieser ComplexSocketServer akzeptiert Anfragen eines Clients am Port 4711,
 * ruft fuer die Anfrage die Verarbeitung der Anfrage auf und ist sofort wieder
 * fuer die naechste Anfrage bereit.
 *
 * Test: <code>telnet localhost 4711 ... mit $ beenden</code>
 *
 * @author johann
 *
 */
public class ComplexSocketServer
{

    static ServerSocket serverSocket;

    static void acceptClient() throws IOException
    {
        System.out.println("server wartet auf client ...");

        final Socket socket = serverSocket.accept();

        new Thread()
        {
            String buff;

            BufferedReader br;
            PrintWriter pw;

            @Override
            public void run()
            {
                System.out.println("Verbindung hergestellt ...");

                try
                {
                    pw = new PrintWriter(new OutputStreamWriter(
                            socket.getOutputStream()));
                    br = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));

                    buff = br.readLine();
                    while (!buff.equals("$"))
                    {
                        pw.println(":" + buff);
                        pw.flush();

                        buff = br.readLine();
                    }

                    pw.println("ende");
                    pw.flush();
                    System.out.println("ende ...");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void main(String[] args) throws IOException
    {
        startServer();

        while (true)
        {
            acceptClient();
        }
    }

    static void startServer() throws IOException
    {
        serverSocket = new ServerSocket(4711);
    }
}
