import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  백준 1463 1로만들기
 *  
 *  메모리 50828
 *  시간 120
 *  
 *  정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지이다.
 *  1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 *  2. X가 2로 나누어 떨어지면, 2로 나눈다.
 *  3. 1을 뺀다.
 *  
 *  정수 N이 주어졌을 때, 위와 같은 세 개를 적절히 사용해서 1을 만들려고 한다.
 *  연산을 사용하는 횟수의 최솟값을 출력하시오.
 *  
 *  @입력
 *  10^6 보다 작거나 같은 정수 N
 *  
 *  @출력
 *  첫째줄에 연산을 하는 횟수의 최솟값을 출력한다.
 *  
 *  @해결방안
 *  dp[1] = 0, dp[2] = 1, dp[3] = 1;
 *  에서 시작하여 
 *  n/3 == 0
 *  3나누는 연산
 *  n/3 == 1 
 *  -1연산 => dp[n-1] +1
 *  b/3 == 2
 *  2나누는 연산으로 판별
 *  
 */

public class Main {

	static int[] dp = new int[10000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;

		for (int i = 4; i <= N; i++) {
			dp[i] = dp[i - 1] + 1;
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
			}
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
			}
//			if (i % 6 == 0) {
//				dp[i] = Math.min(dp[i / 3], dp[i / 2]) + 1;
//			} else if (i % 3 == 0) {
//				dp[i] = Math.min(dp[i / 3], dp[i - 1]) + 1;
//			} else if (i % 2 == 0) {
//				dp[i] = Math.min(dp[i / 2], dp[i - 1]) + 1;
//			} else {
//				dp[i] = dp[i - 1] + 1;
//			}
		}
//		for (int i = 0; i <= N; i++) {
//			System.out.print(dp[i] + " ");
//		}
//		System.out.println();
		System.out.println(dp[N]);
	}

}