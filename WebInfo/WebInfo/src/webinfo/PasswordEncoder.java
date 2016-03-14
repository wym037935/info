package webinfo;

import java.security.MessageDigest;

public class PasswordEncoder {
	private static String byteArrayToHexString(byte buffer[]) 
	{
		StringBuffer sb = new StringBuffer(buffer.length * 2);  
		for (int i = 0; i < buffer.length; i++) 
		{
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));  
			sb.append(Character.forDigit(buffer[i] & 15, 16));  
		}  
	  
		return sb.toString();  
	}
	
	public static String Encode(String algorithm,String target)
	{
		MessageDigest md;
		String encodedString=null;
		try
		{
			md=MessageDigest.getInstance(algorithm);
			encodedString=byteArrayToHexString(md.digest(target.getBytes()));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return encodedString;
	}
}
