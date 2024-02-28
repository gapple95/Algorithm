import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 백준 1149 RGB거리
 * 
 * 메모리 
 * 시간 
 * 
 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순선대로 있다.
 * 집은 빨강, 초록,파랑 중 하나의 색으로 칠해야한다.
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때,
 * 아래의 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 * 
 * * 1번 집의 색은 2번직의 색과 같지 않아야한다.
 * * N번 집의 색은 N-1번 집의 색과 같지 않아야 한다
 * * i(2<=i<=N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아햐 한다.
 * 
 * @입력
 * 첫째 줄에 집의 수 N(2~1000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.
 * 칠하는 비용으 1000보다 작거나 같은 자연수
 * 
 * @출력
 * 첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 * 
 * @해결방안
 * 인접 집이 같은 색이면 안되기 때문에, 완탐으로 n-1, n, n+1의 경우의 수를 생각해야하며
 * 각각 3개의 값을 고려해야한다. 이는 3C3을 최소 N+1가지 해야하므로 3^N+1 경우의수가 터진다.
 * 
 * DP로 풀어야한다.
 * 첫번째 집과 두번째 집의 색을 결정하고,
 * 색은 1번과 2번이 색칠을 하면 3번은 1번이 칠한 색과 2번이 칠하지 않은 색 중 하나를 선택해야한다.
 * 1번과 2번이 칠했을 때 3번은 각 앞에 색깔을 제외한 색의 최솟값을 사용해야하는데,
 * 
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		
		st = new StringTokenizer(br.readLine());
		int[][] dp = new int[N+1][3];
		
		dp[1][0] = Integer.parseInt(st.nextToken());
		dp[1][1] = Integer.parseInt(st.nextToken());
		dp[1][2] = Integer.parseInt(st.nextToken());
		
		
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + Integer.parseInt(st.nextToken());
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + Integer.parseInt(st.nextToken());
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			min = Math.min(min, dp[N][i]);
		}
		System.out.println(min);
	}

}