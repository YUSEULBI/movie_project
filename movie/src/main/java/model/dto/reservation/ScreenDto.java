package model.dto.reservation;

public class ScreenDto {
	private int sno ;	// 고유번호
	private int seat;		// 좌석수
	
	public ScreenDto(int sno, int seat) {
		super();
		this.sno = sno;
		this.seat = seat;
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
