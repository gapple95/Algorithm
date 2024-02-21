import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 3289 서로소 집합
 * 
 * 메모리 22148
 * 시간 232
 * 
 * 초기에 {1}, {2}, ... , {n}이 각각 n개의 집합을 이루고 있다.
 * 여기에 합집합 연산과 두 원소가 같은 집합에 포함되어있는지를 확인하는 연산을 수행하려고한다.
 * 연산을 수행하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫 번째 줄에 테스트 케이스 수 T가 주어진다.
 * 첫째 줄에 n(1<=n<=1,000,000), m(1<=m<=100,000)이 주어진다.
 * m은 입력으로 주어지는 연산의 개수이다.
 * 
 * 다음 m개의 줄에는 각각의 연산이 주어진다.
 * 합집합은 0 a b의 형태로 입력이 주어진다.
 * 이는 a가 포함 되어 있는 집합과, b가 포함되어 있는 집합을 합친다는 의미이다.
 * 두 원소가 같은 집합에 포함되는지 확인은 1 a b
 * 
 * 이는 a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다.
 * a와 b는 n 이하의 자연수이며 같을 수 있다.
 * 
 * @출력
 * 각 테스트 케이스마다 1로 시작하는 입력에 대해서 같은 집합에 속해 있다면 1을,
 * 아니면 0을 순서대로 한줄에 연속하여 출력한다.
 * 
 * @해결방안
 * 서로소집합 연산을 구현한다.
 * 먼저 find는 들어온 값의 parents를 참고하여 재귀적으로 대표자인지 판별
 * 대표자인지는 parents[a]와 a가 같으면 즉, 자기자신만을 가리키면  대표자
 * path compression을 적용하기 위해 마지막 return에는 find(parents[a])의 값을
 * parents[a]에 넣고 반환하기 -> 대표자에서 자식노드들의 거리를 1로 줄여준다.
 * find할 때마다 0인지 1인지 출력
 * 
 * 합연산
 * 제일먼저 해당 노드의 대표 노드를 불러와야한다. -> find연산을 먼저 진행
 * 합할 두 노드의 집합의 대표자들중 하나만 선정하여 parents의 값을 다른 요소로 가리키게끔 변경
 * 사실 위에꺼만 해도되지만, 집합의 갯수를 구하기 위해서 void에서 boolean으로 반환 타입변경.
 * 집합을 구할 수 있으면 true, 없으면 false로 
 * 
 */

public class Solution {

	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		int n,m;
		int cmd, a, b;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			sb.append("#"+t+" ");
			
			// 서로소 집합 구현
			parents = new int[n+1];
			for (int i = 1; i <= n; i++) {
				parents[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				cmd = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(cmd == 0) {
					union(a,b);
				} else {
					sb.append(find(a) == find(b)?"1":"0");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int find(int a) {
		if(a == parents[a])
			return a;
		
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA==rootB)
			return false;
		
		parents[rootA] = rootB;
		
		return true;
	}
}