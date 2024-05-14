import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		List<String> list = new ArrayList<>();
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			String tmp = br.readLine();
			if(set.contains(tmp)) {
				cnt++;
				list.add(tmp);
			}
		}
		sb.append(cnt).append("\n");
		Collections.sort(list);
		for (String str: list) {
			sb.append(str).append("\n");
		}
		System.out.println(sb);
	}

}