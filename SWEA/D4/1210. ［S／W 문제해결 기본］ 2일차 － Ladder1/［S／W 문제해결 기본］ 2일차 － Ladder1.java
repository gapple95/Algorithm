/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int[] dx = { -1, 1 };
	public static void main(String args[]) throws Exception
	{
		Scanner s = new Scanner(System.in);
		for (int t = 1; t <= 10; t++) {
			int test_case = s.nextInt();
			int[][] map = new int[100][100];
//		int[][] map = new int[10][10];
			int tmp;
			int endX = 0, endY = 0;

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					tmp = s.nextInt();
					map[i][j] = tmp;
					if (tmp == 2) {
						endX = j;
						endY = i;
					}
				}
			}

//		System.out.printf("Strat X : %d, Y : %d\n",endX,endY);

			while (endY != 0) {
				endY--;
				for (int i = 0; i < 2; i++) {
					if (endX + dx[i] >= 100 || endX + dx[i] < 0) // 확인할 때 범위를 벗어 나는지 확인
						continue;
					if (map[endY][endX + dx[i]] == 1) { // 양쪽을 확인한다.
						while (endX + dx[i] < 100 && endX + dx[i] >= 0) { // 범위 안에서만
							if (map[endY][endX + dx[i]] == 0) { // 범위를 벗어 나면
								break;
							}
							endX += dx[i];
						}
						break;
					}
				}
//			System.out.printf("X : %d, Y : %d\n",endX,endY);
			}
			System.out.printf("#%d %d\n", test_case, endX);
        }
	}
}