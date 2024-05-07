class Solution {
    static boolean[] selected;
    static int N, cnt;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        N = numbers.length;
        selected = new boolean[N];
        
        subset(0, numbers, target);
        
        answer = cnt;
        
        return answer;
    }
    
    public static void subset(int idx, int[] numbers, int target) {
        if(idx == N) {
            int sum = 0;
            for(int i=0; i<N ; i++) {
                if(selected[i])
                    sum += numbers[i];
                else
                    sum -= numbers[i];
            }
            
            if(sum == target)
                cnt++;
            
            return;
        }
        selected[idx] = true;
        subset(idx+1, numbers, target);
        selected[idx] = false;
        subset(idx+1, numbers, target);
    }
}