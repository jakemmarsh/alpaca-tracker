package alpaca;
import PacaTraca.PacaTracaImpl;

public class Alpaca {
	
	private PacaTracaImpl hardware;
	private int trackerID;
	private String name;
	private float longitude;
	private float latitude;
	
	public Alpaca ()
	{
		hardware = new PacaTracaImpl ();
	}
	
	public void setName (String name)
	{
		this.name = name;
	}
	
	public void setTrackerID (double id)
	{
		this.trackerID = (int) id;
	}
	
	public void Update ()
	{
		System.out.println (name);
	}
	
	public void setLongitude (float longitude)
	{
		this.longitude = longitude;
	}
	
	public void setLatitude (float latitude)
	{
		this.latitude = latitude;
	}
	
	public int getTrackerID ()
	{
		return this.trackerID;
	}
	
	public float getLongitude ()
	{
		return this.longitude;
	}
	
	public float getLatitude ()
	{
		return this.latitude;
	}
}
