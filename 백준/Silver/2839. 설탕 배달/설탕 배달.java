import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 백준 2839 설탕 배달
 * 
 * 사탕가게에 설탕을 정확하게 N킬로그램을 배달해야한다.
 * 설탕공장에서 만드는 설탕은 봉지에 담겨져 있다. 봉지는 3킬로그램 봉지와 5킬로그램 봉지가 있다.
 * 
 * 최대한 적은 봉지를 구하는 프로그램
 * 
 * @입력
 * 첫째 줄에 배달해야할 설탕 N킬로그램이 입력
 * 
 * @출력
 * 상근이가 배달하는 봉지의 최소 개수를 출력한다. 만약, 정확하게 N킬로그램을 만들 수 없다면 -1을 출력한다.
 * 
 * @해결방안
 * N에 관하여 5의 배수만큼 제하고 나머지 3의 배수로 나눌수 있는지 구한다.
 * ! 5로 나누고 1일때는 6이 남은것이니 5봉지를 -1, 3kg로 2개이니 +2. -> +1
 *           2일때는 7이 남은것이니 5봉지를 -2, 3kg로 4개이니 +4. -> +2 
 *           3일때는 8이 남은것이니 5봉지를 -0, 3kg로 1개이니 +1. -> +1
 *           4일때는 9가 남은것이니 5봉지를 -1, 3kg로 3개이니 +3. -> +2
 * ! -1이 나오는 경우는..?
 *   10보다 작은 경우에서..! 이유는 5봉지를 더 뺄순 없으니깐
 *   그러면 몫을 구하고, 5봉지를 -1을 할 때 음수가 나오는 경우들은 전부 없다고 판단!
 *   
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int cal = N % 5;
		int value = N / 5;
		
		if(N == 3) {
			System.out.println(1);
			return;
		}
		
		if(N == 1 || N == 2 || N ==4 || N == 7) {
			System.out.println(-1);
			return;
		}
		
		switch (cal) {
		case 0:
			System.out.println(value);
			break;
		case 1:
			System.out.println(value + 1);
			break;
		case 2:
			System.out.println(value + 2);
			break;
		case 3:
			System.out.println(value + 1);
			break;
		case 4:
			System.out.println(value + 2);
			break;
		}
	}

}