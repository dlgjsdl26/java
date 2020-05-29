package vo;

public class MatchResultVO {

	private int matchResult_id; // 매치결과 기본키
	private int HomeTeamScore; // 홈팀스코어
	private int AwayTeamScore; // 어웨이팀 스코어
	private String matchResult; // 경기결과
	private int schedule_id; // 일정 참조키
	
	
	
	public int getMatchResult_id() {
		return matchResult_id;
	}
	public void setMatchResult_id(int matchResult_id) {
		this.matchResult_id = matchResult_id;
	}
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getHomeTeamScore() {
		return HomeTeamScore;
	}
	public void setHomeTeamScore(int homeTeamScore) {
		HomeTeamScore = homeTeamScore;
	}
	public int getAwayTeamScore() {
		return AwayTeamScore;
	}
	public void setAwayTeamScore(int awayTeamScore) {
		AwayTeamScore = awayTeamScore;
	}
	public String getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}
	
	
	
	
	
	
}
