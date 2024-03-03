package programmers.백트래킹.수레움직이기_L3;

class Solution {
    private int n, m;
    private int[][] map;
    private boolean[][][] visit; //0:빨강, 1:파랑
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    // private boolean redIsDone = false, blueIsDone = false;

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        visit = new boolean[2][n][m];
        map = new int[n][m];

        Cart redCart = null, blueCart = null;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++){
                if(maze[i][j] == 1) {
                    redCart = new Cart(i, j);
                }
                if(maze[i][j] == 2){
                    blueCart = new Cart(i, j);
                }
                map[i][j] = maze[i][j];
            }
        }

        visit[0][redCart.x][redCart.y] = true;
        visit[1][blueCart.x][blueCart.y] = true;
        int count = backTracking(redCart, blueCart, false, false, 0);
        return (count == Integer.MAX_VALUE) ? 0 : count;
    }

    private int backTracking(Cart redCart, Cart blueCart, boolean redIsDone, boolean blueIsDone, int count) {
        if(redIsDone && blueIsDone) {
            return count;
        }
        int answer = Integer.MAX_VALUE;

        // 경우 16가지
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++){
                Cart newRedCart = (!redIsDone) ? Cart.createCart(redCart.x, redCart.y, i) : redCart;
                Cart newBlueCart = (!blueIsDone) ? Cart.createCart(blueCart.x, blueCart.y, j) : blueCart;

                if(!isMoved(newRedCart, newBlueCart, redCart, blueCart, redIsDone, blueIsDone)) {
                    continue;
                }

                visit[0][newRedCart.x][newRedCart.y] = true;
                visit[1][newBlueCart.x][newBlueCart.y] = true;
                if(map[newRedCart.x][newRedCart.y] == 3) {
                    redIsDone = true;
                }
                if(map[newBlueCart.x][newBlueCart.y] == 4) {
                    blueIsDone = true;
                }

                answer = Math.min(answer, backTracking(newRedCart, newBlueCart, redIsDone, blueIsDone, count+1));

                // redIsDone = false;
                // blueIsDone = false;
                visit[0][newRedCart.x][newRedCart.y] = false;
                visit[1][newBlueCart.x][newBlueCart.y] = false;
            }
        }

        return answer;
    }

    private boolean isMoved(Cart redCart, Cart blueCart, Cart preRedCart, Cart preBlueCart, boolean redIsDone, boolean blueIsDone) {
        // 빨강 수레 기본 검증
        if(redCart.x<0 || redCart.x>=n || redCart.y<0 || redCart.y>=m || map[redCart.x][redCart.y]==5) {
            return false;
        }

        // 파랑 수레 기본 검증
        if(blueCart.x<0 || blueCart.x>=n || blueCart.y<0 || blueCart.y>=m || map[blueCart.x][blueCart.y]==5) {
            return false;
        }

        // 빨강 수레 방문 검증 (도착 위치는 계속 유지)
        if(visit[0][redCart.x][redCart.y] && !redIsDone) {
            return false;
        }

        // 파랑 수레 방문 검증
        if(visit[1][blueCart.x][blueCart.y] && !blueIsDone) {
            return false;
        }

        // 스위치 검증
        if(redCart.x == preBlueCart.x && redCart.y == preBlueCart.y && blueCart.x == preRedCart.x && blueCart.y == preRedCart.y) {
            return false;
        }

        // 같은 위치인지
        if(redCart.x == blueCart.x && redCart.y == blueCart.y) {
            return false;
        }

        return true;
    }

    private static class Cart {
        private int x, y;
        public Cart(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public static Cart createCart(int x, int y, int idx) {
            return new Cart(x+dx[idx], y+dy[idx]);
        }
    }
}