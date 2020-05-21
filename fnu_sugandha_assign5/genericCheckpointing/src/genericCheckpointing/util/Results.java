package genericCheckpointing.util;

import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;

public class Results implements StdoutDisplayInterface,FileDisplayInterface {

	private String output;
	private BufferedWriter bw;
	private static Results INSTANCE;
	
	private Results() {
		MyLogger.writeMessage("Results - constructor",MyLogger.DebugLevel.CONSTRUCTOR);
		output = "";
	}
	
	public static Results getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Results();
        }
        return INSTANCE;
    }
	
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public BufferedWriter getBw() {
		return bw;
	}

	public void setBw(BufferedWriter bw) {
		this.bw = bw;
	}
	
	public void addResult(String messagein) {
		output = output + messagein;
	}
	
	@Override
	public void displayStdoutResults() {
		// TODO Auto-generated method stub
		System.out.println(output);
	}
	
	@Override
	public void displayResults() {
		// TODO Auto-generated method stub
		try {
			bw.write(output);
			//bw.newLine(); 
	      } catch (IOException e) {
	    	  MyLogger.writeMessage("IO exception in Results-displayResults method",MyLogger.DebugLevel.ERROR );
			  e.printStackTrace();
			  System.exit(1);
	      }	
	}
}
