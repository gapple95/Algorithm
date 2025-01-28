import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();

		st = new StringTokenizer(br.readLine());
		String tnt = st.nextToken();

		Stack<Character> answer = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			answer.push(str.charAt(i));

			// 끝에서 계속 폭발 문자열을 확인
			if(answer.size() < tnt.length())
				continue;
			
			// 끝부터 확인해보기
			boolean flag = true;
			for (int j = 0; j < tnt.length(); j++) {
				if(answer.get(answer.size() - j - 1) != tnt.charAt(tnt.length() - j - 1))
					flag = false;
			}

			if(flag) {
				for (int j = 0; j < tnt.length(); j++) {
					answer.pop();
				}
			}
				
		}

		if(answer.isEmpty())
			sb.append("FRULA");
		else {
			for (char chr : answer) {
				sb.append(chr);
			}
		}

		System.out.println(sb);

		br.close();
	}

}
