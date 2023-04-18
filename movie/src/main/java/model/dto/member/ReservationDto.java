package model.dto.member;


public class ReservationDto {
	
	private int rno;		// 예약번호
	private String seatnum; 	// 좌석번호
	private int mno;		// 회원번호
	private PlayinglistDto plistdto;	// 상영정보 dto
	
	public ReservationDto() {	}

	public ReservationDto(int rno, String seatnum, int mno, PlayinglistDto plistdto) {
		super();
		this.rno = rno;
		this.seatnum = seatnum;
		this.mno = mno;
		this.plistdto = plistdto;
	}

	@Override
	public String toString() {
		return "ReservationDto [rno=" + rno + ", seatnum=" + seatnum + ", mno=" + mno + ", plistdto=" + plistdto + "]";
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public PlayinglistDto getPlistdto() {
		return plistdto;
	}

	public void setPlistdto(PlayinglistDto plistdto) {
		this.plistdto = plistdto;
	}

	

	
	 
	
}
