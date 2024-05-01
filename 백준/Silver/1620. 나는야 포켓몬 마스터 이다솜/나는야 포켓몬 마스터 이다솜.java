import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map1 = new HashMap<>();
		Map<Integer, String> map2 = new HashMap<>();
		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			map1.put(str, i);
			map2.put(i, str);
		}
		
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			// 숫자면
			if(str.charAt(0) >= '1' && str.charAt(0) <='9') {
				sb.append(map2.get(Integer.parseInt(str))).append("\n");
			}
			else {
				sb.append(map1.get(str)).append("\n");
			}
		}
		System.out.println(sb);
	}

}