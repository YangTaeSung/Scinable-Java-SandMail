import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;

/*
 * 
 * Scinable 
 * 5�ʿ� �� ���� ���� ���� (�� ���� �����ϴµ� �� �������� ���� 40�� = 200�� = 3������)
 * 1�ð� �ֱ�� �� 4ȸ �ݺ�
 * 
 */

public class WorkTask extends TimerTask{

	static int flag = 0;
	final static int TOTAL_MAIL_NUMBER = 160; // 1ȸ�ݺ�
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			Send.send();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
	
		while(flag < TOTAL_MAIL_NUMBER) {
			
			TimerTask timerTask = new WorkTask();
	        Timer timer = new Timer(true);
	        timer.schedule(timerTask, 1000); // 1�� �Ŀ� ���� ������
	        flag++;
	        System.out.println("TimerTask started");	         
	        
	        try {
	            Thread.sleep(1000 * 5); // 5�� �������� while�� ����
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        if(flag%40 == 0) {	// �� ���� ������ ���� �ѽð� �ڿ� ����.
				
				try {
					Thread.sleep(1000 * 60 * 60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	        
	        // timer.cancel(); // Ÿ�̸� ���� ����
	        System.out.println("TimerTask cancelled");
	        
		}
	
	}
	
}
