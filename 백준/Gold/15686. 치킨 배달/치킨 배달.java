import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/* 
 * 백준 15686 치킨배달
 * 
 * 메모리
 * 시간
 * 
 * 크기가 NxN인 도시가 있다. 도시는 1x1크기의 칸으로 나누어져있다. 도시의 각 칸은 빈칸, 치킨집, 집, 중
 * 하나이다. 도시의 칸은 (r,c)와 같은 형태로 나타내고, r행 c열 또는 위에서부터 r번째 칸, 왼쪽에서부터 c번째 칸
 * 을 의미한다. r과 c는 1부터 시작한다.
 * 
 * 치킨거리는 집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있다. 도시의 치킨거리는 모든 집의 치킨 거리의
 * 합이다.
 * 
 * 임의의 두칸은 |r1-r2| + |c1-c2|로 구한다.
 * 
 * 치킨집의 최대 개수는 M개라는 사실을 알아 내었다.
 * 도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시킨다. 도시의 치킨 거리가
 * 가장 작게 될지 구하는 프로그램을 자것ㅇ하시오.
 * 
 * @입력
 * 첫째 줄에 N(2<=N<=50)과 M(1<=M<=13)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 도시의 정보가 주어진다.
 * 도시 정보는 0은 빈칸, 1은 집, 2는 치킨집을 의미한다.
 * 집의 개수는 2N개를 넘지 않으며 적어도 1개는 존재한다.
 * 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.
 * 
 * @출력
 * 첫째 줄에 폐업시키지 않을 치킨집 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력한다.
 * 
 * @해결방안
 * 치킨집에서 집까지의 거리를 다 계산을 한 후, 오름차순으로 sort 후 M개 픽업. => 포기.. 다음기회에
 * 
 * 최대 M개의 치킨집들에서 조합으로  경우의수를 구하고,
 * 집까지의 거리를 다 계산 한 후, 가장 작은 값을 출력 
 * 
 */

public class Main {

	static Map<Integer, int[]> home;
	static Map<Integer, int[]> store;
	static int N, M;
	static int[][] map;
	static int[] picked;
	static int chicken; // 집 수
	static int house; // 집 수
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		picked = new int[M];

		home = new HashMap<>();
		store = new HashMap<>();

		map = new int[N][N];
		int tmp;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp == 1) {
					// tmp = 값, 가장 가까운 매장 거리, 좌표 y,x
					home.put(house++, new int[] { tmp, Integer.MAX_VALUE, i, j , 0, 0});
				} else if (tmp == 2) {
					// tmp = 값, 가장 가까운 매장 거리, 좌표 y,x
					store.put(chicken++, new int[] { tmp, Integer.MAX_VALUE, i, j });
				}
			}
		}

//		// 집
//		for (int key : home.keySet()) {
//			// 치킨집
//			for (int key1 : store.keySet()) {
//				// 집(key)에서 치킨집(key1)까지의 거리 중 가장 가까운곳은?
//				tmp = cal(key, key1);
//				if (home.get(key)[1] > tmp) {
//					home.get(key)[1] = tmp;
//					home.get(key)[4] = store.get(key1)[2]; // 가장 가까운 매장 y
//					home.get(key)[5] = store.get(key1)[3]; // 가장 가까운 매장 x
//				}
//			}
//		}
		comb(0,0);
		System.out.println(min);
	}

	static void comb(int idx, int start) {
		if (idx == M) {
			int sum = 0;
			for(int i=0 ; i<house; i++) {
				int tmp = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					tmp = Math.min(tmp, cal(i, picked[j]));
				}
				sum += tmp;
			}
			if(min > sum)
				min = sum;
			return;
		}
		for (int i = start; i < chicken; i++) {
			picked[idx] = i;
			comb(idx+1, i+1);
		}
	}

	/** 
	 * 
	 * @param a : home의 인덱스값
	 * @param b : store의 인덱스값
	 * @return 집부터 치킨집까지의 거리
	 */
	static int cal(int a, int b) {
		return Math.abs(home.get(a)[2] - store.get(b)[2]) + Math.abs(home.get(a)[3] - store.get(b)[3]);
	}
}