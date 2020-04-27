package d_array;

import java.util.Arrays;
import java.util.Scanner;

public class Score {

	public static void main(String[] args) {
		/*
		 *한줄삭제 : Ctrl + d
		 *한줄복사 : Ctrl + Alt + 방향키(위/아래)
		 *한줄이동 : Alt + 방향키(위/아래)
		 *
		 * 우리반 모두의 Java, Oracle, HTML, CSS, JQuery, JSP 점수를 50 ~100까지 랜덤으로 생성시키고
		 * 석차순으로 석차, 이름, 과목별 점수, 총점, 평균을 출력해주세요.
		 * 직접 사용자가 작성해서 출력할 수 있도록 만들기
		 */
		
//		String[] students = {"최우성", "유효상", "이재민", "이원우", "이희욱", "김동훈", "김아현", "김찬희", "박경범", "박성하", "박승화", "박하은", "박해선", "안승원", "오송헌", "원종찬", "이지윤", "이헌이", "정성훈", "정재은", "정지수", "채홍규", "하지민", "한진수", "정혁도" };
//	String[] subjects = {"Java", "Oracle", "HTML", "CSS", "JQuery", "JSP"};
	
		String[] students = new String[3];
		String[] subjects = new String[3];
		double[][] scores = new double[students.length][subjects.length + 3];
		
		Scanner s = new Scanner(System.in);
		for(int i = 0; i < students.length; i++){
			System.out.print("학생이름>");
			students[i] = s.nextLine();
		}
		
		for(int i = 0; i < subjects.length; i++){
			System.out.print("과목명>");
			subjects[i] = s.nextLine();
		}
		
		for(int i = 0; i < scores.length; i++){
			scores[i][0] = 1;
			for(int j = 0; j < subjects.length; j++){
				System.out.print(students[i] + "의 " + subjects[i] + "점수>");
				scores[i][j+1] = Integer.parseInt(s.nextLine());
//				scores[i][j + 1] = (int)(Math.random() * 51) + 50;
			}
		}
		
		for(int i = 0; i < scores.length; i++){
			for(int j = 0; j < subjects.length; j++){
				scores[i][scores[i].length - 2] += scores[i][j + 1];
			}
			scores[i][scores[i].length - 1] = 
					(int)((double)scores[i][scores[i].length - 2]
							/ subjects.length * 100 + 0.5) / 100.0;
		}
		
		for(int i = 0; i < scores.length; i++){
			for(int j = 0; j < scores.length; j++){
				if(scores[i][scores[i].length - 1] < scores[j][scores[j].length - 1]){
					scores[i][0]++;
				}
			}
		}
		
		for(int i = 0; i < scores.length - 1; i++){
			for(int j = i + 1; j < scores.length; j++){
				if(scores[i][0] > scores[j][0]){
					double[] temp = scores[i];
					scores[i] = scores[j];
					scores[j] = temp;
					
					String tempName = students[i];
					students[i] = students[j];
					students[j] = tempName;
				}
			}
		}
		
		System.out.print("석차\t이름");
		for(int i = 0; i < subjects.length; i++){
			System.out.print("\t" + subjects[i]);
		}
		System.out.println("\t총점\t평균");
		
		for(int i = 0; i < scores.length; i++){
			System.out.print((int)scores[i][0] + "\t" + students[i]);
			for(int j = 1; j < scores[i].length; j++){
				if(j == scores[i].length - 1){
					System.out.print("\t" + scores[i][j]);
				}else{
					System.out.print("\t" + (int)scores[i][j]);
				}
			}
			System.out.println();
		
		}
		
		
		
		
		/*int[][] scores = new int[24][6];
		int[] sum = new int[scores.length];
		double[] avg = new double[scores.length];
//		int j = 0;
		
		for(int i = 0; i < scores.length; i++){
			for(int j = 0; j < scores[i].length; j++){
				scores[i][j] = (int)(Math.random() * 51) + 50;
				sum[i] += scores[i][j];		
			}
			avg[i] = (double)sum[i] / scores[i].length;
//			System.out.println(Arrays.toString(sum));
			
		}
//		System.out.println(Arrays.toString(sum));
//		System.out.println(Arrays.toString(avg));

//		printRank(sum);



//	private static void printRank(int[] sum) {
		int[] rep = new int[scores.length];
		 for(int i = 0; i < scores.length; i++)
			rep[i] =1;
		 
		for(int i = 0; i < scores.length; i++){
			for(int j = 0; j < scores.length; j++){
			if(sum[i] < sum[j]){
			rep[i] += 1;
				
			}
				
			}
		
		}
//		System.out.println(Arrays.toString(rep));
		
		String[][] name = new String[scores.length][6];
		
		name[0] = "김동훈";
		*/
	}
}
