package model.dto.member;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberDto {
	private int mno;
    private String mid;
    private String mpwd;
    private String memail;
    private String mimg;
    
    // 1. 이메일 전송 메소드
    public boolean sendEmail( String toemail , String updatePwd ) {
    	
    	// 1. 보내는 사람의 정보
		String fromEmail = "movieproject33@naver.com"; // 아이디
		String emailPwd = "movieproject1!"; // 비번
		// 2. 호스팅 설정 [ 네이버기중]
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.naver.com");
		properties.put("mail.smtp.port", 587 );
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		// 3. 인증처리 // Session import -> javax.mail
			// Session.getDefaultInstance( '설정' , new Authenticator() {	}) ;
			// PasswordAuthentication : import javax.mail
			// new PasswordAuthentication("인증할계정주소", "비밀번호");
		Session session = Session.getDefaultInstance(
				properties , new Authenticator() {	
					// 오버라이딩
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, emailPwd);
					}
				} );
		// 4. 메일 보내기
		try {
			// Mime프로토콜 : smtp가 보낼 수 있는 표준형식[포맷]
			MimeMessage message = new MimeMessage( session );	// 인증된 세션 대입해서 포맷 객체 생성
			message.setFrom( new InternetAddress( fromEmail ) ); // 보내는사람
			message.addRecipient( Message.RecipientType.TO , new InternetAddress( toemail ) ); // 받는 사람
			// 내용 구성
			message.setSubject( "[ cineverse 임시 비밀번호 ]" ); // 메일제목
			message.setText("임시비밀번호 : " + updatePwd ); // 메일 내용
			// 전송
			Transport.send(message); // 표준형식[포맷]의 데이터를 SMTP가 전송
			
			return true; // 메일 전송 성공
		}catch (Exception e) {	System.out.println(e); 		}
		return false; // 메일전송실패
    }
    
    public MemberDto() {   }
    
    
    // 풀생성자
   public MemberDto(int mno, String mid, String mpwd, String memail, String mimg) {
      super();
      this.mno = mno;
      this.mid = mid;
      this.mpwd = mpwd;
      this.memail = memail;
      this.mimg = mimg;
   }
   
   //회원가입용 생성자
   public MemberDto(String mid, String mpwd, String memail, String mimg) {
      super();
      this.mid = mid;
      this.mpwd = mpwd;
      this.memail = memail;
      this.mimg = mimg;
   }
   
   
   // 회원정보수정 생성자
   public MemberDto(String mpwd, String memail, String mimg) {
	super();
	this.mpwd = mpwd;
	this.memail = memail;
	this.mimg = mimg;
}

@Override
   public String toString() {
      return "MemberDto [mno=" + mno + ", mid=" + mid + ", mpwd=" + mpwd + ", memail=" + memail + ", mimg=" + mimg
            + "]";
   }

   
   public int getMno() {
      return mno;
   }

   public void setMno(int mno) {
      this.mno = mno;
   }

   public String getMid() {
      return mid;
   }

   public void setMid(String mid) {
      this.mid = mid;
   }

   public String getMpwd() {
      return mpwd;
   }

   public void setMpwd(String mpwd) {
      this.mpwd = mpwd;
   }

   public String getMemail() {
      return memail;
   }

   public void setMemail(String memail) {
      this.memail = memail;
   }

   public String getMimg() {
      return mimg;
   }

   public void setMimg(String mimg) {
      this.mimg = mimg;
   }

}
