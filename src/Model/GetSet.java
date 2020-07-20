package Model;

import Control.Controller;

public class GetSet {
	Controller controller =new Controller();
	private int filmId;
	private String filmName;
	private String filmCountry;
	private String filmMark;
	private String filmDate;
	private String filmIntro;
	private String filmSite;
	private int userId;
	private int gender;
	private String userName;
	private String userCode;
	private String answer;
	private int status;
	public void setAll(GetSet user,String username) {
		controller.setUser(user, username);
	}
	public int getId() {
		return filmId;
	}
	public void setId(int id) {
		this.filmId = id;
	}
	public String getMovies_name() {
		return filmName;
	}
	public void setMovies_name(String movies_name) {
		this.filmName = movies_name;
	}
	public String getMovies_country() {
		return filmCountry;
	}
	public void setMovies_country(String movies_country) {
		this.filmCountry = movies_country;
	}
	public String getMovies_mark() {
		return filmMark;
	}
	public void setMovies_mark(String movies_mark) {
		this.filmMark = movies_mark;
	}
	public String getMovies_date() {
		return filmDate;
	}
	public void setMovies_date(String movies_date) {
		this.filmDate = movies_date;
	}
	public String getMovies_intro() {
		return filmIntro;
	}
	public void setMovies_intro(String movies_intro) {
		this.filmIntro = movies_intro;
	}
	public String getMovies_site() {
		return filmSite;
	}
	public void setMovies_site(String movies_site) {
		this.filmSite = movies_site;
	}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status=status;
}
	public int getID() {
		return userId;
	}
	public void setID(int id) {
		this.userId = id;
	}
	public String getName() {
		return userName;
	} 
	public void setName(String username) {
		this.userName = username;
	}
	public String getCode() {
		return userCode;
	}
	public void setCode(String code) {
		this.userCode = code;
	}
	public  void setGender(int gender) {
		this.gender = gender;
	}
	public int getGender(){
		return gender;
	}
	public void setAnswer(String answer) {
		this.answer = answer;

	}
	public String getAnswer() {
		return answer;
	}


}
