package dao;

import java.util.ArrayList;

import vo.MatchResultVO;
import vo.ScheduleVO;
import data.DataBase;

public class MatchResultDao {
	
	private static MatchResultDao instance;
	
	private MatchResultDao(){}
	
	public static MatchResultDao getInstance(){
		if (instance == null){
			instance = new MatchResultDao();
		}
		return instance;
	}
	
	ScheduleDao scheduleDao = ScheduleDao.getInstance();
	DataBase Database = DataBase.getInstance();
	
	public void insertMatchResult(MatchResultVO matchResult){ //일정 입력
		Database.tb_matchresult.add(matchResult);
	}
	
	public ArrayList<MatchResultVO> selectMatchResultList() {
		return Database.tb_matchresult;
	}
	
	//////////////////////헌이형///////////////////////
	
	public ArrayList<MatchResultVO> comparResult(){
		
		ArrayList<ScheduleVO> schedule = scheduleDao.compareResult();
		ArrayList<MatchResultVO> result = new ArrayList<>();
		
		for(int i = 0; i < schedule.size(); i++){
			for (int j = 0; j < Database.tb_matchresult.size(); j++) {
				if(schedule.get(i).getScheduls_id() == Database.tb_matchresult.get(j).getSchedule_id()){
					result.add(Database.tb_matchresult.get(j));
					
				}
			}
			
		}
		
		return result;
	}
}
