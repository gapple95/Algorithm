import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        // { 삭제
        s = s.replace("{","");
        // } 삭제
        s = s.replace("}","");
        
        
        String[] strings = s.split(",");
            
        Map<String, Integer> map_str = new HashMap<>();
        for(String str: strings) {
            if(map_str.get(str) == null) {
                map_str.put(str, 0);
            } else {
                map_str.put(str, map_str.get(str) + 1);
            }
        }
        
        answer = new int[map_str.size()];
        
        for(String key: map_str.keySet()) {
            answer[map_str.size() - 1 - map_str.get(key)] = Integer.parseInt(key);
        }
    
        
        return answer;
    }
}