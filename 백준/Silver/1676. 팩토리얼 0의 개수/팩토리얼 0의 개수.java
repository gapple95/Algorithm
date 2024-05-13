import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int mul5 = 0;
		int mul25 = 0;
		int mul125 = 0;
		
		mul5 = N / 5;
		mul25 = N / 25;
		mul125 = N / 125;
		
		sb.append(mul5+mul25+mul125);
		System.out.println(sb);
	}

}