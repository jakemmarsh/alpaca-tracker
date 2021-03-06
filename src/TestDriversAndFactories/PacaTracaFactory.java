package TestDriversAndFactories;

import PacaTraca.PacaTraca;

/**
 * @author Bryan Wells
 * PacaTraca Factory Interface.  All PacaTracaFactories must implement this interface.  
 * 
 */

public interface PacaTracaFactory {
	public PacaTraca createPacaTraca( String sensorID );
}