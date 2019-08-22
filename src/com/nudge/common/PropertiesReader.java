package com.nudge.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader 
{
	
	InputStream inputStream;
	
	public  Properties getPropertyFile()
	{
//        String propFileName = "local_config.properties";
          String propFileName = "server_config.properties";
		
		
		
		Properties properties=new Properties();
		
		//System.out.println("AAAAA-->"+getClass().getClassLoader());
		
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try
		{
		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		} 
	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return properties;
	}

}
