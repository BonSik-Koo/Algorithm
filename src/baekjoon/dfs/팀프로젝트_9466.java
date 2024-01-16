package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 팀프로젝트_9466 {
    static int[] arr;
    static boolean[] visit, isFinished;
    static int n, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr = new int[n+1];
            visit = new boolean[n+1];
            isFinished = new boolean[n+1];
            cnt = 0;
            for(int k=1; k<=n; k++){
                arr[k] = Integer.parseInt(st.nextToken());

            }

            for(int j=1; j<=n; j++){
                if(isFinished[j]) {
                    continue;
                }
                dfs(j);
            }

            System.out.println(n-cnt);
        }
    }

    static void dfs(int idx){
        if(isFinished[idx]) { // 탐색이 완료된 노드
            return;
        }
        if(visit[idx]) { // 이전에 방문한 노드인 경우 = 사이클 형성
            isFinished[idx] = true;
            cnt+=1;
            // 사이클에 존재하는 노드 개수 알기 위해 반복해서 다음 노드를 탐색한다. -> visit 로 인해 조기 탐색이 종료될 것임.
        }

        visit[idx] = true; // 방문 처리
        dfs(arr[idx]); // 다음 연결된 노드 탐색
        isFinished[idx] = true;
        visit[idx] = false;
    }


}
