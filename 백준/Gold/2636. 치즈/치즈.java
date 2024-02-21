import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2636 치즈
 * 
 * 메모리
 * 시간
 * 
 * 정사각형 칸들로 이루어진 사각형 모양의 판이 있고, 그 위로 얇은 치즈()가 놓여있다.
 * 판의 가장자리에는 치즈가 놓여있지 않으며 치즈에는 하나 이상의 구멍이 있을 수 있다.
 * 
 * 이 치즈를 공기 중에 놓으면 녹게 되는데 공기와 접촉된 칸은 한 사긴이 지나면 녹아 없어진다.
 * 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍속으로
 * 공기가 들어가게 된다. 그림을 보면 가운데에는 공기가 없어서 안녹는다.
 * 공기와 접촉한 면은 한시간뒤 사라진다.
 * 
 * 입력으로 사각형 모양의 판의 크기와 한 조각의 치즈가 판 위에 주어졌을 때, 공기 중에서
 * 치즈가 모두 녹아 없어지는 데 걸리는 시간과 모두 녹기 한 시간 전에 남아 있는 치즈조각이
 * 놓여 있는 칸의 개수를 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에는 사각형 모양판의 세로과 가로의 길이가 양의 정수로 주어진다. 최대 100
 * 그 다음줄로 가로줄의 모양이 순서대로 출력. 치즈가 없는 칸은 0, 치즈가 있는 칸은1
 * 주어지며 각 숫자 사이에는 빈칸이 하나씩 있다.
 * 
 * @출력
 * 첫째 줄에는 츠자가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고, 둘째 줄에는 모두
 * 녹기 한 시간 전에 남아 있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.
 * 
 * @해결방안
 * BFS로 공기와 닿아있는 곳을 0으로 바꾼다. 단 밖의 0과 이어지지 않는 곳은 제외한다.
 * map과 tmp_map을 계속 사용할 것이기 때문에, 완전히 사라질 때, 바로 map을 소환하여
 * 1의 갯수를 샌다.
 * 
 * 기저조건은 N*M
 * 치즈의 갯수를 계속새고, 0이되면 다된거다. 그렇다면 그전 값을 반환.
 * 먼저 값을 입력할때 치즈의 갯수를 카운트
 * 
 * do while로 구현하여 cnt == 0이면 나오게끔. answer에도 cnt를 넣어야한다.
 * 하루 돌 때마다 map을 tmp_map에서 copy
 * 
 */

public class Main {

	static int[][] map;
	static int[][] tmp_map;
	static boolean[][] visited;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		tmp_map = new int[N][M];
		
		int answer = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if(tmp == 1)
					cnt++;
			}
		}
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, tmp_map[i], 0, M);
		}

		int hour = 0;
		do {
			answer = cnt;
			// map복사
			cnt = cnt - bfs(0,0);
			for (int i = 0; i < N; i++) {
				System.arraycopy(tmp_map[i], 0, map[i], 0, M);
			}
			hour++;
//			System.out.println("cnt : " + cnt + ", hour : " + hour);
//			print(tmp_map);
//			System.exit(0);
		} while(cnt != 0);
		
		System.out.println(hour);
		System.out.println(answer);
	}

	// cnt를 꼭 return;
	public static int bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[N][M];
		visited[i][j]  = true;
		q.offer(new int[] {i,j});
		
		// 사라져 가는 치즈의 수
		int cnt=0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d=0 ;d<4; d++) {
				int x = cur[1] + dx[d];
				int y = cur[0] + dy[d];
				if(x<0 || x>=M || y<0 || y>=N)
					continue;
				if(visited[y][x])
					continue;
				visited[y][x] = true;
				if(map[y][x] == 1) {
					// 치즈가 사라져 간다.
					tmp_map[y][x] = 0;
					cnt++;
				}
				else if (map[y][x] == 0) {
					q.offer(new int[] {y,x});
				}		
			}
		}
//		System.out.println(cnt);
		return cnt;
	}
	
	public static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}