/*
 * SWEA 벽돌깨기
 * 
 * 메모리 
 * 시간 
 * 
 * W x H크기의 배열
 * 0은 빈공감 벽돌은 1~9
 * 벽돌은 깨질때, 자신의 숫자 -1 만큼의 상하좌우가 같이 제거된다.
 * 
 * 사라지면 벽돌은 아래로 빈공간을 채운다.
 * 
 * N개의 구슬을 떨어트려 최대한 많은 벽돌을 제거하려고 한다.
 * N,W,H가 주어질 때, 남은 벽돌 개수 구하기
 * 
 * @제약사항
 * N 1~4
 * W 2~12
 * H 2~15
 * 
 * @입력
 * T 테스트 케이스
 * 
 * @출력
 * #t 정답
 * 정답은 남은 벽돌 갯수의 최댓값
 * 
 *  
 * @아이디어
 * 최대한 빨리 깰려면 세로의 값의 최대인 곳을 먼저 깨기..?
 * 중복 조합으로 다 깨보기
 * 
 *  
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int W, H, N, min;
	static int[][] map, copy_map;
	static int[] result;

	static int[] bottom_brick_height;
	static int[] top_brick_height;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			copy_map = new int[H][W];
			bottom_brick_height = new int[W];
			top_brick_height = new int[W];
			result = new int[N];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					map[i][j] = tmp;
				}
			}
			
			for (int i = 0; i < H; i++) {
				System.arraycopy(map[i], 0, copy_map[i], 0, W);
			}
			
			perm(0);
			sb.append(min).append("\n");
			
			
		}
		System.out.println(sb);
	}

	public static void perm(int idx) {
		if (idx == N) {
			for (int i = 0; i < H; i++) {
				System.arraycopy(map[i], 0, copy_map[i], 0, W);
			}
			
			for (int i = 0; i < N; i++) {
				updateTopBrickHeight();
				shot(result[i]);
				updateMap(copy_map);
			}

			min = Math.min(min, check(copy_map));

			return;
		}
		for (int i = 0; i < W; i++) {
			result[idx] = i;
			perm(idx + 1);
		}
	}
	
	public static int check(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] != 0)
					cnt++;
			}
		}
		return cnt;
	}

	// 구슬 쏘기
	// 열이 결정되면 바로 top_brick_height를 쏘기
	public static void shot(int c) {
		visited = new boolean[H][W];

		int[] start = new int[] { top_brick_height[c], c, copy_map[top_brick_height[c]][c] };

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				for (int range = 0; range < cur[2]; range++) {
					int nr = cur[0] + dy[d] * range;
					int nc = cur[1] + dx[d] * range;

					if (nr < 0 || nc < 0 || nr >= H || nc >= W)
						continue;

					if (visited[nr][nc])
						continue;

					visited[nr][nc] = true;

					
					if (map[nr][nc] == 0)
						continue;
					
					q.offer(new int[] { nr, nc, copy_map[nr][nc] });
					copy_map[nr][nc] = 0;
				}
			}
		}

	}
	

	// 현재 벽돌의 가장 높은 벽돌의 높이를 업데이트
	// 부셔질때마다 업데이트
	public static void updateTopBrickHeight() {
		for (int i = 0; i < W; i++) {
			// 위에서 아래로
			for (int j = 0; j < H; j++) {
				// 벽돌을 만나면 해당 위치 업데이트 해주고 옆 칸으로 옮기기
				if (copy_map[j][i] != 0) {
					top_brick_height[i] = j;
					break;
				}
			}
		}
//		System.out.println("가장 윗 바닥 업데이트");
//		System.out.println(Arrays.toString(top_brick_height));
//		System.out.println();
	}

	// 현재 벽돌의 가장 낮은 벽돌의 높이를 업데이트
	// 부셔질때마다 업데이트
	public static void updateBottomBrickHeight() {
		for (int i = 0; i < W; i++) {
			// 아래에서 위로
			for (int j = H - 1; j >= 0; j--) {
				// 0을 만나면 해당 위치 업데이트 해주고 옆 칸으로 옮기기
				if (copy_map[j][i] == 0) {
					bottom_brick_height[i] = j + 1;
					break;
				}
			}
		}
//		System.out.println("가장 아래 바닥 업데이트");
//		System.out.println(Arrays.toString(bottom_brick_height));
//		System.out.println();
	}

	// 바닥으로 벽돌들 다 보내기
	public static void updateMap(int[][] map) {
		int[][] update_map = new int[H][W];

		for (int i = H - 1; i >= 0; i--) {
			for (int j = W - 1; j >= 0; j--) {
				if (map[i][j] != 0) {
					dropBrick(i, j);
				}
			}
		}

		// 맵 업데이트 적용
		for (int i = 0; i < H; i++) {
			System.arraycopy(map[i], 0, update_map[i], 0, W);
		}
	}

	// 해당 위치의 벽돌이 바닥까지 떨어지게끔
	public static void dropBrick(int h, int w) {

		int height = h;
		// 바닥은 할 필요 없다.
		while (true) {
			if (height + 1 >= H)
				return;
			if (copy_map[height + 1][w] != 0)
				return;
			copy_map[height + 1][w] = copy_map[height][w];
			copy_map[height][w] = 0;

			height++;

		}
	}

	public static void print(int map[][]) {
//		System.out.println("print Map");
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
