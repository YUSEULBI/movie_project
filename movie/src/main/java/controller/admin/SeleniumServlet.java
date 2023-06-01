package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/movie/selenium")
public class SeleniumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SeleniumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("셀레니움테스트 서블릿 실행");
		String dtCdListString = request.getParameter("dtCdList");
		System.out.println("dtCdListString : "+dtCdListString);
		// 쉼표(,)로 구분된 문자열을 배열로 분할
        //String[] cdArray = dtCdListString.split(",");
        
        // 배열을 ArrayList로 변환
        //ArrayList<String> cdList = new ArrayList<>(Arrays.asList(cdArray));
		/*
		Selenium selenium = new Selenium();
		String result = selenium.seleniumtest(cdArray);
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		String json= mapper.writeValueAsString(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
		*/
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
