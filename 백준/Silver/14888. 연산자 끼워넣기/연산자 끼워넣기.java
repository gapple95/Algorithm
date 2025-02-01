import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
	static int N, arr[], oper[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		oper = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		
		func(0, arr[0]);
		
		sb.append(max).append("\n").append(min);
		
		System.out.println(sb);
		
		br.close();
	}
	
	public static void func(int depth, int value) {
		if(depth == N-1) {
			max = Math.max(max, value);
			min = Math.min(min, value);
			return;
		}
		
		if(oper[0] > 0) {
			oper[0]--;
			func(depth + 1, value + arr[depth+1]);
			oper[0]++;
		}
		if(oper[1] > 0) {
			oper[1]--;
			func(depth + 1, value - arr[depth+1]);
			oper[1]++;
		}
		if(oper[2] > 0) {
			oper[2]--;
			func(depth + 1, value * arr[depth+1]);
			oper[2]++;
		}
		if(oper[3] > 0) {
			oper[3]--;
			func(depth + 1, value / arr[depth+1]);
			oper[3]++;
		}
		
	}

	
	
}
