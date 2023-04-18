package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.member.MemberDao;
import model.dto.member.MemberDto;

/**
 * Servlet implementation class MemberInfo
 */
@WebServlet("/member/info")
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getSession().getServletContext().getRealPath("/member/img");
		System.out.println(path);
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				path ,
				1024*1024*10 ,
				"UTF-8" ,
				new DefaultFileRenamePolicy()
			);
		
		
	    String mid = multi.getParameter("mid"); System.out.println("mid : "+mid);
	    String mpwd = multi.getParameter("mpwd"); System.out.println("mpwd : "+mpwd);
	    String memail = multi.getParameter("memail"); System.out.println("memail : "+memail);
	    String mimg = multi.getFilesystemName("mimg"); System.out.println("mimg : "+mimg);
	      
	    MemberDto dto = new MemberDto(mid, mpwd, memail, mimg);
	    boolean result =  MemberDao.getInstance().signUp(dto);
	    response.getWriter().print(result);

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberInfo서블릿 put 실행");
		String path = request.getSession().getServletContext().getRealPath("/member/img");
		System.out.println(path);
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				path ,
				1024*1024*10 ,
				"UTF-8" ,
				new DefaultFileRenamePolicy()
			);
		
		// 현재비밀번호
	    String mpwd = multi.getParameter("mpwd"); System.out.println("mpwd : "+mpwd);
	    // 새비밀번호
	    String newmpwd = multi.getParameter("newmpwd"); System.out.println("newmpwd : "+newmpwd);
	    String memail = multi.getParameter("memail"); System.out.println("memail : "+memail);
	    String newmimg = multi.getFilesystemName("newmimg"); System.out.println("newmimg : "+newmimg);
	    
	    if ( newmimg == null ) {
	    	newmimg = MemberDao.getInstance().getMemberDto(request.getSession().getAttribute("login")).getMimg();
	    	System.out.println("newmimg : "+newmimg);
	    }
	    if ( newmpwd == null || newmpwd.equals("") ) {
	    	newmpwd = mpwd;
	    }
	    
	    MemberDto dto = new MemberDto(newmpwd, memail, newmimg);
	    //String mno = (String)request.getSession().getAttribute("login");
	    int mno = (int)request.getSession().getAttribute("login");
	    boolean result =  MemberDao.getInstance().updateMember(dto, mno , mpwd);
	    response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mpwd = request.getParameter("mpwd");
		int mno = (int)request.getSession().getAttribute("login");
		
		boolean result = MemberDao.getInstance().deleteMember(mpwd, mno);
		response.getWriter().print(result);
	}

}
