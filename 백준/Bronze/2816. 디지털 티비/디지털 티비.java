import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N;
		List<String> list;
		
		N = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		String str;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			str = st.nextToken();
			list.add(str);
		}
		
		int cur = 0;
		String tmp;
		while(true) {
			if(list.get(0).equals("KBS1") && list.get(1).equals("KBS2"))
				break;
			
			if(!list.get(0).equals("KBS1")) {
				if(!list.get(cur).equals("KBS1")) {	
					cur++;
					sb.append(1);
				}
				else if(list.get(cur).equals("KBS1")) {
					tmp = list.get(cur-1);
					list.set(cur-1, list.get(cur));
					list.set(cur, tmp);
					cur--;
					sb.append(4);
				}
			}
			else if(!list.get(1).equals("KBS2")) {
				if(!list.get(cur).equals("KBS2")) {	
					cur++;
					sb.append(1);
				}
				else if(list.get(cur).equals("KBS2")) {
					tmp = list.get(cur-1);
					list.set(cur-1, list.get(cur));
					list.set(cur, tmp);
					cur--;
					sb.append(4);
				}
			}
		}
		
		System.out.println(sb);
		
		br.close();
	}

}
