import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static Map<String, Integer> game;
	static int N, game_type;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		game = new HashMap<>();
		
		game.put("Y", 1);
		game.put("F", 2);
		game.put("O", 3);
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		game_type = game.get(st.nextToken());
		
		Set<String> person = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			person.add(st.nextToken());
		}
		
		sb.append(person.size() / game_type);
		
		System.out.println(sb);
		
		br.close();
	}

}
