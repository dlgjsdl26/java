package dao;

import java.util.ArrayList;

import vo.TicketVO;
import vo.UserVO;
import data.DataBase;
import data.Session;

public class TicketDao {

	private static TicketDao instance;
	
	private TicketDao(){}
	
	public static TicketDao getInstance(){
		if (instance == null){
			instance = new TicketDao();
		}
		return instance;
	}
	
	DataBase Database = DataBase.getInstance();
	
	
	public void removeTicket(TicketVO ticket){
		Database.tb_ticket.remove(ticket);
	}
	
	public void insertTicket(TicketVO ticket){ //티켓 입력
		Database.tb_ticket.add(ticket);
	}
	/**
	 * 유저 아이디 = 티켓 아이디
	 * @param (UserVO)user 유저 아이디
	 * @return (ArrayList<TicketVO>)rtnTicket 티켓 확인
	 */
	public ArrayList<TicketVO> ticketCheck(UserVO user){
		ArrayList<TicketVO> rtnTicket = null;
		for (int i = 0; i < Database.tb_ticket.size(); i++) { 
			TicketVO ticket = Database.tb_ticket.get(i);
			
			if(ticket.getUserId().equals(user.getId())){
				rtnTicket.add(ticket);

			}
		}	
		return rtnTicket;
	}
	
	
	
	public ArrayList<TicketVO> selectTicketList() { //티켓 리스트 출력
		return Database.tb_ticket;
	}
	
	public boolean checkOverTicketNum(String num){ //티켓번호 중복확인 메소드
		boolean checking = false;
		
		if(Database.tb_ticket.isEmpty()){
			checking = true;
		}
		else {
			for (int i = 0; i < Database.tb_ticket.size(); i++) {
				
				if(num.equals(Database.tb_ticket.get(i).getTicketNo())){
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
	
	/**
	 * 파라미터로 받은 좌석번호가 티켓테이블에 없으면  true
	 * @param seat
	 * @return
	 */
	public boolean checkiTicket(String seatNo, String seatName,String date){
		
		boolean tof = true;
		
		for (int i = 0; i < Database.tb_ticket.size(); i++) {
			
			if(Database.tb_ticket.get(i).getStadiumName().equals(Session.nowStadium.getStadumName()) 
					&& Database.tb_ticket.get(i).getSchedul().equals(date)
					&& Database.tb_ticket.get(i).getSeatName().equals(seatName) 
					&& Database.tb_ticket.get(i).getSeatNo().contains(seatNo)){
				tof = false;
			}
			
		}
		
		return tof;
	}
	
	
	/**
	 * 세션에 저장된 유저가 가지고있는 티켓을 리턴
	 * @return
	 */
	public ArrayList<TicketVO> showMyTicket(){
		
		ArrayList<TicketVO> ticket = new ArrayList<>();
		
		for (int i = 0; i < Database.tb_ticket.size(); i++) {
			if(Session.loginUser.getId() == Database.tb_ticket.get(i).getUserId()){
				ticket.add(Database.tb_ticket.get(i));
			}
		}
		
		
		return ticket;
		
	}
	
}// 클래스 끝













