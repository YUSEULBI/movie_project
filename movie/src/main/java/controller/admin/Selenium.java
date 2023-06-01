package controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import model.dto.admin.MovieSeleniumDto;

public class Selenium {
	
	public List<MovieSeleniumDto> seleniumtest( List<String> dtCdList ) {
		System.out.println("셀레니움 테스트 실행");
		System.out.println("cdArray : "+ dtCdList);
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		
		//옵션 객체 생성
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("headless");
		WebDriver webDriver = new ChromeDriver(chromeOptions);
		
		List<MovieSeleniumDto> seleniumDtoList = new ArrayList<>();
		for( int i = 0 ; i < dtCdList.size() ; i++ ) { 
			System.out.println("dtCdList.get(i) : "+dtCdList.get(i) );
		
			webDriver.get("https://kobis.or.kr/kobis/business/mast/mvie/searchMovieList.do?dtTp=movie&dtCd="+dtCdList.get(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { e.printStackTrace(); }
			// 포스터 이미지 a태그
			WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div/div[1]/div[2]/a"));
			System.out.println(webElement);
			// 포스터 이미지 a태그의 href
			String href = webElement.getAttribute("href");
			System.out.println("href : "+href);
			MovieSeleniumDto dto = new MovieSeleniumDto();
			dto.setMovieCd(dtCdList.get(i));
			dto.setImgUrl(href);
			seleniumDtoList.add(dto);
		}
		return seleniumDtoList;
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
