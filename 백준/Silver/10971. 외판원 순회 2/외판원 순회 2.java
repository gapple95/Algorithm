import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
 * 백준 10971 외판원 순회 2
 * 
 * 메모리 
 * 시간 
 * 
 * 1번부터 N번까지 번호가 매겨져 있는 도시들이 있고, 도시들 사이에는 길이있다(없을수도 있다.).
 * 한 외판원이 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행경로를
 * 게획할려고 한다. 단, 한 번 갔던 도시로는 갈 수 없다(맨 마지막에 여행을 출발햇던 도시로 돌아오는 것은 예외)
 * 
 * 각 도시로 이동하는데 드는 비용은 행렬 W[i][j]형태로 주어진다. i=>j로 가는 비용 갈 수 없을때는 0
 * 
 * @입력
 * 첫째 줄에 도시 수 N(2~10)
 * 각 행렬의 성분은 1,000,000 이하의 양의 정수
 * 
 * 항상 순회할 수 있는 경우만 입력
 * 
 * @출력
 * 첫째 줄에 외판원의 순회에 필요한 비용을 출력한다.
 * 
 * @해결방안
 * 1번 도시에서 ~ N번도시들을 돌고 다시 1번으로 돌아와야한다.(X)
 * 각 어떤 도시 하나를 선택해서 최소인 경우
 * DFS로 풀어야한다. 최악의 경우 9^10의 경우를 10번 돌아야한다. 가지치기까지하면 간신히 될지도..?
 * 다익스트라를 구현해서 사용한다. 각각의 도시들을 다익스트라로 최소 거리를 구한다.
 * 아니면.. MST? 싸이클이 안되기 때문에 전체 순환이 가능하고, 마지막에만 자신으로 돌아올수 있는 경우를 구한다면?
 * 각 도시를 kruscal로 돌면서 자신에게 돌아오고, 그게 가장 작은 값을 구하자.
 * 
 * 1. find-union으로 kruscal을 구한다.
 * 2. 해당 도시가 마지막 트리에서 자신으로 돌아오면, 순회가 가능하다고 판단.
 * 3. 모든 도시에서 순회가 가능한 도시들의 최소 비용을 계산.
 * 
 * 희정이 아이디어.
 * 순열로 모든 경우의수를 구한다.
 * 번호를 구해도 안되는 경우는 그냥 제외.
 * 마지막에 순서가 나오면, 인접행렬을 참고하여 순회 비용을 계산.
 * N번 반복하여 최솟값을 계산.
 * 
 * 시간복잡도.
 * 10! * 10 = 약 3600만
 * 
 * 
 */

public class Main {

	static int N, map[][], picked[], min = Integer.MAX_VALUE;
	static boolean visited[];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
				
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		picked = new int[N+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(1);
		System.out.println(min);
	}

	
	public static void perm(int idx) {
		if(idx == N) {
			picked[0] = 1;
			picked[N] = 1;
			// 순열이 나오면
//			System.out.println(Arrays.toString(picked));
			int sum = 0;
			for (int i = 0; i < N; i++) {
//				System.out.println(picked[i] + " " + picked[i+1]);
				int tmp = map[picked[i]-1][picked[i+1]-1];
				
				if(tmp==0) { // 인접행렬이 0이면 갈수 없으므로 다음 순서로
					return;
				} else { // weight가 있으면 더하기
					sum += tmp;
				}
			}
			min = Math.min(min, sum);
			return;
		}
		for (int i = 1; i < N; i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			picked[idx] = i+1;
			perm(idx+1);
			visited[i] = false;
		}
	}
}