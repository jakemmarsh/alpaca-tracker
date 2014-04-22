
import alpaca.PacaAnalyzer;
import alpaca.PacaCollection;
import alpaca.PacaWorld;

/**
 * Handles program flow and initializes base-level code.
 * @author Clayton Peterson, Jonathan Cole
 */
public class Program {
	
	// The analysis update speed in seconds
	private int updateSpeed = 10; 
	
	// The collection of alpacas
	private PacaCollection collection;
	private PacaAnalyzer   analyzer; 
	private PacaWorld      world;
	
	public Program ()
	{
		System.out.println ("[Program] Starting alpaca tracker");
		collection  = new PacaCollection ();
		world       = new PacaWorld      ();
		analyzer    = new PacaAnalyzer   (world);
		update ();
	}

	/**
	 * This function updates each of the alpaca's statistics
	 * to the database according to a given time step in seconds.   
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
		System.out.println ("[Program] Exiting alpaca tracker");
	}
}
