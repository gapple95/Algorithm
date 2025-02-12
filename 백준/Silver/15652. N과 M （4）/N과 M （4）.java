import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M, arr[];
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());		
		
		arr = new int[M];
		
		comb(0, 1);
		
	}
	
	public static void comb(int idx, int start) {
		if(idx == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = start; i <= N; i++) {
			arr[idx] = i;
			comb(idx + 1, i);
		}
	}

}