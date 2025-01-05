import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int[] map;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[101];
		visited = new boolean[101];
		
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int target, to;
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());

			target = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			map[target] = to;
		}
		
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {1, 0});
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[0] == 100) {
				sb.append(cur[1]);
				break;
			}
			
			for (int d = 1; d <= 6; d++) {
				int next = cur[0] + d;
				
				if(next > 100)
					continue;
				
				if(visited[next])
					continue;
				
				visited[next] = true;
				if(map[next] != 0)
					next = map[next];
				q.offer(new int[] {next, cur[1] + 1});
			}
		}
		
		System.out.println(sb);
		br.close();
	}

}
