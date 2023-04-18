package model.dto.reservation;

import java.util.ArrayList;

import model.dto.admin.MovieDto;

public class plistDto {
	private int pno; 			// 고유번호
    private boolean pstate;  		//예약가능상태
    private int pseat;			// 현재예약좌석수
    private int pprice; 		//가격
    private String playtime; 	// 상영시간
	private int mno;			//영화(FK)
    private int sno;			//영화관번호(FK)
    
    //Screen
    private int s_seat ; 		//상영관 총 좌석수
    private int aseat;	//남은좌석
    
    // movieDto
    private String title ;		// 영화제목
    
    
    
   public plistDto(String playtime, int asea, int sno, int s_seat, int pno) {
		super();
		this.playtime = playtime;
		this.aseat = asea;
		this.sno = sno;
		this.s_seat = s_seat;
		this.pno = pno;
	}

	public plistDto(int mno, String title) {
	super(); 
	this.mno = mno;
	this.title = title;
	}
	
	public plistDto( String title , String playtime, int sno , int pprice) {
		super();
		this.playtime = playtime;
		this.sno = sno;
		this.title = title;
		this.pprice = pprice;
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

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getS_seat() {
		return s_seat;
	}

	public void setS_seat(int s_seat) {
		this.s_seat = s_seat;
	}

	public int getAseat() {
		return aseat;
	}

	public void setAseat(int aseat) {
		this.aseat = aseat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "plistDto [pno=" + pno + ", pstate=" + pstate + ", pseat=" + pseat + ", pprice=" + pprice + ", playtime="
				+ playtime + ", mno=" + mno + ", sno=" + sno + ", s_seat=" + s_seat + ", aseat=" + aseat + ", title="
				+ title + "]";
	}


	
	
    
}
