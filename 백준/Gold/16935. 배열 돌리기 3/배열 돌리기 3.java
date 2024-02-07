import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
 * 백준 16935 배열 돌리기 3
 * 
 * 메모리
 * 시간
 * 
 * 크기가 N * M인 배열이 있을 때, 배열에 연산을 R번
 * 적용하려고 한다. 연산은 총 6가지가 있다.
 * 
 * 1번연산은 배열을 상하 반전시키는 연산이다.
 
1 6 2 9 8 4 → 4 2 9 3 1 8
7 2 6 9 8 2 → 9 2 3 6 1 5
1 8 3 4 2 9 → 7 4 6 2 3 1
7 4 6 2 3 1 → 1 8 3 4 2 9
9 2 3 6 1 5 → 7 2 6 9 8 2
4 2 9 3 1 8 → 1 6 2 9 8 4
   <배열>       <연산 결과>
 
 * 2번 연산은 배열을 좌우 반전시키는 연산이다

1 6 2 9 8 4 → 4 8 9 2 6 1
7 2 6 9 8 2 → 2 8 9 6 2 7
1 8 3 4 2 9 → 9 2 4 3 8 1
7 4 6 2 3 1 → 1 3 2 6 4 7
9 2 3 6 1 5 → 5 1 6 3 2 9
4 2 9 3 1 8 → 8 1 3 9 2 4
   <배열>       <연산 결과>
  
 * 3번 연산은 오른쪽으로 90도 회전시키는 연산이다.
1 6 2 9 8 4 → 4 9 7 1 7 1
7 2 6 9 8 2 → 2 2 4 8 2 6
1 8 3 4 2 9 → 9 3 6 3 6 2
7 4 6 2 3 1 → 3 6 2 4 9 9
9 2 3 6 1 5 → 1 1 3 2 8 8
4 2 9 3 1 8 → 8 5 1 9 2 4
   <배열>       <연산 결과>
 * 4번 연산은 왼쪽 90도 회전시키는 연산이다.
1 6 2 9 8 4 → 4 2 9 1 5 8
7 2 6 9 8 2 → 8 8 2 3 1 1
1 8 3 4 2 9 → 9 9 4 2 6 3
7 4 6 2 3 1 → 2 6 3 6 3 9
9 2 3 6 1 5 → 6 2 8 4 2 2
4 2 9 3 1 8 → 1 7 1 7 9 4
   <배열>       <연산 결과>
 * 5,6번 연산을 수행하려면 배열을 크기가 N/2 * M/2인 4개의 부분 배열로 나눠야 한다.
 * 아래 그림은 크기가 6*8인 배열을 4개의 그룹으로 나눈 것이고, 1부터 4까지의 수로 나타냈다.
1 1 1 1 2 2 2 2
1 1 1 1 2 2 2 2
1 1 1 1 2 2 2 2
4 4 4 4 3 3 3 3
4 4 4 4 3 3 3 3
4 4 4 4 3 3 3 3
 * 5번 연산은 1번 그룹의 부분 배열을 2번 그룹위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로
 * 이동시키는 연산이다.
3 2 6 3 1 2 9 7 → 2 1 3 8 3 2 6 3
9 7 8 2 1 4 5 3 → 1 3 2 8 9 7 8 2
5 9 2 1 9 6 1 8 → 4 5 1 9 5 9 2 1
2 1 3 8 6 3 9 2 → 6 3 9 2 1 2 9 7
1 3 2 8 7 9 2 1 → 7 9 2 1 1 4 5 3
4 5 1 9 8 2 1 3 → 8 2 1 3 9 6 1 8
     <배열>            <연산 결과>
 * 6번 연산은 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 
 * 이동시키는 연산이다.
3 2 6 3 1 2 9 7 → 1 2 9 7 6 3 9 2
9 7 8 2 1 4 5 3 → 1 4 5 3 7 9 2 1
5 9 2 1 9 6 1 8 → 9 6 1 8 8 2 1 3
2 1 3 8 6 3 9 2 → 3 2 6 3 2 1 3 8
1 3 2 8 7 9 2 1 → 9 7 8 2 1 3 2 8
4 5 1 9 8 2 1 3 → 5 9 2 1 4 5 1 9
 * 
 * @입력
 * 첫째 줄에 배열의 크기ㅏ N,M과 수행해야 하는 연산의 수 R이 주어진다.
 * 둘째 줄부터 N개의 줄에 배열 A의 원소 A_ij가 주어진다.
 * 마지막 줄에는 수행해야하는 연산이 주어진다. 연산은 공백으로 구분되어져있고,
 * 문제에서 설명한 연산 번호이며, 순서대로 적용시켜야한다.
 * 
 * @출력
 * 입력으로 주어진 배열에 R개의 순서대로 수행한 결과를 출력한다.
 * 
 * @해결방안
 * 1,2번 임시 배열에 채우고 다시 깊은 복사를 해준다.
 * 
 * 
 */

public class Main {

	static int N;
	static int M;
	static int R;
	static int L;

	static int[][] map;
	static int[][] map_tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		L = Math.max(N, M);

		map = new int[L][L];
		map_tmp = new int[L][L];

		// 배열 입력 받는 곳
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cmd;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case 1:
				one();
				break;
			case 2:
				two();
				break;
			case 3:
				three();
				break;
			case 4:
				four();
				break;
			case 5:
				five();
				break;
			case 6:
				six();
				break;
			}
			copy();
		}
		print();
	}
	
	static void copy() {
		for(int i=0; i<N; i++) {
			map[i] = Arrays.copyOf(map_tmp[i], map_tmp.length);
		}
	}

	// 1번연산
	static void one() {
		for (int i = 0; i < N; i++) {
			map_tmp[N - i - 1] = map[i].clone();
		}
	}

	// 2번연산
	static void two() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map_tmp[i][M - j - 1] = map[i][j];
			}
		}
	}

	// 3번연산
	static void three() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map_tmp[j][N - i - 1] = map[i][j];
			}
		}

		// 회전해서 배열의 모양이 바뀜
		int tmp = N;
		N = M;
		M = tmp;
	}

	// 4번연산
	static void four() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map_tmp[M - j - 1][i] = map[i][j];
			}
		}

		// 회전해서 배열의 모양이 바뀜
		int tmp = N;
		N = M;
		M = tmp;
	}

	// 5번연산
	static void five() {
		// 1 -> 2
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				map_tmp[i][j + (M / 2)] = map[i][j];
			}
		}
		// 2 -> 3
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				map_tmp[i + (N / 2)][j] = map[i][j];
			}
		}
		// 3 -> 4
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				map_tmp[i][j - (M / 2)] = map[i][j];
			}
		}
		// 4 -> 1
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				map_tmp[i - (N / 2)][j] = map[i][j];
			}
		}

	}

	// 6번연산
	static void six() {
		// 1 -> 4
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				map_tmp[i + (N / 2)][j] = map[i][j];
			}
		}
		// 4 -> 3
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				map_tmp[i][j + (M / 2)] = map[i][j];
			}
		}
		// 3 -> 2
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				map_tmp[i - (N / 2)][j] = map[i][j];
			}
		}
		// 2 -> 1
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				map_tmp[i][j - (M / 2)] = map[i][j];
			}
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0)
					continue;
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}