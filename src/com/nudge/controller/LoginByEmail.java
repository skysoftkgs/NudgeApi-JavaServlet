package com.nudge.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
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
 * Servlet implementation class LoginByEmail
 */
@WebServlet("/LoginByEmail")
public class LoginByEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	JSONObject user_details;

	JSONObject obj;
	Connection con;
	HttpServletResponse response;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginByEmail() {
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


		try {
			obj = new JSONObject();
			user_details =  new JSONObject();
			Connection	con = Config.getInstance().getConnection();


			String email = Util.getParamValue(request, "email");
			String password = Util.getParamValue(request, "password");
			String device_token = Util.getParamValue(request, "device_token");
			String device_type = Util.getParamValue(request, "device_type");

		//	System.out.println("email--->"+email);
			//System.out.println("password--->"+password);
			//System.out.println("device_token=="+device_token);
			

			/*System.out.println("nudge *************************");
			Enumeration headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
			  String headerName = (String)headerNames.nextElement();
			  System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
			}
			 */



			if(email.length()!=0 && password.length()!=0)
			{

				/*LoginModel obj_model = new LoginModel();

				boolean add_response = obj_model.CheckLoginByEmail(con, email, password);*/

				user_details = new LoginModel().CheckLoginByEmail(con, email, password);

				if(user_details.length()!=0 )
				{

					int email_verfy = new LoginModel().CheckEmailVerify(con, email, password);
					
                  if(email_verfy==1)
					{
                	  
                	  if(device_token.length()!=0 && device_token!=null && device_token!="" && device_type.length()!=0 && device_type!=null && device_type!="")
                	  {
                	    boolean add_device_token=new LoginModel().addDeviceToken(con,email,password,device_token,device_type);
                	  }
                	  
                	  
                    	int login_status=new LoginModel().CheckLoginStatus(con, email, password);
                        String login_status_res=""; 
                        
                        if(login_status==1)
                        {
                        	login_status_res="1";  
                        	}
                        else{  
                        		login_status_res="0"; 
                        		//call function to update login_status_res=1
                        		int add_response = new LoginModel().updateLoginStatus(con, email, password);                        		
                        		}
                    	
                    	
						printOutput(Constant.StatusSuccess,login_status_res,Constant.MessageUL,user_details);
					}
                  else
					{	
						printOutputError(Constant.StatusFail, Constant.EMAIL_NOTVERIFIED);
					}

				}

				else
				{	
					printOutput(Constant.StatusFail,"",Constant.MessageLNS,user_details);
				}
			}
			else
			{
				printOutput(Constant.StatusFail,"", Constant.INVALID_INPUTS,user_details);
			}
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			try {
				obj.put("Exception", e);

			} catch (Exception e2) {
				// TODO: handle exception
			}


			printOutput(Constant.StatusFail, "",Constant.FAIL_RESPONSE,user_details);
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

	public void printOutputError(String status,String message)
	{
		try {
			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);


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
