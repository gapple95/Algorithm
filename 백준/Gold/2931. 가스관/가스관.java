import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2931
 * 
 * 메모리 
 * 시간 
 * 
 */

public class Main {

	static int R, C, map[][], length;
	static int startR, startC, endR, endC;
	static boolean visited[][], select[][], up, down, left, right;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[] settings = { '|', '-', '1', '2', '3', '4', '+' };
	static int[] fixed;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		visited = new boolean[R][C];
		select = new boolean[R][C];

		List<int[]> dots_position = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = str.charAt(j);
				map[i][j] = ch;
				if (ch != '.')
					length++;
				
				if(ch == '+')
					length++;

				if (ch == 'M') {
					startR = i;
					startC = j;
				}

				else if (ch == 'Z') {
					endR = i;
					endC = j;
				}
			}
		}

		int nr = startR;
		int nc = startC;

		for (int d = 0; d < 4; d++) {
			if(nr+dy[d] < 0 || nc+dx[d] < 0 || nr+dy[d] >= R || nc+dx[d] >= C)
				continue;
			
			if(map[nr+dy[d]][nc+dx[d]]=='Z')
				continue;
			
			if(d==0) {
				if (map[nr + dy[d]][nc + dx[d]] == '|' || map[nr + dy[d]][nc + dx[d]] == '+' || map[nr + dy[d]][nc + dx[d]] == '1' || map[nr + dy[d]][nc + dx[d]] == '4') {
					nr = startR + dy[d];
					nc = startC + dx[d];
					break;
				}
			} else if(d==1) {
				if (map[nr + dy[d]][nc + dx[d]] == '|' || map[nr + dy[d]][nc + dx[d]] == '+' || map[nr + dy[d]][nc + dx[d]] == '2' || map[nr + dy[d]][nc + dx[d]] == '3') {
					nr = startR + dy[d];
					nc = startC + dx[d];
					break;
				}
			} else if(d==2) {
				if (map[nr + dy[d]][nc + dx[d]] == '-' || map[nr + dy[d]][nc + dx[d]] == '+' || map[nr + dy[d]][nc + dx[d]] == '1' || map[nr + dy[d]][nc + dx[d]] == '2') {
					nr = startR + dy[d];
					nc = startC + dx[d];
					break;
				}
			} else if(d==3) {
				if (map[nr + dy[d]][nc + dx[d]] == '-' || map[nr + dy[d]][nc + dx[d]] == '+' || map[nr + dy[d]][nc + dx[d]] == '3' || map[nr + dy[d]][nc + dx[d]] == '4') {
					nr = startR + dy[d];
					nc = startC + dx[d];
					break;
				}
			}
			
		}

		int[] start = new int[] { nr, nc, startR, startC, 1 };

		bfs(start);

	}

	public static void bfs(int[] start) {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(start);

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			int nr = cur[0];
			int nc = cur[1];
			int d = -1;

			if (cur[0] == endR && cur[1] == endC && cur[4] == length) {
				System.out.println((fixed[0] + 1) + " " + (fixed[1] + 1) + " " + (char) fixed[2]);
				System.exit(0);
			}
			switch (map[cur[0]][cur[1]]) {
			case '|':
			case '-':
			case '+':
				// 진행방향 그대로 가야한다.
				d = calD(cur, '|');
				nr += dy[d];
				nc += dx[d];
				q.offer(new int[] { nr, nc, cur[0], cur[1], cur[4] + 1 });
				break;

			case '1': {
				d = calD(cur, '1');
				nr += dy[d];
				nc += dx[d];
				q.offer(new int[] { nr, nc, cur[0], cur[1], cur[4] + 1 });
				break;
			}
			case '2': {
				d = calD(cur, '2');
				nr += dy[d];
				nc += dx[d];
				q.offer(new int[] { nr, nc, cur[0], cur[1], cur[4] + 1 });
				break;
			}
			case '3': {
				d = calD(cur, '3');
				nr += dy[d];
				nc += dx[d];
				q.offer(new int[] { nr, nc, cur[0], cur[1], cur[4] + 1 });
				break;
			}
			case '4': {
				d = calD(cur, '4');
				nr += dy[d];
				nc += dx[d];
				q.offer(new int[] { nr, nc, cur[0], cur[1], cur[4] + 1 });
				break;
			}
			case '.': {
				
				if (nr+dy[0] >= 0)
					if (map[nr + dy[0]][nc + dx[0]] != '.' & (map[nr + dy[0]][nc + dx[0]] == '|' || map[nr + dy[0]][nc + dx[0]] == '+' || map[nr + dy[0]][nc + dx[0]] == '1' || map[nr + dy[0]][nc + dx[0]] == '4'))
					up = true;
				if (nr+dy[1] < R)
					if (map[nr + dy[1]][nc + dx[1]] != '.' & (map[nr + dy[1]][nc + dx[1]] == '|' || map[nr + dy[1]][nc + dx[1]] == '+' || map[nr + dy[1]][nc + dx[1]] == '2' || map[nr + dy[1]][nc + dx[1]] == '3'))
					down = true;
				if (nc+dx[2] >= 0)
					if (map[nr + dy[2]][nc + dx[2]] != '.' & (map[nr + dy[2]][nc + dx[2]] == '-' || map[nr + dy[2]][nc + dx[2]] == '+' || map[nr + dy[2]][nc + dx[2]] == '1' || map[nr + dy[2]][nc + dx[2]] == '2'))
					left = true;
				if (nc+dx[3] < C)
					if (map[nr + dy[3]][nc + dx[3]] != '.' & (map[nr + dy[3]][nc + dx[3]] == '-' || map[nr + dy[3]][nc + dx[3]] == '+' || map[nr + dy[3]][nc + dx[3]] == '3' || map[nr + dy[3]][nc + dx[3]] == '4'))
					right = true;

				char ch = 0;
				if (up & down & !left & !right)
					ch = '|';
				else if (!up & !down & left & right)
					ch = '-';
				else if (up & down & left & right) {
					ch = '+';
					length++;
				}
				else if (!up & down & !left & right)
					ch = '1';
				else if (up & !down & !left & right)
					ch = '2';
				else if (up & !down & left & !right)
					ch = '3';
				else if (!up & down & left & !right)
					ch = '4';

				map[nr][nc] = ch;
				q.offer(cur);
				fixed = new int[] { nr, nc, ch };
				break;
			}
			}
		}
	}

	public static int calD(int[] cur, char pipe) {
		if (pipe == '|') {
			// nr > or
			if (cur[0] < cur[2])
				return 0; // 상
			// nr < or
			else if (cur[0] > cur[2])
				return 1; // 하
			// nc > oc
			else if (cur[1] > cur[3])
				return 3; // 우
			// nc < oc
			else if (cur[1] < cur[3])
				return 2; // 좌
		} else {
			if (pipe == '1') {
				// nr < or
				if (cur[0] < cur[2])
					return 3;
				// nc < oc
				else if (cur[1] < cur[3])
					return 1;
			} else if (pipe == '2') {
				// nr > or
				if (cur[0] > cur[2])
					return 3;
				// nc < oc
				else if (cur[1] < cur[3])
					return 0;
			} else if (pipe == '3') {
				// nr > or
				if (cur[0] > cur[2])
					return 2;
				// nc > oc
				else if (cur[1] > cur[3])
					return 0;
			} else if (pipe == '4') {
				// nr < or
				if (cur[0] < cur[2])
					return 2;
				// nc > oc
				else if (cur[1] > cur[3])
					return 1;
			}
		}

		return -1;
	}
}