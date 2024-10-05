/*
 * 배열 돌리기2
 * 
 * 메모리 
 * 시간 
 * 
 * # 문제
 * 크기가 NxM인 배열이 있을 때 배열을 돌려보려고 한다.
 * 배열은 반시계 방향으로 돌려야한다.
 * 
 * 배열과 정수 R이 주어졌을 때, 배열으 R번 회전시킨 결과를 구해보자.
 * 
 * # 입력
 * 첫쨰 줄에 배열의 크기, N,M,회전의수 R이 주어진다.
 * 2<=N,M<=300
 * 1<=R<=10^9
 * min(N,M) mod 2=0
 * 1<=Aij<=10^8
 * 둘째 줄에 N개의 줄에 배열 A의 원소 Aij가 주어진다.
 * 
 * # 출력
 * 입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.
 * 
 * # 생각
 * 배열 돌리기 1과 같은 줄 알았으나, 시간초과가 발생
 *  
 * # 풀이
 * 회전수가 10^9이다.
 * R번 돌면 1바퀴를 도는건 똑같음. 그러니 모듈러 연산으로 줄이고 해야한다.
 * 1바퀴를 돈다는건, ((가로+세로)*2) - 4
 * 층마다 다 다르다.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R, arr[][];
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		spin();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

	public static void spin() {
		int cnt = Math.min(N, M) / 2;
		int width = M;
		int height = N;
		int save;
		int spin;
		int r;
		for (int c = 0; c < cnt; c++) {
			r = ((width - c) + (height - c)) * 2 - 4;
			if (r == 0)
				spin = R;
			else
				spin = R % r;
			
			--width;
			--height;
			for (int i = 0; i < spin; i++) {

				save = arr[c][c];

				// 위
				for (int w = c; w < width; w++) {
					arr[c][w] = arr[c][w + 1];
				}

				// 오른쪽
				for (int h = c; h < height; h++) {
					arr[h][width] = arr[h + 1][width];
				}

				// 아래
				for (int w = width; w > c; w--) {
					arr[height][w] = arr[height][w - 1];
				}

				// 왼쪽
				for (int h = height; h > c; h--) {
					arr[h][c] = arr[h - 1][c];
				}

				arr[c + 1][c] = save;
			}
		}
	}

}