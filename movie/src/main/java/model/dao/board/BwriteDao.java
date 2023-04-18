package model.dao.board;


import java.util.ArrayList;

import model.dao.Dao;
import model.dto.board.BoardDto;
import model.dto.board.ReplyDto;

public class BwriteDao extends Dao{
	
	private static BwriteDao dao = new BwriteDao();
	private BwriteDao() {}
	public static BwriteDao getInstane() {return dao;};

	
	
	public boolean bwrite(BoardDto dto) {
		String sql = "insert into board(bmovie , bcontent , bscore , mno) values(?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBmovie());
			ps.setString(2, dto.getBcontent());
			ps.setInt(3, dto.getBscore());
			ps.setInt(4, dto.getMno());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public ArrayList<BoardDto> bprint(String bmovie){
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "select bno , bmovie , bcontent , bdate , bscore , mno , mid from board natural join member where bmovie like '%"+bmovie+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
				list.add(dto);
			}
			
			
		} catch (Exception e) {System.out.println("e" + e);
			// TODO: handle exception
		}
		System.out.println("Dao bprint : " + list);
		return list;
	}
	
	
	public ArrayList<BoardDto> reply_view(int bno){
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "select b.bno , b.bmovie , b.bcontent , b.bdate , b.bscore , b.mno , m.mid from board b , member m where b.mno=m.mno and bno="+bno;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//BoardDto dto = new BoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
				BoardDto dto = new BoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), rs.getString(7));
				list.add(dto);
			}
		} catch (Exception e) {System.out.println(e);
			// TODO: handle exception
		}
		return list;
	}
	
	public boolean reply_write(ReplyDto dto) {
		String sql = "insert into reply(rcontent, rmovie, rindex, mno, bno , mid) values(?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getRcontent());
			ps.setString(2, dto.getRmovie());
			ps.setInt(3, dto.getRindex());
			ps.setInt(4, dto.getMno());
			ps.setInt(5, dto.getBno());
			ps.setString(6, dto.getMid());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	public ArrayList<ReplyDto> reply_print(String movie){
		ArrayList<ReplyDto> list = new ArrayList<>();
		
		String sql ="select  r.rno , r.rcontent , r.rmovie ,  r.rindex , r.mno, b.bno, r.rdate , r.mid from board b , reply r where r.rindex=b.bno and r.rmovie=?";
		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, movie);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(true) {ReplyDto dto = new ReplyDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7) , rs.getString(8));
				list.add(dto);}
				
				
				
			}
			
		} catch (Exception e) {System.out.println("r_print : " + e);}
		return list;
	}
	
}




































