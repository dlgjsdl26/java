package h_useful;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import e_oop.ScanUtil;

public class RegularExpression {

	public static void main(String[] args) {

		/*
		 * 정규표현식 : 문자열의 패턴을 검사하는 표현식
		 * 
		 * ^	뒷 문자로 시작
		 * $	앞 문자로 종료
		 * .	임의의 문자(줄바꿈 제외)
		 * *	앞 문자가 0개 이상
		 * +	앞 문자가 1개 이상
		 * ?	앞 문자가 없거나 1개
		 * []	문자의 집합이나 범위([a-z] : a부터 z까지, [^a-z]: a부터 z가 아닌것)
		 * {}	앞 문자의 개수({2} : 2개, {2,4} : 2개 이상 4개 이하)
		 * ()	그룹화(1개의 문자처럼 인식)
		 * |	OR 연산
		 *\S	공백, 탭, 줄바꿈
		 *\s	공백, 탭, 줄바꾼이 아닌 문자
		 *\w	알파벳이나 숫자
		 *\W	알파벳이나 숫자가 아닌 문자
		 *\d	숫자
		 *\D	숫자가 아닌 문자
		 *(?i)  뒷 문자의 대소문자 구분 안함
		 *\		정규표현식에서 사용되는 특수문자 표현
		 */
		
//		String str = "abc123";
//		String regex = "[a-z]{3}[0-9]{1,3}";
//		String regex = "[a-z0-9]+";
//		String regex = "\\w*";
//		String regex = ".*";
		
		
//		Pattern p = Pattern.compile(regex);
//		
//		Matcher m = p.matcher(str);
//		
//		System.out.println(m.matches());

		
		
		//아이디, 전화번호, 이메일주소의 유효성을 검사하는 정규표현식을 만들어주세요.
		
		//아이디
		System.out.println("아이디를 입력 해주세요>");
		String input1 = ScanUtil.nextLine(); 

		String id = "\\w{5,20}";
		Pattern p1 = Pattern.compile(id);
		Matcher m1 = p1.matcher(input1);

		if(m1.matches() == true)
			System.out.println("가능합니다.");
		else 
		{do 
			{System.out.println("알파벳과 숫자 5~20자 이상 입력해주세요>");
		input1 = ScanUtil.nextLine(); 

		id = "\\w{5,20}";
		p1 = Pattern.compile(id);
		m1 = p1.matcher(input1);
		}while(m1.matches() == false);
		System.out.println("가능합니다.");
			}
		
		
		//전화번호
		System.out.println("전화번호를 입력 해주세요>");
		String input2 = ScanUtil.nextLine(); 
		
		String pn = "\\d{3,4}-?\\d{4}-?\\d{4}";
		Pattern p2 = Pattern.compile(pn);
		Matcher m2 = p2.matcher(input2);

		System.out.println(m2.matches());

		
		//이메일 주소
		System.out.println("이메일주소를 입력 해주세요>");
		String input3 = ScanUtil.nextLine();
		
		String mail = "[a-z0-9_-]{5,20}@[a-zA-Z0-9]+\\.(?!)(com|net|org|[a-z]{2}\\.kr))$";
		Pattern p3 = Pattern.compile(mail);
		Matcher m3 = p3.matcher(input3);
		
		if(m3.matches() == true)
			System.out.println("가능합니다.");
		else
		{do
			{System.out.println("이메일 형식이 아닙니다. 다시 입력해주세요>");
			input3 = ScanUtil.nextLine();
			
			mail = "[a-z0-9_-]{5,20}@[a-zA-Z]+\\.(?!)(com|net|org|[a-z]{2}\\.kr))$";
			p3 = Pattern.compile(mail);
			m3 = p3.matcher(input3);
			System.out.println();
			}while(m3.matches() == false);
		}
		System.out.println("가능합니다.");
	}

}

//아이디 : [a-z0-9_-]{5,20}
//전화번호 : ^0\\d{1,3}-\\d{3,4}\\d{4}
//이메일주소 : [a-z0-9_-]{5,20}@[a-zA-Z]+\\.(?!)(com|net|org|[a-z]{2}\\.kr))$




















