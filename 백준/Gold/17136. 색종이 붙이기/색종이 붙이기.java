import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
 * 색종이 붙이기
 * 
 * 메모리 
 * 시간 
 * 
 * 색종이
 * 1x1, 2x2, 3x3, 4x4, 5x5가 각각 5개씩있다.
 * 
 * 10x10이 종이가 있고, 1을 최소 종이로 다 덮을수 있는 색종이 갯수를 출력
 * 
 * 그리고 1을 정확히 덮어야한다. 0에 닿으면 안된다!
 * 
 * @입력
 * 10x10
 * 
 * @출력
 * 최소 색종이 갯수, 만약 안되면 -1
 * 
 * @해결방안
 * 왼쪽위부터 천천히 탐색해나가면서 붙일수있으면 붙이자
 * 그리디하게 접근.
 * 
 * selected를 이용하여 가능한곳 찾기?
 * 
 * 1의 좌표들을 list로 저장
 * 
 * 그리디하게 접근하면 비효율적으로 가는 경우가 생김.
 * 
 * 모두 돌아서 가능한 색종이의 최대 크기를 먼저 선정하고,
 * 큰 색종이부터 다 붙이고 뒤에 생각하기.
 * 
 * 먼저 하니 안되는 반례가 존재한다.
 * 
 * DFS로 채워보고 안되면, 나와서 다른종이를 붙이는 백트랙킹이 답인듯하다.
 * 
 */

public class Main {

	static int map[][], one, two, three, four, five, answer, cnt;
	static List<int[]> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		one = 5;
		two = 5;
		three = 5;
		four = 5;
		five = 5;

		answer = Integer.MAX_VALUE;

		map = new int[10][10];

		list = new ArrayList<>();
		int tmp;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp == 1) {
					list.add(new int[] { i, j });
					cnt++;
				}
			}
		}

		dfs(0);

		// 25개에서 각각 빼준값이 최종값
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	// 재귀가 할것이 무엇인지? 명확하게
	// 재귀함수는 1x1, 2x2 ~ 5x5를 선택만 해주자.
	public static void dfs(int idx) {
		if (cnt == 0) {
			// 모든 케이스가 완료됨
			answer = Math.min(answer, 25 - (one + two + three + four + five));
			return;
		}

		if (idx == list.size())
			return;
		
		

		int[] cur = list.get(idx);
		
		if(map[cur[0]][cur[1]] == 0)
			dfs(idx + 1);

		// 5채우기
		if (five(cur)) {
			fillFive(cur, true);
//			printMap(map);
			dfs(idx + 1);
			fillFive(cur, false);
		}

		// 4채우기
		if (four(cur)) {
			fillFour(cur, true);
//			printMap(map);
			dfs(idx + 1);
			fillFour(cur, false);
		}

		// 3채우기
		if (three(cur)) {
			fillThree(cur, true);
//			printMap(map);
			dfs(idx + 1);
			fillThree(cur, false);
		}

		// 2채우기
		if (two(cur)) {
			fillTwo(cur, true);
//			printMap(map);
			dfs(idx + 1);
			fillTwo(cur, false);
		}

		// 1채우기
		if (one(cur)) {
			fillOne(cur, true);
//			printMap(map);
			dfs(idx + 1);
			fillOne(cur, false);
		}

	}

	public static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void fillOne(int[] cur, boolean fill) {

		// 크기가 1인 색종이
		if (fill) {
			map[cur[0]][cur[1]] = 0;
			one--;
			cnt--;
		} else {
			map[cur[0]][cur[1]] = 1;
			one++;
			cnt++;
		}

	}

	public static void fillTwo(int[] cur, boolean fill) {

		if (fill) {
			for (int i = cur[0]; i < cur[0] + 2; i++) {
				for (int j = cur[1]; j < cur[1] + 2; j++) {
					map[i][j] = 0;
					cnt--;
				}
			}
			two--;
		} else {
			for (int i = cur[0]; i < cur[0] + 2; i++) {
				for (int j = cur[1]; j < cur[1] + 2; j++) {
					map[i][j] = 1;
					cnt++;
				}
			}
			two++;
		}
	}

	public static void fillThree(int[] cur, boolean fill) {

		if (fill) {
			for (int i = cur[0]; i < cur[0] + 3; i++) {
				for (int j = cur[1]; j < cur[1] + 3; j++) {
					map[i][j] = 0;
					cnt--;
				}
			}
			three--;
		} else {
			for (int i = cur[0]; i < cur[0] + 3; i++) {
				for (int j = cur[1]; j < cur[1] + 3; j++) {
					map[i][j] = 1;
					cnt++;
				}
			}
			three++;
		}
	}

	public static void fillFour(int[] cur, boolean fill) {

		if (fill) {
			for (int i = cur[0]; i < cur[0] + 4; i++) {
				for (int j = cur[1]; j < cur[1] + 4; j++) {
					map[i][j] = 0;
					cnt--;
				}
			}
			four--;
		} else {
			for (int i = cur[0]; i < cur[0] + 4; i++) {
				for (int j = cur[1]; j < cur[1] + 4; j++) {
					map[i][j] = 1;
					cnt++;
				}
			}
			four++;
		}
	}

	public static void fillFive(int[] cur, boolean fill) {
		if (fill) {
			for (int i = cur[0]; i < cur[0] + 5; i++) {
				for (int j = cur[1]; j < cur[1] + 5; j++) {
					map[i][j] = 0;
					cnt--;
				}
			}
			five--;
		} else {
			for (int i = cur[0]; i < cur[0] + 5; i++) {
				for (int j = cur[1]; j < cur[1] + 5; j++) {
					map[i][j] = 1;
					cnt++;
				}
			}
			five++;
		}
	}

	public static boolean one(int[] cur) {

		if (one == 0)
			return false;
		
		if (map[cur[0]][cur[1]] == 0)
			return false;

		return true;
	}

	public static boolean two(int[] cur) {

		if (two == 0)
			return false;

		if (cur[0] + 1 >= 10 || cur[1] + 1 >= 10)
			return false;

		for (int i = cur[0]; i < cur[0] + 2; i++) {
			for (int j = cur[1]; j < cur[1] + 2; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static boolean three(int[] cur) {

		if (three == 0)
			return false;

		if (cur[0] + 2 >= 10 || cur[1] + 2 >= 10)
			return false;

		for (int i = cur[0]; i < cur[0] + 3; i++) {
			for (int j = cur[1]; j < cur[1] + 3; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static boolean four(int[] cur) {

		if (four == 0)
			return false;

		if (cur[0] + 3 >= 10 || cur[1] + 3 >= 10)
			return false;

		for (int i = cur[0]; i < cur[0] + 4; i++) {
			for (int j = cur[1]; j < cur[1] + 4; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static boolean five(int[] cur) {

		if (five == 0)
			return false;

		if (cur[0] + 4 >= 10 || cur[1] + 4 >= 10)
			return false;

		for (int i = cur[0]; i < cur[0] + 5; i++) {
			for (int j = cur[1]; j < cur[1] + 5; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

}