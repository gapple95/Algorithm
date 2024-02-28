import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 백준 1149 RGB거리
 * 
 * 메모리 
 * 시간 
 * 
 * 말의 움직은 체스의 나이트와 같은 이동방식을 가진다. 참고로 말은 장애물을 뛰어넘을 수 있다.
 * 하지만 원숭이는 능력이 부족해서 총 K번만 위와 같이 움직일 수 있고, 그 외에는 그냥 인접한 칸으로만 움직일 수 있다.
 * 대각선 방향은 인접한 칸에 포함되지 않는다.
 * 
 * 이제 원숭이는 머나먼 여행길을 떠난다. 격자판의 맨 왼쪽위에서 맨오른쪽 아래까지 가야한다.
 * 인접한 네바향으로 한번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다.
 * 격자판이 주어질 때 원숭이가 최소한의 동작으로 도착지점가지 갈 수 있는 방법을 알아내는 프로그램을 작성하시오.
 * 
 * 
 * @입력
 * 첫째 줄에 정수 K가 주어진다. 
 * 둘째 줄에 격자판의 가로길이 W, 세로길이 H가 주어진다.
 * 그다음 H 줄에 걸쳐 W개의 숫자가 주어지는데
 * 0은 평지
 * 1은 장애물
 * 장애물이 있는 곳으로는 이동할 수 없다.
 * 시작점과 도착점은 항상 평지이다.
 * W와H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수이다.
 * 
 * @출력
 * 첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.
 * 
 * @해결방안
 * K번까지는 말처럼 대각선, 한칸으로 이동.
 * K번이후부터는 4방
 * FloodFill 변형 문제.
 * 
 * 
 */

public class Main {

	static class horse_monkey {
		int r, c; // 현 위치
		int pr, pc; // 전 위치
		int state; // K를 몇번 했는지
		int range; // 얼마나 이동했는지

		public horse_monkey(int r, int c, int state, int range) {
			super();
			this.r = r;
			this.c = c;
			this.state = state;
			this.range = range;
		}

		public horse_monkey(int r, int c, int pr, int pc, int state, int range) {
			super();
			this.r = r;
			this.c = c;
			this.pr = pr;
			this.pc = pc;
			this.state = state;
			this.range = range;
		}

		@Override
		public String toString() {
			return "horse_monkey [r=" + r + ", c=" + c + ", pr=" + pr + ", pc=" + pc + ", state=" + state + ", range="
					+ range + "]";
		}

	}

	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;

	static int[] dx = { 0, 1, 0, -1, -1, 1, 2, 2, 1, -1, -2, -2 }; // 0~3 : 상우하좌 / 4~11 : 나이트
	static int[] dy = { -1, 0, 1, 0, -2, -2, -1, 1, 2, 2, 1, -1 };
//	static int[] dx = {0,1,0,-1}; // 0~3 : 상우하좌
//	static int[] dy = {-1,0,1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K+1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs(new horse_monkey(0, 0, 0, 0));

	}

	static void bfs(horse_monkey hs) {
		Queue<horse_monkey> q = new ArrayDeque<>();
		q.offer(hs);
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			horse_monkey cur = q.poll();

			// 기저조건
			if (cur.r == H - 1 && cur.c == W - 1) {
//				System.out.println(cur);
				System.out.println(cur.range);
				System.exit(0);
			}

			for (int d = 0; d < 12; d++) {
				int nr = cur.r + dy[d];
				int nc = cur.c + dx[d];
				if (nr < 0 || nc < 0 || nr >= H || nc >= W)
					continue;
				if (d > 3) {
					if (cur.state >= K) // 현 노드가 K번 다 뛰었다면?
						break;
					if (map[nr][nc] == 1)
						continue;
					if (visited[nr][nc][cur.state+1])
						continue;
					visited[nr][nc][cur.state+1] = true;
					q.offer(new horse_monkey(nr, nc, cur.r, cur.c, cur.state+1, cur.range + 1));
				}
				else {
					if (map[nr][nc] == 1)
						continue;
					if (visited[nr][nc][cur.state])
						continue;
					visited[nr][nc][cur.state] = true;
					q.offer(new horse_monkey(nr, nc, cur.r, cur.c, cur.state, cur.range + 1));
				}
				
//				print(0);
//				print(1);
			}
		}
		System.out.println(-1);
		System.exit(0);
	}
	

	public static void print(int K) {
		System.out.println("K : " + K);
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(visited[i][j][K]?"■":"□");
			}
			System.out.println();
		}
		System.out.println();
	}
}