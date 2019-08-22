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
import com.nudge.model.UploadProfileModel;

/**
 * Servlet implementation class GetCategory
 */
@WebServlet("/GetCategory")
public class GetCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	HttpServletResponse response;
	JSONObject obj;
	
	JSONObject userobj;
	JSONArray category_details;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCategory() {
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
			
			con = Config.getInstance().getConnection();
			
			obj = new JSONObject();
			category_details =  new JSONArray();	
			category_details = new UploadProfileModel().GetCategory(con);
		
			printOutput(Constant.StatusSuccess,category_details);
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			printOutput(Constant.StatusFail,category_details);
		}
		
	}
	public void printOutput(String status,JSONArray category_details)
	{
		try {
			obj.put(Constant.STATUS, status);
			//obj.put(Constant.MESSAGE,message);
			obj.put(Constant.CATEGORYDETAILS,category_details);

			//System.out.println("response--->"+obj.toString());

			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		
		}

		finally
		{
			Config.DB_colse();
		}
	}

}
