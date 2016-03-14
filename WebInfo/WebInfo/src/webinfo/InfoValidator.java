package webinfo;

import java.util.regex.*;

public class InfoValidator {
	public static boolean verifyEmail(String email)
	{
		Pattern p=Pattern.compile("^\\w+@((\\w)+)(\\.(\\w)+)+$");
		Matcher m=p.matcher(email);
		if(m.matches())
			return true;
		return false;
	}
	
	public static boolean verifyPassword(String password)
	{
		if(password.length()<8)
			return false;
		return true;
	}
	
	public static boolean verifyMobile(String mobile)
	{
		Pattern p=Pattern.compile("^\\d{11}$");
		Matcher m=p.matcher(mobile);
		if(m.matches())
			return true;
		return false;
	}
	
	public static boolean verifyBirthday(String yy,String mm,String dd)
	{
		try
		{
			int yyVal=Integer.parseInt(yy);
			int mmVal=Integer.parseInt(mm);
			int ddVal=Integer.parseInt(dd);
			if(yyVal>2014)
				return false;
			if((mmVal<1)||(mmVal>12))
				return false;
			if(ddVal<1)
				return false;
			if(ddVal>31)
				return false;
			if(mmVal==2)
			{
				if((yyVal%4==0)&&(ddVal>29))
					return false;
				if((yyVal%4!=0)&&(ddVal>28))
					return false;
				return true;
			}
			if(ddVal>30)
			{
				if((mmVal==1)||(mmVal==3)||(mmVal==5)||(mmVal==7)||(mmVal==8)||(mmVal==10)||(mmVal==12))
					return true;
				else
					return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
