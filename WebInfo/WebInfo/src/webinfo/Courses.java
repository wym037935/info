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

public class Courses {

	public boolean isExist(String course_number) {
		String sqlstr = "select course_number from course where course_number = '"
				+ course_number + "';";
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
	
	public boolean isExist(String email,String course_number) {
		String sqlstr = "select status from course where course_number = '"
				+ course_number +  "' AND email = '" + email + "' AND status = 'current';";
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
	/**
	 * getXXX�����ݿ��ж�ȡ����
	 * 
	 * @param course_number
	 *            ����Ҫ��ȡ�û���userName
	 * @return rs.getString(1), ���ض�ȡ����Ϣ
	 * 
	 * */
	/**
	 * setXXX�����ݿ���д������
	 * 
	 * @param course_number
	 *            ��xxx��Ҫ��course_numberl���������д�������xxx
	 * 
	 * */

	private synchronized String getsomething(String course_number,
			String something) {
		String sqlstr = "select " + something
				+ " from courses where course_number = '" + course_number
				+ "';";
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

	private synchronized void setsomething(String course_number,
			String newValue, String something) {
		Connection conn = null;
		Statement stmt; // ��������

		try {
			conn = DBUtil.getConnForMySql();
			System.out.println("Connect to database");
			stmt = conn.createStatement();
			stmt.executeUpdate("update courses set " + something + "='"
					+ newValue + "' where course_number='" + course_number
					+ "';");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
	}

	private synchronized int getsomething_int(String course_number,
			String something) {
		String sqlstr = "select " + something
				+ " from courses where course_number = '" + course_number
				+ "';";
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnForMySql();
			cs = conn.createStatement();
			rs = cs.executeQuery(sqlstr);
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.CloseResources(conn, cs);
		}
		return -1;
	}

	private synchronized void setsomething_int(String course_number,
			String newValue, String something) {
		Connection conn = null;
		Statement stmt; // ��������

		try {
			conn = DBUtil.getConnForMySql();
			System.out.println("Connect to database");
			stmt = conn.createStatement();
			stmt.executeUpdate("update courses set " + something + "="
					+ newValue + " where course_number='" + course_number
					+ "';");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
	}

	public String getname(String course_number){
		return getsomething(course_number,"name");
	}
	
	public void setname(String course_number,String name){
		setsomething(course_number,name,"name");
	}
	

	public String getbegin_date(String course_number){
		return getsomething(course_number,"begin_date");
	}
	
	public void setbegin_date(String course_number,String begin_date){
		setsomething(course_number,begin_date,"begin_date");
	}

	public String getend_date(String course_number){
		return getsomething(course_number,"end_date");
	}
	
	public void setend_date(String course_number,String end_date){
		setsomething(course_number,end_date,"end_date");
	}
	
	public String getlocation(String course_number){
		return getsomething(course_number,"location");
	}
	
	public void setlocation(String course_number,String location){
		setsomething(course_number,location,"location");
	}
	
	public String getdescription(String course_number){
		return getsomething(course_number,"description");
	}
	
	public void setdescription(String course_number,String description){
		setsomething(course_number,description,"description");
	}
	
	public String gethour(String course_number){
		return getsomething(course_number,"hour");
	}
	
	public void sethour(String course_number,String hour){
		setsomething(course_number,hour,"hour");
	}

	public String getteacher(String course_number){
		return getsomething(course_number,"teacher");
	}
	
	public void setteacher(String course_number,String teacher){
		setsomething(course_number,teacher,"teacher");
	}

	public String getcredit(String course_number){
		return getsomething(course_number,"credit");
	}
	
	public void setcredit(String course_number,String credit){
		setsomething(course_number,credit,"credit");
	}
	
	public int getmax_allowance(String course_number){
		return getsomething_int(course_number,"max_allowance");
	}
	
	public void setmax_allowance(String course_number,String max_allowance){
		setsomething_int(course_number,max_allowance,"max_allowance");
	}

	public int getcurrent_count(String course_number){
		return getsomething_int(course_number,"current_count");
	}
	
	public void setcurrent_count(String course_number,String current_count){
		setsomething_int(course_number,current_count,"current_count");
	}

	public int getday_of_week(String course_number){
		return getsomething_int(course_number,"day_of_week");
	}
	
	public void setday_of_week(String course_number,String day_of_week){
		setsomething_int(course_number,day_of_week,"day_of_week");
	}

	public int getcourse_of_day(String course_number){
		return getsomething_int(course_number,"course_of_day");
	}
	
	public void setcourse_of_day(String course_number,String course_of_day){
		setsomething_int(course_number,course_of_day,"course_of_day");
	}
	
	
	/*
	 * ����¿γ�
	 */
	public synchronized String addCourse(Map<String, Object> course) {
		Connection conn = null;
		Statement stmt; // ��������
		String course_number = "" + course.get("course_number");

		if (isExist(course_number))
			return "The course has existed!";
		try {
			conn = DBUtil.getConnForMySql();
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into course(course_number) values('"
					+ course_number+ "');");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
		setname(course_number,""+course.get("name"));
		setbegin_date(course_number,""+course.get("begin_date"));
		setend_date(course_number,""+course.get("end_date"));
		setmax_allowance(course_number,""+course.get("max_allowance"));
		setcurrent_count(course_number,""+course.get("current_count"));
		setlocation(course_number,""+course.get("location"));
		setdescription(course_number,""+course.get("description"));
		setday_of_week(course_number,""+course.get("day_of_week"));
		setcourse_of_day(course_number,""+course.get("course_of_day"));
		setcredit(course_number,""+course.get("credit"));
		sethour(course_number,""+course.get("hour"));
		setteacher(course_number,""+course.get("teacher"));

		return "Add course succeed!";
	}

	public synchronized void removestudent_course(String course_number) {
		Connection conn = null;
		Statement stmt; // ��������

		try {
			conn = DBUtil.getConnForMySql();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from student_course where course_number = '" + course_number + "';");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
	}
	
	public synchronized String removeCrouse(String course_number) {
		Connection conn = null;
		Statement stmt; // ��������

		if (!isExist(course_number))
			return "The course hasn't existed!";
		try {
			conn = DBUtil.getConnForMySql();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from course  where course_number = '" + course_number + "';");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
		
		removestudent_course(course_number);
		return "Delete course succeed!";
	}
	
	public synchronized String addstudent_to_course(String email,String course_number){
		Connection conn = null;
		Statement stmt; // ��������
		int maxC,nowC;
		
		if (isExist(email,course_number))
			return "The record has existed!";
		maxC=getmax_allowance(course_number);
		nowC=getcurrent_count(course_number);
		if(nowC >= maxC)
			return "Hava no capacity!";
		setcurrent_count(course_number,""+(nowC+1));
		try {
			conn = DBUtil.getConnForMySql();
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into student_course(email,course_number) values('"
					+ email +"' , '"+ course_number + "');");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
		return "add student to course succeed!";
	}
	
	public synchronized String removestudent_to_Crouse(String email,String course_number,String status) {
		Connection conn = null;
		Statement stmt; // ��������

		if (!isExist(email,course_number))
			return "The record hasn't existed!";
		
		int nowC=getcurrent_count(course_number);
		setcurrent_count(course_number,""+(nowC-1));
		
		try {
			conn = DBUtil.getConnForMySql();
			stmt = conn.createStatement();
			stmt.executeUpdate("update student_course set status"  + "='"
					+ status + "' where course_number = '" + course_number +  "' AND email = '" + email + "';");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
		
		removestudent_course(course_number);
		return "Delete student in course succeed!";
	}
	
	public synchronized ArrayList<String> get_student_course_list(String email,String status) {
		String sqlstr = "select course_number from student_course where email='" + 
	email + "' AND status='" + status + "';";
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();

		try {
			conn = DBUtil.getConnForMySql();
			cs = conn.createStatement();
			rs = cs.executeQuery(sqlstr);
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.CloseResources(conn, cs);
		}
		return result;
	}
	
	public synchronized ArrayList<String> get_course_student_list(String course_number,String status) {
		String sqlstr = "select email from student_course where course_number='" + 
	course_number + "' AND status='" + status + "';";
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();

		try {
			conn = DBUtil.getConnForMySql();
			cs = conn.createStatement();
			rs = cs.executeQuery(sqlstr);
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.CloseResources(conn, cs);
		}
		return result;
	}
	
	public synchronized ArrayList<String> get_all_courses() {
		String sqlstr = "select course_number from courses where 1=1";
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();

		try {
			conn = DBUtil.getConnForMySql();
			cs = conn.createStatement();
			rs = cs.executeQuery(sqlstr);
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.CloseResources(conn, cs);
		}
		return result;
	}
}
