/*

13460 구슬 탈출2

보드의 세로크기 N, 가로크기 M, 1x1로 나뉨
가장 바깥 행과 열은 모두 막혀져있음. 구멍은 하나
빨간 구슬과 파란구슬도 1x1크기. 둘은 겹치지 않음
빨간 구슬만 구멍을 통해 빼내는 게임. 파란구슬은 구멍으로 가면 안됨

중력으로 왼쪽, 오른쪽, 위쪽, 아래쪽으로 기울이는 네가지 동작
파란 구슬이 빠져도 실패. 둘이 동시에 구멍에 빠져도 실패. => 파란구슬이 빠지면 무조건 실패
기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 떄까지.
즉, 한번 기울이면 끝까지 간다.

보드의 상태가 주어졌을 떄, 최소 몇 번만에 빨간 구슬을 구멍을 통해
빼낼 수 있는지 구하는 프로그램 작성

@입력
첫째줄에 보드의 세로, 가로 크기 N,M(3<=N, M<=10)
다음 N개의 줄에 보드 모양을 나타내는 길이 M의 문자열이 주어진다.
문자열은 빈칸 ., 벽 #, 구멍 O, 빨강구슬 R, 파랑구슬 B
입력에 있어서 모든 보드의 가장자리는 #이 있음
구멍과 구슬들은 항상 1개

@출력
최소 몇 번만에 빨강 구슬을 구멍을 통해 빼낼 수 있는지 출력
만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 뺴낼 수 없으면
-1을 출력한다.

@풀이
입력받아서 보드를 완성 후, BFS로 시뮬레이션을 하는 방식
왼쪽, 오른쪽, 위, 아래 기울이기 기능들을 만들고, 구슬의 이동을
저장해서 완성하자.


 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int N, M, map[][], answer=-1;
	static int[] blue, red, hole;
	static boolean check = false;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		red = new int[2];
		blue = new int[2];
		hole = new int[2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			for (int j = 0; j < M; j++) {
				if (tmp.charAt(j) == 'B') {
					blue[0] = i;
					blue[1] = j;
					map[i][j] = '.';
				} else if (tmp.charAt(j) == 'R') {
					red[0] = i;
					red[1] = j;
					map[i][j] = '.';
				} else if (tmp.charAt(j) == 'O') {
					hole[0] = i;
					hole[1] = j;
					map[i][j] = 'O';
				} else
					map[i][j] = tmp.charAt(j);
			}
		}

//		print();
//		print_position(red, blue);
//
//		int[] tmp = up_slope(red, blue);
//
//		red[0] = tmp[0];
//		red[1] = tmp[1];
//		blue[0] = tmp[2];
//		blue[1] = tmp[3];
//		
//		tmp = left_slope(red, blue);
//
//		red[0] = tmp[0];
//		red[1] = tmp[1];
//		blue[0] = tmp[2];
//		blue[1] = tmp[3];
//
//		print_position(red, blue);

		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { red[0], red[1], blue[0], blue[1], 0, 0 });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int[] cur_red = { cur[0], cur[1] }, cur_blue = { cur[2], cur[3] };
			int[] tmp = null;

			// 움직임이 총 10번을 넘어가면 스킵
			if (cur[5] == 10)
				continue;

			// 이전 방향이 위나 아래였다면
			if (cur[4] == 1 || cur[4] == 2) {
				// 왼쪽과 오른쪽만 보내야한다.
				tmp = left_slope(cur_red, cur_blue);
				if((tmp[0] != -1 && tmp[1] != -1) && (tmp[2] != -1 && tmp[3] != -1) && !(tmp[0] == cur_red[0] && tmp[1] == cur_red[1] && tmp[2] == cur_blue[0] && tmp[3] == cur_blue[1]))
					q.offer(new int[] { tmp[0], tmp[1], tmp[2], tmp[3], 3, cur[5] + 1 });
				// 빨간 구슬만 구멍에 도착했을 때
				if(tmp != null && (tmp[0] == -1 && tmp[1] == -1) && (tmp[2] != -1 && tmp[3] != -1)) {
					answer = cur[5] + 1;
					break;
				}
				tmp = right_slope(cur_red, cur_blue);
				if((tmp[0] != -1 && tmp[1] != -1) && (tmp[2] != -1 && tmp[3] != -1) && !(tmp[0] == cur_red[0] && tmp[1] == cur_red[1] && tmp[2] == cur_blue[0] && tmp[3] == cur_blue[1]))
					q.offer(new int[] { tmp[0], tmp[1], tmp[2], tmp[3], 4, cur[5] + 1 });
				// 빨간 구슬만 구멍에 도착했을 때
				if(tmp != null && (tmp[0] == -1 && tmp[1] == -1) && (tmp[2] != -1 && tmp[3] != -1)) {
					answer = cur[5] + 1;
					break;
				}
			}
			// 이전 방향이 왼쪽이나 오른쪽이였다면
			else if (cur[4] == 3 || cur[4] == 4) {
				// 위와 아래만 보내야한다.
				tmp = up_slope(cur_red, cur_blue);
				if((tmp[0] != -1 && tmp[1] != -1) && (tmp[2] != -1 && tmp[3] != -1) && !(tmp[0] == cur_red[0] && tmp[1] == cur_red[1] && tmp[2] == cur_blue[0] && tmp[3] == cur_blue[1]))
					q.offer(new int[] { tmp[0], tmp[1], tmp[2], tmp[3], 1, cur[5] + 1 });
				// 빨간 구슬만 구멍에 도착했을 때
				if(tmp != null && (tmp[0] == -1 && tmp[1] == -1) && (tmp[2] != -1 && tmp[3] != -1)) {
					answer = cur[5] + 1;
					break;
				}
				tmp = down_slope(cur_red, cur_blue);
				if((tmp[0] != -1 && tmp[1] != -1) && (tmp[2] != -1 && tmp[3] != -1) && !(tmp[0] == cur_red[0] && tmp[1] == cur_red[1] && tmp[2] == cur_blue[0] && tmp[3] == cur_blue[1]))
					q.offer(new int[] { tmp[0], tmp[1], tmp[2], tmp[3], 2, cur[5] + 1 });
				// 빨간 구슬만 구멍에 도착했을 때
				if(tmp != null && (tmp[0] == -1 && tmp[1] == -1) && (tmp[2] != -1 && tmp[3] != -1)) {
					answer = cur[5] + 1;
					break;
				}
			}
			// 맨 처음 시작은 상, 하, 좌, 우를 넣고 시작.
			else {
				tmp = up_slope(cur_red, cur_blue);
				if((tmp[0] != -1 && tmp[1] != -1) && (tmp[2] != -1 && tmp[3] != -1) && !(tmp[0] == cur_red[0] && tmp[1] == cur_red[1] && tmp[2] == cur_blue[0] && tmp[3] == cur_blue[1]))
					q.offer(new int[] { tmp[0], tmp[1], tmp[2], tmp[3], 1, cur[5] + 1 });
				// 빨간 구슬만 구멍에 도착했을 때
				if(tmp != null && (tmp[0] == -1 && tmp[1] == -1) && (tmp[2] != -1 && tmp[3] != -1)) {
					answer = cur[5] + 1;
					break;
				}
				tmp = down_slope(cur_red, cur_blue);
				if((tmp[0] != -1 && tmp[1] != -1) && (tmp[2] != -1 && tmp[3] != -1) && !(tmp[0] == cur_red[0] && tmp[1] == cur_red[1] && tmp[2] == cur_blue[0] && tmp[3] == cur_blue[1]))
					q.offer(new int[] { tmp[0], tmp[1], tmp[2], tmp[3], 2, cur[5] + 1 });
				// 빨간 구슬만 구멍에 도착했을 때
				if(tmp != null && (tmp[0] == -1 && tmp[1] == -1) && (tmp[2] != -1 && tmp[3] != -1)) {
					answer = cur[5] + 1;
					break;
				}
				tmp = left_slope(cur_red, cur_blue);
				if((tmp[0] != -1 && tmp[1] != -1) && (tmp[2] != -1 && tmp[3] != -1) && !(tmp[0] == cur_red[0] && tmp[1] == cur_red[1] && tmp[2] == cur_blue[0] && tmp[3] == cur_blue[1]))
					q.offer(new int[] { tmp[0], tmp[1], tmp[2], tmp[3], 3, cur[5] + 1 });
				// 빨간 구슬만 구멍에 도착했을 때
				if(tmp != null && (tmp[0] == -1 && tmp[1] == -1) && (tmp[2] != -1 && tmp[3] != -1)) {
					answer = cur[5] + 1;
					break;
				}
				tmp = right_slope(cur_red, cur_blue);
				if((tmp[0] != -1 && tmp[1] != -1) && (tmp[2] != -1 && tmp[3] != -1) && !(tmp[0] == cur_red[0] && tmp[1] == cur_red[1] && tmp[2] == cur_blue[0] && tmp[3] == cur_blue[1]))
					q.offer(new int[] { tmp[0], tmp[1], tmp[2], tmp[3], 4, cur[5] + 1 });
				// 빨간 구슬만 구멍에 도착했을 때
				if(tmp != null && (tmp[0] == -1 && tmp[1] == -1) && (tmp[2] != -1 && tmp[3] != -1)) {
					answer = cur[5] + 1;
					break;
				}
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
	}

	public static int[] left_slope(int[] red, int[] blue) {
		int[] red_result = new int[2];
		int[] blue_result = new int[2];

		// 빨간 구슬이 더 왼쪽에 있을 경우
		if (red[1] < blue[1]) {
			// 먼저 빨강이 움직이고 파랑이 움직임
			red_result = left_move(red, blue);
			blue_result = left_move(blue, red_result);
		}
		// 파랑 구슬이 더 왼쪽에 있을 경우
		else {
			// 먼저 파랑이 움직이고 빨강이 움직임
			blue_result = left_move(blue, red);
			red_result = left_move(red, blue_result);
		}

		return new int[] { red_result[0], red_result[1], blue_result[0], blue_result[1] };
	}

	public static int[] right_slope(int[] red, int[] blue) {
		int[] red_result = new int[2];
		int[] blue_result = new int[2];

		// 빨간 구슬이 더 오른쪽에 있을 경우
		if (red[1] > blue[1]) {
			// 먼저 빨강이 움직이고 파랑이 움직임
			red_result = right_move(red, blue);
			blue_result = right_move(blue, red_result);
		}
		// 파랑 구슬이 더 오른쪽에 있을 경우
		else {
			// 먼저 파랑이 움직이고 빨강이 움직임
			blue_result = right_move(blue, red);
			red_result = right_move(red, blue_result);
		}

		return new int[] { red_result[0], red_result[1], blue_result[0], blue_result[1] };
	}

	public static int[] up_slope(int[] red, int[] blue) {
		int[] red_result = new int[2];
		int[] blue_result = new int[2];

		// 빨간 구슬이 더 위쪽에 있을 경우
		if (red[0] < blue[0]) {
			// 먼저 빨강이 움직이고 파랑이 움직임
			red_result = up_move(red, blue);
			blue_result = up_move(blue, red_result);
		}
		// 파랑 구슬이 더 위쪽에 있을 경우
		else {
			// 먼저 파랑이 움직이고 빨강이 움직임
			blue_result = up_move(blue, red);
			red_result = up_move(red, blue_result);
		}

		return new int[] { red_result[0], red_result[1], blue_result[0], blue_result[1] };
	}

	public static int[] down_slope(int[] red, int[] blue) {
		int[] red_result = new int[2];
		int[] blue_result = new int[2];

		// 빨간 구슬이 더 위쪽에 있을 경우
		if (red[0] > blue[0]) {
			// 먼저 빨강이 움직이고 파랑이 움직임
			red_result = down_move(red, blue);
			blue_result = down_move(blue, red_result);
		}
		// 파랑 구슬이 더 위쪽에 있을 경우
		else {
			// 먼저 파랑이 움직이고 빨강이 움직임
			blue_result = down_move(blue, red);
			red_result = down_move(red, blue_result);
		}

		return new int[] { red_result[0], red_result[1], blue_result[0], blue_result[1] };
	}

	public static int[] left_move(int[] bead_target, int[] bead_disrupt) {
		int[] bead_result = new int[] { bead_target[0], bead_target[1] };
		while (true) {
			if (bead_result[1] <= 1)
				break;
			if (bead_result[0] == bead_disrupt[0] && bead_result[1] - 1 == bead_disrupt[1])
				break;
			if (map[bead_result[0]][bead_result[1] - 1] == '#')
				break;
			bead_result[1]--;

			// 구슬이 구멍에 도착하면 끝내기
			if (bead_result[0] == hole[0] && bead_result[1] == hole[1]) {
				check = true;
				bead_result[0] = -1;
				bead_result[1] = -1;
				break;
			}
		}
		return bead_result;
	}

	public static int[] right_move(int[] bead_target, int[] bead_disrupt) {
		int[] bead_result = new int[] { bead_target[0], bead_target[1] };
		while (true) {
			if (bead_result[1] >= M - 2)
				break;
			if (bead_result[0] == bead_disrupt[0] && bead_result[1] + 1 == bead_disrupt[1])
				break;
			if (map[bead_result[0]][bead_result[1] + 1] == '#')
				break;
			bead_result[1]++;

			// 구슬이 구멍에 도착하면 끝내기
			if (bead_result[0] == hole[0] && bead_result[1] == hole[1]) {
				check = true;
				bead_result[0] = -1;
				bead_result[1] = -1;
				break;
			}
		}
		return bead_result;
	}

	public static int[] up_move(int[] bead_target, int[] bead_disrupt) {
		int[] bead_result = new int[] { bead_target[0], bead_target[1] };
		while (true) {
			if (bead_result[0] <= 1)
				break;
			if (bead_result[1] == bead_disrupt[1] && bead_result[0] - 1 == bead_disrupt[0])
				break;
			if (map[bead_result[0] - 1][bead_result[1]] == '#')
				break;
			bead_result[0]--;

			// 구슬이 구멍에 도착하면 끝내기
			if (bead_result[0] == hole[0] && bead_result[1] == hole[1]) {
				check = true;
				bead_result[0] = -1;
				bead_result[1] = -1;
				break;
			}
		}
		return bead_result;
	}

	public static int[] down_move(int[] bead_target, int[] bead_disrupt) {
		int[] bead_result = new int[] { bead_target[0], bead_target[1] };
		while (true) {
			if (bead_result[0] >= N - 2)
				break;
			if (bead_result[1] == bead_disrupt[1] && bead_result[0] + 1 == bead_disrupt[0])
				break;
			if (map[bead_result[0] + 1][bead_result[1]] == '#')
				break;
			bead_result[0]++;

			// 구슬이 구멍에 도착하면 끝내기
			if (bead_result[0] == hole[0] && bead_result[1] == hole[1]) {
				check = true;
				bead_result[0] = -1;
				bead_result[1] = -1;
				break;
			}
		}
		return bead_result;
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print((char) map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void print_position(int[] red, int[] blue) {
		System.out.println("빨간 구슬 : (" + red[0] + "," + red[1] + ")");
		System.out.println("파란 구슬 : (" + blue[0] + "," + blue[1] + ")");
	}
}
