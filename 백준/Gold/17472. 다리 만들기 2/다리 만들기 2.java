import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, map[][], adj[][];
	static List<int[]> island;
	static boolean visited[][];
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		island = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		map = upDateMap(map);
//		printMap(map);
		makeBridge(map);
		sb.append(prim(adj));
		
		System.out.println(sb);

	}
	
	public static int prim(int[][] adj) {
		boolean[] selected = new boolean[adj.length];
		int[] minEdge = new int[adj.length];
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1[1], o2[1]);
		});
		q.offer(new int[] {1, 0}); // 정점인덱스, weight <=  초기값
		
		
		int result = 0;
		int c = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 이미 들린 정점이면 스킵
			if(selected[cur[0]])
				continue;
			
			result += cur[1];
			selected[cur[0]] = true;
			
			// 트리 정점을 들릴때마다 카운팅
			if(++c == adj.length-1) break;

			
			for (int i = 1; i < adj.length; i++) {
				if(!selected[i] && adj[cur[0]][i] != 0 && adj[cur[0]][i] < minEdge[i]) {
					minEdge[i] = adj[cur[0]][i]; // 최솟값 갱신
					// 업데이트된 트리 값을 큐에 넣기
					q.offer(new int[] {i, minEdge[i]});
				}
			}
		}
		
//		System.out.println(Arrays.toString(minEdge));
//		System.out.println(c);
//		System.out.println(result);
		
		// 현재 배열은 0번도 포함하기 때문에 전체 길이에서 -1을 해야한다.
		// 만약에 안 들리고 먼저 나온다? -> 전체 순환이 안되기 때문에 -1 출력
		return c==adj.length-1?result:-1;
	}

	public static void makeBridge(int[][] map) {
		visited = new boolean[N][M];
		for (int i = 1; i <= island.size(); i++) {
			int[] cur = island.get(i-1);
			findIsland(cur[0], cur[1], i);
		}

//		printMap(adj);
	}

	public static void findIsland(int i, int j, int island) {
		A: for (int d = 0; d < 4; d++) {
			int nr = i + dy[d];
			int nc = j + dx[d];

			if (!isRange(nr, nc))
				continue;

			// 물을 만나게되면 이제부터 다리 길이를 측정!
			if (map[nr][nc] == 0) {
				int dis_r = nr;
				int dis_c = nc;
				int dis = 0;
				while (map[dis_r][dis_c] == 0) {
					dis_r += dy[d];
					dis_c += dx[d];
					if (!isRange(dis_r, dis_c))
						continue A;
					dis++;
				}
				// 다른 섬을 만날 때,
				// 거리가 1인 경우도 제외를 해야한다.
				// 인접행렬에 넣어준다.
				// 비교해서 더 다리가 짧으면 교환
				if (dis == 1)
					continue A;

				if (adj[map[dis_r][dis_c]][island] > dis) {
					adj[map[dis_r][dis_c]][island] = dis;
					adj[island][map[dis_r][dis_c]] = dis;
				} else if(adj[map[dis_r][dis_c]][island] == 0) {
					adj[map[dis_r][dis_c]][island] = dis;
					adj[island][map[dis_r][dis_c]] = dis;
				}
				continue A;
			}

			if (visited[nr][nc])
				continue;
			visited[nr][nc] = true;

			findIsland(nr, nc, island);
		}
	}

	public static int[][] upDateMap(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					map[i][j] = ++cnt;
					dfs(i, j, cnt);
					island.add(new int[] { i, j });
				}
			}
		}

		adj = new int[cnt+1][cnt+1];

		return map;
	}

	public static void dfs(int i, int j, int n) {
		for (int d = 0; d < 4; d++) {
			int nr = i + dy[d];
			int nc = j + dx[d];

			if (!isRange(nr, nc))
				continue;

			if (map[nr][nc] == 0)
				continue;

			if (visited[nr][nc])
				continue;
			visited[nr][nc] = true;

			map[nr][nc] = n;
			dfs(nr, nc, n);
		}
	}

	public static boolean isRange(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}

	public static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}