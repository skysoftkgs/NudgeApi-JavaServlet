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

import com.nudge.bean.registration_bean;
import com.nudge.common.AESHelper;
import com.nudge.common.Config;
import com.nudge.common.Constant;
import com.nudge.common.PropertiesReader;
import com.nudge.common.SendMail;
import com.nudge.common.SendMailOld;
import com.nudge.model.registrationModel;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
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
	String login_type="";
	String unique_id="";
	String location="";
	String status="";
	String message="";
	String last_name="";
	JSONObject userobj;
	String verify_url;
	String gender;

	public Registration() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.response=response;
		try {
			obj=new JSONObject();

			PropertiesReader pr = new PropertiesReader();
			Properties prop = new Properties();
			prop = pr.getPropertyFile();

			String UPLOAD_IMAGE_PATH = prop.getProperty("UPLOAD_IMAGE_PATH");
			String IMAGE_PATH = prop.getProperty("IMAGE_PATH");
			String EMAIL_VER_URL = prop.getProperty("EMAIL_VER_URL");
			String EMAIL_VER_SUBJECT = prop.getProperty("EMAIL_VER_SUBJECT");
			String MAIL_FROM = prop.getProperty("MAIL_FROM");
			String LOGO = prop.getProperty("LOGO");
			String SERVER_NAME = prop.getProperty("SERVER_NAME");
			String SERVER_PORT = prop.getProperty("SERVER_PORT");
			String PASSWORD = prop.getProperty("PASSWORD");


			//System.out.println("upload image path"+UPLOAD_IMAGE_PATH+"image path : "+IMAGE_PATH); 


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

							image = IMAGE_PATH+file_name;

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
					if(item.getFieldName().equals("login_type"))
					{
						login_type=item.getString();
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
					else if(item.getFieldName().equals("unique_id"))
					{
						unique_id=item.getString();
					}
					else if(item.getFieldName().equals("password"))
					{
						password=item.getString();
					}
					else if(item.getFieldName().equals("dob"))
					{
						dob=item.getString();
					}
					else if(item.getFieldName().equals("location"))
					{
						location=item.getString();
					}
					else if(item.getFieldName().equals("gender"))
					{
						gender=item.getString();
					}

				}

			} 

			
			con = Config.getInstance().getConnection();


			if(login_type.equalsIgnoreCase("email") )
			{
				
				userobj=new JSONObject() ;
				
				if(name.length()!=0 && email.length()!=0 && password.length()!=0 && dob.length()!=0 && login_type.length()!=0 && unique_id.length()!=0 && location.length()!=0 && last_name.length()!=0 && gender.length()!=0)
				{
					registrationModel model_obj = new  registrationModel();
					boolean CheckEmail = model_obj.CheckEmail(con, unique_id);

					if(CheckEmail)
					{
						printOutput(Constant.StatusFail, Constant.MessageEmail,userobj);
					}
					else
					{
					
						registration_bean obj_bean =new registration_bean();
						obj_bean.setLogin_type(login_type);
						obj_bean.setName(name);
						obj_bean.setEmail(email);
						obj_bean.setPassword(AESHelper.GetEncryptValue(password));
						obj_bean.setDob(dob);
						obj_bean.setImage(image);
						obj_bean.setUnique_id(unique_id);
						obj_bean.setLocation(location);
						obj_bean.setLast_name(last_name);
						obj_bean.setGender(gender);

						int add_response = model_obj.AddUser(con, obj_bean);

						

						if(add_response!=0)
						{
							userobj = new JSONObject();

							userobj.put("id",add_response);
							userobj.put("name",name);
							userobj.put("last_name", last_name);
							userobj.put("email",email);
							userobj.put("dob",dob);
							userobj.put("image",image);
							userobj.put("location",location);
							userobj.put("gender",gender);
							

							//obj.put("user_details", userobj);
							//printOutput(Constant.StatusSuccess,Constant.MessageUR,userobj);	
							
						//	UserDetailsBean bean_obj = model_obj.GetUserDetailsBean(email, con);


							verify_url=EMAIL_VER_URL+userobj.getString("email");
							//System.out.println(verify_url);

							String NewMailBody1 = NewRegistrationHTMLMailFormat(userobj.getString("name"), verify_url,LOGO,userobj.getString("email"),SERVER_NAME,SERVER_PORT);

							
							SendMail.SendMailUser(NewMailBody1, userobj.getString("email"), EMAIL_VER_SUBJECT,MAIL_FROM,PASSWORD);

							printOutput(Constant.StatusSuccess, Constant.EMAIL_SEND_SUCCESS,userobj);

							
						}
						else
						{
							printOutput(Constant.StatusFail, Constant.MessageANS,userobj);
						}

					}

				}
				else
				{
					printOutput(Constant.StatusFail, Constant.INVALID_INPUTS,userobj);
				}
			}


			else if(login_type.equalsIgnoreCase("fb") )
			{
				registrationModel model_obj = new  registrationModel();

				if(name.length()!=0 && email.length()!=0 && password.length()!=0 && dob.length()!=0 && login_type.length()!=0 && unique_id.length()!=0 && location.length()!=0 && last_name.length()!=0
						&& gender.length()!=0)
				{
					boolean result = model_obj.CheckEmail(con, unique_id);

					if(result)
					{
						printOutput(Constant.StatusFail, Constant.MessageUsername,userobj);
					}
					else
					{
						registration_bean obj_bean =new registration_bean();

						obj_bean.setName(name);
						obj_bean.setEmail(email);
						obj_bean.setPassword(AESHelper.GetEncryptValue(password));
						obj_bean.setDob(dob);
						obj_bean.setUnique_id(unique_id);
						obj_bean.setImage(image);
						obj_bean.setLogin_type(login_type);
						obj_bean.setLocation(location);
						obj_bean.setLast_name(last_name);
						obj_bean.setGender(gender);

						int add_response = model_obj.AddUser(con, obj_bean);

						if(add_response!=0)
						{
							userobj= new JSONObject();
							
							userobj.put("id",add_response);
							userobj.put("name",name);
							userobj.put("last_name", last_name);
							userobj.put("email",email);
							userobj.put("dob",dob);
							userobj.put("image",image);
							userobj.put("location",location);
							userobj.put("gender",gender);
							


							//obj.put("user_details", userobj);

							printOutput(Constant.StatusSuccess,Constant.MessageUR,userobj);	
						}
						else
						{
							printOutput(Constant.StatusFail, Constant.MessageANS,userobj);
						}
					}

				}
				else
				{
					printOutput(Constant.StatusFail, Constant.INVALID_INPUTS,userobj);
				}
			}


		}	

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			printOutput(Constant.StatusFail, Constant.FAIL_RESPONSE,userobj);

		}
		finally
		{
			Config.DB_colse();
		}

	}


	public void printOutput(String status,String message, JSONObject userobj)
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

	public static String NewRegistrationHTMLMailFormat(String name, String verify_url, String LOGO,String email, String SERVER_NAME, String SERVER_PORT)
	{
		String message_body="";

	/*	message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#620404' style='width:100%;background:#660208'>";

		message_body+="<tbody><tr><td><table border='0' cellspacing='0' cellpadding='0' align='center' width='550' style='width:100%;padding:40px;padding-bottom:25px'>";

		message_body+="<tbody><tr><td><div style='direction:ltr;max-width:600px;margin:0 auto'>";

		message_body+="<table border='0' cellspacing='0' cellpadding='0' bgcolor='#ffffff' style='width:100%;background-color:#fff;text-align:left;margin:0 auto;max-width:1024px;min-width:320px'>";

		message_body+="<tbody><tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0' height='3'  style='width:100%;background-color:#0D3E9D;height:8px'>";

		message_body+="<tbody><tr><td></td></tr></tbody></table><table style='width:100%' width='100%' border='0' cellspacing='0' cellpadding='20' bgcolor='#ffffff'><tbody>";

		message_body+="<tr><td valign='top' style='padding:20px'><img src='"+LOGO+"' width='250px' height='80px'>";

		message_body+="<p style='direction:ltr;font-size:14px;line-height:1.4em;color:#444444;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;margin:0 0 1em 0;margin:0 0 20px 0'>Click On The Link Below For Email Verification</p>";

		message_body+="<p style='direction:ltr;font-size:14px;line-height:1.4em;color:#444444;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;margin:0 0 1em 0;margin:5px 0 10px 0'>";

		//		message_body+="<a href='"+RESET_ADMIN_PASSWORD+username+"' style='text-decoration:underline;color:#051662;border-radius:10em;border:1px solid #0D3E9D;text-decoration:none;color:#fff;background-color:#051662;padding:7px 30px;font-size:16px;line-height:1.4em;font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-weight:normal' target='_blank'>Reset Password</a></p>";

		message_body+="<a href='"+verify_url+"' style='text-decoration:underline;color:#051662;border-radius:10em;border:1px solid #0D3E9D;text-decoration:none;color:#fff;background-color:#660208;padding:7px 30px;font-size:16px;line-height:1.4em;font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-weight:normal' target='_blank'>Email Verification</a></p>";

		message_body+="</td><td></td></tr><tr></tr></tbody></table></td></tr></tbody></table>";

		message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' height='3'  style='width:100%;background-color:#660208;height:4px'>";

		message_body+="<tbody><tr><td></td></tr></tbody></table></div></td></tr></tbody></table>";

		message_body+="<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center' style='width:100%;padding-bottom:2em;color:#555555;font-size:12px;height:18px;text-align:center;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif'>";

		message_body+="<tbody><tr><td align='center'><a href='#' style='text-decoration:underline;color:#2585b2;font-size:14px;color:#FFF!important;text-decoration:none;font-family:&quot;Helvetica Neue&quot;,Helvetica,Arial,sans-serif;color:#FFF!important;font-size:14px;text-decoration:none' target='_blank'>Thanks for registering with <img border='0' src='"+LOGO +"' alt='' style='vertical-align:middle' width='25px' height='16' class='CToWUd'> Nudge</a></td>";

		message_body+="</tr></tbody></table></td></tr></tbody></table>";
		return message_body;*/
		
		message_body+="<div style='font-family:HelveticaNeue-Light,Arial,sans-serif;background-color:#eeeeee'>";
		message_body+="<table align='center' width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee'>";
		message_body+="<tbody>";
		message_body+="<tr>" ;
		message_body+="	<td>";  
		message_body+="<table align='center' width='750px' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee' style='width:750px!important'>";
		message_body+=" <tbody>";      
		message_body+="<tr>";
		message_body+="<td>";
		message_body+="<table width='690' align='center' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee'>";
		message_body+="<tbody>";
		message_body+="<tr>";
		message_body+="<td colspan='3' height''80' align='center' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee' style='padding:0;margin:0;font-size:0;line-height:0'>";
		//message_body+="<table width='690' align='center' border='0' cellspacing='0' cellpadding='0'>";
		//message_body+="<tbody>";
		//message_body+="<tr>";
		//message_body+="<td width='30'></td>";
		//message_body+="<td align='left' valign='middle' style='padding:0;margin:0;font-size:0;line-height:0'><a href='' target='_blank'><img src='http://36.255.3.15:8080/nudge_uploads/1497604990_Lighthouse.jpg' style='height:100px;'  alt='NUDGE' ></a></td>";
		//message_body+="<td width='30'></td>";
		//message_body+="</tr>";
		//message_body+="</tbody>";
		//message_body+="</table>";
		message_body+="</td>";
		message_body+="</tr>";
		message_body+="<tr>";
		message_body+="<td colspan='3' >";
		message_body+="<table width='630'  border='0' cellspacing='0' cellpadding='0'>";
		message_body+="<tbody>";
		message_body+="<tr>";
		message_body+=" <td colspan='3' height='40'></td></tr><tr><td width='25'></td>";
		message_body+=" <td>";
		message_body+=" <p style='font-size:12px;color:#404040;line-height:24px;margin:0;padding:0'>Hey "+name+"<br>Welcome to Nudge - a truly unique social platform which lets you set reminders for important dates of your friends and family, and send them gifts to celebrate the occasion.To complete the registration, please verify your email address by clicking the link below. </p><br>";
		message_body+="<a href='"+verify_url+"' style='text-decoration:underline;color:#051662;border-radius:10em;border:1px solid #0D3E9D;text-decoration:none;color:#fff;background-color:#660208;padding:7px 30px;font-size:16px;line-height:1.4em;font-family:Helvetica Neue,Helvetica,Arial,sans-serif;font-weight:normal' target='_blank'>Email Verification</a></p>";
		message_body+="  </td>";
		message_body+=" <td width='25'></td>";
		message_body+=" </tr>";
		message_body+="<tr>";
		message_body+="	<td colspan='3' height='40'></td></tr><tr><td colspan='5' >";
		message_body+="  <p style='color:#404040;font-size:12px;line-height:24px;padding:0;margin:0'>The link is valid for the next 24 hours only.<br>If you have any questions, send us an email at nudge@gmail.com.</p><br><br>Cheers!<br>Team Nudge ";
		message_body+="  <p style='color:#404040;font-size:12px;line-height:22px;padding:0;margin:0'>";
		//message_body+="<b>'.$licence_key.' </b>";
					 
		message_body+="	 </p>";
					   
				  
		message_body+=" </td>";
		message_body+=" </tr>";
		message_body+=" <tr>";
	                                           
		message_body+="</tr>";
		message_body+="<tr><td colspan='3' height='30'></td></tr>";
		message_body+="</tbody>";
		message_body+="</table>";
		message_body+="</td>";
		message_body+="</tr>";
	                            
	                           
		message_body+=" </tbody>";
		message_body+="</table>";
		message_body+="<table align='center' width='750px' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee' style='width:750px!important'>";
		message_body+=" <tbody>";
		message_body+="<tr>";
		message_body+="	<td>";
		message_body+=" <table width='630' align='center' border='0' cellspacing='0' cellpadding='0' bgcolor='#eeeeee'>";
		message_body+="<tbody>";
		message_body+="<tr><td colspan='2' height='30'></td></tr>";
		message_body+=" <tr>";
		message_body+=" <td width='360' valign='top'>";
		message_body+="<div style='color:#a3a3a3;font-size:12px;line-height:12px;padding:0;margin:0'>&copy; 2017 NUDGE. All rights reserved.</div>";
		message_body+="	<div style='line-height:5px;padding:0;margin:0'>&nbsp;</div>";
		message_body+="	<div style='color:#a3a3a3;font-size:12px;line-height:12px;padding:0;margin:0'></div>";
		message_body+="</td>";
		//message_body+="<td align='right' valign='top'>";
		//message_body+="<span style='line-height:20px;font-size:10px'><a href='https://www.facebook.com/ShieldCrypt/' target='_blank'><img src='http://i.imgbox.com/BggPYqAh.png' alt='fb'></a>&nbsp;</span>";
	    //message_body+="<span style='line-height:20px;font-size:10px'><a href='https://twitter.com/shieldcrypt' target='_blank'><img src='http://i.imgbox.com/j3NsGLak.png' alt='twit'></a>&nbsp;</span>";
	    //message_body+="<span style='line-height:20px;font-size:10px'><a href='https://plus.google.com/116132685670688176031' target='_blank'><img src='http://i.imgbox.com/wFyxXQyf.png' alt='g'></a>&nbsp;</span>";
	    //message_body+="</td>";
		message_body+=" </tr>";
		message_body+="<tr><td colspan='2' height='5'></td></tr>";
		                                      
		message_body+="</tbody>";
		message_body+=" </table>";
		message_body+="</td>";
		message_body+="</tr>";
		message_body+="</tbody>";
		message_body+="</table>";
		message_body+="</td>";
		message_body+="</tr>";
		message_body+="</tbody>";
		message_body+="</table>";
		message_body+="</td>";
		message_body+="</tr>";
		message_body+="</tbody>";
		message_body+="</table>";
		message_body+="</div>";
		return message_body;
	}

}
