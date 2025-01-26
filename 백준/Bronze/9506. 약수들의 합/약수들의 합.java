import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			if(n == -1)
				break;
			
			sb.append(n);
			
			List<Integer> list = new ArrayList<>();
			int sum = 0;
			for (int i = 1; i < n; i++) {
				if(n % i == 0) {
					sum+=i;
					list.add(i);
				}
			}
			
			if(sum == n) {
				sb.append(" = ");
				for (int i = 0; i < list.size() - 1; i++) {
					sb.append(list.get(i)).append(" + ");
				}
				sb.append(list.get(list.size() - 1));
			} else
				sb.append(" is NOT perfect.");
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}

}
