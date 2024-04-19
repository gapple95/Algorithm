import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long arr[], selected[], min_arr[], min;
	static boolean check[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		selected = new long[2];
		min = Long.MAX_VALUE;
		min_arr = new long[2];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		for (int i = 0; i < N; i++)
			// 이분탐색으로 Nlog(N)
			binarySearch(i);

		sb.append(min_arr[0]).append(" ").append(min_arr[1]);
		System.out.println(sb);
	}

	public static void binarySearch(int idx) {
		int start = idx+1;
		int end = N - 1;
		int mid;
		long value;
		
		while(start <= end) {
			mid = (start + end) / 2;
			value = Math.abs(arr[idx] + arr[mid]);
			if(min > value) {
				min = value;
				min_arr[0] = arr[idx];
				min_arr[1] = arr[mid];
			}
			
			if(arr[mid] >= arr[idx] * -1) {
				end = mid -1;
			}
			else if(arr[mid] < arr[idx] * -1) {
				start = mid + 1;
			}
			
		}

	}
}