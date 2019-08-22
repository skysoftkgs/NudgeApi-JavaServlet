package com.nudge.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.BasicConfigurator;
import org.json.JSONException;
import org.json.JSONObject;

import com.nudge.bean.UploadProfileBean;
import com.nudge.model.UploadProfileModel;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;

public class PushNotifictionHelper {



	public final static String AUTH_KEY_FCM = "AIzaSyCVuHfom0gR9UAAaZ381HiRvxXSjY7JYQ0";

	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public static String sendPushNotification(String deviceToken,String title,String message, String data) {
		String result = "";
		try {


			URL url = new URL(API_URL_FCM);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);

			conn.setRequestProperty("Content-Type", "application/json");

			JSONObject json = new JSONObject();


			JSONObject notification = new JSONObject();
			notification.put("title", title); // Notification title
			notification.put("message",message ); // Notification Message
			notification.put("icon","appicon"); 

			json.put("notification", notification);
			json.put("to", deviceToken.trim());
			json.put("data", new JSONObject(data));
			
			
			notification.put("title", json.toString()); // Notification title
			

			System.out.println(json.toString());
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());

			/*    {
	        	   "to" : "e1w6hEbZn-8:APA91bEUIb2JewYCIiApsMu5JfI5Ak...",
	        	   "notification": {
	        	       "message": "Upcoming event",
	        	       "title": "Nudge",
	        	       "icon": "appicon"
	        	   },
	        	   "data" : {
	        	    "type" : "birthday",
	        	    "event" : "birthday in 10 days!Don't forget to get princy the perfect gift."
	        	  }
	        	}*/
			
			
			wr.write(json.toString());
			wr.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			//System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			result = "Message send successfully";
		} catch (Exception e) {
			e.printStackTrace();
			result = "Failed to send message";
		}
		System.out.println("GCM Notification is sent successfully");

		return result;

	}


	public String sendPushNotificationApple(String deviceToken,String title,String message, String body) {
		String result = "";
		BasicConfigurator.configure();
		try {
			PushNotificationPayload payload = PushNotificationPayload.complex();

			/*{
        "aps" : {
                     "alert"     :  "Title Of Push",
                     "sound"  :   "default",
                     "body     : {
                                      " notification type" : "Type",
                                      "Hotel Name"        : "Hotel Name"
                                      "General Message" : "Message"
                              }
         }
  }*/
			// For Room No 


			payload.addAlert(title+"/r/n"+message);
			payload.addBadge(1);
			payload.addSound("default");
			payload.addCustomDictionary("body", body);

			System.out.println(payload.toString());

			InputStream is = getClass().getClassLoader().getResourceAsStream("Certificates.p12");

			List < PushedNotification > NOTIFICATIONS = Push.payload(payload,is, "advantal", false, deviceToken);
			for (PushedNotification NOTIFICATION: NOTIFICATIONS) {
				if (NOTIFICATION.isSuccessful()) {
					// APPLE ACCEPTED THE NOTIFICATION AND SHOULD DELIVER IT 
					System.out.println("PUSH NOTIFICATION SENT SUCCESSFULLY TO: " +
							NOTIFICATION.getDevice().getToken());
					//  STILL NEED TO QUERY THE FEEDBACK SERVICE REGULARLY 
					result = "Message send successfully";
				} else {
					String INVALIDTOKEN = NOTIFICATION.getDevice().getToken();
					// ADD CODE HERE TO REMOVE INVALIDTOKEN FROM YOUR DATABASE 
					// FIND OUT MORE ABOUT WHAT THE PROBLEM WAS 
					Exception THEPROBLEM = NOTIFICATION.getException();
					THEPROBLEM.printStackTrace();
					//  IF THE PROBLEM WAS AN ERROR-RESPONSE PACKET RETURNED BY APPLE, GET IT 
					ResponsePacket THEERRORRESPONSE = NOTIFICATION.getResponse();
					if (THEERRORRESPONSE != null) {
						System.out.println(THEERRORRESPONSE.getMessage());
					}
				}
			}
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeystoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
			/*push logic*/
	
	public static void main(String args[]) {

		try { 

			Connection con;
			con = Config.getInstance().getConnection();
			//	System.out.println("connn--------------"+con);


			// current timestamp
			long time=System.currentTimeMillis();
			//System.out.println("time=="+time);


			//current date
			String currentdate=Util.MillisecondToDdMmYYYY(Long.toString(time));

			String arr[]=currentdate.split("/"); 

			for(int k=0;k<arr.length;k++)
			{
				String year=arr[2];
				String month=arr[1];
				String date1=arr[0];

				currentdate=year+"/"+month+"/"+date1;
				//System.out.println("currentdate=="+currentdate);

			}

			
			// current day of month
			/*Calendar cal = Calendar.getInstance();
			int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
			String dayOfMonthStr = String.valueOf(dayOfMonth);
			System.out.println("dayOfMonth=="+dayOfMonth);
			 */

			// current month
			/*Calendar rightNow = Calendar.getInstance();
			java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
			System.out.println("month=="+df1.format(rightNow.getTime()));
			 */

			List<UploadProfileBean> all_user= new ArrayList<UploadProfileBean>();
			UploadProfileModel model_obj = new UploadProfileModel();

			all_user =model_obj.getNotDate(con, currentdate);


			for (int i = 0; i < all_user.size(); i++)
			{
				UploadProfileBean obj_bean=all_user.get(i);
				String user_id=obj_bean.getUser_id();
				String friend_name=obj_bean.getName();
				String event_name=obj_bean.getEvent_name();
				String event = obj_bean.getEvent();
				String custom_name = obj_bean.getCustom_name();
				String notify_days=obj_bean.getNotify_days();
				String deviceToken = obj_bean.getDevice_token();
				String deviceType = obj_bean.getDevice_type();
				String repeat = obj_bean.getRepeat();
				String friend_id=obj_bean.getFriend_id();
				//String nudge_date = obj_bean.getNudge_date();
				String not_date=obj_bean.getDate();
				String org_date = obj_bean.getOriginal_date();
				String image= obj_bean.getImage();
				String image_url="http://shieldcrypt.com:8080/nudgeimages/"+image;
				
				String notArr[]=not_date.split("/"); 
				for(int k=0;k<notArr.length;k++)
				{
					
					String month=notArr[1];
					String date1=notArr[2];
					not_date=date1+"/"+month;
						
				}
				
				String orgArr[]=org_date.split("/"); 
				
				for(int k=0;k<orgArr.length;k++)
				{	
					String month=orgArr[1];
					String date1=orgArr[0];
					org_date=date1+"/"+month;
				}
				
				
				
				String title ="The Gifting App";
				String message = "Upcoming events";
				if(user_id!=null)
				{
				//	System.out.println("custom "+custom_name);
					JSONObject data= new JSONObject();
					if(event.equals("1000"))
					{
						data.put("event",custom_name +" in "+ notify_days  +" days! Don't forget to get " +friend_name +" the perfect gift.");
						data.put("type", "Occassion");		
						data.put("image", image_url);
					}
					else
					{
						data.put("event",event_name +" in "+ notify_days  +" days! Dont forget to get " +friend_name +" the perfect gift.");
						data.put("image", image_url);
						
					if(event_name.equalsIgnoreCase("birthday"))
					{
						 data.put("type", "Birthday");	
					}
					else
					{
						 data.put("type", "Occassion");	
					}
					}
					
					
					/*JSONObject obj = new JSONObject(""
					 		+ "{'to':'cas9-g_rt9w:APA91bFV4acRTcD1SvGp1IXo5tkE0E4d3v6SlB3TU2Y7_1E92sToU6M9B4BZ0cWlV4WXPkR_2qNYDVVKf0FLB6wyZ8_2lhpvyUUAEZ1-sn8U7SNkG7HxuMGOsiH31dzom6DSgcd7ZFin','data':{'event':'n_message','type':'occasion'},'notification':{'icon':'i am message','title':'i am title','message':'upcomming event'}}");
					 */

 
					if(deviceType.equalsIgnoreCase("android")){
						PushNotifictionHelper.sendPushNotification(deviceToken,title,message,data.toString());

					}if(deviceType.equalsIgnoreCase("iphone")){
						PushNotifictionHelper hp = new PushNotifictionHelper();
						hp.sendPushNotificationApple(deviceToken,title,message,data.toString());
					}
					
					if(!org_date.equalsIgnoreCase(not_date))
					{
					
						if(repeat.equals("1"))
						{	
							Date dt = new Date();
							Calendar c = Calendar.getInstance(); 
							c.setTime(dt); 
							c.add(Calendar.DATE, 1);
							dt = c.getTime();
							SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd" ); 
							String convertedDate=dateFormat.format(c.getTime());    
							//System.out.println("Date increase by one.."+convertedDate);
							
							String converted_ts=Util.DateToMillisecondYYYYMMDD(convertedDate);
						
							
							model_obj.updateNotDate(con,user_id,friend_id,event,custom_name,convertedDate,converted_ts,notify_days);
						}
					}
					else
					{
						model_obj.updateRepeat(con,user_id,friend_id,event,custom_name);

					}
					
					
				}
			}


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// PushNotifictionHelper h=new PushNotifictionHelper();
		//  h.sendPushNotificationApple("M","00623FAAC1088C6C15356579F506F98950CBC44AC0FC7CAC09034905BC6B27B8","test",obj.toString());	
	}

}
