import PacaTraca.*;

/**
 * This is our big main class. All program flow should begin here.
 */
public class Main {

	public static void main (String[] args) 
	{
		Program pacaclient = new Program();
		pacaclient.BeginUpdateLoop ();
		pacaclient.Exit ();
	}
}
