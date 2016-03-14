package webinfo;

import java.util.Date;
import java.util.Calendar; 
import java.text.SimpleDateFormat;
import java.net.InetAddress;

public class SendMail {
	
	public void Send(String email,String real_name,String Id,String ActiCode,String localHost){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy��MM��dd��");//���Է�����޸����ڸ�ʽ
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost("smtp.163.com");    
	    mailInfo.setMailServerPort("25");    
	    mailInfo.setValidate(true);    
	    mailInfo.setUserName("cs5646216fe2e0332c@163.com");    
	    mailInfo.setPassword("813d3d9c396509f6");//������������    
	    mailInfo.setFromAddress("cs5646216fe2e0332c@163.com");    
	    mailInfo.setToAddress(email);    
	    mailInfo.setSubject("ע��ȷ��");    
	    mailInfo.setContent(real_name+"����ã�\n\r\t"+
	     "��л��ע������Ż��������������Ӽ������ע�᣺\n\r\t"+localHost+
	     ":8080/WebInfo/RequestHandler?target=reg_confirmation&id="+Id+"&vc="+ActiCode+"\n\r\t"+
	     "(��������޷�������뽫��������������ĵ�ַ���С�)\n\r\t"+
	     "��Ľ����Ż��ʺ��ǣ�"+email+"\n\r\t"+
	        "�����Ż�\n\r\t"+dateFormat.format(now)
	        );    
	        //�������Ҫ�������ʼ�   
	    SimpleMailSender sms = new SimpleMailSender();   
	    sms.sendTextMail(mailInfo);//���������ʽ    
	}  
	public static void main(String[] args){
		new SendMail().Send("wym6110@gmail.com","��һ��","12345","cxq940215","http://59.66.130.139");
	}  
}
