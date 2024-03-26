import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static class Word implements Comparable<Word> {
		int cnt=0;
		String word;
		
		public Word(int cnt, String word) {
			this.cnt = cnt;
			this.word = word;
		}

		@Override
		public int compareTo(Word o) {
			if(o.cnt < cnt)
				return -1;
			else if (o.cnt == cnt){
				if(o.word.length() < word.length()) 
					return -1;
				else if(o.word.length() == word.length()) {
					return word.compareTo(o.word);
				}
			}
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Word> dic = new HashMap<>();
		
		Word tmp;
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			if(str.length() < M)
				continue;
			
			if(dic.containsKey(str)) {
				tmp = dic.get(str);
				tmp.cnt++;
			} else {
				dic.put(str, new Word(0, str));
			}
		}
		
		List<Word> dic_array = new ArrayList<>();
		for(String word: dic.keySet()) {
			dic_array.add(dic.get(word));
		}
		
		Collections.sort(dic_array);
		
		for(Word word: dic_array) {
			sb.append(word.word).append("\n");
		}
		
		System.out.println(sb);
	}
}