package SWExpertAcademyProblem.DP5차시.배낭채우기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - DP문제
 * - 0,1배낭채우기 문제
 */
public class Knapsack {

    public static class Product { //물건 정보
        int w; int p;
        public Product(int w, int p) {
            this.w = w;
            this.p = p;
        }
    }
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/SWExpertAcademyProblem/DP5차시/res/input2.txt"));

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(bf.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //초기화
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken()); //물건의 개수
            int W  = Integer.parseInt(st.nextToken()); //최대 부피
            Product []products = new Product[N+1];
            int[][] value = new int[W+1][N+1]; //가치를 담는 DP 배열
            for(int i=0;i<N;i++){
                st = new StringTokenizer(bf.readLine());
                products[i+1] = new Product(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            //배열 초기화
            for(int i=0;i<=W;i++)
                value[i][0] = 0;
            for(int i=0;i<=N;i++)
                value[0][i] = 0;

            //DP 알고리즘
            for(int i=1;i<=W;i++){
                for(int j=1;j<=N;j++){
                    Product product = products[j]; //검증할 현재 물건
                    if(i<product.w) { //현재 물건이 포함될수 없을때(부피가 더커서)
                        value[i][j] = value[i][j-1];
                    }else { //현재 물건을 포함할수 있을때
                        value[i][j] = Math.max(value[i][j-1], value[i-product.w][j-1]+product.p);
                    }
                }
            }

            //출력
            System.out.println("#"+test_case+" "+value[W][N]);
        }
    }
}
