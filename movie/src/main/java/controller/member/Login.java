package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.member.MemberDao;
import model.dto.member.MemberDto;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // 로그인 dto객체 반환
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object mno = request.getSession().getAttribute("login");
		System.out.println("mno : "+mno);
		
		MemberDto dto = MemberDao.getInstance().getMemberDto(mno);
		System.out.println("dto : "+dto);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(dto);
		System.out.println("json : "+json);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// 로그인 세션 저장
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = request.getParameter("mid"); 	System.out.println("mid : "+mid);
		String mpwd = request.getParameter("mpwd"); System.out.println("mpwd : "+mpwd);
		
		int mno = MemberDao.getInstance().login(mid, mpwd);
		System.out.println("mno : "+mno);
		
		if ( mno > 0 ) {
			request.getSession().setAttribute("login", mno);
			System.out.println("session : "+request.getSession().getAttribute("login"));
			response.getWriter().print("true");
		}else {
			response.getWriter().print("false");
		}
		
		
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
		// TODO Auto-generated method stub
	}

}
