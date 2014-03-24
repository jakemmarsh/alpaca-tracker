import pacatraca.*;

/**
 * @author jon
 * This is our big main class. All program flow should begin here.
 */
public class main {

	public static void main (String[] args) 
	{
		Program pacaclient = new Program();
		pacaclient.Init ();
		pacaclient.BeginUpdateLoop ();
		pacaclient.Exit ();
	}
}
