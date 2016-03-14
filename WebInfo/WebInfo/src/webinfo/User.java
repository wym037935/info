package webinfo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class User {
	public static String IP="http://59.66.130.139";
	
	public void setIP(String IP){
		this.IP=IP;
	}
	
	public boolean isExist(String email) {
		String sqlstr = "select email from users where email = '" + email
				+ "';";
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			conn = DBUtil.getConnForMySql();
			System.out.println("Connect to database");
			cs = conn.createStatement();
			rs = cs.executeQuery(sqlstr);
			result = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.CloseResources(conn, cs);
		}
		return result;
	}

	public String logIn(String email, String password) {
		if(!isExist(email))
			return "not_exist";
		if(getstatus(email).equals("needactive"))
			return "not_activated";
		if(getstatus(email).equals("disabled"))
			return "disabled";
		if (getpassword(email).equals(MD5.toMD5String(password)))
			return "successful";
		else
			return "wrong_info";
	}

	/**
	 * getXXX从数据库中读取数据
	 * 
	 * @param email
	 *            ，所要读取用户的userName
	 * @return rs.getString(1), 返回读取的信息
	 * 
	 * */
	/**
	 * setXXX向数据库中写入数据
	 * 
	 * @param email
	 *            ，xxx，要向email所代表的行写入的数据xxx
	 * 
	 * */

	 private synchronized String getsomething(String email, String something) {
		String sqlstr = "select " + something + " from users where email = '"
				+ email + "';";
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			cs = conn.createStatement();
			rs = cs.executeQuery(sqlstr);
			if (rs.next())
				return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.CloseResources(conn, cs);
		}
		return "";
	}

	private synchronized void setsomething(String email, String newValue, String something) {
		Connection conn = null;
		Statement stmt; // 创建声明

		try {
			conn = DBUtil.getConnForMySql();
			System.out.println("Connect to database");
			stmt = conn.createStatement();
			stmt.executeUpdate("update users set " + something + "='"
					+ newValue + "' where email='" + email + "';");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
	}

	public String getpassword(String email) {
		return getsomething(email, "password");
	}

	public void setpassword(String email, String password) {
		setsomething(email, MD5.toMD5String(password), "password");
	}

	public String modifypassword(String email, String oldpassword,String newpassword){
		if(MD5.toMD5String(oldpassword).equals(getpassword(email))){
			setpassword(email,newpassword);
			return "successful";
		}
		else
			return "wrong_old_password";
	}
	
	public String getpermission(String email) {
		return getsomething(email, "permision");
	}
	
	public void setpermission(String email, String permision) {
		setsomething(email, permision, "permision");
	}
	
	public void setstatus(String email, String status) {
		setsomething(email, status, "status");
	}
	
	public String getstatus(String email) {
		return getsomething(email, "status");
	}

	public void setmobile(String email, String mobile) {
		setsomething(email, mobile, "mobile");
	}
	
	public String getmobile(String email) {
		return getsomething(email, "mobile");
	}

	public void setreal_name(String email, String real_name) {
		setsomething(email, real_name, "real_name");
	}
	
	public String getreal_name(String email) {
		return getsomething(email, "real_name");
	}
	
	public void setalias(String email, String alias) {
		setsomething(email, alias, "alias");
	}
	
	public String getalias(String email) {
		return getsomething(email, "alias");
	}
	
	public void setgender(String email, String gender) {
		setsomething(email, gender, "gender");
	}
	
	public String getgender(String email) {
		return getsomething(email, "gender");
	}
	
	public void setcontact_name(String email, String contact_name) {
		setsomething(email, contact_name, "contact_name");
	}
	
	public String getcontact_name(String email) {
		return getsomething(email, "contact_name");
	}	

	public void setcontact_mobile(String email, String contact_mobile) {
		setsomething(email, contact_mobile, "contact_mobile");
	}
	
	public String getcontact_mobile(String email) {
		return getsomething(email, "contact_mobile");
	}	
	
	public void setyear_of_birth(String email, String year_of_birth) {
		setsomething(email, year_of_birth, "year_of_birth");
	}
	
	public String getyear_of_birth(String email) {
		return getsomething(email, "year_of_birth");
	}

	public void setmonth_of_birth(String email, String month_of_birth) {
		setsomething(email, month_of_birth, "month_of_birth");
	}
	
	public String getmonth_of_birth(String email) {
		return getsomething(email, "month_of_birth");
	}
	
	public void setday_of_birth(String email, String day_of_birth) {
		setsomething(email, day_of_birth, "day_of_birth");
	}
	
	public String getday_of_birth(String email) {
		return getsomething(email, "day_of_birth");
	}
	
	public void setposting_permision(String email, String posting_permision) {
		setsomething(email, posting_permision, "posting_permision");
	}
	
	public String getposting_permision(String email) {
		return getsomething(email, "posting_permision");
	}
	
	public void setacticode(String email, String acticode) {
		setsomething(email, acticode, "acticode");
	}
	
	public String getacticode(String email) {
		return getsomething(email, "acticode");
	}
	
	private String calcacticode(String email){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");// 可以方便地修改日期格式

		return MD5.toMD5String(email+dateFormat.format(now));
	}
	
	public synchronized ArrayList<String> get_allusers(String email){
		String sqlstr = "select  email from users where 1=1;";
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();
		
		if(!getpermission(email).equals("moderator"))
			return result;
		try {
			conn = DBUtil.getConnForMySql();
			cs = conn.createStatement();
			rs = cs.executeQuery(sqlstr);
			while (rs.next())
			{
				result.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.CloseResources(conn, cs);
		}
		return result;
	}
	
	/*激活新注册的账户
	 * 
	 */
	public synchronized String active(String email,String acticode){
		if(getacticode(email).equals(acticode))
		{
			setstatus(email,"enabled");
			return "activation_success";
		}
		return "activation_fail";
	}
	
	/*注册新用户并发送激活邮件
	 * 
	 */
	public synchronized String newUser(Map <String,Object> user){
		Connection conn = null;
		Statement stmt; // 创建声明
		String email = ""+user.get("email");
		String acticode = calcacticode(email);
		
		if(isExist(email))
			return "user_exists";
		try {
			conn = DBUtil.getConnForMySql();
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into users(email,password) values('"+
			email+"','"+user.get("password")+"');" );

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
		
		setmobile(email,""+user.get("mobile"));
		setreal_name(email,""+user.get("real_name"));
		setalias(email,""+user.get("alias"));
		setgender(email,""+user.get("gender"));
		setcontact_name(email,""+user.get("contact_name"));
		setcontact_mobile(email,""+user.get("contact_mobile"));
		setyear_of_birth(email,""+user.get("year_of_birth"));
		setmonth_of_birth(email,""+user.get("month_of_birth"));
		setday_of_birth(email,""+user.get("day_of_birth"));
		setpermission(email,""+user.get("permission"));
		setacticode(email,acticode);
		
		new SendMail().Send(email, "伍一鸣", email, acticode,IP);
		return "reg_success";
	}
}
