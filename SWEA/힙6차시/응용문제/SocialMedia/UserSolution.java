package SWExpertAcademyProblem.힙6차시.응용문제.SocialMedia;

import java.util.*;

/**
 * - 우선순위 큐 문제
 * - 문제: https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AXASthA6AyQDFAXq&categoryId=AYWab_JKjkwDFAQK&categoryType=BATTLE&battleMainPageIndex=1
 */
class UserSolution {

    class Node{
        int uID, pID, time;
        public Node(int uID, int pID, int time) {
            this.uID = uID;
            this.pID = pID;
            this.time = time;
        }
    }
    PriorityQueue<Node> timeQD; //시간 기준 내림차순 - 메인 큐

    PriorityQueue<Node> timeQA; //시간 기준 오름차순
    PriorityQueue<Node> likeQ; // 게시물 좋아요,timestamp 내림차순

    Boolean[][] check; //팔로우 체크 배열
    List<Integer> likeList; //게시물별 좋아요 개수를 담는 배열

    public void init(int N)
    {
        check = new Boolean[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i==j)
                    check[i][j] = true; //자기 자신
                else
                    check[i][j] = false;
            }
        }

        likeList = new ArrayList<>();
        likeList.add(0); //게시물 id 가 1부터 시작하니

        timeQD = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.time - o1.time;
            }
        });
        timeQA = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.time - o2.time;
            }
        });
        likeQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(likeList.get(o1.pID) == likeList.get(o2.pID)) {
                    return o2.time - o1.time;
                }else {
                    return likeList.get(o2.pID) - likeList.get(o1.pID);
                }
            }
        });
    }

    public void follow(int uID1, int uID2, int timestamp)
    {
        check[uID1][uID2] = true; //팔로우 설정
    }

    public void makePost(int uID, int pID, int timestamp)
    {
        Node newNode = new Node(uID, pID, timestamp);
        timeQD.add(newNode); //시간 내림 차순 큐에 저장
        likeList.add(0); //초기 좋아요 값 설정 - pID 가 오름차순으로 제공되니

        //시간 내림차순 큐, 시간 오름차순 큐 재배치
        while (!timeQD.isEmpty() && !timeQA.isEmpty()) {

            Node n1 = timeQD.peek();
            Node n2 = timeQA.peek();
            if(n1.time > n2.time) { //스왑
                n1 = timeQD.poll();
                n2 = timeQA.poll();

                timeQD.add(n2);
                timeQA.add(n1);
            }else
                break;
        }
    }

    public void like(int pID, int timestamp)
    {
        int num = likeList.get(pID) +1;
        likeList.set(pID, num); //좋아요 개수 1 증가
    }

    public void getFeed(int uID, int timestamp, int pIDList[])
    {
        //timeQD 재배치
        while (!timeQD.isEmpty()){
            Node QD_node = timeQD.peek();
            if(timestamp - QD_node.time > 1000){ //정상적인 경우
                break;
            }else {
                timeQA.add(QD_node);
                timeQD.poll();
            }
        }
        //timeQA 재배치
        while (!timeQA.isEmpty()){
            Node QA_node = timeQA.peek();
            if(timestamp - QA_node.time <=1000){
                break;
            }else{
                timeQD.add(QA_node);
                timeQA.poll();
            }
        }

        //likeQ 세팅
        Queue<Node> temp = new LinkedList<>(); //임시 저장 큐
        while (!timeQA.isEmpty()){
            Node poll = timeQA.poll();
            temp.add(poll);

            if(check[uID][poll.uID]==false)
                continue;
            likeQ.add(poll);
        }

        //다시 원상 복귀 시키기
        while (!temp.isEmpty()){
            timeQA.add(temp.poll());
        }

        //좋아요 큐에서 옮겨 담기
        int index =0;
        while (index<10 && !likeQ.isEmpty()){
            Node poll = likeQ.poll();

            if(check[uID][poll.uID]==false)
                continue;
            pIDList[index++] = poll.pID;
        }

        // 담을수 있을시 시간 내림차순 큐에서 옮겨 담기
        temp = new LinkedList<>();
        while (index<10 && !timeQD.isEmpty()){
            Node poll = timeQD.poll();
            temp.add(poll);
            if(check[uID][poll.uID]==false)
                continue;
            pIDList[index++] = poll.pID;
        }

        //다시 원상 복귀 시키기
        while (!temp.isEmpty()){
            timeQD.add(temp.poll());
        }

        likeQ.clear();
    }

}

