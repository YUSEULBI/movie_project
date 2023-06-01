package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		System.out.println("셀레니움테스트 서블릿 실행");
		request.setCharacterEncoding("UTF-8");
		String moviecdlistString = request.getParameter("moviecdlist");
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(moviecdlistString);
		List<String> dtCdList = new ArrayList<>();
		while (matcher.find()) {
			dtCdList.add(matcher.group());
		}

		Selenium selenium = new Selenium();
		List<MovieSeleniumDto> result = selenium.seleniumtest(dtCdList);
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		String json= mapper.writeValueAsString(result);
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
