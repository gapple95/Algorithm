import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * SWEA 2383 점심 식사시간
 * 
 * 메모리 
 * 시간 
 * 
 * 방안의 사람들은 P, 계단입구는 S라고할 때 맵에 표시를 해준다.
 * 이동완료 시간은 모든 사람들이 계단을 내려가 아래층으로 이동을 완료한 시간이다.
 * 
 * 사람들이 아래층으로 이동하는 시간은 계단 입구까지 이동 시간과 계단을 내려가는
 * 시간이 포함된다.
 * 1.계단 입구까지 이동시간은
 *   이동 시간(분) = |PR - SR | + | PC - SC |
 * 
 * 2.계단을 내려가는 시간
 *   계단을 내려가는 시간은 계단 입구에 도착한 후부터 계단을 완전히 내려갈때까지의
 *   시간이다.
 *   계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다.
 *   계단 위에는 동시에 최대 3명까지만 올라가 있을 수 있다.
 *   이미 계단을 3명이 내려가고 있는 경우, 그 중 한명이 계단을 완전히
 *   내려갈때까지 계단 입구에서 대기해야 한다.
 *   계단마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K분이 걸린다.
 * 
 * 사람의 취이와 계단 입구의 위치 및 계단의 길이 정보가 N*N크기의 지도가 주어진다.
 * 이때, 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우를 찾고,
 * 그때의 소요시간을 출력
 * 
 * @제약사항
 * 시간제한 : 최대 50개 테케 모두 통과 3초
 * 
 * N은 4 ~ 10이하의 정수
 * 사람의 수는 1 ~ 10이하의 정수
 * 계단의 입구는 반드시 2개이며, 서로 위치가 겹치지 않는다.
 * 계단의 길이는 2 ~ 10 이하의 정수
 * 초기에 입력으로 주어지는 사람의 위치와 계단 입구의 위치는 서로 겹치지 않는다.
 * 
 * @해결방안
 * 부분집합으로 2개의 계단에 향할 모든 경우의 수를 구한다.
 * 각 경우의 수를 탐색하며 가장 적은 시간 최솟값을 출력
 * 
 */

public class Solution {

	static int map[][], N, picked[], min;
	static boolean[] visited;
	static Map<Integer, int[]> stairs, humen;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());

			min = Integer.MAX_VALUE;

			stairs = new HashMap<>();
			humen = new HashMap<>();

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					map[i][j] = tmp;
					if (tmp > 1) {
						stairs.put(stairs.size(), new int[] { i, j, tmp });
					} else if (tmp == 1) {
						humen.put(humen.size(), new int[] { i, j, 0, 0, 0 }); // 현재 사람 i위치, j위치, 어떤 계단(0,1)으로 향할지, 계단까지의
																				// 거리
					}
				}
			}

			visited = new boolean[humen.size()];
			picked = new int[humen.size()];

			subset(0);
			sb.append(min).append("\n");
		} // test case end

		System.out.println(sb);
	}

	public static void subset(int idx) {
		if (idx == visited.length) {
//			for (int i = 0; i < visited.length; i++) {
//				System.out.print(visited[i] ? (i + 1) + " " : "X ");
//			}
//			System.out.println();

			// 계단 설정
			int[] first = stairs.get(0);
			int[] second = stairs.get(1);

			// 사람 설정
			for (int i = 0; i < visited.length; i++) {
				int[] human = humen.get(i);
				if (visited[i]) { // 1번계단
					humen.get(i)[3] = calD(new int[] { human[0], human[1] }, new int[] { first[0], first[1] }); // 1번
																												// 계단까지의
																												// 거리
					humen.get(i)[2] = 0; // 1번 계단으로 향한다.
				} else { // 2번 계단
					humen.get(i)[3] = calD(new int[] { human[0], human[1] }, new int[] { second[0], second[1] }); // 2번
																													// 계단까지의
																													// 거리
					humen.get(i)[2] = 1; // 2번 계단으로 향한다.
				}
				humen.get(i)[4] = 0;
			}

			int k0 = first[2]; // 첫번째 계단
			int[] first_stair_wait = new int[k0+1];
			Arrays.fill(first_stair_wait, 0);
			int first_num = 0;

			int k1 = second[2]; // 두번째 계단
			int[] second_stair_wait = new int[k1+1];
			Arrays.fill(second_stair_wait, 0);
			int second_num = 0;

			int humen_num = humen.size();
			int time = 1;
			while (true) {
				
				// 이동완료
				if (first_num > 0) {
					// 내려가기 시작
					for (int j = 1; j < first_stair_wait.length; j++) {
						first_stair_wait[j - 1] = first_stair_wait[j];
					}
					first_stair_wait[k0] = 0;
					// 만약 첫번째 계단에서 나가는 사람이있다면
					if (first_stair_wait[0] != 0) {
						first_num -= first_stair_wait[0];
						humen_num -= first_stair_wait[0];
						first_stair_wait[0] = 0;
					}

				}
				if (second_num > 0) {
					// 내려가기 시작
					for (int j = 1; j < second_stair_wait.length; j++) {
						second_stair_wait[j - 1] = second_stair_wait[j];
					}
					second_stair_wait[k1] = 0;
					// 만약 두번째 계단에서 나가는 사람이있다면
					if (second_stair_wait[0] != 0) {
						second_num -= second_stair_wait[0];
						humen_num -= second_stair_wait[0];
						second_stair_wait[0] = 0;
					}
				}

				if (humen_num == 0)
					break;
				
				//계단 프로세스
				for (int i = 0; i < humen.size(); i++) {
					int[] cur = humen.get(i);
					if (cur[3] == 0) { // 계단의 거리 만큼 사람이 이동했다면 그것은 도착했다는 것.
						// 이미 들린 계단을 타기 시작했다면,
						if(cur[4] == 1)
							continue;
						
						// 첫번째 계단으로 향해야한다면
						if (cur[2] == 0) {
							// 입구 도착
							// 내부에 3명 미만이라면 넣기.
							if (first_num < 3) {
								cur[4] = 1;
								first_stair_wait[k0]++;
								first_num++;
							}
							// 3명 이상이라면 기다리기
							else {

							}
						}
						// 두번째 계단으로 향해야한다면
						else if(cur[2] == 1){
							// 입구 도착
							// 내부에 3명 미만이라면 넣기.
							if (second_num < 3) {
								cur[4] = 1;
								second_stair_wait[k1]++;
								second_num++;
							}
							// 3명 이상이라면 기다리기
							else {

							}
						}
					}
				}
				
				
				
				// 전체 이동
				// 계단까지 찾아가는 로직(시간)
				for (int i = 0; i < humen.size(); i++) {
					int[] cur = humen.get(i);
					if (cur[3] <= 0) // 계단을 내려간 상태면 더이상 하지 않는다.
						continue;
					cur[3] -= cur[3] > 0 ? 1 : 0;

				} // for end

				time++;

			} // while end

			min = Math.min(min, time);
//			System.out.println(min);

			return;
		}
		visited[idx] = true;
		subset(idx + 1);
		visited[idx] = false;
		subset(idx + 1);
	}

	public static int calD(int[] human, int[] stair) {
		return Math.abs(human[0] - stair[0]) + Math.abs(human[1] - stair[1]);
	}
}