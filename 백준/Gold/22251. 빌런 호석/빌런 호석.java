import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,K,P,X;
	
	static int[] num = {
        0b1111110, // 0
        0b0110000, // 1
        0b1101101, // 2
        0b1111001, // 3
        0b0110011, // 4
        0b1011011, // 5
        0b1011111, // 6
        0b1110000, // 7
        0b1111111, // 8
        0b1111011  // 9
    };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		int[] cur = new int[K];
		
		int x = X;
		for (int i = 0; i < K; i++) {
			cur[i] = x % 10;
			x /= 10;
		}
		
		int answer = 0;
		int cnt, tmp;
		for(int i=1; i<=N; i++) {
			if(i==X)
				continue;
			cnt = 0;
			tmp = i;
			for(int j=0; j < K ; j++) {
				cnt += Integer.bitCount(num[tmp % 10] ^ num[cur[j]]);
				tmp /= 10;
			}
			if(cnt <= P && cnt >= 1)
				answer++;
		}
		
		sb.append(answer);
		
		System.out.println(sb);
		
		br.close();
	}

}
