import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    
    public int solution(int[][] maps) {
        int answer = -1;
		int N = maps.length;
		int M = maps[0].length;
        
        
	    int[] dx = {0,0,-1,1};
	    int[] dy = {-1,1,0,0};
		
		int[][] isSelected = new int[N][M];
		
		Queue<Integer> q = new ArrayDeque<>();

		q.offer(0);
		q.offer(0);
		isSelected[0][0] = 1;

		while(!q.isEmpty()) {

			int y = q.poll();
			int x = q.poll();
			int cnt = isSelected[y][x];
			
			// 현재 위치가 도착 지점이라면..
			if(y == N - 1 && x == M - 1) {
				answer = cnt;
				break;
			}
			for(int d=0; d<4; d++) {
				int _x = x + dy[d];
				int _y = y + dx[d];
				if(_x<0 | _x>=M | _y<0 | _y >=N) {
					continue;
				}
				if(isSelected[_y][_x] != 0)
					continue;
				if(maps[_y][_x] == 1) {
					q.offer(_y);
					q.offer(_x);
					isSelected[_y][_x] = cnt + 1;
				}
			}
		}
		return answer;
    }
}