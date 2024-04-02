/*
 * 백준 17070 파이프 옮기기 1
 * 
 * 메모리 
 * 시간 
 * 
 * NxN
 * 파이프는 회전시킬수 있으며, - | \ 요렇게 회전 가능하다.
 * 파이프는 항상 빈칸만 차지
 * 
 * 파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다.
 * 
 * 가장 처음에 파이프는 (1,1)과 (1,2)를 차지하고 있고, 바향은 가로이다.
 * 파이프의 한쪽 끝을 (N,N)으로 이동시키는 방법의 개수를 구해보자.
 * 
 * @입력
 * 첫째줄에 집의 크기 N(3~16)
 * 둘째 줄부터 N개의 줄에는 집의 상태가 주어진다. 빈칸은 0, 벽은 1
 * (1,1)과 (1,2)는 항상 빈 칸이다.
 * 
 * 
 * @출력
 * 첫째줄에 파이프의 한쪽 끝을(N,N)으로 이동시키는 방법의 수를 출력한다.
 * 이동할 수 없는 경우에는 0을 출력한다.
 * 방법의 수는 항상 1,000,000보다 작거나 같다
 * 
 * @해결방안
 * 완탐이 가능할까?
 * bfs로는 안되고, DFS로 해야한다.
 * 도달할 수 있는 모든 경우의 수를 계산한다.
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int head_r, head_c, rear_r, rear_c, state;

		// state 0 : 가로 / state 1 : 세로 / state 2 : 대각

		public Node(int head_r, int head_c, int rear_r, int rear_c, int state) {
			this.rear_r = rear_r;
			this.rear_c = rear_c;
			this.head_r = head_r;
			this.head_c = head_c;
			this.state = state;
		}

		public boolean horizonable(int choose) {

			switch (choose) {
			// 가로로 가는 경우
			case 0: {
				if (head_c + 1 >= N)
					return false;
				if (map[head_r][head_c + 1] != 0)
					return false;
				break;
			}
			// 세로로 가는 경우
			case 1: {
				return false;
			}
			// 대각으로 가는 경우
			case 2: {
				if (head_c + 1 >= N || head_r + 1 >= N)
					return false;
				if (map[head_r][head_c + 1] != 0 || map[head_r + 1][head_c + 1] != 0 || map[head_r + 1][head_c] != 0)
					return false;
				break;
			}
			}

			return true;
		}

		public boolean verticalable(int choose) {
			switch (choose) {
			// 가로로 가는 경우
			case 0: {
				return false;
			}
			// 세로로 가는 경우
			case 1: {
				if (head_r + 1 >= N)
					return false;
				if (map[head_r + 1][head_c] != 0)
					return false;
				break;
			}
			// 대각으로 가는 경우
			case 2: {
				if (head_c + 1 >= N || head_r + 1 >= N)
					return false;
				if (map[head_r][head_c + 1] != 0 || map[head_r + 1][head_c + 1] != 0 || map[head_r + 1][head_c] != 0)
					return false;
				break;
			}
			}
			return true;
		}

		public boolean diagonalable(int choose) {
			switch (choose) {
			// 가로로 가는 경우
			case 0: {
				if (head_c + 1 >= N)
					return false;
				if (map[head_r][head_c + 1] != 0)
					return false;
				break;
			}
			// 세로로 가는 경우
			case 1: {
				if (head_r + 1 >= N)
					return false;
				if (map[head_r + 1][head_c] != 0)
					return false;
				break;
			}
			// 대각으로 가는 경우
			case 2: {
				if (head_c + 1 >= N || head_r + 1 >= N)
					return false;
				if (map[head_r][head_c + 1] != 0 || map[head_r + 1][head_c + 1] != 0 || map[head_r + 1][head_c] != 0)
					return false;
				break;
			}
			}

			return true;
		}

		public Node moveHorizon(int choose) {
			int head_r = this.head_r;
			int head_c = this.head_c;
			int rear_r = this.rear_r;
			int rear_c = this.rear_c;
			int state = -1;

			switch (choose) {
			// 가로로 가는 경우
			case 0: {
				head_c++;
				rear_c++;
				state = 0;
				break;
			}
			// 세로로 가는 경우
			case 1: {
				return null;
			}
			// 대각으로 가는 경우
			case 2: {
				head_r++;
				head_c++;
				rear_c++;
				state = 2;
				break;
			}
			}

			return new Node(head_r, head_c, rear_r, rear_c, state);
		}

		public Node moveVertical(int choose) {
			int head_r = this.head_r;
			int head_c = this.head_c;
			int rear_r = this.rear_r;
			int rear_c = this.rear_c;
			int state = -1;

			switch (choose) {
			// 가로로 가는 경우
			case 0: {
				return null;
			}
			// 세로로 가는 경우
			case 1: {
				head_r++;
				rear_r++;
				state = 1;
				break;
			}
			// 대각으로 가는 경우
			case 2: {
				head_r++;
				head_c++;
				rear_c++;
				state = 2;
				break;
			}
			}

			return new Node(head_r, head_c, rear_r, rear_c, state);
		}

		public Node moveDiagonal(int choose) {
			int head_r = this.head_r;
			int head_c = this.head_c;
			int rear_r = this.rear_r;
			int rear_c = this.rear_c;
			int state = -1;

			switch (choose) {
			// 가로로 가는 경우
			case 0: {
				head_c++;
				rear_c++;
				state = 0;
				break;
			}
			// 세로로 가는 경우
			case 1: {
				head_r++;
				rear_r++;
				state = 1;
				break;
			}
			// 대각으로 가는 경우
			case 2: {
				head_r++;
				head_c++;
				rear_c++;
				state = 2;
				break;
			}
			}

			return new Node(head_r, head_c, rear_r, rear_c, state);
		}

	}

	static int N, map[][], cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Node start = new Node(0, 1, 0, 0, 0);

		dfs(start);

		sb.append(cnt);
		System.out.println(sb);
	}

	public static void dfs(Node node) {
		if (node.head_r == N - 1 && node.head_c == N - 1) {
			cnt++;
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (node.state == 0 && node.horizonable(i)) {
				dfs(node.moveHorizon(i));
			}
			if (node.state == 1 && node.verticalable(i)) {
				dfs(node.moveVertical(i));
			}
			if (node.state == 2 && node.diagonalable(i)) {
				dfs(node.moveDiagonal(i));
			}
		}
	}
}