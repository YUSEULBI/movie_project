package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import model.dto.board.SearchDto;

public class BoxOffice {
	
	public static ArrayList<SearchDto> search() throws IOException {
		ArrayList<SearchDto> list = new ArrayList<>();
		System.out.println("a");
		Document doc = Jsoup.connect("https://pedia.watcha.com/ko-KR").get();
		System.out.println(doc);
		
		for(int i = 0 ; i < 10 ; i++ ) {
			// [data-rowindex='0'] = 박스오피스인덱스
			// [attr=value]: 속성 값이 있는 요소 [width=500](예: 와 같이 인용 가능 [data-name='launch sequence'])
			// .css-5yuqaa : 영화이름
			// 박스오피스 1위~10위 영화이름 가져오기 같음
			Element title = doc.select("[data-rowindex='0']").select(".css-5yuqaa").get(i);
			// 예매율 , 누적관객
			Element counts = doc.select("[data-rowindex='0']").select(".css-u4moi6").get(i);
			// 개봉연도 , 개봉한나라
			Element year = doc.select("[data-rowindex='0']").select(".css-1rxwuxd").get(i);
			
			//Element.text(String text)요소의 기존 내부 HTML을 지우고 제공된 텍스트로 바꿉니다.
			String titleStr = title.text(); 	// 패왕별희
			String countStr = counts.text();	// 예매율 1.7% ・ 누적 관객 12만명
			String yearStr = year.text();		// 1993 ・ 중국
			System.out.println(titleStr); 
			System.out.println(countStr); 
			System.out.println(yearStr); 
			
			// 영화별 상세페이지 링크
			// 절대 URL을 얻으려면 abs:속성 값이 문서의 기본 URI(원래 위치)에 대해 확인되도록 하는 속성 키 접두사가 있습니다.attr("abs:href")
			// https://pedia.watcha.com/ko-KR/contents/mJO1awO
			// String으로 반환
			String link = doc.select("[data-rowindex='0']").select(".css-8y23cj a").get(i).attr("abs:href");
			System.out.println(link);
			
			//img태그의 src를 웹주소형식으로 가져오기
			// https://an2-img.amz.wtchn.net/image/v2/T7qP_idp-A7AdHCV6-wZBA.jpg?jwt=ZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKdmNIUnpJanBiSW1SZk5Ea3dlRGN3TUhFNE1DSmRMQ0p3SWpvaUwzWXlMM04wYjNKbEwybHRZV2RsTHpFMk56VTJOVE16TlRNNE9EVTVNVEEyTURVaWZRLmZxSThtNU1jQl9HSDFxQ0plZGlUYUxPa1R4WTVwSC1kZGhNWVhISy16anM
			// String으로 반환
			String pimg = doc.select("[data-rowindex='0']").select(".ezcopuc0 img").get(i).attr("abs:src");
			System.out.println("pimg : "+ pimg);
			
			// 영화제목 / 포스터웹주소  / [예매율,누적관객]  / 상세페이지링크 / [개봉연도,개봉나라]
			SearchDto dto = new SearchDto(titleStr, pimg, countStr, link, yearStr);
			list.add(dto);
		}// for문 end
		System.out.println(list);
		return list;
	}
	
	public static ArrayList<SearchDto> getnetflixtopten() throws IOException {
		ArrayList<SearchDto> list = new ArrayList<>();
		Document doc = Jsoup.connect("https://pedia.watcha.com/ko-KR").get();
		System.out.println(doc);
		
		for (int i = 0; i < 10; i++) {
        	// [data-rowindex=2] = 넷플릭스 인덱스
        	Element title = doc.select("[data-rowindex=2]").select(".css-5yuqaa").get(i);
        	//Element year = doc.select("[data-rowindex=2]").select(".css-1rxwuxd").get(i);
        	
        	String titleStr = title.text();
        	//String yearStr = year.text();
                       
            // String link = doc.select("[data-rowindex=2]").select(".css-8y23cj a").get(i).attr("abs:href");
            //  System.out.println("link : "+link);
            String pimg = doc.select("[data-rowindex=2]").select(".ezcopuc0 img").get(i).attr("abs:src");
            //  System.out.println("pimg : "+ pimg);
            SearchDto dto =	new SearchDto(titleStr, pimg, null, null, null);
            list.add(dto);
		}// for문 end
		System.out.println(list);
		return list;
	}
	
}
