package controller.member;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.member.MemberDao;

/**
 * Servlet implementation class FindIdPw
 */
@WebServlet("/findidpw")
public class FindIdPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdPw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println("type : " + type);
		System.out.println("type.equals(\"1\") : " + type.equals("1"));
		
		// 타입 1 ,2(아이디찾기,비번찾기) 공통으로 이메일 필요함
		String memail = request.getParameter("memail");
		System.out.println("memail : " + memail);
		
		// 공통 result
		String result = null;
		
		// 타입 1이면 아이디찾기
		if ( type.equals("1")) {
			
			result = MemberDao.getInstance().findid(memail);
			System.out.println("findid" + result);
			
		
		// 타입 2이면 비밀번호찾기
		}else if( type.equals("2") ) {
			
			String mid = request.getParameter("mid");
			
			// 뽑을 난수 문자 목록
			String ranStr = 
					"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			
			// 임시 비밀번호 만들기
			String updatePwd = "";
			
			// 랜덤클래스 객체만들기
			Random random = new Random();
			
			// 12자리 비밀번호
			for ( int i = 0 ; i < 12 ; i++ ) {
				// ranStr의 문자인덱스 난수생성 ( 0~마지막인덱스 )
				int ranindex = random.nextInt( ranStr.length()  );
				// ranindex번째 문자를 난수비밀번호에 추가
				updatePwd += ranStr.charAt(ranindex);
			} // 비밀번호 생성 끝
			System.out.println("updatePwd : "+updatePwd);
			
			result = MemberDao.getInstance().findpwd(mid, memail, updatePwd);
		}
		
		response.getWriter().print(result);
		
		
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
		// TODO Auto-generated method stub
	}

}
