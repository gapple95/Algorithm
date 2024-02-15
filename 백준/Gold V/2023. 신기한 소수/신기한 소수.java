import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] head_num = { 2, 3, 5, 7 };
	static int[] body_num = { 1, 3, 7, 9 };
	static int[] picked;
	static boolean[] isSelected;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		picked = new int[N];
		isSelected = new boolean[4];
		
		for (int i = 0; i < head_num.length; i++) {
			picked[0] = head_num[i];
			perm(1, head_num[i]);
		}
	}

	public static void perm(int idx, int head) {
		if(idx == N) {
			int sum = 0;
			for(int i=0;i<N; i++) {
				sum += picked[i];
				if(!check(sum)) {
					return;
				}
				sum *= 10;
			}
			System.out.println(sum/10);
			return;
		}
		for(int i=0 ; i<4; i++) {
			picked[idx] = body_num[i];
			perm(idx+1, head);
		}
	}
	
	// 소수확인 함수
	public static boolean check(int num) {
		for (int i = 2; i < num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
}