package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.TeamVO;
import data.DataBase;

public class TeamDao {

	private static TeamDao instance;
	
	private TeamDao(){}
	
	public static TeamDao getInstance(){
		if (instance == null){
			instance = new TeamDao();
		}
		return instance;
	}
	
	DataBase Database = DataBase.getInstance();
	
	public void insertTeam(TeamVO team){//팀 입력
		Database.tb_team.add(team);
	}
	
	/**
	 * team_id 받아서 리턴
	 * @param team_id
	 * @return
	 */
	public TeamVO rtnTeam(int team_id){
		TeamVO team = null;
		for (int i = 0; i < Database.tb_team.size(); i++) {
			if(Database.tb_team.get(i).getTeam_id() == team_id){
				team = Database.tb_team.get(i);
				break;
			}else{
				team = null;
			}
		}
		return team;
	}
	
	public ArrayList<TeamVO> selectTeamList() { //팀 목록 출력
		return Database.tb_team;
	}
	
	/**
	 * 입력한 팀의 번호가 있는지 확인
	 * @param (Map<String, Integer>)teamHash
	 * @return (TeamVO)rtnTeam
	 */
	public TeamVO indexCheck(HashMap<String, Integer> teamHash) { // tid, 1

		TeamVO rtnTeam = null;

		for (int i = 0; i < Database.tb_team.size(); i++) {
			TeamVO team = Database.tb_team.get(i);
			boolean flag = true;

			for (String key : teamHash.keySet()) {
				int value = teamHash.get(key);
				if (key.equals("TEAM_ID")) {
					if (team.getTeam_id() != value) {
						flag = false;
					}
				}
			}
			if (flag) {
				rtnTeam = team;
			}
		}
		return rtnTeam;
	}
	
	
	/**
	 * 팀 정보 수정
	 * @param (int)teamId 팀 번호
	 * @param (TeamVO)team
	 */
	public void updateTeam(int index, TeamVO team){ //팀 정보 수정 (완료시 true)
		Database.tb_team.set(index, team);
	}
	
	/**
	 * 팀 정보 삭제
	 * @param (int)teamId
	 * @param (TeamVO)team
	 */
	public void deleteTeam(int teamId){
		Database.tb_team.remove(teamId);
	}

	public int rtnTeamdbSize() {
		return Database.tb_team.size();
	}

	/**
	 * 파라미터로 받은 team(팀)의 인덱스 리턴
	 * @param team
	 * @return index
	 */
	public int rtnTeamIndex(TeamVO team) {
		
		int index = 0;
		for (int i = 0; i < Database.tb_team.size(); i++) {
			if(team.getTeam_id() == Database.tb_team.get(i).getTeam_id()){
				index = i;
				break;
			}
		}
		
		
		
		return index;
	}

	/**
	 * 파라미터로 인덱스를 받아 그 인덱스의 팀을 리턴
	 * @param index
	 * @return
	 */
	public TeamVO rtnIndexTeam(int index) {
		return Database.tb_team.get(index);
	}
	/**
	 * 팀 테이블에있는 마지막 데이터의 id를 반환
	 * @return
	 */
	public int lastTeamID(){
		return Database.tb_team.get(Database.tb_team.size()-1).getTeam_id();
	}
}

























