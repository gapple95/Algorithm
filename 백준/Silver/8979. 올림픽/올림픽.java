import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Nation {
		int num, gold, silver, cooper, rank;

		public Nation(int num, int gold, int silver, int cooper) {
			super();
			this.num = num;
			this.gold = gold;
			this.silver = silver;
			this.cooper = cooper;
		}

		public boolean equals(Nation o) {
			if(o.gold == gold && o.silver == silver && o.cooper == cooper)
				return true;
			else
				return false;
		}
	}

	static int rank = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Nation> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int cooper = Integer.parseInt(st.nextToken());
			
			list.add(new Nation(num, gold, silver, cooper));
		}
	
		Collections.sort(list, (o1, o2) -> {
			if(o1.gold < o2.gold)
				return 1;
			else if(o1.gold > o2.gold)
				return -1;
			else {
				if(o1.silver < o2.silver)
					return 1;
				else if(o1.silver > o2.silver)
					return -1;
				else {
					if(o1.cooper < o2.cooper)
						return 1;
					else
						return -1;
				}
			}
		});
		
		list.get(0).rank = rank;
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			if(list.get(i).equals(list.get(i-1))) {
				list.get(i).rank = rank;
				cnt++;
			} else {
				list.get(i).rank = rank + cnt;
				rank += cnt;
				cnt = 1;
			}
		}
		
		for(Nation nation : list) {
			if(nation.num == K) {
				sb.append(nation.rank);
				break;
			}
		}
		
		System.out.println(sb);
		
		br.close();
	}

}
