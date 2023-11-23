package programmers.kakao.미로탈출명령어;

class Solution {
    String answer = "impossible";
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    String[] name = {"d", "l", "r", "u"};
    int endRow, endCol;
    int maxRow, maxCol;
    StringBuilder sb = new StringBuilder();
    String result = null;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        endRow = r;
        endCol = c;
        maxRow = n;
        maxCol = m;

        dfs(x, y, 0, k);

        return (result != null) ? result : "impossible";
    }

    public void dfs(int row, int col, int count, int k){
        // 명령어를 사전 순으로 탐색하기 때문에, 처음 찾은 명령어가 반드시 최소값
        // 그러므로 명령어를 찾으면 탐색이 종료되어야 된다.
        if(result != null){
            return;
        }
        if(row == endRow && col == endCol && count == k){
            result = sb.toString();
            return;
        }

        // 가지치기(백 트래킹)
        int minDistance = Math.abs(row - endRow) + Math.abs(col - endCol);
        if(minDistance > (k - count)){ //최단 거리로도 도착하지 못하는 경우
            return;
        }
        int remainCount = (k - count) - minDistance;
        if((remainCount % 2) != 0){
            return;
        }
        //최단 거리로 도착했을 때, 남은 이동 횟수는 짝수여야지 최소한 가능
        //최단 거리가 짝수/홀수이면 무조건 도착 거리는 짝수/홀수 임.
        //결국 최단 거리의 배수 형태이기 때문에, 최단 거리로 도착했을 때 기준으로 도착 가능한지를 미리 판단하면 됨.

        for(int i=0; i<4; i++){
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if(1>newRow || newRow>maxRow || 1>newCol || newCol>maxCol){
                continue;
            }

            sb.append(name[i]);
            dfs(newRow, newCol, count+1, k);
            sb.delete(count, count+1);
        }
    }

}