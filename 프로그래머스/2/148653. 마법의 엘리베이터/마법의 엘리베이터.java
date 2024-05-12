class Solution {
    public int solution(int storey) {
        int answer = 0;

        while(storey > 0) {
            int num = storey % 10;
            storey /= 10;
            
            if(num > 5) {
                while(num < 10) {
                    num++;
                    answer++;
                }
                storey ++;
            }
            else if (num == 5) {
                if(storey % 10 >= 5) {
                    while(num < 10) {
                        num++;
                        answer++;
                    }
                    storey ++;
                }
                else {
                    while(num > 0) {
                        num--;
                        answer++;
                    }      
                }
            }
            else {
                while(num > 0) {
                    num--;
                    answer++;
                }
            }
        }
        
        return answer;
    }
}