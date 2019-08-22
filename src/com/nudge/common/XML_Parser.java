package com.nudge.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;




import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class XML_Parser {
	
	
	public static String xml_parsing(String xml)
	{
		String response_tag,response_value,tag_response="";
		
		try
		{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db=factory.newDocumentBuilder();
		
		Document dom=db.parse(new InputSource(new StringReader(xml)));
		
		Element docE1=dom.getDocumentElement();
		
		response_tag=docE1.getTagName();
		
		response_value=docE1.getTextContent();
		
		if(response_tag.equals("result")  && response_value.equals("ok"))
		{
			//System.out.println("Add success start");
			
		}
		else
		{
			//System.out.println("Add Fail start");
		}
	
		return response_tag+"@-#"+response_value;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "fail";
		}
	}

}
