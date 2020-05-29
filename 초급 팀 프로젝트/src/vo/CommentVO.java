package vo;

public class CommentVO {

	
	
	private int commentNo; // 댓글 번호
	private String commentContent; // 댓글 내용
	private String commentUser; // 댓글 작성자
	private String commentDate; // 작성일자
	private int matchResult_id; // 매치결과 기본키 참조
	
	
	
	public int getMatchResult_id() {
		return matchResult_id;
	}
	public void setMatchResult_id(int matchResult_id) {
		this.matchResult_id = matchResult_id;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	 
	
	
	
	
	
}
