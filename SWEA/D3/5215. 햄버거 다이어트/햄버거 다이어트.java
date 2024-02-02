import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 5215 햄버거 다이어트
 * 
 * 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지 않는
 * 햄버거를 주문하여 먹으려고 한다.
 * 
 * 재료는 원하는 조합대로 바로 만들어준다.
 * 
 * 민기의 햄버거 재료에 대한 점수와 가게에서 제공하는 재료에 대한
 * 칼로리가 주어졌을 때, 민기가 좋아하는 햄버거를 먹으면서도
 * 정해진 칼로리 이하의 조합 중에서 가장 선호하는 
 * 햄버거를 조합해주는 프로그램을 만들어 보자
 * 
 * !같은 재료를 여러번 사용할 수없다.
 * 
 * @입력
 * 첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
 * 각 테스트 케이스의 첫 번째 줄에는 제한 칼로리를 나타내는
 * N, L(1<=N<=20, 1<= L <=10^4)이 공백으로 구분되어 주어진다.
 * 다음 N개의 줄에는 재료에 대한 민기의 맛에 대한 점수와 칼로리를
 * 나타내는 Ti, ki(1<=Ti<=10^3 , 1<=Ki<=10^3)가 제공
 * N는 재료 갯수, L은 최대 칼로리
 * 
 * @출력
 * #T를 출력한 뒤 주어진 칼로리 이하의 조합중에서
 * 가장 맛에 대한 점수가 높은 햄버거의 점수를 출력한다.
 * 
 * @해결방안
 * 부분집합으로 모든 칼로리의 수를 구한다? 2^20승이라면 1백만이라 가능하다.
 * L(최대칼로리)를 넘으면 바로 포기하고, 최댓값을 비교하여 저장
 * 마지막에 출력
 * 
 */

public class Solution {

	static int N, L, score;
	static int[] indient, cal;
	static boolean[] isSelected;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			max = 0;
            score = 0;
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			indient = new int[N];
			cal = new int[N];
			isSelected = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				indient[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			powerSet(0);
			System.out.printf("#%d %d\n", t, score);
		}
	}

	static void powerSet(int idx) {
		if (idx == N) {
			int tmp = 0; // 칼로리에 대한 임시 변수
			int tmp2 = 0; // 맛 점수에 대한 임시 변수
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					tmp += cal[i];
					tmp2 += indient[i];
				}
			}
			if (tmp <= L) {
				if (score < tmp2) {
					max = tmp;
					score = tmp2;
				}
			}
//			for (int i = 0; i < N; i++) {
//				System.out.print(isSelected[i] ? "■" : "□");
//			}
//			System.out.println();
			return;
		}
		isSelected[idx] = true;
		powerSet(idx + 1);
		isSelected[idx] = false;
		powerSet(idx + 1);
	}
}
