package dao;

import java.util.ArrayList;

import vo.SeatVO;
import vo.TicketVO;
import data.DataBase;
import data.Session;

public class SeatDao {

	private static SeatDao instance;
	
	private SeatDao(){}
	
	public static SeatDao getInstance(){
		if (instance == null){
			instance = new SeatDao();
		}
		return instance;
	}
	
	DataBase Database = DataBase.getInstance();
	
	public void insertSeat(SeatVO seat){ //좌석 입력
		Database.tb_seat.add(seat);
	}
	
	
	public ArrayList<SeatVO> selectSeatList() {
		return Database.tb_seat;
	}
	
	/**
	 * 세션 경기장 아이디랑 좌석이랑 비교 해서 출력
	 */
	public ArrayList<SeatVO> rtnSeat(){
		
		ArrayList<SeatVO> seat = new ArrayList<>();
		for (int i = 0; i < Database.tb_seat.size(); i++) {
			if(Session.nowStadium.getStadium_id() == Database.tb_seat.get(i).getStadium_id()){
				
				seat.add(Database.tb_seat.get(i));
			}
		}
		
		
		return seat;
		
	}
	
	/**
	 * 세션에 저장된 경기장 아이디랑 같은 tb_seat중에 입력받은 좌석이름(seatNum)이 포함되어 있으면 true
	 * @param seatNum
	 * @return tof
	 */
	public boolean existenceSeat(String seatNum){
		boolean tof = false;
		
		for (int i = 0; i < Database.tb_seat.size(); i++) {
			
			if(Session.nowStadium.getStadium_id() == Database.tb_seat.get(i).getStadium_id() 
					&& Database.tb_seat.get(i).getSeatNum().contains(seatNum)){
				tof = true;
			}
			
		}
		
		
		return tof;
	}
	
	
	public int priceTicketSeat(TicketVO ticket){
		
		int price = 0;;
		
		for (int i = 0; i < Database.tb_seat.size(); i++) {
			if(ticket.getStadium_id() == Database.tb_seat.get(i).getStadium_id() && 
					ticket.getSeatName().equals(Database.tb_seat.get(i).getSeatName())){
	
				price = Database.tb_seat.get(i).getSeatPrice();
				break;
			}
				
		}

		return price;
	}
	
	
	public int lastSeatNum(){
		return Database.tb_seat.get(Database.tb_seat.size()-1).getSeat_id();
	}
	
}












