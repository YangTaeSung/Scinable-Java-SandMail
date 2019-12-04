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
    static final String[] TO = {"*****@icloud.com","*******@hotmail.com", "*******@naver.com", "******@gmail.com"};
 
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
    
    public static int HOSTNUM,TONUM,FROMNUM = 0; // WorkTask���� �������鼭 ���� ��� �ٲ������ϴϱ�
    
    
    public static void send() throws UnsupportedEncodingException, MessagingException {
    		
    		Properties props = System.getProperties();
    		props.put("mail.transport.protocol", "smtp");
    		props.put("mail.smtp.host", HOST[HOSTNUM]);
    		props.put("mail.smtp.port", PORT); 
    		props.put("mail.smtp.starttls.enable", "true");
    		props.put("mail.smtp.auth", "true");
    		props.put("mail.user", SMTP_USERNAME);
    		props.put("mail.password", SMTP_PASSWORD);
    		
    		if(HOSTNUM > 4) { // IPó�� 5���� ~~.~~.com ���, ���� 5���� ~~.~~.net ���
    			
    			FROMNUM = 1;
    		} else {
    			
    			FROMNUM = 0;
    			
    		}
            
    			Authenticator auth = new Authenticator() {
    				
    				public PasswordAuthentication getPasswordAuthentication() {
    					
                    return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
                    
    				}
    				
    			};
            
    			Session session = Session.getDefaultInstance(props, auth);
    			MimeMessage msg = new MimeMessage(session);
    			msg.setFrom(new InternetAddress(FROM[FROMNUM], FROMNAME));
    			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO[TONUM]));
    			msg.setSubject(SUBJECT);
    			msg.setContent(BODY, "text/html;charset=euc-kr");
            
    			Transport transport = session.getTransport();
     
    			try {
    				
    				System.out.println("Sending...");
                
    				transport.connect(HOST[HOSTNUM], SMTP_USERNAME, SMTP_PASSWORD);
    				transport.sendMessage(msg, msg.getAllRecipients());
     
    				System.out.println("Email sent!");
    				
    				if(HOSTNUM != 9) { // ������ IP�� �ƴϸ� ����IP�� �θ� �� �ְ� HOSTNUM ���� 
    					
    					HOSTNUM++;
    				
    				} else { // ������ IP�̸� HOSTNUM�� �ʱ�ȭ �Ǿ� �ٸ������� ������ �۽��� �� �ٽ� ù IP���� �ݺ��ϰ� �ȴ�.
    					
    					HOSTNUM = 0;
    					
					// �� �� ���� ������ ������ �������� ����. ������ ������ �ƴ϶�� ���� ������ �θ� �� �ֵ���
    					if(TONUM != 3) { 
    						
    						TONUM++;
    						
    					} else {
    					
    						TONUM = 0;	
    						
    					}
    					
    				} 
    				
    				
    			} catch (Exception ex) {
    				
    				ex.printStackTrace();
     
    			} finally {
    				
    				transport.close();
                
    			}
    			
    	}
    
}
    
