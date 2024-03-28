import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA 2115 벌꿀채취
 * 
 * 메모리 
 * 시간 
 * 
 */

public class Solution {

	static int N, M, C;
	static int[] value, arr;
	static boolean selected[];
	static int max, max_value, max_values;
	static boolean max_index[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

//			System.out.println(N + " " + M + " " + C);

			value = new int[N * N];
			max = Integer.MIN_VALUE;
			max_index = new boolean[M];
			arr = new int[2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					value[i * (N) + j] = Integer.parseInt(st.nextToken());
				}
			}
			comb(0, 0);

			sb.append(max).append("\n");
		}

		System.out.println(sb);
	}

	public static void comb(int idx, int start) {
		// 기저조건
		if (idx == 2) {
			// arr[0] 일꾼1
			// arr[1] 일꾼2

			// 일꾼이하는 일에 겹치면 바로 짤!
			for (int i = arr[0] + 1; i < arr[0] + M; i++) {
				if (i == arr[1])
					return;
			}
			// 일꾼이하는 일에 겹치면 바로 짤!
			for (int i = arr[1] + 1; i < arr[1] + M; i++) {
				if (i == arr[0])
					return;
			}

			// 행을 넘어가는 순간 끝
			for (int i = arr[0] + 1; i < arr[0] + M; i++) {
				if (i % N == 0)
					return;
			}

			// 행을 넘어가는 순간 끝
			for (int i = arr[1] + 1; i < arr[1] + M; i++) {
				if (i % N == 0)
					return;
			}

			int sum = 0;
			int sumA = 0;
			selected = new boolean[M];
			max_value = Integer.MIN_VALUE;
			max_values = Integer.MIN_VALUE;
			subsetA(0);
			// 일꾼이 C 이전까지 꿀을 갖고 간다.
			int idxA = arr[0];
			for (int i = 0; i < M; i++) {
				if (max_index[i]) {
					sumA += value[idxA + i] * value[idxA + i];
				}
			}

			sum = 0;
			int sumB = 0;
			selected = new boolean[M];
			max_values = Integer.MIN_VALUE;
			subsetB(0);
			// 일꾼이 C 이전까지 꿀을 갖고 간다.
			int idxB = arr[1];
			for (int i = 0; i < M; i++) {
				if (max_index[i]) {
					sumB += value[idxB + i] * value[idxB + i];
				}
			}

			max = Math.max(max, sumA + sumB);
//			System.out.println(max);
//			System.out.println(sumA + " " + sumB);
//			System.out.println();
			return;
		}

		for (int i = start; i < N * N; i++) {
			arr[idx] = i;
			comb(idx + 1, i + 1);
		}
	}

	public static void subsetA(int idx) {
		if (idx == M) {
			int sum = 0;
			int sums = 0;
			int idxA = arr[0];
			for (int i = 0; i < M; i++) {
				if (selected[i]) {
					sum += value[idxA + i];
					sums += value[idxA + i] * value[idxA + i];
				}
			}
			
			if(sum > C)
				return;
			
			if (max_values <= sums) {
				max_value = sum;
				max_values = sums;
				System.arraycopy(selected, 0, max_index, 0, M);
			}

			return;
		}
		selected[idx] = true;
		subsetA(idx + 1);
		selected[idx] = false;
		subsetA(idx + 1);
	}

	public static void subsetB(int idx) {
		if (idx == M) {
			int sum = 0;
			int sums = 0;
			int idxB = arr[1];
			for (int i = 0; i < M; i++) {
				if (selected[i]) {
					sum += value[idxB + i];
					sums += value[idxB + i] * value[idxB + i];
				}
			}
			
			if(sum > C)
				return;
			
			if (max_values < sums) {
				max_value = sum;
				max_values = sums;
				System.arraycopy(selected, 0, max_index, 0, M);
			}

			return;
		}
		selected[idx] = true;
		subsetB(idx + 1);
		selected[idx] = false;
		subsetB(idx + 1);
	}
}