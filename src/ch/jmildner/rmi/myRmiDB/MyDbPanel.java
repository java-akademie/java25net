package ch.jmildner.rmi.myRmiDB;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyDbPanel extends Panel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	Button dbOpenAccess = new Button("dbOpenAccess");
	Button dbOpenMySql = new Button("dbOpenMySql");
	Button dbClose = new Button("dbClose");
	Button dbMetadaten = new Button("metadaten");
	Button kCreate = new Button("createKunde");
	Button kDrop = new Button("dropKunde");
	Button kInsert = new Button("insertKunde");
	Button kDelete = new Button("deleteKunde");
	Button kSelect = new Button("selectKunde");
	Button kClear = new Button("Clear");
	Button kEnde = new Button("Ende");

	TextArea ta = new TextArea(20, 80);
	TextField t1 = new TextField("", 20);
	TextField t2 = new TextField("", 20);
	TextField t3 = new TextField("", 20);

	MyDb mdb;


	public MyDbPanel(String ip) throws RemoteException,
			NotBoundException, MalformedURLException
	{
		mdb = (MyDb) Naming.lookup("rmi://" + ip + "/mydb");

		makeTheLayout();
		addTheListener();
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			String s;

			if (e.getSource() == dbOpenAccess)
			{
				s = mdb.dbOpen("access");
				ta.setText(s);
			}

			if (e.getSource() == dbOpenMySql)
			{
				s = mdb.dbOpen("mysql");
				ta.setText(s);
			}

			if (e.getSource() == dbClose)
			{
				s = mdb.dbClose();
				ta.setText(s);
			}

			if (e.getSource() == dbMetadaten)
			{
				s = mdb.dbMetadaten();
				ta.setText(s);
			}

			if (e.getSource() == kCreate)
			{
				s = mdb.kCreate();
				ta.setText(s);
			}

			if (e.getSource() == kDrop)
			{
				s = mdb.kDrop();
				ta.setText(s);
			}

			if (e.getSource() == kInsert)
			{
				int nr;
				String na = t1.getText();
				try
				{
					nr = Integer.parseInt(t2.getText());
				}
				catch (Exception ee)
				{
					nr = 0;
				}
				if (nr > 0)
				{
					s = mdb.kInsert(na, nr);
				}
				else
				{
					s = mdb.kInsert();
				}
				ta.setText(s);
			}

			if (e.getSource() == kDelete)
			{
				s = mdb.kDelete();
				ta.setText(s);
			}

			if (e.getSource() == kSelect)
			{
				s = mdb.kSelect();
				ta.setText(s);
			}
		}
		catch (RemoteException re)
		{
		}
	}


	public void addTheListener()
	{
		dbOpenAccess.addActionListener(this);
		dbOpenMySql.addActionListener(this);
		dbClose.addActionListener(this);
		dbMetadaten.addActionListener(this);
		kCreate.addActionListener(this);
		kDrop.addActionListener(this);
		kInsert.addActionListener(this);
		kDelete.addActionListener(this);
		kSelect.addActionListener(this);
		kClear.addActionListener(this);
		kEnde.addActionListener(this);
	}


	public void makeTheLayout()
	{
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();

		p1.setLayout(new GridLayout(2, 5));
		p2.setLayout(new FlowLayout());
		p3.setLayout(new FlowLayout());

		p1.add(dbOpenAccess);
		p1.add(dbOpenMySql);
		p1.add(dbClose);
		p1.add(dbMetadaten);
		p1.add(new Label(""));
		p1.add(kClear);
		p1.add(kCreate);
		p1.add(kDrop);
		p1.add(kInsert);
		p1.add(kDelete);
		p1.add(kSelect);
		p1.add(kEnde);

		p2.add(t1);
		p2.add(t2);
		p2.add(t3);

		p3.add(ta);

		setLayout(new BorderLayout());

		add("North", p1);
		add("Center", p2);
		add("South", p3);
	}
}
