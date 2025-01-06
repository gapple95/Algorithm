import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());

			int[][] map = new int[l][l];
			boolean[][] visited = new boolean[l][l];
			
			st = new StringTokenizer(br.readLine());
			int startY, startX;
			startY = Integer.parseInt(st.nextToken());
			startX = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int endY, endX;
			endY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			
			Deque<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {startY, startX, 0});
			visited[startY][startX] = true;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				if(cur[0] == endY && cur[1] == endX) {
					sb.append(cur[2]);
					break;
				}
				
				for (int d = 0; d < 8; d++) {
					int nr = cur[0] + dy[d];
					int nc = cur[1] + dx[d];
					
					if(nr < 0 || nc < 0 || nr >= l || nc >= l)
						continue;
					
					if(visited[nr][nc])
						continue;
					
					visited[nr][nc] = true;
					
					q.offer(new int[] {nr, nc, cur[2] + 1});
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}

}
