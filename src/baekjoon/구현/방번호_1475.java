package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 방번호_1475 {
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int setCount = 1;
		int[] set = new int[9];
		Arrays.fill(set, 1);
		set[6]+=1; //6,9 서로 상호 사용 가능
		for(int i=0;i<str.length();i++){
			int value = Character.getNumericValue(str.charAt(i));
			
			//값 변화
			if(value==9){
				value=6;
			}
			
			//새로운 세트가 필요한 경우
			if(set[value]<=0){
				for(int j=0;j<9;j++){
					if(j==6){
						set[j]+=1;
					}
					set[j]+=1;
				}
				setCount++;
			}
			set[value]-=1;
		}
		
		System.out.println(setCount);
	}
}
