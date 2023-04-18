package controller.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.reservation.rmovieDao;
import model.dto.reservation.plistDto;
import model.dto.reservation.reservationDto;

/**
 * Servlet implementation class ticket
 */
@WebServlet("/ticket")
public class ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ticket() { super();}

    //선택한 특정영화의 정보 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno = Integer.parseInt( request.getParameter("pno"));
		
		plistDto dto = rmovieDao.getInstance().plist_print(pno);
		  
		ObjectMapper objMapper = new ObjectMapper(); String jsonArray =
		objMapper.writeValueAsString(dto);
		  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // 전달[전송]타입을 json 명시
		response.getWriter().print(jsonArray ); // 변환된 json형식의 문자열 전달
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션을 int로
		Object Omno = request.getSession().getAttribute("login");
		int mno = Integer.parseInt((String.valueOf(Omno)));
		
		//상영번호
		int pno = Integer.parseInt( request.getParameter("pno") );
		
		//좌석
		String s_seat = request.getParameter("s_seat");
		String[] seat = s_seat.split(",");
		
		boolean result = rmovieDao.getInstance().reservation_ticket(seat, mno, pno);
		
		response.getWriter().print(result);
		
	}


	//예약된 좌석 호출 put과 안어울림
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno = Integer.parseInt( request.getParameter("pno"));
		System.out.println("put pno :" + pno);
		
		ArrayList<reservationDto> result = rmovieDao.getInstance().seatnumPrint(pno);
		System.out.println("result :" + result);
		
		
		ObjectMapper objMapper = new ObjectMapper(); String jsonArray =
		objMapper.writeValueAsString(result);
				  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // 전달[전송]타입을 json 명시
		response.getWriter().print(jsonArray ); // 변환된 json형식의 문자열 전달
	}
	
	

}
