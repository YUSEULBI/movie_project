package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dto.admin.MovieSeleniumDto;


@WebServlet("/movie/selenium")
public class SeleniumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SeleniumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String moviecdlistString = request.getParameter("moviecdlist");
		
		// moviecdlist에서 숫자만 꺼내기
		Pattern pattern = Pattern.compile("\\d+"); 
		Matcher matcher = pattern.matcher(moviecdlistString); 
		List<String> dtCdList = new ArrayList<>();
		while ( matcher.find() ) { dtCdList.add(matcher.group()); }

		List<MovieSeleniumDto> dtoList = new ArrayList<>();
		CountDownLatch latch = new CountDownLatch(dtCdList.size());
		for( int i = 0 ; i < dtCdList.size() ; i++ ) {
			final String index = dtCdList.get(i);
			// 멀티스레드
			Thread thread = new Thread(()->{
				MovieSeleniumDto dto = new Selenium().seleniumOneMovie(index);
				dtoList.add(dto);
				latch.countDown();				
			});
			thread.start();
		}
		try { latch.await(); }catch (InterruptedException e) { e.printStackTrace();	}
		
		ObjectMapper mapper = new ObjectMapper();
		String json= mapper.writeValueAsString(dtoList);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
