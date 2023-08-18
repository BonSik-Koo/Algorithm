class Solution {
    public int solution(int sticker[]) {
        
        //원소가 한개뿐인 예외 상황
        if(sticker.length==1)
            return sticker[0];
        
        int[] d1 = new int[sticker.length]; //첫번째 원소가 포함되는 배열
        int[] d2 = new int[sticker.length]; //첫번째 원소가 포함되지 않는 배열
        
        d1[0] = sticker[0];
        d1[1] = sticker[0];
        for(int i=2;i<sticker.length-1;i++){ //첫번째 원소가 포함되니 자동으로 마지막 원소는 포함할수 없음
            d1[i] = Math.max(d1[i-1], d1[i-2]+sticker[i]);
        }
        
        d2[0] = 0;
        d2[1] = sticker[1];
        for(int i=2;i<sticker.length;i++){
            d2[i] = Math.max(d2[i-1], d2[i-2]+sticker[i]);
        }
        
        int answer = 0;
        answer = Math.max(d1[sticker.length-2], d2[sticker.length-1]);
        return answer;
    }
}
