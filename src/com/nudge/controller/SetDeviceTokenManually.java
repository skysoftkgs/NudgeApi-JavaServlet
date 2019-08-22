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
import com.nudge.model.LoginModel;

/**
 * Servlet implementation class SetDeviceTokenManually
 */
@WebServlet("/SetDeviceTokenManually")
public class SetDeviceTokenManually extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	HttpServletResponse response;
	JSONObject obj;



	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetDeviceTokenManually() {
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
			Connection	con = Config.getInstance().getConnection();

			String unique_id=Util.getParamValue(request, "unique_id");
			String password=Util.getParamValue(request, "password");
			String device_token=Util.getParamValue(request, "device_token");
			String device_type=Util.getParamValue(request, "device_type");

			if(unique_id.length()!=0 && password.length()!=0 && device_token.length()!=0 && device_token!=null && device_token!="" && device_type!=null && device_type!="")
			{
			
				boolean add_device_token=new LoginModel().addDeviceToken(con,unique_id,password,device_token,device_type);

				if(add_device_token)
				{
					printOutput(Constant.StatusSuccess, Constant.MsgAddDevice);
				}
				else
				{
					printOutput(Constant.StatusFail, Constant.MsgNotAddDevice);

				}
		
		}
			else{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS);
			}
			}
		catch (Exception e) {
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
			//	obj.put(Constant.USERDETAILS,user_details);


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
