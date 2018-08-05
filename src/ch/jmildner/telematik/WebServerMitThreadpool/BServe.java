package ch.jmildner.telematik.WebServerMitThreadpool;

import java.net.ServerSocket;
import java.net.Socket;

public class BServe
{

    static final int BUFLEN = 1024;

    public static void main(String[] arg)
    {
        String docRoot = ".";
        boolean pooling = false;
        TPool tPool = null;

        if (arg.length > 0)
        {
            docRoot = arg[0];
        }
        if (arg.length > 1)
        {
            pooling = true;
            int size = Integer.parseInt(arg[1]);
            tPool = new TPool(size);
        }

        try
        {
            @SuppressWarnings("resource")
            ServerSocket ss = new ServerSocket(8080);
            while (true)
            {
                Socket connection = ss.accept();
                RequestHandler handler = new RequestHandler(connection,
                        docRoot);
                if (pooling)
                {
                    tPool.execute(handler);
                }
                else
                {
                    Thread handlerThread = new Thread(handler);
                    handlerThread.start();
                }
            }
        }
        catch (java.io.IOException ex)
        {
            System.err.println(ex);
        }
    }
}
