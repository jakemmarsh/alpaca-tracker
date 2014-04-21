package alpaca;

import java.util.ArrayList;
import java.util.HashMap;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;

import alpaca.Alpaca;

/**
 * This class is used to store alpaca objects. It first
 * retrieves the name and tracker ID of each alpaca from
 * the database and creates an alpaca object that can be 
 * used to update the information of the alpaca in the 
 * database. 
 * 
 * Process representation
 * [DB] -> [PacaCollection] = [x number of Alpaca objects]
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
	
	public PacaCollection ()
	{
		url = "https://crackling-fire-2064.firebaseio.com/alpacas";
		dataRef = new Firebase (url);
        alpacas = new ArrayList <Alpaca> ();
        
        /* This adds a listener to the alpaca reference and creates
         * an alpaca object for each alpaca stored in the online 
         * database.
         */
		dataRef.addValueEventListener (new ValueEventListener () 
		{
			@Override
			public void onDataChange(DataSnapshot snapshot) 
			{
				ArrayList snap = (ArrayList) snapshot.getValue ();
				
				for (int i = 0; i < snap.size (); i ++)
				{
					HashMap qualities = (HashMap) snap.get (i);
										
					Alpaca newAlpaca = new Alpaca ();
					newAlpaca.setName      ((String) qualities.get ("name"));
					newAlpaca.setLatitude  (String.valueOf (qualities.get ("lat")));
					newAlpaca.setLongitude (String.valueOf (qualities.get ("lng")));
					newAlpaca.setHeartRate((Float) qualities.get ("heartRate")); 
					newAlpaca.setTrackerID (String.valueOf (qualities.get ("trackerID")));
					newAlpaca.setDBRef     (url, i);
			    	
					if (alpacas.size() == 0)
					{
						alpacas.add (newAlpaca);
					}
					else 
					{
						boolean in = false;
						for (int x = 0; x < alpacas.size (); x++)
						{
							if (newAlpaca.name == alpacas.get (x).name)
								in = true;
						}
						if (in == false)
							alpacas.add (newAlpaca);
					}
				}
			}

			@Override
			public void onCancelled(FirebaseError arg0) 
			{}
		});
	}
	
	/** 
	 * This method tells each of the alpaca objects to update their
	 * information on the database. 
	 * @author Clayton Peterson
	 */
	public void update ()
	{
		for (int i = 0; i < alpacas.size (); i ++)
		{
			Alpaca a = alpacas.get (i);			
			Firebase dataRef = new Firebase (a.dbRef);
			dataRef.child ("lat").setValue ((a.hardware.getLatitudeDecimalDegrees  ()));
			dataRef.child ("lng").setValue ((a.hardware.getLongitudeDecimalDegrees ()));
			dataRef.child ("heartRate").setValue ((a.hardware.getHeartRate ()));
		}
	}
	
	/**
	 * @return the collection of alpacas
	 * @author Clayton Peterson
	 */
	public ArrayList <Alpaca> getAlpacas ()
	{
		return alpacas; 
	}
}

