/**
 * @author jon
 * Handles program flow.
 */
public class Program {
	
	//True if running, false to disable the program.
	private boolean isRunning;
	
	public Program(){
	}
	
	/**
	 * Initialize program before update loop
	 */
	public void Init(){
		
		isRunning = true;
	}
	
	/**
	 * Runs the update loop.
	 */
	public void BeginUpdateLoop(){
		while(isRunning){
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
