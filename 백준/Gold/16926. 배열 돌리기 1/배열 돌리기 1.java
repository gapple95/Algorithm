/*
 * 배열 돌리기1
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
 * 1<=R<=1000
 * min(N,M) mod 2=0
 * 1<=Aij<=10^8
 * 둘째 줄에 N개의 줄에 배열 A의 원소 Aij가 주어진다.
 * 
 * # 출력
 * 입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.
 * 
 * # 생각
 * 전체가 같이 회전하는게 아닌, 1줄씩 한칸씩 밀리는 구조이다.
 * 시작점(왼쪽위)을 따로 뺴고, 반시계방향으로 한칸씩 빼서 넣어주자.
 * 그다음 (0,0) 그다음 (1,1), 그다음 (2,2) ... 쭉쭉하게끔
 * 안으로 하는 것은 세로 길이의 절반 만큼만 (예를 들면 세로길이가 4이면 2번, 5~6이면 3번)
 * 처음엔 가로 N-1, 세로 N-1 만큼하지만, 그 다음줄에는 N-3, 그다음 줄은 N-5만큼
 * 반드시 가로든 세로드 2의 배수가 하나는 존재.=>끝까지 돌릴수 있다!
 * 그러면, 가로와 세로 중에 작은걸 기준으로 도는걸 정하자.
 * 가로, 세로 둘중에 가장 작은거를 / 2 한것이 껍질의 수
 *  
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

		for (int i = 0; i < R; i++)
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
		for (int c = 0; c < cnt; c++) {
			--width;
			--height;
			
			save = arr[c][c];

			// 위
			for(int w=c; w<width; w++) {
				arr[c][w] = arr[c][w+1];
			}
			
			// 오른쪽
			for(int h=c; h<height; h++) {
				arr[h][width] = arr[h+1][width];
			}
			
			// 아래
			for(int w=width; w>c; w--) {
				arr[height][w] = arr[height][w-1];
			}
			
			
			// 왼쪽
			for(int h=height; h>c; h--) {
				arr[h][c] = arr[h-1][c];
			}
			
			arr[c+1][c] = save;
			
		}
	}

}