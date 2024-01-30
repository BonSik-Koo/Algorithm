package baekjoon.구현;

import java.util.*;
import java.io.*;
public class 빗물_14719 {
    static int[] blocks;
    static int H, W;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        blocks = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        while(true) {
            int isAddRainCnt = 0;
            for (int i = 1; i < W; i++) {
                if (blocks[i - 1] > blocks[i]) {
                    boolean isAddRain = addRain(i);
                    if (isAddRain) {
                        isAddRainCnt++;
                    }
                }
            }

            if (isAddRainCnt == 0) {
                break;
            }
        }
        System.out.println(result);
    }

    static boolean addRain(int startIdx) {
        boolean isAddRain = false;
        int endIdx = -1;
        int startHeight = blocks[startIdx];
        for(int i=startIdx+1; i<W; i++){
            if(startHeight < blocks[i]) {
                endIdx = i;
                isAddRain = true;
                break;
            }
        }

        if(isAddRain) { // 빗물을 채울 수 있는 경우
            int maxHeight = Math.min(blocks[startIdx-1], blocks[endIdx]);
            for(int i=startIdx; i<endIdx; i++) { // 빗물 채우기
                if(maxHeight > blocks[i]) {
                    int addedHeight = (maxHeight - blocks[i]);
                    blocks[i] += addedHeight;
                    result += addedHeight;
                }
            }
        }
        return isAddRain;
    }

}
