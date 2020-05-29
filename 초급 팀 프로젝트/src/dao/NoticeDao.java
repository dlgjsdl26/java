package dao;

import java.util.ArrayList;

import vo.NoticeVO;
import data.DataBase;

public class NoticeDao {

	private static NoticeDao instance;
	
	
	private NoticeDao(){}
	
	public static NoticeDao getInstance(){
		if (instance == null){
			instance = new NoticeDao();
		}
		return instance;
	}
	
	DataBase Database = DataBase.getInstance(); 
	
	public void insertNotice(NoticeVO notice){ // 공지사항 작성
		Database.tb_notice.add(notice);
	}
	
	public ArrayList<NoticeVO> selectNoticeList() { //공지사항 출력
	
	      return Database.tb_notice;
	}
	
	////////////////////////////헌이형////////////////////////////////////
	
	public NoticeVO showNoticeContent(int selectNum){//공지사항 내용 조회
		NoticeVO rtnNotice= null;
		
		for(int i = 0; i < Database.tb_notice.size(); i++){
			NoticeVO notice = Database.tb_notice.get(i);
			if(selectNum == notice.getNoticeNo()){
				rtnNotice = notice;  
			}	
		}
		return rtnNotice;
	}
	
	public void updateNotice(int index, NoticeVO notice){ // 공지사항 수정
		
		
		Database.tb_notice.set(indexOfNotice(notice), notice);
		
		
	}
	
	public int indexOfNotice(NoticeVO notice){//공지사항 수정 -> 인덱스 찾기
		int index = 0;
		for (int i = 0; i < Database.tb_notice.size(); i++) {
			if(notice.getNoticeNo() == Database.tb_notice.get(i).getNoticeNo()){
				index = i;
				break;
			}
		}
		return index;
		
	}
	
	
	
	public void deleteNotice(NoticeVO notice){//공지사항 삭제
		Database.tb_notice.remove(notice);
	}
	
	
}




















