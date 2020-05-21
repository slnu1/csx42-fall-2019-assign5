package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.MyLogger;
import genericCheckpointing.util.SerializableObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StoreRestoreHandler implements InvocationHandler {
	
	private String inputFileName;
	private String outputFileName;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// if the method is write
	       // if the wireFormat is XML
	           //  call serializeData(args[0], new XMLSerializationStrategy());
		if (method.getName().equals("writeObj")) {
			if(args[1].equals("XML")) {
				serializeData((SerializableObject)args[0],new XMLSerialization());
			}
		}
	    // if statements to check if it is the read method so that
	    // deserialization can be done ...
		if (method.getName().equals("readObj")) {
			return deSerializeData((String) args[0] ,new XMLDeserialization());
		}
		return null;
	}
	
	public void serializeData(SerializableObject sObject, SerStrategyI sStrategy) {
        sStrategy.processInput(sObject);
	}
	
	public Object deSerializeData(String dInput, DeSerStrategyI dStrategy) {
        return dStrategy.processInput(dInput);
	}
	
	/**
	 * Open input file
	 * @param fileNameIn String
	 * @return BufferedReader*/
	public BufferedReader openFile(String fileNameIn) {
		inputFileName = fileNameIn;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(inputFileName);
			br = new BufferedReader(fr);
		}catch(FileNotFoundException ex) {
			MyLogger.writeMessage("FileNotFoundException exception in StoreRestoreHandler-openFile method",MyLogger.DebugLevel.ERROR );
			System.exit(1);
		}finally {
		}
		return br;
	}
	
	/**
	 * Close Input file
	 * @param br BufferedReader
	 * @return void */
	public void closeFile(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			MyLogger.writeMessage("IOException exception in StoreRestoreHandler-closeFile method",MyLogger.DebugLevel.ERROR );
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public BufferedWriter createOutputFile(String fileNameIn) {
		outputFileName = fileNameIn;
		BufferedWriter bw = null;
		try {
			 File file = new File(outputFileName);
	         if (!file.exists()) {
	            file.createNewFile();
	         } 
	         FileWriter fw = new FileWriter(file.getAbsoluteFile());
		     bw = new BufferedWriter(fw);
		} catch (IOException e) {
			 MyLogger.writeMessage("IOException exception in StoreRestoreHandler-createOutputFile method",MyLogger.DebugLevel.ERROR );
			 e.printStackTrace();
			 System.exit(1);
		}
		return bw;
	}
	
	
	public void closeOutputFile(BufferedWriter bw) {
		//System.out.println("close file");
		try {
			bw.close();
		} catch (IOException e) {
			MyLogger.writeMessage("IOException exception in StoreRestoreHandler-closeOutputFile method",MyLogger.DebugLevel.ERROR );
			e.printStackTrace();
			System.exit(1);
		}
		
	}
}
