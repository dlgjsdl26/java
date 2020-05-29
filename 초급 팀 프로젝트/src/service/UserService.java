package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vo.*;
import dao.*;
import data.*;

public class UserService {

	private static UserService instance;
	
	private UserService(){}
	
	public static UserService getInstance(){
		if(instance == null){
			instance = new UserService();
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
	
	//회원 가입
	public void join(){
		String name;
		String id;
		String password;
		mainrogo();
		System.out.println("=============================");
		System.out.println("------------회원가입------------");
		System.out.println("[아이디]는 5~20자의 영문 소문자, 숫자와 ");
		System.out.println("특수기호(_),(-)만 사용 가능합니다.");
		System.out.println("------------------------------");
		System.out.println("[비밀번호]는 8~20자의 영문 대소문자,숫자와 ");
		System.out.println("특수기호가 무조건 포함되어야 합니다.");
		System.out.println("=============================");
//		int cnt = 0;
		id : 
		while(true){
			System.out.print("아이디 : "); 
			id = ScanUtil.nextLine(); //아이디 입력
			
			if(checkingID(id) && userDao.checkOverLapID(id)){ // 아아디 조건이 참, 중복이 없을때
				while(true){ //비밀번호 조건 틀리면 반복하기
					System.out.print("비밀번호 : ");
					password = ScanUtil.nextLine();
					if(checkingPW(password)) { // 비밀번호 조건이 트루일때 
						System.out.print("이름 : ");
						name = ScanUtil.nextLine();
						UserVO user = new UserVO();
						
						user.setId(id);
						user.setPassword(password);
						user.setName(name);
						
						userDao.insertUser(user); //db에 저장
						
						System.out.println("환영합니다!!!."); //메시지
//						cnt++;
//						continue id;
						break id; // 아이디 만들기 반복문 빠져나오기
					}
					else if (!checkingPW(password)) {
						System.out.println("비밀번호 형태가 올바르지 않습니다. 다시 입력해주세요"); //비밀번호 조건이 거짓일때 반복
					}
				}//while 끝
			}//
			else if(!checkingID(id)){ //아이디 조건이 거짓일때 다시 반복
				System.out.println("아이디 형태가 올바르지 않습니다.");
			}
			else if(!userDao.checkOverLapID(id)){ //아이디가 중복이면 다시 반복
				System.out.println("아이디가 중복 입니다.");
			}
			
		}//id while 끝
		
		//정규표현식으로 유효성검사
		//아이디중복확인
		
		
	}
	
	//로그인
	public void login(){
		
		System.out.print("아이디 : ");
		String id = ScanUtil.nextLine();
		System.out.print("비밀번호 : ");
		String password = ScanUtil.nextLine();
		
		HashMap<String, String> param = new HashMap<>();
		param.put("ID", id);
		param.put("PASSWORD", password);
		
		UserVO user = userDao.selectUser(param);
		
		if(user == null){
			System.out.println("로그인 실패");
		}else{
			Session.loginUser = user;
			System.out.println("로그인 성공!!");
			
		}
		
	}
	
	//회원 목록
	public void userList(){
		
		ArrayList<UserVO> userList = userDao.selectUserList(); //유저 테이블
		
		System.out.println("---------------------------------------");
		System.out.println("번호\t아이디\t이름");
		System.out.println("---------------------------------------");
		for (int i = 0; i < userList.size(); i++) {
			UserVO user = userList.get(i);
			System.out.println(i+1 + "\t" + user.getId() + "\t" + user.getName());
		}
		System.out.println("---------------------------------------");
	}
	
	
	// 아이디 정규 표현식
	public boolean checkingID(String id) {

		// 아이디 조건 : 영어 소문자, 숫자, 특수문자는 _-만 허용, 글자수 5~20
		String regexid = "^[a-z0-9_-]{5,20}$";
		Pattern pid = Pattern.compile(regexid);
		Matcher mid = pid.matcher(id);

		return mid.matches();
	}

	// 비밀번호 정규표현식
	public boolean checkingPW(String password) {

		// 숫자,영어 대/소문자, 특수문자를 포함은 8~20자
		String regexpw = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=.*\\S+$).{8,20}$";
		Pattern ppw = Pattern.compile(regexpw);
		Matcher mpw = ppw.matcher(password);

		return mpw.matches();

	}
	
	public String oneUserNum() {

		int usernum1;
		int usernum2;
		int usernum3;
		int usernum4;
		int usernum5;

		String usernum;
		while (true) {

			usernum1 = (int) (Math.random() * 10);
			usernum2 = (int) (Math.random() * 10);
			usernum3 = (int) (Math.random() * 10);
			usernum4 = (int) (Math.random() * 10);
			usernum5 = (int) (Math.random() * 10);

			usernum = String.valueOf(usernum1) + String.valueOf(usernum2)
					+ String.valueOf(usernum3) + String.valueOf(usernum4)
					+ String.valueOf(usernum5);

			if(userDao.checkOverLapID(usernum)){
				break;
			}else{
				continue;
			}
			
		}
		return usernum;

	}	
	
	public void showteaName(){ // 팀이름 나오기
		ArrayList<TeamVO> team = teamDao.selectTeamList();
		int cnt = 1;
		for (int i = 0; i < team.size(); i++) {
			System.out.print("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\t");
			System.out.print((i+1) + ". " + team.get(i).getName() +" \t");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
//			if((i+1) % 2 == 0){
//				System.out.println();
//			}
		}
		
	}
	
	public void noneUserlogin(){
		
		UserVO noneUser = new UserVO();
		
		String nonUserId = noneUserID();
		System.out.println("당신의 아이디는  " + nonUserId + "  입니다. ");
		noneUser.setId(nonUserId);
		noneUser.setPassword(nonUserId);
		noneUser.setName(nonUserId);
		
		userDao.insertUser(noneUser);
		
		Session.loginUser = noneUser;

	}
	
	
	
	
	/**
	 * 경기예약 메서드
	 */
	public void reservation(){ 
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
		int stadium_id  = Session.nowStadium.getStadium_id();
		
		ArrayList<ScheduleVO> schedule = scheduleDao.rtnSchedule(stadium_id);
		ArrayList<SeatVO> seat = seatDao.rtnSeat();
		
		main : 
		while(true){
			mainrogo();
			System.out.println("----------------------------------------");
			for (int i = 0; i < schedule.size(); i++) {
				System.out.println((i+1)+". " + schedule.get(i).getHomeTeamName() + " VS "  + schedule.get(i).getAwayTeamName() 
						+ "  " + schedule.get(i).getDate());
			}
			System.out.println("----------------------------------------");
			System.out.println("관람하실 경기를 선택해 주세요(0. 뒤로가기)");
			System.out.print("입력 >> ");
			String choiceMatch = ScanUtil.nextLine();
			
			if(choiceMatch.equals("0")) { // 0이면 메소드 탈출
				return;
			}
			
			Session.schedule = schedule.get(Integer.parseInt(choiceMatch)-1);
			
			for (int i = 0; i < seat.size(); i++) {
				System.out.println((i+1) + ". " + seat.get(i).getSeatName() + " 가격 : " + seat.get(i).getSeatPrice());
			}
			
			System.out.println("자리를 선택해 주세요");
			System.out.print("입력 >> ");
			String choiceSeat = ScanUtil.nextLine();
			//////////////////////@@@@@@@@@@@@@@@@@@@////////////
			seatArray(seat.get(Integer.parseInt(choiceSeat)-1));
			
			//String choiceSeatNum = "";
			System.out.println("관람 인원수를 입력해 주세요");
			System.out.print("입력 >> ");
			ArrayList<String> seatNum = new ArrayList<>();
			int cntuser = ScanUtil.nextInt();
			int cnt = 0;
			int price = 0;
			
			while(cnt != cntuser){
				
				System.out.println("좌석을 선택해 주세요");
				System.out.print("입력 >> ");
				String choiceSeatN = ScanUtil.nextLine();
				
				
				if(checkingSeat(choiceSeatN)){
					if(!ticketDao.checkiTicket(choiceSeatN,seat.get(Integer.parseInt(choiceSeat)-1).getSeatName()
													,schedule.get(Integer.parseInt(choiceMatch)-1).getDate())){
						System.out.println("이미 예매된 좌석 입니다.");
						continue;
					}else if(!seatDao.existenceSeat(choiceSeatN)){
						System.out.println("해당 경기장에 없는 좌석 입니다.");
						continue;
					}else if(seatNum.contains(choiceSeatN)){
						System.out.println("이미 선택된 좌석입니다.");
						continue;
					}
					//break;
				}else{
					System.out.println("유효하지 않은 좌석입니다. 다시 선택해 주세용");
					continue;
				}
				
				seatNum.add(choiceSeatN);
				cnt++;
			}
			
			price = seat.get(Integer.parseInt(choiceSeat)-1).getSeatPrice()*cntuser; // 총가격
			
				while(true){
					
					if(Session.loginUser.getCash() < price){
						System.out.println("캐쉬 잔액이 부족합니다. 충전 하시겠습니다.? (1. YES / 2.NO)");
						String cash = ScanUtil.nextLine();
						if(cash.equals("1")){
							System.out.println("현재 남은 캐쉬 : " + Session.loginUser.getCash());
							cashCharge();
							System.out.println("현재 남은 캐쉬 : " + Session.loginUser.getCash());
						
						//
						}else if(cash.equals("2")){
							Session.schedule = null;
							return;
						}else {
							System.out.println("잘못 입력 하셨습니다.");
							continue;
						}
					}else{
						break;
					}
				}
			
			while(true){
				
				System.out.println("==========================================================");
				System.out.println("선택하신 경기 : " + schedule.get(Integer.parseInt(choiceMatch)-1).getHomeTeamName() + " VS " 
						+ schedule.get(Integer.parseInt(choiceMatch)-1).getAwayTeamName());
				System.out.println("선택하신 날짜 : " + schedule.get(Integer.parseInt(choiceMatch)-1).getDate());
				System.out.println("경기장 : " + Session.nowStadium.getStadumName());
				System.out.println("인원 수 : " + cntuser);
				System.out.println("선택하신 좌석 : " + seat.get(Integer.parseInt(choiceSeat)-1).getSeatName());
				System.out.print("좌석 번호 : ");
				for (int i = 0; i < seatNum.size(); i++) {
					System.out.print(seatNum.get(i) + ", ");
				}
				System.out.println();
				System.out.println("총가격 : " + price);
				System.out.println("==========================================================");
				System.out.println("(1. 결제완료 / 2. 결제 취소)");
				System.out.print("입력 >> ");
				String buychoice = ScanUtil.nextLine();
				if(buychoice.equals("1")){
					
					Session.loginUser.setCash(Session.loginUser.getCash() - price);
					userDao.modifyuserInfo(Session.loginUser);
					for (int i = 0; i < seatNum.size(); i++) {
						TicketVO ticket = new TicketVO();
						ticket.setTicketNo(ticketNum()); // 티켓번호
						ticket.setUserId(Session.loginUser.getId()); // 현재 유저아이디
						ticket.setBuyDate(sdf.format(today)); // 현재시각
						ticket.setSchedul(schedule.get(Integer.parseInt(choiceMatch)-1).getDate()); // 경기날짜
						ticket.setStadiumName(Session.nowStadium.getStadumName());
						
						ticket.setSeatNo(seatNum.get(i)); // 경기장좌석 번호
						
						ticket.setSeatName(seat.get(Integer.parseInt(choiceSeat)-1).getSeatName()); // 좌석이름
						ticket.setStadium_id(Session.nowStadium.getStadium_id());
						ticketDao.insertTicket(ticket); // 추가
					}
					
					System.out.println("티켓 구매 성공");
					Session.schedule = null;
					break;
				}else if(buychoice.equals("2")){
					Session.schedule = null;
					return;
				}else {
					System.out.println("잘못 입력하셨습니다.");
					continue;
				}
			}
			
//				if(ticketDao.checkiTicket(choiceSeatNum,seat.get(Integer.parseInt(choiceSeat)-1).getSeatName()) && seatDao.existenceSeat(choiceSeatNum)){ //티켓 테이블에 입력받은 좌석번호가 없고, 좌석 테이블에 좌석이 있으면 구매 성공
					
//				}
			
		}//while 문 끝
		
	}
	public void showSeat(){
		ArrayList<SeatVO> seat = seatDao.rtnSeat();
		for (int i = 0; i < seat.size(); i++) {
			System.out.println((i+1) + ". " + seat.get(i).getSeatName() + " 가격 : " + seat.get(i).getSeatPrice());
		}
		
	}
	
	public String noneUserID(){
		
		int usernum1;
		int usernum2;
		int usernum3;
		int usernum4;
		int usernum5;
		String mainID = "비회원";
		String usernum;
		while (true) {

			usernum1 = (int) (Math.random() * 10);
			usernum2 = (int) (Math.random() * 10);
			usernum3 = (int) (Math.random() * 10);
			usernum4 = (int) (Math.random() * 10);
			usernum5 = (int) (Math.random() * 10);

			usernum = mainID + String.valueOf(usernum1) + String.valueOf(usernum2)
					+ String.valueOf(usernum3) + String.valueOf(usernum4)
					+ String.valueOf(usernum5);
			if(userDao.checkOverLapID(usernum)){
				return usernum;
			}else{
				continue;
			}
			
			
			
			
		}
		
		
		
		
	}
	/*
	public void showTeamInfo(){
		
		ArrayList<TeamVO> teamList = teamDao.selectUserList();
		
		System.out.println("---------------------------------------");
		System.out.println("번호\t팁이름\t팀정보");
		System.out.println("---------------------------------------");
		for (int i = 0; i < teamList.size(); i++) {
			TeamVO team = teamList.get(i);
			System.out.println(i+1 + "\t" + team.getName() + "\t" + team.getTeamInfo());
		}
		System.out.println("---------------------------------------");
		
		
	}*/

	/**
	 * tema_id 받아서 리턴
	 * @param team_id
	 * @return
	 */
	public TeamVO rtnTeam(int team_id) {
		
		return teamDao.rtnTeam(team_id);
	}
	
	
	public void showUserInfo(){ //내정보 메소드
		
		String choice = "";
		do{
			mainrogo();
			
			System.out.println("=============================");
			System.out.println("------------ 내정보  -----------");
			System.out.println("=============================");
			System.out.println("아이디 : " + Session.loginUser.getId());
			System.out.println("이름 : " + Session.loginUser.getName());
			System.out.println("캐쉬 잔액 : " + Session.loginUser.getCash());
			
			System.out.println("1.티켓확인 | " + "2.캐쉬충전 | " + "3.뒤로가기" );
			System.out.println("-----------------------------");
			System.out.print("입력 >> ");
			choice = ScanUtil.nextLine();
			switch(choice){
				
			case "1":
				showTicketInfo();
				break;
			case "2":
				cashCharge();
				break;
			case "3":
				return;
			}
		}while(true);
	}
	
	/**
	 * 내가 가지고있는 모든 티켓정보 보기
	 */
	public void showTicketInfo(){
		ArrayList<TicketVO> ticket = ticketDao.showMyTicket();

		mainrogo();
		System.out.println("=============================");
		System.out.println("----------- 티켓정보 -----------");
		System.out.println("=============================");
		
		if(ticket.isEmpty()){
			System.out.println("<< 비어있음 >>");
		}
		else{
			for (int i = 0; i < ticket.size(); i++) {
					System.out.println((i+1) + ". ");
					System.out.println("티켓번호 : " + ticket.get(i).getTicketNo());
					System.out.println("경기일정 : " + ticket.get(i).getSchedul());
					System.out.println("구매날짜 : " + ticket.get(i).getBuyDate());
					System.out.println("경기장 : " + ticket.get(i).getStadiumName());
					System.out.println("좌석이름 : " + ticket.get(i).getSeatName());
					System.out.println("좌석번호 : " + ticket.get(i).getSeatNo());
					System.out.println("-----------------------------");
			}
			System.out.print("0. 뒤로가기 >> ");
			String choice = ScanUtil.nextLine();
			if(choice.equals("0")) return;
			else System.out.println("잘못 입력하셨습니다."); showTicketInfo();
		}
	}
	
	/**
	 * 티켓번호 생성
	 * @return
	 */
	public String ticketNum() { //티켓번호 난수생성 메소드

		int ticketnum1;
		int ticketnum2;
		int ticketnum3;
		int ticketnum4;
		int ticketnum5;

		String ticketnum;
		while (true) {

			ticketnum1 = (int) (Math.random() * 10);
			ticketnum2 = (int) (Math.random() * 10);
			ticketnum3 = (int) (Math.random() * 10);
			ticketnum4 = (int) (Math.random() * 10);
			ticketnum5 = (int) (Math.random() * 10);

			ticketnum = String.valueOf(ticketnum1) + String.valueOf(ticketnum2)
					+ String.valueOf(ticketnum3) + String.valueOf(ticketnum4)
					+ String.valueOf(ticketnum5);

			if(ticketDao.checkOverTicketNum(ticketnum)){
				break;
			}else{
				continue;
			}
			
		}
		return ticketnum;

	}
	
	/**
	 * 캐쉬 충전 해주는 메소드
	 */
	public void cashCharge(){ // 캐쉬충전
		
		
		int cash;
		System.out.println("충전할 금액을 입력해주세요");
		System.out.print("입력 >> ");
		cash = ScanUtil.nextInt();		
		Session.loginUser.setCash(Session.loginUser.getCash() + cash);
		System.out.println("충전완료");
		userDao.modifyuserInfo(Session.loginUser);
		
		
	}

	/**
	 * 세션에 팀정보, 경기장 저장
	 * @param team_id
	 */
	public void saveSession(int index) {

		TeamVO team = teamDao.rtnIndexTeam(index);
		Session.nowTeam = team;
		Session.nowStadium = stadiumDao.sessionStadium(Session.nowTeam.getTeam_id());//세션에 저장한 팀의 경기장 정보를 세션에 저장
		
	}
	
	/**
	 * selectTeamList메소드 불러오기
	 * @return
	 */
	public ArrayList<TeamVO> rtnTeam(){
		return teamDao.selectTeamList();
	}
	
	
	
	/**
	 * 예매 취소
	 */
	public void cancelReservation(){
		
		ArrayList<TicketVO> ticket = ticketDao.showMyTicket();
		
		while(true){
			
			System.out.println("=============================");
			System.out.println("----------- 티켓정보 -----------");
			System.out.println("=============================");
			
			if(ticket.isEmpty()){
				System.out.println("<< 비어있음 >>");
				return;
			}
			else{
				for (int i = 0; i < ticket.size(); i++) {
					System.out.println((i+1) + ". ");
					System.out.println("티켓번호 : " + ticket.get(i).getTicketNo());
					System.out.println("경기일정 : " + ticket.get(i).getSchedul());
					System.out.println("구매날짜 : " + ticket.get(i).getBuyDate());
					System.out.println("경기장 : " + ticket.get(i).getStadiumName());
					System.out.println("좌석이름 : " + ticket.get(i).getSeatName());
					System.out.println("좌석번호 : " + ticket.get(i).getSeatNo());
					System.out.println("-----------------------------");
				}
				
			}
			System.out.println("취소하실 티켓을 선택해주세요");
			System.out.print("입력 (0. 뒤로가기)>> ");
			String choice = ScanUtil.nextLine();
			if(choice.equals("0")){
				return;
			}else if(Integer.parseInt(choice) > ticket.size()){
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}else{
				System.out.println("환불 받으실 금액" + seatDao.priceTicketSeat(ticket.get(Integer.parseInt(choice)-1)));
				System.out.println("정말로 취소 하시겠습니까? (1. YES / 2. NO)");
				System.out.print("입력 >> ");
				String choice1 = ScanUtil.nextLine();
				if(choice1.equals("1")){
					Session.loginUser.setCash(Session.loginUser.getCash() 
							+ seatDao.priceTicketSeat(ticket.get(Integer.parseInt(choice)-1))); // 취소한티켓 가격 추가
					userDao.modifyuserInfo(Session.loginUser); // 
					ticketDao.removeTicket(ticket.get(Integer.parseInt(choice)-1));
					break;
				}else if(choice1.equals("2")){
					System.out.println("취소하셨습니다.");
					return;
				}else{
					System.out.println("잘못 입력하셨습니다. ");
					continue;
				}
				
			}
		}
		
		
	}
	/**
	 * 지난 경기 댓글 관리
	 */
	public void commentAdmin(){
		
		ArrayList<CommentVO> comment = commentDao.selectReplyList();
		
		for (int i = 0; i < comment.size(); i++) {
			System.out.println("-------------------------------------");
			System.out.println((i+1) + "번 댓글");
			System.out.println(comment.get(i).getCommentNo());
			System.out.println(comment.get(i).getCommentContent());
			System.out.println(comment.get(i).getCommentDate());
			System.out.println(comment.get(i).getCommentUser());
			System.out.println("-------------------------------------");
			
		}
		
		System.out.println("수정 또는 삭제하실 댓글을 선택해 주세요");
		System.out.print("입력 (0. 뒤로가기 )>> ");
		String choiceComment = ScanUtil.nextLine();
		if(choiceComment.equals("0")) {
			return;
		}
		System.out.println("댓글번호 " + comment.get(Integer.parseInt(choiceComment)-1).getCommentNo() + " 번 댓글이 선택되었습니다.");
		
		System.out.println("1. 수정하기, 2. 삭제하기");
		System.out.print("입력 >> ");
		String choice = ScanUtil.nextLine();
		switch(choice){
		case "1":
			//수정하기
			modifyComment(comment.get(Integer.parseInt(choiceComment)-1));
			break;
		case "2":
			//삭제하기
			deleteComment(comment.get(Integer.parseInt(choiceComment)-1));
			break;
		}
		
	}
	
	/**
	 * 댓글수정
	 * @param comment
	 */
	public void modifyComment(CommentVO comment){
		while(true){
			System.out.println("---------------댓글 수정하기---------------");
			System.out.println("수정 전");
			System.out.println(comment.getCommentContent());
			System.out.println("수정하실 내용을 입력하세요");
			System.out.print("입력 >> ");
			String content = ScanUtil.nextLine();
			
			System.out.println("수정 후");
			System.out.println(content);
			System.out.println("입력하신 내용으로 수정 하시겠습니까? (1. YES / 2. NO)");
			System.out.print("입력 >> ");
			String choice = ScanUtil.nextLine();
			if(choice.equals("1")) {
				comment.setCommentContent(content);
				int index = commentDao.findCommentIndex(comment);
				commentDao.setComment(index, comment);
				System.out.println("수정완료");
				return;
			}
			else if(choice.equals("2")){
				continue;
			}else{
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			
		}
		
		
	}
	/**
	 * 댓글 삭제하기
	 * @param comment
	 */
	public void deleteComment(CommentVO comment){
		while(true){
			System.out.println("댓글 번호" + comment.getCommentNo()+" 번 댓글을 ");
			System.out.println("정말로 삭제하시겠습니까?? (1. YES / 2. NO)");
			System.out.print("입력 >> ");
			String choice = ScanUtil.nextLine();
			if(choice.equals("1")){
				commentDao.deleteComment(comment);
				break;
			}
			else if (choice.equals("2")) {
				return;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	/**
	 * 욕설체크
	 * @param comment
	 * @return
	 */
	public String checkBadWord(String comment){
		
		String[] badword = {"새끼", "시발", "병신"};
		
		for (int i = 0; i < badword.length; i++) {
			if(comment.contains(badword[i])){
				comment = "클린봇이 부적절한 표현을 감지한 댓글입니다.";
			}
		}
		
		return comment;
	}
	
	
	
	
	
	/**
	 * 좌석 유효한지 확인하기
	 * @param seatnum
	 * @return
	 */
	public boolean checkingSeat(String seatnum){
		
		String reg = "[A-Z]{1}[0-9]+";
		
		Pattern ppw = Pattern.compile(reg);
		Matcher mpw = ppw.matcher(seatnum);

		return mpw.matches();

		
	}
	
	////////////////////////////////////////////////받은 메소드들///////////////////////////////////////////////////////////////
	/**
	 * 팀 정보 보기
	 */
	public void showTeamInfo(){
		
		TeamVO team = Session.nowTeam;
		while(true){
			
			
			rogo();
			System.out.println(" 팀 번호 : " + team.getTeam_id());
			System.out.println(" 팀 이름 : " + team.getName());
			System.out.println(" 팀 설명 : " + team.getTeamInfo());
			System.out.println(" 팀 지역 : " + team.getLoc());
			System.out.println(" 팀 경기장 이름 : " + team.getStadiumName());
			System.out.println("0. 뒤로가기");
			System.out.println("=======================================");
			System.out.print("입력 >> ");
			
			String choice = ScanUtil.nextLine();
			if(choice.equals("0")){
				return;
			}else {
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
	}
	

	
	
	
	
	////////////////////////////////////////////////////

	
	public int rtnTeamDbSize(){
		return teamDao.rtnTeamdbSize();
	}
	//////////////////////////////////헌이형(공지,이전경기댓글)///////////////////////////////////////////////////
	
	SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	ArrayList<NoticeVO> notice = noticeDao.selectNoticeList();
	
	
	NoticeVO noticeVO = new NoticeVO();
	
	public void notice(){//공지사항 목록
		
		
		
		
		while(true){
			if(Session.loginUser == null) return;
			
			System.out.println("=======================================");
			System.out.println("번호\t제목\t  작성자\t\t작성일");
			System.out.println("---------------------------------------");
			for(int i = 0; i < notice.size(); i++){
				System.out.printf("%-8d", notice.get(i).getNoticeNo());
				System.out.printf("%-10s", notice.get(i).getNoticeTitle());
				System.out.printf("%-10s", notice.get(i).getNoticeUser());
				System.out.printf("%s%n", notice.get(i).getNoticeDate());
			}
			
			System.out.println("=======================================");
			
			
				System.out.println("1.조회  / 2. 등록하기 /3. 뒤로가기 /0. 로그아웃");
				System.out.print("입력 >> ");
				String selectNotice = ScanUtil.nextLine();
				
				switch(selectNotice) {
				case "1" : //조회
					if(notice.isEmpty()){
						System.out.println("공지사항이 없습니다.");
						break;
					}
					list();
					break;
				case "2" : //뒤로가기
					reg();
					break;
				case "3" : //뒤로가기
					return;
				case "0" : //종료
					Session.loginUser = null;
					break;
				}
				
			
			
			
		}
		
	}	
	
	
	public void list(){		//공지사항 목록에 있는 제목, 내용 조회
		ArrayList<NoticeVO> notice = noticeDao.selectNoticeList();
		
		System.out.println("조회할 목록의 번호를 선택하세요 >");
		int selectNum = ScanUtil.nextInt();
		NoticeVO notice1= null;
		notice1 = noticeDao.showNoticeContent(selectNum);

		System.out.print("제목 : ");
		System.out.println(notice1.getNoticeTitle());
		System.out.print("내용 : ");
		System.out.println(notice1.getNoticeContent());
		
		if(Session.loginUser.getId().equals("admin")){
			System.out.println("1.수정\t2.삭제\t3목록");
			int selectList2 = ScanUtil.nextInt();
			int deleteyn;
			switch (selectList2){
			case 1 :
				String format_time = format.format (System.currentTimeMillis());
				System.out.println("무엇을 수정하시겠습니까? 1.제목\t2.내용");
				int selectReviseNum = ScanUtil.nextInt();
				switch (selectReviseNum){
					case 1 :
						System.out.print("새로운 제목을 입력하세요>");
						String newtitle = ScanUtil.nextLine();
						
						NoticeVO revisenotice = noticeDao.showNoticeContent(selectNum);
					
						
						revisenotice.setNoticeTitle(newtitle);
						revisenotice.setNoticeDate(format_time);
						
						int index = noticeDao.indexOfNotice(revisenotice);
						noticeDao.updateNotice(index, revisenotice);
						
						break;
						
					case 2 :
						System.out.print("새로운 내용을 입력하세요>");
						String newcontent = ScanUtil.nextLine();
						NoticeVO revisenotice1 = noticeDao.showNoticeContent(selectNum-1);
						if(revisenotice1 == null){
							System.out.println("조회된 게시판이 없습니다 다시 선택해주세요");
							
						}
						
						revisenotice1.setNoticeContent(newcontent);
						revisenotice1.setNoticeDate(format_time);
						
						int index1 = noticeDao.indexOfNotice(revisenotice1);
						noticeDao.updateNotice(index1, revisenotice1);
					}
					break;
			case 2 :
				do{
					System.out.println("정말 삭제하시겠습니까? 1.YES\t 2.NO");
					deleteyn = ScanUtil.nextInt();
					if(deleteyn == 1){
						noticeDao.deleteNotice(notice1);
						break;
					}else if(deleteyn == 2){
						return;	
					}else System.out.println("(Y,N)중에 입력해주세요.");
				}while(!(deleteyn == 1) || !(deleteyn == 2));
			case 3 : 
				return;
		}
	}else{
		System.out.println("0. 목록");
		int input = ScanUtil.nextInt();
		if(input == 0) 
			return;
	}
	}
	
	
	

	public void reg(){//공지사항 등록
		ArrayList<NoticeVO> notice = noticeDao.selectNoticeList();
		
		int counts = notice.get(notice.size()-1).getNoticeNo() + 1;
		
		if(Session.loginUser.getId().equals("admin")){
			
		String format_time = format.format (System.currentTimeMillis());

		System.out.print("제목을 입력해주세요 > ");
		String title = ScanUtil.nextLine();
		System.out.print("내용을 입력해주세요 > ");
		String content = ScanUtil.nextLine();
		
		NoticeVO addNotice = new NoticeVO();
		
		addNotice.setNoticeTitle(title);
		addNotice.setNoticeContent(content);
		addNotice.setNoticeUser(Session.loginUser.getId());
		addNotice.setNoticeNo(counts);
		addNotice.setNoticeDate(format_time);
		noticeDao.insertNotice(addNotice);
		
		}else System.out.println("권한이 없습니다.");	
	}

	
	
	
	
	
	
	
	
	public void previousMatch(){//이전경기 확인
		
		ArrayList<ScheduleVO> result = scheduleDao.compareResult();
		
		ArrayList<MatchResultVO> tb_matchresult = matchResultDao.comparResult();
		
		while(true){
			

			System.out.println("=============================");
			System.out.println("----------- 이전 경기  -----------");
			System.out.println("=============================");
			
			for(int i = 0; i < tb_matchresult.size(); i++){
				System.out.print(i+1 + ". ");
				System.out.print(result.get(i).getDate());
				System.out.print(" - ");
				System.out.print(result.get(i).getHomeTeamName());
				System.out.print(":");
				System.out.print(tb_matchresult.get(i).getHomeTeamScore());
				System.out.print(" VS ");
				System.out.print(result.get(i).getAwayTeamName());
				System.out.print(":");
				System.out.print(tb_matchresult.get(i).getAwayTeamScore());
				System.out.print("  ");
				System.out.println(tb_matchresult.get(i).getMatchResult());
				
				
			}
			
			System.out.println("1. 경기선택\t2. 돌아가기");
			System.out.print("입력 >> ");
			int pmcomment = ScanUtil.nextInt();
			
			switch(pmcomment){
			case 1 :
				System.out.println("몇 번째 경기의 댓글을 확인하시겠습니까?");
				System.out.print("입력>>");
				int pmcomment2 = ScanUtil.nextInt();
				commentout(tb_matchresult.get(pmcomment2-1),result.get(pmcomment2-1));
				break;
			case 2 :
				return;
				
			}
		}
		
		
 	}
	
	public void commentout(MatchResultVO matchResult, ScheduleVO schedule){//이전경기의 댓글 입력
		
		ArrayList<CommentVO> comment = commentDao.findcomment(matchResult);
		
		
		
		
			System.out.println("===========================================================");
			System.out.println("----------------------------- 댓글  -------------------------");
			System.out.println("===========================================================");
			
			System.out.println("-----------------------------------------------------------");
			System.out.print(schedule.getDate());
			System.out.print(" - ");
			System.out.print(schedule.getHomeTeamName());
			System.out.print(":");
			System.out.print(matchResult.getHomeTeamScore());
			System.out.print(" VS ");
			System.out.print(schedule.getAwayTeamName());
			System.out.print(":");
			System.out.print(matchResult.getAwayTeamScore());
			System.out.print("  ");
			System.out.println(matchResult.getMatchResult());
			System.out.println("-----------------------------------------------------------");
			if(comment.isEmpty()){
				System.out.println("<< 비어있음 >>");
			}else{
				for(int i = 0; i < comment.size(); i++){
					System.out.print(comment.get(i).getCommentContent());
					System.out.print("  ");
					System.out.print(comment.get(i).getCommentUser());
					System.out.print("  ");
					System.out.println(comment.get(i).getCommentDate());
				}
			}
			System.out.println("-----------------------------------------------------------");
				System.out.println("1. 댓글달기\t 2.돌아가기");
				System.out.print("입력 >> ");
				int choice = ScanUtil.nextInt();
				
				switch(choice){
				case 1 :
					String format_time = format.format (System.currentTimeMillis());
					System.out.println("작성자 : " + Session.loginUser.getName());
					System.out.println("댓글 내용 입력>>");
					String comment1 = ScanUtil.nextLine();
					comment1 = checkBadWord(comment1);
					int commentNO = commentDao.selectReplyList().get(commentDao.selectReplyList().size()-1).getCommentNo() + 1;
					CommentVO commentadd = new CommentVO();
					commentadd.setCommentNo(commentNO);
					commentadd.setCommentUser(Session.loginUser.getId());
					commentadd.setCommentContent(comment1);
					commentadd.setCommentDate(format_time);
					commentadd.setMatchResult_id(matchResult.getMatchResult_id());
					
					commentDao.updateComment(commentadd);
					break;
				case 2 :
					
					return;
					
				}

	}
	
	//////////////////////////////해선////////////////////////////
	// 경기일정 보기
	public void showSchduleInfo() {
		ArrayList<ScheduleVO> schedulecheck = scheduleDao.compareSchedule();
		System.out.println("-----------------------------------------------");
		System.out.println("번호: \t날짜: \t\t홈: \t어웨이: \t");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < schedulecheck.size(); i++) {
			ScheduleVO schedule = schedulecheck.get(i);
			System.out.println(i + 1 + "\t" + schedule.getDate() + "\t\t"
					+ schedule.getHomeTeamName() + "\t"
					+ schedule.getAwayTeamName());
		}
		System.out.println("-----------------------------------------------");
		System.out.println("0. 뒤로가기");
		String choice = ScanUtil.nextLine();
		if(choice.equals("0")){
			return;
		}else{
			System.out.println("잘못 입력하셨습니다.");
			showSchduleInfo();
		}
	}
	// 게시판 보기
	public void showBoardInfo() {
		ArrayList<BoardVO> boardlist = boardDao.selectBoardList();
		// boardDao.insertBoard();
		// boardDao.selectBoard();
		// boardDao.updateBoard(board);
		while(true){
			System.out.println("----------------------------");
			System.out.println("번호: \t댓글내용: \t\t\t댓글작성자: \t\t작성일자: ");
			System.out.println("----------------------------");
			for (int i = 0; i < boardlist.size(); i++) {
				
				BoardVO board = boardlist.get(i);
				if(board.getTeam_id() == Session.nowTeam.getTeam_id()){
					
					System.out.println(board.getBoardNo() + "\t"
							+ board.getBoardContent() + "\t\t" + board.getBoardUser()
							+ "\t\t" + board.getBoardDate());
				}
			}
			System.out.println("-----------------------------------------");
			System.out.println("1. 등록\t2.수정\t3.삭제\t4.뒤로가기");
			System.out.print("입력 >> ");
			int choice = ScanUtil.nextInt();
			switch (choice) {
			case 1:
				teamBoardwirte();
				break;
			case 2:
				teamBoardupdate();
				break;
			case 3:
				teamBoarddelete();
				break;
			case 4:
				return;
			}
			
		}
	}

	/**
	 * 일반 유저 팀게시판 등록, 수정, 삭제
	 */

	// 등록
	public void teamBoardwirte() {
		Date date = new Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BoardVO board = new BoardVO();
		//ArrayList<BoardVO> boardList = boardDao.selectBoardList();
		//ArrayList<TeamVO> teamList = teamDao.selectTeamList();

		System.out.print("입력할 내용을 작성해주세요 > ");
		String inputcontent = ScanUtil.nextLine();
		inputcontent = checkBadWord(inputcontent);
		int BoardNo = 0;
		
		if(boardDao.selectBoardList().size() == 0){
			BoardNo = 1;
		}else{
			BoardNo = boardDao.selectBoardList().get(boardDao.selectBoardList().size()-1).getBoardNo() + 1;
		}
		
		board.setBoardNo(BoardNo);
		board.setBoardContent(inputcontent);
		board.setBoardUser(Session.loginUser.getId());
		board.setBoardDate(sdf3.format(date));
		board.setTeam_id(Session.nowTeam.getTeam_id());
		boardDao.insertBoard(board);

		
	}

	// 수정
	public void teamBoardupdate() {
		BoardVO board = new BoardVO();
		Date today = new Date();
		ArrayList<BoardVO> boardList = boardDao.selectBoardList();
		ArrayList<UserVO> userList = userDao.selectUserList();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");

		System.out.println("수정할 게시물번호를 선택해주세요>");
		int selectNum = ScanUtil.nextInt();
		BoardVO reviseBoard = boardDao.compareBoard(selectNum);
		
		if(!reviseBoard.getBoardUser().equals(Session.loginUser.getId())){
			System.out.println("권한이 없습니다.");
			return;
		}
		
		System.out.print("새로운 내용을 입력하세요>");
		String newcontent = ScanUtil.nextLine();

		reviseBoard.setBoardContent(newcontent);
		reviseBoard.setBoardDate(sdf.format(today));

		int index = boardDao.indexOfBoard(reviseBoard);
		boardDao.updateBoard(index, reviseBoard);

	}


	// 삭제
	public void teamBoarddelete() {
		//BoardVO board = new BoardVO();
		//ArrayList<UserVO> userList = userDao.selectUserList();
		String username = Session.loginUser.getId();

		System.out.println("삭제할 게시판 번호를 입력해주세요.");
		System.out.print("입력 >> ");
		int selectNum = ScanUtil.nextInt();
		BoardVO board = boardDao.compareBoard(selectNum);
		
			if (username.equals(board.getBoardUser())) {
				
				
				boardDao.deleteBoard(board);
				System.out.println("삭제되었습니다.");
				showBoardInfo();
			}else { 
				System.out.println("권한이 없습니다.");
				return;
			}

	}
	///////////////////////////////재민///////////////////////////////////////////
	/**
	 * 좌석 정보 보기
	 */
	public void showSeatInfo() {

		ArrayList<SeatVO> seatList = seatDao.rtnSeat();
		//SeatVO seat = null;
		String select; // 메뉴선택

		do {
			System.out.println("-----------------------------");
			System.out.println(Session.nowTeam.getName());
			System.out.println(Session.nowStadium.getStadumName());
			System.out.println("-----------------------------");
			for (int i = 0; i < seatList.size(); i++) {
				System.out.println((i + 1) + ". " + seatList.get(i).getSeatName());
			}

			System.out.println((seatList.size()+1) + ". 뒤로가기");
			System.out.println("0. 종료");
			System.out.println("-----------------------------");
			System.out.print("선택 >> ");
			select = ScanUtil.nextLine();
			
			if(select.equals("0")){
				System.out.println("이용해 주셔서 감사합니다.!!");
				System.exit(0); // 종료
			}else if(Integer.parseInt(select) <= seatList.size()){
				System.out.println("=============================");
				System.out.println("좌석이름 : " + seatList.get(Integer.parseInt(select) - 1).getSeatName());
				System.out.println("가격 : "
								+ seatList.get(Integer.parseInt(select) - 1).getSeatPrice());
				while(true){
					seatArray(seatList.get(Integer.parseInt(select) - 1)); 
					System.out.print("0. 뒤로가기 >> ");
					String choice1 = ScanUtil.nextLine();
					if(choice1.equals("0")){
						break;
					}else {
						System.out.println("잘못 입력하셨습니다.");
						continue;
					}
				}
			
			}else if(select.equals(String.valueOf(seatList.size()+1))){
				return;
			}
			else {
				System.out.println("잘못 입력했습니다.");
			}


		} while (true);
	}
	
	/**
	 * 입력받은 문자열에서 Y(행) 리턴
	 * @param (String)seatNum
	 * @return (int)seaty
	 */
	public int input_charAt_Y(String seatNum){ //A,B,C,...
		char seatY = ' ';
		int seaty = 0;
		
		for (int i = 0; i < seatNum.length(); i++) {
			if(seatNum.charAt(i) >= 65 && seatNum.charAt(i) <= 90 || //대문자
					seatNum.charAt(i) >= 97 && seatNum.charAt(i) <= 122){ //소문자
				seatY = seatNum.charAt(i);
			}else if(seatNum.charAt(i) >= 48 && seatNum.charAt(i) <= 57){//숫자
			}else{
				System.out.println("좌석 입력 형식이 잘못되었습니다.");
				System.out.println("다시 입력해주세요.");
				//다시 입력하는 메서드
			}	
			seaty = (seatY - ('A'-1));

			
		}return seaty;
		
	}

	/**
	 * 입력받은 문자열에서 X(열) 리턴
	 * @param (String)seatNum
	 * @return (int)seatx
	 */
	private int input_charAt_X(String seatNum) { //1,2,..20
		char seatX1 = ' ';
		char seatX2 = ' ';
		String seatX = "";
		int seatx = 0;
		
		for (int i = 0; i < seatNum.length(); i++) {
			if(seatNum.charAt(i) >= 65 && seatNum.charAt(i) <= 90 || //대문자
					seatNum.charAt(i) >= 97 && seatNum.charAt(i) <= 122){ //소문자
				
			}else if(seatNum.charAt(i) >= 48 && seatNum.charAt(i) <= 57){//숫자
				if(seatX1 == ' '){
					seatX1 = seatNum.charAt(i);
				}else{
					seatX2 = seatNum.charAt(i);
				}
			}else{
				System.out.println("좌석 입력 형식이 잘못되었습니다.");
				System.out.println("다시 입력해주세요.");
				//다시 입력하는 메서드
			}
			if(seatX2 == ' '){		
				seatX = toString().valueOf(seatX1);
			}else{
				seatX = toString().valueOf(seatX1) + toString().valueOf(seatX2);
			}
		}
		seatx = Integer.parseInt(seatX);

		return seatx;
	}

	/**
	 * 경기장, 구역 별 좌석 배열 표시
	 * @param (StadiumVO)stadium
	 * @param (String)select
	 */
	public void seatArray(SeatVO p_seat){
		//세션, 1
		ArrayList<SeatVO> seatList = seatDao.selectSeatList();
		SeatVO seat = new SeatVO();
		int seatX = 0;
		int seatY = 0;
		boolean flag = true;
	
		System.out.println("===============================");
			for (int i = 0; i < seatList.size(); i++) {
				
				seat = seatList.get(i);

				int lastY = seat.getSeatNum().charAt(seat.getSeatNum().length()-4) - ('A'-1);
				int lastX = (seat.getSeatNum().charAt(seat.getSeatNum().length()-3) - ('1'-1)) * 10 + 
							(seat.getSeatNum().charAt(seat.getSeatNum().length()-2) - ('1'-1));
				
					if(seat.getSeatName().equals(p_seat.getSeatName())){//사용자가 선택한 좌석이 좌석목록에 있는지 검증
						if(seat.getStadium_id() == Session.nowStadium.getStadium_id()){	
							System.out.println("경기장 : " + Session.nowStadium.getStadumName()+"\t\t좌석 : "+seat.getSeatName());
							System.out.println("예매 가능한 좌석 : □\t예매 불가능한 좌석 : ■");
							seatY = lastY;	// 마지막 좌석의 Y
							seatX = lastX; // 마지막 좌석의 X
							flag = showSeatArray(seatY, seatX, seat);
							

						}

						if(!flag){
							System.out.println("===============================");
							return;
						}
					}
			}
					
	}
	
	/**
	 * 좌석 배열 그리는 메서드 
	 * @param (int)seatY //유저가 선택한 경기장, 구역의 행  ex)C
	 * @param (int)seatX	//유저가 선택한 경기장, 구역의 열  ex)4
	 * @param (SeatVO)seat	//유저가 선택한 좌석
	 * @return
	 */
	public boolean showSeatArray(int seatY, int seatX, SeatVO seat){
		//경기장인지, 구역인지 체크하는
		ArrayList<TicketVO> ticketList = ticketDao.selectTicketList();
		TicketVO ticket = null;
		int y_r = 0; //예약된 행의 번호  
		int x_r = 0; //예약된 열의 번호
		int cnt = 0;
		boolean flag = true;
		
		ArrayList<ArrayList<Integer>> seatarr = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> seatarr_y = new ArrayList<>();
		ArrayList<Integer> seatarr_x = new ArrayList<>();
		ArrayList<Integer> count = new ArrayList<>();
		
		for (int i = 0; i < ticketList.size(); i++) { 
			ticket = ticketList.get(i);
			y_r = input_charAt_Y(ticket.getSeatNo()); //int
			seatarr_y.add(y_r);
			
			x_r = input_charAt_X(ticket.getSeatNo()); //int
			seatarr_x.add(x_r);
			
			count.add(cnt++);
		}
		seatarr.add(seatarr_y); //예매된 좌석의 y(행)
		seatarr.add(seatarr_x); //예매된 좌석의 x(열)
		seatarr.add(count);
		
		String[][] seatArr = resetSeat(seatY, seatX); //좌석 초기화
		
		
		if(Session.schedule != null){
			//예매된 좌석
			
			for (int i = 0; i < ticketList.size(); i++) {
				ticket = ticketList.get(i);
				
				if(ticket.getStadium_id() == seat.getStadium_id() && ticket.getSchedul().equals(Session.schedule.getDate())){		//티켓에 저장된 경기장 번호 = 유저가 선택한 좌석의 경기장 번호
					if(ticket.getSeatName().equals(seat.getSeatName())){	//티켓에 저장된 좌석 이름 = 유저가 선택한 좌석의 이름
						for (int j = 0; j < seatarr.get(0).size(); j++) {
							if(seatarr.get(2).get(j) == i){
								if(seatArr[seatarr.get(0).get(j)-1][seatarr.get(1).get(j)-1].equals("__")){
									seatArr[seatarr.get(0).get(j)-1][seatarr.get(1).get(j)-1] = Session.loginUser.getName();
								}
							}
						}
					}
				}
			}
			reference(seatArr); //좌석 그려주는 메서드 (변경사항까지)
		}else{
			String[][] seatArr1 = resetSeat(seatY, seatX);
			reference(seatArr1); //좌석 그려주는 메서드 (변경사항까지)
		}

		
		return flag;
	}
	
	/**
	 * 유저가 선택한 경기장,구역의 좌석 초기화
	 * @param (int)seatY 행
	 * @param (int)seatX 열
	 */
	public String[][] resetSeat(int seatY, int seatX){
		String[][] seatArr = new String[seatY][seatX];
		
		for (int i = 0; i < seatArr.length; i++) {
			for (int j = 0; j < seatArr[i].length; j++) {
				seatArr[i][j] = "__";
			}
		}
		return seatArr;
	}
	
	/**
	 * 행 번호 + 좌석 그리기 (변경사항 포함)
	 * @param (String[][])seatArr
	 */
	public void reference(String[][] seatArr){
		char row = 'A';
		
		for (int i = 0; i < seatArr.length; i++) {
			System.out.print("\n" + row + "행  ");
			row++;
			for (int j = 0; j < seatArr[i].length; j++) {
				String seat = seatArr[i][j].equals("__")?"□":"■";
				System.out.print(seat + "  ");
			}
		}
		System.out.println();
	}
	
	
	public void seatDel(){
		
	}
	
	
	
	public void rogo(){
		
		if(Session.nowTeam.getTeam_id() == 1){
			System.out.println("                                 w5B9999E98Dj                              ");
			System.out.println("                            5ZZZZz    ,     wZZZZZ                         ");
			System.out.println("                         DZZZ     ZZ  ZZ ,ZZZ    WZZZ                      ");
			System.out.println("                       ZZZ    ZZB ZZ8 ZZ  ZZZ ZZZ    ZZj                   ");
			System.out.println("                     ZZy  9ZZW,Z9 W8Z988EEEBW ZZ  ZZ5  ZZ                  ");
			System.out.println("                    ZZ EZ, BDD5Eyz5         j5zEWzZ9 8W ,ZZ                ");
			System.out.println("                  8Z,   ZZZ,ED                  y8B wZZww 9Z               ");
			System.out.println("                 ZZ  zZZ  9z  ,   zZZZ  ZZZZZ      ZD  wjW 5Z              ");
			System.out.println("                8Z wW,5,5Z   j  ZZZZZZZZZ        WW jZjWjjj jZ             ");
			System.out.println("                Z ,jjWwz8  5,   ZZ   ZZZE  8    wy    ZjWjjj DZ            ");
			System.out.println("               Ey   wW5Z w5     ZZ  ZZzZZZZZ   w       9   ,W Z,zZZZZZZ9EZy");
			System.out.println("    jZZZZZZEZZE9zEZZ ,Z  w      8w              yEZZZZEz5DZ ,wZ55jy59ZZZZZ ");
			System.out.println("  ZZzWy5z9ZZZZZZZE9Z8B           5EZZZZZZ8Z5  Z9D5DBDz9EZZZ 5Z ZZZ99B5yW   ");
			System.out.println(" ZWBZZZZZZZZZ9ZWZ8zZ5E  ZZZZ  ZZz5555B8ZZEZ wZ ZZZZZZZWZ8ZD Z ZE95,Ww      ");
			System.out.println(" ZZZ5     B   Z Z8zZ 8jZwyZ, Z,5ZZZZZZ Z88Z Z ZEEZ   85EzZ  ZEZZZZZEzzZZZ  ");
			System.out.println("ZZ     ZBWZ   Z Z8zZ 5ZyZEZ ZyEZ8Z   Z Z8ZZ Z ZzZZ  Z Z9zZ ,  ,yB,DZzZE9Z5 ");
			System.out.println("z    DZ ZZZ  ,ZWZ89Z Z Z98Z Z Z88Z   ZwZzZw ZjZzZw  Z Z88Z ,   z8 5B Z8zZ  ");
			System.out.println("     Z ZEZZ  9DE98EE Z Z8EZ Z Z8EZ  9jZ9zZ zWZ9zZ  jZ Z8ZW ZZBD5Www8ZZZZZ  ");
			System.out.println("     Z ZzZE  Z Z8zZ5 ZwZzZz Z ZzZ9  Z Z88Z Z Z8zBB85 ZZZ59ByzZZZZZZZZZZD   ");
			System.out.println("    yBZEzZ   Z Z8zZ Wzz9zZ w58EzZ   Z Z9ZZ ZwZZZZZZZZZzwBZE9B Z            ");
			System.out.println("    E,Z88ZB8z 9E8ZZ 9 ZE9Z ZwZZZZ   ZZZZZZ ZBBj       8      WZ            ");
			System.out.println(" ZZZE,Z9E5jEZZZZZZ  ZZZZZZ 88BW,                     ZjWjjj,wZ             ");
			System.out.println("   ZZZZZZZZZZzy  ZZ      ,EW                jW,    Z8  ,jW jZ              ");
			System.out.println("                  ZZ,,WjjWWyZ8              W   jE9  ZZ5, zZ               ");
			System.out.println("                   WZ8 ,jW,W w8EDD          jj8Z5 ZZ y8  ZZ                ");
			System.out.println("                     ZZ5  ZZ8 ZZwwDZZz9ZZEE8w5j ZE zZ  ZZj                 ");
			System.out.println("                       ZZ8   BZz ZZ 9ZjwwWZZ ZZw Z   ZZz                   ");
			System.out.println("                         8ZZz j  ZZ DZ BW ZZ 58  ,ZZZ                      ");
			System.out.println("                            zZZZEW     5  , ,zZZZZj                        ");
			System.out.println("                                 jz899EEE99zDw                             ");
		}

		else if(Session.nowTeam.getTeam_id() == 2){
            
            
			System.out.println("                                     jDzz9E8DW                                      ");
			System.out.println("                               ,9ZZZZZZZZZZZZZZZZZZZ8                               ");
			System.out.println("                           zZZZZZZZDw         j9ZZZZZZZ5                            ");
			System.out.println("                         5ZZZZZ5                     EZZZZZ                         ");
			System.out.println("                      8ZZZZj         Z    8ZZj         9ZZZZw                       ");
			System.out.println("                     5ZZZZ            Z   Zw              yZZZZ                     ");
			System.out.println("                   ZZZZ              Z   Z  9Z             jZZZZ                    ");
			System.out.println("                  ZZZZ               ZZZ9 ZZZD              ZZZZB                   ");
			System.out.println("                                                                                    ");
			System.out.println("              EZZZZZZZZZZ ZZZ  jZZZ  ZZZ, 8ZZ9 8ZZZ    9ZZD 5ZZZZZZZZ5              ");
			System.out.println("              9ZZEZZZEZZZ ZZZ  wZZZ  ZZZ  zZZ9  ZZZZ   8ZZ5 5ZZEDEZZZj              ");
			System.out.println("                  Z9Z     Z9Z   ZZZ  ZZZ  BZZz  Z99ZZ5 jZZj zZZE                    ");
			System.out.println("                  Z9Z     ZEZ   ZZE  ZZZ  BZZz  Z9Z5ZZzWZZj   9ZZZZ                 ");
			System.out.println("              	  Z9Z     ZZZ  wZEZ  ZZZ  BZZz  Z9Z  ZZZ9Zj      9ZZZw              ");
			System.out.println("             	  Z9Z      ZZZZZZZZZZZZ   BZZz  Z9Z   ZZ9Zj WW    59Z,              ");
			System.out.println("              	  ZZZ       jZZZ  ZZZZ    ZZZZ  ZZZ    ZZZ9 ZZZZZZZZZ5              ");
			System.out.println("                                                                                    ");
			System.out.println("                           ZZZEEEZZzZZ8ZZzZZZEZ9ZEEEZZZD                            ");
			System.out.println("                             jZZZZZ5 9, ZwW5Z  8 9ZZZZZ                             ");
			System.out.println("                              8zzzZZE9W5898w58ZZEzz8D                               ");
			System.out.println("                                   ZZZZ     ZZZz                                    ");
			System.out.println("                                     jZZZ  ZZZZ                                     ");
			System.out.println("                                      ZZZZZZZ                                       ");
			System.out.println("                                       ZZZZ8                                        ");
			System.out.println("                                         BZ                                         ");
		}
		else if(Session.nowTeam.getTeam_id() == 3){
			
			System.out.println("                              ,jBDB555yw                              ");
			System.out.println("                        jZZZZZZZZzZZZZZZZZZZZz                        ");
			System.out.println("                     DZZZZZ8  Z   ZEZ   Z  ZZZZZZ                     ");
			System.out.println("                   ZZZZZ   Z  Z 55ZZZ,DW8 zZ  ZZZZZ,                  ");
			System.out.println("                 ZZZ8  WZ  ZZZZZZZZZZZZZZZZ  zZ  ZZZZ                 ");
			System.out.println("               yZZE,Z,  ZZZZZw          ,ZZZZZ  EZ ,ZZZ               ");
			System.out.println("              ZZZjZy ZZZZ8             5D , WZZZ8 WZ8ZZZ              ");
			System.out.println("             ZZZw  ,ZZZ5              5yBEw    ZZZZ   ZZZ             ");
			System.out.println("            ZZEEZZwZZZ             EZZ9BB       5ZZwZZZ9ZZ            ");
			System.out.println("           8ZE999ZZZw          DD88Z Z D          ZZZ99EZZZ      ww,D ");
			System.out.println("           ZZEZZZZZ     , z Z Zj Z Z           ,DwEZZZZZZZZZ 9ZZZ8WZZ ");
			System.out.println("          ZZZZZZ8wEZZ, Z, ZWZ85zBZzZZZZZZZZZZZZZw     E    ZZZ    WZ  ");
			System.out.println("     BZZZZZZ     ZZZZZZZZZZD5 ZZD  j5       Z   ,z   DZ    Z     WZ   ");
			System.out.println("     Z    EZ   EZ       ZZ    Z,  Z   ZZZ  Zw  9ZZ  ,Z  w    B   Z5   ");
			System.out.println("    ZZ  ,ZZ   ZZ   ZZ ,,Z   WWw  Z,  EZ8  zB ,ZZZ   Zw  Z  WZ   ZZ    ");
			System.out.println("    ZD  8W  zZZ   8ZZ j   9 j   Z5 WZZZ  wz   W    Z,  ,Z yZ   ZZ     ");
			System.out.println("   ZZ  w  WyZZW  jZwZ    9Z    Z9       DZW     5ZZZwz8ZZ9ZZZZZZ      ");
			System.out.println("  ZZ   ZZ   Zy   Z  Z   EZz WDZZZZZZZZZZz9ZZZZZZZ5z ZZZZZE9Z          ");
			System.out.println(" wZ   ZZZ5   j55ZZEjZZZZZZZZZZy      j Z Z Z Z ,5j  Z89998ZZ          ");
			System.out.println(" ZZZZZZ ,Z   yZZZZZy              Z BZ ZzD9        ZZZZE99Zy          ");
			System.out.println("         ZZZZZZZDzZZ5         , ZjyZ8WZ           ZZZj8Z9ZZ           ");
			System.out.println("            ZZZ   zZZZ       Z ZBZ8             zZZZj  ZZZ            ");
			System.out.println("             ZZZWzWwZZZB    9j8D               ZZZzEj ZZZ             ");
			System.out.println("              ZZZ5y w8ZZZE    j             5ZZZZ B WZZZ              ");
			System.out.println("               WZZZj  ZZZZZZZ8          wZZZZZZwZZw9ZZZ               ");
			System.out.println("                 ZZZZ9Z  WZZZZZZZZZZZZZZZZZZ5y D 8ZZZ                 ");
			System.out.println("                   ZZZZEE W y zDEDZ8ZEZwZ y W 8ZZZZ                   ");
			System.out.println("                     jZZZZZED     Z      WZEZZZZ9                     ");
			System.out.println("                        WZZZZZZZZZZZZZZZZZZZZD                        ");
			System.out.println("                               wjy555jW                               ");
		}
		else if(Session.nowTeam.getTeam_id() == 4){
			System.out.println("                            ,wWw,                                     ");
			System.out.println("                    5ZZZZZZZZZZZZZZZZZZZD                             ");
			System.out.println("                 8ZZZZZZEZZZZZZZZZZZZZZZZZZ9                          ");  
			System.out.println("               ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ,                       ");
			System.out.println("              ZZZZZZZZZZZz           BZZZZZZZZZZZ                     ");
			System.out.println("            ZZZZEZZZZ8,                  BZZZZZZZZZ                   ");
			System.out.println("           ZZZEZZZZW                        ZZZZEZZZ                  ");
			System.out.println("          ZZZEZZZE  ZZZEZ5ZWZjZjZEz ZZ ZZyZ  8ZZZEZZZ                 ");
			System.out.println("         ZZZEZZZy   ZBZZZyZZZZZEZZZZzZDZZZ,   wZZZZZZZ                ");
			System.out.println("        ZZZZZZz                                 ZZZZZZZ               ");
			System.out.println(" 5ZZZZZZZE5Zz5,ZZZZZZZZ9y ,  ZZZZz  , ZZZZZZZZZ   ZZz9ZZZZZ           ");
			System.out.println(" 8        Dz Z         ZZ   Z5   ZZ   Z        ZZ yBy      Zj         ");
			System.out.println("  wZw jZZZ   ZEZ  ZZZZ  9Z   Z D5 9Z   5Z  BZZZ  BZ E  ZZZ5  ZZ       ");
			System.out.println("   Z, 5ZZZ   ZwZ  ZZZZZZZZ  Z  ZZ  Z9 w Z  8ZZZ   ZwZ  ZZZZZZZB       ");
			System.out.println("   Z,      WZZ Z      ZE   jZ EZZZ zZ   Z       jZZ Z,       5        ");
			System.out.println("   Z, yZZZ   Z Z  ZZZZZZ   Z        ZZ  Z  EZZ  EZ  ZBZZZZZ  ZZ       ");
			System.out.println("   ZW yZZZ   ZBZ  ZZ8ZDyZZDZ ZZZZZZ 9Z  Z  EZZZ  ZZ Z  ZZZZ  ZZ       ");
			System.out.println("  B         ZZ8         ZZ    WZZ,    ZZ    5Zy    ZZ        ZZ       ");
			System.out.println("  9ZZZZZZ9ZZZ ZEZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ9Zj9z9ZZZZZZ        ");
			System.out.println("  E99988Z85yZ  jB99999zDB5Z88DyyB8989DDDz88D5DBj wz,ZEZ988B,          ");
			System.out.println("        ZZZZZZZ                                 EZZZZZZ               ");
			System.out.println("         ZZZEZZZ5      WDZZZZZZZZZZZEZ w      WZZZEZZZ                ");
			System.out.println("          ZZZEZZZE    5 5ZZZZZZzZZZZ ZZ,5    8ZZZEZZZ                 ");
			System.out.println("           ZZZEZZZZB                       DZZZZEZZZ                  ");
			System.out.println("            ZZZZEZZZZzw                 ,BZZZZZZZZZ                   ");
			System.out.println("              ZZZZEZZZZZZBw         ,DZZZZZZZZZZZ                     ");
			System.out.println("               ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ,                       ");
			System.out.println("                   9ZZZZZZEZZZZZZZZZZZEZZZZZZ9                        ");
			System.out.println("                      5ZZZZZZZZZZZZZZZZZZZD                           ");
			System.out.println("                             wWWWw                                    ");
		}
		else if(Session.nowTeam.getTeam_id() == 5){
			System.out.println("                             zZZZZZZZZZZZZZZZ9                          ");
			System.out.println("                         BZZZZZy Z ZZ ZZE zZ ZZZZw                      ");
			System.out.println("                      wZZZZZ zZB Z ZZ ZZj Z  98ZZZZZ                    ");
			System.out.println("                    wZZZZEEZEw9ZZZZZZZZZZZZZZEZEEEZZZZ                  ");
			System.out.println("                   ZZZEEEZZZZZz             8ZZZZZEEZZZE                ");
			System.out.println("                 8ZZZZEZZZZW          w,       yZZZEEEZZZ               ");
			System.out.println("                 5  jZZZZ              ww        ,ZZZEEEZZ,             ");
			System.out.println("          5ZZZZZZZZZD B  EZZZ           WW,   ,,   zZZZZZW,E,           ");
			System.out.println("        ZZZZZZZj jZzEZ  ZZZZZ            wWw   ,,w, jZZ  zZZ            ");
			System.out.println("      ZZZ9ZZW     ZZZZ   D5W                           9ZEEZZ           ");
			System.out.println("     ZZ98ZZ     ZZ5    BZZZZ   ZZZZZZZZZD ZZZZZZZZZE ZZZ89ZZy 8ZZZZZZ8  ");
			System.out.println("    ZZ8zZZ           , ZE8Z  ZZZZZ Z989Z zZz9Zy Z89Z  98zZW zZZZ5 zZZZZ ");
			System.out.println("    Z88zZ,  ,ZZZZZZZZ EZzZZ ZZ8Z8  Z8zZD Z98Z,  Zz8Z  Z88Z  Z8zEB       ");
			System.out.println("    Z88zZ wZZZB5Zz8Z  Z88Z  Z88Z   Z89Z yZzEZ  8ZzEZ ZZzZZ  ZZZZZZZZZ   ");
			System.out.println("    ZZ9zEZ      ZzZZ 5Zz8Z 8Zz8E  ZZzEy Z9zZy  Z9zZ  Z8zZ  ZZ   WZz8ZZ  ");
			System.out.println("     ZZZZZZZZZZZZ9Z  ZZ99EjZZZEZ ZZZ9ZDBZ8EZ   ZZ8Z ZZZ9Z ZZZZ,  ZZZZ   ");
			System.out.println("       jZZZZZZZZEZZ   ZZZZZw 9ZZZZ 8ZZZZZZZW   WEZZZ8 EZZZEWEZZZZB  Z   ");
			System.out.println("                                            ,W,                  yZZy   ");
			System.out.println("     WyyyD9ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ     ");
			System.out.println("     58ZZZZZZE98zzzzzzz88EZZZZZZZZZZZZZZZZZZZZZZZEBj,     ,5zDj         ");
			System.out.println("           ZZZ9zz9ZZZZZZZEzj,                      WE99EEZZ             ");
			System.out.println("             ZZZZZy          w W,wwwWWWWjjjjjW   8ZZZZZZZZW             ");
			System.out.println("                  ZZZZZZZZZj   ,5WDwjWwWw,     EZZj ZZZZZ               ");
			System.out.println("                   ZZZZZ EZZZZ5      ,     WZZZZW Z  ZZZ                ");
			System.out.println("                    DZZ , j ZEZZZZZZZZZZZZZZZ  8   ZZZ,                 ");
			System.out.println("                      zZZZZ 8 W wZZWZ5Z8ZZZ w,  ZZZZ                    ");
			System.out.println("                         ZZZZZwB z  D W ,ZZZ ZZZZz                      ");
			System.out.println("                            5ZZZZZZZZZZZZZZZZZ,                         ");
		}
		else if(Session.nowTeam.getTeam_id() == 6){
			System.out.println("                jZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ                 ");
			System.out.println("                 ZZ     ZE88ZEZ9ZEZZZZZ988ZZ     ZZ                 ");
			System.out.println("                 ZZ     BZ9zZ   Z  Z  9Z8EZ      ZZ                 ");
			System.out.println("                 ZZ      EZ8Z W Z  B Z Z9Zj      ZZ                 ");
			System.out.println("                jZZ       ZZZZZZZZZZZZZZZZ       ZZ                 ");
			System.out.println("                                                                    ");
			System.out.println("   8ZzDDDD5DBzZ ZDyE DZDDz8z5jj  8jj5z98Z 885Dz8B5j  Zz5DDBDDD8ZW   ");
			System.out.println("    ZZZZZZZZZZZ DZZZ  ZZZZz8ZZZZ ZZZZ589Z  ZZZZZZZZZ DZZZZZZZZZD    ");
			System.out.println("         ZzZ    jZ8Z  Z8ZD       ZEEW      Z8Z   ZzZ yZEZ           ");
			System.out.println("         ZzZW   jZ8Z  Z8Z8 ZZZZZ ZEZZZZZZ  Z8Z9B5Z9Z  EZZZZB        ");
			System.out.println("         ZzZj   jZ8Z  Z8Zy  yEZZ ZEE       ZzZDBZZZ8      EZZZ      ");
			System.out.println("         ZzZj   jZ8Z  ZZZZZZZZZZ ZZZZZZZZ  ZZZ  5ZZw zZZZ  Z9ZD     ");
			System.out.println("         Z8Z5   8ZZZ  ,W,               W  D5Z   ZZZ ,ZZZ  Z9Z5     ");
			System.out.println("         ZZZ5              ,Wj5BzzBD5yjWw           j WZZZZZZZB     ");
			System.out.println("                 ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ         z9      ");
			System.out.println("                ,ZE998wZ E 8 9 B z 5BE D ZyDDWZ89EZ                 ");
			System.out.println("                 Z889y 5,DjzyZjEz9W8jEBz,BwwW Zz8zZ                 ");
			System.out.println("                 Zz88EZZZZEE9E999EEE999EEEEZZZEz8zZ                 ");
			System.out.println("                ,ZZZ988888888888888888888888888EZZZ                 ");
			System.out.println("                   jZZZE88888888888888888888EZZZ                    ");
			System.out.println("                 ZZ,  ,ZZZE8zEZZZZZZZZ9z8ZZZZ   BZZ                 ");
			System.out.println("                   ZZZ    ZZZZ       EZZZ9   yZZZ                   ");
			System.out.println("                     BZZZ    B        8   5ZZZ,                     ");
			System.out.println("                        ZZZZ,          yZZZZ                        ");
			System.out.println("                          8ZZZZD    8ZZZZj                          ");
			System.out.println("                            ,ZZZZZZZZZZ                             ");
			System.out.println("                               ZZZZZB                               ");
			System.out.println("                                 D,                                 ");
		}
		else if(Session.nowTeam.getTeam_id() == 7){
			System.out.println("           ,WW8ZZZZZZZZZ5,                                       ");
			System.out.println("                   ,9ZZZZZZZZ5                                   ");
			System.out.println("                       zZZZEZZZZB                                ");
			System.out.println("                         WZZZ89ZZZZ                              ");
			System.out.println("           wjyyyjjWW       jZZZZEEZZZ                            ");
			System.out.println("                ,WWwyZZZZBW    ZZZZZZZ,                          ");
			System.out.println("                       jZZZZZ9w   5ZZZZ5                         ");
			System.out.println("                    ,    DZ8Bz9ZZj   9ZZW wWWw,                  ");
			System.out.println("       wwwwww,         W   Z9BBB9ZZZ   BZ          ,,wwwwW       ");
			System.out.println("        WWw     ,jDEZZZZZ5  Z9BBj   ZZ   , ZZZByw     wwW        ");
			System.out.println("         Ww 8ZZZZZZZZ998ZZZ  EEzEy   ZZE    ZZZZZZZZZ ,W         ");
			System.out.println("          W  zZ98888888889ZZ  8Z8Z,55WE8ZZZw  yE88ZZ  w,         ");
			System.out.println("          ,,  ZZ88888888889ZZ, WZE9E99Z      ,zE89Z, ,w          ");
			System.out.println(" jWw,         yZ888888888888ZZZ  8Z8E85  ZZZZZZ98ZZ          wwW,");
			System.out.println("  Ww BW   ,WW  Z88888999988889ZZB  D8  WZZ9888888Zy       wD,wWw ");
			System.out.println("   W 8ZZZZZZZZZZ8889ZZZZ9888888EZZZ  5ZZZ88888888EZZZZZZZZZZ w,  ");
			System.out.println("    , yZE9Z   ZZZZZZZ  ZZZ9EZZZZ9EZZZZZZZZZZZZZZZZZZZZZZEZZ  ,   ");
			System.out.println("     , BZEZB 5Z9  8      D9E   ZZZZ ZZZ   Z   ZD       9ZZ  ,    ");
			System.out.println("        8ZZD DW  ZZZZ  ZZZ9EZ9  ZZ  WZz  ZZZ  ZZ9ZZ,  ZZZ        ");
			System.out.println("         ZZD   wZZEZZ  ZZ988EZD W  W z  ZZZZ  ZZZZ   ZZZ         ");
			System.out.println("        ZZZD z8  ZZZZ  ZZZ988ZZ   ZZ   ZZ9EZ  ZZZ  ZZZZZZw       ");
			System.out.println("       ZZ9Z   ZZ   EZD   Z9888ZZ EZZZ ZZ98EZ  Zz       5ZZ       ");
			System.out.println("    w,ZZZZZZZZZZZZZZEZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ5,,   ");
			System.out.println("   yWWw        DE9998ZWy5Z Z Z Z,Z Z y Z y Z99999Z         jwyw  ");
			System.out.println("               ZE8888Zw  Z Z ZZ w zy Z Z Z 998889Z               ");
			System.out.println("            W  ZE9888EEzZZZ8ZZZZZZZZEBZZBZzZ98899Z5 w,           ");
			System.out.println("           wW yZZZZZZ9EEE9EZZZZZZZZZZZZ99E99EZZZZZE wj           ");
			System.out.println("           ww,    w8ZZZZEZZZw        zZZEZZZZEy    ,ww           ");
			System.out.println("                      jZZZ  W  ,,,  ww ZZZD                      ");
			System.out.println("                           w5y ,,, W5y                           ");
			System.out.println("                         ,w Dy  ,, jDww,                         ");
			System.out.println("                            yZy ,,,ZB                            ");
			System.out.println("                              wwWWw                              ");
		}
		else if(Session.nowTeam.getTeam_id() == 8){
			System.out.println("                     EZZ                     ");
			System.out.println("               DZZZZZZ9ZZZZZZz               ");
			System.out.println("            ZZZZZ9zzzzzzzz89ZZZZZ            ");
			System.out.println("          ZZZ8z8889EEEEEEZZz98z8ZZZw         ");
			System.out.println("        ZZZ88888EZZE9y58Z8  Z88888EZZ        ");
			System.out.println("       ZZ88888889    ZZww ZZZ8888888ZZ       ");
			System.out.println("      ZZ88888888ZzZZzZz   Z8888888888ZZ      ");
			System.out.println("     8Z8888888zzz895    DZ9zzzz8888888ZZ     ");
			System.out.println("     Z8zzzzBzz9EZZZZZZZZZZZZZE9zzBzzzz8Z     ");
			System.out.println("    ,ZBz9ZZZZE98zzzzzzzzBBzzz89EZZZZ9zDZ5    ");
			System.out.println("    ZZZZ9zBBzzzz8888888888888zzzzBBz9ZZZZ    ");
			System.out.println("    ZZ8Bz8888zzz8EZZZZZZZZZE98zz8888zB8ZZw   ");
			System.out.println("   E, ZZ8z8EZZZZZZZZE999EZZZZZZZZE8zzEZj Z   ");
			System.out.println("   Z   ZZZZz                     BZZZZw  Z   ");
			System.out.println("   Z       ZZZ      5ZZZB      ZZZj      Z   ");
			System.out.println("   Z      DZZZ    9Z,    ZZ    ZZZ9      Z   ");
			System.out.println("   Z              Z9yw yDzZ              Z   ");
			System.out.println(" ZZZZZZZj         wZZZZZZZj         w9ZZZZZZ ");
			System.out.println("Z ZBZ8z8ZZZZZy        ,        jEZZZZ889E9Z Z");
			System.out.println("Z  ZZz9EE99zzZZZZZ5       jZZZZZ889EEE98ZZ  Z");
			System.out.println(" 59DZ8888889EEE9zzEZZZZZZZZz89EEE988888zZ98D ");
			System.out.println("    ZZ88888888EZZZZZZ9Z8ZZZZZE998888888ZZ    ");
			System.out.println("     ZZ8888889Bj wD   ZzDD 8D859888888EZ     ");
			System.out.println("      ZZ888889zD ZD ZZDZ,WZ B D988888EZW     ");
			System.out.println("       ZZ8zzzz8Zjwjy5   j5D5B89zzzz8ZZ       ");
			System.out.println("        ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ        ");
			System.out.println("          DB                     j9          ");
			System.out.println("            BZZZ             8ZZZ            ");
			System.out.println("                DZZZZZZZZZZZE                ");
			System.out.println("               9ZE9       Z8ZZ               ");
		}
		else if(Session.nowTeam.getTeam_id() == 9){
			System.out.println("                                                                   ");
			System.out.println("                         ,w  ,yww5DjWywW,w,                        ");
			System.out.println("                      w ww ZEwZy ZBw Z Z y5,ww,                    ");
			System.out.println("                     jWBZy 89Z5Z 8ZBjEyE ZZBy,,w,                  ");
			System.out.println("                 ,WyEB  ZZZW9BWzWBzWj99,Z,Z9 99ZWyw                ");
			System.out.println("               ,W5WyZzZ Z Ww,           Ww8 Zj8y,jyy,              ");
			System.out.println("               yjWw, 9EwW                 ,,5Ez wwwWy,,            ");
			System.out.println("            ,,yjWWWWw,    jW,j ,, w  w jwj     ,     W,  zZZZZZZB  ");
			System.out.println("             yjWWWWw       wwWwWw w W ,j      j zEZZ9ww,ZZZ5  BZZE ");
			System.out.println("            jyWWWWWwWZZZZy               8ZZZZZEDZ8Z8,,DZ89D  EZ9B ");
			System.out.println("         wBZjjWWWjjjZZZZB,    yZZZZZZZj  ,BE9Z   ZzZw,,zZz8Z       ");
			System.out.println("      8ZZZZZ ,WWWy      j   BZZZ   E8Zz  WZ8Ez  E98Z  ,wZ9zEZ,     ");
			System.out.println("     w 98zZDwwWWjW wWD9Zw  9Z9Zy  zE8Zw  BZzZj  Z8Ey,w, jZ9zEZy    ");
			System.out.println("      wZz8Z ,wWWj jZZZZD  yZ89Z   Z8E8   Z89Z  BEzZ  ,w,  ZEz9Zy   ");
			System.out.println("     ,DZzE8,wWWWy  988Z,  zEzZj  zZzZj  5ZzZj  Z98Zw8j     9Ez9Zw  ");
			System.out.println("     ,Z9zZw,wWWWj WZzE8  WZ88Z,  Z9ZZ,  ZZZZj BZZZZ5  W8ZB  ZzzZz  ");
			System.out.println("     WZz8Z ,WWWWW,BE8Zw  5Zz9j  yZZZj  BZZzj          ZZZZ  988ZB  ");
			System.out.println("     EEzE5 wWWww, Z88z   DZZZZEZZZB            wBZZZZEZj    ZEZEw  ");
			System.out.println("    wZ8zZ  ,,  jwWZEZB5Zj yzE8BW        WzZZZZZZZDj   WZ959ZZZz    ");
			System.out.println("    DZzz8   ,yZZw BEZZZj          yEZZZZZZZzj      ,ww ,WzBy       ");
			System.out.println("    E989ZEZZZZZW ,          y8ZZZZZZZZzw        W,y  wWy           ");
			System.out.println(" ,DEZZZZZzy  ,  ,,     BZEZZZZZZZZBw     W,Ww wyw8ZEEwy ,          ");
			System.out.println(" 9ZZBw            Wz9ZZZZEZZZZ9j            ,ww zDzDWjww           ");
			System.out.println("jW           yz8ZZZE988EZZ8W               yWyB9 8B,j ,            ");
			System.out.println("       wBZZZZZZ98zz89ZZ8W             ,Wjyjw ZwB8ywW,              ");
			System.out.println("    yZzZZEEZZZZZZEZZEj   , zD jwWwWwwW D 8,Z  BDwW,                ");
			System.out.println("               W9Z8w wWjWwyZZWZ,Z5ZyZZ,ZZ98z9yjw,                  ");
			System.out.println("                      wWyyyBj895EZZy58DB W,Ww,                     ");
			System.out.println("                         ,,w,wwWw,,wWWwwwW,                        ");
			
		}
		else if(Session.nowTeam.getTeam_id() == 10){
			System.out.println("               DZZEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEZZ5               ");
			System.out.println("               wZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ                ");
			System.out.println("                ZZEEEEEEEEEEEEZyy9ZBBzyEEEEEEEEEEEEEZZ                ");
			System.out.println("                ZZEEEEEEEEEEEEZZ85Zz55ZZZEEEEEEEEEEEZZ                ");
			System.out.println("                ZZEEEEEEEEEEEEZyBy8D89WzEEEEEEEEEEEEZE                ");
			System.out.println("   zZ9ZZZ88ZZ9BZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ89 zZZZZZE9Z,  ");
			System.out.println("    ZZ,  ZZ  ZZ  Z  wZ   Z  zZ   Z     ,ZZ     ZZZZ     ZZZwwWj jZZ   ");
			System.out.println("     ZZ  ZZ  ZZ  Z  WZw  Z  ZZ   Z  ZZ  Wz  Z8  Zw  Zz  Z       Z     ");
			System.out.println("     ,Z  ZZ  ZZ  Z  DZ5  Z  ZZ  WZ  ZZZZZZ  ZZ  Zw  ZZ  Z  9Zy  Z     ");
			System.out.println("     wZ  ZZ  ZZ  Z  zZD  Z  ZZ  WZ     8Z8   ,  ZW  ZZ  Z  ZZZZZZ     ");
			System.out.println("     wZ  ZZ  ZZ  Z9     ZZ  ZZ  wZ  ZZZZZE  ZD  ZW  ZZ  Z       Z     ");
			System.out.println("     wZ  ZZ  ZZ  ZZZ, WZZZ  yZ   Z  ZZ  8Z  ZZ  ZW  ZZ  ZZZZZj  Z     ");
			System.out.println("     wZ  ZZ  ZZ  ZZZ   ZZZZj   EZZ      Z   ZZ  Zw  ZZ  Z  ZZ9  Z     ");
			System.out.println("     WZ  ZZ  ,   ZZZ   ZEZZZZ9ZZZZ9ZZZZZ85BwZZ  Z   ZB  Z  wZB  Z     ");
			System.out.println("     WZy    ,9ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZz     EZ     ");
			System.out.println("       ZZZZZZB   8ZZZEEEZ B j        Z j WW,,ZEEEZZZD    zZZZZZ9      ");
			System.out.println("                   ZZZZEZ58Z9ZZZZZZZZZZZ8Ez8BZEZZZZ                   ");
			System.out.println("                    wZZZZZZZZZZZZZZZZZZZZZZZZZZZZw                    ");
			System.out.println("                      DZZZEZZZZZW    jZZZZEEZZZD                      ");
			System.out.println("                        9ZZZZW          BZZZZ9                        ");
			System.out.println("                         ,ZZ  B,      ,B  ZZ                          ");
			System.out.println("                           ZZ  y      5  Z9                           ");
			System.out.println("                            jZZy      yZZW                            ");
			System.out.println("                              DZ8    ZZ5                              ");
			System.out.println("                                ZZ  ZZ                                ");
			System.out.println("                                 ,ZZ                                  ");
		}
	}
	
	public void mainrogo(){
		
		                                                                                                                                              
			System.out.println("		                   W5DDD555DDzD                                                                                                               ");
			System.out.println("		                wD5yjWWWWWWWj  ,WWW,                                                                                                          ");
			System.out.println("		              ,DyjWWWWWWWWWWjjyyyyy5DDW                                                                                                       ");
			System.out.println("		         WZ  W5jWWwww,,,,,,,,wwwWWWWWjyDy                                                                                                     ");
			System.out.println("		       ZZZZ w5WwwWjj5Bz899EE9zzDjWwwWWWWyDW                                                                                                   ");
			System.out.println("		     ZZZZZ  5Wjy5DB8B5w        ,y88z5jjWWj5y                                                                                                  ");
			System.out.println("		    ZZ99Z  yyjyDz5,                  WDDyjWyy                yW      W               ,5 5W                                  ,j55BzBD5jw       ");
			System.out.println("		   ZZ88EZ  By5Bj            ZZZZZZZ5    wDyj5,                EZZZZZZZ        ,ZZZZZZZj  ZZZZZZZZZZZZZZZZZZZZZZ         yZZZZZZEEEEEZZZZZZ    ");
			System.out.println("		  BZ888Zz ,B55              ZEBB   ZZZz   ,D55                 ZBDDDZ5       DZ8BB8ZE     EBDDDBBzzzzzzzBBDDDBEZ      5ZZ8BBBzz8888zBBDDB9ZZ  ");
			System.out.println("		  ZZ888ZW Wzy ,ZZZZZZZ,      ZZZ    ZZZZZ   j8                 ZBDDBZ       ZZzDBEZw      ZBDDBEEZZZZZZZZ9BDDDBZ     ZZzBB8ZZZZEEEEZZZ9BDDBZE ");
			System.out.println("		  ZE888Zw j8      wZZZZZZ     8ZZ    ZZ8ZZZ  W                 ZDDDBZ     jZ9BB8ZE       WZDDDzZ         ZBDDDzZ    EZBDD8Zw          E8DDDzZ ");
			System.out.println("		  ZZ888Z5 jW         ZZy        Z9    Zzz8ZZ5                 yZDDD8Z    ZZzBBEZj        99DDD9B         ZBDDBE8   wZBDDBZ             EDDDDZ ");
			System.out.println("		j  Z988ZZ             9ZZZW    9ZZZ5  ZzBBz9Z9                E8DDDEy  yZEBB8ZE          ZzDDD9         5EBDB9Z    Z8DDD8Z             ZDDDDZ ");
			System.out.println("		wZjD9889Z              ZZZZw     ZZZW Z8zzzz9ZD               ZzDDDZ  ZZzBDz8            ZDDDD8ZZZZZZZZZEzDB9Z     ZBDDBZW            WZDDDBZ ");
			System.out.println("		 ZZE9888ZZ              ZzZZ       Z  Z8zzzzz9Z               ZBDDBZ ZZzDDDz,           wZDDDBBBBBBBBBBBDDDBy     DEDDDBZ             89DDDBZ ");
			System.out.println("		 Z9888889Z,             Zz8Z          ZzzzzzzzZ              jZDDDzZ  ZZzDDzZZ          89DDD8ZZZZZZZZZZ8DDB9Z    Z8DDDzZ             ZzDDD8Z ");
			System.out.println("		 ZZ999999ZZ            ,Zz8Z         EZzzzzzzzZ              99DDDEB   8ZzDDBEZ         ZzDDDZ          ZDDDBE9   ZBDDD8E             ZBDDBZj ");
			System.out.println("		 yZZ998888ZZ           ZZz9Z        wZ8zzzzzzzZ              ZzDDBZ,    8ZBDDBEZ        ZBDDBZ          ZDDDD9E   ZBDDD8z            B9DDD8Z  ");
			System.out.println("		  ZZEE99889ZZ,        9Z88Z8       ,Z9BBBBBBBEZ              ZBDDBZ      ZEBDDBEZ      ,ZDDDBZ          ZDDDBZD   Z8DDDzZ           jZzDDzZW  ");
			System.out.println("		   BZE9EEE99ZZZ     ,ZZ99ZZ       5Z9zBBBBBB9Z              wZDDDzZ       Z8BDDBZD     BEDDDBB        WZ8DDDzZ    5ZBDDB9ZW       jZZzDB8Zj   ");
			System.out.println("		     ZZE8z999ZZZZ zZZZ9ZZZ       ZZ8BBBBBBB8Zy              89DDD9z        ZzDDDzZ     ZzDDDB9ZZZZZZZZEzBDB8ZB     ZZzDDB8EZZZZZZZE8BDzEZj    ");
			System.out.println("		      ,ZZZZE8zBzZZZZZZZZ       ZZEzBBBzzzz9ZD               Z9zzzZj        5Z8zz8EZ    Z8zzzzzzzzzzzzz889ZZZ5       8ZZ9zBBDDDDDBBz8EZZB      ");
			System.out.println("		          5ZZZZZZZZZ8       zZZ9zBBBBzZjZZZ                 ZZZZZZ          ZZZZZZZZ  jZZZZZZZZZZZZZZZZZ9D            WEZZZZZZZZZZZZD         ");
			System.out.println("		                        DZZZZ8BBBBBB8ZD ZE                                                                                                    ");
			System.out.println("		           ZZZZZZZZZZZZZZ9zBBBBBBBB9Zz  ,                                                                                                     ");
			System.out.println("		             9ZZZ98zBBBBBBBBBBBB8ZZZ                                                                                                          ");
			System.out.println("		               WZZZZZ9888889EZZZZB                                                                                                            ");
			System.out.println("		                   wB8ZZZZZZ85                                                                                                                ");
		                                                                                                                                              
		
		
		
		
		
		
		
		
		
		
	}
	
	
	public void noneUserTicketCheck(){
		
		System.out.println("비회원 아이디를 입력해주세요");
		System.out.print("입력 >> ");
		String noneUserId = ScanUtil.nextLine();
		
		HashMap<String, String> param = new HashMap<>();
		param.put("ID", noneUserId);
		
		UserVO user = userDao.selectUser(param);
		
		if(user == null){
			System.out.println("로그인 실패");
		}else{
			Session.loginUser = user;
			System.out.println("로그인 성공!!");
			
		}
		showUserInfo();
	}
	
	public void noneUserTicketCancel(){
		System.out.println("비회원 아이디를 입력해주세요");
		System.out.print("입력 >> ");
		String noneUserId = ScanUtil.nextLine();
		
		HashMap<String, String> param = new HashMap<>();
		param.put("ID", noneUserId);
		
		UserVO user = userDao.selectUser(param);
		
		if(user == null){
			System.out.println("로그인 실패");
		}else{
			Session.loginUser = user;
			System.out.println("로그인 성공!!");
		}
		
		cancelReservation();
	}
	
	
	
}

















