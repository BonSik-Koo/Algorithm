package programmers.백트래킹.사라지는발판_L3;

class Solution {
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    static class Result {
        boolean isWin;
        int count;
        public Result(boolean isWin, int count){
            this.isWin = isWin;
            this.count = count;
        }
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        // A부터 항상 시작
        Result result = turn(board, aloc[0], aloc[1], bloc[0], bloc[1], 0);
        return result.count;
    }

    // false : 자신이 진 경우/상대방이 이긴 경우
    // true : 자신이 이긴 경우/상대방이 진 경우
    public Result turn(int[][] board, int r1, int c1, int r2, int c2, int cnt){
        // 상대방과 같은 위치에 있는 경우 -> 다음 차례에서 자신이 움직여야하니 반드시 진다.
        if(board[r1][c1] == 0){
            return new Result(false, cnt); // 상대방이 한칸더 이동해야지 자신이 지기 때문에 : +1
        }

        board[r1][c1] = 0;
        int maxLose = Integer.MIN_VALUE;
        int minWin = Integer.MAX_VALUE;
        boolean isCanGo = false;
        for(int i=0; i<4; i++){
            int nr = r1 + dr[i];
            int nc = c1 + dc[i];
            if(nr<0 || nr>=board.length || nc<0 || nc>=board[0].length || board[nr][nc]==0) {
                continue;
            }
            isCanGo = true;
            // 상대방이 움직일 차례
            Result result = turn(board, r2, c2, nr, nc, cnt + 1);

            if(result.isWin){ // 상대방이 이긴 경우, 자신은 지게 됨
                maxLose = Math.max(maxLose, result.count);
            }else{
                minWin = Math.min(minWin, result.count);
            }
        }

        board[r1][c1] = 1;
        // 자신이 움직일 곳이 없는 경우
        if(!isCanGo){
            return new Result(false, cnt);
        }

        if(minWin < Integer.MAX_VALUE){
            return new Result(true, minWin); // 내가 이긴다면 하위 경우의 수 중 최소값
        }else{
            return new Result(false, maxLose); // 내가 진다면 하위 경우의 수 최대값
        }
    }

}