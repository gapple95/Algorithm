import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * SWEA 1949 등산로 조성
 * 
 * 메모리 
 * 시간 
 * 
 * 등산로를 조성하려고한다.
 * 부지는  n*n 이 있으며, 최대한 긴 등산로를 만들 계획이다.
 * 
 * 맵안의 숫자는 지혀으이 높이
 * 
 * 규칙은 다음과같다
 * 1. 등산로는 가장 높은 봉우리에서 시작해야한다.
 * 2. 등산로는 산으로 올라갈 수 있도록 반드시 높은 지형에서 낮은 지형으로
 *    가로 또는 세로 방향으로 연결이 되어야 한다.
 *    즉, 높이가 같은 곳 혹은 낮은 지형이나, 대각선 방향의 연결은 불가능
 * 3. 긴 등산로를 만들기 위해 "딱 한곳"을 정해 최대 K 깊이 만큼
 *    지형을 깍는 공사를 할 수 있다.
 * 
 * N*N 크기의 지도가 주어지고, 최대 공사 가능 깊이 K가 주어진다.
 * 이때 만들 수 있는 가장 긴 등산로를 찾아 그 길이를
 * 출력하는 프로그램을 작성하라.
 * 
 * @입력
 * T
 * 한변의 길이 N, 최대 공사 가능깊이 K
 * 
 * @출력
 * #t 최대 등산로 길이
 * 
 * @해결방안
 * 단 한번 높이를 깍을 수 있다면,, 모든 곳을 깍는것보다.
 * 가는길에 한번씩 깍아보고 가능한 길이의 최댓값을 구해보자.
 * 
 * 
 * 
 */

public class Solution {

	static int N;
	static int K;
	static int[][] map;
	static int max;
	static List<int[]> max_pos;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			max = Integer.MIN_VALUE;
			max_pos = new ArrayList<>();

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					map[i][j] = tmp;
					max = Math.max(max, tmp);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max)
						max_pos.add(new int[] { i, j }); // y, x, k flag, range
				}
			}

			max = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= K; k++) {
						map[i][j]-=k;
						bfs();
//					System.out.println(max);
						map[i][j]+=k;
					}
				}
			}

			sb.append(max).append("\n");
		}
		System.out.println(sb);

	}

	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		for (int[] pos : max_pos)
			q.offer(pos);

		int size = q.size();
		int cnt = 1;

		while (!q.isEmpty()) {
			if (size == 0) {
				cnt++;
				size = q.size();
			}

			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dy[d];
				int nc = cur[1] + dx[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;

				if (map[cur[0]][cur[1]] <= map[nr][nc])
					continue;

				q.offer(new int[] { nr, nc });

			}
			size--;
		}

		max = Math.max(max, cnt);
	}
}