import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[16];
		
		dp[0] = 2;
		
		for (int i = 1; i <= N; i++) {
			dp[i] = 2 * dp[i-1] - 1;
		}
		
		sb.append(dp[N] * dp[N]);
		System.out.println(sb);
	}

}