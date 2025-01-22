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
		
		int check, score, time;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			check = Integer.parseInt(st.nextToken());
			
			if(check == 1) {
				score = Integer.parseInt(st.nextToken());
				time = Integer.parseInt(st.nextToken());
				
				stack.push(new int[] {score, time});
			}
			
			if(!stack.isEmpty()) {
				int[] cur = stack.pop();
				// 시간이 다 되었으면 점수 획득
				if(cur[1] - 1 == 0) {
					sum += cur[0];
				}
				// 시간이 아직 안되었으면 다시 스택에 넣기
				else {
					stack.push(new int[] {cur[0], cur[1] - 1});
				}
			}
		}
		
		sb.append(sum);
		
		System.out.println(sb);
		
		br.close();
	}

}
