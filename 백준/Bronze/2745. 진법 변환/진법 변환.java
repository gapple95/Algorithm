import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String N = st.nextToken();
		int B = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		int tmp = 0 ;
		for (int i = 0; i < N.length(); i++) {
//			System.out.println(i + " : " + N.charAt(i));
			if(N.charAt(i) >= 'A')
				tmp = N.charAt(i) - 'A' + 10;
			else
				tmp = N.charAt(i) - '0';
			
			answer += tmp * Math.pow(B, N.length() - i - 1);
		}
		System.out.println(answer);
	}

}