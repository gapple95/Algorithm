import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[][] map, answer;
	static int N, M;
	static int targetX, targetY;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		answer = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					targetX = j;
					targetY = i;
				}
				answer[i][j] = -1;
			}
		}
		
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {targetY, targetX, 0});
		answer[targetY][targetX] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dy[d];
				int nc = cur[1] + dx[d];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				
				if(map[nr][nc] == 0)
					continue;
				
				if(answer[nr][nc] != -1)
					continue;
				
				answer[nr][nc] = cur[2] + 1;
				q.offer(new int[] {nr, nc, cur[2] + 1});
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(answer[i][j] == -1 && map[i][j] == 0)
					sb.append(0);
				else
					sb.append(answer[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}
