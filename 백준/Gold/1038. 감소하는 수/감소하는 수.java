import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		List<Long> list = new ArrayList<>();
		
		if(N <= 10)
			sb.append(N);
		else if (N > 1022)
			sb.append(-1);
		else {
			for (int i = 0; i <= 9; i++) {
				func(i, 1, list);
			}
			Collections.sort(list);
			sb.append(list.get(N));
		}

		System.out.println(sb);

		br.close();
	}
	
	public static void func(long num, int idx, List<Long> list) {
		if(idx > 10)
			return;
		
		list.add(num);
		for (int i = 0; i < num % 10; i++) {
			func((num * 10) + i, idx + 1, list);
		}
	}
}
