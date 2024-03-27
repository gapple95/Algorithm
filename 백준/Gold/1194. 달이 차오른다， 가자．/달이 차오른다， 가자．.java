import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1194 달이 차오른다, 가자
 * 
 * 메모리 
 * 시간 
 * 
 */

public class Main {

	static class Minsik {
		int r, c;
		int Keys;
		int range;

		public Minsik(int r, int c, int Keys, int range) {
			this.r = r;
			this.c = c;
			this.Keys = Keys;
			this.range = range;
		}

		public boolean isKey(int door) {
			for (int i = 0; i < 6; i++) {
				if((Keys & 1 << (door - 'A')) != 0)
					return true;
			}
			return false;
		}

		public int getKey(int key) {
			key = 1 << (key - 'a');
			return Keys | key;
		}

		public int getR() {
			return r;
		}

		public void setR(int r) {
			this.r = r;
		}

		public int getC() {
			return c;
		}

		public void setC(int c) {
			this.c = c;
		}

		public int getKeys() {
			return Keys;
		}

		public void setKeys(int keys) {
			Keys = keys;
		}

		public int getRange() {
			return range;
		}

		public void setRange(int range) {
			this.range = range;
		}
		
		
	}

	static int[][] map;
	static boolean[][][] visited;
	static int N, M, startX, startY, endX, endY;
	static int min;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;

		map = new int[N][M];
		visited = new boolean[N][M][64];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char ch = str.charAt(j);
				map[i][j] = ch;
				if (ch == '0') {
					startX = j;
					startY = i;
				} else if (ch == '1') {
					endX = j;
					endY = i;
				}
			}
		}

		Minsik minsik = new Minsik(startY, startX, 0, 0);
		visited[startY][startX][0] = true;

		bfs(minsik);

		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	public static void bfs(Minsik minsik) {
		Queue<Minsik> q = new ArrayDeque<>();
		q.offer(minsik);

		while (!q.isEmpty()) {
			Minsik cur = q.poll();
			
			if(map[cur.r][cur.c] == '1') {
//				System.out.println(cur.range);
				min = Math.min(min, cur.range);
				continue;
			}
			
			for (int d = 0; d < dx.length; d++) {
				int nr = cur.r + dy[d];
				int nc = cur.c + dx[d];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				
				if(visited[nr][nc][cur.Keys])
					continue;

				switch (map[nr][nc]) {

				// 벽을 만나면 못간다.
				case '#': {
					continue;
				}
				
				// 키
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
					visited[nr][nc][cur.Keys] = true; 
					q.offer(new Minsik(nr, nc, cur.getKey(map[nr][nc]), cur.getRange()+1));
					break;
					
				// 문
					// 키
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
					// true 키가 있다.
					if(cur.isKey(map[nr][nc])) {
						visited[nr][nc][cur.Keys] = true; 
						q.offer(new Minsik(nr, nc, cur.getKeys(), cur.getRange()+1));
					}
					// 키가 없다
					else {
						// 갈 수없다.
						continue;
					}
				
				// 이동
				case '.':
				case '0':
				case '1':
					visited[nr][nc][cur.Keys] = true; 
					q.offer(new Minsik(nr, nc, cur.getKeys(), cur.getRange()+1));
					break;
				}

			}
		}

		// 도착지점이면 끝
		if (minsik.r == endY && minsik.c == endX) {
			System.out.println(minsik.range);
			System.exit(0);
		}

	}

}