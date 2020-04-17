package c_statement;

import java.util.Scanner;

public class SimpleCalculator {

	public static void main(String[] args) {
		//두개의 숫자와 연산자를 입력받아 연산결과를 알려주는 프로그램을 만들어주세요.

		Scanner s = new Scanner(System.in);
		System.out.print("숫자를 입력해주세요>");
		int input1 = Integer.parseInt(s.nextLine());
		
		System.out.print("연산자를 입력해주세요>");
		String input2 = s.nextLine();
		
		System.out.print("숫자를 입력해주세요>");
		int input3 = Integer.parseInt(s.nextLine());
		
		/*int sum = input1 + input3;
		int min = input1 - input3;
		int gob = input1 * input3;
		double nan = input1 / input3;
		result = null;
		
		if(input2 == "+")
			result = sum;
		else if(input2 == "-")
			result = min;
		else if(input2 == "*")
			result = gob;
		else if(input2 == "/")
			result = nan;*/
		
		
			
		
	}

}
