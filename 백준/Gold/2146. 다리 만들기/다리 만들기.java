import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 넘버링
		int number = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 1)
					continue;
				Deque<int[]> q = new ArrayDeque<>();
				q.offer(new int[] { i, j });
				map[i][j] = number;

				while (!q.isEmpty()) {
					int[] cur = q.poll();

					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dy[d];
						int nc = cur[1] + dx[d];

						if (nr < 0 || nc < 0 || nr >= N || nc >= N)
							continue;

						if (map[nr][nc] == 0)
							continue;

						if (visited[nr][nc])
							continue;

						visited[nr][nc] = true;
						map[nr][nc] = number;

						q.offer(new int[] { nr, nc });

					}
				}
				number++;
			}
		}

		// 다리 잇기
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				visited = new boolean[N][N];

				Deque<int[]> q = new ArrayDeque<>();
				q.offer(new int[] { i, j, 0 });
				visited[i][j] = true;

				while (!q.isEmpty()) {
					int[] cur = q.poll();

					// 첫 출발 위치와 다른 섬이여야한다. 0은 바다이므로 제외
					if (map[cur[0]][cur[1]] != map[i][j] && map[cur[0]][cur[1]] != 0) {
						min = Math.min(min, cur[2]);
						break;
					}

					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dy[d];
						int nc = cur[1] + dx[d];

						if (nr < 0 || nc < 0 || nr >= N || nc >= N)
							continue;

						if (map[nr][nc] == map[i][j])
							continue;

						if (visited[nr][nc])
							continue;

						visited[nr][nc] = true;

						q.offer(new int[] { nr, nc, cur[2] + 1 });
					}
				}
			}
		}
		
		sb.append(min - 1);
		
		System.out.println(sb);
		
		br.close();
	}

}
