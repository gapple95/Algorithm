import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int map[] = new int[100001];
	static boolean visited[] = new boolean[100001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map[N] = 1;
		map[K] = 2;
		
		Deque<int[]> q = new ArrayDeque<>();
		visited[N] = true;
		q.offer(new int[]{N, 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[0] == K) {
				sb.append(cur[1]);
				break;
			}
			
			int next;
			next = cur[0] * 2;
			if(next >= 0 && next < 100001 && !visited[next]) {
				visited[next] = true;
				q.offer(new int[] {next, cur[1]});
			}
			
			next = cur[0] - 1;
			if(next >= 0 && next < 100001 && !visited[next]) {
				visited[next] = true;
				q.offer(new int[] {next, cur[1] + 1});
			}
			
			next = cur[0] + 1;
			if(next >= 0 && next < 100001 && !visited[next]) {
				visited[next] = true;
				q.offer(new int[] {next, cur[1] + 1});
			}
			
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
