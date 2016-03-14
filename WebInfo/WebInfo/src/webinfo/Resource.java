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

public class Resource {
	
	public boolean isExist(String name,String type) {
		String sqlstr = "select name from resource where name = '" + name
				+ "' AND type = '"+type+"';";
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
	 * @param name
	 *            ����Ҫ��ȡ�û���userName
	 * @return rs.getString(1), ���ض�ȡ����Ϣ
	 * 
	 * */
	/**
	 * setXXX�����ݿ���д������
	 * 
	 * @param email
	 *            ��xxx��Ҫ��email���������д�������xxx
	 * 
	 * */

	 private synchronized String getsomething(String name, String something) {
		String sqlstr = "select " + something + " from resource where name = '"
				+ name + "';";
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

	private synchronized void setsomething(String name, String newValue, String something) {
		Connection conn = null;
		Statement stmt; // ��������

		try {
			conn = DBUtil.getConnForMySql();
			System.out.println("Connect to database");
			stmt = conn.createStatement();
			stmt.executeUpdate("update resource set " + something + "='"
					+ newValue + "' where name='" + name + "';");

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
	}
	
	public String gettype(String name){
		return getsomething(name,"type");
	}
	
	public void settype(String name,String type){
		setsomething(name,type,"type");
	}
	
	public String getcontent(String name){
		return getsomething(name,"content");
	}
	
	public void setcontent(String name,String content){
		setsomething(name,content,"content");
	}
	
	public String getdate(String name){
		return getsomething(name,"date");
	}
	
	public void setdate(String name,String date){
		setsomething(name,date,"date");
	}
	
	public String getpublisher(String name){
		return getsomething(name,"publisher");
	}
	
	public void setpublisher(String name,String publisher){
		setsomething(name,publisher,"publisher");
	}
	
	public String getdescription(String name){
		return getsomething(name,"description");
	}
	
	public void setdescription(String name,String description){
		setsomething(name,description,"description");
	}
	
	
	/*�������Դ
	 * 
	 */
	public synchronized String newResource(Map <String,Object> resource){
		Connection conn = null;
		Statement stmt; // ��������
		String name = ""+resource.get("name");
		String type = ""+resource.get("type");
		
		if(isExist(name,type))
			return "The resource has existed!";
		try {
			conn = DBUtil.getConnForMySql();
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into resource(name,type,content) values('"+
			name+"','"+type+"','"+resource.get("content")+"');" );

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
		
		setdate(name,""+resource.get("date"));
		setpublisher(name,""+resource.get("publisher"));
		setdescription(name,""+resource.get("description"));
		
		return "Add resource succeed!";
	}

	public synchronized String deleteResource(String name,String type){
		Connection conn = null;
		Statement stmt; // ��������
		
		if(!isExist(name,type))
			return "The resource hasn't existed!";
		try {
			conn = DBUtil.getConnForMySql();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from resource  where name = '" + name
				+ "' AND type = '"+type+"';" );

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		} finally {
			DBUtil.CloseResources(conn);
		}
		return "Delete resource succeed!";
	}
	public synchronized ArrayList<String> get_a_kind_of_resource(String type){
		String sqlstr = "select name from resource where type='"+type+"';";
		Connection conn = null;
		Statement cs = null;
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();
		
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
}
