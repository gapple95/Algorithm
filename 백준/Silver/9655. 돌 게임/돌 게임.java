import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
 * 백준 9655 돌 게임
 * 
 * 돌 게임은 두 명이서 즐기는 재밌는 게임이다.
 * 탁자위에 돌 N개가 있다. 상근이와 창영이는 턴을 번갈아가면서 돌을
 * 가져가며, 돌은 1개 또는 3개 가져갈 수 있다. 마지막 돌을 가져가는 사람이
 * 게임을 이기게 된다.
 * 
 * 두사람이 완벽하게 게임을 했을 때, 이기는 사람을 구하는
 * 프로그램을 작성하시오. 게임은 상근이가 먼저 시작한다.
 * 
 * @입력
 * 첫째 줄에 N이 주어진다.(1<=N<=1000)
 * 
 * @출력
 * 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY을 출력한다.
 * 
 * @해결방안
 * 1과 3으로 최선을 다해서 게임을 한다면, N이 5이고
 * 상근이가 처음으로 시작을 하기 떄문에 상근이는 1 or 3을 낼수있다.
 * 상근이가 1을 냈을 떄는 창영이가 1이나 3개를 가져가면 다음에 상근이가
 * 1개 혹은 3개를 먹으면서 이긴다.
 * 상근이가 3을 낼을 때는, 어쩔수 없이 창영이가 1을 내므로
 * 상근이가 1을 먹으면서 게임을 이긴다.
 * 
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		if(N%2==0)
			sb.append("CY");
		else
			sb.append("SK");
		System.out.println(sb);
	}

}