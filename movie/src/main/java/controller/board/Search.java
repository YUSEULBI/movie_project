package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import model.dto.board.SearchDto;

public class Search {

	// 1. 박스오피스 영화순위 and 넷플릭스 영화순위
	public static ArrayList<SearchDto> search() throws IOException{
		
		
		ArrayList<SearchDto> list = new ArrayList<>();
		
		// 크롤링할 URL 입력하고 객체생성
		Document doc = Jsoup.connect("https://pedia.watcha.com/ko-KR").get();
			// 박스오피스 구하기
        for (int i = 0; i < 10; i++) {
        	// [data-rowindex='0'] = 박스오피스인덱스
        	// 연결한doc에서 data-rowindex='0'이고 class="css-5yuqaa"인 데이터를 가져와 Element만들기
        	Element title = doc.select("[data-rowindex='0']").select(".css-5yuqaa").get(i);
            Element counts = doc.select("[data-rowindex='0']").select(".css-u4moi6").get(i);
            Element year = doc.select("[data-rowindex='0']").select(".css-1rxwuxd").get(i);
            
            // Element 객체를 String으로 변환
            String titleStr = title.text();
            String countStr = counts.text();
            String yearStr = year.text();
           // System.out.println("title : "+titleStr);
          //  System.out.println("count : " + countStr);
           
            // 연결한doc에서 data-rowindex='0'이고 class="css-8y23cj"인 데이터중에서 a태그의 href를 웹주소형식으로 가져오기 
            String link = doc.select("[data-rowindex='0']").select(".css-8y23cj a").get(i).attr("abs:href");
            System.out.println("link : "+link);
            // 연결한doc에서 data-rowindex='0'이고 class="ezcopuc0"인 데이터중에서 img태그의 src를 웹주소형식으로 가져오기
            String pimg = doc.select("[data-rowindex='0']").select(".ezcopuc0 img").get(i).attr("abs:src");
           // System.out.println("pimg : "+ pimg);
            
            // 크롤링한 값을 모아서 list에 넣기
            SearchDto dto = new SearchDto(titleStr, pimg, countStr, link, yearStr);
            list.add(dto);
         }
        	
        
        	// 넷플릭스 구하기
        	for (int i = 0; i < 10; i++) {
        	// [data-rowindex=2] = 넷플릭스 인덱스
        	Element title = doc.select("[data-rowindex=2]").select(".css-5yuqaa").get(i);
        	Element year = doc.select("[data-rowindex=2]").select(".css-1rxwuxd").get(i);
        	
        	String titleStr = title.text();
        	String yearStr = year.text();
           
          
            
            String link = doc.select("[data-rowindex=2]").select(".css-8y23cj a").get(i).attr("abs:href");
          //  System.out.println("link : "+link);
            String pimg = doc.select("[data-rowindex=2]").select(".ezcopuc0 img").get(i).attr("abs:src");
          //  System.out.println("pimg : "+ pimg);
            SearchDto dto =	new SearchDto(titleStr, pimg, null, link, yearStr);
            list.add(dto);
         }
      //  System.out.println(list.toString());
        return list;
	}
	
	
	// 2. 검색한 영화 목록 찾아서 보내기
	public static ArrayList<SearchDto> search_m(String search) throws IOException{
		ArrayList<SearchDto> list = new ArrayList<>();
		
		// 검색한 단어를 넣은 크롤링 주소값 넣기
		Document doc = Jsoup.connect("https://pedia.watcha.com/ko-KR/search?query="+search+"&category=contents").get();
		
		// 검색해서 나온 결과값의 사이즈 구하기
		int listsize = doc.getElementsByClass("css-8y23cj").size();
		System.out.println("listsize : " + listsize);
		
		// 사이즈만큼 반복
		for(int i = 0 ; i<listsize ; i++) {
			
			// 검색내용중 영화만 골라내기
			Element catagory = doc.select(".css-8y23cj").select(".css-jgn78h").get(i);
			String catagoryStr = catagory.text();
			System.out.println("catagoryStr : "+catagoryStr);
			
			// 카테고리가 영화가 아니면 컨티뉴
			if(catagoryStr.equals("영화") != true) {
				System.out.println("실패");
				continue;
			}
			
			// 카테고리가 영화일경우 실행됨
			Element title = doc.select(".css-8y23cj").select(".css-31iyzt").get(i);
			Element year = doc.select(".css-8y23cj").select(".css-1thqxgo").get(i);
        	String titleStr = title.text();
        	String yearStr = year.text();
        	//System.out.println("titleStr :" + titleStr);
			
        	String link = doc.select(".css-8y23cj a").get(i).attr("abs:href");
			
			String pimg = doc.select(".css-8y23cj").select(".ezcopuc0 img").get(i).attr("abs:src");
            
			// System.out.println(pimg); 
           
            SearchDto dto = new SearchDto(titleStr, pimg, null, link, yearStr);
			list.add(dto);
			
        }
		//System.out.println("searchlist : " + list.toString());
		return list;
	}

	
	// 3. 선택한 목록 상세출력
	public static ArrayList<SearchDto> select(String select) throws IOException{
		
		ArrayList<SearchDto> list = new ArrayList<>();
		
		// 검색한 단어를 넣기 (select = 선택한 영화 메인페이지)
		Document doc = Jsoup.connect(select).get();
		// 추가로 가져올 자료를 위해 상세보기 주소 추가 (select+"/overview" = 선택한 영화의 상세보기)
		Document doc2 = Jsoup.connect(select+"/overview").get();
		
		
		String infotitle="";
		String infoyear="";
		String infocountry="";
		String infogenre="";
		String infotime="";
		String infoold="";
		String infoconetent="";
		// 상세보기의 자료 가져오기
		for(int i = 0 ; i<6 ; i++) {
			if(i == 0) {
				Element title = doc2.select(".e1kvv3951").select(".e1kvv3953").get(i);
				infotitle = title.text();
			}
			else if( i == 1) {
				Element year = doc2.select(".e1kvv3951").select(".e1kvv3953").get(i);
				infoyear = year.text();
			}
			else if( i == 2) {
				Element country = doc2.select(".e1kvv3951").select(".e1kvv3953").get(i);
				infocountry = country.text();
			}
			else if( i == 3) {
				Element genre = doc2.select(".e1kvv3951").select(".e1kvv3953").get(i);
				infogenre = genre.text();
			}
			else if( i == 4) {
				Element time = doc2.select(".e1kvv3951").select(".e1kvv3953").get(i);
				infotime = time.text();
			}
			else if( i == 5) {
				Element old = doc2.select(".e1kvv3951").select(".e1kvv3953").get(i);
				infoold = old.text();
			}
		}
				Element conetent = doc2.select(".e1kvv3951").select(".e1kvv3954").first();
				infoconetent = conetent.text();
		
				// 상세보기로 가져온 값 확인
				System.out.println(infotitle + " - " +infoyear + " - " +infocountry + " - " +infogenre + " - " +infotime + " - " +infoold + " - " +infoconetent);
		
		// 영화 메인페이지의 가져오기
		Element title = doc.select(".e1svyhwg17").first();
		Element year = doc.select(".e1svyhwg18").first();
		Element count = doc.select(".e1svyhwg20").first();
		
		String titleStr = title.text();
		String yearStr = year.text();
		String countStr = count.text();
		String pimg = doc.select(".ezcopuc0 img").first().attr("abs:src");
		
		// 메인페이지에서 가져온 값 확인
		System.out.println(titleStr + " - " +yearStr + " - " +countStr + " - " +pimg);
		
		// dto로 모아서 list에 담아 리턴
		SearchDto dto = new SearchDto(titleStr, pimg, countStr, null, yearStr, pimg , infotitle, infoyear, infotime, infogenre, infocountry, infoold, infoconetent);
		list.add(dto);
		return list;
	}
	
	public static String img_find(String movie) throws IOException{
		
		Document doc = Jsoup.connect("https://pedia.watcha.com/ko-KR/contents/"+movie).get();
		String img = doc.select(".ezcopuc0 img").first().attr("abs:src");
		
		return img;
	}
	
}

