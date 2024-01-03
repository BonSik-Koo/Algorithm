package baekjoon.이분탐색;


import java.util.*;
import java.io.*;
public class 드래곤앤던전_16434 {
    static long result = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long atk = Long.parseLong(st.nextToken());

        long maxHp = 0;
        long[][] room = new long[N][3];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            long t = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            room[i][0] = t; room[i][1] = a; room[i][2] = h;

            if(t == 1){
                maxHp += ((h/atk+1) * a);
            }
        }

        bs(1, maxHp, atk, room);
        System.out.println(result);
    }

    static void bs(long low, long high, long atk, long[][] room){
        while(low <= high){
            long mid = (high + low) / 2;

            if(verify(atk, mid, room)){
                high = mid - 1;
                result = Math.min(result, mid);
            }else{
                low = mid + 1;
            }
        }
    }

    static boolean verify(long atk, long hp, long[][] rooms){
        long HP = hp;
        for(long[] room : rooms){
            long t = room[0];
            long a = room[1];
            long h = room[2];

            if(t==1){
                HP -= (h%atk==0) ? ((h/atk-1) * a) : ((h/atk) * a);
            }else{
                atk += a;
                HP += h;
                if(HP > hp){ // 최대 생명력을 초과한 경우
                    HP = hp;
                }
            }

            if(HP <= 0){
                return false;
            }
        }

        return true;
    }

}
