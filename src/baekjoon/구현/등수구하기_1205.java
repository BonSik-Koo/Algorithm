package baekjoon.구현;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 등수구하기_1205 {
	public static void main (String[]args){
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int new_score = scanner.nextInt();
		int p = scanner.nextInt();
		
		Integer[] arr = new Integer[n];
		for(int i=0; i<n; i++){
			arr[i] = scanner.nextInt();
		}
		Arrays.sort(arr, Collections.reverseOrder());
	
		//예외
		if(n == p && new_score <= arr[arr.length-1])
			System.out.print(-1);
		else{
			int rank = 1;
			for(int i=0; i<arr.length; i++){
				if(new_score < arr[i])
					rank++;
				else
					break;
			}
			System.out.print(rank);
		}
	}
	
}
