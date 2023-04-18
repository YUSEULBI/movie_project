package model.dto.board;

public class SearchDto {
	private String title; //
	private String pimg;
	private String count; // 
	private String link; 
	private String year;
	
	private String infopimg;
	private String infotitle;
	private String infoyear; //
	private String infotime; //
	private String infogenre; //
	private String infocountry; // 
	private String infoold;//
	private String infocontent; //
	
	
	
	
	public SearchDto(String infopimg, String infotitle, String infoyear, String infotime, String infogenre,
			String infocountry, String infoold, String infocontent) {
		super();
		this.infopimg = infopimg;
		this.infotitle = infotitle;
		this.infoyear = infoyear;
		this.infotime = infotime;
		this.infogenre = infogenre;
		this.infocountry = infocountry;
		this.infoold = infoold;
		this.infocontent = infocontent;
	}




	public SearchDto(String title, String pimg, String count, String link, String year) {
		super();
		this.title = title;
		this.pimg = pimg;
		this.count = count;
		this.link = link;
		this.year = year;
	}




	@Override
	public String toString() {
		return "SearchDto [title=" + title + ", pimg=" + pimg + ", count=" + count + ", link=" + link + ", year=" + year
				+ ", infopimg=" + infopimg + ", infotitle=" + infotitle + ", infoyear=" + infoyear + ", infotime="
				+ infotime + ", infogenre=" + infogenre + ", infocountry=" + infocountry + ", infoold=" + infoold
				+ ", infocontent=" + infocontent + "]";
	}




	public SearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}




	public SearchDto(String title, String pimg, String count, String link, String year, String infopimg,
			String infotitle, String infoyear, String infotime, String infogenre, String infocountry, String infoold,
			String infocontent) {
		super();
		this.title = title;
		this.pimg = pimg;
		this.count = count;
		this.link = link;
		this.year = year;
		this.infopimg = infopimg;
		this.infotitle = infotitle;
		this.infoyear = infoyear;
		this.infotime = infotime;
		this.infogenre = infogenre;
		this.infocountry = infocountry;
		this.infoold = infoold;
		this.infocontent = infocontent;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getPimg() {
		return pimg;
	}




	public void setPimg(String pimg) {
		this.pimg = pimg;
	}




	public String getCount() {
		return count;
	}




	public void setCount(String count) {
		this.count = count;
	}




	public String getLink() {
		return link;
	}




	public void setLink(String link) {
		this.link = link;
	}




	public String getYear() {
		return year;
	}




	public void setYear(String year) {
		this.year = year;
	}




	public String getInfopimg() {
		return infopimg;
	}




	public void setInfopimg(String infopimg) {
		this.infopimg = infopimg;
	}




	public String getInfotitle() {
		return infotitle;
	}




	public void setInfotitle(String infotitle) {
		this.infotitle = infotitle;
	}




	public String getInfoyear() {
		return infoyear;
	}




	public void setInfoyear(String infoyear) {
		this.infoyear = infoyear;
	}




	public String getInfotime() {
		return infotime;
	}




	public void setInfotime(String infotime) {
		this.infotime = infotime;
	}




	public String getInfogenre() {
		return infogenre;
	}




	public void setInfogenre(String infogenre) {
		this.infogenre = infogenre;
	}




	public String getInfocountry() {
		return infocountry;
	}




	public void setInfocountry(String infocountry) {
		this.infocountry = infocountry;
	}




	public String getInfoold() {
		return infoold;
	}




	public void setInfoold(String infoold) {
		this.infoold = infoold;
	}




	public String getInfocontent() {
		return infocontent;
	}




	public void setInfocontent(String infocontent) {
		this.infocontent = infocontent;
	}
	
	
	
	
	
}