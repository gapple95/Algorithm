import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<Integer> house_count;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		house_count = new PriorityQueue<>((o1, o2) -> {
			if(o1 > o2)
				return 1;
			else
				return -1;
		});
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for (int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
	
		
		Deque<int[]> q = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 집이있으면서, 한번도 방문한적이 없다면
				if(map[i][j] == 1 && !visited[i][j]) {
					// 큐에 넣고 BFS 시작
					q.offer(new int[] {i, j});
					visited[i][j] = true;
					int cnt = 1;
					
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						
						int nr, nc;
						for (int d = 0; d < 4; d++) {
							nr = cur[0] + dy[d];
							nc = cur[1] + dx[d];
							
							if(nr < 0 || nc < 0 || nr >= N || nc >= N)
								continue;
							
							if(map[nr][nc] == 0)
								continue;
							
							if(visited[nr][nc])
								continue;
							
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc});
							cnt++;
						}
					}
					
					house_count.offer(cnt);
				}
			}
		}
		
		sb.append(house_count.size()).append("\n");
		
		while(!house_count.isEmpty())
			sb.append(house_count.poll()).append("\n");
		
		System.out.println(sb);
		
		br.close();
	}

}
