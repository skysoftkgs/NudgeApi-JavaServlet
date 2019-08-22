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
import com.nudge.model.FavoritesModel;

/**
 * Servlet implementation class getFavoriteByProdId
 */
@WebServlet("/getFavoriteByProdId")
public class getFavoriteByProdId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	HttpServletResponse response;
	JSONObject obj;
	JSONArray favoriteDetails;
       
	String user_id="";
	String prod_id="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFavoriteByProdId() {
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
			favoriteDetails=new JSONArray();
			Connection	con = Config.getInstance().getConnection();
			
			user_id=request.getParameter("user_id");
			prod_id=request.getParameter("prod_id");
			
			if(user_id.length()!=0 && prod_id.length()!=0)
			{
				FavoritesModel obj_model=new FavoritesModel();
				
				favoriteDetails=obj_model.getFavoritesByProdId(con,user_id,prod_id);
				
				printOutput(Constant.StatusSuccess, Constant.FAVORITESFETCHED,favoriteDetails);
			}
			
			else
			{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS, favoriteDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
			printOutput(Constant.StatusFail, Constant.FAV_GET_FAIL,favoriteDetails);
		}
	}
	public void printOutput(String status,String message,JSONArray favoriteDetails)
	{
		try {
			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.FAVORITEDETAILS,favoriteDetails);
	

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
