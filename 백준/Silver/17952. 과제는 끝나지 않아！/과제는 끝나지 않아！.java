import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Deque<int[]> stack = new ArrayDeque<>();
		
		int score, time;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("1")) {
				score = Integer.parseInt(st.nextToken());
				time = Integer.parseInt(st.nextToken());
				
				stack.push(new int[] {score, time});
			}
			
			if(!stack.isEmpty()) {
				if(--stack.peek()[1] == 0)
					sum += stack.pop()[0];
			}
		}
		
		sb.append(sum);
		
		System.out.println(sb);
		
		br.close();
	}

}
