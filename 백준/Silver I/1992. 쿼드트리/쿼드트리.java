import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 백준 1992 쿼드트리
 * 
 * 메모리
 * 시간
 * 
 * 흑백영상을 압축하여 표현하는 데이터 구조로 쿼드 트리라는 방법이 있다.
 * 흰점을 나타내는 0과 검은점을 나타내는 1로만 이루어진 2차원 배열/영상에서 같은 숫자의
 * 점들이 한곳에 많이 몰렸으면, 쿼드 트리에서는 ㅣ을 랑ㅂ축하여 간단히 표현할 수 있다.
 * 
 * 주어진 영상이 모두 0으로만 되어있으면 압축결과는 "0"이 되고, 모두 1로만 되어있으면
 * 압축 결과는 "1"이 된다. 만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고,
 * 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며,
 * 이 4개의 영역을 압훅한 결과를 차례대로 괄호 안에 묶어서 표현한다.
 
 0 0 0 0 0 0 0 0
 0 0 0 0 0 0 0 0
 0 0 0 0 1 1 1 1
 0 0 0 0 1 1 1 1
 0 0 0 1 1 1 1 1
 0 0 1 1 1 1 1 1
 0 0 1 1 1 1 1 1
 0 0 1 1 1 1 1 1
 
 위의 배열은 "(0(0011)(0(0111)01)1)"로 표현된다.
  
 *
 * NxN이 주어질때 압축한 결과를 출력하시오.
 * 
 * @입력
 * 첫째줄에 영상의 크기를 나타내는 숫자 N이며 언제 2의 제곱수로 주어진다. 1<=N<64
 * 두번째 부터 N줄 N열의 문자열 0또는 1의 숫자로 이루어진 배열이 입력
 * 
 * @출력
 * 영상을 압축한 결과를 출력한다.
 * 
 * @해결방안
 * 4등분하여 확인한다.하나의 크기마다 ()로 표현한다.
 * 각안에는 ()로 또 표현이 가능하다.
 * N=8인경우에 가장 작은 단위는 (0001)이런식으로 표현이 된다.
 * 한칸이 들어가기 때문.
 * 
 * 재귀로 구현하여 
 * 
 */

public class Main {

	static int N;
	static int map[][];
	static String answer = "";
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				int value = tmp.charAt(j) - '0';
				map[i][j] = value;
				count+= value;
			}
		}

		df(0,0,N);
		
		System.out.println(answer);
	}
	
	public static void df(int x, int y, int n) {
		if(isSame(x,y,n)) {
			answer += map[y][x];
			return;
		}
		answer += "(";

		int half = n / 2;

		//1사분면 왼쪽 위
		df(x, y, half);
		
		//2사분면
		df(x + half, y, half);
		
		//3사분면
		df(x, y + half, half);
		
		//4사분면
		df(x + half, y + half, half);

		answer += ")";
	}

	public static boolean isSame(int x, int y, int n) {
		int value = map[y][x];
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if(map[i][j] != value) return false;
			}
		}
		return true;
	}
}