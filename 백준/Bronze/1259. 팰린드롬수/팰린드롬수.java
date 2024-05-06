import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String num = br.readLine();
			if(num.equals("0"))
				break;
			int start = 0;
			int end = num.length() - 1;
			boolean check = false;
			while(start <= end) {
				if(num.charAt(start) != num.charAt(end)) {
					check = true;
					break;
				}
				start++;
				end--;
			}
			sb.append(check?"no":"yes").append("\n");
		}
		System.out.println(sb);
	}

}