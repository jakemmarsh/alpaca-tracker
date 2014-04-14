
import alpaca.PacaAnalyzer;
import alpaca.PacaCollection;
import alpaca.PacaWorld;

/**
 * Handles program flow and initializes base-level code.
 */
public class Program {
	
	// An update speed in seconds
	private int updateSpeed; 
	
	// The collection of alpacas
	private PacaCollection collection;
	private PacaAnalyzer   analyzer; 
	private PacaWorld      world;
	
	public Program ()
	{
		System.out.println ("Starting alpaca tracker");
		updateSpeed = 2;
		collection  = new PacaCollection ();
		world       = new PacaWorld      ();
		analyzer    = new PacaAnalyzer   (world);
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
				collection.update ();
				analyzer.analyze  (collection.getAlpacas ());
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
		System.out.println ("Quiting alpaca tracker");
	}
}
