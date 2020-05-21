package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import genericCheckpointing.util.MyLogger;

public class XMLDeserialization implements DeSerStrategyI {

	
	@Override
	public Object processInput(String input) {
	
		Map<String, Class> types = new HashMap<String, Class>();
		types.put("int", int.class);
		types.put("integer", int.class);
		types.put("short", short.class);
		types.put("double", double.class);
		types.put("float", float.class);
		types.put("char", char.class);
		types.put("string", String.class);
		types.put("boolean", boolean.class);
		types.put("long", long.class);
		
		Map<String, Class> castType = new HashMap<String, Class>();
		castType.put("int", int.class);
		castType.put("integer", Integer.class);
		castType.put("short", Short.class);
		castType.put("double", Double.class);
		castType.put("float", Float.class);
		castType.put("char", char.class);
		castType.put("string", String.class);
		castType.put("boolean", Boolean.class);
		castType.put("long", Long.class);
		
		String lines[] = input.split("\\r?\\n");
		Object o = null;
		Class cls = null;
		for(int i = 0; i < lines.length; i++) {
			if(lines[i].indexOf("<complexType") > -1) {//get class name
				String className = lines[i].substring(lines[i].indexOf("\"")+1,lines[i].lastIndexOf("\">"));
				//System.out.println("className "+lines[i].substring(lines[i].indexOf("\"")+1,lines[i].lastIndexOf("\">")));
				try {
					cls = Class.forName(className);
				} catch (ClassNotFoundException e1) {
					MyLogger.writeMessage("Class not found exception in XMLDeserialization method-processInput method",MyLogger.DebugLevel.ERROR );
					e1.printStackTrace();
					System.exit(1);
				}
				try {
					o = cls.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					MyLogger.writeMessage("InstantiationException or IllegalAccessException in XMLDeserialization method-processInput method",MyLogger.DebugLevel.ERROR );
					e.printStackTrace();
					System.exit(1);
				}
				
			}else if(lines[i].indexOf("</complexType>") > -1) {// complex type end
				return o;
			}else {//get data members
				Method met = null;
				String dataMember = lines[i].substring(lines[i].indexOf("</")+2, lines[i].lastIndexOf(">"));
				String dataType = lines[i].substring(lines[i].indexOf("xsd:")+4, lines[i].lastIndexOf("\">"));
				String dataValue = lines[i].substring(lines[i].indexOf(">")+1, lines[i].lastIndexOf("<"));
				String methName = "set" + dataMember.substring(0, 1).toUpperCase() + dataMember.substring(1);
				Class[] signature = new Class[1];
				signature[0] = types.get(dataType);
				try {
					met = cls.getMethod(methName, signature);
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					MyLogger.writeMessage("NoSuchMethodException or SecurityException in XMLDeserialization method-processInput method",MyLogger.DebugLevel.ERROR );
					e.printStackTrace();
					System.exit(1);
				}
				
				Object[] params = new Object[1];
				params[0] = myParse(dataValue, castType.get(dataType), dataType);
				try {
					met.invoke(o, params);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					MyLogger.writeMessage("IllegalAccessException or IllegalArgumentException or InvocationTargetException in XMLDeserialization method-processInput method",MyLogger.DebugLevel.ERROR );
					e.printStackTrace();
					System.exit(1);
				}	
			}
		}
		return o;	
	}
	
	public <T> T myParse(String input, Class<T> typeclass, String datatype) {
		if(datatype.equals("int") || datatype.equals("integer")) {
			return (T)Integer.valueOf(input);
		} else if(datatype.equals("double")) {
			return (T)Double.valueOf(input);
		} else if(datatype.equals("float")) {
			return (T)Float.valueOf(input);
		} else if (datatype.equals("short")) {
			return (T)Short.valueOf(input);
		} else if(datatype.equals("boolean")) {
			return (T)Boolean.valueOf(input);
		} else if (datatype.equals("char")) {
			return (T)Character.valueOf(input.charAt(0));
		} else if (datatype.equals("long")) {
			return (T)Long.valueOf(input);
		} else {
			return (T)input;
		}
	}

}
