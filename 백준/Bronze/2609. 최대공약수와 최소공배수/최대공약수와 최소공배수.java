import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2609 최대공약수 최소공배수
 * 
 * 메모리 
 * 시간 
 * 
 * 두개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에는 두 개의 자연수가 주어진다. 이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.
 * 
 * @출력
 * 첫째 줄에는 입력으로 주어진 두 수의 최대공약수를, 둘째 줄에는 입력으로 주어진 두수의 공배수를
 * 출력한다.
 * 
 * @해결방안
 * 최대공약수를 구하면 최소공배수도 구할수 있다.
 * A*B / 최대공약수 = 최소공배수
 * 
 * 최대공약수는 a,b를 b = b % a; 로 하여서 구한다.
 * 
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int G, L;

		int max = Math.max(A, B);
		int min = Math.min(A, B);

		G = gcd(max, min);
		L = lcm(max, min);
		sb.append(G).append("\n").append(L);
		System.out.println(sb);
	}

	static int gcd(int a, int b) {
		if(b==0) return a;
		return gcd(b, a%b);
	}

	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
}