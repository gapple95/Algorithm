import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 12891 DNA 비밀번호
 * DNA문자열은 모든 문자열에 등장하는 문자가 {'A','C','G','T'}
 * 인 문자열을 말한다.
 * ACKA는 DNA 문자열이 아니지만 "ACCA"는 DNA 문자열이다.
 * 
 * 비멀번호로 DNA문자열을 사용하면 매우 취약하다. AAAA와 같은비번.
 *  민호는 부분문자열에서 등장하는 문자의 개수가 특정 개수
 *  이상이여야 비밀번호로 사용할 수 있따는 규칙을 만들었따.
 *  
 *  임의의 DNA문자열이 "AAACCTGCCAA"이고 민호가 뽑을
 *  문자열의 길이를 4라고 하자. 그리고 부분 문자 열에'A'는
 *  1개이상, 'C'는 1개이상, 'G'는 1개 이상, 'T'는 0개이상이
 *  듣ㅇ장해야 비밀번호를 사용할 수 있다고 하자. 'ACCT'는 G가 1개
 *  이상 등장 못 했기 때문에 사용 x
 *  GCCA는 모든 조건을 만족하기 때문에 사용 가능
 *  
 *  ACGT가 각각 몇 번이상 등장해야 빌밀번호로
 *  사용할 수 있는지 순서대로 주어졌을 때
 *  민호가 만들 수 있는 비밀번호의 종류의
 *  수를 구하는 프로그램을 작성하자.
 *  단 부분문자열이 등장하는 위치가 다르다면 부분 문자열이
 *  같다고 하더라도 다른 문자열로 취급한다.
 *  
 *  @입력
 *  첫 번째줄에 민호가 임의로 만든 DNA 문자열 길이
 *  S와 비밀번호로 사용할 부분문자열의 길이 P가 주어진다.
 *  두번 째 줄에는 민호가 임의로 만든 DNA 문자열이 주어진다.
 *  세번 째 줄에는 부분물자열에 포함되어야할 A C G T의
 *  최소 개수가 공백으로 주어진다.
 *  각각의 수는 S보다 작거나 같은 음이 아닌 정수이며 S보다 작거나
 *  같음이 보장된다.
 *  
 *  
 *  @출력
 *  첫 번째 줄에 민호가 만들 수 있는 비밀번호의 종류의 수를 출력해라
 *  
 *  
 *  @해결방안
 *  1. 먼저 배열에 갯수를 카운팅하고 안되면 0
 *  2. 되었다면 순서에 상관없으므로 순열로 배열들을 생성
 *  3. 생성된 배열에서 P 의 길이만큼 뽑아서 사용
 *  4. P에 조건이 A C G T 의 범위와 같다면 카운팅
 *  => 연속된 부분문자열을 찾는것이기 때문에 배열을 활용해서 풀기
 *  필요한 A C GT의 배열을 찾는 함수가 필요
 * 
 */

public class Main {

	public static int[] dna = new int[4];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			dna[i] = Integer.parseInt(st.nextToken());
		}
		int[] copy = dna.clone();

		//받은 배열
		int A = dna[0];
		int C = dna[1];
		int G = dna[2];
		int T = dna[3];
		// 애초에 아예 안맞는다면
		for (int i = 0; i < S; i++) {
			switch (str.charAt(i)) {
			case 'A':
				copy[0]--;
				break;
			case 'C':
				copy[1]--;
				break;
			case 'G':
				copy[2]--;
				break;
			case 'T':
				copy[3]--;
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (copy[i] >= 1) {
				System.out.println(0);
				System.exit(0);
			}
		}

		// 배열 확인
		int answer = 0;
		
		//확인 배열
		int a = 0;
		int c = 0;
		int g = 0;
		int t = 0;
		int[] select = new int[P];
		for (int i = 0; i < S; i++) {
			char rear = str.charAt(i);
			switch (rear) {
			case 'A':
				a++;
				break;
			case 'C':
				c++;
				break;
			case 'G':
				g++;
				break;
			case 'T':
				t++;
				break;
			}
			
			if (i > P-1) { // P만큼 커지면
				char head = str.charAt(i - P);
				switch (head) {
				case 'A':
					a--;
					break;
				case 'C':
					c--;
					break;
				case 'G':
					g--;
					break;
				case 'T':
					t--;
					break;
				}
			}

			if (A <= a && C <= c && G <= g && T <= t && P-1 <= i) {
				answer++;
			}
		}
		System.out.println(answer);
	}

}