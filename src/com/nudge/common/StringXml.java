package com.nudge.common;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;




public class StringXml 
{
	
	public static Document convert_xml(String request_body)
	{
		 Document document=null;
		try
		{
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			
			DocumentBuilder db=factory.newDocumentBuilder();
			
		   document=db.parse(new InputSource(new StringReader(request_body)));
		   
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			//System.out.println(ex.getMessage());
			
		}
		return document;
	}
	
	public void create_xml(Document doc, String xml_name)
	{
		
		try {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		
			Transformer transformer = transformerFactory.newTransformer();
		
		DOMSource domSource = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(new File("/"+xml_name));
		
		transformer.transform(domSource, streamResult);

		//System.out.println("Done creating XML File");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String convertDocumentToString(Document doc) 
	{
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
            
        } catch (TransformerException e) {
            e.printStackTrace();
        }
         
        return null;
    }

}
