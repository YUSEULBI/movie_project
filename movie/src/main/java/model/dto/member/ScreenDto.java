package model.dto.member;

public class ScreenDto {
	
	private int sno;  // 상영관번호
	private int seat; // 상영관 총좌석수
	
	public ScreenDto() {	}

	public ScreenDto(int sno, int seat) {
		super();
		this.sno = sno;
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "ScreenDto [sno=" + sno + ", seat=" + seat + "]";
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	
}
