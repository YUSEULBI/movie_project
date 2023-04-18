package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.admin.MovieListDao;
import model.dto.admin.MovieListDto;


@WebServlet("/admin/movieList")
public class MovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MovieList() {
        super();       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		ArrayList<MovieListDto> result = null;
		
		if(type.equals("1")) {
			result = MovieListDao.getInstance().getallmovielist();
		}else if(type.equals("2")) {
			String selectday = request.getParameter("selectday");
			String selecttime = request.getParameter("selecttime");
			String selectdate = null;
			selectdate = selectday+" "+selecttime;
			result = MovieListDao.getInstance().gettimemovielist(selectdate);
		}else if(type.equals("3")) {
			String selectday = request.getParameter("selectday");
			String tomorrow = request.getParameter("tomorrow");
			result = MovieListDao.getInstance().getdaylist(selectday,tomorrow);
			
		}
		ObjectMapper mapper = new ObjectMapper();
		String json= mapper.writeValueAsString(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("post로 왔음");
		int pprice = Integer.parseInt(request.getParameter("pprice"));
		String playtime = request.getParameter("playtime");
		int mno = Integer.parseInt(request.getParameter("mno"));
		int sno = Integer.parseInt(request.getParameter("sno"));
		boolean result = MovieListDao.getInstance().newplayinglist(pprice,playtime,mno,sno);
		response.getWriter().print(result);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno = Integer.parseInt(request.getParameter("pno"));
		boolean result = MovieListDao.getInstance().pdelete(pno);
		response.getWriter().print(result);
	}

}
