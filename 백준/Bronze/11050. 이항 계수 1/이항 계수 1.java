import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		comb(0,0);
		
		sb.append(cnt);
		System.out.println(sb);
		
	}

	public static void comb(int idx, int start) {
		if(idx == K) {
			cnt++;
			return;
		}
		for (int i = start; i < N; i++) {
			comb(idx+1, i+1);
		}
	}
}