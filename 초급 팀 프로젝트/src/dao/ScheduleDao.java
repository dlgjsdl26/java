package dao;

import java.util.ArrayList;

import vo.ScheduleVO;
import vo.TeamVO;
import data.DataBase;
import data.Session;

public class ScheduleDao {
	
	private static ScheduleDao instance;
	
	private ScheduleDao(){}
	
	public static ScheduleDao getInstance(){
		if (instance == null){
			instance = new ScheduleDao();
		}
		return instance;
	}
	
	DataBase Database = DataBase.getInstance();
	
	public void insertSchedule(ScheduleVO schedule){ //일정 입력
		Database.tb_schedule.add(schedule);
	}
	
	/**
	 * 팀 이름 = 홈||어웨이 -> 일정
	 * @param (TeamVO)team
	 * @return (ArrayList<ScheduleVO>)schedule
	 */
	public ArrayList<ScheduleVO> scheduleCheck(TeamVO team){
		ArrayList<ScheduleVO> rtnSchedule = new ArrayList<>();
		for (int i = 0; i < Database.tb_schedule.size(); i++) { 
			ScheduleVO schedule = Database.tb_schedule.get(i);
			
			if(schedule.getHomeTeamName().equals(team.getName()) || schedule.getAwayTeamName().equals(team.getName())){
				rtnSchedule.add(schedule);
			}
		}
		return rtnSchedule;
	}
	
	public ArrayList<ScheduleVO> selectScheduleList() { //일정 목록 출력
		return Database.tb_schedule;
	}

	
	
	/**
	 * 경기장 일정 뽑기(예매)
	 * @param stadium_id
	 * @return ArrayList<ScheduleVO> rtnSchedule
	 */
	public ArrayList<ScheduleVO> rtnSchedule(int stadium_id) {
		
		ArrayList<ScheduleVO> schedule = new ArrayList<>();
		
		for (int i = 0; i < Database.tb_schedule.size(); i++) {

			if(Database.tb_schedule.get(i).getStadium_id() == stadium_id){
				schedule.add(Database.tb_schedule.get(i));  
			}
		}
		
		return schedule;
	}
	/////////////////////////////////////////////////////@@@@
	public ArrayList<ScheduleVO> ScheduleList() {//일정 목록 출력
//		ArrayList<ScheduleVO> schedule_list = new ArrayList<>();
// 		for (int i = 0; i < Database.tb_schedule.size(); i++) { 
//				schedule_list.add(Database.tb_schedule.get(i));	
//		}
		return Database.tb_schedule;
	}
	
	public int indexS(ScheduleVO schedule1){
		
		int index = 0;
		
		for (int i = 0; i < Database.tb_schedule.size(); i++) {
			if(schedule1.getScheduls_id() == Database.tb_schedule.get(i).getScheduls_id()){
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public void setSchedule(int index, ScheduleVO schedule){
		Database.tb_schedule.set(index, schedule);
		
	}

	public ScheduleVO rtnOneSchedule(int num) {
		
		ScheduleVO schedule = null;
		
		for (int i = 0; i < Database.tb_schedule.size(); i++) {
			if(Database.tb_schedule.get(i).getScheduls_id() == num){
				schedule = Database.tb_schedule.get(i);
				break;
			}
				
		}
		
		
		return schedule;
	}
	
	//////////////////////////////////헌이형//////////////////////////////////////
	
	
	public ArrayList<ScheduleVO> compareResult() { // 팀 결과 인데 팀을 구분하는 기능
			
			ArrayList<ScheduleVO> result = new ArrayList<>(); 
			
			for(int i = 0; i < Database.tb_schedule.size(); i++ ){
				
			if(Session.nowTeam.getName().equals(Database.tb_schedule.get(i).getAwayTeamName())
					|| Session.nowTeam.getName().equals(Database.tb_schedule.get(i).getHomeTeamName())){
				
				result.add(Database.tb_schedule.get(i));
				
			}
		}
			return result;
	}
	
	//////////////////////해선/////////////////
	public ArrayList<ScheduleVO> compareSchedule(){
		ArrayList<ScheduleVO> result = new ArrayList<>();
		for(int i = 0; i < Database.tb_schedule.size(); i++){
			if(Database.tb_schedule.get(i).getHomeTeamName().equals(Session.nowTeam.getName()) ||
					Database.tb_schedule.get(i).getAwayTeamName().equals(Session.nowTeam.getName())){
				result.add(Database.tb_schedule.get(i));
			}
		}
		return result;
	}
}













