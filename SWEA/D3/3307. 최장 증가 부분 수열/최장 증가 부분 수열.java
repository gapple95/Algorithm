import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 1194 최장 증가 수열
 * 
 */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int max = Integer.MIN_VALUE;
			
			int N = Integer.parseInt(br.readLine());
			int arr[] = new int[N + 1];
			int LIS[] = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				LIS[i] = 1;
				for (int j = 1; j <= i - 1; j++) {
					if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1)
						LIS[i] = LIS[j] + 1;
				}
				max = Math.max(max, LIS[i]);
			}
			sb.append("#"+t+" ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}
}