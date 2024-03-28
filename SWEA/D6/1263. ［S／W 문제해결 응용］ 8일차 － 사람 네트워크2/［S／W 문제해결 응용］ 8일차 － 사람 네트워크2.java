import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 사람 네트워크 2 
 * 
 * 메모리 
 * 시간 
 * 
 */

public class Solution {

	static final int INF = 1001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());

			int map[][] = new int[N][N];
			int dp[][] = new int[N][N];

			int tmp;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmp = Integer.parseInt(st.nextToken());
					map[i][j] = tmp;
					if (i != j && tmp == 0) {
						map[i][j] = INF;
					}
				}
			}
			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + "\t");
//				}
//				System.out.println();
//			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
//					if (i == k)
//						continue;
					for (int j = 0; j < N; j++) {
//						if (j == k || j == i)
//							continue;

						map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
					}
				}
			}
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + "\t");
//				}
//				System.out.println();
//			}
			
			int min = Integer.MAX_VALUE;
			int sum;
			for (int i = 0; i < N; i++) {
				sum = 0;
				for (int j = 0; j < N; j++) {
					sum+=map[i][j];
				}
//				System.out.println(sum);
				min = Math.min(min, sum);
			}
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}

}