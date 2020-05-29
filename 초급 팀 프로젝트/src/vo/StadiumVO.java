package vo;

public class StadiumVO {

	private int stadium_id; //경기장 기본키
	private String stadumName; //경기장 이름
	private String loc;
	private int team_id; //님 기본키를 참조
	
	
	
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	
	public int getStadium_id() {
		return stadium_id;
	}
	public void setStadium_id(int stadium_id) {
		this.stadium_id = stadium_id;
	}
	public String getStadumName() {
		return stadumName;
	}
	public void setStadumName(String stadumName) {
		this.stadumName = stadumName;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
	
	
	
}
