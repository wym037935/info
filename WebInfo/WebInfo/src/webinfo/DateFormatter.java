package webinfo;

import java.util.*;
import java.text.SimpleDateFormat;

public class DateFormatter {
	public static String format(Date d)
	{
		SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd");
		return format.format(d);
	}
}
