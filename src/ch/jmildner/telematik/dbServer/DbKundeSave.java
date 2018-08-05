package ch.jmildner.telematik.dbServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbKundeSave
{

    private Connection con;
    private Statement stm;
    private ResultSet rs;
    private boolean test = true;

    public DbKundeSave()
    {
        openDB();
    }

    public void closeDB()
    {
        try
        {
            protokoll("\nclose access DB");
            stm.close();
            con.close();
        }
        catch (Exception e)
        {
        }
    }

    public void createKunde()
    {
        try
        {
            System.out.println("vor create table kunde");
            stm.executeUpdate("CREATE TABLE kunde          "
                    + "(name     CHAR(20)     "
                    + ",nummer   INT NOT NULL "
                    + ",PRIMARY KEY (nummer)  " + ") " + ";");
            System.out.println("vor create index kunde");
            stm.executeUpdate("CREATE INDEX kunde_index on kunde(nummer) "
                    + ";");
        }
        catch (Exception e)
        {
            System.out.println("fehler - create kunde\n" + e);
        }
        finally
        {
            protokoll("nach create kunde\n");
        }
    }

    public void dropKunde()
    {
        try
        {
            protokoll("vor loeschen kunde");
            stm.executeUpdate("DROP TABLE kunde");
        }
        catch (Exception e)
        {
            System.out.println("fehler - loeschen kunde\n" + e);
        }
        finally
        {
            protokoll("nach loeschen kunde\n");
        }
    }

    public String getKundenName(int i)
    {
        String name = "name zu id(" + i + ") nicht gefunden";
        try
        {
            rs = stm.executeQuery("SELECT kunde.name " + "FROM kunde "
                    + "WHERE kunde.nummer=" + i + " ");
            while (rs.next())
            {
                name = rs.getString(1);
            }
        }
        catch (Exception e)
        {
            name = name + "\n" + e;
        }
        return name;
    }

    public void insertKunde()
    {
        try
        {
            protokoll("vor insert kunde");
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
            System.out.println("fehler - insert kunde\n" + e);
        }
        finally
        {
            protokoll("nach insert kunde");
        }
    }

    public void openDB()
    {
        try
        {
            protokoll("\nopen access DB");
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:atest");
            stm = con.createStatement();
        }
        catch (Exception e)
        {
        }
    }

    private void protokoll(String s)
    {
        if (test)
        {
            System.out.println(s);
        }
    }
}
