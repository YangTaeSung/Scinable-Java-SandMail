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
    
    static final String SUBJECT = "소프트웨어학부 재학생 설문조사 참여요청";
    
    static final String BODY = String.join(
        System.getProperty("line.separator"),
        "<h1>소프트웨어학부 재학생 설문조사 참여요청</h1>",
        "<p>안녕하세요.\r\n" + 
        "\r\n" + 
        "귀하께서 응답해 주신 자료는 대학 교육의 질적인 개선을 위한 소중한 자료로 활용될 것이며,\r\n" + 
        "응답하신 내용은 통계법 제 33조에 의거하여 비밀이 보장됩니다.\r\n" + 
        "설문 취지를 감안하시어 바쁘시더라도 잠시 시간을 내주셔서 응답해 주시면 감사하겠습니다.</p>."
    );
    
    public static int HOSTNUM,TONUM,FROMNUM = 0; // WorkTask에서 루프돌면서 값이 계속 바뀌어줘야하니까
    
    
    public static void send() throws UnsupportedEncodingException, MessagingException {
    		
    		Properties props = System.getProperties();
    		props.put("mail.transport.protocol", "smtp");
    		props.put("mail.smtp.host", HOST[HOSTNUM]);
    		props.put("mail.smtp.port", PORT); 
    		props.put("mail.smtp.starttls.enable", "true");
    		props.put("mail.smtp.auth", "true");
    		props.put("mail.user", SMTP_USERNAME);
    		props.put("mail.password", SMTP_PASSWORD);
    		
    		if(HOSTNUM > 4) { // IP처음 5개는 ~~.~~.com 사용, 이후 5개는 ~~.~~.net 사용
    			
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
    				
    				if(HOSTNUM != 9) { // 마지막 IP가 아니면 다음IP를 부를 수 있게 HOSTNUM 증가 
    					
    					HOSTNUM++;
    				
    				} else { // 마지막 IP이면 HOSTNUM은 초기화 되어 다른계정에 메일을 송신할 때 다시 첫 IP부터 반복하게 된다.
    					
    					HOSTNUM = 0;
    					
					// 총 네 개의 계정에 메일을 보내도록 설정. 마지막 계정이 아니라면 다음 계정을 부를 수 있도록
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
    
