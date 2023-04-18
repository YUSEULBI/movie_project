package model.dao.member;

import model.dao.Dao;
import model.dto.member.MemberDto;

public class MemberDao extends Dao {
	
	private static MemberDao dao = new MemberDao();
	   private MemberDao() {}
	   public static MemberDao getInstance() { return dao; }
	   
	   // 회원가입
	   public boolean signUp( MemberDto dto ) {
	      String sql = "insert member( mid , mpwd , memail , mimg ) values( ? , ? , ? , ? );";
	      try {
	         ps = con.prepareStatement(sql);
	         ps.setString(1, dto.getMid());
	         ps.setString(2, dto.getMpwd());
	         ps.setString(3, dto.getMemail());
	         ps.setString(4, dto.getMimg());
	         int count = ps.executeUpdate();
	         if (count == 1 ) { return true; }
	      } catch (Exception e) {System.out.println(e); }
	      return false;
	   }
	   
	   // 중복 아이디 검사
	   public boolean idCheck( String mid ) {
	      String sql = "select mid from member where mid = ?";
	      try {
	         ps = con.prepareStatement(sql);
	         ps.setString(1, mid);
	         rs = ps.executeQuery();
	         if ( rs.next() ) { return true; } // 중복
	      } catch (Exception e) {System.out.println(e); }
	      return false; // 중복아님
	      
	   }
	   
	   // 중복 이메일 검사
	   public boolean emailCheck( String memail ) {
	      String sql = "select memail from member where memail = "+memail;
	      try {
	         ps = con.prepareStatement(sql);
	         int count = ps.executeUpdate();
	         if (count == 1 ) { return true; }
	      } catch (Exception e) {System.out.println(e); }
	      return false;
	   }
	   

	   // 로그인 // mno 반환
	   public int login( String mid , String mpwd ) {
	      String sql = "select mno from member where mid = '"+mid+"' and mpwd ='"+mpwd+"'";
	      System.out.println("sql : "+sql);
	      try {
	         ps = con.prepareStatement(sql);
	         rs = ps.executeQuery();
	         if ( rs.next() ) { return rs.getInt(1); }
	      } catch (Exception e) { System.out.println(e);    }
	      return 0;
	   }
	   
	   // 로그인정보 dto 반환
	   public MemberDto getMemberDto( Object mno ) {
		   String sql = "select * from member where mno = "+mno;
		   try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if ( rs.next() ) { 
				return new MemberDto(rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5));}
			} catch (Exception e) { System.out.println(e);	}
		   return null;
	   }
	   
	   // 아이디찾기
	   public String findid( String memail ) {
	      String sql = "select mid from member where memail = '"+memail+"'";
	      try {
	         ps = con.prepareStatement(sql);
	         rs = ps.executeQuery();
	         if (rs.next()) { return rs.getString(1); }
	      } catch (Exception e) {System.out.println(e);   }
	      return "false";
	   }
	   
	   // 비밀번호찾기
	   public String findpwd( String mid , String memail , String updatePwd ) {
	      String sql = "select mno from member where mid = ? and memail = ? ";
	      try {
	         ps = con.prepareStatement(sql);
	         ps.setString(1, mid);
	         ps.setString(2, memail);
	         rs = ps.executeQuery();
	         System.out.println("첫번째 rs에 받음 완료");
	         if ( rs.next() ) {
	            System.out.println("첫번째 sql 완료");
	            sql = "update member set mpwd = '"+updatePwd+"' where mno = "+rs.getInt(1);
	            ps = con.prepareStatement(sql);
	            int result = ps.executeUpdate();
	               if ( result == 1 ) {
	            	   System.out.println("두번째 sql 완료");
	                  new MemberDto().sendEmail( memail , updatePwd );
	                  return "true";
	               }
	         }
	      } catch (Exception e) { System.out.println(e);   }
	      return "false";
	   }
	   
	   // 회원정보 업데이트
	   public boolean updateMember( MemberDto dto , int mno , String mpwd ) {
		   System.out.println("dto.getMimg() : "+ dto.getMimg());
		   System.out.println("dto.getMemail() : "+ dto.getMemail());
		   System.out.println("dto.getMemail().equals(getMemberDto(mno).getMemail()) : "+ dto.getMemail().equals(getMemberDto(mno).getMemail()));
		   String sql = "";
		   if ( mpwd == null && dto.getMemail().equals(getMemberDto(mno).getMemail()) ) {
			   sql = "update member set mimg = '"+dto.getMimg()+"' where mno ="+mno;
		   }else if( mpwd == null && !(dto.getMemail().equals(getMemberDto(mno).getMemail())) ){
			   return false;
		   }
		   else {
			   sql = "update member set mpwd = '"+dto.getMpwd()+"' , memail = '"+dto.getMemail()+"' , mimg = '"+dto.getMimg()+"' where mno = "+mno+" and mpwd = "+mpwd;
		   }
		   try {
			ps = con.prepareStatement(sql);
			System.out.println(ps);
			int count = ps.executeUpdate();
			if ( count == 1 ) { return true; }
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}return false;
	   }
	   
	   // 회원탈퇴
	   public boolean deleteMember( String mpwd , int mno ) {
		   String sql = "delete from member where mpwd = ? and mno = ?";
		   try {
			   ps = con.prepareStatement(sql);
			   ps.setString(1, mpwd);
			   ps.setInt(2, mno);
			   int count = ps.executeUpdate();
			   if ( count == 1 ) {return true;}
		   } catch (Exception e) { 	System.out.println(e);   }
		   return false;
	   }

}
