package model.dto.reservation;

public class reservationDto {

	private int rno; 			// 예약고유번호
	private String seatnum; 	// 선택한 좌석
	private int mno; 			//	예약회원번호(FK)
	private int pno; 			//	 상영번호(FK)	
	
	//2023-04-04 영화금액 추가
	private int ppirce; //영화금액


	public reservationDto(int rno, String seatnum, int mno, int pno) {
		super();
		this.rno = rno;
		this.seatnum = seatnum;
		this.mno = mno;
		this.pno = pno;
	}
	
	
	//예약된 좌석만 뽑기
	public reservationDto(String seatnum, int ppirce) {
		super();
		this.seatnum = seatnum;
		this.ppirce = ppirce;
	}


	public reservationDto() {
		super();
	}
	public int getPpirce() {
		return ppirce;
	}


	public void setPpirce(int ppirce) {
		this.ppirce = ppirce;
	}


	public String getSeatnum() {
		return seatnum;
	}         


	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}


	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
	}


	public int getPno() {
		return pno;
	}


	public void setPno(int pno) {
		this.pno = pno;
	}


	@Override
	public String toString() {
		return "reservationDto [rno=" + rno + ", seatnum=" + seatnum + ", mno=" + mno + ", pno=" + pno + ", ppirce="
				+ ppirce + "]";
	}



	
	
	
}
