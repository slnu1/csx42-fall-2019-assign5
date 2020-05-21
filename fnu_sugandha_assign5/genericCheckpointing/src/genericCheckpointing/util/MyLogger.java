package genericCheckpointing.util;

public class MyLogger {
	
	public static enum DebugLevel { 
		ERROR,
		OUTPUT,
		CONSTRUCTOR,
		NONE
    };
    
    private static DebugLevel debugLevel;
    
    public static void setDebugLevel (int levelIn) {
    	switch (levelIn) {
	    	case 2: debugLevel = DebugLevel.CONSTRUCTOR; break;
	    	case 1: debugLevel = DebugLevel.OUTPUT; break;
	    	case 0: debugLevel = DebugLevel.ERROR; break;
	    	default: debugLevel = DebugLevel.NONE; break;
    	}
    }
    
    public static void setDebugLevel (DebugLevel levelIn) {
    	debugLevel = levelIn;
    }

    public static void writeMessage (String message, DebugLevel levelIn) {
    	if (levelIn == debugLevel) {
    		System.out.println(message);
    	}
    }

    public String toString() {
    	return "The debug level has been set to the following " + debugLevel;
    }

}
