
import alpaca.PacaCollection;
import TestDriversAndFactories.PacaTracaTestDriver;

/**
 * Handles program flow and initializes base-level code.
 */
public class Program {
	
	// The collection of alpacas
	private PacaCollection collection;

	public Program ()
	{
		System.out.println ("Starting alpaca tracker");
		collection = new PacaCollection ();
	}
    
	/* This call starts the programs update function */
	public void StartUpdate ()
	{	
		update ();
	}
	
	/* This function updates each of the alpaca's statistics in 
	 * one second intervals. 
	 */
	private void update ()
	{
		while (true)
		{
			collection.update ();
			try 
			{
			    Thread.sleep(1000);
			} catch (InterruptedException e) 
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
