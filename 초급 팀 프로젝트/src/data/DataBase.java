package data;

import java.util.ArrayList;

import vo.*;

public class DataBase {

	private static DataBase instance;

	private DataBase() {
	}

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	
	public ArrayList<UserVO> tb_user = new ArrayList<>();

	{
		UserVO user = new UserVO();
		user.setId("admin");
		user.setPassword("admin");
		user.setName("관리자");
		tb_user.add(user);
		
		UserVO user2 = new UserVO();
		user2.setId("leehw6269");
		user2.setPassword("@Gmldnr123");
		user2.setName("이희욱");
		user2.setCash(20000);
		tb_user.add(user2);
		
		
	}

	public ArrayList<TeamVO> tb_team = new ArrayList<>();

	{
		TeamVO NC = new TeamVO();
		NC.setTeam_id(1);
		NC.setLoc("경남 창원시");
		NC.setName("NC다이노스");
		NC.setStadiumName("창원NC파크");
		NC.setTeamInfo("이동욱 감독이 이끌고 있으며 우승0회입니다.");
		tb_team.add(NC);

		TeamVO LG = new TeamVO();
		LG.setTeam_id(2);
		LG.setLoc("서울 특별시");
		LG.setName("LG트윈스");
		LG.setStadiumName("잠실야구장");
		LG.setTeamInfo("류중일 감독이 이끌고 있으며 우승2회입니다.");
		tb_team.add(LG);

		TeamVO KIWOOM = new TeamVO();
		KIWOOM.setTeam_id(3);
		KIWOOM.setLoc("서울특별시");
		KIWOOM.setName("키움히어로즈");
		KIWOOM.setStadiumName("고척스카이돔");
		KIWOOM.setTeamInfo("손혁 감독이 이끌고 있으며 우승0회입니다.");
		tb_team.add(KIWOOM);

		TeamVO DOOSAN = new TeamVO();
		DOOSAN.setTeam_id(4);
		DOOSAN.setLoc("서울특별시");
		DOOSAN.setName("두산베어스");
		DOOSAN.setStadiumName("잠실야구장");
		DOOSAN.setTeamInfo("김태형 감독이 이끌고 있으며 우승6회입니다.");
		tb_team.add(DOOSAN);

		TeamVO LOTTE = new TeamVO();
		LOTTE.setTeam_id(5);
		LOTTE.setLoc("부산광역시");
		LOTTE.setName("롯데자이언츠");
		LOTTE.setStadiumName("사직야구장");
		LOTTE.setTeamInfo("허문회 감독이 이끌고 있으며 우승2회입니다.");
		tb_team.add(LOTTE);

		TeamVO KIA = new TeamVO();
		KIA.setTeam_id(6);
		KIA.setLoc("광주광역시");
		KIA.setName("KIA타이거즈");
		KIA.setStadiumName("기아챔피언스필드");
		KIA.setTeamInfo("맷 윌리엄스 감독이 이끌고 있으며 우승11회입니다.");
		tb_team.add(KIA);

		TeamVO KT = new TeamVO();
		KT.setTeam_id(7);
		KT.setLoc("경기도 수원시");
		KT.setName("KT위즈");
		KT.setStadiumName("수원KT위즈파크");
		KT.setTeamInfo("이강철 감독이 이끌고 있으며 우승0회입니다.");
		tb_team.add(KT);

		TeamVO HANWHA = new TeamVO();
		HANWHA.setTeam_id(8);
		HANWHA.setLoc("대전광역시");
		HANWHA.setName("한화이글스");
		HANWHA.setStadiumName("한화생명이글스파크");
		HANWHA.setTeamInfo("한용덕 감독이 이끌고 있으며 우승1회입니다.");
		tb_team.add(HANWHA);

		TeamVO SAMSUNG = new TeamVO();
		SAMSUNG.setTeam_id(9);
		SAMSUNG.setLoc("대구광역시");
		SAMSUNG.setName("삼성라이온즈");
		SAMSUNG.setStadiumName("대구삼성라이온즈파크");
		SAMSUNG.setTeamInfo("허삼영 감독이 이끌고 있으며 우승8회입니다.");
		tb_team.add(SAMSUNG);

		TeamVO SK = new TeamVO();
		SK.setTeam_id(10);
		SK.setLoc("인천광역시");
		SK.setName("SK와이번즈");
		SK.setStadiumName("인천SK행복드림구장");
		SK.setTeamInfo("엄경엽 감독이 이끌고 있으며 우승4회입니다.");
		tb_team.add(SK);

	}

	public ArrayList<ScheduleVO> tb_schedule = new ArrayList<>();
	{
		int key = 1;
		
		ScheduleVO sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-26-18:30");
		sch.setHomeTeamName("두산베어스");
		sch.setAwayTeamName("한화이글스");
		sch.setStadium_id(2);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-26-18:30");
		sch.setHomeTeamName("KIA타이거즈");
		sch.setAwayTeamName("LG트윈스");
		sch.setStadium_id(7);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-26-18:30");
		sch.setHomeTeamName("삼성라이온즈");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(9);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-26-18:30");
		sch.setHomeTeamName("KT위즈");
		sch.setAwayTeamName("NC다이노스");
		sch.setStadium_id(8);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-26-18:30");
		sch.setHomeTeamName("키움히어로즈");
		sch.setAwayTeamName("SK와이번즈");
		sch.setStadium_id(5);
		key++;
		tb_schedule.add(sch);
		
		//
		 sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-27-18:30");
		sch.setHomeTeamName("두산베어스");
		sch.setAwayTeamName("한화이글스");
		sch.setStadium_id(2);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-27-18:30");
		sch.setHomeTeamName("KIA타이거즈");
		sch.setAwayTeamName("LG트윈스");
		sch.setStadium_id(7);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-27-18:30");
		sch.setHomeTeamName("삼성라이온즈");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(9);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-27-18:30");
		sch.setHomeTeamName("KT위즈");
		sch.setAwayTeamName("NC다이노스");
		sch.setStadium_id(8);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-27-18:30");
		sch.setHomeTeamName("키움히어로즈");
		sch.setAwayTeamName("SK와이번즈");
		sch.setStadium_id(5);
		key++;
		tb_schedule.add(sch);
		
		//
		 sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-28-18:30");
		sch.setHomeTeamName("두산베어스");
		sch.setAwayTeamName("한화이글스");
		sch.setStadium_id(2);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-28-18:30");
		sch.setHomeTeamName("KIA타이거즈");
		sch.setAwayTeamName("LG트윈스");
		sch.setStadium_id(7);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-28-18:30");
		sch.setHomeTeamName("삼성라이온즈");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(9);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-28-18:30");
		sch.setHomeTeamName("KT위즈");
		sch.setAwayTeamName("NC다이노스");
		sch.setStadium_id(8);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-28-18:30");
		sch.setHomeTeamName("키움히어로즈");
		sch.setAwayTeamName("SK와이번즈");
		sch.setStadium_id(5);
		key++;
		tb_schedule.add(sch);

		//
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-29-18:30");
		sch.setHomeTeamName("두산베어스");
		sch.setAwayTeamName("KIA타이거즈");
		sch.setStadium_id(2);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-29-18:30");
		sch.setHomeTeamName("삼성라이온즈");
		sch.setAwayTeamName("LG트윈스");
		sch.setStadium_id(9);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-29-18:30");
		sch.setHomeTeamName("NC다이노스");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(3);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-29-18:30");
		sch.setHomeTeamName("KT위즈");
		sch.setAwayTeamName("SK와이번즈");
		sch.setStadium_id(8);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-29-18:30");
		sch.setHomeTeamName("키움히어로즈");
		sch.setAwayTeamName("한화이글스");
		sch.setStadium_id(5);
		key++;
		tb_schedule.add(sch);
		
		//
		 sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-30-14:00");
		sch.setHomeTeamName("두산베어스");
		sch.setAwayTeamName("KIA타이거즈");
		sch.setStadium_id(2);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-30-14:00");
		sch.setHomeTeamName("삼성라이온즈");
		sch.setAwayTeamName("LG트윈스");
		sch.setStadium_id(9);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-30-14:00");
		sch.setHomeTeamName("NC다이노스");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(3);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-30-14:00");
		sch.setHomeTeamName("KT위즈");
		sch.setAwayTeamName("SK와이번즈");
		sch.setStadium_id(8);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-30-14:00");
		sch.setHomeTeamName("키움히어로즈");
		sch.setAwayTeamName("한화이글스");
		sch.setStadium_id(5);
		key++;
		tb_schedule.add(sch);
		
		//
		 sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-31-14:00");
		sch.setHomeTeamName("두산베어스");
		sch.setAwayTeamName("KIA타이거즈");
		sch.setStadium_id(2);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-31-14:00");
		sch.setHomeTeamName("삼성라이온즈");
		sch.setAwayTeamName("LG트윈스");
		sch.setStadium_id(9);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-31-14:00");
		sch.setHomeTeamName("NC다이노스");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(3);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-31-14:00");
		sch.setHomeTeamName("KT위즈");
		sch.setAwayTeamName("SK와이번즈");
		sch.setStadium_id(8);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-05-31-14:00");
		sch.setHomeTeamName("키움히어로즈");
		sch.setAwayTeamName("한화이글스");
		sch.setStadium_id(5);
		key++;
		tb_schedule.add(sch);
		
		//
		 sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-02-18:30");
		sch.setHomeTeamName("NC다이노스");
		sch.setAwayTeamName("두산베어스");
		sch.setStadium_id(3);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-02-18:30");
		sch.setHomeTeamName("한화이글스");
		sch.setAwayTeamName("KT위즈");
		sch.setStadium_id(1);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-02-18:30");
		sch.setHomeTeamName("LG트윈스");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(4);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-02-18:30");
		sch.setHomeTeamName("KIA타이거즈");
		sch.setAwayTeamName("삼성라이온즈");
		sch.setStadium_id(7);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-02-18:30");
		sch.setHomeTeamName("SK와이번즈");
		sch.setAwayTeamName("키움히어로즈");
		sch.setStadium_id(10);
		key++;
		tb_schedule.add(sch);
		
		
		 sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-03-18:30");
		sch.setHomeTeamName("NC다이노스");
		sch.setAwayTeamName("두산베어스");
		sch.setStadium_id(3);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-03-18:30");
		sch.setHomeTeamName("한화이글스");
		sch.setAwayTeamName("KT위즈");
		sch.setStadium_id(1);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-03-18:30");
		sch.setHomeTeamName("LG트윈스");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(4);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-03-18:30");
		sch.setHomeTeamName("KIA타이거즈");
		sch.setAwayTeamName("삼성라이온즈");
		sch.setStadium_id(7);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-03-18:30");
		sch.setHomeTeamName("SK와이번즈");
		sch.setAwayTeamName("키움히어로즈");
		sch.setStadium_id(10);
		key++;
		tb_schedule.add(sch);
		
		 sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-04-18:30");
		sch.setHomeTeamName("NC다이노스");
		sch.setAwayTeamName("두산베어스");
		sch.setStadium_id(3);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-04-18:30");
		sch.setHomeTeamName("한화이글스");
		sch.setAwayTeamName("KT위즈");
		sch.setStadium_id(1);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-04-18:30");
		sch.setHomeTeamName("LG트윈스");
		sch.setAwayTeamName("롯데자이언츠");
		sch.setStadium_id(4);
		key++;
		tb_schedule.add(sch);
		
		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-04-18:30");
		sch.setHomeTeamName("KIA타이거즈");
		sch.setAwayTeamName("삼성라이온즈");
		sch.setStadium_id(7);
		key++;
		tb_schedule.add(sch);

		sch = new ScheduleVO();
		sch.setScheduls_id(key);
		sch.setDate("2020-06-04-18:30");
		sch.setHomeTeamName("SK와이번즈");
		sch.setAwayTeamName("키움히어로즈");
		sch.setStadium_id(10);
		key++;
		tb_schedule.add(sch);
		
		
	}

	public ArrayList<NoticeVO> tb_notice = new ArrayList<>();
	{
		NoticeVO NO1 = new NoticeVO();
		NO1.setNoticeNo(0001);
		NO1.setNoticeTitle("2020-06-06-18:30 경기 지연 안내");
		NO1.setNoticeContent("2020-06-06-18:30 키움히어로즈와 SK와이번스의 경기가 \r 우천으로 인해 2020-06-06-19:00 으로 지연됨을 알려드립니다.");
		NO1.setNoticeUser("관리자");
		NO1.setNoticeDate("2020-06-06-17:30");
		tb_notice.add(NO1);
	}
	public ArrayList<SeatVO> tb_seat = new ArrayList<>();
	{

		
		int key = 1;
		int cnt = 1;
		char seatY = 'a';
		int seatX = 0;
		int fk_key = 1;
		int price = 0;
		String a = "응원석"; //1
		String b = "1루지정석"; //2
		String c = "3루지정석"; //3
		String d = "익사이팅존"; //4
		String e = "중앙지정석"; //5
		String seatname = "";
		String st1 = "";
		
		do{
			// 한화생명 이글스파크의 좌석 fk = 1
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ D15
				seatname = a;
				seatX = 15;
				seatY = 'D';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ D15
				seatname = b;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ D15
				seatname = c;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C20
				seatname = d;
				seatX = 20;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ F10
				seatname = e;
				seatX = 10;
				seatY = 'F';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++; // 2
		cnt = 1;
		
		do{
			// 잠실야구장 의 좌석 (두산)
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ E20
				seatname = a;
				seatX = 20;
				seatY = 'E';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ E20
				seatname = b;
				seatX = 20;
				seatY = 'E';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ E20
				seatname = c;
				seatX = 20;
				seatY = 'E';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C25
				seatname = d;
				seatX = 25;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ G10
				seatname = e;
				seatX = 10;
				seatY = 'G';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++; // 3
		cnt = 1;
		
		do{
			// 창원 NC파크의 좌석
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ D15
				seatname = a;
				seatX = 15;
				seatY = 'D';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ D15
				seatname = b;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ D15
				seatname = c;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C20
				seatname = d;
				seatX = 20;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ F10
				seatname = e;
				seatX = 10;
				seatY = 'F';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++; //4
		cnt = 1;
		
		do{
			// 잠실야구장 의 좌석 (LG)
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ E20
				seatname = a;
				seatX = 20;
				seatY = 'E';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ E20
				seatname = b;
				seatX = 20;
				seatY = 'E';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ E20
				seatname = c;
				seatX = 20;
				seatY = 'E';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C25
				seatname = d;
				seatX = 25;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ G10
				seatname = e;
				seatX = 10;
				seatY = 'G';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++; //5
		cnt = 1;
		do{
			// 고척스카이돔 의 좌석
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ D15
				seatname = a;
				seatX = 15;
				seatY = 'D';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ D15
				seatname = b;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ D15
				seatname = c;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C20
				seatname = d;
				seatX = 20;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ F10
				seatname = e;
				seatX = 10;
				seatY = 'F';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++;//6
		cnt = 1;
		
		do{
			// 사직야구장의 좌석
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ D15
				seatname = a;
				seatX = 15;
				seatY = 'D';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ D15
				seatname = b;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ D15
				seatname = c;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C20
				seatname = d;
				seatX = 20;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ F10
				seatname = e;
				seatX = 10;
				seatY = 'F';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++; //7
		cnt = 1;
		
		do{
			// 기아챔피언스필드의 좌석
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ D15
				seatname = a;
				seatX = 15;
				seatY = 'D';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ D15
				seatname = b;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ D15
				seatname = c;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C20
				seatname = d;
				seatX = 20;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ F10
				seatname = e;
				seatX = 10;
				seatY = 'F';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++; // 8
		cnt = 1;
		
		do{
			// 수원KT의 좌석
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ D15
				seatname = a;
				seatX = 15;
				seatY = 'D';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ D15
				seatname = b;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ D15
				seatname = c;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C20
				seatname = d;
				seatX = 20;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ F10
				seatname = e;
				seatX = 10;
				seatY = 'F';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++; //9
		cnt = 1;
		
		do{
			// 대구삼성라이온즈파크 의 좌석
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ D15
				seatname = a;
				seatX = 15;
				seatY = 'D';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ D15
				seatname = b;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ D15
				seatname = c;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C20
				seatname = d;
				seatX = 20;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ F10
				seatname = e;
				seatX = 10;
				seatY = 'F';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
		fk_key++; //10
		cnt = 1;
		
		do{
			// 인천SK행복드림구장의 좌석
			st1 = "";
			
			
			if(cnt == 1){ // 응원석 A1 ~ D15
				seatname = a;
				seatX = 15;
				seatY = 'D';
				price = 15000;
			}
			else if(cnt == 2){ // 1루지정석  A1 ~ D15
				seatname = b;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 3){ // 3루지정석  A1 ~ D15
				seatname = c;
				seatX = 15;
				seatY = 'D';
				price = 12000;
			}
			else if(cnt == 4){ //익사이팅존 A1 ~ C20
				seatname = d;
				seatX = 20;
				seatY = 'C';
				price = 20000;
			}
			else if(cnt == 5){ // 중앙지정석 A1 ~ F10
				seatname = e;
				seatX = 10;
				seatY = 'F';
				price = 35000;
			}
			
			for (char ch = 'A'; ch <= seatY; ch++) {
				for (int i = 1; i <= seatX; i++) {
					st1 += ch + "" + i + ",";
				}
			}
			
			SeatVO seat1 = new SeatVO();

			seat1.setStadium_id(key);
			seat1.setSeatName(seatname);
			seat1.setSeatPrice(price);
			seat1.setSeatNum(st1);
			seat1.setStadium_id(fk_key);
			tb_seat.add(seat1);
			key++;
			cnt++;
			
			
			
		}while(cnt < 6);
		
//		System.out.println(tb_seat); // tb_seat 잘들어갔는지 확인
	}

	public ArrayList<BoardVO> tb_board = new ArrayList<>();
	{
		BoardVO BR1 = new BoardVO();
		BR1.setBoardNo(1);
		BR1.setBoardContent("취소만 안됬으면 좋겠네요");
		BR1.setBoardUser("leehw6269");
		BR1.setBoardDate("2020-06-06-17:35");
		BR1.setTeam_id(1);
		tb_board.add(BR1);

	}

	public ArrayList<CommentVO> tb_comment = new ArrayList<>();
	{
		CommentVO CM1 = new CommentVO();
		CM1.setCommentNo(0001);
		CM1.setCommentContent("와 경기 수듄;;;");
		CM1.setCommentUser("이희욱");
		CM1.setCommentDate("2020-05-26-21:35");
		CM1.setMatchResult_id(1);
		tb_comment.add(CM1);
		
		CommentVO CM2 = new CommentVO();
		CM2.setCommentNo(0002);
		CM2.setCommentContent("와 개못하네?");
		CM2.setCommentUser("이희욱");
		CM2.setCommentDate("2020-05-26-22:35");
		CM2.setMatchResult_id(2);
		tb_comment.add(CM2);
	}

	public ArrayList<MatchResultVO> tb_matchresult = new ArrayList<>();
	{
		MatchResultVO MR1 = new MatchResultVO();
		int key = 1;
		MR1.setMatchResult_id(key);
		MR1.setHomeTeamScore(2);
		MR1.setAwayTeamScore(6);
		MR1.setMatchResult("한화이글스 승리");
		MR1.setSchedule_id(1); // 2020-05-26-18:30 두산 한화 경기결과
		tb_matchresult.add(MR1);
		key++;
		
		MatchResultVO MR2 = new MatchResultVO();
		MR2.setMatchResult_id(key);
		MR2.setHomeTeamScore(2);
		MR2.setAwayTeamScore(1);
		MR2.setMatchResult("기아타이거즈 승리");
		MR2.setSchedule_id(2); //2020-05-26-18:30 기아 엘쥐 경기결과
		tb_matchresult.add(MR2);
		key++;
		
		MatchResultVO MR3 = new MatchResultVO();
		MR3.setMatchResult_id(key);
		MR3.setHomeTeamScore(5);
		MR3.setAwayTeamScore(3);
		MR3.setMatchResult("삼성라이온즈 승리");
		MR3.setSchedule_id(3); //2020-05-26-18:30 삼성 롯데경기결과
		tb_matchresult.add(MR3);
		key++;
		
		MatchResultVO MR4 = new MatchResultVO();
		MR4.setMatchResult_id(key);
		MR4.setHomeTeamScore(4);
		MR4.setAwayTeamScore(8);
		MR4.setMatchResult("NC다이노스 승리");
		MR4.setSchedule_id(4); //2020-05-26-18:30 kt nc 경기결과
		tb_matchresult.add(MR4);
		key++;
		
		MatchResultVO MR5 = new MatchResultVO();
		MR5.setMatchResult_id(key);
		MR5.setHomeTeamScore(4);
		MR5.setAwayTeamScore(13);
		MR5.setMatchResult("SK와이번즈 승리");
		MR5.setSchedule_id(5);//2020-05-26-18:30 SK 키움 경기결과
		tb_matchresult.add(MR5);
		key++;

		// ---------------
		
		MatchResultVO MR6 = new MatchResultVO();
		MR6.setMatchResult_id(key);
		MR6.setHomeTeamScore(2);
		MR6.setAwayTeamScore(6);
		MR6.setMatchResult("한화이글스 승리");
		MR6.setSchedule_id(6); // 2020-05-27-18:30 두산 한화 경기결과
		tb_matchresult.add(MR6);
		key++;
		
		MatchResultVO MR7 = new MatchResultVO();
		MR7.setMatchResult_id(key);
		MR7.setHomeTeamScore(4);
		MR7.setAwayTeamScore(1);
		MR7.setMatchResult("기아타이거즈 승리");
		MR7.setSchedule_id(7); //2020-05-27-18:30 기아 엘쥐 경기결과
		tb_matchresult.add(MR7);
		key++;
		
		MatchResultVO MR8 = new MatchResultVO();
		MR8.setMatchResult_id(key);
		MR8.setHomeTeamScore(7);
		MR8.setAwayTeamScore(8);
		MR8.setMatchResult("롯데자이언츠 승리");
		MR8.setSchedule_id(8); //2020-05-27-18:30 삼성 롯데경기결과
		tb_matchresult.add(MR8);
		key++;
		
		MatchResultVO MR9 = new MatchResultVO();
		MR9.setMatchResult_id(key);
		MR9.setHomeTeamScore(4);
		MR9.setAwayTeamScore(5);
		MR9.setMatchResult("NC다이노스 승리");
		MR9.setSchedule_id(9); //2020-05-27-18:30 kt nc 경기결과
		tb_matchresult.add(MR9);
		key++;
		
		MatchResultVO MR10 = new MatchResultVO();
		MR10.setMatchResult_id(key);
		MR10.setHomeTeamScore(3);
		MR10.setAwayTeamScore(1);
		MR10.setMatchResult("키움히어로즈 승리");
		MR10.setSchedule_id(10);//2020-05-27-18:30 SK 키움 경기결과
		tb_matchresult.add(MR10);
		key++;

		// ---------------
		
		MatchResultVO MR11 = new MatchResultVO();
		MR11.setMatchResult_id(key);
		MR11.setHomeTeamScore(0);
		MR11.setAwayTeamScore(12);
		MR11.setMatchResult("한화이글스 승리");
		MR11.setSchedule_id(11); // 2020-05-28-18:30 두산 한화 경기결과
		tb_matchresult.add(MR11);
		key++;
		
		MatchResultVO MR12 = new MatchResultVO();
		MR12.setMatchResult_id(key);
		MR12.setHomeTeamScore(7);
		MR12.setAwayTeamScore(6);
		MR12.setMatchResult("기아타이거즈 승리");
		MR12.setSchedule_id(12); //2020-05-28-18:30 기아 엘쥐 경기결과
		tb_matchresult.add(MR12);
		key++;
		
		MatchResultVO MR13 = new MatchResultVO();
		MR13.setMatchResult_id(key);
		MR13.setHomeTeamScore(2);
		MR13.setAwayTeamScore(0);
		MR13.setMatchResult("삼성라이온즈 승리");
		MR13.setSchedule_id(13); //2020-05-28-18:30 삼성 롯데경기결과
		tb_matchresult.add(MR13);
		key++;
		
		MatchResultVO MR14 = new MatchResultVO();
		MR14.setMatchResult_id(key);
		MR14.setHomeTeamScore(4);
		MR14.setAwayTeamScore(2);
		MR14.setMatchResult("KT위즈 승리");
		MR14.setSchedule_id(14); //2020-05-28-18:30 kt nc 경기결과
		tb_matchresult.add(MR14);
		key++;
		
		MatchResultVO MR15 = new MatchResultVO();
		MR15.setMatchResult_id(key);
		MR15.setHomeTeamScore(2);
		MR15.setAwayTeamScore(7);
		MR15.setMatchResult("SK와이번즈 승리");
		MR15.setSchedule_id(15);//2020-05-28-18:30 SK 키움 경기결과
		tb_matchresult.add(MR15);
		key++;

		// ---------------
		
		
		
	}

	public ArrayList<StadiumVO> tb_stadium = new ArrayList<>();
	{
		
		StadiumVO stadium = new StadiumVO();
		stadium.setStadium_id(1);
		stadium.setStadumName("한화생명이글스파크");
		stadium.setLoc("대전광역시 ...");
		stadium.setTeam_id(8);
		tb_stadium.add(stadium);
		
		StadiumVO stadium2 = new StadiumVO();
		stadium2.setStadium_id(2);
		stadium2.setStadumName("잠실야구장");
		stadium2.setLoc("서울특별시");
		stadium2.setTeam_id(4);
		tb_stadium.add(stadium2);
		
		StadiumVO stadium3 = new StadiumVO();
		stadium3.setStadium_id(3);
		stadium3.setStadumName("창원NC파크");
		stadium3.setLoc("창원");
		stadium3.setTeam_id(1);
		tb_stadium.add(stadium3);
		
		StadiumVO stadium4 = new StadiumVO();
		stadium4.setStadium_id(4);
		stadium4.setStadumName("잠실야구장");
		stadium4.setLoc("서울특별시");
		stadium4.setTeam_id(2);
		tb_stadium.add(stadium4);
		
		StadiumVO stadium5 = new StadiumVO();
		stadium5.setStadium_id(5);
		stadium5.setStadumName("고척스카이돔");
		stadium5.setLoc("서울 고척");
		stadium5.setTeam_id(3);
		tb_stadium.add(stadium5);
		
		StadiumVO stadium6 = new StadiumVO();
		stadium6.setStadium_id(6);
		stadium6.setStadumName("사직야구장");
		stadium6.setLoc("부산");
		stadium6.setTeam_id(5);
		tb_stadium.add(stadium6);
		
		StadiumVO stadium7 = new StadiumVO();
		stadium7.setStadium_id(7);
		stadium7.setStadumName("기아챔피언스필드");
		stadium7.setLoc("광주광역시");
		stadium7.setTeam_id(6);
		tb_stadium.add(stadium7);
		
		StadiumVO stadium8 = new StadiumVO();
		stadium8.setStadium_id(8);
		stadium8.setStadumName("수원KT위즈파크");
		stadium8.setLoc("수원");
		stadium8.setTeam_id(7);
		tb_stadium.add(stadium8);
		
		StadiumVO stadium9 = new StadiumVO();
		stadium9.setStadium_id(9);
		stadium9.setStadumName("대구삼성라이온즈파크");
		stadium9.setLoc("대구광역시");
		stadium9.setTeam_id(9);
		tb_stadium.add(stadium9);
		
		StadiumVO stadium10 = new StadiumVO();
		stadium10.setStadium_id(10);
		stadium10.setStadumName("인천SK행복드림구장");
		stadium10.setLoc("인천");
		stadium10.setTeam_id(10);
		tb_stadium.add(stadium10);
		
	}
	
	public ArrayList<TicketVO> tb_ticket = new ArrayList<>();
	{
		TicketVO ticket = new TicketVO();
		ticket.setTicketNo("02401");
		ticket.setSchedul("2020-06-03-18:30");
		ticket.setUserId("leehw6269");
		ticket.setBuyDate("2020-05-28-22:15");
		ticket.setStadiumName("한화생명이글스파크");
		ticket.setSeatName("응원석");
		ticket.setSeatNo("B7");
		ticket.setStadium_id(1);
		tb_ticket.add(ticket);
		
	}

}
















