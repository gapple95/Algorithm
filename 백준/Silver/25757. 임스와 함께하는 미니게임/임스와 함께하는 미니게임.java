import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N=0, game_type=-1;
		String type;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		type = st.nextToken();
		
		switch(type) {
		case "Y":
			game_type=1;
			break;
		case "F":
			game_type=2;
			break;
		case "O":
			game_type=3;
			break;
		}
		
		Set<String> person = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			person.add(br.readLine());
		}
		
		sb.append(person.size() / game_type);
		
		System.out.println(sb);
		
		br.close();
	}

}
