import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2206 벽 부수고 이동하기
 * 
 * 메모리
 * 시간
 * 
 * NxM 행렬로 표현되는 맵이있다. 맵에서 0은 이동할 수 있는 곳, 1은 이동할 수 없는 벽이 있는곳을 나타낸다.
 * (1,1)에서 (N,M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다.
 * 최단 경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 * 
 * 만약에 이동하는 도중에 한 개의 벽을 부소고 이동하는 것이 좀더 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 * 인접한 칸은 상하좌우이다.
 * 
 * 최단경로를 구하는 프로그램을 작성
 * 
 * @입력 첫째줄에 N(1<=N<=1,000), M (1<=M<=1,000)이 주어진다. 
 * 다음 N개의 줄에 M개의 숫자로 맵이 주어진다.
 * (1,1)과 (N,M)은 항상 0이다.
 * 
 * @출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할때는 -1 출력한다.
 * 
 * @해결방안
 * 사실상 간선은 1인 그래프인 문제이다 =>BFS최단거리. 거기에 벽의 갯수만큼 없는 경우도 생각하여 풀어야한다.
 * 벽의 위치도 좌표로 기억해야하므로 hashMap을 사용.
 * BFS로 풀어서 최악의 경우 N*M 1,000 * 1,000 = 1,000,000.
 * 여기에 모든 벽들의 갯수를 한다면..? 최악의 경우 1,000,000 - 2인 경우에 터지지 않을려나..
 * 이동을 하고 주위가 1이면 -1 출력(지나온 길도 제외)
 * 
 * 모든 벽의 경우를 세는 경우는 어쩔수 없이 시간초과가 난다.
 * 
 * 벽을 이동하면서 뚫은적이 있는지 없는지를 따지고, 벽을 뚫었다면 계속해서 0만 찾아가는 방식을 도입
 * 
 */

public class Main {

	static int N, M, K, wall_cnt;
	static int max = -1;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();

		System.out.println(max);

	}

	public static void bfs() {

		Queue<int[]> q = new ArrayDeque<>(); // { y, x, 벽을 뚫었는가?, 여태 지나온거리};
		visited = new boolean[N][M][K + 1];

		q.offer(new int[] { 0, 0, 0, 1 });
		for (int i = 0; i <= K; i++) {
			visited[0][0][i] = true;
		}

		int[] cur = new int[4];
		while (!q.isEmpty()) {
			cur = q.poll();

			if (cur[0] == N - 1 && cur[1] == M - 1) { // 도착지점에 제대로 간 경우
				max = cur[3];
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dy[d];
				int nc = cur[1] + dx[d];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (cur[2] == 0) { // 벽을 아직 안 뚫었을 경우
					if (visited[nr][nc][0])
						continue;
					if (map[nr][nc] == 1) { // 벽을 만난 경우
						visited[nr][nc][cur[2] + 1] = true;
						q.offer(new int[] { nr, nc, cur[2] + 1, cur[3] + 1 }); // 벽을 뚫은 경우를 q에 삽입
					} else { // 벽을 안만난 경우
						visited[nr][nc][0] = true;
						q.offer(new int[] { nr, nc, 0, cur[3] + 1 }); // 벽을 안 뚫은 경우를 q에 삽입
					}
				} else {
					if (cur[2] < K) { // 부실수 있는 횟수 이하인가?
						if (visited[nr][nc][cur[2]])
							continue;

						if (map[nr][nc] == 0) { // 벽을 한번 이상 부신 상태로 빈칸을 만난 경우
							visited[nr][nc][cur[2]] = true;
							q.offer(new int[] { nr, nc, cur[2], cur[3] + 1 }); // 벽을 뚫은 경우를 q에 삽입
						} else { // 벽을 한번 이상 부신 상태로 벽을 만난 경우
							visited[nr][nc][cur[2]+1] = true;
							q.offer(new int[] { nr, nc, cur[2]+1, cur[3] + 1 }); // 벽을 뚫은 경우를 q에 삽입
						}
					} else { // 더이상 벽을 못 부신다면,
						if (visited[nr][nc][cur[2]])
							continue;
						if (map[nr][nc] == 0) { // 벽을 한번 이상 부신 상태로 빈칸을 만난 경우
							visited[nr][nc][cur[2]] = true;
							q.offer(new int[] { nr, nc, cur[2], cur[3] + 1 }); // 벽을 뚫은 경우를 q에 삽입
						} else { // 더 이상 부시지 못 하는 상태로 벽을 만난 경우
							continue;
						}
					}
				}
			}
		}
	}
}