
import alpaca.PacaCollection;
import TestDriversAndFactories.PacaTracaTestDriver;

/**
 * Handles program flow and initializes base-level code.
 */
public class Program {
	
	// An update speed in seconds
	private int updateSpeed; 
	
	// The collection of alpacas
	private PacaCollection collection;

	public Program ()
	{
		System.out.println ("Starting alpaca tracker");
		updateSpeed = 2;
		collection  = new PacaCollection ();
		update ();
	}

	/* This function updates each of the alpaca's statistics in 
	 * one second intervals. 
	 */
	private void update ()
	{
		while (true)
		{
			try 
			{			
				System.out.println ("Update");
				collection.update ();
			    Thread.sleep (updateSpeed * 1000);
			} 
			catch (InterruptedException e) 
			{
			    e.printStackTrace();
			}
		}
	}
	
	/**
	 * Cleans up and then exits the program.
	 */
	public void Exit ()
	{
		
	}
}
