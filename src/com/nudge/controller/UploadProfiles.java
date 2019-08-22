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
import com.nudge.model.LoginModel;
import com.nudge.model.UploadProfileModel;

/**
 * Servlet implementation class UploadProfiles
 */
@WebServlet("/UploadProfiles")
public class UploadProfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	JSONObject obj;
	
	HttpServletResponse response;
	
	String event="";
	


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadProfiles() {
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
				JSONArray jArray = jObject.getJSONArray("profiles");
				
				UploadProfileModel model_obj=new UploadProfileModel();
				boolean checkresponse=model_obj.checkUserId(con,user_id);
				
				if(checkresponse)
				{

				for (int i = 0; i < jArray.length(); i++)
				{
					JSONObject explrObject = jArray.getJSONObject(i);

					String name = explrObject.getString("name");
					String last_name = explrObject.getString("last_name");
					String relationship = explrObject.getString("relationship");
					String gender = explrObject.getString("gender");
					String dob = explrObject.getString("dob");
					String contact_no = explrObject.getString("contact_no");
					String type = explrObject.getString("type");
					String fb_id = explrObject.getString("fb_id");
					String image = explrObject.getString("image");
					
					

					if(type.equals("fb"))
					{
						boolean check_fb_id = new UploadProfileModel().CheckFbId(con, user_id, fb_id);

					/*	if(check_fb_id == false)
						{*/
							UploadProfileBean bean_obj = new UploadProfileBean();

							bean_obj.setUser_id(user_id);
							bean_obj.setName(name);
							bean_obj.setLast_name(last_name);
							bean_obj.setRelationship(relationship);
							bean_obj.setGender(gender);
							bean_obj.setDob(dob);
							bean_obj.setContact_no(contact_no);
							bean_obj.setFb_id(fb_id);
							bean_obj.setImage(image);
							bean_obj.setUser_id(user_id);
							bean_obj.setType(type);
							bean_obj.setBudget("");
							bean_obj.setLocation("");
							long seconds1=System.currentTimeMillis();
							bean_obj.setEntry_date(String.valueOf(seconds1));

							//UploadProfileModel model_obj = new UploadProfileModel();
							
							if(check_fb_id)
							{
								int add_response = model_obj.UpdateProfileMultiple(con, bean_obj);
								
								System.out.println("add_response fb==="+add_response);
								System.out.println("dob fb==="+dob);
								
								if(add_response!=0 && dob!=null && !dob.isEmpty())
								{
								//String dobToTimestamp=Util.DateToMillisecondNew(dob);
								
								//timestamp to current year or 1+ year conversion
								long nudge_timestamp=DateEstimation.getFinalTimestamp(Long.parseLong(dob));   
								
								// To convert timestamp to date
								String timestampToDate= Util.MillisecondToDateNew(dob);
								
								// To convert timestamp to date
								String nudgeDateTimestampToDate= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
								
								
								boolean nudge_response = model_obj.UpdateDobAsEventInUpload(con, user_id,add_response,event,timestampToDate,dob,nudge_timestamp,nudgeDateTimestampToDate);
							}
							}
							else
							{

							int add_response = model_obj.UploadProfileMultiple(con, bean_obj);	
							
							System.out.println("add_response fb insert==="+add_response);
							System.out.println("dob fb insert==="+dob);
							
							
							if(add_response!=0 && dob!=null && !dob.isEmpty())
							{
							//String dobToTimestamp=Util.DateToMillisecondNew(dob);
							
							//timestamp to current year or 1+ year conversion
							long nudge_timestamp=DateEstimation.getFinalTimestamp(Long.parseLong(dob));   
							
							// To convert timestamp to date
							String timestampToDate= Util.MillisecondToDateNew(dob);
							
							// To convert timestamp to date
							String nudgeDateTimestampToDate= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
							
							
							boolean nudge_response = model_obj.AddDobAsEvent(con, user_id,add_response,event,timestampToDate,dob,nudge_timestamp,nudgeDateTimestampToDate);
						}
							}
							
						
							
					/*	}*/
							
						
					}
					else if(type.equals("contact_no"))
					{
						boolean check_contact = new UploadProfileModel().CheckContactNo(con, user_id, contact_no);

						/*if(check_contact == false)
						{
						*/	UploadProfileBean bean_obj = new UploadProfileBean();

							bean_obj.setUser_id(user_id);
							bean_obj.setName(name);
							bean_obj.setRelationship(relationship);
							bean_obj.setGender(gender);
							bean_obj.setDob(dob);
							bean_obj.setContact_no(contact_no);
							bean_obj.setFb_id(fb_id);
							bean_obj.setImage(image);
							bean_obj.setUser_id(user_id);
							bean_obj.setType(type);
							bean_obj.setBudget("");
							bean_obj.setLocation("");
							long seconds1=System.currentTimeMillis();
							bean_obj.setEntry_date(String.valueOf(seconds1));
							bean_obj.setLast_name(last_name);

						//	UploadProfileModel model_obj = new UploadProfileModel();
							
						    if(check_contact)
						    {
						    	int add_response = model_obj.UpdateProfileMultiple(con, bean_obj);
						    	
						    	System.out.println("add_response contact==="+add_response);
								System.out.println("dob contact==="+dob);
								
								
						    	if(add_response!=0 && dob!=null && !dob.isEmpty())
								{
	                           // String dobToTimestamp=Util.DateToMillisecondNew(dob);
								
								//timestamp to current year or 1+ year conversion
								long nudge_timestamp=DateEstimation.getFinalTimestamp(Long.parseLong(dob));   
								
								// To convert timestamp to date
								String timestampToDate= Util.MillisecondToDateNew(dob);
								
								// To convert timestamp to date
								String nudgeDateTimestampToDate= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
								
								
								boolean nudge_response = model_obj.UpdateDobAsEventInUpload(con, user_id,add_response,event,timestampToDate,dob,nudge_timestamp,nudgeDateTimestampToDate);
								}
						    }
						    else
						    {
							int add_response = model_obj.UploadProfileMultiple(con, bean_obj);	
							
							if(add_response!=0 && dob!=null && !dob.isEmpty())
							{
                           // String dobToTimestamp=Util.DateToMillisecondNew(dob);
							
							//timestamp to current year or 1+ year conversion
							long nudge_timestamp=DateEstimation.getFinalTimestamp(Long.parseLong(dob));   
							
							// To convert timestamp to date
							String timestampToDate= Util.MillisecondToDateNew(dob);
							
							// To convert timestamp to date
							String nudgeDateTimestampToDate= Util.MillisecondToDateNew(String.valueOf(nudge_timestamp));
							
							
							boolean nudge_response = model_obj.AddDobAsEvent(con, user_id,add_response,event,timestampToDate,dob,nudge_timestamp,nudgeDateTimestampToDate);
							}
						    }
						/*}*/
							
						
					}
					else
					{
						api_response = 1;
						printOutput(Constant.StatusFail, Constant.INVALID_TYPE);
						break;
					}
				
				}
				if(api_response == 0)
				{
					printOutput(Constant.StatusSuccess, Constant.MessagePUPLOAD);
				}
				
				}
				
				else
				{
					printOutput(Constant.StatusFail, Constant.MessageUserIdExist);
				}
				
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


	public void printOutput(String status,String message)
	{
		try {


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


