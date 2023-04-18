package model.dto.board;

import java.util.ArrayList;

public class BoardDto {
	
	private int bno;
	private String bmovie; 	
	private String bcontent;
	private String bdate ;
	private int bscore;
	private int mno;
	private String mid;
	private ArrayList<ReplyDto> reList;


	
	
	public BoardDto(int bno, String bmovie, String bcontent, String bdate, int bscore, int mno, String mid,
			ArrayList<ReplyDto> reList) {
		super();
		this.bno = bno;
		this.bmovie = bmovie;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bscore = bscore;
		this.mno = mno;
		this.mid = mid;
		this.reList = reList;
	}













	public ArrayList<ReplyDto> getReList() {
		return reList;
	}













	public void setReList(ArrayList<ReplyDto> reList) {
		this.reList = reList;
	}













	public BoardDto(int bno, String bmovie, String bcontent, String bdate, int bscore, int mno, String mid) {
		super();
		this.bno = bno;
		this.bmovie = bmovie;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bscore = bscore;
		this.mno = mno;
		this.mid = mid;
	}













	public String getMid() {
		return mid;
	}













	public void setMid(String mid) {
		this.mid = mid;
	}













	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}













	public BoardDto(String bmovie, String bcontent, int bscore, int mno) {
		super();
		this.bmovie = bmovie;
		this.bcontent = bcontent;
		this.bscore = bscore;
		this.mno = mno;
	}













	public BoardDto(int bno, String bmovie, String bcontent, String bdate, int bscore, int mno) {
		super();
		this.bno = bno;
		this.bmovie = bmovie;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bscore = bscore;
		this.mno = mno;
	}













	public int getBno() {
		return bno;
	}













	public void setBno(int bno) {
		this.bno = bno;
	}













	public String getBmovie() {
		return bmovie;
	}













	public void setBmovie(String bmovie) {
		this.bmovie = bmovie;
	}













	public String getBcontent() {
		return bcontent;
	}













	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}













	public String getBdate() {
		return bdate;
	}













	public void setBdate(String bdate) {
		this.bdate = bdate;
	}













	public int getBscore() {
		return bscore;
	}













	public void setBscore(int bscore) {
		this.bscore = bscore;
	}













	public int getMno() {
		return mno;
	}













	public void setMno(int mno) {
		this.mno = mno;
	}













	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", bmovie=" + bmovie + ", bcontent=" + bcontent + ", bdate=" + bdate
				+ ", bscore=" + bscore + ", mno=" + mno + "]";
	}
	
	
	
	
	
	
	
}
