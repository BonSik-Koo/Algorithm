package programmers.L3.파괴되지않은건물패키지;

/**
 * 다시 풀문제!!
 *
 * - skill 배열을 단순히 for문으로 하게 되면 배열 access 횟수의 복잡도가 증가
 * - 기준 배열을 마지막 한번 access 하고 누적합 방식을 사용
 * - 양 끝에만 값을 표시하고 난뒤 가로,세로를 반복하면서 누적합 방식으로 값을 채운다.
 *
 * 참고 링크: https://jangcenter.tistory.com/121
 */

public class 파괴되지않은건물 {

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int N = board.length;
        int M = board[0].length;
        int[][] temp = new int[N+1][M+1];
        for(int i=0;i<skill.length;i++) {
            int type = skill[i][0]; int r1 = skill[i][1]; int c1=skill[i][2]; int r2=skill[i][3]; int c2=skill[i][4]; int degree=skill[i][5];
            degree = degree * (type==1?-1:1);
            temp[r1][c1] +=degree; temp[r1][c2+1] +=degree*-1;
            temp[r2+1][c1]+=degree*-1; temp[r2+1][c2+1]+=degree;
        }
        calculate(temp);

        //최종 배열 값 계산
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(board[i][j]+temp[i][j] >0)
                    answer++;
            }
        }

        return answer;
    }

    public static void calculate(int [][]temp) {
        int x = temp.length; int y = temp[0].length;
        //세로 누적합 계산
        for(int i=0;i<y;i++) {
            for(int j=1;j<x;j++){
                temp[j][i] = temp[j][i] + temp[j-1][i];
            }
        }

        //가로 누적합 계산
        for(int i=0;i<x;i++) {
            for(int j=1;j<y;j++){
                temp[i][j] = temp[i][j] + temp[i][j-1];
            }
        }
    }

    public static void main(String[] args) {

        int [][] board ={{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        int [][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        int solution = solution(board, skill);
        System.out.println(solution);
    }

}
