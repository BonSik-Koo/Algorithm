package SWExpertAcademyProblem.실전실습문제.최대값최소값찾기;

/**
 * - 세그먼트트리 문제
 *
 * [포인트]
 * - 각 노드에 아래 자식들에서 살아있는 노드(죽지않은 노드)의 개수를 포함(alive)
 * - find 에서 찾고자 하는 인덱스가 살아있는 노드의 인덱스 수를 묻기 때문에 alive 필드를 추가함
 * - alive 값을 통해서 실제 배열 인덱스 값을 추출(Kth) -> 추가, 삭제등에서 주는 인덱스가 살아있는 노드중의 인덱스 번호이기 때문에
 * - 삭제시 트리에서 직접 삭제하지 않고 (MAX,MIN,0)으로 만 업데이트
 *
 * - 문제:https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AYWaZW_ai2YDFAQK&categoryId=AYYpK-5qsTMDFARc&categoryType=BATTLE&battleMainPageIndex=1&&&&
 * - 팁 해설: https://swexpertacademy.com/main/talk/solvingClub/boardCommuView.do?solveclubId=AYWjN5DaiAsDFAQK&searchClsftn=AYWjN5DqiA0DFAQK&schClsName=Notice&searchCondition=COMMU_DETAIL-COMMU_TITLE-NICK_NAME_TAG&commuId=AYZO9e-aHbYDFARc&orderBy=&searchKeyword=&sortingType=DATE_DESC&pageSize=10&pageIndex=1
 */
public class UserSolution {

    public static final int MAXSIZE = 200000;

    static class Node {
        int min, max;
        int alive; //살아 있는 원소의 개수
        public Node(int min, int max, int alive) {
            this.min = min;
            this.max = max;
            this.alive = alive;
        }
        public Node(){
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
            this.alive = 0;
        }
    }
    static class SegmentTree{
        Node[] tree;
        int size;
        //생성자
        public SegmentTree(){

            int height = (int) Math.ceil(Math.log(MAXSIZE) / Math.log(2)); //리프 노드를 최대 200,000만개로 생성
            size = (int)Math.pow(2,height+1);
            tree = new Node[size];
        }
        //초기화
        public void clearTree(){
            for(int i=1;i<size;i++)
                tree[i]  = new Node();
        }
        //값 추가, 삭제
        public void update(int node, int start, int end, int idx, int value) {
            if(idx<start || end<idx)
                return;

            if(start == end){
                if(value == -1)
                    tree[node] = new Node();
                else
                    tree[node] = new Node(value, value,1);
                return;
            }else {
                update(node * 2, start, (start + end) / 2, idx, value);
                update(node * 2 + 1, (start + end) / 2 + 1, end, idx, value);
                tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
            }
        }
        //값 합치기
        public Node merge(Node n1, Node n2){
            int min = Math.min(n1.min, n2.min);
            int max = Math.max(n1.max, n2.max);
            int alive = n1.alive + n2.alive;
            return new Node(min, max, alive);
        }
        //구간의 값 구하기
        public Node query(int node, int start, int end, int left, int right){
            if(left>end || right<start)
                return new Node();

            //범위 내 완전히 포함 시에는 더 내려가지 않고 리턴
            if(left<=start && end<=right){
                return tree[node];
            }

            Node n1 = query(node * 2, start, (start + end) / 2, left, right);
            Node n2 = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            return merge(n1,n2);
        }
        //특정 번째의 수의 위치를 구하기(K번째 == 살아있는 원소의 개수(alive)의 인덱스)
        public int Kth(int node, int start, int end, int K){
            if(start==end)
                return start;

            if(tree[node*2].alive < K)
                return Kth(node*2+1, (start+end)/2+1, end, K-tree[node*2].alive);
            else
                return Kth(node*2, start, (start+end)/2 , K);
        }
    }

    static SegmentTree segmentTree;
    static int totalNum; //총개수(제거해도 총 개수는 변하지 않음)

    public void init(int N, int mValue[]){

        segmentTree = new SegmentTree();
        segmentTree.clearTree();
        totalNum = 0;
        for(int i=0;i<N;i++)
            segmentTree.update(1,1,MAXSIZE, ++totalNum, mValue[i]);
    }

    public void add(int M, int mValue[]){

        for(int i=0;i<M;i++)
            segmentTree.update(1,1,MAXSIZE, ++totalNum, mValue[i]);
    }

    public void erase(int mFrom, int mTo){

        for(int i = mTo; i>=mFrom;i--){
            int del_index = segmentTree.Kth(1,1,MAXSIZE, i);
            segmentTree.update(1,1,MAXSIZE, del_index ,-1);
        }

    }

    public int find(int K){

        int totalAlive = segmentTree.tree[1].alive; //트리에서 살아있는 노드의 개수
        int temp_start = totalAlive -K +1;
        int real_start = segmentTree.Kth(1,1,MAXSIZE,temp_start);

        Node result = segmentTree.query(1, 1, MAXSIZE, real_start, totalNum);
        return result.max - result.min;
    }
}
