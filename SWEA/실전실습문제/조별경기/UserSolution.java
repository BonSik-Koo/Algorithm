package SWExpertAcademyProblem.실전실습문제.조별경기;

/**
 * - 트리 문제
 * - 공통조상 응용 문제
 * - 시간 초과를 막기 위해 "path 압축 알고리즘" 사용해야함
 *
 * - 문제: https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AYTLCHZqhWcDFARs&categoryId=AYYpK-5qsTMDFARc&categoryType=BATTLE&battleMainPageIndex=1
 * - 풀이: https://swexpertacademy.com/main/talk/solvingClub/boardCommuView.do?solveclubId=AYWjN5DaiAsDFAQK&searchClsftn=AYWjN5DqiA0DFAQK&schClsName=Notice&searchCondition=COMMU_DETAIL-COMMU_TITLE-NICK_NAME_TAG&commuId=AYZJP0uaqX0DFARc&orderBy=&searchKeyword=&sortingType=DATE_DESC&pageSize=10&pageIndex=1
 */
class UserSolution
{
    int[] score; //각 선수의 점수
    int[] parent; //각 선수의 최상 부모 노드

    public void init(int N) //(1~N)
    {
        score = new int[N+1];
        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            score[i] = 0;
            parent[i] = i;
        }
    }

    private int find(int id) { //최상의 부모노드를 찾는 함수

        if(parent[id] == id)
            return id;
        return find(parent[id]);
    }
    
    private int pathCompression(int id){ //깊이를 모두 1로 만드는 것!!

        if(parent[id] == id)
            return id; //최상의 루트

        if(parent[parent[id]] == parent[id]) //루트 바로 밑 노드일 때
            return parent[id]; //최상의 루트

        int root = pathCompression(parent[id]); //깊이를 모두 1로 만들기
        score[id] += score[parent[id]];
        parent[id] = root; //깊이가 1이 되는 지점.

        return root;
    }

    public void updateScore(int mWinnerID, int mLoserID, int mScore)
    {
        int p1 = find(mWinnerID);
        int p2 = find(mLoserID);

        //최상 노드의 점수만 변경
        score[p1] += mScore;
        score[p2] -= mScore;
    }

    public void unionTeam(int mPlayerA, int mPlayerB)
    {
        int p1 = pathCompression(mPlayerA);
        int p2 = pathCompression(mPlayerB);

        parent[p2] = p1; //p2의 부모(자기자신)를 p1으로 설정 -> 연결하면서 깊이가 2가 될수 있다.
        score[p2] -= score[p1]; //!!!
    }

    public int getScore(int mID)
    {
        int root = pathCompression(mID); //깊이 2인 경우를 대비하여 1로 만들기

        if(root == mID)
            return score[mID];

        return score[mID] + score[parent[mID]];

        //int result = cal(mID);
        //return result;
    }

//    //시간 초과
//    private int cal(int id) {
//
//        if(parent[id] == id)
//            return score[id];
//
//        return score[id] + cal(parent[id]);
//    }
}
