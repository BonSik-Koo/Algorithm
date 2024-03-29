package programmers.L3.bfs;

import java.util.*;

/**
 * !hard!
 * -풀이 방법-
 * 1 : 주어진 사각형들을 이차원 배열안에 테투리(경로)를 그리면서 경로를 표시한다.
 *     <주의할점>
 *     1.1 : 각 사각형들의 겹치는 부분을 그리지 않고 겹치지않은 테투리만 경로로 표시한다.
 *     1.2 : [테스트1]
 *           만약 주어진 사각형의 좌표를 그대로 사용하게 된다면 'ㄷ' 이렇게 턴하는 모양 부분에서 4개의 좌표가 바로옆으로 이어지기 때문에 나중에 bfs로 경로를 따라갈때 문제가 발생한다.
 *           ('ㄷ'모양으로 돌아가야되는데 바로 옆으로 이동하게되는 문제)
 *           그렇기 때문에 주어진 사각형의 좌표를 모두 "*2"하여 사각형을 크기를 2배늘려 바로이어지지 않게 한다.
 *           [테스트2]
 *           주어진 좌표그대로 사용하게 되면 중앙에 "정사각형"모양의 경로로도 이동하게 된다(좌표가 바로 붙어있기 때문에)
 *           위와 같이 모든 사각형의 좌표를 "*2"하면 사각형이 2배 커지기 때문에 바로이어지지 않는다.
 * 2 : 위방식으로 먼저 경로를 모두 표시한뒤 "bfs"로 주어진 경로를 따라가며 가장 빨리도착하는 시간을 구한다.
 * 3 : 시간을 그대로 출력하는 것이 아니라 사각형들을 2배 늘려 경로가 2배 증가하였기 때문에 시간을 "/2"해준다.
 */
public class 아이템줍기 {
    static class Rectangle {
        int x1, x2;
        int y1, y2;
        public Rectangle(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
    static class Pos {
        int x , y, num;
        public Pos(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    static List<Rectangle> rectangleList = new ArrayList<>();
    static boolean[][] router;
    static boolean[][] visit;
    static int[]x = {1,-1,0,0,};
    static int[]y= {0,0,1,-1};

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        int maxX=0, maxY=0;
        for(int i=0;i<rectangle.length;i++) {
            int x1 = rectangle[i][0]*2;
            int y1 = rectangle[i][1]*2;
            int x2 = rectangle[i][2]*2;
            int y2 = rectangle[i][3]*2;

            maxX = Math.max(maxX, x2); maxY = Math.max(maxY, y2);
            rectangleList.add(new Rectangle(x1,x2,y1,y2));
        }

        router = new boolean[maxX+1][maxY+1];
        visit = new boolean[maxX+1][maxY+1];
        for(int i = 0; i< router.length; i++) {
            Arrays.fill(router[i], false);
            Arrays.fill(visit[i], false);
        }

        drawRoute();
        answer = bfs(characterX*2, characterY*2, itemX*2, itemY*2);
        System.out.println(answer);

        return answer;
    }

    public static void drawRoute() {

        boolean check = false;
        for(int i=0;i<rectangleList.size();i++) {
            Rectangle rectangle = rectangleList.get(i);

            for(int j= rectangle.x1;j<=rectangle.x2;j++) {
                for(int k=rectangle.y1;k<=rectangle.y2;k++) {

                    if((rectangle.y1<k && k< rectangle.y2) && (rectangle.x1<j && j< rectangle.x2))
                        continue;

                    for(int h =0;h<rectangleList.size();h++) {
                        if(i!=h) {
                            Rectangle otherRectangle = rectangleList.get(h);
                            if( (otherRectangle.x1<j && j<otherRectangle.x2) && (otherRectangle.y1<k && k<otherRectangle.y2) ) { //경로가 될수없는 좌표
                                check =true;
                                break;
                            }
                        }
                    }
                    if(check==true) {
                        check = false; //초기화
                        continue;
                    }
                    router[j][k] = true; //경로임을 표시
                }
            }
        }

//test 출력
//        for(int i=2;i<router.length;i++) {
//            for(int j=2;j<router[0].length;j++) {
//                if(router[i][j]==true)
//                    System.out.print("1" + " ");
//                else
//                    System.out.print("0" + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("=======================");
    }

    public static int bfs(int charX, int charY , int itemX, int itemY) {

        int result=0;
        Queue<Pos> queue = new LinkedList<>();
        visit[charX][charY] = true; //처음 방문 표시
        queue.add(new Pos(charX, charY, 0));

        while (!queue.isEmpty()) {
            Pos poll = queue.poll();

            if((poll.x==itemX) && (poll.y==itemY)) {
                result = poll.num/2;
                break;
            }
            for(int i=0;i<4;i++) {
                int newX = poll.x + x[i];
                int newY = poll.y + y[i];
                int newNum = poll.num + 1;
                //유망성 검사
                if( (2>newX || newX>=visit.length) || (2>newY || newY>=visit[0].length) )
                    continue;
                if(router[newX][newY]==true && visit[newX][newY]!=true) {
                    queue.add(new Pos(newX, newY, newNum));
                    visit[newX][newY]= true; //방문 처리
                }
            }
        }
        return result;

    }






    public static void main(String[] args) {

        //int[][]array = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int[][]array ={{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}};
        //int[][]array ={{1,1,5,7}};

        //solution(array, 1,3,7,8);
        solution(array, 9,7,6,1);
        //solution(array, 1,1,4,7);

    }
}
