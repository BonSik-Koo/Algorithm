import java.util.*;

/**
 * -풀이순서-
 * 1. board, table 의 퍼즐별로 각 블럭 좌표들을 bfs로 탐색하여 퍼즐별 하나의 list에 저장하여 boardList, tableList에 저장
 * 2. table 퍼즐들중 하나씩을 꺼내어 board 의 퍼즐들 중에서 들어갈수 있는지 검증
 * 3-1. 검증 방식은 board 퍼즐들둥 하나, table 퍼즐들중 하나씩 비교하고 블럭의 개수와 해당 table 퍼즐의 사용여부로 1차 검사
 * 3-2. 각퍼즐 내부블럭 좌표를 정렬후(그래야 두개의 블럭의 순서를 동일하게 만듬) 각 첫번째 블럭 좌표값으로 나머지 블럭좌표에 빼주기 (가장 처음 블럭 좌표를 0,0 기준으로 좌표를 만들기 위해서)
 * 3-3. 계산후 하나씩 블럭좌표를 비교하고 다르면 table 블럭들을 90도 씩 회전시키면서 반복해보기(회전 알고리즘 필요)
 * 4. board 퍼즐에 들어갈수 있으면 들어간 블럭을 board 블럭을 같은 방식으로 반복해준다.

 ※참고 링크 : https://tmdrl5779.tistory.com/206
 */

public class 퍼즐조각채우기 {

    static class pos implements Comparable<pos>{ //각 블럭별 좌표
        int x ,y ;

        public pos(int i, int j) {
            this.x = i; this.y =j;
        }
        @Override
        public int compareTo(pos o) {
            if(this.x > o.x)
                return 1;
            else if(this.x < o.x)
                return -1;
            else {
                if(this.y > o.y)
                    return 1;
                else
                    return -1;
            }
        }
    }
    static int[] x ={1,-1,0,0};
    static int[] y = {0,0,1,-1};

    public static void bfs(int firstX, int firstY, boolean[][]visit, int[][]field,  List<List<pos>> puzzles, int value) {

        List<pos> list = new ArrayList<>();
        Queue<pos> queue = new LinkedList<>();
        queue.add(new pos(firstX,firstY));
        visit[firstX][firstY]=true;
        list.add(new pos(firstX-firstX,firstY-firstY)); //(0,0) 좌표로 맞춤

        while(!queue.isEmpty()) {

            pos poll = queue.poll();
            for(int i=0;i<4;i++) {

                int newX = poll.x + x[i];
                int newY = poll.y + y[i];
                //유망성 검사
                if((0>newX || newX>=field.length) || (0>newY || newY>=field.length))
                    continue;
                if( field[newX][newY] == value && visit[newX][newY]!=true) {
                    visit[newX][newY] = true; //방문 처리
                    queue.add(new pos(newX, newY));
                    //가장 작은 좌표를 (0,0)으로 맞추어 표현
                    list.add(new pos(newX-firstX, newY-firstY));
                }
            }
        }

        puzzles.add(list);
    }

    public static int findPuzzles(List<List<pos>>board_puzzles, List<List<pos>> table_puzzles) {

        Boolean[] tablePuzzle_check = new Boolean[table_puzzles.size()];
        Arrays.fill(tablePuzzle_check,false);
        int result =0;

        for(int i=0;i<board_puzzles.size();i++) {
            List<pos> current_boardPuzzle = board_puzzles.get(i);

            for(int j=0;j<table_puzzles.size();j++) {
                List<pos> current_tablePuzzle = table_puzzles.get(j);

                //1차 검사
                if((current_boardPuzzle.size() == current_tablePuzzle.size()) && tablePuzzle_check[j]!=true ) {
                    if(checkPuzzle(current_boardPuzzle, current_tablePuzzle)){
                        result+=current_tablePuzzle.size(); //해당 퍼즐 블럭 개수 추가
                        tablePuzzle_check[j] = true; //해당 퍼즐 사용여부 표시
                        break;
                    }

                }
            }
        }
        return result;
    }

    public static Boolean checkPuzzle(List<pos> board, List<pos>table) {

        Boolean check = false;
        Collections.sort(board);//board puzzle 정렬

        for(int i=0;i<4;i++) { //table 한 puzzle 를 90도씩 회전 시키며 체크

            int count = 0;
            Collections.sort(table); //table puzzle 정렬

            int firstX = table.get(0).x;
            int firstY = table.get(0).y;

            //table 한 puzzle 를 회전 시키면 좌표위치가 달라지기 때문
            //table 의 가퍼즐의 블럭 좌표를 다시 가장 작은 좌표를 (0,0)으로 맞추어 표현
            for(int j=0;j<table.size();j++) {
                table.get(j).x = table.get(j).x -firstX;
                table.get(j).y = table.get(j).y -firstY;
            }

            //2차 검사
            for(int j=0;j<board.size();j++) {

                pos current_tableBlock = table.get(j);
                pos current_boardBlock = board.get(j);
                //board 퍼즐과 table 퍼즐이 일치하는 경우
                if((current_tableBlock.x==current_boardBlock.x) && (current_tableBlock.y==current_boardBlock.y)) {
                    count++;
                }
            }

            if(count == board.size()) {
                check =true;
                break;
            }else {
                //90도 회전 시키기기 (x,y) -> (y,-x)
                for(int j=0;j<table.size();j++) {
                    int temp = table.get(j).x;
                    table.get(j).x = table.get(j).y;
                    table.get(j).y = -temp;
                }
            }
        }

        return check;
    }

    public static int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        boolean[][] boardCheck = new boolean[table.length][table.length];
        boolean[][] tableCheck = new boolean[table.length][table.length];
        List<List<pos>> board_puzzle = new ArrayList<>() , table_puzzle = new ArrayList<>();

        for(int i=0;i<table.length;i++) {
            for(int j=0;j<table.length;j++) {

                if(game_board[i][j] ==0 && boardCheck[i][j] !=true)
                    bfs(i,j,boardCheck,game_board,board_puzzle,0 );

                if(table[i][j] ==1 && tableCheck[i][j] !=true)
                    bfs(i,j,tableCheck, table, table_puzzle,1);
            }
        }

        answer = findPuzzles(board_puzzle, table_puzzle);
        return answer;
    }

    public static void main(String[]args) {
        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int [][]table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
//        int[][] game_board = {{0,0,0},{1,1,0},{1,1,1}};
//        int [][]table = {{1,1,1},{1,0,0},{0,0,0}};

        int solution = solution(game_board, table);
        System.out.println(solution);
    }
}
