import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * SWEA 2117 홈방범서비스
 * 
 * 운용 비용은 K * K + (K-1) * (K-1)이다.
 * 홈방범 서비스를 제공 받는 집들은 각각 M의 비용을 지불 할 수 있어,
 * 보안 회사에서는 손해를 보지 않는 한 최대한 많은 집에 홈방범 서비스를 제공하려한다.
 * 도시의 크기 N과 하나의 집이 지불할 수 있는 비용 M, 도시의 정보가 주어진다.
 * 이때, 손해를 보지 않으면서 홍방범 서비스를 가장 많은 집들에 제공하는 서비스 영역을
 * 찾고, 그 때의 홈방법 서비스를 제공 받는 집들의 수를 출력하는 프로그램을
 * 작성하라.
 * 
 * @입력
 * 입력의 맨 첫 줄에는 총 테스트 케이스의 개수 T가 주어지고, 그 다음 줄 부터 T개의
 * 테스트 케이스가 주어진다.
 * 각 테스트 케이스의 첫 번재 줄에는 도시의 크기 N과 하나의 집이 지불할 수 있는
 * 비용 M이 주어진다.
 * 그 다음 줄 부터 N*N 크기의 도시정보가 주어진다. 지도에서 1은 집, 나머지는 0
 * 
 * N은 5~20 정수
 * M은 1~10 정수
 * 
 * @출력
 * 최소 운영 비용
 * 
 * @해결방안
 * Flood Fill 방식으로 전체 완탐 N*N *K*K*K = 최대 3,200,000?
 * 
 */

public class Solution {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> q;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0, };
	static int money, range;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			range = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i,j);
				}
			}
			sb.append(range).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(int i, int j) {
		int r = i;
		int c = j;

		visited = new boolean[N][N];
		q = new ArrayDeque<>();
	
		money = map[r][c] == 1 ? M : 0;
        calK(1);
		q.offer(new int[] { r, c });
		visited[r][c] = true;
		int size = q.size();
		
		for (int k = 2; k < 2 * N; k++) {
			while (!q.isEmpty()) {
				if (size == 0) {
					size = q.size();
					calK(k);
					break;
				}
				int[] cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dy[d];
					int nc = cur[1] + dx[d];
					if (nr < 0 || nc < 0 || nr >= N || nc >= N)
						continue;
					if (visited[nr][nc])
						continue;
					visited[nr][nc] = true;
					if (map[nr][nc] == 1)
						money += M;
					
					q.offer(new int[] { nr, nc });
				}
				size--;
			}
		}
	}

	static void calK(int K) {
		int cost = K*K + (K-1) * (K-1);
//		cost_max = Math.max(cost_max, money-cost);
		if(money-cost >= 0) {
//			cost_max = money-cost;
//			System.out.print("money : " + money + "\tcost : " + cost);
//			System.out.println("\tcost_max : " + cost_max);
			range = Math.max(money / M, range);
		}
	}
}