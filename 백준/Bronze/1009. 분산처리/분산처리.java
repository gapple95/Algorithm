import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			long result = a;
			
			for (int i = 1; i < b; i++) {
				result = result * a % 10;
			}
			
			if(result % 10 == 0)
				sb.append(10);
			else
				sb.append(result % 10);
			sb.append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}
