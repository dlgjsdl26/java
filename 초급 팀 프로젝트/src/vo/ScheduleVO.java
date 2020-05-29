package vo;

public class ScheduleVO {

	private int scheduls_id; //기본키
	private String date; //날짜 ( 시간포함 )
	private String homeTeamName; //홈 팀 이름
	private String awayTeamName; //어 웨이팀 이름
	private int stadium_id; // 경기장 기본키 참조
	
	
	
	
	public int getScheduls_id() {
		return scheduls_id;
	}
	public void setScheduls_id(int scheduls_id) {
		this.scheduls_id = scheduls_id;
	}
	public int getStadium_id() {
		return stadium_id;
	}
	public void setStadium_id(int stadium_id) {
		this.stadium_id = stadium_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	public String getAwayTeamName() {
		return awayTeamName;
	}
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}
	
	
}
