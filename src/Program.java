import java.util.HashMap;

import alpaca.Alpaca;
import alpaca.PacaAnalyzer;
import alpaca.PacaCollection;
import PacaTraca.*;
import TestDriversAndFactories.PacaTracaTestDriver;

/**
 * Handles program flow and initializes base-level code.
 */
public class Program {
	
	PacaTracaTestDriver testDriver = new PacaTracaTestDriver();
	
	//True if running, false to close the program.
	private boolean isRunning;
	
	// The collection of alpacas
	private PacaCollection collection;
	
	// The alpaca analyzer
	private PacaAnalyzer analyzer;
	
	public Program ()
	{
		System.out.println ("Starting alpaca tracker");
		collection = new PacaCollection ();
	}
	
	/**
	 * Runs the update loop.
	 */
	public void BeginUpdateLoop()
	{
		isRunning = true;
		
		while (isRunning)
		{
			Update ();
		}
	}
	
	/**
	 * Update loop. Runs over and over until program ends.
	 */
	public void Update()
	{
		for (Alpaca a : collection.getCollection())
			a.Update();
	}
	
	/**
	 * Cleans up and then exits the program.
	 */
	public void Exit()
	{
		
	}
}
