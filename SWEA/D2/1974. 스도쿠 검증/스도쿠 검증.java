import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * SWEA 1974 스도쿠 검증
 * 
 * 스도쿠 검증
 * 3X3에서 1~9까지 겹치지 않아야 한다.
 * 세로줄 가로줄에서도 겹치지 않아야한다.
 * 
 * @입력
 * 첫 줄에 테스트 케이스 개수 T가 온다.
 * 다음줄부터 9개의 줄에 9*9 크기의 퍼즐 데이터가 온다.
 * 
 * @출력
 * "#t"을 찍고 한칸 띄고 정답을 출력한다.
 * 정답은 1, 아니면 0
 * 
 * @해결방안
 * 45값을 확인을 한다.
 * 33작은격자까지 볼필요 없지 않을까
 * 세로줄 가로줄 확인
 * 
 * 비트마스킹을 이용
 * 
 * 
 */

public class Solution {

	static int[][] arr;
	static int[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			boolean cFlag = true;
			boolean rFlag = true;
			boolean lFlag = true;

			arr = new int[9][9];

			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < 9; i++) {
				if (!(cFlag && rFlag))
					break;
				cFlag = colCheck(i);
				rFlag = rowCheck(i);
			}
			A : if ((cFlag && rFlag)) {
				for (int i = 0; i < 9; i += 3) {
					for (int j = 0; j < 9; j += 3) {
						if(!lFlag)
							break A;
						lFlag = layerCheck(j, i);
					}
				}
			}

			System.out.print("#" + t + " ");
			if (cFlag && rFlag && lFlag)
				System.out.println("1");
			else
				System.out.println("0");
		}
	}

	static boolean colCheck(int col) {
		boolean result = true;
		check = new int[10];
		for (int i = 0; i < 9; i++) {
			check[arr[i][col]]++;
		}

		for (int i = 1; i <= 9; i++) {
			if (check[i] != 1) {
				result = false;
				break;
			}
		}

		return result;
	}

	static boolean rowCheck(int row) {
		boolean result = true;
		check = new int[10];
		for (int i = 0; i < 9; i++) {
			check[arr[row][i]]++;
		}

		for (int i = 1; i <= 9; i++) {
			if (check[i] != 1) {
				result = false;
				break;
			}
		}
		return result;
	}

	static boolean layerCheck(int x, int y) { // 왼쪽 위 모서리
		boolean result = true;
		check = new int[10];
//		int cnt = 1;
		for (int i = y; i < y + 3; i++) {
			for (int j = x; j < x + 3; j++) {
				check[arr[i][j]]++;
			}
		}

		for (int i = 1; i <= 9; i++) {
			if (check[i] != 1) {
				result = false;
				break;
			}
		}
		return result;
	}
}