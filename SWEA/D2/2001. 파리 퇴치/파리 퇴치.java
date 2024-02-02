import java.util.Scanner;

/*
 * M*M크기의 파리채를 한 번 내리쳐 최대한 많은 파리를 죽이고자 한다.
 * 
 * @제약 사항
 * N은 5이상 15이하
 * M은 2이상 N이하
 * 각 영역의 파리 갯수는 30이하 이다.
 * 
 * @입력
 * 가장 첫 줄에는 테스트 케이스 T
 * 각 테스트 케이스는 첫 번째 줄에 N과 M이 주어지고,
 * 다음 N 줄에 걸쳐 N * N 배열이 주어진다.
 * 
 * @출력
 * 출력의 각 줄은 "#t"로 시작하고, 공백을 한 칸 둔 다음
 * 정답을 출력
 * 정답은 최대한 M*M으로 잡을 수 있는 최대파리
 * 
 * @해결방안
 * 한줄씩 읽는 배열(구간합5 방식)을 구성하여 필요한 지점만 더한다.
 * 
 */

public class Solution {

	static int M,N;
	static int[][] map;
	static int[][] atk;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		int T = s.nextInt();
		for(int t=1; t<=T; t++) {
			N = s.nextInt();
			M = s.nextInt();
			
			map = new int[N+1][N+1];
			atk = new int[M][M];
			
			int[][] map2 = new int[N+1][N+1];
			int sum;
			for (int i = 1; i <= N; i++) {
				sum = 0;
				for (int j = 1; j <= N; j++) {
					map[i][j] = s.nextInt();
					sum += map[i][j];
					map2[i][j] = sum;
				}
			}
			
//			// 한줄 합계 테이블
//			for (int i = 0; i <= N; i++) {
//				for (int j = 0; j <= N; j++) {
//					System.out.print(map2[i][j] + "\t");
//				}
//				System.out.println();
//			}
			
			int max = Integer.MIN_VALUE;
			for(int i=M; i<=N; i++) {
				for(int j=M; j<=N; j++) {
					sum = 0;
					for(int k=i-M+1; k<=i; k++) {
//						System.out.printf("i : %d, j : %d , k : %d\n",i,j,k);
						sum+=map2[k][j];
						sum-=map2[k][j-M];
					}
					if(max < sum) {
						max = sum;
					}
				}
			}
			System.out.printf("#%d %d\n",t, max);
		}
	}

	
}