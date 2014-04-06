package alpaca;
import PacaTraca.PacaTracaImpl;

public class Alpaca {
	
	public PacaTracaImpl hardware;
	
	public Alpaca (String SensorID)
	{
		hardware = new PacaTracaImpl(SensorID);
	}
	
}
