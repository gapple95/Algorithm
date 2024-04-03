import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * SWEA 5643 키순서
 * 
 * 메모리 
 * 시간 
 * 
 * N명의 학생이 있고, 키는 모두 다르다.
 * 1번부터 N번까지 번호가 붙여져있는 학생들에 대하여
 * 두학생끼리 키를 비교한 결과의 일부가 주어져있다.
 * 
 * 이 비교 결과로부터 모든 학생 중에서 키가 가장 작은 학생부터
 * 자신이 몇 번째 인지 알 수 있는 학생들도 있고,
 * 그렇지 못한 학생들도 있다.
 * 
 * 학생들의 키를 비교한 결과가 주어질때, 자신의 키가 몇번째인지
 * 알수 있는 학생들이 모두 몇병인지 계산하는 프로그램
 * 
 */

public class Solution {

	static int[][] adjMatrix;
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adjMatrix = new int[N+1][N+1];
			int[] c = new int[N+1];
			int[] p = new int[N+1];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// a인 학생이 b인 학생보다 키가 작다.
				adjMatrix[a][b] = 1;
			}

//			for (int i = 1; i <= N; i++) {
//				c[i] = checkChild(i);
//				p[i] = checkParent(i);
//			}
//
//			System.out.println(Arrays.toString(c));
//			System.out.println(Arrays.toString(p));
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if(checkChild(i) + checkParent(i) == N - 1)
					cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	public static int checkChild(int idx) {
		int cnt = 0;
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if(adjMatrix[idx][i] == 1) {
				q.offer(i);
				cnt++;
				visited[i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 1; i <= N; i++) {
				if(adjMatrix[cur][i] == 1 && !visited[i]) {
					visited[i] = true;
					q.offer(i);
					cnt++;
				}
			}
		}
		
		
		return cnt;
	}
	
	public static int checkParent(int idx) {
		int cnt = 0;
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if(adjMatrix[i][idx] == 1) {
				q.offer(i);
				cnt++;
				visited[i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 1; i <= N; i++) {
				if(adjMatrix[i][cur] == 1 && !visited[i]) {
					visited[i] = true;
					q.offer(i);
					cnt++;
				}
			}
		}
		
		
		return cnt;
	}
}