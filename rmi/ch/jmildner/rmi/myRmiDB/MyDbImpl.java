package ch.jmildner.rmi.myRmiDB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDbImpl extends UnicastRemoteObject implements MyDb
{
	private static final long serialVersionUID = 1L;

	static String dbDriver = "";
	static String dbURL = "";
	static Connection con = null;
	static Statement stm = null;
	static ResultSet rs = null;


	public MyDbImpl() throws RemoteException
	{
		System.out.println("konstruktor MyDbImpl");
	}


	@Override
	public String dbClose() throws RemoteException
	{
		if (con == null)
		{
			return "datenbank ist nicht geoeffnet";
		}
		try
		{
			con.close();
			con = null;
			return "db geschlossen";
		}
		catch (SQLException e)
		{
			return "connection kann nicht geschlossen werden\n" + e;
		}
	}


	@Override
	public String dbMetadaten() throws RemoteException
	{
		if (con == null)
		{
			return "datenbank ist nicht geoeffnet";
		}
		DatabaseMetaData metadaten = null;
		String s = "";
		try
		{
			metadaten = con.getMetaData();
			if (metadaten == null)
			{
				return "keine Metadaten verfuegbar";
			}
			else
			{
				s = s + "Metadaten \n----------------";
				s = s + "\nDatabaseProductName     :\t"
						+ metadaten.getDatabaseProductName();
				s = s + "\nDatabaseProductVersion  :\t"
						+ metadaten.getDatabaseProductVersion();
				s = s + "\nDriverName              :\t"
						+ metadaten.getDriverName();
				s = s + "\nsupportOrderByUnrelated :\t"
						+ metadaten.supportsOrderByUnrelated();
				s = s + "\nNumericFunctions        :\t"
						+ metadaten.getNumericFunctions();
				s = s + "\nsupportsTransactions    :\t"
						+ metadaten.supportsTransactions();
				s = s + "\nende der Metadaten";
				/*******************************************************
				 * ResultSet rs=metadaten.getTables(null,"%","%",null);
				 * 
				 * while(rs.next()) { String s1=rs.getString(1); String
				 * s2=rs.getString(2); s=s+s1+"\t"+s2+"\n";
				 * System.out.println(s1); System.out.println(s2); }
				 */
				return s;
			}
		}
		catch (Exception e)
		{
			return "Metadaten nicht ermittelbar\n" + e;
		}
	}


	@Override
	public String dbOpen(String dbase) throws RemoteException
	{
		System.out.println("dbOpen");
		if (con != null)
		{
			return "datenbank ist schon geoeffnet";
		}
		if (dbase.equals("access"))
		{
			dbDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			dbURL = "jdbc:odbc:atest";
		}
		if (dbase.equals("mysql"))
		{
			dbDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
			dbURL = "jdbc:odbc:mtest";
		}
		try
		{
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbURL);
			return "db geoeffnet";
		}
		catch (ClassNotFoundException e)
		{
			return "fehler bei treiber laden\n" + e;
		}
		catch (SQLException e)
		{
			return "fehler bei connect\n" + e;
		}
	}


	@Override
	public String kCreate() throws RemoteException
	{
		if (con == null)
		{
			return "datenbank ist nicht geoeffnet";
		}
		try
		{
			con.setAutoCommit(false);
			stm = con.createStatement();
			stm.executeUpdate("CREATE TABLE kunde "
					+ "(name 		CHAR(20)  " + ",nummer 	INT NOT NULL"
					+ ",PRIMARY KEY (nummer)" + ");");
			stm.close();
			con.commit();
			return "create kunde ok";
		}
		catch (SQLException e)
		{
			return "fehler bei create kunde" + e;
		}
	}


	@Override
	public String kDelete() throws RemoteException
	{
		if (con == null)
		{
			return "datenbank ist nicht geoeffnet";
		}
		try
		{
			con.setAutoCommit(false);
			stm = con.createStatement();
			stm.executeUpdate("DELETE FROM kunde WHERE name='xxxx'");
			stm.close();
			con.commit();
			return "delete kunde OK";
		}
		catch (SQLException e)
		{
			return "fehler bei delete kunde\n" + e;
		}
	}


	@Override
	public String kDrop() throws RemoteException
	{
		if (con == null)
		{
			return "datenbank ist nicht geoeffnet";
		}
		try
		{
			con.setAutoCommit(false);
			stm = con.createStatement();
			stm.executeUpdate("DROP TABLE kunde ");
			stm.close();
			con.commit();
			return "drop kunde ok";
		}
		catch (SQLException e)
		{
			return "fehler bei drop kunde: " + e;
		}
	}


	@Override
	public String kInsert() throws RemoteException
	{
		int max = 5;
		if (con == null)
		{
			return "datenbank ist nicht geoeffnet";
		}
		try
		{
			con.setAutoCommit(false);
			stm = con.createStatement();
			String name;
			int nummer;
			for (int i = 1; i <= max; i++)
			{
				if (i % 1000 == 0)
					System.out.println(i);
				name = "name" + i;
				nummer = i;
				stm.executeUpdate("INSERT INTO kunde " + "VALUES ('"
						+ name + "'," + nummer + ")");
				name = "xxxx";
				nummer = i;
				stm.executeUpdate("INSERT INTO kunde " + "VALUES ('"
						+ name + "'," + (nummer + 1000000) + ")");
			}
			stm.close();
			con.commit();
			return "insert kunde OK";
		}
		catch (SQLException e)
		{
			return "fehler bei insert kunde\n" + e;
		}
	}


	@Override
	public String kInsert(String name, int nummer)
			throws RemoteException
	{
		if (con == null)
		{
			return "datenbank ist nicht geoeffnet";
		}
		try
		{
			con.setAutoCommit(false);
			stm = con.createStatement();
			stm.executeUpdate("INSERT INTO kunde " + "VALUES ('" + name
					+ "'," + nummer + ")");
			stm.close();
			con.commit();
			return "insert kunde OK";
		}
		catch (SQLException e)
		{
			return "fehler bei insert kunde\n" + e;
		}
	}


	@Override
	public String kSelect() throws RemoteException
	{
		if (con == null)
		{
			return "datenbank ist nicht geoeffnet";
		}
		try
		{
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT name,nummer FROM kunde ORDER BY kunde.nummer");
			String s3 = "";
			while (rs.next())
			{
				String s1 = rs.getString(1);
				String s2 = rs.getString(2);
				s3 = s3 + s1 + "\t" + s2 + "\n";
				System.out.println(s1);
				System.out.print(s2);
			}
			stm.close();
			con.commit();
			return s3;
		}
		catch (SQLException e)
		{
			return "fehler bei anzeigenKunden\n" + e
					+ "\ndatenbank ist eventuell nicht geoeffnet"
					+ "\noder tabelle 'kunde' nicht vorhanden";
		}
	}
}
