package dao;

import java.util.ArrayList;

import vo.CommentVO;
import vo.MatchResultVO;
import data.DataBase;

public class CommentDao {
	
	private static CommentDao instance;
	
	private CommentDao(){}
	
	public static CommentDao getInstance(){
		if (instance == null){
			instance = new CommentDao();
		}
		return instance;
	}
	
	DataBase Database = DataBase.getInstance();
	
	public void insertReply(CommentVO reply){ //댓글 입력
		Database.tb_comment.add(reply);
	}
	
	public ArrayList<CommentVO> selectReplyList() {
		return Database.tb_comment;
	}
	
	public void setComment(int index, CommentVO comment){
		Database.tb_comment.set(index, comment);
	}
	
	public int findCommentIndex(CommentVO comment){
		int index = 0;
		
		for (int i = 0; i < Database.tb_comment.size(); i++) {
			if(comment.getCommentNo() == Database.tb_comment.get(i).getCommentNo()){
				index = i;
				break;
			}
		}
		
		
		return index;
	}

	/**
	 * tb_comment에서 파라미터로받은 comment에 해당하는 데이터 삭제
	 * @param comment
	 */
	public void deleteComment(CommentVO comment) {

		Database.tb_comment.remove(comment);
		
	}
	
	////////////////////////////헌이형///////////////////////////////////
	public ArrayList<CommentVO> findcomment(MatchResultVO matchResult){//이전경기결과에 들어가는 댓글 찾기
		ArrayList<CommentVO> comment = new ArrayList<>();
		
		for(int i = 0; i < Database.tb_comment.size(); i++){

			if (Database.tb_comment.get(i).getMatchResult_id() == matchResult.getMatchResult_id()) {
				
				comment.add(Database.tb_comment.get(i));

			}
		}
		return comment;
	}
	
	public void updateComment(CommentVO comment){//이전경기결과에 들어가는 댓글 찾기
		Database.tb_comment.add(comment);
	}




}













