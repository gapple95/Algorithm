import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		TreeMap<String, String> map = new TreeMap<>(Collections.reverseOrder());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String state = st.nextToken();
			if(state.equals("enter")) {
				map.put(name, state);
			}
			else {
				map.remove(name);
			}
		}
		
		for(String key: map.keySet()) {
			sb.append(key).append("\n");
		}
		
		System.out.println(sb);
	}

}