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

import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.Util;
import com.nudge.model.registrationModel;

/**
 * Servlet implementation class UserDetails
 */
@WebServlet("/UserDetails")
public class UserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JSONObject obj;
	JSONObject userdetails;
	Connection con;
	HttpServletResponse response;

	String user_id="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetails() {
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
		
		this.response=response;
		
		try {
			obj = new JSONObject();
			userdetails =  new JSONObject();
			Connection	con = Config.getInstance().getConnection();


			String user_id = Util.getParamValue(request, "user_id");
			
			registrationModel obj_model=new registrationModel();
			
			if(user_id.length()!=0)
			{
				userdetails = obj_model.GetUserDetails(con,user_id);
				
				printOutput(Constant.StatusSuccess,Constant.MessageUDF,userdetails);
			}
			else
			{
				printOutput(Constant.StatusFail,Constant.INVALID_INPUTS,userdetails);

			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail,Constant.FAIL_RESPONSE,userdetails);

		}
	}
	
	public void printOutput(String status,String message,JSONObject user_details)
	{
		try {
			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.USERDETAILS,userdetails);
		//	obj.put(Constant.PERSONNADETAILS,personna_details);

			

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
