import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        Deque<Character> q;
        boolean check;
        for(int t=0; t<skill_trees.length ; t++) {
            q = new ArrayDeque<>();
            check = true;
            for(int i=0; i<skill.length(); i++)
                q.offer(skill.charAt(i));
            
            // System.out.println(skill_trees[t]);
            for(int i=0; i<skill_trees[t].length() ; i++) {
                // skill이 포함되면
                // System.out.println(skill_trees[t].charAt(i) + " " + q.peek());
                if(skill.contains(skill_trees[t].charAt(i)+"")){
                    // 유효성 검사
                    // 큐에 peek과 같다면 통과.
                    // 아니라면 바로 나가기
                    if(skill_trees[t].charAt(i) == q.peek()) {
                        q.poll();
                    } else {
                        check = false;
                        break;
                    }
                }
                // skill이 포함이 안되면 pass
                else
                    continue;
            }
            
            if(check)
                answer++;
            // System.out.println(answer);
        }
        
        return answer;
    }
}