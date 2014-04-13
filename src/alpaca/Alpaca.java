package alpaca;
import com.firebase.client.Firebase;

import PacaTraca.PacaTraca;
import PacaTraca.PacaTracaImpl;
import TestDriversAndFactories.PacaTracaHardwareFactory;
import TestDriversAndFactories.PacaTracaTestDriver;

public class Alpaca {
	
	private PacaTraca hardware;
	private String trackerID;
	public String name;
	public String dbRef;
	private double longitude = 10;
	private double latitude = 10;
	
	public Alpaca ()
	{
		PacaTracaHardwareFactory pthf = new PacaTracaHardwareFactory ();
		hardware = pthf.createPacaTraca (trackerID);
	}
	
	public void updateDB ()
	{
		System.out.println (String.valueOf (hardware.getLongitudeDecimalDegrees ()));
		
		String.valueOf (hardware.getLongitudeDecimalDegrees ());
		
		Firebase dataRef = new Firebase (dbRef);
		dataRef.child ("lng").setValue  (longitude);
		dataRef.child ("lat").setValue  (latitude);
	}
	
	public void setName (String name)
	{
		this.name = name;
	}
	
	public void setDBRef (String location, int ID)
	{
		System.out.println (location + "/" + ID);
		this.dbRef = location + "/" + ID;
	}
	
	public void setTrackerID (Long id)
	{
		this.trackerID = String.valueOf (id);
	}
	
	public void setLongitude (double longitude)
	{
		this.longitude = longitude;
	}
	
	public void setLatitude (double latitude)
	{
		this.latitude = latitude;
	}
	
	public String getTrackerID ()
	{
		return this.trackerID;
	}
	
	public double getLongitude ()
	{
		return this.longitude;
	}
	
	public double getLatitude ()
	{
		return this.latitude;
	}
}
