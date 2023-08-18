import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S_16235 {
    static int num =0;
    static int treeCount = 0;
    static int year =0;

    static int [][] addGround;
    static int [][] currentGround;
    static class tree implements Comparable<tree>{
        int x; int y; int age;
        public tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(tree o) {
            if(this.age > o.age)
                return 1;
            else if (this.age < o.age)
                return -1;
            return 0;
        }
    }
    static Queue<tree> trees = new PriorityQueue<>(); //현재 살아있는 나무들을 담는 리스트 -> 우선순위큐는 같은 좌표의 여러나무가 있는 경우를 위해 사용함
    static List<tree> deadTree = new ArrayList<>(); //죽은 나무를 담는 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        num = Integer.parseInt(st.nextToken());
        treeCount = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());

        //초기화
        addGround = new int[num][num];
        currentGround  = new int[num][num];
        for(int i = 0; i< num; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<num;j++) {
                addGround[i][j] = Integer.parseInt(st.nextToken());
                currentGround[i][j] = 5;
            }
        }
        for(int i=0;i<treeCount;i++) {
            st = new StringTokenizer(bf.readLine());
            int treeX = Integer.parseInt(st.nextToken())-1;
            int treeY = Integer.parseInt(st.nextToken())-1;
            int treeAge = Integer.parseInt(st.nextToken());
            trees.add(new tree(treeX, treeY,treeAge));
        }

        //조건의 year 만큼 반복
        for(int i=0;i<year;i++) {
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(trees.size());

    }

    public static void spring() {
        Queue<tree> newTrees = new PriorityQueue<>();
        int currentX = 0; int currentY = 0; int currentAge = 0;

        while (!trees.isEmpty()) {
            tree popTree = trees.poll();
            currentX = popTree.x;
            currentY = popTree.y;
            currentAge = popTree.age;

            //나무의 나이만큼 땅에 영양분이 있을때
            if(currentGround[currentX][currentY] >= currentAge) {
                newTrees.add(new tree(currentX, currentY,currentAge+1));
                currentGround[currentX][currentY] -= currentAge;
            }
            else { //나무가 죽는 경우
                deadTree.add(new tree(currentX,currentY,currentAge));
            }
        }

        //우선순위 큐를 초기화
        trees = newTrees;
    }

    public static void summer() {

        for(int i=0;i<deadTree.size();i++) {
            tree tree = deadTree.get(i);
            currentGround[tree.x][tree.y] += (tree.age/2);
        }
        //죽은 나무 리스트 초기화
        deadTree.clear();
    }

    static int[]x = {1,-1,0,0,1,-1,1,-1};
    static int[]y = {0,0,1,-1,-1,1,1,-1};
    public static void fall() {
        Queue<tree> newTrees = new PriorityQueue<>();
        int currentX = 0; int currentY = 0; int Age = 0;

        while (!trees.isEmpty()) {
            tree popTree = trees.poll();
            Age = popTree.age;

            if(Age%5 ==0) { //나무의 나이가 5의 배수인 경우

                for(int i=0;i<8;i++) {
                    currentX = popTree.x + x[i];
                    currentY = popTree.y + y[i];

                    if((0<=currentX && currentX<num) && (0<=currentY && currentY<num)) { //나무 번식이 가능한 좌표
                        newTrees.add(new tree(currentX,currentY,1)); //나이가 1인 나무 생성
                    }
                }
            }
            newTrees.add(new tree(popTree.x, popTree.y, Age));
        }

        //초기화
        trees = newTrees;
    }

    public static void winter() {
        for(int i=0;i<num;i++) {
            for(int j=0;j<num;j++) {
                currentGround[i][j] += addGround[i][j];
            }
        }
    }


}
