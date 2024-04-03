import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * SWEA 키 순서
 * 
 * 메모리 24292
 * 시간 108
 * 
 * 4개의 자석이 놓여져 있었고, 각 자석은 8개의 날을 가지고 있다.(톱니같이)
 * 자석의 각 날 마다 N극 또는 S극의 자성을 가지고 있다.
 * 
 * 이 특이한 자석은 1번부터 4번까지 판에 일렬로 배치되어 있고,
 * 빨간색 화살표 위치에 날 하나가 오도록 배치되어 있다.
 * 
 * 이 자석에는 신기한 규칙이있다.
 * 임의의 자석을 1칸씩 K번 회전시키려고 해보니,
 * ***
 * 하나의 자석이 1칸 회전될 때, 붙어 있는 자석은 서로 붙어있는 날의
 * 자성과 다를 경우에만 인력에 의해 반대방향으로 1칸 회전된다.
 * ***
 * 
 * 점수는 다음과 같다.
- 1 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 1 점을 획득한다.

- 2 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 2 점을 획득한다.

- 3 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 4 점을 획득한다.

- 4 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 8 점을 획득한다.
 * 
 * 4개 자석의 자성정보와 자석을 1칸씩 K번 회전시키려고 할때,
 * K번 자석을 회전시킨 후 획득하는 점수의 총 합을 출력하는 프로그램을
 * 작성하라.
 * 
 * @입력
 * 테스트 케이스 T가 주어지고
 * 각 테스트 케이스의 첫 뻔재 줄에는 자석을 회전시키는 횟수 K가 주어진다.
 * 다음 4개의 줄에는 1번 자석부터 4번 자서까지 각각 8개 날의 자성정보가
 * 차례대로 주어진다.
 * 그 다음 K개의 줄에는 자석을 1칸씩 회전시키는 회전 정보가 주어진다.
 * 회전 정보는 "자석의 번호", "회전방향"으로 구성되어있으며,
 * 회전방향은 1일 경우 시계방향, -1일 경우 반시계 방향이다.
 * 
 */

public class Main {

	static class Magnet {
		int name;
//		Deque<Integer> mag;
		int[] mag;
		int left, right;

		public Magnet(int name) {
			this.name = name;
			mag = new int[8];
		}

		public void reverseSpin() {
			int tmp = mag[0];
			System.arraycopy(mag, 1, mag, 0, 7);
			mag[7] = tmp;

			this.left = mag[6];
			this.right = mag[2];
		}

		public void spin() {
			int tmp = mag[7];
			System.arraycopy(mag, 0, mag, 1, 7);
			mag[0] = tmp;

			this.left = mag[6];
			this.right = mag[2];
		}

//		// 2은 오른쪽에 영향
//		public boolean checkRight(int NS) {
//			boolean flag = false;
//			reverseSpin();
//			reverseSpin();
//			if (mag.peek() == NS)
//				flag = false;
//			else
//				flag = true;
//			spin();
//			spin();
//			return flag;
//		}
//
//		// 6은 오른쪽에 영향
//		public boolean checkLeft(int NS) {
//			spin();
//			spin();
//			boolean flag = false;
//			if (mag.peek() == NS)
//				flag = false;
//			else
//				flag = true;
//			reverseSpin();
//			reverseSpin();
//			return flag;
//		}

		// 오른쪽에 영향
		public int checkRight(int NS, int spin) {
			if (right == NS) {
				spin = 0;
			}
			return spin;
		}

		// 왼쪽에 영향
		public int checkLeft(int NS, int spin) {
			if (left == NS) {
				spin = 0;
			}
			return spin;
		}

	}

	static int K;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		

		Magnet[] magnets = new Magnet[4];

		for (int i = 0; i < magnets.length; i++) {
			magnets[i] = new Magnet(i);
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				int tmp = str.charAt(j) - '0';
				magnets[i].mag[j] = tmp;
				if (j == 2)
					magnets[i].right = tmp;
				else if (j == 6)
					magnets[i].left = tmp;
			}
		}
		
		K = Integer.parseInt(br.readLine());

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int name = Integer.parseInt(st.nextToken()) - 1;
			int spin = Integer.parseInt(st.nextToken());

			int name_0 = 0, name_1 = 0, name_2 = 0, name_3 = 0; // 0 회전 X, 1 시계방향, -1 반시계방향

			switch (name) {
			case 0: {
				// 시계방향
				if (spin == 1) {
					// 첫번째 자석
					name_0 = 1;
				}
				// 반시계방향
				else {
					// 첫번째 자석
					name_0 = -1;
				}
				// 두번째 자석
				name_1 = magnets[name + 1].checkLeft(magnets[name].right, name_0 * -1);
				// 세번째 자석
				name_2 = magnets[name + 2].checkLeft(magnets[name + 1].right, name_1 * -1);
				// 네번째 자석
				name_3 = magnets[name + 3].checkLeft(magnets[name + 2].right, name_2 * -1);
				break;
			}
			case 1: {
				// 시계방향
				if (spin == 1) {
					// 두번째 자석
					name_1 = 1;
				}
				// 반시계방향
				else {
					// 두번째 자석
					name_1 = -1;
				}
				// 첫번째 자석
				name_0 = magnets[name - 1].checkRight(magnets[name].left, name_1 * -1);
				// 세번째 자석
				name_2 = magnets[name + 1].checkLeft(magnets[name].right, name_1 * -1);
				// 네번째 자석
				name_3 = magnets[name + 2].checkLeft(magnets[name + 1].right, name_2 * -1);
				break;
			}
			case 2: {
				// 시계방향
				if (spin == 1) {
					// 세번째 자석
					name_2 = 1;
				}
				// 반시계방향
				else {
					// 세번째 자석
					name_2 = -1;
				}
				// 두번째 자석
				name_1 = magnets[name - 1].checkRight(magnets[name].left, name_2 * -1);
				// 네번째 자석
				name_3 = magnets[name + 1].checkLeft(magnets[name].right, name_2 * -1);
				// 첫번째 자석
				name_0 = magnets[name - 2].checkRight(magnets[name - 1].left, name_1 * -1);
				break;
			}
			case 3: {
				// 시계방향
				if (spin == 1) {
					// 네번째 자석
					name_3 = 1;
				}
				// 반시계방향
				else {
					// 네번째 자석
					name_3 = -1;
				}
				// 세번째 자석
				name_2 = magnets[name - 1].checkRight(magnets[name].left, name_3 * -1);
				// 두번째 자석
				name_1 = magnets[name - 2].checkRight(magnets[name - 1].left, name_2 * -1);
				// 첫번째 자석
				name_0 = magnets[name - 3].checkRight(magnets[name - 2].left, name_1 * -1);
				break;
			}
			}

//				System.out.println(name_0 + " " + name_1 + " " + name_2 + " " + name_3);

			// 첫번째 자석 돌리기
			if (name_0 == 1) {
				magnets[0].spin();
			} else if (name_0 == -1) {
				magnets[0].reverseSpin();
			}

			// 두번째 자석 돌리기
			if (name_1 == 1) {
				magnets[1].spin();
			} else if (name_1 == -1) {
				magnets[1].reverseSpin();
			}

			// 세번째 자석 돌리기
			if (name_2 == 1) {
				magnets[2].spin();
			} else if (name_2 == -1) {
				magnets[2].reverseSpin();
			}

			// 네번째 자석 돌리기
			if (name_3 == 1) {
				magnets[3].spin();
			} else if (name_3 == -1) {
				magnets[3].reverseSpin();
			}
		} // K end

		int score = 0;
		for (int i = 0; i < 4; i++) {

			score += magnets[i].mag[0] == 0 ? 0 : (1 << i);
		}
		sb.append(score).append("\n");

		System.out.println(sb);
	}

}