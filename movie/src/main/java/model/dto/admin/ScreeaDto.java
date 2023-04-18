package model.dto.admin;

public class ScreeaDto {
	
	private int sno; 	//고유번호
	private int seat; 	//좌석수
	
	public ScreeaDto() {
		// TODO Auto-generated constructor stub
	}

	public ScreeaDto(int sno, int seat) {
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

	@Override
	public String toString() {
		return "ScreeaDto [sno=" + sno + ", seat=" + seat + "]";
	}
	
	
	
	

}
