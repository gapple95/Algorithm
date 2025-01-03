import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[][] map;
	static boolean[][] visited;
	static int N, M;

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tmp.charAt(j) + "");
			}
		}
		
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0, 0, 1});
		visited[0][0] = true;
		
		int min = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 끝에 도착했을 경우
			if(cur[0] == N - 1 && cur[1] == M - 1) {
				min = Math.min(min, cur[2]);
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dy[d];
				int nc = cur[1] + dx[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				
				if(map[nr][nc] == 0)
					continue;
				
				if(visited[nr][nc])
					continue;
				visited[nr][nc] = true;
				
				q.offer(new int[] {nr, nc, cur[2] + 1});
				
			}
		}
		
		sb.append(min);
		
		System.out.println(sb);
		
		br.close();
	}

	public static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
