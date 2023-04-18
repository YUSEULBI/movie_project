package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.member.MypageDao;
import model.dto.member.ReservationDto;

/**
 * Servlet implementation class MyReservation
 */
@WebServlet("/myreservation")
public class MyReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	// 로그인한 회원의 영화예매목록 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = (int)request.getSession().getAttribute("login");
		System.out.println("mno : "+mno);
		
		// 로그인한 회원의 영화예매목록 리스트 가져오기
		ArrayList<ReservationDto> rlist = MypageDao.getInstance().getRnoPnoMno(mno);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonlist = mapper.writeValueAsString(rlist);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonlist);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pno = Integer.parseInt(request.getParameter("pno")); // 상영식별번호
		int mno = (int)request.getSession().getAttribute("login"); // 회원식별번호
		// pno와 mno가 동일한 [ 모든 레코드 찾아서 레코드수만큼 playinglist pseat차감 , reservation 레코드 삭제 ] 
		boolean result = MypageDao.getInstance().cancelReservation(pno, mno);
		response.getWriter().print(result);
	}

}
