/*

2573 빙산

2차원 배열
빙산의 각 부분별 높이 저오는 양의 정수
바다에 해당되는 칸은 0

바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에, 칸에 있는 높이는 일년마다
그 칸에 동서남북 네 방향으로 붙어있느 0이 저장된 칸의 개수만큼 줄어든다.
0보다 더 줄어들진 않는다.

한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상 분리되는 최초의 시간(년)을
구하는 프로그램

만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력

# 입력
첫줄에는 이차원 배열의 행의 개수와 열의 개수
두 정수 N과 M이 한 개의 빈칸
N과 M은 3 이상 300 이하

각칸은 0이상 10이하

전체 크기는 10,000개 이하이다.

# 출력
첫 줄에 빙산이 분리된 최초의 시간을 출력한다. 만일 빙산이 다 녹을떄까지 분리되지 않으면
0을출력한다.

# 방법
BFS로 풀자. 모든 행에서 BFS를 실시, 섬을 찾을 때마다 저장하는 배열에서 1, 그다음 섬은 2
이런식으로 섬의 갯수를 구하자.
만약 2가 나오게된다면, 그때 시간을 출력
끝까지 갔는데도 분리가 안되면 0을 출력

먼저 빙하들을 녹이고 시작.

한꺼번에 녹아야하므로, 그냥 이중포문으로는 안된다.
map을 복사해서, 뺴주고, 다시 그걸 원본으로 복사해준자.

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, arr[][], arr2[][], visited[][], time, cnt;
    static int[] dx={0,0,-1,1}, dy={1,-1,0,0};
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+2][M+2];
        arr2 = new int[N+2][M+2];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            int island = 1;
            time++;
            visited = new int[N+2][M+2];

            // 맵 복사
            for(int i=0; i<N+2 ; i++) {
                System.arraycopy(arr[i], 0, arr2[i], 0, M+2);
            }

            // 얼음을 녹이기
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=M; j++) {
                    if(arr[i][j]==0) {
                        for(int d=0; d<4; d++) {
                            arr2[i+dy[d]][j+dx[d]]--;
                            if(arr2[i+dy[d]][j+dx[d]] < 0)
                                arr2[i+dy[d]][j+dx[d]] = 0;
                        }
                    }
                }
            }

            // 맵 복사
            for(int i=0; i<N+2; i++) {
                System.arraycopy(arr2[i], 0, arr[i], 0, M+2);
            }

            Queue<int[]> q;
            // 섬 갯수 구하기
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=M; j++) {
                    // 바다가 아니고, 들린적도 없다면,
                    if(arr[i][j] != 0 && visited[i][j] == 0) {
                        // BFS로 섬 레이블링
//                        q = new LinkedList<>();
                        q = new ArrayDeque<>();

                        q.offer(new int[]{i,j});
                        visited[i][j] = island;

                        while(!q.isEmpty()) {
                            int[] cur = q.poll();
                            for(int d=0; d<4; d++) {
                                int nx = cur[0] + dx[d];
                                int ny = cur[1] + dy[d];

                                // 범위 체크
                                if(nx < 1 || ny < 1 || nx >= N || ny >= M)
                                    continue;

                                // 바다면 넘어가기
                                if(arr[nx][ny] == 0)
                                    continue;

                                // 이미 들렸던 섬이라면 넘어가기
                                if(visited[nx][ny] == island)
                                    continue;

                                visited[nx][ny] = island;
                                q.offer(new int[]{nx,ny});
                            }
                        }
                        // 섬을 들리는것이기 때문에 섬 카운트 + 1
                        island++;
                    }
                }
            }
//            print();

            // 만약 모든 탐사가 끝났는데, 섬이 2개라면 종료
            if((island-1) >= 2) {
                sb.append(time);
                break;
            }

            // 만약 아무런 섬을 못 봤다면 0을 출력
            if((island-1) == 0) {
                sb.append(0);
                break;
            }

        }
        System.out.println(sb);
    }

    public static void print() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
    }
}