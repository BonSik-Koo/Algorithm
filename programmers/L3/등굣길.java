//DP 문제

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][]map = new int[m+1][n+1];
        int[][]dp = new int[m+1][n+1];
        for(int[] ar: puddles){
            map[ar[0]][ar[1]] = -1; //웅덩이 표시
        }
        
        dp[1][0] = 1; 
        for(int i=1;i<=m;i++){
            for(int j=1; j<=n;j++){
                if(map[i][j] == -1){
                    dp[i][j] = 0; //웅덩이 인 경우
                    continue;
                }
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000007;
            }
        }
        
        int answer = dp[m][n];
        return answer;
    }
}
