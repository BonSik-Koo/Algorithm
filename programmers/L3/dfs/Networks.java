package programmers.L3.dfs;

public class Networks {

    public boolean[]c;
    public int answer =0;

    public int solution(int n, int[][] computers) {

        c =new boolean[n];
        for(int i=0;i<n ;i++) {
            if(c[i]==false) { //방문하지 않았으면 다른 네트워크 상이다!!!
                answer++;
                dfs(i,computers);
            }
        }
        return answer;
    }

    public void dfs(int n ,int [][]computers) {
        c[n]=true;

        for(int i=0;i<computers[n].length;i++ ) {
            if(c[i]==false && computers[n][i]==1) { //방문하였으므로 같은 네트워크 상이다.!!!
                dfs(i, computers);
            }
        }
    }

    public static void main(String[] args) {
        Networks ne = new Networks();
        int[][]a = {{1,1,0}, {1,1,0} , {0,0,1}};

        int t = ne.solution(3, a);
        System.out.println(t);
    }


}
