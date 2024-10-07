/*
 * 배열 돌리기 3
 * 
 * 메모리
 * 시간
 * 
 * # 문제
 * 크기가 NxM인 배열이 있을 때, 배열에 연산을 R번 적용하려고 한다. 연산은 총 6가지가 있다.

1번 배열을 상하 반전
1 6 2 9 8 4 → 4 2 9 3 1 8
7 2 6 9 8 2 → 9 2 3 6 1 5
1 8 3 4 2 9 → 7 4 6 2 3 1
7 4 6 2 3 1 → 1 8 3 4 2 9
9 2 3 6 1 5 → 7 2 6 9 8 2
4 2 9 3 1 8 → 1 6 2 9 8 4
   <배열>       <연산 결과>

2번 연산은 배열을 좌우 반전
1 6 2 9 8 4 → 4 8 9 2 6 1
7 2 6 9 8 2 → 2 8 9 6 2 7
1 8 3 4 2 9 → 9 2 4 3 8 1
7 4 6 2 3 1 → 1 3 2 6 4 7
9 2 3 6 1 5 → 5 1 6 3 2 9
4 2 9 3 1 8 → 8 1 3 9 2 4
   <배열>       <연산 결과>

3번 연산은 오른쪽으로 90도 회전
1 6 2 9 8 4 → 4 9 7 1 7 1
7 2 6 9 8 2 → 2 2 4 8 2 6
1 8 3 4 2 9 → 9 3 6 3 6 2
7 4 6 2 3 1 → 3 6 2 4 9 9
9 2 3 6 1 5 → 1 1 3 2 8 8
4 2 9 3 1 8 → 8 5 1 9 2 4
   <배열>       <연산 결과>

4번 연산은 왼쪽으로 90도 회전
1 6 2 9 8 4 → 4 2 9 1 5 8
7 2 6 9 8 2 → 8 8 2 3 1 1
1 8 3 4 2 9 → 9 9 4 2 6 3
7 4 6 2 3 1 → 2 6 3 6 3 9
9 2 3 6 1 5 → 6 2 8 4 2 2
4 2 9 3 1 8 → 1 7 1 7 9 4
   <배열>       <연산 결과>

5, 6번 연산을 수행하려면 배열을 크기가 N/2×M/2인 4개의 부분 배열로 나눠야 한다.

5번 연산은 1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동시키는 연산
3 2 6 3 1 2 9 7 → 2 1 3 8 3 2 6 3
9 7 8 2 1 4 5 3 → 1 3 2 8 9 7 8 2
5 9 2 1 9 6 1 8 → 4 5 1 9 5 9 2 1
2 1 3 8 6 3 9 2 → 6 3 9 2 1 2 9 7
1 3 2 8 7 9 2 1 → 7 9 2 1 1 4 5 3
4 5 1 9 8 2 1 3 → 8 2 1 3 9 6 1 8
     <배열>            <연산 결과>

6번 연산은 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동시키는 연산
3 2 6 3 1 2 9 7 → 1 2 9 7 6 3 9 2
9 7 8 2 1 4 5 3 → 1 4 5 3 7 9 2 1
5 9 2 1 9 6 1 8 → 9 6 1 8 8 2 1 3
2 1 3 8 6 3 9 2 → 3 2 6 3 2 1 3 8
1 3 2 8 7 9 2 1 → 9 7 8 2 1 3 2 8
4 5 1 9 8 2 1 3 → 5 9 2 1 4 5 1 9
     <배열>            <연산 결과>
 * 
 * # 입력
 * 첫째 줄에 배열의 크기 N, M과 수행해야 하는 연산의 수 R이 주어진다.
 * 둘째 줄부터 N개의 줄에 배열 A의 원소 Aij가 주어진다.
 * 마지막 줄에는 수행해야 하는 연산이 주어진다. 연산은 공백으로 구분되어져 있고, 문제에서 설명한 연산 번호이며, 순서대로 적용시켜야 한다.
 * 
 * # 출력
 * 입력으로 주어진 배열에 R개의 연산을 순서대로 수행한 결과를 출력
 * 
 * # 제한
 * 2 ≤ N, M ≤ 100
 * 1 ≤ R ≤ 1,000
 * N, M은 짝수
 * 1 ≤ A_ij ≤ 10^8
 * 
 * # 풀이
 * 1번과 2번은 R을 %2로 나눠서 진행. 상하는 2번이면 제자리로 돌아오기때문
 * 나머지들은 %4로 나눠서 진행
 * 그리고 연산들은 전부 System.arraycopy를 사용하여 새로운 배열을 만드는 형식으로 진행
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R, arr[][], tmp[][], cmd;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		tmp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < R; i++) {
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case 1:
				cal1();
				break;
			case 2:
				cal2();
				break;
			case 3:
				cal3();
				break;
			case 4:
				cal4();
				break;
			case 5:
				cal5();
				break;
			case 6:
				cal6();
				break;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void cal1() {

		for (int i = 0; i < arr.length; i++) {
			System.arraycopy(arr[i], 0, tmp[arr.length - i - 1], 0, arr[0].length);
		}

		for (int i = 0; i < arr.length; i++) {
			System.arraycopy(tmp[i], 0, arr[i], 0, arr[0].length);
		}

	}

	public static void cal2() {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				tmp[i][j] = arr[i][arr[0].length - j - 1];
			}

		}

		for (int i = 0; i < arr.length; i++) {
			System.arraycopy(tmp[i], 0, arr[i], 0, arr[0].length);
		}

	}

	public static void cal3() {
		tmp = spinR(arr);

		arr = new int[tmp.length][tmp[0].length];

		for (int i = 0; i < tmp.length; i++) {
			System.arraycopy(tmp[i], 0, arr[i], 0, tmp[0].length);
		}
	}

	public static void cal4() {
		tmp = spinL(arr);

		arr = new int[tmp.length][tmp[0].length];

		for (int i = 0; i < tmp.length; i++) {
			System.arraycopy(tmp[i], 0, arr[i], 0, tmp[0].length);
		}
	}

	public static void cal5() {
		int n = arr.length / 2;
		int m = arr[0].length / 2;

		// 1번
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tmp[i][j + m] = arr[i][j];
			}
		}
		// 2번
		for (int i = 0; i < n; i++) {
			for (int j = m; j < arr[0].length; j++) {
				tmp[i + n][j] = arr[i][j];
			}
		}
		// 3번
		for (int i = n; i < arr.length; i++) {
			for (int j = m; j < arr[0].length; j++) {
				tmp[i][j - m] = arr[i][j];
			}
		}
		// 4번
		for (int i = n; i < arr.length; i++) {
			for (int j = 0; j < m; j++) {
				tmp[i - n][j] = arr[i][j];
			}
		}
		for (int i = 0; i < tmp.length; i++) {
			System.arraycopy(tmp[i], 0, arr[i], 0, tmp[0].length);
		}

	}

	public static void cal6() {
		int n = arr.length / 2;
		int m = arr[0].length / 2;

		// 1번
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tmp[i + n][j] = arr[i][j];
			}
		}
		// 2번
		for (int i = n; i < arr.length; i++) {
			for (int j = 0; j < m; j++) {
				tmp[i][j + m] = arr[i][j];
			}
		}
		// 3번
		for (int i = n; i < arr.length; i++) {
			for (int j = m; j < arr[0].length; j++) {
				tmp[i - n][j] = arr[i][j];
			}
		}
		// 4번
		for (int i = 0; i < n; i++) {
			for (int j = m; j < arr[0].length; j++) {
				tmp[i][j - m] = arr[i][j];
			}
		}
		for (int i = 0; i < tmp.length; i++) {
			System.arraycopy(tmp[i], 0, arr[i], 0, tmp[0].length);
		}

	}

	public static int[][] spinR(int[][] target) {
		int r = target.length;
		int c = target[0].length;

		int[][] tmp = new int[c][r];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				tmp[j][r - i - 1] = target[i][j];
			}
		}

		return tmp;
	}

	public static int[][] spinL(int[][] target) {
		int r = target.length;
		int c = target[0].length;

		int[][] tmp = new int[c][r];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				tmp[c - j - 1][i] = target[i][j];
			}
		}

		return tmp;
	}

}