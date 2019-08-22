package com.nudge.common;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class URLConnectionReader 
{
	
	public static String getText(String url)throws Exception
	{
		StringBuilder response=new StringBuilder();
		
		 URL website = new URL(url);
		 
		 URLConnection connection = website.openConnection();
		 
		 //connection.setRequestMethod("POST");
		 
		 connection.setRequestProperty("Content-type", "text/xml");
		 connection.setRequestProperty("SOAPAction","http://tempuri.org/GetStudentDetails");


		 
		 
		 BufferedReader in = new BufferedReader(
                 new InputStreamReader(
                     connection.getInputStream()));
		 
		 String inputLine;
		 
		 while ((inputLine = in.readLine()) != null) 
	            response.append(inputLine);

	        in.close();

	        return response.toString();


		
		
	}
	
	public static String  callHttpsPost(String API_URL)
	{
		String response = "";
		
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {

					public java.security.cert.X509Certificate[] getAcceptedIssuers()
					{
						return null;
					}
					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
					{
						//No need to implement.
					}
					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
					{
						//No need to implement.
					}
				}
		};
		// Install the all-trusting trust manager
		try 
		{
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			URL url = new URL(API_URL);
			HttpsURLConnection httpsCon = (HttpsURLConnection) url.openConnection();
			httpsCon.setHostnameVerifier(new HostnameVerifier()
			{      
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					// TODO Auto-generated method stub
					return false;
				}
			});
			httpsCon.setDoInput(true);
			httpsCon.setDoOutput(true);
			httpsCon.connect();
			DataOutputStream cgiInput;
			// Send POST output.
			cgiInput = new DataOutputStream(httpsCon.getOutputStream());
//			String content = "firstname=" + URLEncoder.encode(firstname) 
//					+ "&lastname=" 
//					+ URLEncoder.encode(lastname)
//					+ "&password=" 
//					+ URLEncoder.encode(password);

//			cgiInput.writeBytes(keyPair);
			cgiInput.flush();
			cgiInput.close();
			BufferedReader cgiOutput = 
					new BufferedReader(new InputStreamReader(httpsCon.getInputStream()));

			StringBuilder inputStringBuilder = new StringBuilder();
			String line = new String();
			while ((line = cgiOutput.readLine()) != null) 
			{
				inputStringBuilder.append(line);
				line = cgiOutput.readLine();
			}
			cgiOutput.close();
			
			 response = inputStringBuilder.toString();
			
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return response;
	}
	
}
