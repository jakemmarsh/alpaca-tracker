package alpaca;
import PacaTraca.PacaTracaImpl;

public class Alpaca {
	
	public PacaTracaImpl hardware;
	private float longitude;
	private float latitude;
	private String id;
	
	public Alpaca ()
	{
		hardware = new PacaTracaImpl ();
	}
	
	public void setID (String id)
	{
		this.id = id;
	}
	
	public void setLongitude (float longitude)
	{
		this.longitude = longitude;
	}
	
	public void setLatitude (float latitude)
	{
		this.latitude = latitude;
	}
	
	public String getID ()
	{
		return this.id;
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
