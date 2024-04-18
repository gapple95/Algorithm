import java.util.*;

/*

단품 요리들을 조합아셔 새로운 메뉴를 만들 예정
이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품 메뉴들을 선택
단, 코스요리 메뉴는 최소 2가지 이상의 단품 메뉴로 구성
또한, 최소 2명 이상의 손님으로부터 주문된 단품 메뉴 조합에 대해서만 메뉴 후보에 포함하기로 했다.

각손님들이 주문한 담품메뉴가 담긴 orders
단품메뉴들의 갯수가 담긴 course배열이 매개변수로 주어진다.

course 만큼 배열을 돌리면서,
course[i] 만큼 조합의 크기를 설정한 후
orders를 전부 돌린다.

모두 map에 담고, 마지막에 map에 담긴 key중 value가 2이상인 값만 출력




*/



class Solution {
    
    static boolean[] selected;
    static Map<String, Integer> map; // <메뉴이름, 메뉴횟수>
    static List<Integer> list;
    static List<String> answer_list;
    static int[] answer_menu;
    static int K;
    static int max;
    
    public String[] solution(String[] orders, int[] course) {
        
        answer_list = new ArrayList<>();
        map = new HashMap<>();
        
        int menu;
        // 코스 크기 결정
        for(int k=0; k<course.length; k++) {
            K = course[k];
            // 코스 크기별로 가장 많이 함께 주문한 단품메뉴가 다르기 때문에 선언
            map.clear();
            max = Integer.MIN_VALUE;
            // 손님들의 주문들을 받기
            for(int i=0; i<orders.length; i++) {
                // 손님의 주문을 조합으로 돌려서 모든 문자열 만들기
                answer_menu = new int[K];
                comb(0, 0, orders[i]);

            }
            for(String key : map.keySet()) {
                if(map.get(key) == max)
                    answer_list.add(key);
            }
        }
        
        Collections.sort(answer_list);
        String[] answer = new String[answer_list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = answer_list.get(i);
        }
        
        return answer;
    }
            
    public static void comb(int idx, int start, String str) {
        if(K==idx) {
            // K크기만큼의 조합들을 넣자.
            String result = "";
            
            for(int i=0; i<K; i++)
                result += (char)answer_menu[i];
            
            char[] tmp = result.toCharArray();
            Arrays.sort(tmp);
            
            result = new String(tmp);
            
            int cnt;
            // 만약 맵안에 메뉴가 없다면
            if(map.get(result) == null) {
                map.put(result, 1);
            }
            // 메뉴가 있다면
            else {
                cnt = map.get(result);
                map.put(result, cnt+1);
                // System.out.println("result : " + result);
                if(cnt+1 > max) {
                    max = cnt+1;
                    // System.out.println(K + ", max : " + max);
                }
            }
            
            return;
        }
        
        for(int i=start; i<str.length() ; i++) {
            answer_menu[idx] = str.charAt(i);
            comb(idx+1, i+1, str);
        }
    }
}