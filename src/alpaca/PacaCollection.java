package alpaca;

import java.util.ArrayList;
import java.util.HashMap;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import alpaca.Alpaca;

public class PacaCollection {
	
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
		        HashMap childValues = (HashMap) snapshot.getValue ();
		        Double longitude    = (Double) childValues.get ("lng");
		        Double latitude     = (Double) childValues.get ("lat");		        
		        String name         = (String) childValues.get ("name");
		        
		        // Set the alpaca object with info we received from the DB
		        Alpaca in = new Alpaca ();
		        in.setLongitude(longitude.floatValue());
		        in.setLatitude (latitude.floatValue());
		        in.setID (name);
		        
		        alpacas.add (in);
		        info (name);
		    }

		    @Override
		    public void onChildChanged(DataSnapshot snapshot, String previousChildName) 
		    {

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
	
	public void info (String id)
	{
		Alpaca info = get (id);
		if (info != null)
		{
			System.out.println ("ID: " + info.getID());
			System.out.println ("Longitude: " + info.getLongitude());
			System.out.println ("Latitude: " + info.getLatitude());
		}
		System.out.println ();
	}
	
	public Alpaca get (String id)
	{
		for (Alpaca a : alpacas)
		{
			if (a.getID () == id)
				return a;
		}
		return null;
	}
	
	public void add (String key, Alpaca alpaca)
	{	
		dataRef.push ().setValue (alpaca);
	}
	
	public void remove ()
	{
		
	}
	
	public static void main (String[] args) 
	{
		new PacaCollection ();
	}
}

