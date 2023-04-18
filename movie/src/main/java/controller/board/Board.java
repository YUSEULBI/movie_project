package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.board.BoardDao;
import model.dto.board.BoardDto;
import model.dto.board.ReplyDto;

/**
 * Servlet implementation class Board
 */
@WebServlet("/Board/comment")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Board() {
       System.out.println("board/commet 실행");
    }
    
    public void init(ServletConfig config) throws ServletException{
    	System.out.println("서블릿 init 실행");
    }

    public void destroy() {
    	System.out.println("서블릿 destroy 실행");
    }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("doget 호출중");
		
		if(type == 1) {
			ArrayList<BoardDto> list = BoardDao.getInstence().board_print();
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString(list);
			// 응답
			//System.out.println("jsonArray : " + jsonArray);
			response.setContentType("applcation/json");
			response.getWriter().print( jsonArray );
			
		}
		else if(type == 2) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			ArrayList<ReplyDto> rlist = BoardDao.getInstence().reply_view(bno);
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString(rlist);
			// 응답
			System.out.println("jsonArray : " + jsonArray);
			response.setContentType("applcation/json");
			response.getWriter().print( jsonArray );
			
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
