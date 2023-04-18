package model.dao.board;

import java.util.ArrayList;

import model.dao.Dao;
import model.dto.board.BoardDto;
import model.dto.board.ReplyDto;

public class BoardDao extends Dao{

	private static BoardDao dao = new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstence() {return dao;};
	
	
	public ArrayList<BoardDto> board_print() {
		String sql = " select bno , bmovie , bcontent , bdate , bscore , mno , mid from board natural join member";
		ArrayList<BoardDto> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDto dto = new BoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
				
				list.add(dto);
			}
			
		} catch (Exception e) {System.out.println(e);}
		
		return list;
	}
	
	
	public ArrayList<ReplyDto> reply_view(int bno){
		System.out.println("bno : " + bno);
		String sql = "select rno , rcontent , rmovie , rindex , mno , bno , rdate , mid from reply where bno="+bno;
		ArrayList<ReplyDto> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReplyDto dto = new ReplyDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8));
				list.add(dto);
			}
			
		} catch (Exception e) {System.out.println(e);}
		return list;
		
	}
	

	
}

















