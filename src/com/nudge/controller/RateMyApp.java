package com.nudge.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.Util;
import com.nudge.model.registrationModel;

/**
 * Servlet implementation class RateMyApp
 */
@WebServlet("/RateMyApp")
public class RateMyApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	Connection con;
	HttpServletResponse response;
	JSONObject obj;
	
	String user_id="";
	String push_not="";
	String email_not="";
	String feedback="";
	String rating="";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateMyApp() {
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
			Connection	con = Config.getInstance().getConnection();
			obj=new JSONObject();
			
			user_id=Util.getParamValue(request, "user_id");
			push_not=Util.getParamValue(request, "push_not");
			email_not=Util.getParamValue(request, "email_not");
			feedback=Util.getParamValue(request, "feedback");
			rating=Util.getParamValue(request, "rating");
			
			if(user_id.length()!=0 && push_not.length()!=0 && email_not.length()!=0)
			{
				registrationModel obj=new registrationModel();
				boolean add_response=obj.addRating(con,user_id,push_not,email_not,feedback,rating);
				
				if(add_response)
				{
					printOutput(Constant.StatusSuccess,Constant.DETAILS_ADDED );
				}
				else
				{
					printOutput(Constant.StatusFail,Constant.DETAILS_NOTADDED );
				}
			}
			else
			{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			printOutput(Constant.StatusFail,Constant.FAIL_RESPONSE );
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

		finally
		{
			Config.DB_colse();
		}


	}

}
