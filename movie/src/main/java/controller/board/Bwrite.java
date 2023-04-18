package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.board.BwriteDao;
import model.dto.board.BoardDto;
import model.dto.board.ReplyDto;
import model.dto.member.MemberDto;

/**
 * Servlet implementation class Bwrite
 */
@WebServlet("/Board/Bwrite")
public class Bwrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bwrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int type = Integer.parseInt(request.getParameter("type"));
		ArrayList<BoardDto> list = new ArrayList<>();
		
		if(type==1) {
			String movie = request.getParameter("bmovie");
			//System.out.println("bmovie : " + bmovie);
			
			String[] smovie = movie.split("/");
			
			String bmovie = smovie[5];
			
			
			list =  BwriteDao.getInstane().bprint(bmovie);
			//System.out.println(list);
			// java 형식 ---> js형식 
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString( list );
			// 응답
			//System.out.println("jsonArray : " + jsonArray);
			response.setContentType("applcation/json");
			response.getWriter().print( jsonArray );
		}
		
		if(type==2) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			list = BwriteDao.getInstane().reply_view(bno);
			//System.out.println("reply : " + list);
			// java 형식 ---> js형식 
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString( list );
			// 응답
			//System.out.println("jsonArray : " + jsonArray);
			response.setContentType("applcation/json");
			response.getWriter().print( jsonArray );
		}
		if(type==3) {
			String bmovie = request.getParameter("movie");
			
			String[] smovie = bmovie.split("/");
			
			String movie = smovie[5];
			System.out.println("타입3 인수 movie 확인 : " + movie);
			
			ArrayList<ReplyDto> list2 = new ArrayList<>();
			
			list2 = BwriteDao.getInstane().reply_print(movie);
			System.out.println("list2 : " + list2);
			
			// java 형식 ---> js형식 
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString( list2 );
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		
		// 리뷰작성
		if(type == 1) {
			int mno = Integer.parseInt(request.getParameter("mno"));
			String movie = request.getParameter("bmovie");
			String bcontent = request.getParameter("bcontent");
			int bscore = Integer.parseInt(request.getParameter("bscore"));
			String[] smovie = movie.split("/");
			
			String bmovie = smovie[5];
			
			
			
			System.out.println(mno + bcontent + bscore + "||"+ bmovie);
			BoardDto dto = new BoardDto(bmovie, bcontent, bscore, mno);
			System.out.println(dto);
			boolean result = BwriteDao.getInstane().bwrite(dto);
			
			response.getWriter().print(result);
		}
		// 댓글작성
		else if(type == 2) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			String rmovie = request.getParameter("rmovie");
			int rindex = bno;
			String mid = request.getParameter("mid");
			System.out.println(rindex);
			String rcontent = request.getParameter("rcontent");
			int mno = Integer.parseInt(request.getParameter("mno"));
			
			System.out.println("댓글작성 : " + bno +"||"+ rmovie +"||" + rcontent +"||" + mno);
			
			
			
			ReplyDto dto = new ReplyDto(rcontent, rmovie, rindex, mno, bno, mid);
			boolean result = BwriteDao.getInstane().reply_write(dto);
			response.getWriter().print(result);
		}
		
		
		
		
		
	}

}





































