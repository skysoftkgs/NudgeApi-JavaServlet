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
 * Servlet implementation class RemoveUserAccount
 */
@WebServlet("/RemoveUserAccount")
public class RemoveUserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Connection con;
	HttpServletResponse response;
	JSONObject obj;
	
	String user_id="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveUserAccount() {
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
		obj=new JSONObject();
		con = Config.getInstance().getConnection();
		
		user_id=Util.getParamValue(request, "user_id");
		if(user_id.length()!=0)
		{
		registrationModel obj=new registrationModel();
		boolean delete_response=obj.deleteUser(con,user_id);
		
		if(delete_response)
		{
			printOutput(Constant.StatusSuccess,Constant.USER_DELETE_SUCCESS);
		}
		else
		{
			printOutput(Constant.StatusFail,Constant.USER_DELETE_FAIL);
		}
		}
		else
		{
			printOutput(Constant.StatusFail,Constant.INVALID_INPUTS);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			printOutput(Constant.StatusFail,Constant.FAIL_RESPONSE);
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
