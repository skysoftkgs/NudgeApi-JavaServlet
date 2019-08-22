package com.nudge.common;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.nudge.bean.UploadProfileBean;
import com.nudge.model.UploadProfileModel;


public class SendPush {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con;
		con = Config.getInstance().getConnection();
	//	System.out.println("connn--------------"+con);


		// current timestamp
		long time=System.currentTimeMillis();
		//System.out.println("time=="+time);


		//current date
		String currentdate=Util.MillisecondToDdMmYYYY(Long.toString(time));

		String arr[]=currentdate.split("/"); 

		for(int k=0;k<arr.length;k++)
		{
			String year=arr[2];
			String month=arr[1];
			String date1=arr[0];

			currentdate=year+"/"+month+"/"+date1;
			//System.out.println("currentdate=="+currentdate);

		}




		// current day of month
		/*Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		String dayOfMonthStr = String.valueOf(dayOfMonth);
		System.out.println("dayOfMonth=="+dayOfMonth);
		 */

		// current month
		/*Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
		System.out.println("month=="+df1.format(rightNow.getTime()));
		 */

		List<UploadProfileBean> all_user= new ArrayList<UploadProfileBean>();
		UploadProfileModel model_obj = new UploadProfileModel();
		
		all_user =model_obj.getNotDate(con, currentdate);


		for (int i = 0; i < all_user.size(); i++)
		{
			UploadProfileBean obj_bean=all_user.get(i);
			String user_id=obj_bean.getUser_id();
			String friend_id=obj_bean.getFriend_id();
			String event_name=obj_bean.getEvent_name();
			String event = obj_bean.getEvent();
			String custom_name = obj_bean.getCustom_name();
			String nudge_timestamp = obj_bean.getNudge_timestamp();
			
			if(user_id!=null)
			{

				String phpurl="http://app.thegiftingapp.com/woo_api/push_notification.php?to="+user_id+"&friend_id="+friend_id+"&event_name="+event_name+"&event="+event+"&custom_name="+custom_name+"&nudge_timestamp="+nudge_timestamp;
			

				try {
					String urlresponse=URLConnectionReader.getText(phpurl); 
				} 
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}


		
	}


}


