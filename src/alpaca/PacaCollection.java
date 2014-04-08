package alpaca;

import java.util.ArrayList;
import java.util.HashMap;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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
public class PacaCollection implements Runnable{
	
	// URL and reference to the Firebase database
	private String   url;
	private Firebase dataRef;
	
	// The working collection of alpacas
	private ArrayList <Alpaca> alpacas;
	
	public PacaCollection ()
	{
		alpacas = new ArrayList <Alpaca> ();
		
		url = "https://crackling-fire-2064.firebaseio.com/alpacas";
		dataRef = new Firebase (url);
		
		dataRef.addChildEventListener (new ChildEventListener()
		{
		    @Override
		    public void onChildAdded (DataSnapshot snapshot, String previousChildName)
		    {
		    	// Hash-map of alpaca's characteristics 
		        HashMap alpaca = (HashMap) snapshot.getValue ();
		   
		        // Create alpaca object for local collection
		        Alpaca newAlpaca = new Alpaca ();		        
		        newAlpaca.setName      ((String) alpaca.get ("name"));
		        newAlpaca.setTrackerID ((Long) alpaca.get("trackerID"));
		        
		        alpacas.add (newAlpaca);
		        System.out.println (alpacas.size());
		    }

		    @Override
		    public void onChildChanged(DataSnapshot snapshot, String previousChildName) 
		    {
		    	System.out.println ("updating");
		    }

		    @Override
		    public void onChildRemoved(DataSnapshot snapshot)
		    {

		    }

		    @Override
		    public void onChildMoved(DataSnapshot snapshot, String previousChildName)
		    {

		    }

			@Override
			public void onCancelled(FirebaseError arg0) 
			{
				System.out.println ("out");	
			}
		});		
	}
	
	/**
	 * Prints information to the console detailing the
	 * queried alpaca.
	 * @param : ID that corresponds to given alpaca.
	 */
	public void info (int id)
	{
		Alpaca info = get (id);
		if (info != null)
		{
		//	System.out.println ("ID: " + info.getTrackerID());
		//	System.out.println ("Longitude: " + info.getLongitude());
		//	System.out.println ("Latitude: " + info.getLatitude());
		}
		System.out.println ();
	}
	
	/**
	 * Returns the alpaca object corresponding to the
	 * given ID.
	 * @param  : ID that corresponds to given alpaca.
	 * @return : the alpaca object
	 */
	public Alpaca get (int id)
	{
		for (Alpaca a : alpacas)
		{
			if (a.getTrackerID () == id)
				return a;
		}
		return null;
	}
	
	public ArrayList <Alpaca> getCollection ()
	{
		return alpacas;
	}
	
	/**
	 * Query the number of alpacas
	 * @return : the number of alpacas in the collection
	 */
	public int count ()
	{
		return alpacas.size ();
	}

	@Override
	public void run() 
	{
		
	}
}

