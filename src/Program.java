import java.util.HashMap;

import alpaca.Alpaca;

/**
 * Handles program flow and initializes base-level code.
 */
public class Program {
	
	int numberOfAlpacas = 1;
	private HashMap <Integer, Alpaca> alpacas;
	
	//True if running, false to close the program.
	private boolean isRunning;
	
	public Program()
	{
		Init();
		alpacas = new HashMap <Integer, Alpaca> ();
		addAlpaca ("Jeff");
	}
	
	/**
	 * Initialize program before update loop
	 */
	public void Init()
	{
		//Create and initialize the main window.
//		isRunning = true;
	}
	
	/**
	 * Runs the update loop.
	 */
	public void BeginUpdateLoop()
	{
//		while(isRunning)
//		{
//			Update();
//		}
	}
	
	/**
	 * Update loop. Runs over and over until program ends.
	 */
	public void Update(){
		
	}
	
	/**
	 * Cleans up and then exits the program.
	 */
	public void Exit(){
		
	}
	
	private void addAlpaca (String name)
	{
		Alpaca a = new Alpaca (name, numberOfAlpacas);
		alpacas.put (numberOfAlpacas, a);
		numberOfAlpacas ++;
	}
}
