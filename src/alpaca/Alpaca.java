package alpaca;
import com.firebase.client.Firebase;

import PacaTraca.PacaTraca;
import PacaTraca.PacaTracaImpl;
import TestDriversAndFactories.PacaTracaHardwareFactory;
import TestDriversAndFactories.PacaTracaTestDriver;

public class Alpaca {
	
	private PacaTraca hardware;
	private String trackerID;
	public  String name;
	public  String dbRef;
	private String longitude;
	private String latitude;
	
	public Alpaca ()
	{
		PacaTracaHardwareFactory pthf = new PacaTracaHardwareFactory ();
		hardware = pthf.createPacaTraca (trackerID);
	}
	
	public void updateDB ()
	{
		Firebase dataRef = new Firebase (dbRef);

		dataRef.child ("lng").setValue  ((hardware.getLongitudeDecimalDegrees ()));
		dataRef.child ("lat").setValue  ((hardware.getLongitudeDecimalDegrees ()));
	}
	
	public void setName (String name)
	{
		this.name = name;
	}
	
	public void setDBRef (String location, int ID)
	{
		this.dbRef = location + "/" + ID;
	}
	
	public void setTrackerID (String id)
	{
		this.trackerID = String.valueOf (id);
	}
	
	public void setLongitude (String longitude)
	{
		this.longitude = longitude;
	}
	
	public void setLatitude (String latitude)
	{
		this.latitude = latitude;
	}
	
	public String getTrackerID ()
	{
		return this.trackerID;
	}
	
	public String getLongitude ()
	{
		return this.longitude;
	}
	
	public String getLatitude ()
	{
		return this.latitude;
	}
	
	public String getName ()
	{
		return name;
	}
}
