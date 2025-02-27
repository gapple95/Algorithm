import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			String answer = "";
			
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			
			int K = Integer.parseInt(br.readLine());
			
			List<Integer>[] list = new List['z' + 1];
			for (int i = 'a'; i <= 'z'; i++) {
				list[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < str.length(); i++) {
				list[str.charAt(i)].add(i);
			}
			
			for (int i = 'a'; i <= 'z'; i++) {
				if(list[i].size() >= K) {
					
					for (int j = 0; j <= list[i].size() - K; j++) {
						int len = list[i].get(j + K - 1) - list[i].get(j) + 1;
						
						min = Math.min(min, len);
						max = Math.max(max, len);						
					}
				}
			}
			
			if(min == Integer.MAX_VALUE && max == Integer.MIN_VALUE) {
				sb.append("-1\n");
			} else {
				sb.append(min).append(" ").append(max).append("\n");
			}
			
		}
		
		System.out.println(sb);
		
		br.close();
	}

}
