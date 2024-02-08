class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] countA = new int[100];
		
		int n = speeds.length;
		int idx = 0;
		int day = 0;
		
		while(idx<n) {
			addArray(progresses, speeds, day);
			
			int check = idx;
			// for문을 돌면서 100을 넘는지 확인
			for (int i = idx; i < speeds.length; i++) {
				if(progresses[i]>=100) {
					idx++;
					countA[check]++;
				} else {
					break;
				}
			}
			if(check != idx)
				day++;
		}
		
		int cnt=0;
		for(int i=0; i<countA.length; i++) {
			if(countA[i] !=0)
				cnt++;
		}
		
		int[] answer = new int[cnt];
		cnt = 0;
		for(int i=0; i<countA.length; i++) {
			if(countA[i] !=0)
				answer[cnt++]=countA[i];
		}
        return answer;
    }
    static void addArray(int[] arr_P, int[] arr_S, int a) {
		for (int i = a; i < arr_P.length; i++) {
			arr_P[i] +=  arr_S[i];
		}
	}
}