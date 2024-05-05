import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int[] arr = new int[8];
		boolean checkAsc = false, checkDes = false;
		arr[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < 8; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i-1] - arr[i] == -1)
				checkAsc = true;
			else if(arr[i-1] - arr[i] == 1)
				checkDes = true;
			else {
				sb.append("mixed");
				System.out.println(sb);
				System.exit(0);
			}
		}
		
		if(checkAsc && !checkDes)
			sb.append("ascending");
		else if(!checkAsc && checkDes)
			sb.append("descending");
		else
			sb.append("mixed");
		
		System.out.println(sb);
		
	}

}