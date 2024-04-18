import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;
	static int[] arr;
	static boolean[] visited;
	static int[] ops = new int[] { '+', '-', ' ' };
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		Arrays.sort(ops);

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			

			arr = new int[N + N];
			visited = new boolean[N + N];
			arr[0] = '+';
			int idx = 1;
			for (int i = 1; i < N + N; i += 2) {
				arr[i] = idx++;
			}

			dfs(2);
			sb.append("\n");
		}
		System.out.println(sb);

	}

	public static void dfs(int idx) {
		if (idx >= (N + N)) {
//			System.out.println(Arrays.toString(arr));
			int sum = 1;
			for (int i = 2; i < arr.length; i += 2) {
				if (arr[i] == '+') {
					sum += arr[i + 1];
				} else if (arr[i] == '-') {
					sum -= arr[i + 1];
				} else if (arr[i] == ' ') {
					// 앞앞 부호 확인
					// +
					if (arr[i - 2] == '+') {
						sum -= arr[i - 1];
						sum += arr[i - 1] * 10 + arr[i + 1];
					}
					// -
					else if (arr[i - 2] == '-') {
						sum += arr[i - 1];
						sum -= arr[i - 1] * 10 + arr[i + 1];
					}
					// ' '
					else if (arr[i - 2] == ' ') {
						sum *= 10;
						sum += arr[i + 1];
					}
				}
			}

			if (sum == 0) {
//				System.out.println("work : " + sum);
				for (int i = 1; i < arr.length; i++) {
					if (i % 2 == 0)
						sb.append((char) arr[i]);
					else
						sb.append(arr[i]);

				}
				sb.append("\n");
			}

			return;
		}

		for (int i = idx; i < N + N; i += 2) {
			for (int j = 0; j < 3; j++) {
				if (visited[i])
					continue;
				visited[i] = true;
				arr[i] = ops[j];
				dfs(idx + 2);
				visited[i] = false;
			}
		}
	}
}