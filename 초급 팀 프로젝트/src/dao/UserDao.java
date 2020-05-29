package dao;

import java.util.ArrayList;
import java.util.HashMap;

import data.DataBase;
import vo.UserVO;

public class UserDao {

	private static UserDao instance;

	private UserDao() {
	}

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	DataBase database = DataBase.getInstance(); // 유저 데이터베이스
	
	//회원 삽입
	public void insertUser(UserVO user){
		
		database.tb_user.add(user);
		
		
	}

	public UserVO selectUser(HashMap<String, String> param) {
		UserVO rtnUser = null;
		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);
			boolean flag = true;
			
			for(String key : param.keySet()){
				String value = param.get(key);
				if(key.equals("ID")){
					if(!user.getId().equals(value)){
						flag = false;
						
					}
				}else if(key.equals("PASSWORD")){
					if(!user.getPassword().equals(value)){
						flag = false;
						
					}
				}/*else if(key.equals("NAME")){
					if(!user.getName().equals(value)){
						flag = false;
					}
				}*/
				
			}
			if(flag) rtnUser = user;
		}
		return rtnUser;
	}

	public ArrayList<UserVO> selectUserList() {
		
		return database.tb_user;
	}
	
	public boolean checkOverLapID(String id){
		boolean checking = false;
		
		if(database.tb_user.isEmpty()){
			checking = true;
		}
		else {
			for (int i = 0; i < database.tb_user.size(); i++) {
				
				if(id.equals(database.tb_user.get(i).getId())){
					checking = false;
					break;
				}
				else {
					checking = true;
				}
			}
			
		}
		return checking;
	}
	
	
	
	public void modifyuserInfo(UserVO user){
		
		
		for (int i = 0; i < database.tb_user.size(); i++) {
			if(user.getId().equals(database.tb_user.get(i).getId())){
				database.tb_user.set(i, user);
				break;
			}
		}
	}
	
	//////////////////////승화(회원관리)/////////////////////////

	/**
	 * 입력한 유저의 번호가 있는지 확인
	 * @param (Map<String, String>)userHash
	 * @return (UserVO)rtnUser
	 */
	public UserVO useridCheck(HashMap<String, String> userHash){ // 유저아이디 존재하는지 안하는지 확인메소드
		
		UserVO rtnUser = null;
		
		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);
			boolean flag = true;
			
			for(String key : userHash.keySet()){
				String value = userHash.get(key);
				if(key.equals("USER_ID")){
					if(!user.getId().equals(value)){
						flag = false;
					}
				}
			}if(flag){
				rtnUser = user;
			}
		}
		return rtnUser;
	}
	
	/**
	 * 팀 정보 수정
	 * @param (int)teamId 팀 번호
	 * @param (TeamVO)team
	 */
	public void updateUserInfo(int index, UserVO user){ //유저 정보 수정 (완료시 true)
		database.tb_user.set(index, user);
	}
	
	public int userIndex(UserVO user){ //유저 아이디를 인트타입으로 변환
		int index = 0;
		for(int i = 0; i < database.tb_user.size(); i++){
			if(user.getId().equals(database.tb_user.get(i).getId())){
				index = i;
				break;
			}
		}
		
		return index;
	}
	/**
	 * 유저 정보 삭제
	 * @param (String)userId
	 * @param (UserVO)user
	 */
	public void deleteUser(UserVO user){
		database.tb_user.remove(user);
	}
}


























