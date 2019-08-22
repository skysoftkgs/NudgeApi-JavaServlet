package com.nudge.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.bean.UploadProfileBean;
import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.DateEstimation;
import com.nudge.common.Util;
import com.nudge.model.UploadProfileModel;

/**
 * Servlet implementation class EventDate
 */
@WebServlet("/EventDateNew")
public class EventDateNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	HttpServletResponse response;
	JSONObject obj;
	String event_list="";
	String date_list="";
	String custom_name;
	String notification_time="";
	String not_date="";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventDateNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.response = response;

		try 
		{
			int api_response  = 0;
			obj= new JSONObject();


			String body = Util.getParamValue(request, "body");


			con = Config.getInstance().getConnection();

			if(body.length()!=0)
			{	
				JSONObject jObject = new JSONObject(body);

				String user_id = jObject.getString("user_id");
				System.out.println("user_id==="+user_id);
				String friend_id = jObject.getString("f_id");
				int notify = jObject.getInt("notify");
				int notify_days = jObject.getInt("notify_days");
				JSONArray jArray = jObject.getJSONArray("events");
				//String event = jObject.getString("event");
				//String date = jObject.getString("date");
				
				
				System.out.println("notify==="+notify);
				System.out.println("notifydays==="+notify_days);
				
				int j=0;

			     for (int i = 0; i < jArray.length(); i++)
				{
					JSONObject explrObject = jArray.getJSONObject(i);

					String event = explrObject.getString("event");
					String date = explrObject.getString("date");
					//for custom_name id = 100
					String custom_name = explrObject.getString("custom_name");
				
					
				//	event_list = event_list+","+event;
			   //   date_list  = date_list+","+date;
					
					//timestamp to current year or 1+ year conversion
					long nudge_timestamp=DateEstimation.getFinalTimestamp(Long.parseLong(date)); 
					System.out.println("nudge_timestamp=="+nudge_timestamp);
					
					// To convert timestamp to date
					String timestampToDate= Util.MillisecondToDateNew(date);
					
					// To convert timestamp to date
					String nudgeDateTimestampToDate= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
					
					UploadProfileModel model_obj = new UploadProfileModel();
					

					if(notify == 1)
					{
//						notification_time = model_obj.addNotification(con,nudgeDateTimestampToDate,notify_days);
						System.out.println("nudgeDateTimestampToDate--->"+nudgeDateTimestampToDate);
					//	notification_time = GetNofiticationTime(String.valueOf(nudge_timestamp),notify_days);
						notification_time = GetNofiticationTime(String.valueOf(date),notify_days);
						System.out.println("notification_time--->"+Util.MillisecondToDateNew1(String.valueOf(Integer.parseInt(notification_time)*1000)));
					
					
					    not_date= Util.MillisecondToDateNew(notification_time);
						System.out.println("not_time=="+not_date);
						
						
						//convert into yymmdd
						
						String arr[]=not_date.split("/"); 
						
						for(int k=0;k<arr.length;k++)
						{
							String year=arr[2];
							String month=arr[1];
							String date1=arr[0];
							
							not_date=year+"/"+month+"/"+date1;
							
							System.out.println("not_date=="+not_date);
						
						}
							
					}
					
					boolean event_exist=false;
					
					if(event.equalsIgnoreCase("1000"))
					{
			//		event_exist=model_obj.CheckCustomEvent(con, friend_id,user_id,custom_name);
					}
					else
					{
					event_exist=model_obj.CheckDateEvent(con, friend_id, event,user_id,date);
					}
					boolean add_response=false;
					
					if(event_exist)
					{
						
/*						add_response = model_obj.UpdateDateEvent(con,friend_id,event,date,user_id,nudge_timestamp,timestampToDate,nudgeDateTimestampToDate,custom_name,notify,notify_days,notification_time,not_date);
*/					}
					else
					{
/*					 add_response = model_obj.AddDateEvent(con,friend_id,event,date,user_id,nudge_timestamp,timestampToDate,nudgeDateTimestampToDate,custom_name,notify,notify_days,notification_time,not_date);
*/					}
					//System.out.println("update_response========"+add_response);
					
					if(add_response)
					{
						j++;
						
					}
					else
					{
					
						break;
					}
					}

			   
			     if(j==jArray.length())
			     {
			    	 printOutput(Constant.StatusSuccess, Constant.DEADD);
			     }
			     else
			     {
			    	 printOutput(Constant.StatusFail, Constant.DENOTADD);
			     }
				
				
				/*//event_list = event_list.replaceFirst(",", "");
				//date_list  = date_list.replaceFirst(",", "");
				UploadProfileBean bean_obj = new UploadProfileBean();

				bean_obj.setUser_id(user_id);
				bean_obj.setFriend_id(friend_id);
				bean_obj.setEvent(event);
				bean_obj.setDate(date);
				
				UploadProfileModel model_obj = new UploadProfileModel();

				//boolean update_response = model_obj.UpdateDateEvent(con, bean_obj);
				//event_list="";
				//date_list="";
				if(update_response)
				{
					printOutput(Constant.StatusSuccess, Constant.DEADD);
				}
				else
				{
					printOutput(Constant.StatusFail, Constant.DENOTADD);
				}*/

			}

			else
			{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS);
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail, Constant.FAIL_RESPONSE);
		}
	}


	private String GetNofiticationTime(String nudge_timestamp,int notify_days) 
	{
		String timestamp = "";
		
		long long_timestamp = Long.parseLong(nudge_timestamp);
				
		long day_time = notify_days*24*60*60;
		
		long diff = long_timestamp - day_time;
		
		timestamp = String.valueOf(diff);
		System.out.println("time===="+timestamp);
		
		return timestamp;
	}

	public void printOutput(String status,String message)
	{
		try {

			 event_list="";
			 date_list="";
			 custom_name="";
			 notification_time="";


			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);


			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 

		catch (Exception e) {
			// TODO: handle exception
		}
	}

	}


