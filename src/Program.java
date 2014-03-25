import java.util.HashMap;

import Alpaca.Alpaca;

/**
 * @author jon
 * Handles program flow.
 */
public class Program {
	
	int numberOfAlpacas = 1;
	private HashMap <Integer, Alpaca> alpacas;
	
	//True if running, false to close the program.
	private boolean isRunning;
	
	public Program()
	{
		alpacas = new HashMap <Integer, Alpaca> ();
		addAlpaca ("Jeff");
	}
	
	private void addAlpaca (String name)
	{
		Alpaca a = new Alpaca (name, numberOfAlpacas);
		alpacas.put (numberOfAlpacas, a);
		numberOfAlpacas ++;
	}
	
	
	/**
	 * Initialize program before update loop
	 */
	public void Init()
	{
		isRunning = true;
	}
	
	/**
	 * Runs the update loop.
	 */
	public void BeginUpdateLoop()
	{
		while(isRunning)
		{
			Update();
		}
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
}
