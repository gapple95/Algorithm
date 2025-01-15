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
		
		st = new StringTokenizer(br.readLine());
		
		Deque<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			q.offer(new int[] {i+1, Integer.parseInt(st.nextToken())});
		}
		
		int[] cur = q.poll();
		sb.append(cur[0]).append(" ");
		while(!q.isEmpty()) {
			if(cur[1] > 0) {
				for (int j = 0; j < cur[1] - 1; j++) {
					q.offer(q.poll());
				}
			}
			else {
				for (int i = 0; i < (cur[1] * -1); i++) {
					q.addFirst(q.pollLast());;
				}
			}
			
			cur = q.poll();
			sb.append(cur[0]).append(" ");
		}
		
		System.out.println(sb);
		
		br.close();
	}

}
