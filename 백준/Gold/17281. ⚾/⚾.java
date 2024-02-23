import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 17281 야구
 * 
 * 메모리
 * 시간
 * 
 * 야구는 9명으로 이루어진 두 팀이 공격과 수비를 번갈아
 * 하는 게임이다. 하나의 이닝은 공격과 수비로 이루어져있고
 * 총 N이닝 동안 게임을 진행해야한다. 한 이닝에 3아웃이면
 * 종료, 두팀이 공격과 수비를 서로 바꾼다.
 * 
 * 두 팀은 경기가 시작하기 전까지 타순을 정해야 하고, 경기 중에는 타순 변경 안된다.
 * 9번타자까지 공을 쳤는데 3아웃이 없으면 이닝은 안끝나고 1번타자가 다시 타석에 선다.
 * 이닝이 끝나고 다음 이닝에는 전 이닝의 마지막 타자의 다음 순서로 쳐야한다.
 * 
 * 공격은 투수가 던진 공을 타자가 치고, 1루, 2루, 3루, 홈에 도착하면 1점ㅇ이다.
 * 타자가 홈에 도착하지 못하고, 1루,2루,3루 중 하나에 머물러 있을 수 있다.
 * 루에 있는 선수를 주자라고 한다. 이닝이 시작될 대는 주자는 없다.
 * 
 * 타자가 공을 쳐서 안타, 2루타, 3루타, 홈런, 아웃 5개중 하나이다. 각각이 발생했을 대,
 * 벌어지는 일은 다음과 같다.
안타: 타자와 모든 주자가 한 루씩 진루한다.
2루타: 타자와 모든 주자가 두 루씩 진루한다.
3루타: 타자와 모든 주자가 세 루씩 진루한다.
홈런: 타자와 모든 주자가 홈까지 진루한다.
아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.
 * 
 * 총 1-9번까지 선수 중 1-4번 타자는 이미 결정했고, 나머지 선수들 중에서
 * 각선수가 각 이닝에서 어떤 결과를 얻는지 알고 있다면, 가장 많은 득점을 하는 타순을 찾고,
 * 그 때의 득점을 구해보자.
 * 
 * @입력
 * 첫째줄에 이닝 수 N(2~50)이 주어진다. 둘째 줄부터 N개의 줄에는 각 선수가 각 이닝에서 얻는
 * 결과가가 1번이닝부터 N번이닝까지 순서대로 주어진다.
 * 각 결과가 의미하는 정수는 다음과 같다.
안타: 1
2루타: 2
3루타: 3
홈런: 4
아웃: 0
 * 각 이닝에는 아웃을 기록하는 타자가 적어도 한 명 존재한다.
 * 
 * @출력
 * 아인타팀이 얻을 수있는 최대 점수 출력
 * 
 * @해결방안
 * 1번은 4번으로 보내는 순열을 구성.
 * 점수는,, Queue로 구현하여 큐에 밀어 넣자.
 * 만약 홈런(4)이 나오면 점수를 4더하고 큐를 비우자.
 * 
 */

public class Main {

	static int[][] map;
	static boolean[] visited;
	static int[] picked;
	static int max = -1;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < 9; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		picked = new int[8];
		visited = new boolean[8];
		perm(0);

		System.out.println(max);
	}

	public static void perm(int idx) {
		if (idx == 8) {
			// 타순 넣기
			int[] inning = new int[9];
			for (int i = 0; i < 8; i++) {
				if (i >= 3) {
					inning[i + 1] = picked[i];
				} else {
					inning[i] = picked[i];
				}
			}
//			inning[3] = vip;
//			System.out.println(Arrays.toString(inning));

			int score = 0;
			int smash_idx = 0;
			for (int i = 0; i < N; i++) {
				Queue<Integer> q = new ArrayDeque<>();
				q.offer(0);
				q.offer(0);
				q.offer(0);
				int out = 0;
				while (out < 3) {
					if (map[i][inning[smash_idx]] == 0) {
						out++;
					} else if (map[i][inning[smash_idx]] == 1) {
						q.offer(1);
					} else if (map[i][inning[smash_idx]] == 2) {
						q.offer(1);
						q.offer(0);
					} else if (map[i][inning[smash_idx]] == 3) {
						q.offer(1);
						q.offer(0);
						q.offer(0);
					} else if (map[i][inning[smash_idx]] == 4) {
						q.offer(1);
						q.offer(0);
						q.offer(0);
						q.offer(0);
					}
					while (!(q.size() < 4)) {
						score += q.poll();
						;
					}
					smash_idx++;
					if (smash_idx > 8)
						smash_idx = 0;
				}
			}
			max = Math.max(max, score);
			return;
		}

		for (int i = 1; i < 9; i++) {
			if (visited[i - 1])
				continue;
			visited[i - 1] = true;
			picked[idx] = i;
			perm(idx + 1);
			visited[i - 1] = false;
		}
	}

}