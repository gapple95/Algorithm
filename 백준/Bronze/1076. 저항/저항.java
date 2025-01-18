import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		Map<String, Integer> dict = new HashMap<>();
		dict.put("black", 0);
		dict.put("brown", 1);
		dict.put("red", 2);
		dict.put("orange", 3);
		dict.put("yellow", 4);
		dict.put("green", 5);
		dict.put("blue", 6);
		dict.put("violet", 7);
		dict.put("grey", 8);
		dict.put("white", 9);
		
		String value = "";
		st = new StringTokenizer(br.readLine());
		value += dict.get(st.nextToken()) + "";
		st = new StringTokenizer(br.readLine());
		value += dict.get(st.nextToken()) + "";
		st = new StringTokenizer(br.readLine());
		
		int cnt = dict.get(st.nextToken());
		for (int i = 0; i < cnt; i++) {
			value += "0";
		}
		
		sb.append(Long.valueOf(value));
		
		System.out.println(sb);
		
		br.close();
	}

}
