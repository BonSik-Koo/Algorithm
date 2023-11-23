package programmers.kakao.표현가능한이진트리;

import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0; i<numbers.length; i++){
            String binary = toBinaryString(numbers[i]);
            if(isBinaryTree(binary)){
                answer[i] = 1;
            }else{
                answer[i] = 0;
            }
        }

        return answer;
    }

    public String toBinaryString(long number){
        // 이진수 문자열로 변환
        String binary = Long.toBinaryString(number);

        // 포화 이진 트리를 만들기 위 앞에 채워야 할 0 개수 구하기
        int len = binary.length();
        int count = 1;
        int level = 1;
        while(len > count){
            level *= 2; //포화 트리 레벨 별 필요한 노드 개수, 0-1, 1-2, 2-4 ...
            count += level; // 레벨 별 정상 포화 트리 노드 개수 누적
        }
        int diff = count - len;

        return "0".repeat(diff) + binary;
    }

    public boolean isBinaryTree(String str){
        int root = str.length() / 2;
        String right = str.substring(0, root);
        String left = str.substring(root+1);

        //root 노드가 서브 트리에 유일한 노드 일 경우
        if(root == 0){
            return true;
        }

        // root 노드가 0일 경우에는 양쪽 서브 트리가 모두 0 이어야 함.
        if(str.charAt(root) == '0'){
            return isZeroBinaryTree(right) && isZeroBinaryTree(left);
        }

        // root 노드가 1인 경우니 양쪽 서브 트리 탐색 이어서 하기
        return isBinaryTree(right) && isBinaryTree(left);
    }

    public boolean isZeroBinaryTree(String str){
        //root 노드가 서브 트리에 유일한 노드 일 경우
        if(str.length() == 1){
            if(str.equals("1")){
                return false;
            }
            return true;
        }

        int root = str.length() / 2;
        String right = str.substring(0, root);
        String left = str.substring(root+1);

        // 양쪽 서브 트리가 모두 0 이어야 함.
        if(str.charAt(root) == '1'){
            return false;
        }

        // root 노드가 0인 경우니 양쪽 서브 트리가 모두 0인지 탐색 이어서 하기
        return isZeroBinaryTree(right) && isZeroBinaryTree(left);
    }

}