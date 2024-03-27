/*
 * 백준 2239 스도쿠
 * 
 * 메모리
 * 시간
 * 
 * 
 * @해결방안
 * 백트래킹
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean[][] visited;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int size = 0;
	static int idx = 0;

	static List<int[]> zero = new ArrayList<>();
	static Deque<int[]> stack = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		map = new int[9][9];
		visited = new boolean[9][9];

		String str = null;
		int num = 0;
		for (int i = 0; i < 9; i++) {
			str = br.readLine();
			for (int j = 0; j < 9; j++) {
				num = str.charAt(j) - '0';
				if (num == 0) {
					zero.add(new int[] { i, j });
				} else {
					map[i][j] = num;
					visited[i][j] = true;
				}
			}
		}

		size = zero.size();

		dfs(0);
	}

	static void dfs(int cnt) {
		// 기저조건
		if (cnt == size) {
			// 모든 0들을 다 돌았다.
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		int[] cur = zero.get(cnt);
		
		int r = cur[0];
		int c = cur[1];

		for (int i = 1; i <= 9; i++) {
			boolean check1 = check_horizon(r, c, i);
			boolean check2 = check_vertical(r, c, i);
			boolean check3 = check_nine(r, c, i);
			if (check1 && check2 && check3) {
				map[r][c] = i;
				dfs(cnt + 1);
				map[r][c] = 0;
			}
		}
	}

	static boolean check_horizon(int i, int j, int val) {

		for (int k = 0; k < 9; k++) {
			// 같은 위치면 넘어가기
			if (j == k)
				continue;
			// 똑같은 값이므로 바로 탈락
			if (val == map[i][k]) {
				return false;
			}
		}

		// 모두 다른 값이므로 성공
		return true;
	}

	static boolean check_vertical(int i, int j, int val) {
		for (int k = 0; k < 9; k++) {
			// 같은 위치면 넘어가기
			if (i == k)
				continue;
			// 똑같은 값이므로 바로 탈락
			if (val == map[k][j]) {
				return false;
			}
		}

		// 모두 다른 값이므로 성공
		return true;
	}

	static boolean check_nine(int i, int j, int val) {
		int r = (i / 3) * 3;
		int c = (j / 3) * 3;

		for (int y = r; y < r + 3; y++) {
			for (int x = c; x < c + 3; x++) {
				if (i == y && j == x)
					continue;
				if (val == map[y][x]) {
					return false;
				}
			}
		}

		return true;
	}

}