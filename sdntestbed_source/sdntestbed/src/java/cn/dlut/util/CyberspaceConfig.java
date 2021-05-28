package cn.dlut.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CyberspaceConfig {
	private static String filePath = "cyberspacec_onfig.properties";
	public CyberspaceConfig(){}
	private static Properties props = new Properties(); 
	static{
		try {
			props.load(CyberspaceConfig.class.getResourceAsStream("/" + filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
