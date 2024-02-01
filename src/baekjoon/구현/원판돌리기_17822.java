package baekjoon.구현;

import java.util.*;
import java.io.*;
public class 원판돌리기_17822 {
    static List<List<Integer>> circle;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        circle = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            circle.add(new ArrayList<>());
        }
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                circle.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotation(x, d, k);
            remove();
        }

        System.out.println(getResult());
    }

    static void rotation(int x, int d, int k) {
        int init = 1; // 배수
        for(int i=x; i<=N; i=(x*init)) {
            List<Integer> target = circle.get(i);
            if(d==0) { // 시계 방향
                for(int j=0; j<k; j++) {
                    int lastVal = target.remove(M - 1);
                    target.add(0, lastVal);
                }
            } else { // 반시계 방향
                for(int j=0; j<k; j++) {
                    int firstVal = target.remove(0);
                    target.add(firstVal);
                }
            }
            init++;
        }
    }

    static void remove() {
        boolean[][] visit = new boolean[N+1][M];
        boolean isRemoved = false;
        double sum = 0;
        double count = 0;

        // 좌우 검증
        for(int i=1; i<=N; i++){
            List<Integer> target = circle.get(i);
            for(int j=0; j<M; j++) {
                if(j==0 && target.get(j)!=0 && target.get(M-1)!=0 && target.get(j).equals(target.get(M-1))) {
                    visit[i][0] = true;
                    visit[i][M-1] = true;
                    isRemoved = true;
                } else if(j!=0 && target.get(j)!=0 && target.get(j-1)!=0 && target.get(j).equals(target.get(j-1))) {
                    visit[i][j] = true;
                    visit[i][j-1] = true;
                    isRemoved = true;
                }

                sum += target.get(j);
                if(target.get(j) != 0) {
                    count++;
                }
            }
        }

        // 상하 검증
        for(int i=2; i<=N; i++) {
            List<Integer> outCircle = circle.get(i);
            List<Integer> intCircle = circle.get(i-1);

            for(int j=0; j<M; j++) {
                if(intCircle.get(j)!=0 && outCircle.get(j)!=0 && intCircle.get(j).equals(outCircle.get(j))) {
                    visit[i][j] = true;
                    visit[i-1][j] = true;
                    isRemoved = true;
                }
            }
        }

        if(!isRemoved) {
            double avg = sum/count;
            for(int i=1; i<=N; i++) {
                List<Integer> target = circle.get(i);
                for(int j=0; j<M; j++) {
                    if(target.get(j)!=0 && target.get(j) > avg) {
                        target.set(j, target.get(j)-1);
                    } else if(target.get(j) !=0 && target.get(j) < avg) {
                        target.set(j, target.get(j)+1);
                    }
                }
            }
        } else {
            for(int i=1; i<=N; i++) {
                List<Integer> target = circle.get(i);
                for(int j=0; j<M; j++) {
                    if(visit[i][j]) {
                        target.set(j, 0);
                    }
                }
            }
        }
    }

    static int getResult() {
        int result = 0;
        for(int i=1; i<=N; i++) {
            List<Integer> target = circle.get(i);
            for(int j=0; j<M; j++) {
                result += target.get(j);
            }
        }
        return result;
    }

}
