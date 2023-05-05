import java.util.*;

//dfs 완전탐색 -> 모든 조합 구하기
class Solution {
    public int[] discount = {10, 20, 30, 40}; //이모티콘 할인율
    public int[][] pUsers;
    public int[] pEmoticons;
    public PriorityQueue<int[]> result; //[총 이모티콘 플러스 서비스 가입 수, 총 판매금액]
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        pUsers = new int[users.length][2];
        for(int i=0;i<users.length;i++){
            pUsers[i][0] = users[i][0];
            pUsers[i][1] = users[i][1];
        }
        pEmoticons = new int[emoticons.length];
        for(int i=0;i<emoticons.length;i++){
            pEmoticons[i] = emoticons[i];
        }
        int[][]emoticonsState = new int[4][1];
        result = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] a1, int[] a2){
                if(a1[0] == a2[0])
                    return a2[1] - a1[1];
                return a2[0] - a1[0];
            }
        });
        
        //알고리즘 시작
        dfs(0, emoticonsState);
        
        //result
        int[] answer = result.poll();
        return answer;
    }
    
    //모든 조합 구하기
    public void dfs(int index, int[][]emoticonState){
        //이모티콘 별 할인율이 모두 할당 되었을때
        if(index == pEmoticons.length){
            resultCalcuate(emoticonState);
            return;
        }
        
        //[10,20,30,40]
        for(int i=0;i<4;i++){
            int fee = (100-discount[i]) * pEmoticons[index]/100; //최대한 int로 계산하기
            emoticonState[i][0] += fee;
            dfs(index+1, emoticonState);
            emoticonState[i][0] -= fee;
        }
    }
    
    //서비스 가입자 수, 총 판매 금액 계산
    public void resultCalcuate(int[][] emoticonState){
        int[] temp = new int[2];
        for(int i=0;i<pUsers.length;i++){
            int per = pUsers[i][0];
            int price = pUsers[i][1];
            
            int startIndex = 0;
            int div = per / 10;
            int rem = per % 10;
            if(rem == 0)
                startIndex = div-1;
            else 
                startIndex = div;
            
            //자신의 할인률 이상의 할인률을 가진 이모티콘이 아무것도 없는 경우
            if(startIndex > 3)
                continue;
                
            //일정 할인률 이상의 이모티콘 가격 합 구하기
            int sum = 0;
            for(int j=startIndex; j<4;j++){
                sum += emoticonState[j][0];
            }
           
            //이모티콘 플러스 서비스 가입인 경우
            if(sum >= price){
                temp[0]+=1;
            }
            //이모티콘 구매인 경우
            else{
                temp[1]+=sum;
            }
        }
        result.add(temp);
    }
}
