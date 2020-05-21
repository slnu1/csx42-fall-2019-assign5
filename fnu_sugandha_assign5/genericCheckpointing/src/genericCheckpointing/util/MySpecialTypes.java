package genericCheckpointing.util;

public class MySpecialTypes extends SerializableObject {
	
	private int myInt1;
	private int myInt2;
	private String myString1;
	private String myString2;
	private double myDoubleT1;
	private double myDoubleT2;
	private int myInt1Order;
	private int myInt2Order;
	private int myString1Order;
	private int myString2Order;
	private int myDoubleT1Order;
	private int myDoubleT2Order;
	private int orderCounter;
	
	public MySpecialTypes() {
		MyLogger.writeMessage("MySpecialTypes - constructor",MyLogger.DebugLevel.CONSTRUCTOR);
		myInt1 = 0;
		myInt2 = 0;
		myString1 = "";
		myString2 = "";
		myDoubleT1 = 0.0;
		myDoubleT2 = 0.0;
		myInt1Order = -1;
		myInt2Order = -1;
		myString1Order = -1;
		myString2Order = -1;
		myDoubleT1Order = -1;
		myDoubleT2Order = -1;
		orderCounter = 0;
	}

	public int getMyInt1() {
		return myInt1;
	}

	public void setMyInt1(int myInt1) {
		this.myInt1 = myInt1;
		this.myInt1Order = this.orderCounter;
		this.orderCounter++;
	}

	public int getMyInt2() {
		return myInt2;
	}

	public void setMyInt2(int myInt2) {
		this.myInt2 = myInt2;
		this.myInt2Order = this.orderCounter;
		this.orderCounter++;
	}

	public String getMyString1() {
		return myString1;
	}

	public void setMyString1(String myString1) {
		this.myString1 = myString1;
		this.myString1Order = this.orderCounter;
		this.orderCounter++;
	}

	public String getMyString2() {
		return myString2;
	}

	public void setMyString2(String myString2) {
		this.myString2 = myString2;
		this.myString2Order = this.orderCounter;
		this.orderCounter++;
	}

	public double getMyDoubleT1() {
		return myDoubleT1;
	}

	public void setMyDoubleT1(double myDoubleT1) {
		this.myDoubleT1 = myDoubleT1;
		this.myDoubleT1Order = this.orderCounter;
		this.orderCounter++;
	}

	public double getMyDoubleT2() {
		return myDoubleT2;
	}

	public void setMyDoubleT2(double myDoubleT2) {
		this.myDoubleT2 = myDoubleT2;
		this.myDoubleT2Order = this.orderCounter;
		this.orderCounter++;
	}
	
	
	//for orders
	public int getMyInt1Order() {
		return myInt1Order;
	}

	public int getMyInt2Order() {
		return myInt2Order;
	}

	public int getMyString1Order() {
		return myString1Order;
	}

	public int getMyString2Order() {
		return myString2Order;
	}

	public int getMyDoubleT1Order() {
		return myDoubleT1Order;
	}

	public int getMyDoubleT2Order() {
		return myDoubleT2Order;
	}
	
	@Override
	public String toString() {
		return "MySpecialTypes [myInt1=" + myInt1 + ", myInt2=" + myInt2 + ", myString1=" + myString1 + ", myString2="
				+ myString2 + ", myDoubleT1=" + myDoubleT1 + ", myDoubleT2=" + myDoubleT2 + "]";
	}

}
