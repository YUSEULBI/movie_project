package model.dao.reservation;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.Dao;
import model.dto.admin.MovieDto;
import model.dto.reservation.plistDto;
import model.dto.reservation.reservationDto;

public class rmovieDao extends Dao{
	private static rmovieDao rdao = new rmovieDao();
	private rmovieDao() {};
	public static rmovieDao getInstance() {return rdao;}
	 
	//현재 상영중인 영화 리스트
	public ArrayList<plistDto> Playing_movie(){
		
		/* -------------------- 상영중인 영화 리스트 -------------------*/
		String sql = "select  m.mno, m.title  from movie m natural join playinglist p  where pstate =1 group by mno ;";
		ArrayList<plistDto> plist = new ArrayList<>();
		
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while (rs.next()) {
			// 상영중인 영화 리스트 
			plist.add( new plistDto(rs.getInt(1), rs.getString(2) ));
			}//while e	
			
			return plist;
		} catch (SQLException e) {System.err.println(e);}
		return null;
	}
	
	//상영관 정보 출력
	public ArrayList<plistDto> screen_print(int mno, String s_date){
		
		ArrayList<plistDto> plist = new ArrayList<>();
		
		String sql ="select p.playtime ,  s.seat - p.pseat , s.sno ,s.seat , p.pno"
				+ " from movie m natural join playinglist p natural join screen s "
				+ " where mno = "+mno +" and playtime like '%"+s_date+"%' order by playtime asc";
		
		try {
			ps=con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				plistDto pDto = new plistDto(
				rs.getString(1), rs.getInt(2), rs.getInt(3)
				,rs.getInt(4) , rs.getInt(5));
				plist.add(pDto);
			}
			return plist;				
		}catch(Exception e) {System.err.println(e);}
		
		return null;
	}
	
	//특정 영화만 출력
	public plistDto plist_print(int pno) {
		String sql ="select m.title , p.playtime, p.sno, p.pprice "
				+ " from playinglist p "
				+ " natural join movie m where pno = "+pno;
		plistDto dto = null;
		try {
			ps=con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//제목, 날짜, 상영관 위치 
				dto = new plistDto(
				rs.getString(1), rs.getString(2), rs.getInt(3),rs.getInt(4));
			}
		}catch(Exception e) {System.err.println(e);}
		return dto;
	}
	
	//예약된 좌석 확인
	public ArrayList<reservationDto> seatnumPrint(int pno){
		String sql =" select r.seatnum , p.pprice  "
				+ " from reservation r join playinglist p on p.pno = r.pno  "
				+ " where p.pstate=1 and p.pno="+pno;
		
		ArrayList<reservationDto> rlist = new ArrayList<>();
		
		try {
			ps=con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//예약된 좌석만 뽑아오기
				rlist.add(new reservationDto(rs.getString(1) , rs.getInt(2)));
			}
		}catch(Exception e) {System.err.println(e);}
		return rlist;
	}
	
	//결제하기
	public boolean reservation_ticket(String seatnum[],  int mno, int pno) {
		//좌석을 2개이상 예약할 수 있으므로 같은 멤버, 같은 상영관에 다른좌석만 insert
		String sql = "";
		try {
			
			for(int i = 0 ; i <= seatnum.length-1 ; i++) {
				sql = " insert into reservation(seatnum,mno,pno) "
					   + " values ('"+seatnum[i] +"', "+mno+", "+pno+")"; 
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			}
			//예약테이블 insert후 상영중인영화 테이블 좌석 업데이트 
			sql = "update playinglist set pseat=pseat+"+seatnum.length+" where pno = "+pno;
			
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
			return true; 
		}catch(Exception e) {System.err.println(e);}
		return false;
	}
}
