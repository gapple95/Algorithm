import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];
		for(int i=0; i<=n; i++)
			parents[i] = i;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(c==0) {
				union(a,b);
			} else if(c==1) {
				sb.append(find(a)==find(b)?"YES":"NO");
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	public static int find(int a) {
		if(a == parents[a])
			return a;
		
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB)
			return false;
		
		parents[rootA] = rootB;
		
		return true;
	}
}