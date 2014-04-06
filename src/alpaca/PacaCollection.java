package alpaca;

import java.util.HashMap;

import com.firebase.client.Firebase;

import alpaca.Alpaca;

public class PacaCollection {
	
	private Firebase ref;
	
	public PacaCollection ()
	{
		 ref = new Firebase ("https://crackling-fire-2064.firebaseio.com/alpacas");
		 System.out.println (ref);
	}
	
	public void add (String key, Alpaca alpaca)
	{
		
	}
	
	public void remove ()
	{
		
	}
	
	public Alpaca getAlpaca (Integer key)
	{
		return null;
	}
	
	public void eraseCollection ()
	{
		
	}
	
	public boolean contains (Integer key)
	{
		return false;
	}
	
	public static void main (String[] args) 
	{
		new PacaCollection ();
		System.out.println ("hi");
	}
}



