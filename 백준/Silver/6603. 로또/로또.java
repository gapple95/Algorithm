import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 6603 로또
 * 
 * 독일 로또는 1~49에서 수 6개를 고른다.
 * 가장 유명한 전략은 49가지 수 중k(k>6)개의 수를 골라
 * 집합 S를 만든다음 그 수만 가지고 번호를
 * 선택하는 것이다.
 * 
 * 집합 S와 k가 주어졌을 떄, 수를 고르는 
 * 모든 방법을 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 입력은 여러 개의 테스트 케이스로 이루어짐
 * 각 테스트 케이스는 한 줄로 이루어져 있다.
 * 
 * 첫번째 수는 k(6< k <13)이고, 다음에
 * k개 수는 집합 S에 포함되는 수이다.
 * S의 원소는 오름차순으로 주어진다.
 * 
 * 입력의 마지막 줄에는 0이 하나 주어진다.
 * 
 * @출력
 * 각 테스트 케이스 마다 수를 고르는 모든 방법을
 * 출력한다.
 * 이때, 사전순으로 출력한다.
 * 각 테스트 케이스 사이에는 빈줄을 하나 출력한다.
 * 
 * @해결방안
 * 중복없는 조합으로 구현한다.
 * 
 */

public class Main {
	
	static int k;
	static int[] S;
	static int[] picked;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		while(true) {
			
			// 각 테스트 케이스의 배열 입력
			String[] tmp = s.nextLine().split(" ");
			S = new int[tmp.length - 1];
			k = Integer.parseInt(tmp[0]); // 첫 번째 수는 k
			for(int i=1; i<tmp.length; i++) { // 나머지들은 S집합
				S[i-1] = Integer.parseInt(tmp[i]);
			}
			if(k == 0) // 0이 입력되면 테스트케이스 나오기
				break;
//			System.out.println(Arrays.toString(S)); // 입력값 확인
			// picked 선언
			picked = new int[6]; // 6자리만 뽑기 떄문에!
			df(0, 0);
			System.out.println();
		}
	}
	
	public static void df(int idx, int start) {
		if(idx == 6) { // 기저조건
			for(int p: picked) {
				System.out.print(p + " ");
			}
			System.out.println();
			return;
		} else { //유도패턴
			for(int i=start; i<S.length; i++) {
				picked[idx] = S[i];
				df(idx+1, i + 1);
			}
		}
	}

}