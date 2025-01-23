import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		String first = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		String second = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		String third = st.nextToken();
		
		int num;
		if(first.charAt(0) >= '0' && first.charAt(0) <= '9')
			num = Integer.parseInt(first) + 3;
		else if (second.charAt(0) >= '0' && second.charAt(0) <= '9')
			num = Integer.parseInt(second) + 2;
		else
			num = Integer.parseInt(third) + 1;
		
		if(num % 3 == 0 && num % 5 == 0)
			sb.append("FizzBuzz");
		else if(num % 3 == 0 && num % 5 != 0)
			sb.append("Fizz");
		else if(num % 3 != 0 && num % 5 == 0)
			sb.append("Buzz");
		else
			sb.append(num);
		
		System.out.println(sb);
		
		br.close();
	}

}
