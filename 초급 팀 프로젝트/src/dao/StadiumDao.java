package dao;

import java.util.ArrayList;

import vo.StadiumVO;
import vo.TeamVO;
import data.DataBase;

public class StadiumDao {

	private static StadiumDao instance;
	
	private StadiumDao(){}
	
	public static StadiumDao getInstance(){
		if (instance == null){
			instance = new StadiumDao();
		}
		return instance;
	}
	
	DataBase Database = DataBase.getInstance();
	
	public void insertStadium(StadiumVO stadium){ //경기장 입력
		Database.tb_stadium.add(stadium);
	}
	
	/**
	 * 팀 기본키 = 스타디움 기본키
	 * @param (TeamVO)team
	 * @return (StadiumVO)rtnStadium
	 */
	public StadiumVO stadiumCheck(TeamVO team){
		StadiumVO rtnStadium = null;
		for (int i = 0; i < Database.tb_stadium.size(); i++) { 
			StadiumVO stadium = Database.tb_stadium.get(i);
			
			if(stadium.getTeam_id() == team.getTeam_id()){
				rtnStadium = stadium;
				break;
			}else
				continue;
		}
		return rtnStadium;
	}
	
	
	public ArrayList<StadiumVO> selectStadiumList() {
		return Database.tb_stadium;
	}

	/**
	 * 파라미터로 받은 팀아이디디로 tb_stadium에있는 경기장 정보를 리턴
	 * @param team_id
	 * @return StadiumVO stadium 
	 */
	public StadiumVO sessionStadium(int team_id) { 
		
		StadiumVO stadium = null;
		
		for (int i = 0; i < Database.tb_stadium.size(); i++) {
			if(Database.tb_stadium.get(i).getTeam_id() == team_id){
				
				stadium = Database.tb_stadium.get(i);
				break;
				
			}
		}
		
		return stadium;
	}

	
	
	
	
	/**
	 * 경기장 수정
	 * @stadiumHash (int)stadiumId
	 * @stadiumHash (StadiumVO)team
	 */
	public void updateStadium(int index, StadiumVO stadium){
		Database.tb_stadium.set(index, stadium);
	}
	
	/**
	 * 경기장 삭제
	 * @stadiumHash (int)stadiumId
	 */
	public void deleteStadium(int stadiumId){
		Database.tb_stadium.remove(stadiumId);
	}

	/**
	 * 파라미터로 받은 std(경기장)에 대한 인덱스 리턴
	 * @param std
	 * @return index
	 */
	public int rtnStadiumIndex(StadiumVO std) {

		int index = 0;
		
		for (int i = 0; i < Database.tb_stadium.size(); i++) {
			if(std.getStadium_id() == Database.tb_stadium.get(i).getStadium_id()){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public int lastStadiumID(){
		return Database.tb_stadium.get(Database.tb_stadium.size()-1).getStadium_id();
	}

	public int rtnStadiumID(TeamVO home) {
		int stadiumID = 0;
		for (int i = 0; i < Database.tb_stadium.size(); i++) {
			if(home.getTeam_id() == Database.tb_stadium.get(i).getTeam_id()){
				stadiumID = Database.tb_stadium.get(i).getStadium_id();
				break;
			}
		}
		
		
		return stadiumID;
	}
	
}















