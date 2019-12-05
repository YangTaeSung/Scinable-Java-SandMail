import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;

/*
 * 
 * Scinable 
 * 5초에 한 번씩 메일 전송 (한 번씩 전송하는데 총 보내야할 메일 90개 = 450초 = 7~8분정도)
 * 30분 주기로 총 5회 반복
 * 
 */

public class WorkTask extends TimerTask{

	static int flag = 0;
	final static int LOOP_NUMBER = 5; // 반복횟수 
	final static int TOTAL_MAIL_NUMBER = Send.totalTO * Send.totalHOST * LOOP_NUMBER; // 총 보내야할 메일 개수
	
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
	        timer.schedule(timerTask, 1000); // 1초 후에 메일 보내기
	        flag++;
	        System.out.println("TimerTask started");	         
	        
	        try {
	            Thread.sleep(1000 * 5); // 5초 간격으로 while문 동작
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        
	        
	        if(flag % (TOTAL_MAIL_NUMBER / LOOP_NUMBER) == 0) { // 한바퀴 돌린 후 
				
				try {
					Thread.sleep(1000 * 60 * 30); // 한시간 뒤에 동작.
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	        
	        // timer.cancel(); // 타이머 종료 해제
	        System.out.println("TimerTask cancelled");
	        
		}
	
	}
	
}