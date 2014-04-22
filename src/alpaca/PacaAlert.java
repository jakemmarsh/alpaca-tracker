package alpaca;

/**
 * @author Jonathan Cole
 * Framework for alerts sent to the frontend.
 */
public class PacaAlert {
	public boolean readByUser = false;
	public Alpaca alpaca;
	public static enum EventPriority{ Normal, Warning, Critical };
	public EventPriority priority = EventPriority.Normal;
	public static enum EventType{
		TemperatureLow,
		TemperatureHigh,
		Dead,
		Sick,
		Isolated,
		OutOfBounds,
		BatteryLow,
		LowSatellites,
		HeartRateLow,
		HeartRateHigh
	};
	public EventType type;
	
	public PacaAlert(Alpaca alp, EventType t){
		alpaca = alp;
		type = t;
	}

	public PacaAlert(Alpaca alp, EventPriority p, EventType t){
		alpaca = alp;
		priority = p;
		type = t;
	}
		
	/**
	 * @author Jonathan Cole
	 * Returns a string in the form
	 * "Alpaca [name] [information]"
	 */
	public String ToString(){
		StringBuilder s = new StringBuilder();
		s.append(alpaca.getName ());
		s.append(" ");
		//s.append("[");
		//s.append(alpaca.getTrackerID());
		//s.append("] ");
		s.append(GetInfoPostfix());
		return s.toString();
	}
	
	/**
	 * @author Jonathan Cole
	 * Returns a string representing the English form of the current EventType.
	 */
	public String GetInfoPostfix(){
		String outStr = "";
		switch(type){
			case TemperatureLow:
				outStr = "is below the lower temperature threshold";
				break;
			case TemperatureHigh:
				outStr = "is above the upper temperature threshold";
				break;
			case Dead:
				outStr = "has died :(";
				break;
			case Sick:
				outStr = "is sick :(";
				break;
			case Isolated:
				outStr = "is isolated :(";
				break;
			case OutOfBounds:
				outStr = "is out of bounds";
				break;
			case BatteryLow:
				outStr = "has a low battery level";
				break;
			case LowSatellites:
				outStr = "has too few satellites";
				break;
			case HeartRateLow:
				outStr = "has a low heart rate";
				break;
			case HeartRateHigh:
				outStr = "has a high heart rate";
				break;
		}
		return outStr;
	}
	
	@Override
	/**
	 * Override method to compare by value rather than by reference.
	 */
	public boolean equals(Object obj){
		if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof PacaAlert))
            return false;
        
        PacaAlert other = (PacaAlert) obj;
        return (other.readByUser == readByUser && other.alpaca == alpaca && other.priority == priority && other.type == type);
		
	}
}
