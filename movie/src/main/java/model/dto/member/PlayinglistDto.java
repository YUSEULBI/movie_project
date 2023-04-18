package model.dto.member;

import java.util.ArrayList;

public class PlayinglistDto {
	private int pno;			// 상영번호
	private boolean pstate;		// 예약가능상태
	private int pseat;			// 현재 예약된 좌석수
	private int pprice ;		// 금액
	private String playtime; 	// 상영시간
	private MovieDto moviedto;	// 영화정보dto 
	private ScreenDto screendto;	// 상영관정보dto( 상영관번호 , 총좌석수 )
	
	public PlayinglistDto() { 	}

	public PlayinglistDto(int pno, boolean pstate, int pseat, int pprice, String playtime, MovieDto moviedto,
			ScreenDto screendto) {
		super();
		this.pno = pno;
		this.pstate = pstate;
		this.pseat = pseat;
		this.pprice = pprice;
		this.playtime = playtime;
		this.moviedto = moviedto;
		this.screendto = screendto;
	}

	@Override
	public String toString() {
		return "PlayinglistDto [pno=" + pno + ", pstate=" + pstate + ", pseat=" + pseat + ", pprice=" + pprice
				+ ", playtime=" + playtime + ", moviedto=" + moviedto + ", screendto=" + screendto + "]";
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public boolean isPstate() {
		return pstate;
	}

	public void setPstate(boolean pstate) {
		this.pstate = pstate;
	}

	public int getPseat() {
		return pseat;
	}

	public void setPseat(int pseat) {
		this.pseat = pseat;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public String getPlaytime() {
		return playtime;
	}

	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}

	public MovieDto getMoviedto() {
		return moviedto;
	}

	public void setMoviedto(MovieDto moviedto) {
		this.moviedto = moviedto;
	}

	public ScreenDto getScreendto() {
		return screendto;
	}

	public void setScreendto(ScreenDto screendto) {
		this.screendto = screendto;
	}

	
	
	
}
