/*

Queue를 구현하고, 조건이 있음
1. 현재 큐의 가장 앞에 있는 문서의 '중요도'를 확인
2. 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면,
   이 문서를 인쇄하지 않고 큐의 가장 뒤에 재배치 한다. 그렇지 않다면 바로 인쇄를 한다.

예시 A B C D에 중요도 2 1 4 3라면 C를 인쇄하고, 다음으로 D를 인쇄하고 A, B를 인쇄하게된다.

현재 큐에 있는 문서의 수와 중요도가 주어졌을 때, 어떤 한 문서가 몇 번째로 인쇄되는지를 구하기

# 입력
첫 줄에 테스트 케이스의 수가 주어진다. 각 테스트케이스는 두 줄로 이루어짐

테스트 케이스의 첫 번째 줄에는 문서의 개수 N(1<=N<=100)과 몇 번째로 인쇄되었는지
정수 M(0 <= M < N)이 주어진다. 이때 맨 왼쪽은 0번째라고 하자.

두번째 줄에는 N개 문서의 중요도가 차례대로 주어진다. 1~9

#출력
각 테스트 케이스에 몇 번째로 인쇄되는지 출력

# 생각
우선순위 큐를 만들고 노드는 (중요도,문서번호)로 설정.
중요도를 기준으로 내림차순으로 정렬하고, 큐에서 꺼내서 우선순위 큐의 앞부분과 비교
비교해서 더 크다면 출력, 작다면 다시 큐에 넣기
출력했을 때, 카운트를하고, 몇번째로 인쇄되는지 궁금한 문서가 출력하면 카운트를 출력하고
테스트 케이스 넘기기.

최악의 케이스는 N이 100일 때 제일 마지막에 출력이 되는 케이스
100번 큐 다음 하나 출력, 다음 99번 큐 다음 하나 출력, 98번.. 1번이면
100 + 99 + 99 + .. + 1 = 5050번
2초안에 충분히 될듯

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<int[]> pq;
        Queue<int[]> q;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int cnt, n, target, tmp;
        for(int t = 0; t < T; t++) {
            cnt = 0;

            st = new StringTokenizer(br.readLine());
            q = new ArrayDeque<>();

            n = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());

            pq  = new PriorityQueue<>((o1, o2) -> {
                if(o1[0] < o2[0])
                    return 1;
                else
                    return -1;
            });

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                tmp = Integer.parseInt(st.nextToken());
                q.offer(new int[]{tmp, i});
                pq.offer(new int[]{tmp, i});
            }

//            for (int i = 0 ; i < n ; i++)
//                System.out.print(pq.poll()[1] + " ");
//            System.exit(0);

            while(!q.isEmpty()) {
                int[] cur = q.poll();
                
                // 현재 노드가 우선순위가 더 크다면
                // 제거하기
                if(cur[0] > pq.peek()[0]) {
                    cnt++;
                    if(cur[1] == target)
                        break;
                }
                // 현재 노드가 우선순위가 더 작다면
                // 다시 큐에 넣기
                else if(cur[0] < pq.peek()[0]) {
                    q.offer(new int[]{cur[0], cur[1]});
                }
                // 둘이 같다면 우선순위 큐에서도 지우기
                else {
                    cnt++;
                    pq.poll();

                    if(cur[1] == target)
                        break;
                }
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);
    }
}