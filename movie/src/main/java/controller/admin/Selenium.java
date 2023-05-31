package controller.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
	
	public void seleniumtest() {
		System.out.println("셀레니움 테스트 실행");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\504\\Desktop\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver webDriver = new ChromeDriver();
		webDriver.get("https://www.google.co.kr/");
		
	}
}
