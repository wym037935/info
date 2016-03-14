package webinfo;

import java.util.*;
import java.text.DateFormat;

public class HtmlResponder 
{
	static final String doctype="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n";
	
	static final String meta="<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">\n";
	
	static final String general_css="<link href=\"css/general.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String nav_css="<link href=\"css/navigation_bar.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String card_teacher_css="<link href=\"css/card_teacher.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String add_button_css="<link href=\"css/add_button.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String reg_success_css="<link href=\"css/registration_screen.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String course_forecast_css="<link href=\"css/course_forecast.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String course_list_css="<link href=\"css/course_list.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String course_man_css="<link href=\"css/course_management.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String schedule_css="<link href=\"css/schedule.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String discussion_css="<link href=\"css/discussion.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String public_notice_css="<link href=\"css/public_notice.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String home_page_css="<link href=\"css/home_page.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String login_screen_css="<link href=\"css/login_screen.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String public_res_css="<link href=\"css/public_res.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String reg_screen_css="<link href=\"css/registration_screen.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String res_list_css="<link href=\"css/res_list.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	static final String add_screen_css="<link href=\"css/add_screen.css\" rel=\"stylesheet\" type=\"text/css\">\n";
	
	static final String style="<style type=\"text/css\"></style>\n";
	
	static final String html="<html>\n",_html="</html>\n";
	static final String head="<head>\n",_head="</head>\n";
	static final String body="<body class=\"general\">\n",_body="</body>\n";
	
	DataBase dataBase;
	
	public HtmlResponder(DataBase db)
	{
		dataBase=db;
	}
	
	public String getResponse(Map<String,String[]> parameters,String user)
	{
		if(parameters==null)
			return getLoginHtml(null);
		
		if(parameters.get("target")==null)
			return getLoginHtml(null);
		
		String action=parameters.get("target")[0];
		
		if((action==null)||(action.equals("login")))
		{
			if(parameters.get("error")==null)
				return getLoginHtml(null);
			else
				return getLoginHtml(parameters.get("error")[0]);
		}
		
		if(action.equals("reg_success"))
			return getRegSuccessHtml();
		
		if(action.equals("reg_confirmation"))
		{
			User userDb=new User();
			String acResult=userDb.active(parameters.get("id")[0],parameters.get("vc")[0]);
			if(acResult.equals("activation_success"))
				return getActivationSuccessHtml(user);
			else
				return getActivationFailHtml(user);
		}
		
		if(action.equals("teachers"))
			return getTeachersHtml(user);
		
		if(action.equals("register"))
			return getRegisterHtml("Normal");
		
		if(action.equals("public_res_list"))
			return getResListHtml(user);
		if(action.equals("public_res"))
			return getPublicResHtml(user);
		
		if(action.equals("operation"))
		{
			if(parameters.get("type")[0].equals("public_res"))
			{
				if(parameters.get("op")[0].equals("add"))
					return getAddPublicResHtml(user);
			}
		}
		
		if(action.equals("home"))
			return getHomeHtml(user);
		if(action.equals("schedule"))
			return getScheduleHtml(user);
		if(action.equals("public_notices"))
			return getPublicNoticesHtml();
		if(action.equals("course_forecast"))
			return getForecastHtml(user);
		if(action.equals("courses"))
			return getCourseManagementHtml(user);
		if(action.equals("course_list_full"))
			return getFullCourseListHtml(user);
		if(action.equals("course_list_history"))
			return getCourseHistoryListHtml(user);
		if(action.equals("discussion"))
			return getDiscussionHtml(user);
		
		return get404Html();
	}
	
	public String getLoginHtml(String error)
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>学生信息管理系统</title>\n";
		rhtml+=general_css;
		rhtml+=login_screen_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+=body;
		rhtml+="<div class=\"login_background\">\n";
		rhtml+="<div class=\"login_l\">\n";
		rhtml+="<h1 class=\"login_l\">欢迎来到学生信息管理系统</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<div style=\"margin-left:5%\">\n";
		rhtml+="<p class=\"login_l\">欢迎来到学生信息管理系统，这是你的学习生活的好帮手。</p>\n";
		rhtml+="<p class=\"login_l\">请在右侧登陆或注册，登陆后可以进行课程管理、讨论区发帖等操作。</p>\n";
		rhtml+="<p class=\"login_l\">或点击“无账号登陆”以查看公共资源、教务公告和教师信息。</p>\n";
		rhtml+="</div>\n";
		rhtml+="<br>\n";
		rhtml+="</div>\n";
		rhtml+="<div class=\"login_r\">\n";
		rhtml+="<form action=\"?login\" method=\"post\">\n";
		if(error==null)
			rhtml+="<p>用户名</p>\n";
		else if(error.equals("not_exist"))
			rhtml+="<p style=\"color:red\">用户名不存在</p>\n";
		else if(error.equals("not_activated"))
			rhtml+="<p style=\"color:red\">账户尚未激活</p>\n";
		else if(error.equals("disabled"))
			rhtml+="<p style=\"color:red\">账户已被禁用，请联系管理员</p>\n";
		rhtml+="<input class=\"login_r\" type=\"text\" name=\"username\">\n";
		rhtml+="<p>密码</p>\n";
		rhtml+="<input class=\"login_r\" type=\"password\" name=\"password\">\n";
		rhtml+="<input class=\"login_r\" type=\"submit\" value=\"登陆\">\n";
		rhtml+="</form>\n";
		rhtml+="<p style=\"text-align:right;font-size:10px\"><a class=\"login_r\" href=\"RequestHandler?target=register\">新用户？点此注册</a></p>\n";
		rhtml+="<p style=\"text-align:right;font-size:10px\"><a class=\"login_r\" href=\"?this=this\" target=\"_blank\">无账号登陆</a></p>\n";
		rhtml+="</div>\n";
		rhtml+="</div>\n";
		rhtml+=_body;
		
		rhtml+=_html;
				
		return rhtml;
	}

	public String getRegisterHtml(String mode)
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+="<title>注册－学生信息管理系统</title>";
		rhtml+=meta;
		rhtml+=general_css;
		rhtml+=reg_screen_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+=body;
		rhtml+="<div class=\"card_reg\">\n";
		rhtml+="<h1>请填写你的信息</h1>\n";
		rhtml+="<form name=\"reg_info\" action=\"?register\" method=\"post\">\n";
		rhtml+="<hr>\n";
		rhtml+="<table>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">邮箱：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"text\" name=\"email\"></td>\n";
		if(mode.equals("UserExists"))
			rhtml+="<td class=\"note\" style=\"color:red\">该邮箱已被注册，请登陆。</td>\n";
		else if(mode.equals("InvalidEmail"))
			rhtml+="<td class=\"note\" style=\"color:red\">邮箱格式不正确，请重新输入。</td>\n";
		else
			rhtml+="<td class=\"note\">请填写真实邮箱，注册时需要邮箱验证。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">确认邮箱：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"text\" name=\"email_confirm\"></td>\n";
		if(mode.equals("EmailMatchingFailed"))
			rhtml+="<td class=\"note\" style=\"color:red\">确认邮箱不匹配，请重新填写。</td>\n";
		else
			rhtml+="<td class=\"note\">请重新填写一遍上面的邮箱。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">密码：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"password\" name=\"password\"></td>\n";
		if(mode.equals("InvalidPassword"))
			rhtml+="<td class=\"note\" style:\"color:red\">密码长度不得小于8，请重新填写。</td>\n";
		else
			rhtml+="<td class=\"note\">请填写密码。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">确认密码：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"password\" name=\"password_confirm\"></td>\n";
		if(mode.equals("PasswordMatchingFailed"))
			rhtml+="<td class=\"note\" style:\"color:red\">确认密码不匹配，请重新填写。</td>\n";
		else
			rhtml+="<td class=\"note\">请重新填写一遍上面的密码。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">账户类型：</td>\n";
		rhtml+="<td>";
		rhtml+="<input type=\"radio\" name=\"permission\" value=\"user\">普通用户</input>\n";
		rhtml+="<input type=\"radio\" name=\"permission\" value=\"moderator\">管理员</input>\n";
		rhtml+="</td>";
		if(mode.equals("PermissionEmpty"))
			rhtml+="<td class=\"note\" style:\"color:red\">请选择账户类型。</td>\n";
		else
			rhtml+="<td class=\"note\">请选择账户类型。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="<table>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">真实姓名：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"text\" name=\"real_name\"></td>\n";
		if(mode.equals("RealNameInvalid"))
			rhtml+="<td class=\"note\" style=\"color:red\">必须填写真实姓名。</td>\n";
		else
			rhtml+="<td class=\"note\">必须填写真实姓名。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">昵称：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"text\" name=\"alias\"></td>\n";
		if(mode.equals("AliasEmpty"))
			rhtml+="<td class=\"note\" style=\"color:red\">请填写昵称。</td>\n";
		else
			rhtml+="<td class=\"note\">请填写昵称。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">性别：</td>\n";
		rhtml+="<td class=\"field\">\n";
		rhtml+="<input type=\"radio\" name=\"gender\" value=\"male\">男</input>\n";
		rhtml+="<input type=\"radio\" name=\"gender\" value=\"female\">女</input>\n";
		rhtml+="</td>\n";
		if(mode.equals("GenderEmpty"))
			rhtml+=rhtml+="<td class=\"note\" style=\"color:red\">请选择生理性别</td>\n";
		else
			rhtml+="<td class=\"note\">请选择生理性别。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">出生日期：</td>\n";
		rhtml+="<td class=\"field\">\n";
		rhtml+="<table class=\"inner\">\n";
		rhtml+="<tr>\n";
		rhtml+="<td><input type=\"text\" name=\"year_of_birth\"></td>\n";
		rhtml+="<td>年</td>\n";
		rhtml+="<td><input type=\"text\" name=\"month_of_birth\"></td>\n";
		rhtml+="<td>月</td>\n";
		rhtml+="<td><input type=\"text\" name=\"date_of_birth\"></td>\n";
		rhtml+="<td class=\"end\">日</td>\n";
		rhtml+="<tr>\n";
		rhtml+="</table>\n";
		rhtml+="</td>\n";
		if(mode.equals("BirthdayInvalid"))
			rhtml+="<td class=\"note\">出生日期格式不正确，请重新填写。</td>\n";
		else
			rhtml+="<td class=\"note\">请填写真实出生日期。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="<table>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">手机号：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"text\" name=\"mobile\"></td>\n";
		if(mode.equals("MobileInvalid"))
			rhtml+="<td class=\"note\" style=\"color:red\">请正确填写11位手机号码。</td>";
		else
			rhtml+="<td class=\"note\">请填写11位手机号码。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">紧急联系人：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"text\" name=\"contact_name\"></td>\n";
		if(mode.equals("ContactNameEmpty"))
			rhtml+="<td class=\"note\" style=\"color:red\">请填写紧急联系人姓名。</td>\n";
		else
			rhtml+="<td class=\"note\">请填写紧急联系人姓名。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">紧急联系人手机号：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"text\" name=\"contact_mobile\"></td>\n";
		if(mode.equals("ContactMobileInvalid"))
			rhtml+="<td class=\"note\" style=\"color:red\">请正确填写11位手机号码。</td>";
		else
			rhtml+="<td class=\"note\">请填写紧急联系人11位手机号码。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td class=\"col\">家庭住址：</td>\n";
		rhtml+="<td class=\"field\"><input type=\"text\" name=\"home_addr\"></td>\n";
		if(mode.equals("AddrEmpty"))
			rhtml+="<td class=\"note\" style=\"color:red\">请填写家庭住址。</td>\n";
		else
			rhtml+="<td class=\"note\">请填写家庭住址。</td>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="<table style=\"border:none\">\n";
		rhtml+="<tr><td rowspan=3 class=\"note\">*以上均为必填项。</td></tr>\n";
		rhtml+="</table>\n";
		rhtml+="<input type=\"submit\" action=\"\" name=\"\" value=\"提交\">\n";
		rhtml+="</form>\n";
		rhtml+="</div>\n";
		rhtml+=_body;
		
		rhtml+=_html;
		
		return rhtml;
	}

	public String getRegSuccessHtml()
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>注册成功－学生信息管理系统</title>";
		rhtml+=general_css;
		rhtml+=reg_success_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+=body;
		rhtml+="<div class=\"card_reg\">\n";
		rhtml+="<h1>注册成功！</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<p>感谢你注册学生信息管理系统。</p>\n";
		rhtml+="<p>验证邮件已经发送到你的注册邮箱，请点击邮件中的链接完成注册。</p>\n";
		rhtml+="<br>\n";
		rhtml+="</div>\n";
		rhtml+=_body;
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getActivationSuccessHtml(String user)
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>激活成功－学生信息管理系统</title>";
		rhtml+=general_css;
		rhtml+=reg_screen_css;
		rhtml+=_head;
		
		rhtml+="<body class=\"general\">\n";
		rhtml+="<div class=\"card_reg\">\n";
		rhtml+="<h1>验证成功！</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<p>感谢你注册学生信息管理系统。</p>\n";
		rhtml+="<p>你已经完成邮箱验证，请尽情使用吧。</p>\n";
		rhtml+="<br><br>\n";
		rhtml+="<a class=\"green_button\" href=\"RequestHandler?target=login\">登陆系统</a>\n";
		rhtml+="</div>\n";
		rhtml+="</body>\n";
		rhtml+=_html;
		
		return rhtml;
	}

	public String getActivationFailHtml(String user)
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>激活失败－学生信息管理系统</title>";
		rhtml+=general_css;
		rhtml+=reg_screen_css;
		rhtml+=_head;
		
		rhtml+="<body class=\"general\">\n";
		rhtml+="<div class=\"card_reg\">\n";
		rhtml+="<h1>验证失败！</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<p>非常抱歉，邮箱验证失败了。</p>\n";
		rhtml+="<p>这可能是由于验证邮件已经过期，或者你已经完成了激活。</p>\n";
		rhtml+="<br><br>\n";
		rhtml+="<a class=\"green_button\" href=\"RequestHandler?target=login\">尝试登陆系统</a>\n";
		rhtml+="</div>\n";
		rhtml+="</body>\n";
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getHomeHtml(String user)
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>我的主页－学生信息管理系统</title>";
		rhtml+=general_css;
		rhtml+=nav_css;
		rhtml+=home_page_css;
		rhtml+=schedule_css;
		rhtml+=public_notice_css;
		rhtml+=style;
		rhtml+=_head;
		
		User userDb=new User();
		
		rhtml+=body;
		rhtml+=getNav(user,"home");
		rhtml+="<div class=\"home_page\">\n";
		rhtml+="<div class=\"section\">\n";
		rhtml+="<h1>我的信息</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<br>\n";
		rhtml+="<table class=\"personal_info\">\n";
		rhtml+="<tr>\n";
		rhtml+="<th>昵称</th>\n";
		rhtml+=("<td>"+userDb.getalias(user)+"</td>\n");
		rhtml+="</tr>\n";
		rhtml+="<th>真实姓名</th>\n";
		rhtml+=("<td>"+userDb.getreal_name(user)+"</td>\n");
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<th>邮箱</th>\n";
		rhtml+=("<td>"+user+"</td>\n");
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<th>手机</th>\n";
		rhtml+=("<td>"+userDb.getmobile(user)+"</td>\n");
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="<br><br>\n";
		rhtml+="<a class=\"change_button\" href=\"RequestHandler?target=register\">修改我的信息</a>\n";
		rhtml+="</div>\n";
		rhtml+="<div class=\"section\">\n";
		rhtml+="<h1>教务公告</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<table class=\"notice\">\n";
		rhtml+="<tr>\n";
		rhtml+="<th class=\"name\">公告</th>\n";
		rhtml+="<th class=\"date\">发布日期</th>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="<iframe src=\"RequestHandler?target=public_notices\"></iframe>\n";
		rhtml+="</div>\n";
		rhtml+="<div class=\"section\">\n";
		rhtml+="<h1>教学日历</h1>\n";
		rhtml+="<br>\n";
		rhtml+=("<iframe src=\"RequestHandler?target=course_forecast&user="+user+"&session=true\" style=\"height:83%\"></iframe>\n");
		rhtml+="</div>\n";
		rhtml+="<div class=\"section\">\n";
		rhtml+="<h1>我的课表</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<table class=\"course_schedule\">\n";
		rhtml+="<tr>\n";
		rhtml+="<th class=\"first\"></th>\n";
		rhtml+="<th>周一</th>\n";
		rhtml+="<th>周二</th>\n";
		rhtml+="<th>周三</th>\n";
		rhtml+="<th>周四</th>\n";
		rhtml+="<th>周五</th>\n";
		rhtml+="<th>周六</th>\n";
		rhtml+="<th>周日</th>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+=("<iframe src=\"RequestHandler?target=schedule&user="+user+"&session=true\"></iframe>\n");
		rhtml+="</div>\n";
		rhtml+="</div>\n";
		
		rhtml+=_body;
		rhtml+=_html;
		return rhtml;
	}
	
	public String getScheduleHtml(String user)
	{
		Courses courseDb=new Courses();
		
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>课程表－学生信息管理系统</title>\n";
		rhtml+=schedule_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+="<body>\n";
		rhtml+="<table class=\"course_schedule\" style=\"position:absolute;top:0px;left:0px\">\n";
		String[] schedule=getSchedule(user);
		
		for(int i=0;i<6;i++)
		{
			if(i%2==0)
				rhtml+="<tr>\n";
			else
				rhtml+="<tr class=\"alt\">\n";
			
			rhtml+=("<td class=\"first\">"+(i+1)+"</td>\n");
			
			for(int j=0;j<7;j++)
			{
				if(!schedule[i*7+j].equals(""))
					rhtml+=("<td>"+courseDb.getname(schedule[i*7+j])+"</td>\n");
				else
					rhtml+="<td></td>\n";
			}
			
			rhtml+="</tr>\n";
		}
		rhtml+="</table>\n</body>\n";
		rhtml+=_body;
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getPublicNoticesHtml()
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>教学公告</title>\n";
		rhtml+=public_notice_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+="<body class=\"public_notice\">\n";
		rhtml+="<table>\n";
		Map<String,Map<String,String>> notices=dataBase.getNotices();
		Set<String> noticesKey=notices.keySet();
		Iterator<String> i=noticesKey.iterator();
		boolean alt=false;
		while(i.hasNext())
		{
			if(!alt)
				rhtml+="<tr>\n";
			else
				rhtml+="<tr class=\"alt\">\n";
			alt=!alt;
			String entryKey=i.next();
			rhtml+="<td class=\"name\">\n";
			rhtml+=("<a href=\"RequestHandler?target=public_notice_content&entry="+entryKey+"\" target=\"_blank\">");
			rhtml+=notices.get(entryKey).get("name");
			rhtml+="</a></td>\n";
			rhtml+=("<td class=\"date\">"+notices.get(entryKey).get("date")+"</td>\n");
			rhtml+="</tr>\n";
		}
		rhtml+="</table>\n</body>\n";
		
		return rhtml;
	}
	
	public String getForecastHtml(String user)
	{
		Courses courseDb=new Courses();
		
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>教学日历</title>\n";
		rhtml+=course_forecast_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+="<body class=\"course_forecast\">\n";
		rhtml+="<table>\n";
		Date today=new Date();
		int dayToday=today.getDay();
		int dayTomorrow=(dayToday+1)%7;
		int dayAfter=(dayToday+2)%7;
		dayToday=((dayToday==0)?7:dayToday);
		dayTomorrow=((dayTomorrow==0)?7:dayTomorrow);
		dayAfter=((dayAfter==0)?7:dayAfter);
		String[] schedule=getSchedule(user);
		boolean hasToday=false,hasTomorrow=false,hasDayAfter=false;
		boolean altToday=false,altTomorrow=false,altDayAfter=false;
		String todayHtml="<tr><th colspan=3>今天</tr>\n";
		String tomorrowHtml="<tr><th colspan=3>明天</tr>\n";
		String dayAfterHtml="<tr><th colspan=3>后天</tr>\n";
		for(int i=0;i<6;i++)
		{
			if(!schedule[dayToday-1+7*i].equals(""))
			{
				hasToday=true;
				if(altToday)
					todayHtml+="<tr class=\"alt\">\n";
				else
					todayHtml+="<tr>\n";
				todayHtml+=("<td class=\"name\">"+courseDb.getname(schedule[dayToday-1+7*i])+"</td>\n");
				todayHtml+=("<td class=\"date\">第"+courseDb.getcourse_of_day(schedule[dayToday-1+7*i])+"节</td>\n");
				todayHtml+=("<td class=\"location\">"+courseDb.getlocation(schedule[dayToday-1+7*i])+"</td>\n");
				todayHtml+="</tr>\n";
				altToday=!altToday;
			}
			
			if(!schedule[dayTomorrow-1+7*i].equals(""))
			{
				hasTomorrow=true;
				if(altTomorrow)
					tomorrowHtml+="<tr class=\"alt\">\n";
				else
					tomorrowHtml+="<tr>\n";
				tomorrowHtml+=("<td class=\"name\">"+courseDb.getname(schedule[dayTomorrow-1+7*i])+"</td>\n");
				tomorrowHtml+=("<td class=\"date\">第"+courseDb.getcourse_of_day(schedule[dayTomorrow-1+7*i])+"节</td>\n");
				tomorrowHtml+=("<td class=\"location\">"+courseDb.getlocation(schedule[dayTomorrow-1+7*i])+"</td>\n");
				tomorrowHtml+="</tr>\n";
				altTomorrow=!altTomorrow;
			}
			
			if(!schedule[dayAfter-1+7*i].equals(""))
			{
				hasDayAfter=true;
				if(altDayAfter)
					dayAfterHtml+="<tr class=\"alt\">\n";
				else
					dayAfterHtml+="<tr>\n";
				dayAfterHtml+=("<td class=\"name\">"+courseDb.getname(schedule[dayAfter-1+7*i])+"</td>\n");
				dayAfterHtml+=("<td class=\"date\">第"+courseDb.getcourse_of_day(schedule[dayAfter-1+7*i])+"节</td>\n");
				dayAfterHtml+=("<td class=\"location\">"+courseDb.getlocation(schedule[dayAfter-1+7*i])+"</td>\n");
				dayAfterHtml+="</tr>\n";
				altDayAfter=!altDayAfter;
			}
		}
		
		if(hasToday)
			rhtml+=todayHtml;
		if(hasTomorrow)
			rhtml+=tomorrowHtml;
		if(hasDayAfter)
			rhtml+=dayAfterHtml;
		
		if(!(hasToday||hasTomorrow||hasDayAfter))
			rhtml+="<tr><th colspan=3>近3天内没有安排课程</th></tr>\n";
		
		rhtml+="</table>\n";
		rhtml+=_body;
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getPublicResHtml(String user)
	{
		User userDb=new User();
		String userPermission=userDb.getpermission(user);
		
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>公共资源－学生信息管理系统</title>\n";
		if(userPermission.equals("moderator"))
			rhtml+="<link href=\"css/public_res_man.css\" rel=\"stylesheet\" type=\"text/css\">\n";
		else
			rhtml+=res_list_css;
		rhtml+=general_css;
		rhtml+=nav_css;
		rhtml+=public_res_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+=body;
		rhtml+=getNav(user,"public_resource");
		rhtml+="<div class=\"public_res\">\n";
		rhtml+="<h1>公共资源</h1>\n<br>\n";
		rhtml+="<table>\n";
		rhtml+="<tr>\n";
		rhtml+="<th class=\"name\">资源名称</th>\n";
		rhtml+="<th class=\"type\">类型</th>\n";
		rhtml+="<th class=\"publisher\">上传者</th>\n";
		rhtml+="<th class=\"date\">上传日期</th>\n";
		if(userPermission.equals("moderator"))
			rhtml+="<th class=\"op\">操作</th>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+=("<iframe src=\"RequestHandler?target=public_res_list&user="+user+"\"></iframe>");
		rhtml+=_body;
		
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getResListHtml(String user)
	{
		User userDb=new User();
		String userPermission=userDb.getpermission(user);
		
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>公共资源列表</title>\n";
		if(userPermission.equals("moderator"))
			rhtml+="<link href=\"css/public_res_man.css\" rel=\"stylesheet\" type=\"text/css\">\n";
		else
			rhtml+=res_list_css;
		rhtml+=add_button_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+="<body class=\"res_list\">\n";
		rhtml+="<table style=\"position:absolute;left:0px;top:0px\">\n";
		Resource resDb=new Resource();
		ArrayList<String> allMusic=resDb.get_a_kind_of_resource("音乐");
		ArrayList<String> allImages=resDb.get_a_kind_of_resource("视频");
		ArrayList<String> allVideos=resDb.get_a_kind_of_resource("图片");
		boolean alt=false;
		for(String entry:allMusic)
		{
			rhtml+=(alt?"<tr class=\"alt\">\n":"<tr>\n");
			rhtml+=("<td class=\"name\"><a href=\""+resDb.getcontent(entry)+"\" target=\"_blank\">"+resDb.getdescription(entry)+"</a></td>\n");
			rhtml+=("<td class=\"type\">"+resDb.gettype(entry)+"</td>\n");
			rhtml+=("<td class=\"publisher\">"+resDb.getpublisher(entry)+"</td>\n");
			rhtml+=("<td class=\"date\">"+resDb.getdate(entry)+"</td>\n");
			if(userPermission.equals("moderator"))
			{	rhtml+="<td class=\"op\">";
				rhtml+="<a href=\"RequestHandler?target=operation&op=edit&type=public_res&tar="+entry+"&user="+user+"\">编辑</a>";
				rhtml+=" | ";
				rhtml+="<a href=\"RequestHandler?target=operation&op=delete&type=public_res&tar="+entry+"&user="+user+"\">删除</a>";
				rhtml+="</td>";
			}
			rhtml+="</tr>\n";
			alt=!alt;
		}
		for(String entry:allImages)
		{
			rhtml+=(alt?"<tr class=\"alt\">\n":"<tr>\n");
			rhtml+=("<td class=\"name\"><a href=\""+resDb.getcontent(entry)+"\" target=\"_blank\">"+resDb.getdescription(entry)+"</a></td>\n");
			rhtml+=("<td class=\"type\">"+resDb.gettype(entry)+"</td>\n");
			rhtml+=("<td class=\"publisher\">"+resDb.getpublisher(entry)+"</td>\n");
			rhtml+=("<td class=\"date\">"+resDb.getdate(entry)+"</td>\n");
			if(userPermission.equals("moderator"))
			{	rhtml+="<td class=\"op\">";
				rhtml+="<a href=\"RequestHandler?target=operation&op=edit&type=public_res&tar="+entry+"&user="+user+"\">编辑</a>";
				rhtml+=" | ";
				rhtml+="<a href=\"RequestHandler?target=operation&op=delete&type=public_res&tar="+entry+"&user="+user+"\">删除</a>";
				rhtml+="</td>";
			}
			rhtml+="</tr>\n";
			alt=!alt;
		}
		for(String entry:allVideos)
		{
			rhtml+=(alt?"<tr class=\"alt\">\n":"<tr>\n");
			rhtml+=("<td class=\"name\"><a href=\""+resDb.getcontent(entry)+"\" target=\"_blank\">"+resDb.getdescription(entry)+"</a></td>\n");
			rhtml+=("<td class=\"type\">"+resDb.gettype(entry)+"</td>\n");
			rhtml+=("<td class=\"publisher\">"+resDb.getpublisher(entry)+"</td>\n");
			rhtml+=("<td class=\"date\">"+resDb.getdate(entry)+"</td>\n");
			if(userPermission.equals("moderator"))
			{	rhtml+="<td class=\"op\">";
				rhtml+="<a href=\"RequestHandler?target=operation&op=edit&type=public_res&tar="+entry+"&user="+user+"\">编辑</a>";
				rhtml+=" | ";
				rhtml+="<a href=\"RequestHandler?target=operation&op=delete&type=public_res&tar="+entry+"&user="+user+"\">删除</a>";
				rhtml+="</td>";
			}
			rhtml+="</tr>\n";
			alt=!alt;
		}
		rhtml+="</table>\n";
		if(userPermission.equals("moderator"))
			rhtml+=("<a class=\"add_button\" style=\"position:absolute;bottom:100px;right:100px\" target=\"_blank\" href=\"RequestHandler?target=operation&op=add&type=public_res&user="+user+"\">+</a>\n");
		
		rhtml+=_body;
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getAddPublicResHtml(String user)
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>添加公共资源－学生信息管理系统</title>";
		rhtml+=general_css;
		rhtml+=add_screen_css;
		rhtml+=_head;
		
		rhtml+=body;
		rhtml+="<div class=\"card_add\">\n";
		rhtml+="<h1>添加公共资源</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<form action=\"RequestHandler?add_public_res\" method=\"post\">\n";
		rhtml+="<p>描述</p>\n";
		rhtml+="<input type=\"text\" name=\"description\">\n";
		rhtml+="<br>\n";
		rhtml+="<p>链接</p>\n";
		rhtml+="<input type=\"text\" name=\"content\">\n";
		rhtml+="<br>\n";
		rhtml+="<p>上传日期</p>\n";
		rhtml+="<input type=\"text\" readonly value=\""+DateFormatter.format(new Date())+"\" name=\"date\">\n";
		rhtml+="<br>\n";
		rhtml+="<p>上传者</p>\n";
		rhtml+="<input type=\"text\" readonly value=\""+user+"\" name=\"publisher\">\n";
		rhtml+="<br>\n";
		rhtml+="<input type=\"submit\" value=\"添加\">\n";
		rhtml+="</form>\n";
		rhtml+="</div>\n";
		rhtml+="</body>\n";
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getTeachersHtml(String user)
	{
		String rhtml=new String(doctype);
		
		rhtml+=html;
		
		rhtml+=head;
		rhtml+="<title>师资力量－学生信息管理系统</title>";
		rhtml+=meta;
		rhtml+=general_css;
		rhtml+=nav_css;
		rhtml+=card_teacher_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+=body;
		
		rhtml+=getNav(user,"teachers");
		Map<String,Map<String,String>> teachersMap=dataBase.getTeachers();
		Set<String> keys=teachersMap.keySet();
		Iterator<String> i=keys.iterator();
		rhtml+="<div class=\"content_teacher\">\n";
		while(i.hasNext())
		{
			Map<String,String> teacher=teachersMap.get(i.next());
			rhtml+="<div class=\"card_teacher\">\n";
			rhtml+=("<div class=\"card_teacher_img\"><img src=\""+teacher.get("photo")+"\"></div>\n");
			rhtml+="<div class=\"card_teacher_intro\">\n";
			rhtml+=("<h1>"+teacher.get("name")+"</h1>\n");
			rhtml+=("<p>"+teacher.get("description")+"</p>\n");
			rhtml+="</div>\n</div>\n";
		}
		rhtml+="</div>\n";
		
		rhtml+=_body;
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getCourseManagementHtml(String user)
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>课程管理－学生信息管理系统</title>";
		rhtml+=general_css;
		rhtml+=nav_css;
		rhtml+=course_man_css;
		rhtml+=schedule_css;
		rhtml+=course_list_css;
		rhtml+=style;
		rhtml+=_head;
		
		rhtml+=body;
		rhtml+=getNav(user,"courses");
		rhtml+="<div class=\"course\">\n";
		rhtml+="<div class=\"course_list\">\n";
		rhtml+="<h1>可选课程列表</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<table style=\"text-align:center;font-size:10px;width:100%\">\n";
		rhtml+="<tr>\n";
		rhtml+="<th class=\"tick\">已选</th>\n";
		rhtml+="<th class=\"no\">序号</th>\n";
		rhtml+="<th class=\"name\">课程名称</th>\n";
		rhtml+="<th class=\"credit\" style=\"font-size:10px\">学分</th>\n";
		rhtml+="<th class=\"time\">时间</th>\n";
		rhtml+="<th class=\"time_begin\">开始日期</th>\n";
		rhtml+="<th class=\"time_end\">结束日期</th>\n";
		rhtml+="<th class=\"teacher\">教师</th>\n";
		rhtml+="<th class=\"avail\" style=\"font-size:10px\">已选/名额</th>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="<iframe src=\"RequestHandler?target=course_list_full&user="+user+"&session=true\"></iframe>\n";
		rhtml+="</div>\n";
		rhtml+="<div class=\"schedule\">\n";
		rhtml+="<h1>当前课程表</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<table class=\"course_schedule\" style=\"text-align:center;font-size:10px\">\n";
		rhtml+="<tr>\n";
		rhtml+="<th class=\"first\"></th>\n";
		rhtml+="<th>周一</th>\n";
		rhtml+="<th>周二</th>\n";
		rhtml+="<th>周三</th>\n";
		rhtml+="<th>周四</th>\n";
		rhtml+="<th>周五</th>\n";
		rhtml+="<th>周六</th>\n";
		rhtml+="<th>周日</th>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="<iframe src=\"RequestHandler?target=schedule&user="+user+"&session=true\"></iframe>\n";
		rhtml+="</div>\n";
		rhtml+="<div class=\"history\">\n";
		rhtml+="<h1>选课历史</h1>\n";
		rhtml+="<br>\n";
		rhtml+="<table class=\"course_list\" style=\"text-align:center;font-size:10px;width:100%\">\n";
		rhtml+="<tr>\n";
		rhtml+="<th class=\"tick\">已选</th>\n";
		rhtml+="<th class=\"no\">序号</th>\n";
		rhtml+="<th class=\"name\">课程名称</th>\n";
		rhtml+="<th class=\"credit\">学分</th>\n";
		rhtml+="<th class=\"time\">时间</th>\n";
		rhtml+="<th class=\"time_begin\">开始日期</th>\n";
		rhtml+="<th class=\"time_end\">结束日期</th>\n";
		rhtml+="<th class=\"teacher\">教师</th>\n";
		rhtml+="<th class=\"avail\">已选/名额</th>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="<iframe src=\"RequestHandler?target=course_list_history&user="+user+"&session=true\"></iframe>\n";
		rhtml+="</div>\n";
		rhtml+="</div>\n";
		rhtml+=_body;
		
		rhtml+=_html;
		return rhtml;
	}
	
	public String getFullCourseListHtml(String user)
	{
		Courses courseDb=new Courses();
		
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>全部课程列表</title>\n";
		rhtml+=course_list_css;
		rhtml+=_head;
		
		rhtml+="<body class=\"course_list\">\n";
		rhtml+="<form name=\"course_selection\" action=\"RequestHandler?update_courses"+user+"\" method=\"post\">\n";
		rhtml+="<table style=\"position:absolute;top:0px;left:0px\">\n";
		ArrayList<String> allCourses=courseDb.get_all_courses();
		ArrayList<String> currentCourses=courseDb.get_student_course_list(user,"current");
		boolean alt=false;
		for(String id:allCourses)
		{
			rhtml+="<tr>\n";
			if(currentCourses!=null)
			{
				if(currentCourses.indexOf(id)!=-1)
					rhtml+=("<td class=\"tick"+(alt?"_alt":"")+"\"><input type=\"checkbox\" name=\"courses\" checked=1 value=\""+id+"\" ></td>\n");
				else
					rhtml+=("<td class=\"tick"+(alt?"_alt":"")+"\"><input type=\"checkbox\" name=\"courses\" value=\""+id+"\" ></td>\n");
			}
			else
				rhtml+=("<td class=\"tick"+(alt?"_alt":"")+"\"><input type=\"checkbox\" name=\"courses\" value=\""+id+"\" ></td>\n");
			rhtml+=("<td class=\"no"+(alt?"_alt":"")+"\">"+id+"</td>\n");
			rhtml+=("<td class=\"name"+(alt?"_alt":"")+"\">"+courseDb.getname(id)+"</td>\n");
			rhtml+=("<td class=\"credit"+(alt?"_alt":"")+"\">"+courseDb.getcredit(id)+"</td>\n");
			rhtml+=("<td class=\"time"+(alt?"_alt":"")+"\">"+courseDb.getday_of_week(id)+"-"+courseDb.getcourse_of_day(id)+"</td>\n");
			rhtml+=("<td class=\"time_begin"+(alt?"_alt":"")+"\">"+courseDb.getbegin_date(id)+"</td>\n");
			rhtml+=("<td class=\"time_end"+(alt?"_alt":"")+"\">"+courseDb.getend_date(id)+"</td>\n");
			rhtml+=("<td class=\"teacher"+(alt?"_alt":"")+"\">"+courseDb.getteacher(id)+"</td>\n");
			rhtml+=("<td class=\"avail"+(alt?"_alt":"")+"\">"+courseDb.getcurrent_count(id)+"/"+courseDb.getmax_allowance(id)+"</td>\n");
			rhtml+="</tr>\n";
			alt=!alt;
		}
		rhtml+="</table>\n";
		rhtml+="<input class=\"course_list\" type=\"submit\" onclick=\"parent.location.reload()\" value=\"\"></input>\n";
		rhtml+="</form>\n";
		
		rhtml+=_body;
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getCourseHistoryListHtml(String user)
	{
		Courses courseDb=new Courses();
		
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>选课历史列表</title>\n";
		rhtml+=course_list_css;
		rhtml+=_head;
		
		rhtml+="<body class=\"course_list\">\n";
		rhtml+="<form name=\"course_selection\" action=\"RequestHandler?update_courses"+user+"\" method=\"post\">\n";
		rhtml+="<table style=\"position:absolute;top:0px;left:0px\">\n";
		ArrayList<String> currentCourses=courseDb.get_student_course_list(user,"current");
		ArrayList<String> pastCourses=courseDb.get_student_course_list(user,"past");
		boolean alt=false;
		for(String id:currentCourses)
		{
			rhtml+="<tr>\n";
			rhtml+=("<td class=\"tick"+(alt?"_alt":"")+"\"><input type=\"checkbox\" name=\"courses\" checked=1 value=\""+id+"\" ></td>\n");
			rhtml+=("<td class=\"no"+(alt?"_alt":"")+"\">"+id+"</td>\n");
			rhtml+=("<td class=\"name"+(alt?"_alt":"")+"\">"+courseDb.getname(id)+"</td>\n");
			rhtml+=("<td class=\"credit"+(alt?"_alt":"")+"\">"+courseDb.getcredit(id)+"</td>\n");
			rhtml+=("<td class=\"time"+(alt?"_alt":"")+"\">"+courseDb.getday_of_week(id)+"-"+courseDb.getcourse_of_day(id)+"</td>\n");
			rhtml+=("<td class=\"time_begin"+(alt?"_alt":"")+"\">"+courseDb.getbegin_date(id)+"</td>\n");
			rhtml+=("<td class=\"time_end"+(alt?"_alt":"")+"\">"+courseDb.getend_date(id)+"</td>\n");
			rhtml+=("<td class=\"teacher"+(alt?"_alt":"")+"\">"+courseDb.getteacher(id)+"</td>\n");
			rhtml+=("<td class=\"avail"+(alt?"_alt":"")+"\">"+courseDb.getcurrent_count(id)+"/"+courseDb.getmax_allowance(id)+"</td>\n");
			rhtml+="</tr>\n";
			alt=!alt;
		}
		for(String id:pastCourses)
		{
			rhtml+="<tr>\n";
			rhtml+=("<td class=\"tick"+(alt?"_alt":"")+"\"><input type=\"checkbox\" name=\"courses\" checked=1 value=\""+id+"\" ></td>\n");
			rhtml+=("<td class=\"no"+(alt?"_alt":"")+"\">"+id+"</td>\n");
			rhtml+=("<td class=\"name"+(alt?"_alt":"")+"\">"+courseDb.getname(id)+"</td>\n");
			rhtml+=("<td class=\"credit"+(alt?"_alt":"")+"\">"+courseDb.getcredit(id)+"</td>\n");
			rhtml+=("<td class=\"time"+(alt?"_alt":"")+"\">"+courseDb.getday_of_week(id)+"-"+courseDb.getcourse_of_day(id)+"</td>\n");
			rhtml+=("<td class=\"time_begin"+(alt?"_alt":"")+"\">"+courseDb.getbegin_date(id)+"</td>\n");
			rhtml+=("<td class=\"time_end"+(alt?"_alt":"")+"\">"+courseDb.getend_date(id)+"</td>\n");
			rhtml+=("<td class=\"teacher"+(alt?"_alt":"")+"\">"+courseDb.getteacher(id)+"</td>\n");
			rhtml+=("<td class=\"avail"+(alt?"_alt":"")+"\">"+courseDb.getcurrent_count(id)+"/"+courseDb.getmax_allowance(id)+"</td>\n");
			rhtml+="</tr>\n";
			alt=!alt;
		}
		rhtml+="</table>\n";
		rhtml+="<input class=\"course_list\" type=\"submit\" onclick=\"parent.location.reload()\" value=\"\"></input>\n";
		rhtml+="</form>\n";
		
		rhtml+=_body;
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String getDiscussionHtml(String user)
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>讨论区－学生信息管理系统</title>\n";
		rhtml+=discussion_css;
		rhtml+=nav_css;
		rhtml+=general_css;
		rhtml+=add_button_css;
		rhtml+=_head;
		
		rhtml+=body;
		
		rhtml+=getNav(user,"discussion");
		
		rhtml+="<div class=\"controls\">\n";
		rhtml+="<div class=\"category\">\n";
		rhtml+="<table class=\"cat_list\">\n";
		rhtml+="<tr>\n";
		rhtml+="<th>分类浏览</th>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td><a href=\"navigation_bar_test.html\" target=\"_blank\">全部</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td>时事</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td>学习</td>\n";
		rhtml+="</tr>\n";
		rhtml+="<tr>\n";
		rhtml+="<td>体育</td>\n";
		rhtml+="</tr>\n";
		rhtml+="</table>\n";
		rhtml+="</div>\n";
		rhtml+="</div>\n";
		
		rhtml+="<div class=\"discussion\">";
		for(int i=0;i<10;i++)
		{
			rhtml+="<div class=\"post\">\n";
			rhtml+="<table class=\"post_content\">\n";
			rhtml+="<tr>\n";
			rhtml+="<th colspan=5>An American in ISIS's Retweet Army</th>\n";
			rhtml+="</tr>\n";
			rhtml+="<tr>\n";
			rhtml+="<td colspan=5>\"Until early last year, a Twitter account linked to McCain included mostly mundane messages to friends about basketball—how the Lakers suck, comments about the Chicago Bulls—with only a few messages about Allah or Islam,\" NBC noted. \"Then the account went silent for more than a year.\" McCain, who converted to Islam in 2004 and also appears to have used networks like Facebook and MySpace, fired up his feed again in mid-May—around the time that ISIS was publicizing its control over the Syrian city of Raqqa with public executions, and just weeks before the group launched its military offensive in northern Iraq.</td>\n";
			rhtml+="</tr>\n";
			rhtml+="<tr class=\"stats\">\n";
			rhtml+="<td class=\"date\">发布时间：2014-9-15</td>\n";
			rhtml+="<td class=\"publisher\">发布者：Galath</td>\n";
			rhtml+="<td class=\"type\">分类：政治，时事</td>\n";
			rhtml+="<td class=\"view\">浏览：200</td>\n";
			rhtml+="<td class=\"response\">回复：9</td>\n";
			rhtml+="</tr>\n";
			rhtml+="</table>\n";
			rhtml+="</div>\n";
		}
		rhtml+="<a class=\"add_button\" href=\"\">+</a>\n";
		rhtml+="</div>\n";
		rhtml+=_body;
		rhtml+=_html;
		
		return rhtml;
	}
	
	public String get404Html()
	{
		String rhtml=new String(doctype);
		rhtml+=html;
		rhtml+=head;
		rhtml+=meta;
		rhtml+="<title>404－学生信息管理系统</title>";
		rhtml+=general_css;
		rhtml+=reg_screen_css;
		rhtml+=_head;
		
		rhtml+="<body class=\"general\">\n";
		rhtml+="<div class=\"card_reg\">\n";
		rhtml+="<h1>404：你发现了未知。</h1>\n";
		rhtml+="<br><br>\n";
		rhtml+="<p>We work hard to cover all your needs.</p>\n";
		rhtml+="<p>But there will be times when you are on you own.</p>\n";
		rhtml+="<p>Good luck.</p>\n";
		rhtml+="<br><br>\n";
		rhtml+="</div>\n";
		rhtml+="</body>\n";
		rhtml+=_html;
		
		return rhtml;
	}
	
	private static String getNav(String user,String section)
	{
		String rhtml="";
		rhtml+="<div class=\"nav\">\n";
		rhtml+="<ul class=\"nav\">\n";
		
		rhtml+="<li class=\"nav_header\"><a class=\"nav\" href=\"\">学生信息管理系统</a></li>\n";
		
		rhtml+=("<li class=\"nav_l\"><a class=\""+(section.equals("home")?"nav_current":"nav")+"\" href=\"RequestHandler?target=home&user="+user+"&session=true\">我的首页</a></li>\n");
		rhtml+=("<li class=\"nav_l\"><a class=\""+(section.equals("public_resource")?"nav_current":"nav")+"\" href=\"RequestHandler?target=public_res&user="+user+"&session=true\">公共资源</a></li>\n");
		rhtml+=("<li class=\"nav_l\"><a class=\""+(section.equals("teachers")?"nav_current":"nav")+"\" href=\"RequestHandler?target=teachers&user="+user+"&session=true\">师资力量</a></li>\n");
		rhtml+=("<li class=\"nav_l\"><a class=\""+(section.equals("courses")?"nav_current":"nav")+"\" href=\"RequestHandler?target=courses&user="+user+"&session=true\">课程管理</a></li>\n");
		rhtml+=("<li class=\"nav_l\"><a class=\""+(section.equals("discussion")?"nav_current":"nav")+"\" href=\"RequestHandler?target=discussion&user="+user+"&session=true\">讨论区</a></li>\n");
		rhtml+="<li class=\"nav_r\"><a class=\"nav\" href=\"RequestHandler?target=logout&user="+user+"\">登出</a></li>\n";
		
		rhtml+="</ul>\n</div>\n";
		
		return rhtml;
	}

	private String[] getSchedule(String user)
	{
		Courses courseDb=new Courses();
		
		String[] r=new String[42];
		for(int i=0;i<42;i++)
			r[i]=new String();
		ArrayList<String> courseList=courseDb.get_student_course_list(user,"current");
		for(String i:courseList)
		{
			r[(courseDb.getcourse_of_day(i)-1)*7+courseDb.getday_of_week(i)-1]=i;
		}
		
		return r;
	}
}
