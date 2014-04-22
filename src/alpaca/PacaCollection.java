package alpaca;

import java.util.ArrayList;
import java.util.HashMap;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import alpaca.Alpaca;

/**
 * This class is used to store alpaca objects. It first
 * retrieves the name and tracker ID of each alpaca from
 * the database and creates an alpaca object that can be 
 * used to update the information of the alpaca in the 
 * database. 
 * 
 * Process representations
 * 
 * Loading
 * [DB] -> [PacaCollection] = [x number of Alpaca objects]
 * 
 * Updating
 * [Alpaca object] -> [Update] -> [DB]
 * 
 * @author Clayton Peterson
 *
 */
public class PacaCollection {
	
	/* URL and reference to the Firebase database */
	private String   url;
	private Firebase dataRef;
	
	/* The working collection of alpacas */
	private ArrayList <Alpaca> alpacas;
	
	/** =================================================================================
	 * @author clayton peterson 
	 */
	public PacaCollection ()
	{
		url = "https://crackling-fire-2064.firebaseio.com/alpacas";
		dataRef = new Firebase (url);
        alpacas = new ArrayList <Alpaca> ();
        
        loadAlpacasFromDatabase ();
	}
	
	/** =================================================================================
	 * @author clayton peterson 
	 */
	private void loadAlpacasFromDatabase ()
	{
		dataRef.addListenerForSingleValueEvent (new ValueEventListener() 
		{    
			@Override
		    public void onDataChange (DataSnapshot snapshot) 
		    {
				ArrayList <?> database = (ArrayList <?>) snapshot.getValue ();
				
		        for (int ID = 0; ID < database.size (); ID++)
		        {
		        	HashMap <?,?> alpacaDetails = (HashMap <?, ?>) database.get (ID);	
		        	alpacas.add (createAlpaca (ID, alpacaDetails));
		        }
		    }

			@Override
			public void onCancelled (FirebaseError arg0) 
			{
				System.out.println ("Error loading from database");
			}
		});
	}
	
	/** =================================================================================
	 * @param ID
	 * @param alpacaDetails
	 * @return
	 * @author clayton peterson 
	 */
	private Alpaca createAlpaca (int ID, HashMap <?,?> alpacaDetails)
	{
		Alpaca out = new Alpaca ();
		out.setDBRef     (url, ID);
		out.setName      ((alpacaDetails.get ("name")).toString());
		out.setTrackerID ((alpacaDetails.get ("trackerID")).toString());
		out.hardware.setBatteryLife (((Long)
    			alpacaDetails.get ("trackerBatteryLife")).intValue());
		
		return out;
	}
	
	/** =================================================================================
	 * This method tells each of the alpaca objects to update their
	 * information on the database. 
	 * @author clayton peterson 
	 */
	public void update ()
	{
		for (int i = 0; i < alpacas.size (); i ++)
		{
			Alpaca a = alpacas.get (i);			
			Firebase dataRef = new Firebase (a.getDatabaseRef());
			
			// Location
			dataRef.child ("lat").setValue (a.hardware.getLatitudeDecimalDegrees ());
			dataRef.child ("lng").setValue (a.hardware.getLongitudeDecimalDegrees ());
			
			// Movement
			dataRef.child ("speed").setValue (a.hardware.getSpeed ());
			dataRef.child ("course").setValue (a.hardware.getCourse ());
			
			// Flying 
			dataRef.child ("altitude").setValue (a.hardware.getAltitude ());
			dataRef.child ("roll").setValue (a.hardware.getRoll ());
			dataRef.child ("pitch").setValue (a.hardware.getPitch ());

			// Vitals
			dataRef.child ("heartRate").setValue (a.hardware.getHeartRate ());
			dataRef.child ("temperature").setValue (a.hardware.getTemperature ());
			
			// Tracker details
			dataRef.child ("trackerBatteryLife").setValue (a.hardware.getBatteryLife ());
			dataRef.child ("hasFix").setValue (a.hardware.haveFix());
			dataRef.child ("numSatellites").setValue (a.hardware.getNumSatellites ());
			dataRef.child ("signalQuality").setValue (a.hardware.getSignalQuality ());
		}
	}

	/** =================================================================================
	 * @return the collection of alpacas
	 * @author clayton peterson 
	 */
	public ArrayList <Alpaca> getAlpacas ()
	{
		return alpacas; 
	}
}

