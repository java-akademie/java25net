package ch.jmildner.telematik.dbServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbKunde
{

    private Connection con;
    private Statement stm;
    private ResultSet rs;

    public DbKunde()
    {
        openDB();
    }

    public void closeDB()
    {
        try
        {
            stm.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println("fehler bei closeDB\n" + e);
        }
    }

    public String getKundenName(int i)
    {
        String name = "name zu id(" + i + ") nicht gefunden";
        try
        {
            rs = stm.executeQuery("SELECT name FROM kunde WHERE nummer="
                    + i);
            while (rs.next())
            {
                name = rs.getString(1);
            }
        }
        catch (Exception e)
        {
            System.out.println("fehler bei select kunden\n" + e);
            name = name + " / " + e;
        }
        return name;
    }

    public void insertKunde()
    {
        try
        {
            stm.executeUpdate("INSERT INTO kunde VALUES ('anna'     ,1)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('hugo'     ,2)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('fritz'    ,3)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('moritz'   ,4)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('urs'      ,5)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('hans'     ,6)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('jupp'     ,7)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('schorsch' ,8)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('georg'    ,9)");
            stm.executeUpdate("INSERT INTO kunde VALUES ('wilhelm'  ,10)");
        }
        catch (Exception e)
        {
            System.out.println("fehler bei insert kunden\n" + e);
        }
    }

    public void openDB()
    {
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:atest");
            stm = con.createStatement();
        }
        catch (Exception e)
        {
            System.out.println("fehler bei openDB\n" + e);
        }
    }
}
