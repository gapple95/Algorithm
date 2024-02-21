import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 10026 적록색양
 * 
 * 메모리 12356
 * 시간 96
 * 
 * 크기가 NxN인 그리드의 각 칸에 RGB중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구경으로 나뉘어져 있는데,
 * 구역은 같은 색으로 이루어져 있다. 또, 같은 색상이 상하좌우로 인접해있는경우
 * 같은 구역에 속한다.
 * 
RRRBB
GGBBB
BBBRR
BBRRR
RRRRR
 * 
 * 위의 경우의수는 빨강 2 파랑 1 초록1이지만
 * 적록색약인 사람은 구역을 3개 볼수 있다.
 * 빨강-초록2, 파랑1
 * 
 * @입력
 * 첫째줄에 N(1<=N<=100)
 * 둘째줄부터 N개 줄에는 그림이 주어진다.
 * 
 * @출력
 * 적록색야이 아닌사람이 봤을때 구역 + " " + 적록색약인사람이 봤을때 구역
 * 
 * @해결방안
 * BFS로 구역을 나누는것을 진행한다.
 * 적록색약일 경우 같은 색이 붙어있을 경우 다른 지역으로 인식해야하므로
 * R에 붙어 있는 색깔이 아닌 다른 색으로 치환해서 문제 풀기
 * 
 */
public class Main {

	static int N;
	static char map[][];
	static boolean visited[][];
	static int R,G,B;
	
	static char color;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j); 
			}
		}
		
		R=0;
		G=0;
		B=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j])
					continue;
				dfs(map[i][j], i, j);
				switch(map[i][j]) {
				case 'R':
					R++;
					break;
				case 'G':
					G++;
					break;
				case 'B':
					B++;
					break;
				}
				
			}
		}
		System.out.print((R+G+B) + " ");
		
		R=0;
		B=0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j])
					continue;
				dfs2(map[i][j], i, j);
				switch(map[i][j]) {
				case 'R':
					R++;
					break;
				case 'G':
					G++;
					break;
				case 'B':
					B++;
					break;
				}
			}
		}
		System.out.println(R+B);
	}

	public static void dfs(char c, int i, int j) {
		visited[i][j] = true;
		
		for(int d=0; d<4; d++) {
			int x = j + dx[d];
			int y = i + dy[d];
			
			if(x<0 || x>=N || y<0 || y>=N)
				continue;
			if(visited[y][x])
				continue;
			if(map[y][x] != c)
				continue;
			dfs(c, y, x);
		}
	}
	
	public static void dfs2(char c, int i, int j) {
visited[i][j] = true;
		
		for(int d=0; d<4; d++) {
			int x = j + dx[d];
			int y = i + dy[d];
			
			if(x<0 || x>=N || y<0 || y>=N)
				continue;
			if(visited[y][x])
				continue;
			if(map[y][x] == 'G')
				map[y][x] = 'R';
			if(map[y][x] != c)
				continue;
			dfs2(c, y, x);
		}
	}
	
	public static void print(char c) {
		System.out.println(c);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}