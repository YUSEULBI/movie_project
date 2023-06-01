package model.dto.admin;

public class MovieSeleniumDto {
	
	private String movieCd;
	private String imgUrl;
	
	public MovieSeleniumDto() {	}
	
	@Override
	public String toString() {
		return "MovieSeleniumDto [movieCd=" + movieCd + ", imgUrl=" + imgUrl + "]";
	}
	
	public String getMovieCd() {
		return movieCd;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	
	
}
