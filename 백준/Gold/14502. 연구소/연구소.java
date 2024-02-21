import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 14502 연구소
 * 
 * 메모리
 * 시간
 * 
 * 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고한다.
 * 연구소는 크기가 NxM인 직사각형으로 나타낼 수 있으며, 직사각형은 1x1 크기의
 * 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 
 * 칸 하나를 가득 차지한다.
 * 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈칸으로
 * 모두 퍼져나갈수 있다.
 * 새로 세울 수 있는 벽의 개수는 3개이면, 꼭 3개를 세워야한다.
 * 
 * @입력
 * 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다.(3<=N,M<=8)
 * 둘째줄부터 N개의 줄에 빈칸 0, 벽 1, 바이러스 2(2<= 바이러스 <=10)
 * 빈칸의 개수는 3개이상
 * 
 * @출력
 * 첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.
 * 
 * 
 * @해결방안
 * 비어있는 칸중에 벽을 3개 세울 수 있는 모든 경우의 수를 구한다.
 * 그리고 해당 경우의수를 적용한 맵을 다시 구성해 BFS를 사용.
 * 
 * 시간복잡도는  전체 경우의수를 게산 64_C_3 과
 * 전체를 탐색해야하므로 N*M
 * 
 */

public class Main {

	static int N,M;
	static int[][] map, tmp_map;
	static int[][] picked;
	static boolean[][] visited;
	static int max = Integer.MIN_VALUE;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		tmp_map = new int[N][M];
		picked = new int[3][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 				
			}
		}
		
		comb(0,0);
		
		System.out.println(max);
	}
	
	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void comb(int idx, int start) {
		if(idx==3) {
			int cnt = 0;
			// 3개를 제대로 놓았을 때,
//			System.out.println(Arrays.deepToString(picked));
			
			// visited초기화
			visited = new boolean[N][M];
			
			// 맵 복사
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, tmp_map[i], 0, M);
			}
			
			// 맵에 경우의 수 넣기
			for(int[] pos : picked) {
				tmp_map[pos[0]][pos[1]] = 1; 
			}
			
//			print(map);
//			print(tmp_map);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(tmp_map[i][j] == 2)
						bfs(i,j);
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(tmp_map[i][j] == 0)
						cnt++;
				}
			}
			
			if(max < cnt)
				max = cnt;
			
			return;
		}
			
		for(int i=start; i<N*M; i++) {
			int r = i / M; // 행
			int c = i % M; // 열
			//요소 뽑기
			if(map[r][c] != 0)
				continue;
			picked[idx][0] = r;
			picked[idx][1] = c;
			comb(idx+1, i+1);
		}
	}
	
	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i,j});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d=0; d<4; d++) {
				int x = cur[1] + dx[d];
				int y = cur[0] + dy[d];
				if(x<0 || x>=M || y<0 || y>=N)
					continue;
				if(tmp_map[y][x] != 0)
					continue;
				if(visited[y][x])
					continue;
				visited[y][x] = true;
				tmp_map[y][x] = 2;
				q.offer(new int[] {y,x});
			}
		}
	}
}