package controller.admin;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlStrong;

public class HtmlUnit {
	
	public void moviehtmlunit() {
		System.out.println("moviehtmlunit 실행");
		WebClient webClient = new WebClient();
		Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); // 중간 로그를 보이지 않도록 함.
		webClient.getOptions().setThrowExceptionOnScriptError(false);// 자바 스크립트 오류시 처리하지 않도록 수정

		
		// KOFIC 영화 상세페이지
		try { 
			HtmlPage page = webClient.getPage("https://kobis.or.kr/kobis/business/mast/mvie/searchMovieList.do?dtTp=movie&dtCd=20112708");
			HtmlStrong img = (HtmlStrong)page.getFirstByXPath("//*[@id=\"ui-id-1\"]/div/div[1]/div[2]/a/img");
			System.out.println(img.asNormalizedText());
		} catch (FailingHttpStatusCodeException | IOException e) { e.printStackTrace(); }
		
		webClient.close(); // 연결종료
	}
	
}
