package vo;

public class TicketVO {

	private String ticketNo; // 티켓번호
	private String schedul; // 경기일정
	private String userId; //유저아이디
	private String stadiumName; // 경기장 이름
	private String buyDate; //구매 날짜
	private String seatName; //좌석이름
	private String seatNo; //좌석번호	
	private int stadium_id; //경기장 아이디저장
	
	
	public int getStadium_id() {
		return stadium_id;
	}
	public void setStadium_id(int stadium_id) {
		this.stadium_id = stadium_id;
	}
	public String getStadiumName() {
		return stadiumName;
	}
	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getSchedul() {
		return schedul;
	}
	public void setSchedul(String schedul) {
		this.schedul = schedul;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
		
	
}
