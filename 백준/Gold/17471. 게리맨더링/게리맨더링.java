import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static class City {
		int to;
		City next;
		public City(int to, City next) {
			this.to = to;
			this.next = next;
		}
	}
	
	static City[] list;
	static boolean[] isSelected;
	static boolean[] visited;
	static int N, min;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		min = Integer.MAX_VALUE;
		
		N = Integer.parseInt(st.nextToken());
		list = new City[N+1];
		isSelected = new boolean[N+1];
		visited = new boolean[N+1];
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				int b = Integer.parseInt(st.nextToken());
				list[i] = new City(b, list[i]);
				list[b] = new City(i, list[b]);
			}
		}
		
		sub(1);
		
		if(min == Integer.MAX_VALUE)
			sb.append(-1);
		else
			sb.append(min);
		
		System.out.println(sb);
		br.close();
	}

	public static void sub(int idx) {
		if(idx == N+1) {
			
//			for (int i = 1; i < isSelected.length; i++) {
//				System.out.print(isSelected[i]?"■":"□");
//			}
//			System.out.println();
			
			
			int selected_city = 0; // 선택된 지역구 갯수 파악
			for (int i = 1; i <= N; i++) {
				if(isSelected[i])
					selected_city++;
			}
			
			// 모든 구역이 선택되거나, 안되면 스킵
			if(selected_city == 0 || selected_city == N)
				return;
			
			// 선택된 구역을 카운트
			int sum_true = 0;
			int cnt = selected_city;
			for (int i = 1; i <= N; i++) {
				if(isSelected[i]) {
					Deque<Integer> q = new ArrayDeque<>();
					System.arraycopy(isSelected, 0, visited, 0, isSelected.length);
					
					q.offer(i);
					
					visited[i] = false;
					
					while(!q.isEmpty()) {
						int cur = q.poll();
						sum_true += arr[cur];
						
						for(City temp = list[cur]; temp != null; temp = temp.next) {
							if(!visited[temp.to])
								continue;
							visited[temp.to] = false;
							q.offer(temp.to);
						}
						cnt--;
					}
					
					if(cnt != 0)
						sum_true = -1;
					
					break;
				}
			}
			
			// 모든조건에 부합하지 않으면 스킵!
			if(sum_true == -1)
				return;
			
			int unselected_city = 0; // 선택된 지역구 갯수 파악
			for (int i = 1; i <= N; i++) {
				if(!isSelected[i])
					unselected_city++;
			}
			
			// 모든 구역이 선택되거나, 안되면 스킵
			if(unselected_city == 0 || unselected_city == N)
				return;
			
			int sum_false = 0;
			cnt = unselected_city;
			for (int i = 1; i <= N; i++) {
				if(!isSelected[i]) {
					Deque<Integer> q = new ArrayDeque<>();
					System.arraycopy(isSelected, 0, visited, 0, isSelected.length);
					
					q.offer(i);
					
					visited[i] = true;
					
					while(!q.isEmpty()) {
						int cur = q.poll();
						sum_false += arr[cur];
						
						for(City temp = list[cur]; temp != null; temp = temp.next) {
							if(visited[temp.to])
								continue;
							visited[temp.to] = true;
							q.offer(temp.to);
						}
						cnt--;
					}
					
					if(cnt != 0)
						sum_false = -1;
					
					break;
				}
			}
			
			// 선택 안된 지역구도 전부 못 돈다면 스킵!
			if(sum_false == -1)
				return;
			
			min = Math.min(min, Math.abs(sum_true - sum_false));
			
			return;
		}
		
		isSelected[idx] = true;
		sub(idx+1);
		isSelected[idx] = false;
		sub(idx+1);
		
	}

}
