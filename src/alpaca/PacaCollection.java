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
 * @author claytonpeterson
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
					newAlpaca.setLatitude  ((Double) qualities.get ("lat"));
					newAlpaca.setLongitude ((Double) qualities.get ("lng"));
					newAlpaca.setTrackerID ((Long)   qualities.get ("trackerID"));
					newAlpaca.setDBRef     (url, i);
					
					alpacas.add (newAlpaca);
					System.out.println ("Retrieving alpaca: " + newAlpaca.name);
				}
			}

			@Override
			public void onCancelled(FirebaseError arg0) 
			{}
		});
	}
	
	/* This method tells each of the alpaca objects to update their
	 * information on the database. 
	 */
	public void update ()
	{
		for (int i = 0; i < alpacas.size (); i ++)
		{
		//	System.out.println (i);
			alpacas.get (i).updateDB ();	
		}
	}
}

