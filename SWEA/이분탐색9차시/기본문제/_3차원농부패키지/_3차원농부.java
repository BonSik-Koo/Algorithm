package SWExpertAcademyProblem.이분탐색9차시.기본문제._3차원농부패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _3차원농부 {

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/이분탐색9차시/res/input5.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            //초기화
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int []cow = new int[N];
            int []horse = new int[M];
            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<N;i++)
                cow[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<M;i++)
                horse[i] = Integer.parseInt(st.nextToken());

            //정렬
            Arrays.sort(cow);
            Arrays.sort(horse);

            //이분 탐색 로직
            int minL = Integer.MAX_VALUE;
            int count = 0;
            for(int i=0;i<N;i++){
                int cow_y = cow[i];
                int min = 0; int max = M-1;
                while (min<=max){
                    int mid = min + (max-min)/2;
                    int ca  = Math.abs(horse[mid]-cow_y);
                    if(minL > ca) { //최소 거리를 발견한 경우
                        minL = ca;
                        count = 1;

                    }else if(minL == ca){ //최소 거리랑 같은 경우
                        count++;
                    }

                    if(mid!=min && mid!=max){ //양끝이 위치가 아닌 경우 - 3개 이상일때
                        int l1 = Math.abs(horse[mid-1] - cow_y);
                        int l2 = Math.abs(horse[mid+1] - cow_y);

                        if(l2 > l1){ //min-1이 또 최소일 경우
                            max = mid-1;
                        }else if (l1 > l2) {//mid+1이 또 최소일 경우
                            min = mid+1;
                        }else{ //현재 mid 위치가 최소일 경우
                            break;
                        }
                    }else if(mid==min && mid!=max){ //2개일때
                        int l2 = Math.abs(horse[mid+1] - cow_y);
                        if(minL >=l2)
                            min = mid+1;
                        else
                            break;
                    }else if(mid==max && mid!=min){ //2개일때
                        int l1 = Math.abs(horse[mid-1] - cow_y);
                        if(minL >= l1)
                            max = mid-1;
                        else
                            break;
                    }else //1개일때
                        break;
                }
            }

            //출력
            System.out.println("#"+test_case+" "+(minL+Math.abs(c2-c1))+" "+count);
        }
    }

}
