package d_array;

import java.util.Arrays;

public class Array {

	public static void main(String[] args) {
		
		/*
		 * 배열
		 * - int[] number = new int[5];
		 * - int[] number = new int[]{10, 20, 30, 40, 50};
		 * - int[] number = {10, 20, 30, 40, 50};
		 */
		
		//배열은 참조형 타입이다.
		
		int[] array; // 배열의 주소를 저장할 공간이 만들어진다.
		array = new int[5]; //배열이 생성되고 그 주소가 변수에 저장된다.
		//new : 새로운 저장공간 생성 및 주소 반환
		//int[5] : int를 저장할 수 있는 5개의 공간
		//배열초기화시 기본값이 저장된다.
		
		System.out.println(array); //주소가 저장되어 있다.
		
		System.out.println(array[0]); //실제값에 접근하기 위해서는 index를 지정해줘야 한다.
		//index에는 int만 사용할 수 있다.
		//배열의 최대 크기는 int의 최대값(약 20억)이다.
		
		String arrayStr = Arrays.toString(array); //배열의 모든 인덱스에 저장된 값을 문자열로 반환한다.
		System.out.println(arrayStr);
		
		int[] iArray1 = new int[]{1, 2, 3}; //값의 개수로 배열의 길이가 정해진다.
		int[] iArray2 = {1, 2, 3}; //선언과 초기화를 동시에 해야한다.
		int[] iArrya3;
//		iArray3 = {1, 2, 3}//컴파일 에러 발생

		array[0] = 10;
		array[1] = 20;
		array[2] = 30;
		array[3] = 40;
		array[4] = 50;
		
		//정수를 저장할 수 있는 길이가 10인 배열을 생성 및 초기화 해주세요.
		int[] arr = new int[10];
		
		//모든 인덱스에 있는 값을 변경해주세요.]
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;
		arr[3] = 40;
		arr[4] = 50;
		arr[5] = 60;
		arr[6] = 70;
		arr[7] = 80;
		arr[8] = 90;
		arr[9] = 100;
		
		//모든 인덱스에 있는 값을 더해주세요.
		int sum = 0;
		sum += arr[0];
		sum += arr[1];
		sum += arr[2];
		sum += arr[3];
		sum += arr[4];
		sum += arr[5];
		sum += arr[6];
		sum += arr[7];
		sum += arr[8];
		sum += arr[9];
		System.out.println(sum);
		
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i]);
		}
		
		for(int i = 0; i < array.length; i++){
			array[i] = i + 1;
		}
		System.out.println(Arrays.toString(array));
		
		//배열의 합계와 평균을 구해보자.
		int[] numbers = new int[10];
		
		for(int i = 0; i < numbers.length; i++){
			numbers[i] = (int)(Math.random() * 100) + 1;
		}
		System.out.println(Arrays.toString(numbers));
		
		sum = 0;
		double avg = 0;
		for(int i = 0; i < numbers.length; i++){
			sum += numbers[i];
		}
		avg = (double)sum / numbers.length;
		System.out.println("합계 : " + sum + " / 평균 : " + avg);
		
		//향상된 for문
		for(int number : numbers){ //배열에 있는 값을 차례대로 변수에 저장 후 실행한다.
			System.out.println(number);
		}
		
		//배열에 저장된 숫자들 중 최소값과 최대값을 출력해주세요.
		
		/*int max = 0;
		int min = 100;
		for(int number : numbers){
			
			if(number <= min){
				min = number;
				} 	
			}
		for(int number : numbers){
			
			if(number >= max){
				max = number;
				} 	
			}
			System.out.println("최소값 : " + min + "최대값 : " + max);*/
		
		int min = numbers[0];
		int max = numbers[0];
		
		for(int i = 0; i < numbers.length; i++){
			if(numbers[i] < min){
				min = numbers[i];
			}
			if(max < numbers[i]){
				max = numbers[i];
			}
		}
		System.out.println("최소값 : " + min + "최대값 : " + max);
		
		
		int[] shuffle = new int[20];
		
		for(int i = 0; i < shuffle.length; i++){
			shuffle[i] = i + 1;
		}
		System.out.println(Arrays.toString(shuffle));
		
		//배열의 값을 섞어주세요.
		for(int i = 0; i< shuffle.length * 10; i++){
			int random = (int)(Math.random() * shuffle.length);
				
			int temp = shuffle[0];
			shuffle[0] = shuffle[random];
			shuffle[random] = temp;
				
		}
		
		System.out.println(Arrays.toString(shuffle));
		
		
		//1~10 사이의 난수를 500번 생성하고, 각 숫자가 생성된 횟수를 출력해주세요.
		int[] su = new int[10];
		
		su[0] = 0;
		su[1] = 0;
		su[2] = 0;
		su[3] = 0;
		su[4] = 0;
		su[5] = 0;
		su[6] = 0;
		su[7] = 0;
		su[8] = 0;
		su[9] = 0;
		            
		for(int i = 0; i <500; i++){
			
		int ran = (int)(Math.random() * 10) + 1;
		
		switch(ran){
		case 1 :
			su[0] += 1;
			break;
		case 2 :
			su[1] += 1;
			break;
		case 3 :
			su[2] += 1;
			break;
		case 4 :
			su[3] += 1;
			break;
		case 5 :
			su[4] += 1;
			break;
		case 6 :
			su[5] += 1;
			break;
		case 7 :
			su[6] += 1;
			break;
		case 8 :
			su[7] += 1;
			break;
		case 9 :
			su[8] += 1;
			break;
		case 10 :
			su[9] += 1;
			break;
			}
		}
		System.out.println("각 숫자가 생성된 횟 수 :" + "   1 :" + su[0] + "   2 :" + su[1] + "   3 :" + su[2] + "   4 :" + su[3] + "   5 :" + su[4] + "   6 :" + su[5] + "   7 :" + su[6] + "   8 :" + su[7] + "   9 :" + su[8] + "   10 :" + su[9] + "입니다." );
		
		
	}
}


































