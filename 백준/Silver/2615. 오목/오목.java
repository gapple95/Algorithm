import java.util.Scanner;

public class Main {
	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[][] arr;
	static int[][] arr2;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("Test5.txt"));
		// 여기에 코드를 작성하세요.
		Scanner s = new Scanner(System.in);

		arr = new int[19][19];
		arr2 = new int[19][19];

		// 바둑판
		for (int i = 0; i < 19; i++) { // y
			for (int j = 0; j < 19; j++) { // x
				arr[i][j] = s.nextInt();
			}
		}

		int value4;
		int value5;

		int value;
		// 왼 대각
		int leftValue;
		// 오른 대각
		int rightValue;
		// 세로
		int HValue;
		// 가로
		int WValue;

		// 빈공간 0, 검은 바둑알 1, 흰 바둑알 2
		for (int x = 0; x < 19; x++) { // y
			for (int y = 0; y < 19; y++) { // x

				// 왼 대각
				leftValue = 0;

				// 오른 대각
				rightValue = 0;
				// 상
				WValue = 0;
				// 하
				HValue = 0;
				if (arr[y][x] == 0)
					continue;
				for (int d = 0; d < 8; d++) {
					value = checkWin(x, y, arr[y][x], d);

//					System.out.printf("x : %d, y : %d, value : %d\n", x + 1, y + 1, value);

					if (d == 0 || d == 4) {
						leftValue += value;
					} else if (d == 1 || d == 5) {
						WValue += value;
					} else if (d == 2 || d == 6) {
						rightValue += value;
					} else if (d == 3 || d == 7) {
						HValue += value;
					}

				}
//				System.out.printf("left %d \t WValue %d \t rightValue %d \t HValue %d\n",leftValue,WValue,rightValue,HValue);
				if ((leftValue == 4 && leftValue < 5) || (WValue == 4 && WValue < 5)
						|| (rightValue == 4 && rightValue < 5) || (HValue == 4 && HValue < 5)) {
					System.out.println(arr[y][x]);
					System.out.printf("%d %d\n", y + 1, x + 1);
					return;
				}
			}
		}
		System.out.println(0);
		return;

	}

	public static int checkWin(int x, int y, int s, int d) {
		// 범위 확인
		if (x + dx[d] >= 19 | x + dx[d] < 0)
			return 0;
		if (y + dy[d] >= 19 | y + dy[d] < 0)
			return 0;

		int i = x + dx[d];
		int j = y + dy[d];
//		System.out.println(i + ", " + j);
		if (arr[j][i] == s) {
			return 1 + checkWin(i, j, s, d);
		}

		return 0;
	}
}
