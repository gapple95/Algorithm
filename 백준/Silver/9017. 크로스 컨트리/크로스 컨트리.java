import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] team_member = new int[N];
			
			st = new StringTokenizer(br.readLine());
			int M = -1;
			for (int i = 0; i < N; i++) {
				team_member[i] = Integer.parseInt(st.nextToken());
				M = Math.max(M, team_member[i]);
			}
			
			int[] cert = new int[M+1];
			for (int i = 0; i < N; i++) {
				cert[team_member[i]]++;
			}
			
			List<Integer>[] scores = new List[M+1];
			for (int i = 0; i <= M; i++) {
				scores[i] = new ArrayList<>();
			}
			
			int rank = 1;
			for (int i = 0; i < N; i++) {
				if(cert[team_member[i]] < 6)
					continue;
				scores[team_member[i]].add(rank++);
			}
			
			int[] score = new int[M+1];
			for (int i = 1; i <= M; i++) {
				if(cert[i] < 6)
					continue;
				
				int j = 0;
				for(int s: scores[i]) {
					if(j==4)
						break;
					score[i] += s;
					j++;
				}
			}
			
			int min_value = Integer.MAX_VALUE;
			int min_team = -1;
			
			for (int i = 1; i <= M; i++) {
				if(cert[i] < 6)
					continue;
				
				if(min_value > score[i]) {
					min_value = score[i];
					min_team = i;
				} else if(min_value == score[i]) {
					if(scores[min_team].get(4) > scores[i].get(4)) {
						min_value = score[i];
						min_team = i;
					}
				}
			}
			sb.append(min_team).append("\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}

}
