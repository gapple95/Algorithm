import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Dice {
		int one, two, three, four, five, six;

		void moveUp() {
			int tmp;
			tmp = one;
			one = five;
			five = six;
			six = two;
			two = tmp;
		}

		void moveDown() {
			int tmp;
			tmp = one;
			one = two;
			two = six;
			six = five;
			five = tmp;
		}

		void moveRight() {
			int tmp;
			tmp = one;
			one = four;
			four = six;
			six = three;
			three = tmp;
		}

		void moveLeft() {
			int tmp;
			tmp = one;
			one = three;
			three = six;
			six = four;
			four = tmp;
		}
	}

	static int[][] map;
	static int[] dx = { 0, 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		map[y][x] = 0;

		Dice dice = new Dice();
		
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			int nr, nc;
			
			nr = y + dy[cmd];
			nc = x + dx[cmd];
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= M)
				continue;
			
			y = nr;
			x = nc;
			
			switch(cmd) {
			case 1:
				dice.moveRight();
				break;
			case 2:
				dice.moveLeft();
				break;
			case 3:
				dice.moveUp();
				break;
			case 4:
				dice.moveDown();
				break;
			}
			
			if(map[y][x] == 0) {
				map[y][x] = dice.six;
			} else {
				dice.six = map[y][x];
				map[y][x] = 0;
			}

			sb.append(dice.one).append("\n");
		}

		System.out.println(sb);
		
		br.close();
	}

}
