import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int maxY = Integer.MIN_VALUE, maxX = Integer.MIN_VALUE,
				minY = Integer.MAX_VALUE, minX = Integer.MAX_VALUE;
		int y, x;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());

			maxY = Math.max(maxY, y);
			maxX = Math.max(maxX, x);
			minY = Math.min(minY, y);
			minX = Math.min(minX, x);
		}
		
		sb.append((maxY - minY) * (maxX - minX));
		
		System.out.println(sb);
		
		br.close();
	}

}
