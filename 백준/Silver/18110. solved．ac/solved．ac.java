import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		list.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		
		});
		int ratio = Math.round(N * (float)(15.0/100.0));
		
		int upStart = 0;
		int upEnd = ratio - 1;
		
		int downStart = N - ratio;
		int downEnd = N - 1;
		
		int idx = 0;
		int sum = 0;
		int cnt = 0;
		for(int i: list) {
			if((idx>=upStart && idx<=upEnd) || (idx>=downStart && idx<=downEnd)) {
				idx++;
				continue;
			}
			sum += i;
			cnt++;
			idx++;
		}
		sb.append(Math.round(1.0 * sum / cnt));
		System.out.println(sb);
	}

}