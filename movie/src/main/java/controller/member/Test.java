package controller.member;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	
	private static Test test = new Test();
	private Test() {	}
	public static Test getInstance() { 	return test;	}
	
	public void test() throws IOException {
		
		System.out.println("test실행");
		// Jsoup.connect("") 원하는 url문서 연결
		// .get() 가져오기
		Document doc = Jsoup.connect("https://pedia.watcha.com/ko-KR").get();
		//System.out.println(doc);
		
		// 영화,드라마 모든 리스트가 동일한 .css-8y23cj클래스로 되어있어서
		// boxoffice구역 클래스인 css-1qq59e8 우선 선택
		// select 원하는 요소 선택하기 -> Elements 요소여러개 반환
		// .first(); 첫번째 요소만 가져오기 -> Element 요소1개 반환
		Element boxofficediv = doc.select(".css-1qq59e8").first();
		//System.out.println(boxofficediv);
		
		// 박스오피스의 클래스명 css-8y23cj 인 요소 모두 가져오기 -> 박스오피스 리스트 -> Elements 반환
		Elements boxofficelist = boxofficediv.select(".css-8y23cj");
		

		// 박스오피스 리스트에서 영화1개씩 데이터 추출
		// text(); html내 텍스트 가져오기 -> String 반환
		// attr("") 선택한 요소의 속성 선택해서 가져오기 -> String 반환
		for ( int i = 0 ; i <boxofficelist.size() ; i++ ) {
			System.out.println(i);
			// 영화제목
			String title = boxofficelist.get(i).select(".css-5yuqaa").get(0).text();
			System.out.println(title);
			// 영화이미지uri
			String img = boxofficelist.get(i).select("img").attr("src");			
			System.out.println(img);
			
			// 영화개봉년도,개봉국가 - 상세페이지에서 구해서 생략
			// String releaseinfo = boxofficelist.get(i).select(".css-1rxwuxd").text();
			// System.out.println(releaseinfo);
			
			// 예매율,누적관객수
			String audienceinfo = boxofficelist.get(i).select(".css-u4moi6").text();
			System.out.println(audienceinfo);
			// 예매율, 누적관객수 분리
			String[] audienceinfoarray = audienceinfo.split("%");
			// 예매율 // .replaceAll("[^0-9.]", ""); 숫자와마침표가 아니면 공백으로대체
			Double reservationrate = Double.parseDouble(audienceinfoarray[0].replaceAll("[^0-9.]", ""));
			System.out.println( reservationrate );
			// 누적관객수 : " ・ 누적 관객 601만명".split(" ")[4] => "601만명"
			String cumulativeaudience = audienceinfoarray[1].split(" ")[4];
			System.out.println( cumulativeaudience );
			
			
			
			// 상세정보로 링크이동 ///-----------------------------------------------------------------
			String contentslink = boxofficelist.get(i).select("a").attr("href");
			
				// 개봉일 구하기 /////
				// [상세페이지] https://pedia.watcha.com/ko-KR/contents/영화식별번호
			doc = Jsoup.connect("https://pedia.watcha.com"+contentslink).get();
			// [^0-9] : 숫자가 아닌 문자
			// replaceAll("[^0-9]", "") : 숫자가 아닌 문자를 공백으로 대체
			// "개봉3일째" => "3" 로 바꾸기
			String stringReleaseDate = doc.select(".e1svyhwg14").select("li").get(1).text().replaceAll("[^0-9]", "");
			// [오늘날짜에서 개봉3일째+1 빼서 개봉일 구하기] "개봉3일째" => "3"=> int 3 => 개봉일은 오늘날짜.mynusDay(3+1);
			LocalDate releasedate = LocalDate.now().minusDays(Integer.parseInt(stringReleaseDate)+1);
			System.out.println(releasedate);
			
				// 감독 구하기 ////
			String director = "";
			Elements crewlist = doc.select(".css-qkf9j");
			for( int c = 0 ; c < crewlist.size() ; c++ ) {
				if ( crewlist.get(c).select(".css-1evnpxk-StyledSubtitle").text().equals("감독") ) {
					director = crewlist.get(c).select(".css-17vuhtq").text();
					break;
				}
			}
			System.out.println(director);
			
			
				// [상세페이지-기본정보더보기] https://pedia.watcha.com/ko-KR/contents/영화식별번호/overview
			doc = Jsoup.connect("https://pedia.watcha.com"+contentslink+"/overview").get();
			Elements infolist= doc.select(".e1kvv3953");
			
			//원제
			String originaltitle = infolist.get(0).text();		System.out.println(originaltitle);
			//개봉년도
			String releaseyear = infolist.get(1).text();		System.out.println(releaseyear);
			//개봉국가
			String country = infolist.get(2).text();			System.out.println(country);
			//장르
			String genre = infolist.get(3).text();				System.out.println(genre);
			//상영시간
			String runningtime = infolist.get(4).text();		System.out.println(runningtime);
			//관람연령등급
			String age = infolist.get(5).text();				System.out.println(age);
			
			String summary = doc.select(".e1kvv3954").first().text();	System.out.println(summary);
			
			
			
		} // for문 종료
		
	}

}
