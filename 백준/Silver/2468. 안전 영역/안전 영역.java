import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int map[][], N, max, max_area;
	static boolean visited[][];
	static Queue<int[]> q;
	static int[] dx = new int[] { 0, 0, -1, 1 };
	static int[] dy = new int[] { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		max = Integer.MIN_VALUE;
		q = new ArrayDeque<>();

		int tmp;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp > max)
					max = tmp;
			}
		}

		for (int level = 0; level < max; level++) {
//			System.out.println("level : " + level);
			visited = new boolean[N][N];
			bfs(level);
		}
		sb.append(max_area);
		System.out.println(sb);

	}

	public static void bfs(int level) {
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= level || visited[i][j])
					continue;
				visited[i][j] = true;
				q.offer(new int[] { i, j });
				while (!q.isEmpty()) {
					int[] cur = q.poll();
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dy[d];
						int nc = cur[1] + dx[d];

						if (nr < 0 || nc < 0 || nr >= N || nc >= N)
							continue;
						
						if(map[nr][nc] <= level)
							continue;
						
						if(visited[nr][nc])
							continue;
						visited[nr][nc] = true;
						
						q.offer(new int[] {nr, nc});
					}
				}
//				print();
				idx++;
			}
		}
		if(max_area < idx)
			max_area = idx;
	}
	
	public static void print() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(visited[i][j]?"■":"□");
			}
			System.out.println();
		}
		System.out.println();
	}
}