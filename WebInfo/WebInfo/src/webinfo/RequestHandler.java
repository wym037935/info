package webinfo;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.*;

/**
 * Servlet implementation class RequestHandler
 */
@WebServlet("/RequestHandler")
public class RequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private HtmlResponder responder;
	
	private DataBase dataBase;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestHandler() {
        super();
        dataBase=new DataBase();
        dataBase.init();
        responder=new HtmlResponder(dataBase);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		Map<String,String[]> parameterMap=request.getParameterMap();
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter responseWriter=response.getWriter();
		if(parameterMap.get("target")==null)
		{
			responseWriter.write(responder.getResponse(null,null));
			return;
		}
		
		if(parameterMap.get("target")[0].equals("logout"))
		{
			HttpSession session=request.getSession();
			response.sendRedirect("RequestHandler");
			session.invalidate();
			return;
		}
		
		if(parameterMap.get("user")==null)
		{
			System.out.println(parameterMap.get("target")[0]);
			responseWriter.write(responder.getResponse(parameterMap,null));
			return;
		}
		
		if(parameterMap.get("session")==null)
		{	
			responseWriter.write(responder.getResponse(parameterMap,parameterMap.get("user")[0]));
			return;
		}
		
		HttpSession session=request.getSession();
		System.out.println(parameterMap.get("target")[0]);
		System.out.println(parameterMap.get("user")[0]);
		if(session.getAttribute("user")==null)
			return;
		responseWriter.write(responder.getResponse(parameterMap,(String)session.getAttribute("user")));
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println(request.getQueryString());
		String action=request.getQueryString();
		if(action==null)
			return;
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter responseWriter=response.getWriter();
		
		Map<String,String[]> parameterMap=request.getParameterMap();
		
		if(action.equals("login"))
		{
			String username=parameterMap.get("username")[0];
			String password=parameterMap.get("password")[0];
			User userDb=new User();
			String loginResponse=userDb.logIn(username,password);
			System.out.println(loginResponse);
			if(loginResponse.equals("successful"))
			{
				HttpSession session=request.getSession();
				session.setAttribute("user",username);
				response.sendRedirect("RequestHandler?target=home&user="+username+"&session=true");
			}
			else
			{
				response.sendRedirect("RequestHandler?target=login&error="+loginResponse);
			}
			return;
		}

		if(action.equals("register"))
		{
			if(InfoValidator.verifyEmail(parameterMap.get("email")[0])==false)
			{
				responseWriter.write(responder.getRegisterHtml("InvalidEmail"));
				return;
			}
			if(!parameterMap.get("email")[0].equals(parameterMap.get("email_confirm")[0]))
			{
				responseWriter.write(responder.getRegisterHtml("EmailMatchingFailed"));
				return;
			}
			if(InfoValidator.verifyPassword(parameterMap.get("password")[0])==false)
			{
				responseWriter.write(responder.getRegisterHtml("InvalidPassword"));
				return;
			}
			if(!parameterMap.get("password")[0].equals(parameterMap.get("password_confirm")[0]))
			{
				responseWriter.write(responder.getRegisterHtml("PasswordMatchingFailed"));
				return;
			}
			if(parameterMap.get("permission")[0].length()==0)
			{
				responseWriter.write(responder.getRegisterHtml("PermissionEmpty"));
				return;
			}
			if(parameterMap.get("real_name")[0].length()==0)
			{
				responseWriter.write(responder.getRegisterHtml("RealNameInvalid"));
				return;
			}
			if(parameterMap.get("alias")[0].length()==0)
			{
				responseWriter.write(responder.getRegisterHtml("AliasEmpty"));
				return;
			}
			if(parameterMap.get("gender")[0].length()==0)
			{
				responseWriter.write(responder.getRegisterHtml("GenderEmpty"));
				return;
			}
			if(InfoValidator.verifyBirthday(parameterMap.get("year_of_birth")[0],parameterMap.get("month_of_birth")[0],parameterMap.get("date_of_birth")[0])==false)
			{
				responseWriter.write(responder.getRegisterHtml("BirthdayInvalid"));
				return;
			}
			if(InfoValidator.verifyMobile(parameterMap.get("mobile")[0])==false)
			{
				responseWriter.write(responder.getRegisterHtml("MobileInvalid"));
				return;
			}
			if(parameterMap.get("contact_name")[0].length()==0)
			{
				responseWriter.write(responder.getRegisterHtml("ContactNameEmpty"));
				return;
			}
			if(InfoValidator.verifyMobile(parameterMap.get("contact_mobile")[0])==false)
			{
				responseWriter.write(responder.getRegisterHtml("ContactMobileInvalid"));
				return;
			}
			if(parameterMap.get("home_addr")[0].length()==0)
			{
				responseWriter.write(responder.getRegisterHtml("AddrEmpty"));
				return;
			}
			
			User userDb=new User();
			userDb.setIP(InetAddress.getLocalHost().getHostAddress().toString());
			Map<String,Object> userInfo=new HashMap<String,Object>();
			
			userInfo.put("email",parameterMap.get("email")[0]);
			userInfo.put("password",PasswordEncoder.Encode("MD5",parameterMap.get("password")[0]));
			userInfo.put("permisson", parameterMap.get("permission")[0]);
			userInfo.put("real_name",parameterMap.get("real_name")[0]);
			userInfo.put("alias",parameterMap.get("alias")[0]);
			userInfo.put("gender",parameterMap.get("gender")[0]);
			userInfo.put("year_of_birth",parameterMap.get("year_of_birth")[0]);
			userInfo.put("month_of_birth",parameterMap.get("month_of_birth")[0]);
			userInfo.put("date_of_birth",parameterMap.get("date_of_birth")[0]);
			userInfo.put("mobile",parameterMap.get("mobile")[0]);
			userInfo.put("contact_name",parameterMap.get("contact_name")[0]);
			userInfo.put("contact_mobile",parameterMap.get("contact_mobile")[0]);
			userInfo.put("home_addr",parameterMap.get("home_addr")[0]);
			userInfo.put("activated","no");
			
			String commitResult=userDb.newUser(userInfo);
			if(commitResult.equals("reg_success"))
				response.sendRedirect("RequestHandler?target=reg_success&user="+parameterMap.get("email")[0]);
			else
				responseWriter.write(responder.getRegisterHtml("UserExists"));
			
			return;
		}
		
		if(action.indexOf("update_courses")!=-1)
		{
			Courses courseDb=new Courses();
			
			if(parameterMap.get("courses")==null)
				return;
			String[] newCourses=parameterMap.get("courses");
			String user=action.substring(14);
			for(String i:newCourses)
				courseDb.addstudent_to_course(user,i);
		}
	}
}
