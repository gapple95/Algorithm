import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			int[] list = new int[N+1];
			for (int i = 1; i <= N; i++) {
				if(i==X)
					list[i] = 1;
				else
					list[i] = 0;
			}
			
			int K = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				swap(list, a, b);
			}
			
			for (int i = 1; i <= N; i++) {
				if(list[i] == 1) {
					sb.append(i).append("\n");
					break;
				}
			}
		
		System.out.println(sb);
	}
	
	public static void swap(int[] list, int a, int b) {
		int tmp = list[a];
		list[a] = list[b];
		list[b] = tmp;
	}
}