package ch.jmildner.rmi.myrmi;

class Datensatz implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;


	public static void main(String args[])
	{
		System.out.println("start main der Klasse: Datensatz");
		Datensatz ds = new Datensatz("name1", "nummer1", "data1");
		ds.showRecord();
	}
	
	String name;
	String nummer;


	String data;


	public Datensatz(String name, String nummer)
	{
		this(name, nummer, "");
	}


	public Datensatz(String name, String nummer, String data)
	{
		this.name = name;
		this.nummer = nummer;
		this.data = data;
	}


	String getData()
	{
		return (data);
	}


	String getName()
	{
		return (name);
	}


	String getNummer()
	{
		return (nummer);
	}


	public void replaceRecord(String name, String nummer, String data)
	{
		this.name = name;
		this.nummer = nummer;
		this.data = data;
	}


	public void showRecord()
	{
		System.out.println(getName() + " " + getNummer() + " "
				+ getData());
	}
}
