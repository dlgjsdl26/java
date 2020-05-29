package dao;

import java.util.ArrayList;

import vo.BoardVO;
import data.DataBase;

public class BoardDao {
	
	private static BoardDao instance;
	
	private BoardDao(){}
	
	public static BoardDao getInstance(){
		if (instance == null){
			instance = new BoardDao();
		}
		return instance;
	}
	
	DataBase Database = DataBase.getInstance();
	
	public void insertBoard(BoardVO board){ //댓글 입력
		Database.tb_board.add(board);
	}
	
	public ArrayList<BoardVO> selectBoardList() {
		return Database.tb_board;
	}
	
	///////////////////////////////////////////////////
	public void setboard(int index, BoardVO board) {
		Database.tb_board.set(index, board);

	}

	public int indexB(BoardVO board) {

		int index = 0;

		for (int i = 0; i < Database.tb_board.size(); i++) {
			if (board.getBoardNo() == Database.tb_board.get(i).getBoardNo()) {
				index = i;
				break;
			}
		}

		return index;
	}

	public void removeB(BoardVO boardno) {
		Database.tb_board.remove(boardno);
	}
	
	
	/**
	 * 게시판의 번호를 입력받아 해당하는 게시판 리턴
	 * @param choice
	 * @return
	 */
	public BoardVO rtnNumBoard(int choice) {
		
		BoardVO board = null;
		for (int i = 0; i < Database.tb_board.size(); i++) {
			if(choice == Database.tb_board.get(i).getBoardNo()){
				board = Database.tb_board.get(i);
				break;
			}
		}
		
		
		
		return board;
	}

	////////////////////////////////해선///////////////////////////////////
	
	public BoardVO compareBoard(int num){
		BoardVO board = null;
		for(int i = 0; i < Database.tb_board.size(); i++){
			if(Database.tb_board.get(i).getBoardNo() == num){
				board = Database.tb_board.get(i);  
			}
		}
		
		return board;	
	}
	
	public int indexOfBoard(BoardVO board){//공지사항 수정 -> 인덱스 찾기
		int index = 0;
		for (int i = 0; i < Database.tb_board.size(); i++) {
			if(board.getBoardNo() == Database.tb_board.get(i).getBoardNo()){
				index = i;
				break;
			}
		}
		return index;
		
	}
	
	public void updateBoard(int num, BoardVO board){ //댓글 수정
		Database.tb_board.set(num, board);
	}
	
	public void deleteBoard(BoardVO board){ //댓글 삭제
		Database.tb_board.remove(board);
	}

}












