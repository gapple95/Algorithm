import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		String A = st.nextToken();

		st = new StringTokenizer(br.readLine());
		String B = st.nextToken();

		st = new StringTokenizer(br.readLine());
		String C = st.nextToken();
		
		System.out.println((Integer.parseInt(A) + Integer.parseInt(B) - Integer.parseInt(C)));
		System.out.println((Integer.parseInt(A + B) - Integer.parseInt(C)));
		
	}

}
