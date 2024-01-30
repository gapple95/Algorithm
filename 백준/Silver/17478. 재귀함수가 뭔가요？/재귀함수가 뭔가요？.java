/*
 * 백준 17478 재귀함수가 뭔가요?
 * 
어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.
"재귀함수가 뭔가요?"
"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.
마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.
그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어."
____"재귀함수가 뭔가요?"
____"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.
____마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.
____그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어."
________"재귀함수가 뭔가요?"
________"재귀함수는 자기 자신을 호출하는 함수라네"
________라고 답변하였지.
____라고 답변하였지.
라고 답변하였지.

 * 위의 예시를 보고 출력을 만들어 보자
 * 
 * 입력
 * n (1<= n <=50)
 * 
 * 출력
 * 위의 예시
 * 
 */
import java.util.Scanner;

public class Main {

	static int n;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		def(n);
	}
	
	public static void def(int i) {
		for(int j=n; j>i; j--)
			System.out.print("____");
		System.out.println("\"재귀함수가 뭔가요?\"");
		if(i==0) { // 종료 조건
			for(int j=n; j>i; j--)
				System.out.print("____");
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
		} else {
			for(int j=n; j>i; j--)
				System.out.print("____");
			System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
			for(int j=n; j>i; j--)
				System.out.print("____");
			System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
			for(int j=n; j>i; j--)
				System.out.print("____");
			System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			def(i-1);
		}
		for(int j=n; j>i; j--)
			System.out.print("____");
		System.out.println("라고 답변하였지.");
	}

}
