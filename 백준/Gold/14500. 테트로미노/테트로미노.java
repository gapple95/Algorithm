import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, map[i][j]);
				visited[i][j] = false;
				cross(i, j);
			}
		}

		sb.append(max);

		System.out.println(sb);

		br.close();
	}

	public static void dfs(int y, int x, int depth, int sum) {
		if (depth == 3) {
			max = Math.max(max, sum);
			return;
		}

		int nr, nc;
		for (int d = 0; d < 4; d++) {
			nr = y + dy[d];
			nc = x + dx[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= M)
				continue;

			if (visited[nr][nc])
				continue;

			visited[nr][nc] = true;
			dfs(nr, nc, depth + 1, sum + map[nr][nc]);
			visited[nr][nc] = false;
		}
	}

	public static void cross(int y, int x) {
		int sum = map[y][x];

		int nr, nc, cnt = 0;
		for (int d = 0; d < 4; d++) {
			nr = y + dy[d];
			nc = x + dx[d];

			if (nr < 0 || nc < 0 || nr >= N || nc >= M)
				continue;
			sum += map[nr][nc];
			cnt++;
		}

		if (cnt == 3)
			max = Math.max(max, sum);
		else {
			for (int d = 0; d < 4; d++) {
				nr = y + dy[d];
				nc = x + dx[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				max = Math.max(max, sum - map[nr][nc]);
			}
		}
	}
}
