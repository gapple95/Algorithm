import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 1987 알파벳
 * 
 * 메모리
 * 시간
 * 
 * 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩
 * 적혀있고, 좌측 상단 칸(1행 1열)에는 말이 놓여 있다.
 * 
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는
 * 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야한다. 즉, 같은 알파벳이 적힌 칸을
 * 두 번 지날 수 없다.
 * 
 * 좌측상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오.
 * 말이지나는 칸은 좌측 상단의 칸도 포함된다.
 * 
 * @입력
 * 첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다.(1<=R,C<=20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에
 * 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.
 * 
 * @출력
 * 첫째 줄에 말이 지날 수있는 최대의 칸 수를 출력한다.
 * 
 * @해결방안
 * 각 좌표에서의 DFS의 값으로 최댓값을 구한다.
 * 
 * 
 */

public class Main {

	static int R,C, max=Integer.MIN_VALUE;
	static char[][] map;
	static boolean selected[];
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		selected = new boolean[26];
		
		String str;
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
////				visited = new boolean[R][C];
////				selected = new boolean[26];
//				dfs(i,j,0);
//			}
//		}
		selected[map[0][0] - 'A'] = true;
		dfs(0,0,1);
		System.out.println(max);
	}

	public static void dfs(int i, int j, int level) {
		for(int d=0; d<4; d++) {
			int x = j + dx[d];
			int y = i + dy[d];
			
			if(x<0 || x>=C || y<0 || y>=R)
				continue;
			if(selected[map[y][x] - 'A'])
				continue;
			selected[map[y][x] - 'A'] = true;
			dfs(y, x, level+1);
			selected[map[y][x] - 'A'] = false;
		}

		max = Math.max(max, level);
		return;
	}
}