import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 1759 암호 만들기
 * 
 * 메모리 : 
 * 시간 : 
 * 
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의
 * 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어있다고
 * 알려져 있다. 또한 정렬된 문자열을 선화는 조교들의 성향으로 미루어보아
 * 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고
 * 추측된다. 즉 abc는 가능성이 있는 암호지만 bac는 그렇지 않다.
 * 
 * 새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는
 * C가지가 있다고 한다. 이 알파벳을 입수한 민식, 영식 형제는
 * 조교들의 방에 침투하기 위해 암호를 추측해 보려고 한다.
 * C개의 문자들이 모두 주어졌을 때, 가능성이 있는 암호들을 모두 
 * 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에 두 정수 L,C가 주어진다.(3<=L<=C<=15) 다음 줄에는
 * C개의 문자들이 공백으로 구분되어 주어진다. 주어지는 문자들은
 * 알파벳 소문자이며, 중복되는 것은 없다.
 * 
 * @출력
 * 각 줄에 하나씩, 사전식으로 가능성 있는 암호를 모두 출력한다.
 * 
 * @해결방안
 * 조합문제. 6개의 알파벳중 4개의 알파벳을 출력한다.
 * arr을 먼저 sorting을 하고, 그 다음에 조합으로 출력
 * 
 * 최소한 모음하나는 포함되어야한다는 조건이 있다!
 * 자음은 2개이상! 이것도 조건에 넣어야할듯. => 역시나.. 26%까지 올라가고 틀렸다.
 * 
 */

public class Main {

	static char[] arr;
	static char[] picked;
	static int L, C;
	static char[] gather = { 'a', 'e', 'i', 'o', 'u' };
//	static char[] consonant = {};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[C];
		picked = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(arr);

		comb(0, 0);
	}

	static void comb(int idx, int start) {
		if (idx == L) {
			if(check())
				return;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < L; i++) {
				sb.append(picked[i]);
			}
			System.out.println(sb);
		}
		for (int i = start; i < C; i++) {
			picked[idx] = arr[i];
			comb(idx + 1, i + 1);
		}
	}
	
	static boolean check() {
		boolean result = false;
		//3글자인데 모음이 두개 이상이면 안된다.
		//4글자인데 모음이 세개 이상이면 안된다.
		//5글자인데 모음이 네개 이상이면 안된다.
		//L글자인데 모음이 L-1개 이상이면 안된다.
		int cnt = 0;
		A : for (int i = 0; i < L; i++) {
			for (int j = 0; j < 5; j++) {
				// 모음이 포함되어있으면 확인
				if (picked[i] == gather[j]) {
					cnt++;
				}
			}
		}
		if(cnt >= L-1 || cnt==0)
			result = true;
		return result;
	}
}