import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Send {
	
    static final String[] FROM = {"tempuser12@test1.com","tempuser12@test2.net"};
    static final String FROMNAME = "From";
    static final String[] TO = {"*****@icloud.com","*******@hotmail.com", "*******@naver.com", "******@gmail.com"
			"****@yahoo.com", "****@yahoo.com", "****@yahoo.com", "****@yahoo.com", "****@yahoo.com"};
 
    static final String SMTP_USERNAME = "gmailID@gmail.com";
    static final String SMTP_PASSWORD = "gmailPassword";
    
    static final String[] HOST = {"ip1","ip2","ip3",
    			"ip4","ip5","ip6",
    			"ip7","ip8","ip9","ip10"};
    
    static final int PORT = 25;
    
    static final String SUBJECT = "����Ʈ�����к� ���л� �������� ������û";
    
    static final String BODY = String.join(
        System.getProperty("line.separator"),
        "<h1>����Ʈ�����к� ���л� �������� ������û</h1>",
        "<p>�ȳ��ϼ���.\r\n" + 
        "\r\n" + 
        "���ϲ��� ������ �ֽ� �ڷ�� ���� ������ ������ ������ ���� ������ �ڷ�� Ȱ��� ���̸�,\r\n" + 
        "�����Ͻ� ������ ���� �� 33���� �ǰ��Ͽ� ����� ����˴ϴ�.\r\n" + 
        "���� ������ �����Ͻþ� �ٻڽô��� ��� �ð��� ���ּż� ������ �ֽø� �����ϰڽ��ϴ�.</p>."
    );
    
    public static int HOSTloop,TOloop,FROMloop = 0; // WorkTask���� �������鼭 ���� ��� �ٲ������ϴϱ�
    public static int totalFROM = FROM.length; // 2
    public static int totalTO = TO.length; // 9
    public static int totalHOST = HOST.length; // 10
    
    
    public static void send() throws UnsupportedEncodingException, MessagingException {
    		
    		Properties props = System.getProperties();
    		props.put("mail.transport.protocol", "smtp");
    		props.put("mail.smtp.host", HOST[HOSTloop]);
    		props.put("mail.smtp.port", PORT); 
    		props.put("mail.smtp.starttls.enable", "true");
    		props.put("mail.smtp.auth", "true");
    		props.put("mail.user", SMTP_USERNAME);
    		props.put("mail.password", SMTP_PASSWORD);
    		
    		if(HOSTloop > totalHOST/2 - 1) { // HOST[0] ~ HOST[4]������ ".com" ���, HOST[5] ~ HOST[9]������ ".net" ���
    			
    			FROMloop = 1;
    			
    		} else {
    			
    			FROMloop = 0;
    			
    		}
            
    			Authenticator auth = new Authenticator() {
    				
    				public PasswordAuthentication getPasswordAuthentication() {
    					
                    return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
                    
    				}
    				
    			};
            
    			Session session = Session.getDefaultInstance(props, auth);
    			MimeMessage msg = new MimeMessage(session);
    			msg.setFrom(new InternetAddress(FROM[FROMloop], FROMNAME));
    			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO[TOloop]));
    			msg.setSubject(SUBJECT);
    			msg.setContent(BODY, "text/html;charset=euc-kr");
            
    			Transport transport = session.getTransport();
     
    			try {
    				
    				transport.connect(HOST[HOSTloop], SMTP_USERNAME, SMTP_PASSWORD);
    				transport.sendMessage(msg, msg.getAllRecipients());
     
    				System.out.println("[" + "To : " + TO[TOloop] + "]" + "Email sent!");
    				System.out.println("[" + "FROM : " + FROM[FROMloop] + "(" + HOST[HOSTloop] + ")" +"]" );
    				
    				if(HOSTloop != totalHOST - 1) {
    					
    					HOSTloop++;
    				
    				} else { // ������ ip���� ������ ��, �ٽ� ù��° ip�� ������ ���� && �޴� ���ϰ����� ���������� ����(���� �� �������� �ٽ� �ѽð� ���� ������ ���� ù��° ��ġ��)
    					
    					HOSTloop = 0; 
    					
    					if(TOloop != totalTO - 1) {
    						
    						TOloop++;
    						
    					} else {
    					
    						TOloop = 0;	
    						
    					}
    					
    				} 
    				
    				
    			} catch (Exception ex) {
    				
    				ex.printStackTrace();
     
    			} finally {
    				
    				transport.close();
                
    			}
    			
    	}
    
}