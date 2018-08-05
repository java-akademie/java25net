package ch.jmildner.net.primzahlen;

import java.io.IOException;

import ch.jmildner.tools_ee.SocketChannel;

public class StartHandler implements Runnable
{

    private final SocketChannel ch;

    public StartHandler(SocketChannel ch)
    {
        this.ch = ch;
    }

    @Override
    public void run()
    {
        try
        {
            ch.send(PrimServer.nextBigNumber());
            ch.send(PrimServer.nextBigNumber());
            System.out.println("BigNumber gesendet an " + ch);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
