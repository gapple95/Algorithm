import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] coin = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < coin.length; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			int[] dp = new int[target + 1]; // 금액 i를 만드는 방법의 수.
			dp[0] = 1; // 아무것도 선택 안하는 경우
			
			for (int i = 0; i < coin.length; i++) {
				for (int j = 1; j <= target; j++) {
					if(j < coin[i])
						continue;
					dp[j] = dp[j] + dp[j - coin[i]];
				}				
			}
			
			sb.append(dp[target]).append("\n");
		}
		System.out.println(sb);
		
		br.close();
	}

}
