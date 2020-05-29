package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vo.*;
import dao.*;
import data.*;

public class AdminService {

	
	private static AdminService instance;
	
	private AdminService(){}
	
	public static AdminService getInstance(){
		if(instance == null){
			instance = new AdminService();
		}
		return instance;
	}
	
	
	UserDao userDao = UserDao.getInstance();
	TeamDao teamDao = TeamDao.getInstance();
	BoardDao boardDao = BoardDao.getInstance();
	CommentDao commentDao = CommentDao.getInstance();
	MatchResultDao matchResultDao = MatchResultDao.getInstance();
	NoticeDao noticeDao = NoticeDao.getInstance();
	ScheduleDao scheduleDao = ScheduleDao.getInstance();
	SeatDao seatDao = SeatDao.getInstance();
	StadiumDao stadiumDao = StadiumDao.getInstance();
	TicketDao ticketDao = TicketDao.getInstance();
	UserService userService = UserService.getInstance(); 
	
	
	/**
	 * 팀관리
	 */
	public void teamRun() {

		System.out.println("1.팀 추가\t2.팀 수정\t3.팀 삭제\t4.팀 조회\t5.뒤로 가기");
		System.out.print("입력 >> ");
		int input = ScanUtil.nextInt();

		switch (input) {
		case 1:
			teamAdd();
			break;
		case 2:
			teamMod();
			break;
		case 3:
			teamDel();
			break;
		case 4:
			teamPrintOut();
			break;
		case 5:
			return;
		}
	}

	/**
	 * 팀 추가 메서드(관리자 전용)
	 */
	public void teamAdd() {			
		TeamVO team = new TeamVO();
		StadiumVO stadium = new StadiumVO();
		
		System.out.println("팀 추가 기능입니다.");
		System.out.println();
		System.out.print("추가하실 팀의 이름을 입력해주세요> ");
		String teamName = ScanUtil.nextLine();
		team.setName(teamName);
		
		System.out.print("추가하실 팀의 설명을 입력해주세요> ");
		String teamInfo = ScanUtil.nextLine();
		team.setTeamInfo(teamInfo);
		
		System.out.print("추가하실 팀의 연고지를 입력해주세요> ");
		String teamLoc = ScanUtil.nextLine();
		team.setLoc(teamLoc);
		
		System.out.print("추가하실 팀의 경기장 이름을 입력해주세요> ");
		String teamStadium = ScanUtil.nextLine();
		stadium.setStadumName(teamStadium);
		team.setStadiumName(teamStadium);
		
		System.out.print("추가하실 팀의 경기장 위치를 입력해주세요> ");
		String teamStadiumLoc = ScanUtil.nextLine();
		stadium.setLoc(teamStadiumLoc);
		
		int teamNo = teamDao.lastTeamID() + 1; // 팀id
		int stadiumNo = stadiumDao.lastStadiumID() + 1; // 경기장 id
		
		team.setTeam_id(teamNo);
		stadium.setStadium_id(stadiumNo);
		stadium.setTeam_id(teamNo);
		seatAdd(stadium);
		System.out.println("추가하실 팀의 번호는  " + teamNo + " 입니다.(자동부여)");
		System.out.println("추가하실 팀의 경기장 번호는  " + stadiumNo + " 입니다.(자동부여)");
		
		
		
		teamDao.insertTeam(team); //팀 정보 입력
		stadiumDao.insertStadium(stadium); //경기장 정보 입력
		
		System.out.println(teamName + "에 대한 정보가 추가되었습니다.");
		System.out.println();
		
		teamPrintOut();//팀 목록 출력
	}
	/**
	 * 좌석 추가하기
	 * @param stadium
	 */
	public void seatAdd(StadiumVO stadium){
		
		System.out.println("좌석 추가하기");
		
		int seat_id = 0; 
		SeatVO seat = new SeatVO();
		
		String seat_name = "";
		int seat_price = 0;
		
		char seatY = 'a';
		int seatX = 0;
		String st1 = "";
		

		while(true){
			seat_id = seatDao.lastSeatNum() + 1; // 좌석 id
			
			System.out.print("좌석 이름을 입력해주세요 > ");
			seat_name = ScanUtil.nextLine();
			seat.setSeatName(seat_name);
			
			System.out.print("좌석의 가격을 정해주세요 > ");
			seat_price = ScanUtil.nextInt();
			seat.setSeatPrice(seat_price);
			String stringSeatY = "";
			while(true){
				
				System.out.println("좌석의 마지막행을 입력해주세요(A부터 시작)(입력 예. B)");
				System.out.print("입력 >> ");
				stringSeatY = ScanUtil.nextLine();
				if(checkSeatNum(stringSeatY)){
					break;
				}else{
					System.out.println("잘못 입력하셨습니다.");
					continue;
				}
			}
			char[] seatYY = stringSeatY.toCharArray();
			seatY = seatYY[0];
			
			System.out.println("좌석의 마지막열을 입력해주세요(1부터시작)(입력예. 7)");
			System.out.print("입력 >> ");
			seatX = ScanUtil.nextInt();
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			seat.setSeatNum(st1);
			seat.setSeat_id(seat_id);
			seatDao.insertSeat(seat);
			
			System.out.println("좌석을 더 추가하시겠습니까?? (1. YES / 2. NO)");
			System.out.print("입력 >> ");
			String choice = ScanUtil.nextLine();
			if(choice.equals("1")){
				continue;
			}else{
				System.out.println("좌석입력하기 종료");
				break;
			}
		}
		
		
	}
	
	/**
	 * 좌석의 행 유효성 체크
	 * @param seatY
	 * @return
	 */
	public boolean checkSeatNum(String seatY){
		
		String reg = "[A-Z]{1}";
		Pattern pid = Pattern.compile(reg);
		Matcher mid = pid.matcher(seatY);
		
		return mid.matches();
	}
	
	
	
	
	/**
	 * 팀 수정 메서드(관리자 전용)
	 */
	public void teamMod() {
		HashMap<String, Integer> teamHash = new HashMap<>();
		TeamVO team = null;
		StadiumVO stadium = null;
		
		teamPrintOut();//팀 목록 출력(뷰)
		
		System.out.println("수정하실 팀의 번호를 입력해주세요 . ");//수정할 팀 번호 입력
		System.out.print("입력 >> ");
		int input = ScanUtil.nextInt();
			
		teamHash.put("TEAM_ID", input); 
		team = teamDao.indexCheck(teamHash);//팀 목록에 input 번호가 있으면 해당 팀의 정보를 반환
		stadium = stadiumDao.stadiumCheck(team);
			
		if(team == null){ 
			System.out.println("입력하신 번호의 팀이 존재하지 않습니다.");
			System.out.println("다시 입력해주세요");
			teamMod();// 재귀 메서드
		}
		
		String newTeamName = "";
		String newTeamInfo = "";
		String newTeamLoc = "";
		String newTeamStd = "";
		
		//수정 전
		System.out.println("======================================");
		System.out.println("================수정할 팀================");
		System.out.println("1. 팀 번호 : " + team.getTeam_id());
		System.out.println("2. 팀 이름 : " + team.getName());
		System.out.println("3. 팀 설명 : " + team.getTeamInfo());
		System.out.println("4. 팀 지역 : " + team.getLoc());
		System.out.println("5. 팀 경기장 이름 : " + team.getStadiumName());
		System.out.println("======================================");
		main : 
		while(true){
			
			System.out.println("1. 팀이름수정, 2. 팀설명수정, 3. 팀 연고지 수정, 4. 팀 경기장 이름 수정, 5.수정완료, 0. 뒤로가기");
			System.out.print("입력 >> ");
			String choice = ScanUtil.nextLine();
			//수정
			//int newTeamId = teamDao.selectTeamList().get(input-1).getTeam_id();

			switch(choice){
			case "1": //팀이름 수정
				System.out.print("수정할 팀 이름> ");
				newTeamName = ScanUtil.nextLine();
				team.setName(newTeamName);
				break;
			case "2":// 팀설명 수정
				System.out.print("수정할 팀 설명> ");
				newTeamInfo = ScanUtil.nextLine();
				team.setTeamInfo(newTeamInfo);
				break;
				
			case "3": // 팀연고지 수정
				System.out.print("수정할 팀 연고지> ");
				newTeamLoc = ScanUtil.nextLine();
				team.setLoc(newTeamLoc);
				while(true){
					System.out.println("경기장 위치를 바꾸시겠습니까? (1. YES / 2. NO)");
					String yon = ScanUtil.nextLine();
					if(yon.equals("1")){
						System.out.println("새로운 팀 경기장 위치> ");
						String newStadiumLoc = ScanUtil.nextLine();
						stadium.setLoc(newStadiumLoc);
						break;
					}
					else if(yon.equals("2")){
						break;
					}else {
						System.out.println("잘못입력하셨습니다.");
						continue;
					}
					
				}
				break;
				
			case "4": // 팀 경기장 이름 수정
				System.out.print("수정할 팀 경기장 이름> ");
				newTeamStd = ScanUtil.nextLine();
				team.setStadiumName(newTeamStd);
				stadium.setStadumName(newTeamStd);
				break;
			case "5":
				//수정완료
				break main;
			case "0":
				//뒤로가기
				return;
				
			}
		}
		
		
		//team.setTeam_id(newTeamId);
		
		//stadium.setStadium_id(newTeamId);
		
		//stadium.setTeam_id(newTeamId);
		
		teamDao.updateTeam(input-1, team);
		stadiumDao.updateStadium(input-1, stadium);
		
		System.out.println(team.getName() + "팀의 정보가 수정되었습니다.");
		
		showTeamInfo(team);
			
		}
	
	/**
	 * 팀 삭제 메서드(관리자 전용)
	 */
	public void teamDel(){
		
		HashMap<String, Integer> teamHash = new HashMap<>();
		TeamVO team = null;
		
		
		teamPrintOut(); // 팀 목록 출력
		System.out.println("삭제할 팀을 선택해 주세요. ");
		System.out.print("입력 >> ");
		int input = ScanUtil.nextInt();
		
		teamHash.put("TEAM_ID", input-1); 
		team = teamDao.indexCheck(teamHash);//팀 목록에 input 번호가 있으면 해당 팀의 정보를 반환
		
		if(team == null){ 
			System.out.println("입력하신 번호의 팀이 존재하지 않습니다.");
			System.out.println("다시 입력해주세요");
			teamDel();// 재귀 메서드
		}
		
		System.out.println(team.getName() + "팀을 삭제하시겠습니까? ");
		System.out.println("1.삭제\t2.다시 선택\t3.뒤로가기");
		System.out.print("입력 >> ");
		int input1 = ScanUtil.nextInt();
		
		switch(input1){
			case 1:
				teamDao.deleteTeam(input-1);
				stadiumDao.deleteStadium(input-1);
				System.out.println(team.getName() + "팀이 삭제되었습니다.");
				teamPrintOut();
				break;
			case 2:
				teamDel();
				break;
			case 3:
				return;
		}	
	}
	
	
	/**
	 * 팀 정보 보기(관리자)
	 */
	public void showTeamInfo(TeamVO team){
		
		while(true){
			
			System.out.println("=======================================");
			System.out.println("=============="+team.getName()+"===============");
			System.out.println("=======================================");
			System.out.println("팀 번호 : " + team.getTeam_id());
			System.out.println("팀 이름 : " + team.getName());
			System.out.println("팀 설명 : " + team.getTeamInfo());
			System.out.println("팀 연고지 : " + team.getLoc());
			System.out.println("팀 경기장 이름 : " + team.getStadiumName());
			System.out.println("=======================================");
			System.out.print("(0. 뒤로가기) >> ");
			String choice = ScanUtil.nextLine();
			if(choice.equals("0")){
				return;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	

	/**
	 * 팀 목록 출력(뷰)
	 */
	public void teamPrintOut(){
		ArrayList<TeamVO> teamList = teamDao.selectTeamList();
		System.out.println("=====================팀 목록=====================");
		for (int i = 0; i < teamList.size(); i++) {//팀 목록 출력
			TeamVO teamlist = teamList.get(i);
			System.out.println(teamlist.getTeam_id()
							+". "+teamlist.getName());
		}
		System.out.println("===============================================");
	}
	
	

	public void stadiumRun(){
		
		System.out.println("1.경기장 수정\t2.경기장 조회\t3.뒤로가기");
		int input = ScanUtil.nextInt();
		
		switch(input){
			case 1:
				stadiumMod();
				break;
			case 2:
				stadiumPrintOut();
				break;
			case 3:
				return;
		}				
	}
	
	
	/**
	 * 팀별 경기장 목록 출력
	 */
	public void stadiumPrintOut(){
	
		ArrayList<TeamVO> teamList = teamDao.selectTeamList();
		
		System.out.println("=====================================");
		System.out.println("========팀이름=========경기장 이름========");
		for (int i = 0; i < teamList.size(); i++) {
			TeamVO team = teamList.get(i);
		System.out.println(team.getTeam_id()+". "+team.getName()+"\t :   "+team.getStadiumName());
		}
		System.out.println("=====================================");
	}
	
	/**
	 * 경기장 수정
	 */
	public void stadiumMod(){
		
		HashMap<String, Integer> teamHash = new HashMap<>();
		StadiumVO std = null;
		TeamVO team = null;

		stadiumPrintOut(); // 경기장 목록 출력
		
		System.out.println("수정할 경기장 번호를 입력주세요. ");
		System.out.print("입력 >> ");
		int input = ScanUtil.nextInt();
		
		teamHash.put("TEAM_ID", input);
		team = teamDao.indexCheck(teamHash);

		std = stadiumDao.stadiumCheck(team);

		if(std == null){ //반환된 경기장 정보가 없는 경우
			System.out.println("번호를 잘못입력했습니다.");
			System.out.println("다시 입력해주세요.");
			stadiumMod(); //재귀메서드
		}
		
		while(true){
			
			System.out.println("====================================");
			System.out.println("===="+team.getName()+"팀의 경기장 정보====");
			System.out.println("1.경기장 이름 : " + std.getStadumName());
			System.out.println("2.경기장 위치 : " + std.getLoc());
			System.out.println("====================================");
			System.out.println("1. 경기장 이름 수정 / 2. 경기장 위치 수정 / 3. 수정완료 / 0. 뒤로가기");
			String choice = ScanUtil.nextLine();
			if(choice.equals("1")){
				System.out.print("수정할 경기장 이름 입력 >> ");
				String newStdName = ScanUtil.nextLine();
				std.setStadumName(newStdName);
				team.setStadiumName(newStdName);
				continue;
				
			}else if(choice.equals("2")){
				System.out.print("수정할 경기장 위치 입력 >> ");
				String newstdLoc = ScanUtil.nextLine();
				std.setLoc(newstdLoc);
				continue;
				
			}else if(choice.equals("3")){
				break;
			}else if(choice.equals("0")){
				return;
			}else{
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
		}
		
		
		int stdindex = stadiumDao.rtnStadiumIndex(std);
		int teamindex = teamDao.rtnTeamIndex(team);
		
		stadiumDao.updateStadium(stdindex, std);//
		teamDao.updateTeam(teamindex, team);
		
		System.out.println(team.getName() + "팀에 대한 경기장 정보가 수정되었습니다.");
		System.out.println("====================================");
		System.out.println("===="+team.getName()+"팀 경기장 정보====");
		System.out.println("1.경기장 이름 : " + std.getStadumName());
		System.out.println("2.경기장 위치 : " + std.getLoc());
		System.out.println("====================================");
		
	}
	
	

	/**
	 * 일정 추가
	 */
	public void createSchedule() {
		TeamVO home, away  = null;
		String time = "";
		
		ScheduleVO schedule = new ScheduleVO();
		ArrayList<TeamVO> teamlist = teamDao.selectTeamList();
		while(true){
			System.out.println("====================================");
			for (int i = 0; i < teamlist.size(); i++) {
				System.out.println((i+1) + ". " + teamlist.get(i).getName());
			}
			System.out.println("====================================");
			System.out.println("HOME팀을 선택하세요");
			System.out.print("입력 >> ");
			String choiceHomeTeam = ScanUtil.nextLine();
			
			if(Integer.parseInt(choiceHomeTeam)> teamlist.size()){
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}else{
				home = teamlist.get(Integer.parseInt(choiceHomeTeam)-1);
				teamlist.remove(teamlist.get(Integer.parseInt(choiceHomeTeam)-1));
				break;
			}
		}
		
		while(true){
			System.out.println("====================================");
			for (int i = 0; i < teamlist.size(); i++) {
				System.out.println((i+1) + ". " + teamlist.get(i).getName());
			}
			System.out.println("====================================");
			System.out.println("AWAY팀을 선택하세요");
			System.out.print("입력 >> ");
			String choiceAwayTeam = ScanUtil.nextLine();
			if(Integer.parseInt(choiceAwayTeam) > teamlist.size()){
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}else{
				away = teamlist.get(Integer.parseInt(choiceAwayTeam)-1);
				break;
			}
			
		}
		
		while(true){
			System.out.println("날짜와 시간을 입력하세요(YYYY(년)-MM(월)-DD(일)-HH(시):mm(분))");
			time = ScanUtil.nextLine();
			if(checkDate(time)){
				System.out.println("등록 성공!");
				break;
			}else{
				System.out.println("유효하지 않은 날짜 입니다. 다시 입력해주세요");
				continue;
			}
		}
		
		schedule.setScheduls_id(scheduleDao.selectScheduleList().size() + 1);
		schedule.setHomeTeamName(home.getName());
		schedule.setAwayTeamName(away.getName());
		int stadium_id = stadiumDao.rtnStadiumID(home);
		schedule.setStadium_id(stadium_id);
		schedule.setDate(time);

		scheduleDao.insertSchedule(schedule);
	}
	
	/**
	 * 일정 등록, 수정
	 */
	public void schedule_set() {
		int input2;
		do {
			System.out.println("====================================");
			System.out.println("1. 일정 추가");
			System.out.println("2. 일정 조회");
			System.out.println("3. 일정 수정");
			System.out.println("4. 뒤로가기");
			System.out.println("0. 관리자 로그아웃");
			System.out.println("====================================");
			System.out.print("입력 >> ");
			input2 = ScanUtil.nextInt();

			switch (input2) {
			case 1:
				createSchedule();
				break;
			case 2:
				list_s();
				break;
			case 3:
				modify_s();
				break;
			case 4:
				return;
			case 0:
				Session.loginUser = null;
				break;
			}

		} while (input2 != 0);

	}


	
	//일정 조회
	public void list_s() {
		ArrayList<ScheduleVO> schedule = scheduleDao.ScheduleList();
		
		System.out.println("========================================");
		for (int i = 0; i < schedule.size(); i++) {
			System.out.println("----------------------------------------");
			System.out.println(schedule.get(i).getScheduls_id() + ". " + schedule.get(i).getHomeTeamName()
					+ " vs " + schedule.get(i).getAwayTeamName());
			System.out.println("날짜 : " + schedule.get(i).getDate());
			System.out.println("----------------------------------------");
		}
		System.out.println("========================================");

	}
	/**
	 * 일정 수정
	 */
	public void modify_s() {
		
		ArrayList<TeamVO> teamlist = teamDao.selectTeamList();
		
		TeamVO home, away  = null;
		String time = "";

		ScheduleVO schedule1 = null;
		
		list_s();
		System.out.println("수정할 번호를 입력하세요.");
		System.out.print("입력 >> ");
		int num = ScanUtil.nextInt();
		schedule1 = scheduleDao.rtnOneSchedule(num);
		
		System.out.println("해당 일정은 다음과 같습니다.");
		System.out.println(schedule1.getHomeTeamName() + " vs "
				+ schedule1.getAwayTeamName() + "\r"
				+ schedule1.getDate());

		
		
		
		while(true){
			System.out.println("====================================");
			for (int i = 0; i < teamlist.size(); i++) {
				System.out.println((i+1) + ". " + teamlist.get(i).getName());
			}
			System.out.println("====================================");
			System.out.println("HOME팀을 선택하세요");
			System.out.print("입력 >> ");
			String choiceHomeTeam = ScanUtil.nextLine();
			
			if(Integer.parseInt(choiceHomeTeam)> teamlist.size()){
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}else{
				home = teamlist.get(Integer.parseInt(choiceHomeTeam)-1);
				teamlist.remove(teamlist.get(Integer.parseInt(choiceHomeTeam)-1));
				break;
			}
		}
		
		while(true){
			System.out.println("====================================");
			for (int i = 0; i < teamlist.size(); i++) {
				System.out.println((i+1) + ". " + teamlist.get(i).getName());
			}
			System.out.println("====================================");
			System.out.println("AWAY팀을 선택하세요");
			System.out.print("입력 >> ");
			String choiceAwayTeam = ScanUtil.nextLine();
			if(Integer.parseInt(choiceAwayTeam) > teamlist.size()){
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}else{
				away = teamlist.get(Integer.parseInt(choiceAwayTeam)-1);
				break;
			}
			
		}
		while(true){
			System.out.println("날짜와 시간을 입력하세요(YYYY(년)-MM(월)-DD(일)-HH(시):mm(분))");
			time = ScanUtil.nextLine();
			if(checkDate(time)){
				System.out.println("등록 성공!");
				break;
			}else{
				System.out.println("유효하지 않은 날짜 입니다. 다시 입력해주세요");
				continue;
			}
		}
		
		schedule1.setHomeTeamName(home.getName());
		schedule1.setAwayTeamName(away.getName());
		
		int stadium_id = stadiumDao.rtnStadiumID(home);
		schedule1.setStadium_id(stadium_id);
		
		schedule1.setDate(time);

		int index = scheduleDao.indexS(schedule1);

		scheduleDao.setSchedule(index, schedule1);

	}
	/**
	 * 날짜 정규표현식
	 * @param time
	 * @return
	 */
	public boolean checkDate(String time){
		String reg = "(20)\\d{2}(-)(0[1-9]|1[012])(-)(0[1-9]|[12][0-9]|3[01])(-)(0[0-9]|1[0-9]|2[0-3])(:)([0-5][0-9])";
		Pattern pid = Pattern.compile(reg);
		Matcher mid = pid.matcher(time);
		return mid.matches();
	}
	/**
	 * 게시판 관리
	 */
	public void boardlist_m() {

		//userService.saveSession(team_id);
		
		ArrayList<BoardVO> board = boardDao.selectBoardList();
		
		
		String choice = "";
		//String teamName = Session.nowTeam.getName();
		do {
			System.out.println("=============================");
			System.out.println("----------- 티켓야구  -----------");
			System.out.println("----------게시판 관리하기---------");
			System.out.println("=============================");
			for (int i = 0; i < board.size(); i++) {
				System.out.println("번호\t 제목\t\t 작성자\t 날짜");
				System.out.println(board.get(i).getBoardNo() + "\t" + board.get(i).getBoardContent()
						+ "\t\t" + board.get(i).getBoardUser() + "\t"
						+ board.get(i).getBoardDate());
			}
			System.out.println("-----------------------------");
			System.out.println("1. 게시판 수정 / 2. 게시판 삭제 / 3. 뒤로가기 / 0. 로그아웃");
			System.out.print("입력 >> ");
			choice = ScanUtil.nextLine();
			switch (choice) {
			case "1":
				// 목록
				modify_b();
				break;
			case "2":
				// 수정
				remove_b();
				break;
			case "3":
				// 삭제
				return;
			case "0":
				Session.loginUser = null;
				break;

			}

		} while (true);

	}
	/**
	 * 게시판 전체 목록 보기
	 */
	public void list_b()	 {
		ArrayList<BoardVO> board = boardDao.selectBoardList();
		int cnt = 1;
		for (int i = 0; i < board.size(); i++) {
			System.out.println("번호\t 제목\t\t 작성자\t 날짜");
			System.out.println(cnt + " . " + board.get(i).getBoardContent()
					+ " 	" + board.get(i).getBoardUser() + " 	"
					+ board.get(i).getBoardDate());
			cnt++;
		}

	}

	/**
	 * 게시판 수정
	 */
	public void modify_b() {
		String title = "";
		BoardVO board = null;
		while(true){
			
			System.out.println("수정할 게시판의 번호를 선택해주세요. ");
			System.out.print("입력 >> ");
			int choice = ScanUtil.nextInt();
			
			board = boardDao.rtnNumBoard(choice);
			if(board == null){
				System.out.println("없는 게시판 입니다. 다시선택해 주세요");
				continue;
			}else{
				break;
			}
		}
			System.out.print("내용을 입력하세요 >> ");
			title = ScanUtil.nextLine();
			
			board.setBoardContent(title);
			
			int index = boardDao.indexB(board);
			
			boardDao.setboard(index, board);
		
	
	}

	/**
	 * 게시판 삭제
	 */
	public void remove_b() {
		BoardVO board = null;
		while(true){
			
			System.out.println("삭제할 게시판의 번호를 선택해주세요");
			System.out.print("입력 >> ");
			int choice = ScanUtil.nextInt();
			
			board = boardDao.rtnNumBoard(choice);
			if(board == null){
				System.out.println("없는 게시판 입니다. 다시선택해 주세요");
				continue;
			}else{
				break;
			}
		}
		boardDao.removeB(board);

	}
	///////////////////////////////승화(회원관리)///////////////////////////////////////////

	public void userManager(){ //회원관리 뷰 메소드
		String choice;
		
		System.out.println("=============================");
		System.out.println("----------- 회원관리 -----------");
		System.out.println("=============================");
		System.out.println("1. 회원정보수정, " + "2. 회원삭제");
		System.out.println("0. 뒤로가기");
		System.out.println("-----------------------------");
		System.out.print("입력 >> ");
		choice = ScanUtil.nextLine();
		
		switch(choice){
		case "1" :
			userRepair();
			return;
		case "2" :
			userDel();
			return;
		case "0":
			return;
		}
	}
	
	public void userPrintOut(){ //유저 목록 출력메소드
		ArrayList<UserVO> userList = userDao.selectUserList();
		System.out.println("=====================유저 목록=====================");
		for (int i = 0; i < userList.size(); i++) {// 현재 유저 목록 출력
			UserVO userlist = userList.get(i);
			System.out.println(userlist.getId() + " || "+ userlist.getPassword() + " || " + userlist.getName());
		}
		System.out.println("================================================");
	}
	
	public void userRepair() { //유저 수정
	      HashMap<String, String> userHash = new HashMap<>();
	      UserVO user = null;
	      
	      userPrintOut();//팀 목록 출력(뷰)
	      
	      System.out.println("수정하실 유저의 아이디를 입력해주세요. ");//수정할 유저 아이디 입력
	      System.out.print("입력 (0. 뒤로가기) >> ");
	      String input = ScanUtil.nextLine();
	      if(input.equals("0")){
	    	 return;
	      }
	      if(input.equals("admin")){ // 관리자 아이디 수정못하게 하는 기능
	         System.out.println("※ 관리자의 정보는 수정할 수 없습니다 ※");
	         System.out.println("다시 입력해주세요.");
	         userRepair();
	      }else{
	         userHash.put("USER_ID", input); 
	         user = userDao.useridCheck(userHash);//유저 목록에 해당 유저아이디가 있으면 해당 유저 정보를 반환
	        // Session.ChangeUser = userDao.useridCheck(userHash);   
	         if(user == null){ 
	            System.out.println("입력하신 회원정보가 존재하지 않습니다.");
	            System.out.println("다시 입력해주세요");
	            userRepair();// 재귀 메서드
	         }
	         while(true){
	        	 
	        	 System.out.println("======================================");
	        	 System.out.println("================수정할 회원================");
	        	 System.out.println("1. 회원 아이디 : " + user.getId());
	        	 System.out.println("2. 회원 비밀번호 : " + user.getPassword());
	        	 System.out.println("3. 회원 이름 : " + user.getName());
	        	 System.out.println("4. 회원 보유 금액 : " + user.getCash());
	        	 System.out.println("======================================");
	        	 System.out.println("1. 아이디 수정 / 2. 패스워드 수정 / 3. 회원이름 수정 /4. 보유금액 수정 / 5. 완료 / 0. 뒤로가기");
	        	 System.out.print("입력 >> ");
	        	 String choice = ScanUtil.nextLine();
	        	 if(choice.equals("0")){
	        		 return;
	        	 }else if(choice.equals("1")){
	        		 System.out.println("새로운 아이디 > ");
	        		 String newUserId = ScanUtil.nextLine();
	        		 user.setId(newUserId);
	        		 continue;
	        	 }else if(choice.equals("2")){
	        		 System.out.println("새로운 패스워드 > ");
	        		 String newUserPassword = ScanUtil.nextLine();
	        		 user.setPassword(newUserPassword);
	        		 continue;
	        	 }else if(choice.equals("3")){
	        		 System.out.println("새로운 회원이름 > ");
	        		 String newUserName = ScanUtil.nextLine();
	        		 user.setName(newUserName);
	        		 continue;
	        	 }else if(choice.equals("4")){
	        		 System.out.println("새롭게 수정할 회원금액 > ");
	        		 int newUserCash = ScanUtil.nextInt();
	        		 user.setCash(newUserCash);
	        		 continue;
	        	 }else if(choice.equals("5")){
	        		 int index = userDao.userIndex(user);
	        		 userDao.updateUserInfo(index, user);
	        		 System.out.println(user.getId() + "유저의 정보가 수정되었습니다.");
	        		 return;
	        	 }
	         }
	      }
	   }
	
	public void userDel() { //회원 삭제 기능메소드
		HashMap<String, String> userHash = new HashMap<>();
		UserVO user = null;
		
		userPrintOut(); // 유저 목록 출력
		System.out.println("삭제할 유저 아이디를 입력해주세요> ");
		String input = ScanUtil.nextLine();
		if(input.equals("admin")){
			System.out.println("관리자계정은 삭제할 수 없습니다");
			userDel();
		}
		userHash.put("USER_ID", input); 
		user = userDao.useridCheck(userHash); //유저 목록에 확인된 아이디가 있으면 해당 유저 정보를 반환
		System.out.println(user.getId()+"/"+user.getName());
		if(user == null){ 
			System.out.println("입력하신 유저의 아디디가 존재하지 않습니다.");
			System.out.println("다시 입력해주세요");
			userDel();// 재귀 메서드
		}
		
		
		System.out.println(user.getId() + "해당 아이디을 삭제하시겠습니까? ");
		System.out.println("1.삭제\t2.다시 선택\t3.뒤로가기");
		
		int input1 = ScanUtil.nextInt();
		
		switch(input1){
			case 1:
				userDao.deleteUser(user);
				System.out.println(user.getId() + "팀이 삭제되었습니다.");
				userPrintOut();
				break;
			case 2:
				userDel();
				break;
			case 3:
				return;
		}	
	}
	
	
	
}
















