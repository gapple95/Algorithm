import java.util.*;

class Solution {
    int size;
    boolean[] visited;
    List<String> routeList = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        size = tickets.length;
        visited = new boolean[size];

        // 티켓을 사전순 정렬 (출발지 → 도착지 기준)
        Arrays.sort(tickets, (o1, o2) -> {
            if (o1[0].equals(o2[0])) {
                return o1[1].compareTo(o2[1]); // 같은 출발지일 경우 도착지 오름차순 정렬
            }
            return o1[0].compareTo(o2[0]); // 출발지 오름차순 정렬
        });

        // DFS 탐색 시작 (출발지는 "ICN")
        dfs("ICN", "ICN", tickets, 0);

        // 최적 경로 반환
        return routeList.get(0).split(" ");
    }

    private boolean dfs(String cur, String path, String[][] tickets, int count) {
        if (count == size) { // 모든 티켓을 사용했을 경우
            routeList.add(path); // 경로 저장
            return true; // 탐색 종료
        }

        for (int i = 0; i < size; i++) {
            if (!visited[i] && tickets[i][0].equals(cur)) { // 아직 사용하지 않은 티켓이며 출발지가 cur일 경우
                visited[i] = true;
                boolean found = dfs(tickets[i][1], path + " " + tickets[i][1], tickets, count + 1);
                visited[i] = false; // 백트래킹 (다시 탐색할 수 있도록 방문 취소)
                
                if (found) return true; // 첫 번째 경로만 저장하고 종료
            }
        }
        return false;
    }
}
