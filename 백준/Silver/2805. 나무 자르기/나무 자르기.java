import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] trees = new int[N];
		
		int top_trees = Integer.MIN_VALUE;
		for (int i = 0; i < trees.length; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			top_trees = Math.max(top_trees, trees[i]);
		}
		
		int start, end, mid = 0;
		
		end = top_trees;
		start = 0;
		int result = 0;
		while(start <= end) {
			mid = start + (end - start) / 2;
			
			long sum = 0;
			for (int i = 0; i < trees.length; i++) {
				if(trees[i] - mid > 0)
					sum += trees[i] - mid;
			}
			
			if(sum >= M) {
				result = mid;
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		sb.append(result);
		System.out.println(sb);
		
		br.close();
	}

}
