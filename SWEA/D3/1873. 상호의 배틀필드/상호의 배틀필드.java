import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA 1873 상호의 배틀필드 
 * 
 * 단하나의 전차만 있으며 적이나 아군은 없다
 * 격자판으로 이우러진 게임 맵은 아래와 같은 구성 요소가 있다.
 * 
문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

 * 사용자가 넣을 수 있는 입력의 종류는 다음과 같다.
U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 * 
 * 전차가 이동할려고 할 때, 만약 게임 맵 밖이면 이동하지 않는다.
 * 
 * 전차가 포탄을 발사하면, 포탄은 벽돌로 만들어진 벽 또는
 * 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈때까지 직진한다.
 * 
 * 만약 포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이
 * 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
 * 
 * 강철로 만들어진 벽에 포탄이 부딪히면 아무일도 일어나지 않는다.
 * 
 * 게임 맵 밖으로 포탄이 나가면 아무런 일도 일어나지 않는다.
 * 
 * 초기 게임맵의 상태와 사용자가 넣을 입력이 순서대로 주어질때,
 * 모든 입력을 처리하고 나면 게임 맵의 상태가 어떻게 되는지 구하시오
 * 
 * @입력
 * 첫번쨰 줄에 테스트케이스 T
 * 
 * 각 테스트 케이스의 첫번째 줄에는 두 정수 H,W(2<=H,W<=20)
 * 이는 게임 맵의 높이가 H, 너비가 W
 * 
 * H개의 각각의 줄에는 길이가 W인 문자열이 주어진다.
 * 
 * 다음 줄에는 사용자가 넣을 입력의 개수 정수 N(0<N<=100)
 * 다음 줄에는 길이가 N인 문자열이 주어진다.
 * 
 * @출력
 * 각테스트 케이스마다 #T를 출력 한칸을 띄운 후  
 * 모든 입력을 처리하고 난 후 게임의 맵을 H개의 줄에 걸쳐 출력한다.
 * 
 * 
 */

public class Solution {

	static int[][] map;
	static int H, W, N;

	static int x, y, sx, sy; // 탱크의 위치
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int d; //탱크 방향

	static int[] cmd;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				for (int j = 0; j < str.length(); j++) {
					char tmp = str.charAt(j);
					map[i][j] = tmp;
					if (tmp == '^') {
						d = 0;
						x = j;
						y = i;
					}
					if (tmp == 'v') {
						d = 1;
						x = j;
						y = i;
					}
					if (tmp == '<') {
						d = 2;
						x = j;
						y = i;
					}
					if (tmp == '>') {
						d = 3;
						x = j;
						y = i;
					}
				}
			}

			// 명령어 파트
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cmd = new int[N];

			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int i = 0; i < N; i++) {
//				System.out.println(str.charAt(i));
				switch (str.charAt(i)) {
				case 'U': // 평지(전차가 들어갈 수 있다.
					Up();
					break;
				case 'D':
					Down();
					break;
				case 'L':
					Left();
					break;
				case 'R':
					Right();
					break;
				case 'S':
					Shoot();
					break;
				}
			}

			// 결과값
			System.out.printf("#%d ", t);
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print((char) map[i][j]);
				}
				System.out.println();
			}
		}
	}

	public static void Up() {
		// 전차가 바라보는 방향을 위쪽으로 바꾸고
		d = 0;
		map[y][x] = '^';
		// 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
		if (y - 1 >= 0 && map[y - 1][x] == '.') { // 맵밖이라면 제외
			map[y][x] = '.';
			x += dx[d];
			y += dy[d];
			map[y][x] = '^';
			
		}
	}

	public static void Down() {
		// 전차가 바라보는 방향을 아래쪽으로 바꾸고
		d = 1;
		map[y][x] = 'v';
		// 한 칸 아래의 칸이 평지라면 아래 그 칸으로 이동한다.
		if (y + 1 < H && map[y + 1][x] == '.') { // 맵밖이라면 제외
			map[y][x] = '.';
			x += dx[d];
			y += dy[d];
			map[y][x] = 'v';
		}
	}

	public static void Left() {
		// 전차가 바라보는 방향을 왼쪽으로 바꾸고
		d = 2;
		map[y][x] = '<';
		// 한 칸 왼쪽 칸이 평지라면 왼쪽의 그 칸으로 이동한다.
		if (x - 1 >= 0 && map[y][x - 1] == '.') { // 맵밖이라면 제외
			map[y][x] = '.';
			x += dx[d];
			y += dy[d];
			map[y][x] = '<';
		}
	}

	public static void Right() {
		// 전차가 바라보는 방향을 오른쪽으로 바꾸고
		d = 3;
		map[y][x] = '>';
		// 한 칸 오른쪽 칸이 평지라면 오른쪽의 그 칸으로 이동한다.
		if (x + 1 < W && map[y][x + 1] == '.') { // 맵밖이라면 제외
			map[y][x] = '.';
			x += dx[d];
			y += dy[d];
			map[y][x] = '>';
		}
	}

	public static void Shoot() {
		sx = x;
		sy = y;
		while(true) {
			sx += dx[d];
			sy += dy[d];
			if(sx < 0 || sx >= W || sy < 0 || sy >= H) {
				sx = x;
				sy = y;
				break;
			}
			else if(map[sy][sx] =='*') {
				map[sy][sx] = '.';
				sx = x;
				sy = y;
				break;
			}
			else if(map[sy][sx] =='#') {
				map[sy][sx] = '#';
				sx = x;
				sy = y;
				break;
			}
				
		}
	}
}