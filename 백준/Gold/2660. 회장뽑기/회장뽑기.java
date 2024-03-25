import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, arr[], min = Integer.MAX_VALUE;
	static List<Integer>[] member;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		arr =  new int[n+1];
		member = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			member[i] = new ArrayList<>();
		}
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==-1 & b==-1)
				break;
			member[a].add(b);
			member[b].add(a);
		}
		
		for (int i = 1; i <= n; i++) {
			int tmp = bfs(i);
			arr[i] = tmp;
			if(min > tmp)
				min = tmp;
		}
		
		int cnt=0;
		for (int i = 1; i <= n; i++) {
			if(arr[i] == min)
				cnt++;
		}
		sb.append(min).append(" ").append(cnt).append("\n");
		
		for (int i = 1; i <= n; i++) {
			if(arr[i] == min)
				sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	
	static int bfs(int a) {
		boolean[] selected = new boolean[n+1];
		selected[a] = true;
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(a);
		
		int cnt = 0;
		int size = q.size();
		while(!q.isEmpty()) {
			if(size == 0) {
				size = q.size();
				cnt++;
			}
			int cur = q.poll();
			for(int i: member[cur]) {
				if(selected[i])
					continue;
				q.offer(i);
				selected[i] = true;
			}
			size--;
		}
		return cnt;
	}
}