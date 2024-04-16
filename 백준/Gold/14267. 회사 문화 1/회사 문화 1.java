import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 백준 14267 회사문화1
 * 
 * 메모리 
 * 시간 
 * 
 * 상사가 직속 부하를 칭찬하면 그 부하가 부하의 직속 부하를 연쇄적으로 칭찬하는 내리칭찬이 있다.
 * 상사가 한 직속 부하를 칭찬하면, 그 부하의 모든 부하들이 칭찬을 받는다.
 * => 자식노드들 모두 +1?
 * 모든 칭찬에는 칭찬의 정도를 의미하는 수치가 있는데, 이 수치 또한 부하들에게 똑같이 칭찬 받는다.
 * => 자식노드들 모두 + 칭찬 수치
 * 직속 상사와 직속 부하관계에 대해 주어지고, 칭찬에 대한 정보가 주어질때, 각자 얼마의 칭찬을 받았는지 출력하시오.
 * 
 * @입력
 * 첫째 줄에는 회사의 직원 수 n명, 최초의 칭찬의 횟수 m이 주어진다.
 * 직원은 1번부터 n번까지 번호가 매겨져 있다.(2<= n,m<=100,000)
 * 
 * 둘째 줄에는 직원 n명의 직속 상사의 번호가 주어진다. 직속 상사의 번호는 자신의 번호보다 작으며,
 * 최종적으로 1번이 사장이다. 1번의 경우, 상사가 없으므로 -1이 입력된다.
 * 다음 m줄에는 직속상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w가 주어진다.(2<=i<=n, 1<=w<=1,000)
 * 사장은 상사가 없므로 칭찬을 받지 않는다.
 * 
 * @출력
 * 1번부터 n번의 직원까지 칭찬을 받은 정도를 출력하시오.
 * 
 * 
 * 
 */

public class Main {
	
	static ArrayList<Integer>[] subordinates;
    static int[] praises;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		subordinates = new ArrayList[n + 1];
		praises = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			subordinates[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2; i <= n; i++) {
			int boss = Integer.parseInt(st.nextToken());
			subordinates[boss].add(i);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int subordinate = Integer.parseInt(st.nextToken());
			int praise = Integer.parseInt(st.nextToken());
			praises[subordinate] += praise;
		}

		dfs(1, 0);

		for (int i = 1; i <= n; i++) {
			sb.append(praises[i]).append(" ");
		}
		System.out.println(sb);
	}

	static void dfs(int current, int accumulatedPraise) {
		praises[current] += accumulatedPraise;
		for (int subordinate : subordinates[current]) {
			dfs(subordinate, praises[current]);
		}
	}

}