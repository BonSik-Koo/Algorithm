package SWExpertAcademyProblem.실전실습문제.세균번식;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * - 우선순위큐를 이용한 BFS 문제
 * - 문제 : https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AYY4pZIKrgsDFARc&categoryId=AYYpK-5qsTMDFARc&categoryType=BATTLE&battleMainPageIndex=1
 */

class UserSolution {

    class Node implements Comparable<Node>{
        int row,col; //행과 열
        int mTarget; //1:Blue, 2:Red
        int mEnergy; //필요한 에너지 소비량
        int currentE; //현재 세균의 번식 에너지
        public Node(int row, int col, int mTarget, int mEnergy, int currentE) {
            this.row = row;
            this.col = col;
            this.mTarget = mTarget;
            this.mEnergy = mEnergy;
            this.currentE = currentE;
        }
        @Override
        public int compareTo(Node o) {
            if(this.mEnergy == o.mEnergy){
                if(this.row == o.row) {
                    if (this.col == o.col)
                        return this.currentE - o.currentE;
                    return this.col - o.col;
                }
                return this.row - o.row;
            }
            return o.mEnergy- this.mEnergy;
        }
    }
    PriorityQueue<Node> priorityQ;
    int[][] value; //실시간 에너지값 저장
    Node[][] field;
    Boolean[][] visit;

    int N;
    int [] dx = {1,-1,0,0};
    int []dy = {0,0,1,-1};
    int blueN, redN;

    void init(int N, int mDish[][]) {

        //(1,1) 부터 시작
        value = new int[N+1][N+1];
        field = new Node[N+1][N+1];
        visit = new Boolean[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                value[i][j] = mDish[i-1][j-1];
                field[i][j] = null; //null로 초기화
                visit[i][j] = false;
            }
        }

        this.N = N;
        blueN =0; redN =0;
        priorityQ = new PriorityQueue<>();
    }

    //BFS
    void findSameTarget(int row, int col,int mTarget, int mEnergy, int currentE) {
        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(row, col, mTarget, mEnergy, currentE);
        queue.add(node);
        visit[row][col] = true;

        while (!queue.isEmpty()){
            Node poll = queue.poll();

            for(int i=0;i<4;i++){
                int nx = poll.row + dx[i];
                int ny = poll.col + dy[i];

                if(1>nx || nx>N || 1>ny || ny>N)
                    continue;

                //방문하지 않고, 같은 종류의 세균을 발견한 경우
                if(!visit[nx][ny] && value[nx][ny]==0 && field[nx][ny].mTarget==poll.mTarget){
                    queue.add(field[nx][ny]);
                    visit[nx][ny] = true; //방문 처리
                }
                //우선순위 세균 위치의 후보가 될수 있는 경우
                else if(value[nx][ny]!=0){
                    priorityQ.add(new Node(nx,ny, poll.mTarget, value[nx][ny], currentE));
                }
            }
        }

    }

    int dropMedicine(int mTarget, int mRow, int mCol, int mEnergy){

        //약품 떨어뜨리기
        Node node = new Node(mRow, mCol, mTarget, value[mRow][mCol], mEnergy);
        int current_Energy = mEnergy;
        priorityQ.add(node);

        while (!priorityQ.isEmpty()) {
            Node popNode = priorityQ.poll();
            popNode.currentE = current_Energy;

            //세균이 존재하는 위치 -> 초기에 있을수 있으므로
            if(value[popNode.row][popNode.col]==0 && field[popNode.row][popNode.col]!=null){
                //다른 색의 세균인 경우
                if(field[popNode.row][popNode.col].mTarget!=popNode.mTarget)
                    break;
                //같은 색의 세균이 있는 위치 -> 같은 색의 세균을 모두 찾아 활성화
                else
                    findSameTarget(popNode.row, popNode.col, popNode.mTarget, popNode.mEnergy, popNode.currentE);
            }
            //세균이 없는 위치
            else if(value[popNode.row][popNode.col] !=0){


                if(popNode.mTarget==1)
                    blueN+=1;
                else
                    redN+=1;

                //현재 위치의 값들을 업데이트
                int nEnergy = (popNode.currentE- popNode.mEnergy<=0)?
                        0:popNode.currentE- popNode.mEnergy;
                value[popNode.row][popNode.col] = 0;
                field[popNode.row][popNode.col] =
                        new Node(popNode.row, popNode.col, popNode.mTarget, popNode.mEnergy, nEnergy);

                //번식 에너지를를 다쓴 경우 - 종료
                if(nEnergy == 0)
                    break;

                for(int i=0;i<4;i++){
                    int nx = popNode.row + dx[i];
                    int ny = popNode.col + dy[i];

                    if(1>nx || nx>N || 1>ny || ny>N)
                        continue;

                    //다음 셀의 세균이 같은 세균이고 방문하지 않은 셀일 경우
                   if(value[nx][ny]==0){
                        if(field[nx][ny].mTarget==popNode.mTarget &&!visit[nx][ny]){
                            findSameTarget(nx, ny, popNode.mTarget, value[nx][ny], nEnergy);
                        }
                    }
                    //빈 셀일 경우(세균이 없는)
                    else {
                        priorityQ.add(new Node(nx,ny, popNode.mTarget, value[nx][ny],nEnergy));
                    }
                }

                current_Energy  = nEnergy;
            }
        }

        //초기화
        priorityQ.clear();
        for(int i=0;i<=N;i++)
            Arrays.fill(visit[i],false);

        return (mTarget==1)?blueN:redN;
    }

    int cleanBacteria(int mRow, int mCol){

        Queue<Node> queue = new LinkedList<>();
        Boolean[][]check = new Boolean[1+N][1+N];
        for(int i=0;i<=N;i++)
            Arrays.fill(check[i], false);

        //소멸 위치에 아무런 세균이 없는 경우
        if(field[mRow][mCol]==null)
            return -1;

        queue.add(field[mRow][mCol]);
        check[mRow][mCol] = true;
        int target = field[mRow][mCol].mTarget;

        while (!queue.isEmpty()){
            Node poll = queue.poll();

            //실시간 에너지 배열값 원상복구
            value[poll.row][poll.col] = poll.mEnergy;

            //해당 위치의 세균 없애주기
            field[poll.row][poll.col] = null;

            //개수 줄여주기
            if(target==1)
                blueN--;
            else
                redN--;

            for(int i=0;i<4;i++){
                int nx = poll.row+dx[i];
                int ny = poll.col+dy[i];

                if(1>nx || nx>N || 1>ny || ny>N)
                    continue;
                if(field[nx][ny]!=null && value[nx][ny]==0 && !check[nx][ny]){
                    if(field[nx][ny].mTarget==target) {
                        queue.add(field[nx][ny]);
                        check[nx][ny] = true;
                    }
                }
            }
        }

        return (target==1)?blueN:redN;
    }
}