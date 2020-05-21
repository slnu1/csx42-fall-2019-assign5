package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject {
	
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;
	private int myOtherInt;
	private long myOtherLong;
	private int myIntOrder;
	private int myLongOrder;
	private int myStringOrder;
	private int myBoolOrder;
	private int myOtherIntOrder;
	private int myOtherLongOrder;
	private int orderCounter;
	
	public MyAllTypesFirst() {
		MyLogger.writeMessage("MyAllTypesFirst - constructor",MyLogger.DebugLevel.CONSTRUCTOR);
		myInt = 0;
		myLong = 0;
		myString = "";
		myBool = false;
		myOtherInt = 0;
		myOtherLong = 0L;	
		myIntOrder = -1;
		myLongOrder = -1;
		myStringOrder = -1;
		myBoolOrder = -1;
		myOtherIntOrder = -1;
		myOtherLongOrder = -1;
		orderCounter = 0;
	}


	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
		this.myIntOrder = this.orderCounter;
		this.orderCounter++;
	}

	public long getMyLong() {
		return myLong;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
		this.myLongOrder = this.orderCounter;
		this.orderCounter++;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
		this.myStringOrder = this.orderCounter;
		this.orderCounter++;
	}

	public boolean getMyBool() {
		return myBool;
	}

	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
		this.myBoolOrder = this.orderCounter;
		this.orderCounter++;
	}

	public int getMyOtherInt() {
		return myOtherInt;
	}

	public void setMyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
		this.myOtherIntOrder = this.orderCounter;
		this.orderCounter++;
	}

	public long getMyOtherLong() {
		return myOtherLong;
	}

	public void setMyOtherLong(long myOtherLong) {
		this.myOtherLong = myOtherLong;
		this.myOtherLongOrder = this.orderCounter;
		this.orderCounter++;
	}

	//for orders
	public int getMyIntOrder() {
		return myIntOrder;
	}


	public int getMyLongOrder() {
		return myLongOrder;
	}


	public int getMyStringOrder() {
		return myStringOrder;
	}


	public int getMyBoolOrder() {
		return myBoolOrder;
	}


	public int getMyOtherIntOrder() {
		return myOtherIntOrder;
	}


	public int getMyOtherLongOrder() {
		return myOtherLongOrder;
	}
	
	@Override
	public String toString() {
		return "MyAllTypesFirst [myInt=" + myInt + ", myLong=" + myLong + ", myString=" + myString + ", myBool="
				+ myBool + ", myOtherInt=" + myOtherInt + ", myOtherLong=" + myOtherLong + "]";
	}

}
