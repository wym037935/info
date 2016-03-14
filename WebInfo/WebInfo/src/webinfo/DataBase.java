package webinfo;

import java.util.*;

public class DataBase {
	Map<String,Map<String,String>> teachers;
	Map<String,Map<String,Object>> users;
	Map<String,Map<String,Object>> courses;
	Map<String,Map<String,String>> public_notices;
	Map<String,Map<String,String>> public_res;
	
	void init()
	{
		teachers=new HashMap<String,Map<String,String>>();
		users=new HashMap<String,Map<String,Object>>();
		courses=new HashMap<String,Map<String,Object>>();
		public_notices=new HashMap<String,Map<String,String>>();
		public_res=new HashMap<String,Map<String,String>>();
		
		teachers.put("Linus Torvalds",new HashMap<String,String>());
		teachers.get("Linus Torvalds").put("name","Linus Torvalds");
		teachers.get("Linus Torvalds").put("description","林纳斯·本纳第克特·托瓦兹(Linus Benedict Torvalds, 1969年~ )，著名的电脑程序员、黑客。Linux内核的发明人及该计划的合作者。托瓦兹利用个人时间及器材创造出了这套当今全球最流行的操作系统（作业系统）内核之一。");
		teachers.get("Linus Torvalds").put("photo","images/teachers/linus_torvalds.jpg");
		
		teachers.put("Linus Torvalds2",new HashMap<String,String>());
		teachers.get("Linus Torvalds2").put("name","Linus Torvalds");
		teachers.get("Linus Torvalds2").put("description","林纳斯·本纳第克特·托瓦兹(Linus Benedict Torvalds, 1969年~ )，著名的电脑程序员、黑客。Linux内核的发明人及该计划的合作者。托瓦兹利用个人时间及器材创造出了这套当今全球最流行的操作系统（作业系统）内核之一。");
		teachers.get("Linus Torvalds2").put("photo","images/teachers/linus_torvalds.jpg");
		
		teachers.put("Linus Torvalds3",new HashMap<String,String>());
		teachers.get("Linus Torvalds3").put("name","Linus Torvalds");
		teachers.get("Linus Torvalds3").put("description","林纳斯·本纳第克特·托瓦兹(Linus Benedict Torvalds, 1969年~ )，著名的电脑程序员、黑客。Linux内核的发明人及该计划的合作者。托瓦兹利用个人时间及器材创造出了这套当今全球最流行的操作系统（作业系统）内核之一。");
		teachers.get("Linus Torvalds3").put("photo","images/teachers/linus_torvalds.jpg");
		
		teachers.put("Linus Torvalds4",new HashMap<String,String>());
		teachers.get("Linus Torvalds4").put("name","Linus Torvalds");
		teachers.get("Linus Torvalds4").put("description","林纳斯·本纳第克特·托瓦兹(Linus Benedict Torvalds, 1969年~ )，著名的电脑程序员、黑客。Linux内核的发明人及该计划的合作者。托瓦兹利用个人时间及器材创造出了这套当今全球最流行的操作系统（作业系统）内核之一。");
		teachers.get("Linus Torvalds4").put("photo","images/teachers/linus_torvalds.jpg");
		
		teachers.put("Linus Torvalds5",new HashMap<String,String>());
		teachers.get("Linus Torvalds5").put("name","Linus Torvalds");
		teachers.get("Linus Torvalds5").put("description","林纳斯·本纳第克特·托瓦兹(Linus Benedict Torvalds, 1969年~ )，著名的电脑程序员、黑客。Linux内核的发明人及该计划的合作者。托瓦兹利用个人时间及器材创造出了这套当今全球最流行的操作系统（作业系统）内核之一。");
		teachers.get("Linus Torvalds5").put("photo","images/teachers/linus_torvalds.jpg");
		
		users.put("harry_accounts@163.com",new HashMap<String,Object>());
		users.get("harry_accounts@163.com").put("email","harry_accounts@163.com");
		users.get("harry_accounts@163.com").put("password","4264debd9a4e90b96173440aa21e6f5607e0421ae4c1e38507b9b5548338bdf33c67f9482c9f423313fdc11d0436f9452c8bbe95bef2f0532630f108d64e1c81");
		users.get("harry_accounts@163.com").put("permission","user");
		users.get("harry_accounts@163.com").put("mobile","11111111111");
		users.get("harry_accounts@163.com").put("real_name","ZTC");
		users.get("harry_accounts@163.com").put("alias","Galath");
		users.get("harry_accounts@163.com").put("courses",new HashMap<String,String>());
		Map<String,String> coursesMap=(Map<String,String>)users.get("harry_accounts@163.com").get("courses");
		coursesMap.put("C++编程基础", "current");
		coursesMap.put("java编程基础", "past");
		coursesMap.put("计算机组成原理", "current");
		coursesMap.put("程序设计基础", "current");
		//coursesMap.put("欧洲文学概论", "current");
	
		courses.put("java编程基础", new HashMap<String,Object>());
		courses.get("java编程基础").put("day_of_week",1);
		courses.get("java编程基础").put("course_of_day",1);
		courses.get("java编程基础").put("name","java编程基础");
		courses.get("java编程基础").put("location","三教3200");
		courses.get("java编程基础").put("course_number","402690");
		courses.get("java编程基础").put("credit","3");
		courses.get("java编程基础").put("begin_date",new Date(2014-1900,9-1,21));
		courses.get("java编程基础").put("end_date",new Date(2014-1900,9-1,21));
		
		courses.put("计算机组成原理", new HashMap<String,Object>());
		courses.get("计算机组成原理").put("day_of_week",2);
		courses.get("计算机组成原理").put("course_of_day",2);
		courses.get("计算机组成原理").put("name","计算机组成原理");
		courses.get("计算机组成原理").put("location","三教3201");
		courses.get("计算机组成原理").put("course_number","402629");
		courses.get("计算机组成原理").put("credit","5");
		courses.get("计算机组成原理").put("begin_date",new Date(2014-1900,9-1,21));
		courses.get("计算机组成原理").put("end_date",new Date(2014-1900,9-1,21));
		
		courses.put("C++编程基础", new HashMap<String,Object>());
		courses.get("C++编程基础").put("day_of_week",3);
		courses.get("C++编程基础").put("course_of_day",4);
		courses.get("C++编程基础").put("name","C++编程基础");
		courses.get("C++编程基础").put("location","六教6C300");
		courses.get("C++编程基础").put("course_number","401990");
		courses.get("C++编程基础").put("credit","3");
		courses.get("C++编程基础").put("begin_date",new Date(2014-1900,9-1,21));
		courses.get("C++编程基础").put("end_date",new Date(2014-1900,9-1,21));
		
		courses.put("程序设计基础", new HashMap<String,Object>());
		courses.get("程序设计基础").put("day_of_week",4);
		courses.get("程序设计基础").put("course_of_day",5);
		courses.get("程序设计基础").put("name","程序设计基础");
		courses.get("程序设计基础").put("location","五教5013");
		courses.get("程序设计基础").put("course_number","296690");
		courses.get("程序设计基础").put("credit","2");
		courses.get("程序设计基础").put("begin_date",new Date(2014-1900,9-1,21));
		courses.get("程序设计基础").put("end_date",new Date(2014-1900,9-1,21));
		
		courses.put("欧洲文学概论", new HashMap<String,Object>());
		courses.get("欧洲文学概论").put("day_of_week",5);
		courses.get("欧洲文学概论").put("course_of_day",6);
		courses.get("欧洲文学概论").put("name","欧洲文学概论");
		courses.get("欧洲文学概论").put("location","六教6B101");
		courses.get("欧洲文学概论").put("course_number","897490");
		courses.get("欧洲文学概论").put("credit","7");
		courses.get("欧洲文学概论").put("begin_date",new Date(2014-1900,9-1,21));
		courses.get("欧洲文学概论").put("end_date",new Date(2014-1900,9-1,21));
		
		public_notices.put("大作业要交不了了",new HashMap<String,String>());
		public_notices.get("大作业要交不了了").put("name","大作业要交不了了");
		public_notices.get("大作业要交不了了").put("date", "2014-09-21");
		public_notices.get("大作业要交不了了").put("content","卧了个槽");
		
		public_notices.put("大作业要交不了了啊",new HashMap<String,String>());
		public_notices.get("大作业要交不了了啊").put("name","大作业要交不了了啊");
		public_notices.get("大作业要交不了了啊").put("date", "2014-09-21");
		public_notices.get("大作业要交不了了啊").put("content","卧了个槽");
		
		public_notices.put("大作业要交不了了啊啊",new HashMap<String,String>());
		public_notices.get("大作业要交不了了啊啊").put("name","大作业要交不了了啊啊");
		public_notices.get("大作业要交不了了啊啊").put("date", "2014-09-21");
		public_notices.get("大作业要交不了了啊啊").put("content","卧了个槽");
		
		public_notices.put("大作业要交不了了啊啊啊",new HashMap<String,String>());
		public_notices.get("大作业要交不了了啊啊啊").put("name","大作业要交不了了啊啊啊");
		public_notices.get("大作业要交不了了啊啊啊").put("date", "2014-09-21");
		public_notices.get("大作业要交不了了啊啊啊").put("content","卧了个槽");
		
		public_notices.put("大作业要交不了了啊啊啊啊",new HashMap<String,String>());
		public_notices.get("大作业要交不了了啊啊啊啊").put("name","大作业要交不了了啊啊啊啊");
		public_notices.get("大作业要交不了了啊啊啊啊").put("date", "2014-09-21");
		public_notices.get("大作业要交不了了啊啊啊啊").put("content","卧了个槽");
		
		public_res.put("无尽之剑3预告片",new HashMap<String,String>());
		public_res.get("无尽之剑3预告片").put("name","无尽之剑3预告片");
		public_res.get("无尽之剑3预告片").put("type","视频");
		public_res.get("无尽之剑3预告片").put("link","http://v.youku.com/v_show/id_XNjA4ODY3MzA4.html");
		public_res.get("无尽之剑3预告片").put("date","2014-09-21");
		public_res.get("无尽之剑3预告片").put("publisher","ZTC");
		
		public_res.put("无尽之剑3预告片",new HashMap<String,String>());
		public_res.get("无尽之剑3预告片").put("name","无尽之剑3预告片");
		public_res.get("无尽之剑3预告片").put("type","视频");
		public_res.get("无尽之剑3预告片").put("link","http://v.youku.com/v_show/id_XNjA4ODY3MzA4.html");
		public_res.get("无尽之剑3预告片").put("date","2014-09-21");
		public_res.get("无尽之剑3预告片").put("publisher","ZTC");
			
		public_res.put("Doge Classic.jpg",new HashMap<String,String>());
		public_res.get("Doge Classic.jpg").put("name","DogeClassic.jpg");
		public_res.get("Doge Classic.jpg").put("type","图片");
		public_res.get("Doge Classic.jpg").put("link","http://p5.yokacdn.com/pic/star/topic/2014/U230P1T1D925309F9DT20140519091616.jpg");
		public_res.get("Doge Classic.jpg").put("date","2014-09-21");
		public_res.get("Doge Classic.jpg").put("publisher","ZTC");
	}
	
	public final Map<String,Map<String,String>> getTeachers()
	{
		return teachers;
	}
	
	public Map<String,Object> getUserForModification(String userID)
	{
		return users.get(userID);
	}
	
	public final Map<String,Object> getUser(String userID)
	{
		return users.get(userID);
	}
	
	public void addUser(String userID,Map<String,Object> userInfo)
	{
		users.put(userID,userInfo);
	}
	
	public final Map<String,Map<String,Object>> getUsers()
	{
		return users;
	}
	
	public final Map<String,Map<String,Object>> getCourses()
	{
		return courses;
	}
	
	public final Map<String,Object> getSingleCourse(String key)
	{
		return courses.get(key);
	}
	
	public final Map<String,Map<String,String>> getNotices()
	{
		return public_notices;
	}
	
	public final Map<String,Map<String,String>> getResources()
	{
		return public_res;
	}
}
/*
class Post implements Comparable {
	public String name;
	public List<String> tags;
	public Date date;
	public List<String> replies;
	public String publisher;
	public String content;
	
	public String id;
	
	public int CompareTo(Object o)
	{
		if(o instanceof Post)
		{
			
		}
	}
}
*/