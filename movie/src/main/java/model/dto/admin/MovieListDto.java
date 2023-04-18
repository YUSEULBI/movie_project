package model.dto.admin;


public class MovieListDto {
	
	private int pno;				// 고유번호
	private boolean pstate; 		// 예약가능상태
	private int pseat;				// 현재예약좌석수
	private int pprice;				// 가격
	private String playtime; 		// 상영시간
	private int mno ;				// 영화(FK)
	private int sno ;				// 영화관번호(FK)
	// 추가정보
	private String title;			// 영화제목
	private int rseat;				// 잔여좌석수
	
	public MovieListDto() {}


	public MovieListDto(int pno, boolean pstate, int pseat, int pprice, String playtime, int mno, int sno,
			String title) {
		super();
		this.pno = pno;
		this.pstate = pstate;
		this.pseat = pseat;
		this.pprice = pprice;
		this.playtime = playtime;
		this.mno = mno;
		this.sno = sno;
		this.title = title;
	}

	

	public int getRseat() {
		return rseat;
	}


	public void setRseat(int rseat) {
		this.rseat = rseat;
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


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "MovieListDto [pno=" + pno + ", pstate=" + pstate + ", pseat=" + pseat + ", pprice=" + pprice
				+ ", playtime=" + playtime + ", mno=" + mno + ", sno=" + sno + ", title=" + title + "]";
	}
	
	

}
