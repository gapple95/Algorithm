import java.util.HashMap;

class Solution {
    static HashMap<String, Integer> dic;
	static String[] Gaters = {"A","E","I","O","U"};
    
    public int solution(String word) {
        dic = new HashMap<String, Integer>();
		
		def("", 0);
		
        
        int answer = dic.get(word);
        return answer;
    }
    public static void def(String str, int len) {
		dic.put(str, dic.size());
		if(len==5)
			return;
		for(int i=0; i<5; i++) {
			def(str + Gaters[i], len + 1);
		}
	}
}