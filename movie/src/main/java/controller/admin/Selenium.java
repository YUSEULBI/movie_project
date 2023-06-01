package controller.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Selenium {
	
	public String seleniumtest( String[] cdArray ) {
		System.out.println("셀레니움 테스트 실행");
		System.out.println("cdArray : "+ cdArray);
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\504\\Desktop\\chromedriver_win32\\chromedriver.exe");
		
		//옵션 객체 생성
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("headless");
		WebDriver webDriver = new ChromeDriver();
		
		for( int i = 0 ; i < cdArray.length ; i++ ) { 
			System.out.println("cdArray[i] : "+cdArray[i]);
		}
		webDriver.get("https://kobis.or.kr/kobis/business/mast/mvie/searchMovieList.do?dtTp=movie&dtCd="+cdArray[0]);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { e.printStackTrace(); }
		// 포스터 이미지 a태그
		WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div/div[1]/div[2]/a"));
		System.out.println(webElement);
		// 포스터 이미지 a태그의 href
		String href = webElement.getAttribute("href");
		System.out.println("href : "+href);
		return href;
		// 시놉시스 찾기
		/*
		for ( int i = 3 ; i <= 5 ; i++ ) {
																
			WebElement item = webDriver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div/div[1]/div["+i+"]/strong"));
			String itemtext = item.getText();
			System.out.println(itemtext);
			if( itemtext.contains("시놉시스")) {
				WebElement synopsis = webDriver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div/div[1]/div["+i+"]/p"));
				String synopsisText = synopsis.getText();
				System.out.println(synopsisText);
				//*[@id="ui-id-1"]/div/div[1]/div[5]/p
				//*[@id="ui-id-1"]/div/div[1]/div[5]/strong
			}
		}
		*/
	}
}
