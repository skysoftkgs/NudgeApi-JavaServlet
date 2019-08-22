package com.nudge.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.bean.FavoritesBean;
import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.PropertiesReader;
import com.nudge.model.FavoritesModel;

/**
 * Servlet implementation class AddFavorites
 */
@WebServlet("/AddFavorites")
public class AddFavorites extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	HttpServletResponse response;
	JSONObject obj;
   
	String user_id="";
	String friend_id="";
	String prod_name="";
	String prod_id="";
	String prod_image="";
	String price="";
	String slug="";
	String description="";
	String short_description="";
	String regular_price="";
	String sale_price="";
	String total_sales="";
	String external_url="";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFavorites() {
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
			boolean add_response = false;
			obj=new JSONObject();
			Connection	con = Config.getInstance().getConnection();
			
			
			user_id=request.getParameter("user_id");
			friend_id=request.getParameter("friend_id");
			prod_id=request.getParameter("prod_id");
			prod_name=request.getParameter("prod_name");
			price=request.getParameter("price");
			prod_image=request.getParameter("prod_image");
			slug=request.getParameter("slug");
			description=request.getParameter("description");
			short_description=request.getParameter("short_description");
			regular_price=request.getParameter("regular_price");
			sale_price=request.getParameter("sale_price");
			total_sales=request.getParameter("total_sales");
			external_url=request.getParameter("external_url");
		
			
			if(user_id.length()!=0 && friend_id.length()!=0 && prod_id.length()!=0)
			{
				
				FavoritesModel obj_model= new FavoritesModel();
				FavoritesBean obj_bean=new FavoritesBean();
				
				obj_bean.setUser_id(user_id);
				obj_bean.setProd_id(prod_id);
				obj_bean.setProd_name(prod_name);
				obj_bean.setProd_image(prod_image);
				obj_bean.setPrice(price);
				obj_bean.setSlug(slug);
				obj_bean.setDescription(description);
				obj_bean.setShort_description(short_description);
				obj_bean.setRegular_price(regular_price);
				obj_bean.setSale_price(sale_price);
				obj_bean.setTotal_sales(total_sales);
				obj_bean.setExternal_url(external_url);
				//delete fav
				obj_model.checkFavorites(con,obj_bean);
				
				String arr[]=friend_id.split(","); 
				
				for(int k=0;k<arr.length;k++)
				{
					obj_bean.setFriend_id(arr[k]);
					add_response=obj_model.AddFavorites(con, obj_bean);
					
					
				}
				
				if(add_response)
				{
					printOutput(Constant.StatusSuccess, Constant.FAVORITESSUCCESS);
				}
				else
				{
					printOutput(Constant.StatusFail, Constant.FAVORITESFAIL);
				}
				
			}
			else
			{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail, Constant.FAVORITESFAIL);
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
