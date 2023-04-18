package model.dto.member;

public class MovieDto {
	private int mno;			// 영화번호
	private String title;		// 영화제목
	private String openDt;		// 개봉일
	private String directors;	// 감독
	private String genre; 		// 장르
	private int audience;		// 누적관객수
	private boolean mstate; 	// 상영여부 [ true : 상영중, false= 상영중지 ]
	
	public MovieDto() { 	}

	public MovieDto(int mno, String title, String openDt, String directors, String genre, int audience,
			boolean mstate) {
		super();
		this.mno = mno;
		this.title = title;
		this.openDt = openDt;
		this.directors = directors;
		this.genre = genre;
		this.audience = audience;
		this.mstate = mstate;
	}

	@Override
	public String toString() {
		return "MovieDto [mno=" + mno + ", title=" + title + ", openDt=" + openDt + ", directors=" + directors
				+ ", genre=" + genre + ", audience=" + audience + ", mstate=" + mstate + "]";
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpenDt() {
		return openDt;
	}

	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getAudience() {
		return audience;
	}

	public void setAudience(int audience) {
		this.audience = audience;
	}

	public boolean isMstate() {
		return mstate;
	}

	public void setMstate(boolean mstate) {
		this.mstate = mstate;
	}
	
	
}
