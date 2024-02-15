import java.util.Scanner;

/*
 * SWEA 2806 N-Queen
 * 
 * 메모리
 * 시간
 * 
 * N값이 주어질 때, N * N 보드에 N개의 퀸을 서로 다른 두 퀸이 공격하지 못하게
 * 놓는 경우의 수를 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫번째 줄에 테스트케이스 T가 주어진다.
 * 
 * @출력
 * 각 테스트 케이스 "#t " 출력후 경우의 수를 입력 
 * 
 */

public class Solution {

	static int answer;
	static int cnt;
	static int N;
	static int[][] board;

	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int T = s.nextInt();

		for (int t = 1; t <= T; t++) {
			answer = 0;
			cnt = 0;

			N = s.nextInt();

			board = new int[N][N];
			df(0, 0, 0);
			System.out.println("#" + t + " " + cnt);
		}
	}

	public static void df(int y, int x, int n) {
		if (n == N) {
			cnt++;
//			System.out.println("y : " + y + ", x : " + x + ", n : " + n);
//			for (int i = 0; i < board.length; i++) {
//				for (int j = 0; j < board.length; j++) {
//					System.out.print(board[i][j] != 0 ? '■' : '□');
//				}
//				System.out.println();
//			}
//			System.out.println();
			return;
		}
		for (int i = y; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0)
					continue;
				act_plus(i, j);
				df(i, j, n + 1);
				act_minus(i, j);
			}
		}
		if (isFull())
			return;
	}

	// board가 꽉찼는가?
	public static boolean isFull() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	// 지나가는 모든 자리 --
	public static void act_minus(int y, int x) {
		board[y][x]--;
		for (int d = 0; d < 8; d++) {
			int val = 1;
			while (true) {
				int _y = y + (dy[d] * val);
				int _x = x + (dx[d] * val);
				if (_y < 0 || _y >= N || _x < 0 || _x >= N)
					break;
				board[_y][_x]--;
				val++;
			}
		}
	}

	// 지나가는 모든 자리 ++
	public static void act_plus(int y, int x) {
		board[y][x]++;
		for (int d = 0; d < 8; d++) {
			int val = 1;
			while (true) {
				int _y = y + (dy[d] * val);
				int _x = x + (dx[d] * val);
				if (_y < 0 || _y >= N || _x < 0 || _x >= N)
					break;
				board[_y][_x]++;
				val++;
			}
		}

	}
}