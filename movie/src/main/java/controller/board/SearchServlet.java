package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dto.board.SearchDto;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		
		ArrayList<SearchDto> list = new ArrayList<>();
		
		// js에서 보낸 데이터 받기
		int type = Integer.parseInt(request.getParameter("type")); // 실행할 타입
		String search =  request.getParameter("search");	// 검색한 값
		String select = request.getParameter("select");		// 선택한 영화의 링크주소
		String mimg=null;
		
		if(type == 1) { // 메인화면 출력(박스오피스 탑10 / 넷플릭스 탑10
			list = Search.search();
			
		}
		else if(type == 2) { // 검색한 영화 출력
			list = Search.search_m(search);
			
		}
		else if(type == 3) { // 선택한 영화의 상세페이지 출력
			list = Search.select(select);
		}
		else if(type == 4 ) { 
			String movie = request.getParameter("bmovie");
			
			mimg = Search.img_find(movie);
			
			
		}
		
		
		if(type == 4) {
			// java 형식 ---> js형식 
						ObjectMapper mapper = new ObjectMapper();
						String jsonArray = mapper.writeValueAsString(mimg );
						// 응답
						//System.out.println("jsonArray : " + jsonArray);
						response.setContentType("applcation/json");
						response.getWriter().print( jsonArray );
		}
		else {
			// java 형식 ---> js형식 
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString( list );
			// 응답
			System.out.println("jsonArray : " + jsonArray);
			response.setContentType("applcation/json");
			response.getWriter().print( jsonArray );
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
