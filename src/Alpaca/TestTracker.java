package Alpaca;

import java.util.Random;

public class TestTracker {
	
	public float movementSpeed ()
	{
		Random rand = new Random ();
		return rand.nextInt (5); 
	}
	
	public float[] location ()
	{
		return new float [] {1, 2, 3};
	}
	
	public float bodyTemperature ()
	{
		return 0.0f;
	}
	
	// --------------------------------------------------
	public boolean isLayingDown ()
	{
		return true;
	}
	
}
