class Solution {
    int n, size, min;
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        min = Integer.MAX_VALUE;
        n = words.length;
        size = begin.length();
        visited = new boolean[n];
        
        func(begin, target, 0, words);
        
        if(min == Integer.MAX_VALUE)
            answer = 0;
        else
            answer = min;
        
        return answer;
    }
    
    public void func(String begin, String target, int depth, String[] words) {
        if(begin.equals(target)) {
            min = Math.min(min, depth);
            return;
        }
        
        for(int i=0; i<n; i++) {
            if(!check(begin, words[i]))
                continue;
            if(visited[i])
                continue;
            visited[i] = true;
            func(words[i], target, depth + 1, words);
            visited[i] = false;
        }
    }
    
    // 변할 수 있는지 없는지 확인하는 함수
    public boolean check(String str1, String str2) {
        int cnt = 0;
        for(int i=0; i<size; i++) {
            if(str1.charAt(i) != str2.charAt(i))
                cnt++;
            if(cnt>=2)
                break;
        }
        // 하나만 다르면 바꿀수 있다.
        return cnt==1?true:false;
    }
}