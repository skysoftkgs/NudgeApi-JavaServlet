package com.nudge.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nudge.bean.FavoritesBean;
import com.nudge.bean.registration_bean;
import com.nudge.common.Util;

public class FavoritesModel {

	public FavoritesModel()
	{

	}

	public boolean checkFavorites(Connection con, FavoritesBean bean_obj)
	{
		boolean response = false;

		try {

			String qry = "select * from favorites where user_id='"+bean_obj.getUser_id()+"' and prod_id='"+bean_obj.getProd_id()+"'";

			Statement stm = con.createStatement();
			ResultSet rs=  stm.executeQuery(qry);


			if(rs.next())
			{
				deleteFavorites(con,bean_obj);
				response = true;
			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public boolean deleteFavorites(Connection con, FavoritesBean bean_obj)
	{
		boolean response = false;

		try {

			String qry = "Delete from favorites where user_id='"+bean_obj.getUser_id()+"' and prod_id='"+bean_obj.getProd_id()+"' ";

			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(qry);


			if(i>0)
			{
				response = true;
			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}


	public boolean AddFavorites(Connection con, FavoritesBean bean_obj)
	{
		boolean response = false;

		try {

			String qry = "INSERT INTO `favorites`(`user_id`,`friend_id`,`prod_id`,`prod_name`,`prod_image`,`price`,`slug`,`description`,`short_description`,`regular_price`,`sale_price`,`total_sales`,`external_url`)"
					+ " VALUES ('"+bean_obj.getUser_id()+"','"+bean_obj.getFriend_id()+"','"+bean_obj.getProd_id()+"','"+bean_obj.getProd_name()+"',"
					+ "'"+bean_obj.getProd_image()+"','"+bean_obj.getPrice()+"','"+bean_obj.getSlug()+"','"+bean_obj.getDescription()+"',"
					+ "'"+bean_obj.getShort_description()+"','"+bean_obj.getRegular_price()+"','"+bean_obj.getSale_price()+"',"
					+ "'"+bean_obj.getTotal_sales()+"','"+bean_obj.getExternal_url()+"')";


			Statement stm = con.createStatement();
			int i=  stm.executeUpdate(qry);


			if(i>0)
			{
				response = true;
			}


		} catch (Exception e) {


			e.printStackTrace();
			return response;
		}

		return response;
	}

	public JSONArray getFavorites(Connection con,String user_id,String friend_id)
	{
		JSONArray jarray=new JSONArray();
		JSONArray imagearray=new JSONArray();

		try {

			String qry="SELECT * from `favorites` where user_id='"+user_id+"' AND friend_id='"+friend_id+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qry);

			while(rs.next())
			{
				JSONObject jobj=new JSONObject();

				jobj.put("id", Util.getResult(rs, "prod_id"));
				jobj.put("name", Util.getResult(rs, "prod_name"));
				jobj.put("price", Util.getResult(rs, "price"));
				jobj.put("slug", Util.getResult(rs, "slug"));
				jobj.put("description", Util.getResult(rs, "description"));
				jobj.put("short_description", Util.getResult(rs, "short_description"));
				jobj.put("regular_price", Util.getResult(rs, "regular_price"));
				jobj.put("sale_price", Util.getResult(rs, "sale_price"));
				jobj.put("total_sales", Util.getResult(rs, "total_sales"));
				jobj.put("external_url", Util.getResult(rs, "external_url"));

				String image=Util.getResult(rs, "prod_image");
				
				String arr[]=image.split(",");
				
				for(int i=0;i<arr.length;i++)
				{
					String image1=arr[i];
					imagearray.put(image1);
				}
				
				jobj.put("image", imagearray);
				jarray.put(jobj);
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}
	
	

	public JSONArray getFavoritesByProdId(Connection con,String user_id,String prod_id)
	{
		JSONArray jarray=new JSONArray();

		try {

			String qry="SELECT * from `favorites` where user_id='"+user_id+"' AND prod_id='"+prod_id+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qry);

			while(rs.next())
			{
				JSONObject jobj=new JSONObject();

				jobj.put("friend_id", Util.getResult(rs, "friend_id"));

				jarray.put(jobj);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return jarray;
		}

		return jarray;
	}


}
