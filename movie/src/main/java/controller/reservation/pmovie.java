package controller.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.reservation.rmovieDao;
import model.dto.reservation.plistDto;


@WebServlet("/playing/moive")
public class pmovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public pmovie() {super();}

    //상영중인 영화 제목 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<plistDto> result = rmovieDao.getInstance().Playing_movie();
		
		System.out.println("result : "+result);
		ObjectMapper objMapper = new ObjectMapper(); 
		 
		String jsonArray = objMapper.writeValueAsString(result); 
	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.getWriter().print(jsonArray ); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	//선택한 영화+달력의 남는 상영관 출력
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int mno = Integer.parseInt( request.getParameter("mno") );
		String s_date = request.getParameter("s_date");
		
		 ArrayList<plistDto> result = rmovieDao.getInstance().screen_print(mno,s_date );
		System.out.println("click result : "+result);
		
		ObjectMapper objMapper = new ObjectMapper(); 
		 
		String jsonArray = objMapper.writeValueAsString(result); 
	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.getWriter().print(jsonArray ); 
	}
 
	 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
