package genericCheckpointing.util;

public class MyAllTypesSecond extends MyAllTypesFirst{
	
	
	private double myDoubleT;
	private float myFloatT;
	private short myShortT;
	private char myCharT;
	private double myOtherDoubleT;
	private int myDoubleTOrder;
	private int myFloatTOrder;
	private int myShortTOrder;
	private int myCharTOrder;
	private int myOtherDoubleTOrder;
	private int orderCounter;
	
	public MyAllTypesSecond() {
		MyLogger.writeMessage("MyAllTypesSecond - constructor",MyLogger.DebugLevel.CONSTRUCTOR);
		myDoubleT = 0.0d;
		myFloatT = 0.0f;
		myShortT = 0;
		myCharT = '\u0000';
		myOtherDoubleT = 0.0d;
		myDoubleTOrder = -1;
		myFloatTOrder = -1;
		myShortTOrder = -1;
		myCharTOrder = -1;
		myOtherDoubleTOrder = -1;
		orderCounter = 0;
	}

	public double getMyDoubleT() {
		return myDoubleT;
	}

	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
		this.myDoubleTOrder = this.orderCounter;
		this.orderCounter++;
	}

	public float getMyFloatT() {
		return myFloatT;
	}

	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
		this.myFloatTOrder = this.orderCounter;
		this.orderCounter++;
	}

	public short getMyShortT() {
		return myShortT;
	}

	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
		this.myShortTOrder = this.orderCounter;
		this.orderCounter++;
	}

	public char getMyCharT() {
		return myCharT;
	}

	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
		this.myCharTOrder = this.orderCounter;
		this.orderCounter++;
		
	}

	public double getMyOtherDoubleT() {
		return myOtherDoubleT;
	}

	public void setMyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
		this.myOtherDoubleTOrder = this.orderCounter;
		this.orderCounter++;
	}
	
	//for orders
	public int getMyDoubleTOrder() {
		return myDoubleTOrder;
	}

	public int getMyFloatTOrder() {
		return myFloatTOrder;
	}

	public int getMyShortTOrder() {
		return myShortTOrder;
	}

	public int getMyCharTOrder() {
		return myCharTOrder;
	}

	public int getMyOtherDoubleTOrder() {
		return myOtherDoubleTOrder;
	}
	
	@Override
	public String toString() {
		return "MyAllTypesSecond [myDoubleT=" + myDoubleT + ", myFloatT=" + myFloatT + ", myShortT=" + myShortT
				+ ", myCharT=" + myCharT + ", myOtherDoubleT=" + myOtherDoubleT + "]";
	}

}
