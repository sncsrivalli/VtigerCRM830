package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods to perform actions on Properties file
 * @author QPS-Basavanagudi
 *
 */
public class PropertiesUtility {
	private Properties property;
	
	/**
	 * This method is used to initialize Properties file
	 * @param filePath
	 */
	public void propertiesInitialization(String filePath) {
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 property = new Properties();
		
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns the data from Properties file based on the key provided 
	 * @param key
	 * @return
	 */
	public String getDataFromProperties(String key) {
		return property.getProperty(key);
	}
	
	/**
	 * This method writes data to Properties file
	 * @param key
	 * @param value
	 * @param filePath
	 */
	public void writeToProperties(String key, String value, String filePath) {
		property.put(key, value);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			property.store(fos, "Updated Successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
