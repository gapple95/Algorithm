import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			
			arr[i] = tmp;
			
		}
		
		int cnt = 1;
		
		int max = arr[arr.length - 1];
		for (int i = arr.length - 1; i >= 0; i--) {
			if(max < arr[i]) {
				max = arr[i];
				cnt++;
			}
		}
		
		sb.append(cnt);
		
		System.out.println(sb);
		
		br.close();
	}

}
