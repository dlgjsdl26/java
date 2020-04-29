package e_oop;

public class TV {

	boolean power;	//전원
	int channel;	//채널
	int volume;		//음량
	
	final int MIN_CHANNEL = 1;
	final int MAX_CHANNEL = 100;
	final int MIN_VOLUME = 0;
	final int MAX_VOLUME = 10;
	
	TV(){
		power = false;
		channel = 1;
		volume = 5;
	}
	
	void power(){
		power = !power;
		System.out.println(power ? "TV 켜짐" : "TV 꺼짐");
	}
	
	void changeChannel(int channel){
		if(power){
			if(MIN_CHANNEL <= channel && channel <= MAX_CHANNEL){
				this.channel = channel;
			}
			System.out.println("채널. " + this.channel);
		}
	}
	
	void channelUp(){
		changeChannel(channel + 1);
	}
	
	void channelDown(){
		changeChannel(channel - 1);
	}
	
	void volumeUp(){
		if(power){
			if(volume < MAX_VOLUME){
				volume++;
			}
			showVolume();
		}
	}
	
	void volumeDown(){
		if(power){
			if(MIN_VOLUME < volume){
				volume--;
			}
			showVolume();
		}
	}
	
	void showVolume(){
		System.out.print("음량. ");
		for(int i = MIN_VOLUME + 1; i <= MAX_VOLUME; i++){
			if(i <= volume){
				System.out.print("■");
			}else{
				System.out.print("□");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		TV tv = new TV();
		
		int input = 0;
		
		do{
			System.out.println("1.전원\t\t2.채널업\t\t3.채널다운\n4.채널변경\t\t5.볼륨업\t\t6.볼륨다운\n0.종료");
			System.out.print("입력>");
			input = ScanUtil.nextInt();
			
			switch (input) {
				case 1 : tv.power(); break;
				case 2 : tv.channelUp(); break;
				case 3 : tv.channelDown(); break;
				case 4 :
					System.out.print("채널 입력>");
					tv.changeChannel(ScanUtil.nextInt());
					break;
				case 5 : tv.volumeUp(); break;
				case 6 : tv.volumeDown(); break;
				case 0 : System.exit(0); break;
			}
			System.out.println();
		}while(input != 0);
	}
	
}



//이것과 비슷한 대상으로 클래스를 만들기 (ex/ 휴대폰)





