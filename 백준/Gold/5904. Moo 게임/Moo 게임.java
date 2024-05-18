import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		int k = 0;
		int leng = 3;
		int preK = 3;

		while (leng < n) {
			k++;
			leng = 2 * preK + (k + 3);
			preK = leng;
		}

		preK = (leng - (k + 3)) / 2;
		String answer = "";

		if (k == 0) {
			if (n == 1)
				answer = "m";
			else
				answer = "o";
		} else {
			if (preK + 1 <= n && n < preK + (k + 3)) {
				if (preK + 1 == n) {
					answer = "m";
				} else
					answer = "o";
			} else
				answer = func(preK + (k + 3) + 1, k - 1, leng);
		}

		sb.append(answer);

		System.out.println(sb);

	}

	public static String func(int left, int k, int right) {
		int index = ((right - left + 1 - (k + 3)) / 2);

		if (k == 0) {
			if (n == left)
				return "m";
			else
				return "o";
		}

		if (left <= n && n < left + index) {
			if (left == n)
				return "m";
			return func(left, k - 1, left + index - 1);
		} else if (left + index + (k + 3) <= n && n <= right) {
			if (left + index + (k + 3) == n)
				return "m";
			else
				return func(left + index + (k + 3), k - 1, right);
		} else {
			if (left + index == n)
				return "m";
			else
				return "o";
		}
	}
}