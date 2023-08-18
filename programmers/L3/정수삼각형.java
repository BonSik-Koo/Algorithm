//DP 문제
class Solution {
    public int solution(int[][] triangle) {
        
        int[][] arr = new int[triangle.length][triangle.length];
        
        //DP 알고리즘 시작
        arr[0][0]  = triangle[0][0]; //초기값
        for(int i=1; i<triangle.length;i++){
            //왼쪽 벽 라인
            arr[i][0] = arr[i-1][0] + triangle[i][0];
            //오른쪽 벽 라인
            arr[i][i] = arr[i-1][i-1] + triangle[i][i];
        
            //가운데 값
            for(int j=1;j<i;j++){
                arr[i][j] = Math.max(arr[i-1][j-1]+triangle[i][j], arr[i-1][j]+triangle[i][j]);
            }
        }
        
        //정답 찾기
        int answer = 0;
        for(int i=0;i<triangle.length;i++){
            answer = Math.max(answer, arr[triangle.length-1][i]);
        }
        return answer;
    }
}
