import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int r = 31, M = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += ((str.charAt(i) - 'a' + 1) * pow(r, i)) % M; 
            sum %= M;
		}
		sb.append(sum);
		System.out.println(sb);
	}

	public static long pow(int r, int i) {
		long result = 1;
		for (int j = 0; j < i; j++) {
			result %= M;
			result *= r;
		}
		return result;
	}
}