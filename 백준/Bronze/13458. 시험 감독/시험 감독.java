import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		long[] A = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		long cnt = 0;

		for (int i = 0; i < A.length; i++) {
			cnt++;
			A[i] -= B;

			if (A[i] > 0) {
				cnt += A[i] / C;
				if (A[i] % C != 0)
					cnt++;
			}
		}

		sb.append(cnt);
		System.out.println(sb);

	}

}