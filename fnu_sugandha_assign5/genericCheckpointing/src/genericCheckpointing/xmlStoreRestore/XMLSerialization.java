package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import genericCheckpointing.util.MyLogger;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public class XMLSerialization implements SerStrategyI {
	
	@Override
	public void processInput(SerializableObject sObject) {
		Map<Integer, String> orderedAttr = new TreeMap<Integer, String>();
		
		String output = "";
		output = output+"<DPSerialization>\n";
		output = output + " <complexType xsi:type=\""+ sObject.getClass().toString().substring(6) +"\">\n";
		Field[] fields = sObject.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
		}

		Method[] methods = sObject.getClass().getDeclaredMethods();
		int currentOrder = -1;
		for (int i = 0; i < methods.length; i++) {
			if(methods[i].getName().indexOf("get") > -1 && methods[i].getName().indexOf("Order") == -1) {
				Object[] paramsOrder = new Object[0];
				String metNameOrder = methods[i].getName() + "Order";
				Class[] signature = new Class[0];
				Method metOrder = null;
				try {
					metOrder = sObject.getClass().getMethod(metNameOrder, signature);
				} catch (NoSuchMethodException | SecurityException e) {
					MyLogger.writeMessage(" NoSuchMethodException or SecurityException in XMLDeserialization method-processInput method",MyLogger.DebugLevel.ERROR );
					e.printStackTrace();
					System.exit(1);
				}
				try {
					currentOrder = Integer.parseInt(metOrder.invoke(sObject, paramsOrder).toString());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					MyLogger.writeMessage(" IllegalAccessException or IllegalArgumentException or InvocationTargetException in XMLDeserialization method-processInput method",MyLogger.DebugLevel.ERROR );
					e.printStackTrace();
					System.exit(1);
				}
				
				if (currentOrder == -1) {
					continue;
				}
				
				String tempOutput = "";
				String returntype = methods[i].getReturnType().toString();
				if (returntype.indexOf(".") > -1) {
					returntype = returntype.substring(methods[i].getReturnType().toString().lastIndexOf(".") + 1).toLowerCase();
				}
				tempOutput += "  <" + methods[i].getName().substring(3).substring(0, 1).toLowerCase() + methods[i].getName().substring(4) + " xsi:type=\"xsd:" + returntype + "\">";
				try {
					Object[] params = new Object[0];
					tempOutput += methods[i].invoke(sObject, params).toString();
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					MyLogger.writeMessage(" IllegalAccessException or IllegalArgumentException or InvocationTargetException in XMLDeserialization method-processInput method",MyLogger.DebugLevel.ERROR );
					e.printStackTrace();
					System.exit(1);
				}
				tempOutput += "</" + methods[i].getName().substring(3).substring(0, 1).toLowerCase() + methods[i].getName().substring(4) + ">\n";
				orderedAttr.put(currentOrder, tempOutput);
			}
			
		}
		for (Map.Entry<Integer, String> entry : orderedAttr.entrySet()) {
			output += entry.getValue();
		}
		
		output += " </complexType>\n</DPSerialization>";
		Results results = Results.getInstance();
		results.addResult(output);
		//System.out.println(output);
	}
	
	
}
