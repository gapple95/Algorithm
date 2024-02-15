import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/* 
 * 백준 17406 배열 돌리기 4
 * 
 * 메모리
 * 시간
 * 
 * 크기가 N x M 크기인 배열 A가 있을 때, 배열 A의 값은 각 행에 있는 모든 수의 합 중 최솟값을 의미한다.
 * 배열 A가 아래와 같은 경우 1행의 합은 6, 2행의 합은 4, 3행의 합은 15이다. 따라서, 배열 A의 값은 4이다.
1 2 3
2 1 1
4 5 6
 * 
 * 배열은 회전 연산을 수행할 수 있다. 회전 연산은 세 정수(r,c,s)로 이루어져 있고, 가장 왼쪽 윗
 * 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)인 정사각형을 시계 방향으로
 * 한 칸씩 돌린다는 의미이다. 배열의 칸(r,c)는 r행 c열을 의미한다.
 * 
 * 예를 들어, 배열 A의 크기가 6x6이고, 회전 연산이 (3,4,3)인 경우에는 아래 그림과 같이 회전하게 된다.
A[1][1]   A[1][2] → A[1][3] → A[1][4] → A[1][5] → A[1][6]
             ↑                                       ↓
A[2][1]   A[2][2]   A[2][3] → A[2][4] → A[2][5]   A[2][6]
             ↑         ↑                   ↓         ↓
A[3][1]   A[3][2]   A[3][3]   A[3][4]   A[3][5]   A[3][6]
             ↑         ↑                   ↓         ↓
A[4][1]   A[4][2]   A[4][3] ← A[4][4] ← A[4][5]   A[4][6]
             ↑                                       ↓
A[5][1]   A[5][2] ← A[5][3] ← A[5][4] ← A[5][5] ← A[5][6]

A[6][1]   A[6][2]   A[6][3]   A[6][4]   A[6][5]   A[6][6]
 * 
 * 
 * @입력
 * 첫째 줄에 배열 A의 크기 N,M, 횐전 연산의 개수가 K가 주어진다.
 * 둘째 줄 부터 N개의 줄에 배열 A에 들어있는 수 A[i][j]가 주어지고, 다음 K개의 줄에 회전 연산의
 * 정보 r,c,s가 주어진다.
 * 
 * @출력
 * 배열 A의 값의 최솟값을 출력한다.
 * 
 * @해결방안
 * 배열을 돌리는 함수를 구현한 후, 연산 순서를 순열로 조합하여 각 최솟값을 구한다.
 * 
 */

public class Main {

	static Map<Integer, int[]> spin;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int N, M, K;
	static int[] picked;
	static boolean[] isSelected;
	static int min = Integer.MAX_VALUE;
	static int[][] map, tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		tmp = new int[N][M];
		

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		spin = new HashMap<>();

		int r, c, s;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			spin.put(i, new int[] { r, c, s });
		}
		
		isSelected = new boolean[spin.size()];
		picked = new int[spin.size()];

		perm(0);
		System.out.println(min);

	}

	public static int[][] spin_arr(int[][] map, int r, int c, int s) {
		int[][] tmp_map = map.clone();
		
		for (int i = 2; i <= s+1; i++) {
			int _r = r - i;
			int _c = c - i;
//			System.out.println("S : " + i);
//			for (int k = 0; k < N; k++) {
//				System.out.println(Arrays.toString(tmp_map[k]));
//			}
//			System.out.println();
			int tmp = tmp_map[_r][_c];
			for (int d = 0; d < 4; d++) {
				for (int j = 0; j < 2 * i - 2; j++) {
					tmp_map[_r][_c] = tmp_map[_r + dy[d]][_c + dx[d]];
					_r += dy[d];
					_c += dx[d];
				}
			}
			tmp_map[_r][_c+1] = tmp;
//			for (int k = 0; k < N; k++) {
//				System.out.println(Arrays.toString(tmp_map[k]));
//			}
//			System.out.println();
		}

		return tmp_map;
	}
	
	public static void cal(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			int sum = 0;
			for (int j = 0; j < map[0].length; j++) {
				sum += map[i][j];
			}
			if(min>sum)
				min = sum;
		}
	}
	
	public static void perm(int idx) {
		if(idx==spin.size()) {
			for (int i = 0; i < map.length; i++) {
				tmp[i] = map[i].clone();
			}
			for (int i = 0; i < idx; i++) {
					tmp = spin_arr(tmp, spin.get(picked[i])[0], spin.get(picked[i])[1], spin.get(picked[i])[2]);
//					for (int k = 0; k < N; k++) {
//						System.out.println(Arrays.toString(tmp[k]));
//					}
//					System.out.println();
			}
			cal(tmp);
			return;
		}
		for(int i=0; i<spin.size(); i++) {
			if(isSelected[i])continue;
			isSelected[i] = true;
			picked[idx] = i;
			perm(idx+1);
			isSelected[i] = false;
		}
	}
}