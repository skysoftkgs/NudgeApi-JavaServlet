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
import org.json.JSONObject;

import com.nudge.bean.registration_bean;
import com.nudge.common.AESHelper;
import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.PropertiesReader;
import com.nudge.model.registrationModel;

/**
 * Servlet implementation class UpdateRegistration
 */
@WebServlet("/UpdateRegistration")
public class UpdateRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	JSONObject obj;
	Connection con;
	HttpServletResponse response;



	String image="";
	String name="";
	String email="";
	String password="";
	String enc_password="";
	String dob="";
	String user_id="";
	String unique_id="";
	String location="";
	String status="";
	String message="";
	JSONObject userobj;
	String isimage="";
	String last_name="";
	String new_password="";
	String gender="";



	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateRegistration() {
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

			PropertiesReader pr = new PropertiesReader();
			Properties prop = new Properties();
			prop = pr.getPropertyFile();

			String UPLOAD_IMAGE_PATH = prop.getProperty("UPLOAD_IMAGE_PATH");
			String IMAGE_PATH = prop.getProperty("IMAGE_PATH");

			List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest((request));

			long seconds=System.currentTimeMillis()/1000;

			for(FileItem item: multiparts)
			{
				if(!item.isFormField())
				{
					if(item.getFieldName().equals("image"))
					{
						image = new File(item.getName()).getName();

						// System.out.println(image);

						if(image!="")
						{
							String file_name=seconds+"_"+image;

							//System.out.println(file_name);

							image = file_name;

							//System.out.println(file_path);

							item.write(new File(UPLOAD_IMAGE_PATH, file_name));

						}
						else
						{
							image = "";
						}
					}


				}
				else
				{
					if(item.getFieldName().equals("user_id"))
					{
						user_id=item.getString();
					}
					else if(item.getFieldName().equals("name"))
					{
						name=item.getString();
					}
					else if(item.getFieldName().equals("last_name"))
					{
						last_name=item.getString();
					}
					else if(item.getFieldName().equals("email"))
					{
						email=item.getString();
					}
					else if(item.getFieldName().equals("password"))
					{
						password=item.getString();
					}
					else if(item.getFieldName().equals("new_password"))
					{
						new_password=item.getString();
					}
					else if(item.getFieldName().equals("dob"))
					{
						dob=item.getString();
					}
					else if(item.getFieldName().equals("location"))
					{
						location=item.getString();
					}
					else if(item.getFieldName().equals("isimage"))
					{
						isimage=item.getString();
					}
					else if(item.getFieldName().equals("gender"))
					{
						gender=item.getString();
					}


				}

			} 


			con = Config.getInstance().getConnection();

			userobj=new JSONObject();


			if(user_id.length()!=0 && name.length()!=0 && email.length()!=0 && password.length()!=0 && dob.length()!=0 && location.length()!=0 && last_name.length()!=0 && gender.length()!=0)
			{

				registrationModel model_obj=new registrationModel();

				boolean add_response=model_obj.CheckUserId(con,user_id);
				boolean pwd_change=false;


				if(add_response)
				{
					if(new_password.length()!=0)
					{
						String enc_pwd=AESHelper.GetEncryptValue(password);
						pwd_change=model_obj.checkPassword(con,enc_pwd,user_id);

						if(pwd_change)
						{
							System.out.println("pwd exists======");
							//update password
							registration_bean bean_obj=new registration_bean();

							bean_obj.setUser_id(user_id);
							bean_obj.setName(name);
							bean_obj.setEmail(email);
							bean_obj.setPassword(AESHelper.GetEncryptValue(new_password));
							bean_obj.setDob(dob);
							bean_obj.setLocation(location);
							bean_obj.setImage(image);
							bean_obj.setIsimage(isimage);
							bean_obj.setLast_name(last_name);
							bean_obj.setGender(gender);


							boolean update_response=model_obj.UpdateRegistration(con,bean_obj);

							if(update_response)
							{
								userobj = new JSONObject();

								userobj.put("id",Integer.parseInt(user_id));
								userobj.put("name",name);
								userobj.put("last_name", last_name);
								userobj.put("email",email);
								userobj.put("dob",dob);
								userobj.put("image",image);
								userobj.put("location",location);
								userobj.put("gender",gender);

								printOutput(Constant.StatusSuccess, Constant.MessageUpdate,userobj);

							}
							else
							{
								printOutput(Constant.StatusFail, Constant.MessageNOTUPDATE,userobj);
							}
						}
						else{
							printOutput(Constant.StatusFail, Constant.PWD_NOT_MATCH,userobj);

						}
					}

					else{

						registration_bean bean_obj=new registration_bean();

						bean_obj.setUser_id(user_id);
						bean_obj.setName(name);
						bean_obj.setEmail(email);
						bean_obj.setPassword(AESHelper.GetEncryptValue(password));
						bean_obj.setDob(dob);
						bean_obj.setLocation(location);
						bean_obj.setImage(image);
						bean_obj.setIsimage(isimage);
						bean_obj.setLast_name(last_name);
						bean_obj.setGender(gender);

						boolean update_response=model_obj.UpdateRegistration(con,bean_obj);

						if(update_response)
						{
							userobj = new JSONObject();

							userobj.put("id",Integer.parseInt(user_id));
							userobj.put("name",name);
							userobj.put("last_name", last_name);
							userobj.put("email",email);
							userobj.put("dob",dob);
							userobj.put("image",image);
							userobj.put("location",location);
							userobj.put("gender",gender);

							printOutput(Constant.StatusSuccess, Constant.MessageUpdate,userobj);

						}
						else
						{
							printOutput(Constant.StatusFail, Constant.MessageNOTUPDATE,userobj);
						}

					}

				}
				else
				{
					printOutput(Constant.StatusFail, Constant.MessageUNR,userobj);

				}
			}
			else
			{
				printOutput(Constant.StatusFail, Constant.INVALID_INPUTS,userobj);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail, Constant.FAIL_RESPONSE,userobj);
		}
	}

	public void printOutput(String status,String message,JSONObject userobj)
	{
		try {

			obj.put(Constant.STATUS, status);
			obj.put(Constant.MESSAGE,message);
			obj.put(Constant.USERDETAILS,userobj);


			response.getWriter().append(obj.toString());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
		} 

		catch (Exception e) {
			// TODO: handle exception
		}



	}

}
