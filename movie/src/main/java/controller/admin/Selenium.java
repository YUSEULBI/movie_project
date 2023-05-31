package controller.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
	
	public void seleniumtest() {
		System.out.println("셀레니움 테스트 실행");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\504\\Desktop\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver webDriver = new ChromeDriver();
		webDriver.get("https://kobis.or.kr/kobis/business/mast/mvie/searchMovieList.do?dtTp=movie&dtCd=20112708");
		

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) { e.printStackTrace(); }
		// 포스터 이미지 a태그
		WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div/div[1]/div[2]/a"));
		System.out.println(webElement);
		// 포스터 이미지 a태그의 href
		String href = webElement.getAttribute("href");
		System.out.println("href : "+href);
		
		// 시놉시스 가져오기
		WebElement synopsis = webDriver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div/div[1]/div[3]/p"));
		System.out.println(synopsis);
		String synopsisText = synopsis.getText();
		System.out.println(synopsisText);
		
		
		
	}
}
