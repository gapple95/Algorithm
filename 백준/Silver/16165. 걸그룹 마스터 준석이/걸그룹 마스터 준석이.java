import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 백준 16165 걸그룹 마스터 준석이
 * 
 * 메모리 
 * 시간 
 * 
 * 정우는 소무난 걸그룹 덕후이다. 정우의 친구 준석이도 걸그룹을 좋아하지만
 * 이름을 잘 외우지 못한다는 문제가 있었다. 정우는 친구를 위해 걸그룹 개인과
 * 팀의 이름을 검색하여 외우게하는 퀴즈 프로그램을 만들고자 한다.
 * 
 * @입력
 * 첫 번째 줄에는 총 입력 받을 걸그룹의 수 N(0<N<100)과 맞혀야 할
 * 문제의 수 M(0<M<100)을 입력받는다.
 * 
 * 두 번쨰 줄부터는 각 걸그룹마다 팀의 이름, 걸그룹의 인원 수, 멤버의
 * 이름을 한 줄씩 차례대로 입력받는다. 팀과 멤버의 이름은 최대 100글자이며,
 * 모든 글자는 알파벳 소문자이다. 하나의 걸그룹이나 서로 다른 두 걸그룹에
 * 이름이 같은 두 멤버가 있는 경우는 없다.
 * 
 * 그 다음 줄부터는 M개의 퀴즈를 입력받는다. 각각의 퀴즈는 두 줄로 이루어져
 * 있으며, 팀의 이름이나 멤버의 이름이 첫 줄에 주어지고 퀴즈의 종류를
 * 나타내는 0 또는 1이 두 번째 줄에 주어진다. 퀴즈의 종류가 0일 경우
 * 팀의 이름이 주어지며, 1일 경우 멤버의 이름이 주어진다.
 * 
 * 
 * @출력
 * 첫 번째 줄부터 차례대로 퀴즈에 대한 답을 출력한다. 퀴즈의 종류가 0일 경우
 * 해당 팀에 속한 멤버의 이름을 사전순으로 한 줄에 한 명씩 출력한다.
 * 퀴즈의 종류가 1일 경우 해당 멤버가 속한 팀의 이름을 출력한다.
 * 
 * 
 * 
 * 
 */

public class Main {

	static class group {
		String name;
		PriorityQueue<String> members;
		String[] isMembers;
		int size;

		public group(String name) {
			super();
			this.name = name;
			members = new PriorityQueue<>();
		}
		
		public void put(String member) {
			members.offer(member);
			size++;
		}
		
		public String poll_max() {
			return members.poll();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, group> groups = new HashMap<>();
		Map<String, String> members = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			String group_name = br.readLine();
			groups.put(group_name, new group(group_name));
			int num = Integer.parseInt(br.readLine());
			for (int j = 0; j < num; j++) {
				String member_name = br.readLine();
				groups.get(group_name).put(member_name);	
				members.put(member_name, group_name);
			}
		}
		
		for (int i = 0; i < M; i++) {
			String quiz = br.readLine();
			int num = Integer.parseInt(br.readLine());
			// 0일 경우 해당 팀에 속한 멤버의 이름을 사전순으로 한줄에 한 명씩 출력한다.
			if(num == 0) {
				List<String> tmp = new ArrayList<>();
				for (int j = 0; j < groups.get(quiz).size; j++) {
					String name = groups.get(quiz).poll_max();
					tmp.add(name);
					sb.append(name).append("\n");
				}
				for (int j = 0; j < tmp.size(); j++) {
					groups.get(quiz).put(tmp.get(j));
				}
			}
			// 1일 경우 해당 멤버가 속한 팀의 이름을 출력한다.
			else {
				sb.append(members.get(quiz)).append("\n");
			}
		}
		
		System.out.println(sb);
	}

}