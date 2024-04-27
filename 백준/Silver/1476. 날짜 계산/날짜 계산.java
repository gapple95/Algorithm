import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int E, S , M;
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		int e = 0, s = 0, m = 0;
		while(true) {
			e++;
			s++;
			m++;
			e = e > 15?1:e;
			s = s > 28?1:s;
			m = m > 19?1:m;
			
			cnt++;
			if(E==e & S==s & M==m)
				break;
		}
		sb.append(cnt);
		System.out.println(sb);
	}

}