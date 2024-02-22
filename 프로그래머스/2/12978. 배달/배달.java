class Solution {
    
    static class Node {
		int to;
		Node next;
		int weight;
		
		public Node(int to, Node next, int weight) {
			super();
			this.to = to;
			this.next = next;
			this.weight = weight;
		}
	}
    
    static boolean[] answer_arr;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        Node[] list = new Node[N+1];
		
        for(int i=0; i <= N; i++) {
			list[i] = new Node(i, list[i], 0);
		}
		
		for (int i = 0; i < road.length; i++) {
			int a = road[i][0];
			int b = road[i][1];
			int c = road[i][2];
			
			list[a] = new Node(b, list[a], c);
			list[b] = new Node(a, list[b], c);
		}
		
		boolean[] visited = new boolean[N+1];
		answer_arr = new boolean[N+1];
		dfs(list, visited, 1, K, 0);

		for (int i = 1; i < N+1; i++) {
			if(answer_arr[i])answer++;
		}
		System.out.println(answer);

        return answer;
    }
    
    	public static void dfs(Node[] list, boolean[] visited, int cur, int K, int weight) {
		if(weight > K)
			return;
		for(Node temp=list[cur] ; temp!=null; temp=temp.next) {
			// 다음 곳이 이미 들린 곳이면 pass
			if(visited[temp.to])
				continue;
			// 다음 장소까지 가는데 걸리는 시간이 3초보다 더크면 pass
			if(weight + temp.weight > K)
				continue;
			visited[temp.to] = true;
            answer_arr[temp.to] = true;
			dfs(list, visited, temp.to, K, weight+temp.weight);
			visited[temp.to] = false;
		}
//		System.out.println(Arrays.toString(visited));
	}
}