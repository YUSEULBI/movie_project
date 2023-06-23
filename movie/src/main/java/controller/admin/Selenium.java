package controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import model.dto.admin.MovieSeleniumDto;

public class Selenium {
	
	public List<MovieSeleniumDto> seleniumByDtcd( List<String> dtCdList ) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		
		//옵션 객체 생성
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("headless");								
		chromeOptions.addArguments("--disable-popup-blocking");				
		chromeOptions.addArguments("--disable-gpu");						
		chromeOptions.addArguments("--blink-settings=imagesEnabled=false");	
		
		List<MovieSeleniumDto> seleniumDtoList = new ArrayList<>();
		CountDownLatch latch = new CountDownLatch(dtCdList.size());
		
		for( int i = 0 ; i < dtCdList.size() ; i++ ) { 
			
			final String index = dtCdList.get(i);
			
			// 멀티스레드
			Thread thread = new Thread(()->{
				WebDriver webDriver = new ChromeDriver(chromeOptions);
				webDriver.get("https://kobis.or.kr/kobis/business/mast/mvie/searchMovieList.do?dtTp=movie&dtCd="+index);

				// 포스터 이미지 a태그
				WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div/div[1]/div[2]/a"));
				// 포스터 이미지 a태그의 href
				String href = webElement.getAttribute("href");
				MovieSeleniumDto dto = new MovieSeleniumDto();
				dto.setMovieCd(index);
				dto.setImgUrl(href);
				
				// 여러 스레드가 DtoList에 동시에 접근하기 때문에 동기화
				synchronized (seleniumDtoList) {
					seleniumDtoList.add(dto);
				}
				webDriver.quit(); // WebDriver 종료
				latch.countDown();
			});
			thread.start();
		}
		// 모든 스레드 완료를 기다림
		try { latch.await(); } catch (InterruptedException e) { e.printStackTrace(); }
		return seleniumDtoList; // 모든 스레드가 완료되고 반환해야함.
	}
	
	
public MovieSeleniumDto seleniumOneMovie( String dtCd ) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		//옵션 객체 생성
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("headless");								
		chromeOptions.addArguments("--disable-popup-blocking");				
		chromeOptions.addArguments("--disable-gpu");						
		chromeOptions.addArguments("--blink-settings=imagesEnabled=false");	

		WebDriver webDriver = new ChromeDriver(chromeOptions);
		webDriver.get("https://kobis.or.kr/kobis/business/mast/mvie/searchMovieList.do?dtTp=movie&dtCd="+dtCd);

		// 포스터 이미지 a태그
		WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div/div[1]/div[2]/a"));
		// 포스터 이미지 a태그의 href
		String href = webElement.getAttribute("href");
		MovieSeleniumDto dto = new MovieSeleniumDto();
		dto.setMovieCd(dtCd);
		dto.setImgUrl(href);
				
		webDriver.quit(); // WebDriver 종료
		return dto; 
	}
}
