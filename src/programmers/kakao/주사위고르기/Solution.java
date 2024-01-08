package programmers.kakao.주사위고르기;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    int[][] DICE;
    int result = Integer.MIN_VALUE;
    int[] resultArr;

    public int[] solution(int[][] dice) {
        DICE = dice;
        resultArr = new int[dice.length/2];

        find(dice.length, 0, 0, new int[dice.length/2]);
        return resultArr;
    }

    // 주사위를 선택하는 경우의 수 -> 백트래킹
    private void find(int n, int idx, int count, int[] idxA){
        if(count == n/2){
            List<Integer> sumA = new ArrayList<>();
            List<Integer> sumB = new ArrayList<>();

            findSumCombination(0, 0, idxA, sumA);
            int[] idxB = getIdxB(n, idxA);
            findSumCombination(0, 0, idxB, sumB);

            int winNumA = findWinNumA(sumA, sumB);
            if(result < winNumA) {
                for(int i=0; i<idxA.length; i++){
                    resultArr[i] = (idxA[i] + 1);
                }
                result = winNumA;
            }

            return;
        }

        for(int i=idx; i<n; i++){
            idxA[count] = i;
            find(n, i+1, count+1, idxA);
        }
    }

    // 주사위 합 조합 구하기 -> 완탐
    private void findSumCombination(int sum, int count, int[] idxArr, List<Integer> sumArr){
        if(count == idxArr.length){
            sumArr.add(sum);
            return;
        }

        for(int i=0; i<6; i++){
            findSumCombination(sum + DICE[idxArr[count]][i], count+1, idxArr, sumArr);
        }
    }

    // A가 이기는 경우 -> 이분탐색
    // 누적합 사용도 가능
    private int findWinNumA(List<Integer> sumA, List<Integer> sumB){
        Collections.sort(sumB); // 이분 탐색을 위해 sumB 정렬

        int winNum = 0;
        for(int i=0; i<sumA.size(); i++){
            int val = sumA.get(i);

            boolean exist = false; // 인덱스가 0이 되는 경우가, 이기는 경우가 없는 경우와 모두 이기는 경우가 있으니 구분하기 위해
            int result = 0;
            int low = 0;
            int high = sumB.size()-1;
            while(low <= high){
                int mid = (low + high) / 2;

                if(sumB.get(mid) < val){
                    exist = true;
                    low = mid + 1;
                    result = Math.max(result, mid);
                }else{
                    high = mid - 1;
                }
            }

            winNum += (exist) ? (result+1) : 0;
        }

        return winNum;
    }

    private int[] getIdxB(int n, int[] idxA){
        int[] B = new int[n/2];
        int idx = 0;
        for(int i=0; i<n; i++){
            boolean exist = false;
            for(int a=0; a<idxA.length; a++){
                if(idxA[a] == i){
                    exist = true;
                    break;
                }
            }

            if(!exist) {
                B[idx] = i;
                idx++;
            }
        }
        return B;
    }

}