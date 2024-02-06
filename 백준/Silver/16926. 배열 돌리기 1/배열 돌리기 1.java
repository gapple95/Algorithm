import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 16926 배열 돌리기1
 * 
 * 크기가 N x M인 배열이 있을 때, 배열을 반시계방향으로 돌린다.
 *  
A[1][1] ← A[1][2] ← A[1][3] ← A[1][4] ← A[1][5]
   ↓                                       ↑
A[2][1]   A[2][2] ← A[2][3] ← A[2][4]   A[2][5]
   ↓         ↓                   ↑         ↑
A[3][1]   A[3][2] → A[3][3] → A[3][4]   A[3][5]
   ↓                                       ↑
A[4][1] → A[4][2] → A[4][3] → A[4][4] → A[4][5]

아래는 2번 회전시킨 예지이다.

1 2 3 4       2 3 4 8       3 4 8 6
5 6 7 8       1 7 7 6       2 7 8 2
9 8 7 6   →   5 6 8 2   →   1 7 6 3
5 4 3 2       9 5 4 3       5 9 5 4
 <시작>         <회전1>        <회전2>

 * 
 * 배열과 정수 R이 주어졌을 때, 배열을 R번 회전시킨 결과를 구해보자.
 * 
 * @입력
 * 첫째 줄에 배열의 크기 N,M과 수행해야하는 회전의수 R이 주어진다.
 * 둘째 줄부터 N개의 줄에 배열 A의 원소 A_ij가 주어진다.
 * 
 * @출력
 * 입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.
 * 
 * @해결방안
 * (0,0),(m-0,0),(0,n-0),(m-0,n-0) -> (1,1),(m-1,1),(1,n-1),(n-1,m-1) .. 회전구간을 설정
 * 직접 R값만큼 밀어주는 함수 작성.
 * 
 */

public class Main {

	static int[][] arr, answer;
	static int N, M, R, d;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		answer = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}



		for(int i=0; i<Math.min(N, M) / 2;i++)
			df(i);
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(arr[i][j] + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void df(int r) {
		int n = r;
		for (int i = n; i < N - n; i++) {
			for (int j = n; j < M - n; j++) {
				if (j == n || i == n || i == N - n - 1 || j == M - n - 1) {
					// 포함 되어야 하는 부분
//					System.out.print(i+"," + j +"\t");
					int x = j;
					int y = i;
					for (int k = 0; k < R; k++) {
						if (x == n && y < N - n - 1)
							d = 0; // 아래
						else if (x < M - n - 1 && y == N - n - 1)
							d = 1; // 오른쪽
						else if (x == M - n - 1 && y > n)
							d = 2; // 위
						else if (x > n && y == n)
							d = 3; // 왼쪽
						x += dx[d];
						y += dy[d];
					}
//					System.out.print(y+"," + x +"\t");
					answer[y][x] = arr[i][j];
				} else {
					// 포함안되는 부분
//					System.out.print("\t");
				}
			}
//			System.out.println();
		}
	}

	//겉 출력
	public static void df2(int r) {
		int n = r;
		for (int i = n; i < N - n; i++) {
			for (int j = n; j < M - n; j++) {
				if (j == n || i == n || i == N - n - 1 || j == M - n - 1) {
					// 포함 되어야 하는 부분
					System.out.print(i + "," + j + "\t");

				} else {
					// 포함안되는 부분
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}

//	static int df(int x, int y) {
//		int n = 0; // 몇번재 안쪽인지 -> 제일 밖이 0;
//		while(true) {
//			if(((x>=n && y>=n) && (M-n> x && N-n > y)) &&
//					((x<=n+1 && y<=n+1) && (M-(n+1)> x && N-(n+1)>y))) {
//				break;
//			}
//			if(n>=N/2 || n>=M/2) {
//				n--;
//				break;
//			}
//			n++;
//		}
//		System.out.println(n);
//		return 0;
//	}
}