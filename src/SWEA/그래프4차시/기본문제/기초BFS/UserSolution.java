package SWEA.그래프4차시.기본문제.기초BFS;

/**
 * - 문제 : https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AXrCU1l6Aj8DFATi&categoryId=AYWab_JKjkwDFAQK&categoryType=BATTLE&battleMainPageIndex=1
 */
class UserSolution {

    //Queue 직접 구현
    class ArrayQueue {
        int MAX = 1000000;
        int front;    //머리 쪽에 위치할 index값, pop할때 참조하는 index
        int rear;    //꼬리 쪽에 위치할 index값, push할때 참조하는 index
        Node [] queue;
        public ArrayQueue() {
            front = rear = 0;    //초기값 0
            queue = new Node[MAX]; //배열 생성
        }
        public boolean queueisEmpty() { //queue에 아무것도 들어있지 않은지 판단하는 함수
            return front == rear;
        }
        public boolean queueisFull() {    //queue가 가득 차 공간이 없는지 판단하는 함수
            if(rear == MAX-1) {
                return true;
            }else
                return false;
        }
        public int size() { //queue에 현재 들어가 있는 데이터의 개수를 return
            return front-rear;
        }
        public void push(Node value) {
            if(queueisFull()) {
                return;
            }
            queue[rear++] = value; //rear가 위치한 곳에 값을 넣어주고 rear를 증가시킨다.
        }
        public Node pop() {
            if(queueisEmpty()) {
                return null;
            }
            Node popValue = queue[front++];
            return popValue;
        }
    }

    class Node {
        int x,y; //행, 열
        int count;
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    int [][]map;
    Boolean[][]visit;
    ArrayQueue queue;
    int size;

    void bfs_init(int map_size, int map[][]) {

        size = map_size;
        queue = new ArrayQueue();
        this.map = new int[map_size][map_size];
        visit = new Boolean[map_size][map_size];

        for(int i=0;i<map_size;i++){
            for(int j=0;j<map_size;j++){
                this.map[i][j] = map[i][j];
                this.visit[i][j] = false;
            }
        }
    }
    void visit_init(){
        queue = new ArrayQueue();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
                visit[i][j] = false;
        }
    }

    int bfs(int x1, int y1, int x2, int y2) {

        int result = -1;
        Node startNode = new Node(y1-1, x1-1,0); //좌상 좌표가 (1,1)
        queue.push(startNode);
        visit[y1-1][x1-1] = true;

        while(!queue.queueisEmpty()){
            Node pop = queue.pop();

            if(pop.x==y2-1 && pop.y==x2-1){ //종료 조건
                result = pop.count;
                break;
            }
            for(int i=0;i<4;i++){
                int nx = pop.x+dx[i];
                int ny = pop.y+dy[i];
                int nc = pop.count +1;

                if(0>nx || nx>=size || 0>ny || ny>=size)
                    continue;
                if(!visit[nx][ny] && map[nx][ny]==0){
                    Node newNode = new Node(nx, ny, nc);
                    queue.push(newNode);
                    visit[nx][ny] = true;
                }
            }
        }
        visit_init();
        return result;
    }
}
