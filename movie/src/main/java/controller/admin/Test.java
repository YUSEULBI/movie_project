package controller.admin;


import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import model.dto.board.SearchDto;

public class Test {
	
	private static Test test = new Test();
	private Test() {};
	public static Test getInstance() {return test;}
	
	
    public ArrayList<SearchDto> test(String search) throws Exception{
ArrayList<SearchDto> list = new ArrayList<>();
		
		// 검색한 단어를 넣은 크롤링 주소값 넣기
		Document doc = null;
		doc = Jsoup.connect("https://pedia.watcha.com/ko-KR/searches/movies?query="+search).get();
		
		Element title = doc.select(".css-ix1flm").select(".css-x62r3q").get(0);
		String titleStr = title.text();
    	System.out.println("titleStr :" + titleStr);

		
		Element year = doc.select(".css-ix1flm").select(".css-h25two").get(0);
    	String yearStr = year.text();
    	System.out.println("yearStr :" + yearStr);
    	
    	String link = doc.select(".css-ix1flm").select(".css-1aaqvgs-InnerPartOfListWithImage").get(0).attr("abs:href");
    	System.out.println("link :" + link);

		System.out.println("여기까지는 옴");
		//String pimg = doc.select(".css-8y23cj").select(".e1q5rx9q1 style").get(0).attr("abs:background-image: url");
        
		System.out.println(link); 
       
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pimg = doc.select(".ezcopuc0 img").first().attr("abs:src");
		System.out.println("img : " + pimg);
        SearchDto dto = new SearchDto(titleStr, pimg, null, null, yearStr);
		list.add(dto);
			
		System.out.println("searchlist : " + list.toString());
		return list;
    }
}