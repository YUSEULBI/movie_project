package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import model.dto.board.SearchDto;

public class SearchPoster {
	
		public static String searchposter( String search ) throws IOException {
			//ArrayList<SearchDto> list = new ArrayList<>();
			// 검색한 단어를 넣은 크롤링 주소값 넣기
			Document doc = Jsoup.connect("https://pedia.watcha.com/ko-KR/search?query="+search+"&category=contents").get();
			//System.out.println(doc);
			
			// 검색해서 나온 결과값의 사이즈 구하기
			int listsize = doc.getElementsByClass("css-8y23cj").size();
			//System.out.println("listsize : " + listsize);
			
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
	        	String titleStr = title.text();
	        	System.out.println("titleStr :" + titleStr);
				
				String pimg = doc.select(".css-8y23cj").select(".ezcopuc0 img").get(i).attr("abs:src");
				System.out.println(pimg); 
	           
	            //SearchDto dto = new SearchDto(titleStr, pimg, null, null, null);
				//list.add(dto);
				return pimg;
	        }
			//System.out.println("searchlist : " + list.toString());
			return null;
			
		}
	
	// 검색한 영화 목록 찾아서 보내기
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

}
