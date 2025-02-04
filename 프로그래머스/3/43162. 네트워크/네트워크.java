import java.util.*;

class Solution {
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        
        for(int i=0; i<n; i++) {
            if(visited[i])
                continue;
            answer++;
            func(i, n, computers);
        }
        
        return answer;
    }
    
    public void func(int target, int n, int[][] computers) {
        for(int i=0; i<n; i++) {
            if(target == i)
                continue;
            if(computers[target][i] == 0)
                continue;
            if(visited[i])
                continue;
            visited[i] = true;
            func(i, n, computers);
        }
    }
}