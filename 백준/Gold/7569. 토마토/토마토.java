import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int M, N, H, total_tomato, ripe_tomato;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[][][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N][M];
		visited = new boolean[H][N][M];
		Deque<int[]> q = new ArrayDeque<>();

		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if (map[h][i][j] != -1) {
						// 전체 토마토 카운팅
						total_tomato++;
						if (map[h][i][j] == 1) {
							// 익은 토마토 카운팅
							// 추후 BFS 끝나고 모두 익었는지 안 익었는지 판단용
							ripe_tomato++;
							q.offer(new int[] { h, i, j });
							visited[h][i][j] = true;
						}
					}
				}
			}
		}
		
		// 만약 첨부터 모든 토마토가 익었다면 그냥 q를 없앰
		if(total_tomato == ripe_tomato)
			q.clear();

		int time = 0;
		int level = q.size();
		while (!q.isEmpty()) {
			// BFS가 한번 진행되는 척도
			// level이 0이되면 하루가 지난것을 의미
			if (level == 0) {
				time++;
				level = q.size();
				
				// 모든 토마토가 익은 상태이므로 그냥 끝내기
				if(total_tomato == ripe_tomato)
					break;					
			}
			

			int[] cur = q.poll();

			for (int d = 0; d < 6; d++) {
				int nh = cur[0], nr = cur[1], nc = cur[2];
				// 상하좌우
				if (d < 4) {
					nr = cur[1] + dy[d];
					nc = cur[2] + dx[d];
				}
				// 위
				else if (d == 4) {
					nh = cur[0] + 1;
				}
				// 아래
				else if (d == 5) {
					nh = cur[0] - 1;
				}
				
				// 범위 체크
				if(nr < 0 || nc < 0 || nr >= N || nc >= M || nh < 0 || nh >= H)
					continue;
				
				// 안 익은 토마토만 갈 수 있다!
				if(map[nh][nr][nc] != 0)
					continue;
				
				// 들렸던 곳이면 지나가기
				if(visited[nh][nr][nc])
					continue;
				
				visited[nh][nr][nc] = true;
				
				map[nh][nr][nc] = 1;
				ripe_tomato++;
				
				q.offer(new int[] {nh, nr, nc});
				
			}
			
			level--;
		}
		
		// 전체 토마토와 익은 토마토 수가 같다면, 
		if(total_tomato == ripe_tomato) {
			sb.append(time);
		}
		// 다르다면 모두 익지 못하는 상황이므로 -1 출력
		else {
			sb.append(-1);
		}
		
		System.out.println(sb);
		
		br.close();
	}

}
