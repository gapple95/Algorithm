/*
백준 3190 뱀

NxN 정사각 보드위에서 진행되고 몇몇 칸에는 사과가 놓여져 있따.
보드의 상하좌우 끝에 벽이 있다.
게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1, 뱀은 처음에 오른쪽을 향한다.

뱀은 배 초마다 이동을 하는데 다음과 같은 규칙을 따른다.
먼저 뱀은 몰길이을 늘려 머리를 다음칸에 위치시킨다.
만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.

사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라

@입력
첫째줄에 보드의 크기 N이 주어진다. (2<= N <= 100)
다음 줄에 사과의 개수 K가 주어진다. (0<= K <= 100)

다음 K개의 줄에는 사과의 위치가 주어진다. 행, 열. 사과의 위치는 모두 다르며, 1행 1열에는 사과가 없다.

다음 줄에는 뱀의 방향 변환 횟수 L이 주어진다.(1<= L <= 100)
다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데, 정수 X와 문자 C로 이루어져 있으며,
게임 시작 시간으로 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다.
X는 10,000이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.

@출력
첫째 줄에 게임이 몇 초에 끝나는지 출력

@풀이
시뮬레이션.
뱀의 몸의 위치를 기억하는게 중요한듯.
큐로? 구현한다면 어떨까? 사과를 먹는다면 큐에 넣고, 사과를 먹지 않는다면 큐에서 뺴는 방식.
아니면, 뱀의 몸을 나타내는 2차원 배열을 하나 더 만든다면? 이 방법이 뱀의 몸을 지났는지 바로 알 수 있을 듯?
덱으로, 뱀의 몸을 넣구, 2차원 배열까지 활용하자.
사과를 먹지 않았을 때는, 뒷부분을 pop하여 2차원 배열도 수정하고,
사과를 먹었을 경우, 앞부분에 push하고 2차원배열도 추가하는 방식
머리 방향은 정수값으로 static으로 선언하여 L과 D가 들어왔을때 방향값만 추가하자.
0. 상, 1.우, 2.하, 3.좌

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int map[][], handle, N, K, L, X, C, head;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean visited[][];
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Deque<int[]> snake, turn;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 2][N + 2];
		visited = new boolean[N + 2][N + 2];

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			map[i][j] = 1;
		}

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		turn = new ArrayDeque<>();
		for (int l = 0; l < L; l++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			turn.offer(new int[] { X, C });
		}

		// 뱀 셋팅
		snake = new ArrayDeque<>();
		
		head = 1;
		snake.offer(new int[] { 1, 1 });
		visited[1][1] = true;

		boolean alive = true;

		int t = 0;
		while (true) {
			// 뱀 이동
			int[] cur = snake.peek();

			int ny = cur[0] + dy[head];
			int nx = cur[1] + dx[head];

			// 맵 밖으로 나가진다면
			// 몸이 있는 곳에 간다면
			if (ny <= 0 || nx <= 0 || ny > N || nx > N || visited[ny][nx]) {
				// 게임끝
				alive = false;
				break;
			}

			// 먼저 뱀이 움직이고
			snake.offerFirst(new int[] { ny, nx }); // 머리쪽으로 큐 넣기
			visited[ny][nx] = true;

			// 만약 이동한 칸에 사과가 있다면
			// 그 칸에 있던 사과는 없어지고, 꼬리는 움직이지 않는다.
			if (map[ny][nx] == 1) {
				map[ny][nx] = 0; // 사과 없애기
			}
			// 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
			else {
				int[] tmp = snake.pollLast();
				visited[tmp[0]][tmp[1]] = false;
			}

			// 모든 행동 뒤에 시간은 흐르기
			t++;
			
			// 뱀 머리 돌리기
			if (!turn.isEmpty() && turn.peek()[0] == t) {
				if (turn.peek()[1] == 'L')
					head--;
				else if (turn.peek()[1] == 'D')
					head++;

				if (head < 0)
					head = 3;
				if (head > 3)
					head = 0;
				
				turn.poll();
			}
		}

		sb.append(t+1);

		System.out.println(sb);
	}

}
