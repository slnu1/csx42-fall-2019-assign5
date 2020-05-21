package genericCheckpointing.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.MyLogger;
import genericCheckpointing.util.MySpecialTypes;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {

	// FIXME: read the value of checkpointFile from the command line
	public static void main(String[] args) {
		if (args.length != 4 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 4 argumnets.");
			System.exit(1);
		}

		if (args[0].equals("deserser")) {

		} else {
			System.err.println("Error: Please pass the correct mode.Correct mode is deserser");
			System.exit(1);
		}
		
		MyLogger.setDebugLevel(Integer.parseInt(args[3	]));
		ProxyCreator pc = new ProxyCreator();

		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		InvocationHandler storeRestoreHandler = new StoreRestoreHandler();

		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class },
				storeRestoreHandler);

		// FIXME: invoke a method on the handler instance to set the file name for
		// checkpointFile and open the file
		// ((StoreRestoreHandler) cpointRef).setFileName(args[1]);
		BufferedReader br = ((StoreRestoreHandler) storeRestoreHandler).openFile(args[1]);
		
		MyAllTypesFirst myFirst = null;
		MyAllTypesSecond mySecond = null;
		MySpecialTypes mySpecialT = null;
		SerializableObject myRecordRet;

		// read in a loop till the end of file is indicated
		String temp = null;
		String input = "";
		ArrayList<Object> al = new ArrayList<Object>();
		try {
			while ((temp = br.readLine()) != null) {
				temp = temp.trim();
				if (temp.isBlank() || temp.isEmpty()) {
					continue;
				}
				if (temp.indexOf("<complexType") > -1 || input.contains("<complexType")) {
					if (temp.indexOf("</complexType") > -1) {
						input = input + temp;
						myRecordRet = ((RestoreI) cpointRef).readObj(input);
						al.add(myRecordRet);
						input = "";
					} else {
						input = input + temp + System.lineSeparator();
					}
				}
			}
		} catch (IOException e) { // TODO Auto-generatedcatch block
			MyLogger.writeMessage("IO exception in Driver",MyLogger.DebugLevel.ERROR );
			e.printStackTrace();
			System.exit(1);
		}

		// FIXME: store myRecordRet in a data structure

		BufferedWriter bw = ((StoreRestoreHandler) storeRestoreHandler).createOutputFile(args[2]);
		Results results = Results.getInstance();
		// NUM_OF_OBJECTS is the size of the data structure in which the objects have been saved
		for (int i=0; i<al.size(); i++) {
			// use "instance of" to determine which of these methods should be called //
			//use this method for MyAllTypesFirst and MyAllTypesSecond.
			if(al.get(i) instanceof MyAllTypesFirst) {
				((StoreI)cpointRef).writeObj((MyAllTypesFirst)al.get(i), "XML");
			}else if (al.get(i) instanceof MyAllTypesSecond) {
				((StoreI)cpointRef).writeObj((MyAllTypesFirst)al.get(i), "XML");
			}else if (al.get(i) instanceof MySpecialTypes) {
				((StoreI)cpointRef).writeObj((MySpecialTypes)al.get(i), "XML");  
			}
			if (i != al.size() - 1) {
				results.addResult("\n");
			}
		}
		
		results.setBw(bw);
		results.displayResults();
		// FIXME: invoke a method on the handler to close the file (if it hasn't already
		// been closed)
		((StoreRestoreHandler) storeRestoreHandler).closeFile(br);
		((StoreRestoreHandler) storeRestoreHandler).closeOutputFile(bw);
	}
}
