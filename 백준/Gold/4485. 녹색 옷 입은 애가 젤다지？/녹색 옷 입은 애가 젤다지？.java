import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 백준 4485 녹색 옷 입은 애가 젤다지?
 * 
 * 메모리 
 * 시간 
 * 
 * N*N 크기의 동굴 제일 왼쪽 위에 있다.
 * 제일 오른쪽 아래 칸으로 이동해야한다.
 * 
 * 도둑루피가 있는데 도둑루피의 크기만큼 소지금을 잃게 된다.
 * 잃는 금액을 최소로 하여 동굴 건너편가지 이동해야 하며, 한 번에
 * 상하좌우 인접한곳으로 1칸씩 이동
 * 
 * 그렇다면 잃을 수 박에 없는 최소 금액은?
 * 
 * @입력
 * 여러 테스트 케이스로 이루어져 있다.
 * 
 * 각 테스트 케이스의 첫째 줄에는 동굴의 크기 나타내는 정수 N(2~125) N=0이면
 * 입력이 종료
 * N개에 걸쳐 도둑루피의 크기가 공백으로 구분되어 주어진다. k는 0~9
 * 
 * @출력
 * 각테스트 케이스마다 정답 출력
 * 
 * @해결방안
 * 들렸던 곳은 가면 안되니, visited 사용
 * dfs로 모든 경로를 들리며 최소의 값을 뽑아내자
 * 
 */

public class Main {

	static int N, map[][], min, dp[][];
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			sb.append("Problem ").append(T++).append(": ");

			map = new int[N][N];
			dp = new int[N][N];
			visited = new boolean[N][N];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

//			visited[0][0] = true;
//			dfs(0, 0, map[0][0]);
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			dp[0][0] = map[0][0];
			bfs(0, 0);
//			printMap(dp);
			sb.append(dp[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}
	
	static void printMap(int[][] map) {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
//			if (visited[cur[0]][cur[1]])
//				continue;
//			visited[cur[0]][cur[1]] = true;
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dy[d];
				int nc = cur[1] + dx[d];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				
				if(dp[cur[0]][cur[1]] + map[nr][nc] < dp[nr][nc]) {
					dp[nr][nc] = dp[cur[0]][cur[1]] + map[nr][nc];
					q.offer(new int[] {nr, nc});
				}
				
//				dp[nr][nc] = Math.min(dp[cur[0]][cur[1]] + map[nr][nc], dp[nr][nc]);
				
			}
		}
	}

	public static void dfs(int r, int c, int rupee) {
		if (r == N - 1 && c == N - 1) {
			min = Math.min(min, rupee);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dy[d];
			int nc = c + dx[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N)
				continue;
			if (visited[nr][nc])
				continue;
			visited[nr][nc] = true;
			dfs(nr,nc, rupee+map[nr][nc]);
			visited[nr][nc] = false;
		}
	}
}