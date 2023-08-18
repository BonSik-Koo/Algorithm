package SWEA.그래프4차시.기본문제.기초DFS;

/**
 * - 문제: https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AXrCUo46AioDFATi&categoryId=AYWab_JKjkwDFAQK&categoryType=BATTLE&battleMainPageIndex=1
 */
public class UserSolution {

    int [][] childList; //부모, 자식 능력
    int [][] childNum; //자식 개수

    public void dfs_init(int N, int[][] path) {

        childList = new int[100][5];
        childNum = new int[100][1];
        for(int i=0;i<100;i++)
            childNum[i][0] = 0;

        for(int i=0;i<N;i++){
            int parent = path[i][0];
            int child = path[i][1];

            int index = childNum[parent][0];
            childList[parent][index] = child;
            childNum[parent][0]++; //자식 수 늘리기
        }
    }

    int max;
    Boolean check;
    public int dfs(int N) {

        int result = -1;
        max = N;
        check = false; //계승할 자식을 찾은 경우

        findDfs(N);
        if(max != N)
            result = max;

        return result;
    }
    public void findDfs(int N){

        if(max < N){
            check = true;
            max = N;
            return;
        }

        for(int i=0;i<childNum[N][0];i++){
            if(check == true)
                return;
            findDfs(childList[N][i]);
        }
    }
}