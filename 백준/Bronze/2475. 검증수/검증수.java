import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int sum = 0;
		int n;
		for (int i = 0; i < 5; i++) {
			n = Integer.parseInt(st.nextToken());
			sum += n*n;
		}
		sb.append(sum%10);
		System.out.println(sb);
		
	}

}