package com.nudge.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.bean.registration_bean;
import com.nudge.common.AESHelper;
import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.Util;
import com.nudge.model.LoginModel;
import com.nudge.model.registrationModel;

/**
 * Servlet implementation class LoginByFb
 */
@WebServlet("/LoginByFb")
public class LoginByFb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JSONObject user_details;
	JSONObject obj;
	Connection con;
	HttpServletResponse response;
     
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginByFb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        this.response = response;
		
		try {
			obj = new JSONObject();
			Connection	con = Config.getInstance().getConnection();
			
		
			String fb_id = Util.getParamValue(request, "fb_id");
			String password = Util.getParamValue(request, "password");
			String device_token = Util.getParamValue(request, "device_token");
			String device_type = Util.getParamValue(request, "device_type");

			
			if(fb_id.length()!=0 && password.length()!=0)
			{
				
            
				
				user_details = new LoginModel().CheckLoginByFb(con, fb_id, password);
				
				
				if(user_details.length()!=0)
				{
					 if(device_token.length()!=0 && device_token!=null && device_token!="" && device_type.length()!=0 && device_type!=null && device_type!="")
               	  {
            	    boolean add_device_token=new LoginModel().addDeviceToken(con,fb_id,password,device_token,device_type);
               	  }

                 	int login_status=new LoginModel().CheckLoginStatus(con, fb_id, password);
                     String login_status_res=""; 
                     
                     if(login_status==1)
                     {
                     	login_status_res="1";  
                     	}
                     else{  
                     		login_status_res="0"; 
                     		//call function to update login_status_res=1
                     		int add_response = new LoginModel().updateLoginStatus(con, fb_id, password);              
                     		}
                 	
                 	
						printOutput(Constant.StatusSuccess,login_status_res,Constant.MessageUL,user_details);
				//	printOutput(Constant.StatusSuccess,Constant.MessageUL,user_details);	

				}
				
				else{
					
					printOutputError(Constant.StatusFailFb,Constant.MessageUNR,user_details);
					
					
				}
			}
		}
		 catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				printOutputError(Constant.StatusFail, Constant.FAIL_RESPONSE,user_details);
			}
	}
	
	public void printOutput(String status,String loginstatus,String message,JSONObject userdetails)
	{
		try {
			obj.put(Constant.STATUS, status);
			obj.put(Constant.LOGINSTATUS, loginstatus);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.USERDETAILS,userdetails);

			//System.out.println("response--->"+obj.toString());

			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 

		catch (Exception e) {
			// TODO: handle exception
		}

		finally
		{
			Config.DB_colse();
		}


	}
	public void printOutputError(String status,String message,JSONObject userdetails)
	{
		try {
			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.USERDETAILS,userdetails);

			//System.out.println("response--->"+obj.toString());

			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 

		catch (Exception e) {
			// TODO: handle exception
		}

		finally
		{
			Config.DB_colse();
		}


	}
	

}
