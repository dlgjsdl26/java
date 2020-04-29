package e_oop;

public class Static {

	//전역변수
	//static 붙은 전역 변수 : 클래스 변수
	//static 붙지 않은 전역 변수 : 인스턴스 변수
	
	//메서드
	//static 붙은 메서드 : 클래스 메서드
	//static 붙지 않은 메서드 : 인스턴스 메서드
		
		
	//static을 붙이면 객체생성을 하지 않고도 사용할 수 있다.
	
	//값을 공유하기 위해 static을 붙인다.
	static int var;
	
	//메서드에서 인스턴스 변수를 사용하지 않을때 static을 붙이는 것을 고려한다.
	//클래스 변수와 관련된 메서드인 경우
	//유틸리티 성향의 메서드인 경우(Math.random())
	public static void main(String[] args) {
		
		Human 철수 = new Human("철수");
		Human 영희 = new Human("영희");
		
		철수.saveMoney(100000);
		영희.saveMoney(200000);

		철수.saveDateMoney(200000);
		영희.saveDateMoney(200000);
		
		Human.saveDateMoney(100000);
		
		System.out.println("문자열 입력> ");
		String str = ScanUtil.nextLine();
		System.out.println("입력받은 문자열 : " + str);
		
		System.out.println("숫자 입력> ");
		int num = ScanUtil.nextInt();
		System.out.println("입력받은 숫자 : " + num);
	}

}


class Human{
	
	String name;
	
	Human(String name){
		this.name = name;
	}
	
	int account;
	
	void saveMoney(int money){
		account += money;
		System.out.println(name + "의 통장 잔고 : " + account);
	}
	
	static int dateAccount;
	
	static void saveDateMoney(int money){
		dateAccount += money;
		System.out.println("데이트 통장 잔고 : " + dateAccount);
	}
	
}










