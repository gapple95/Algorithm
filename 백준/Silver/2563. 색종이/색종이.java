import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 백준 2563 색종이
 * 
 * 메모리 : 
 * 시간 : 
 * 
 * 가로, 세로 크기가 각각 100인 정사각형 모양의 흰색 도화지가 있따.
 * 이 도와지 위에 가로, 세로의 크기가 각각 10인 정사각형 모양의 검은색
 * 색종이를 색종이의 변과 도화지의 변이 평행하도록 붙인다. 이러한 방식으로
 * 색종이를 한 장 또는 여러 장 붙인 후 색종이가 붙은 검은 영역의 넓이를 구하는
 * 프로그램을 작성하시오.
 * 
 * @ 입력
 * 첫째 줄에 색종이의 수가 주어진다. 100이하의 자연수
 * 둘째 줄 부터 한줄에 하나씩 색종이를 붙인 위치가 주어진다.
 * 두가지 자연수가 주어지는데 첫 번째 자연수는 색종이의 왼쪽변과
 * 도화지의 왼쪽 변 사이의 거리이고,
 * 두번째 자연수는 색종이의 아래쪽 변과 도화지의 아래쪽 변 사이의 거리이다.
 * 
 * @출력
 * 첫째 줄에 색종이가 붙은 검은 영역의 넓이를 출력한다.
 * 
 * @해결방안
 * 100 * 100에 겹치는 부분을 제외하고 나머지들을 카운팅
 * 
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[100][100];
		int x,y;
		

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			for(int i=y; i<y+10; i++) {
				for(int j=x; j<x+10; j++) {
					if(map[i][j] == 1) continue;
					map[i][j] = 1;
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] == 1)
					sum++;
			}
		}
		System.out.println(sum);
	}

}