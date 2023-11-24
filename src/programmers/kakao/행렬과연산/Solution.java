package programmers.kakao.행렬과연산;

import java.util.*;
public class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        List<Deque<Integer>> columns = new LinkedList<>();
        List<Deque<Integer>> rows = new LinkedList<>();

        init(rc, columns, rows);
        for(String op : operations){
            if(op.equals("ShiftRow")){
                shiftRow(columns, rows);
            }else{
                rotate(columns, rows);
            }
        }

        int[][] answer = new int[rc.length][rc[0].length];
        for(int i=0; i<rc.length; i++){
            for(int j=0; j<rc[0].length; j++){
                if(j==0){
                    answer[i][j] = columns.get(0).pollFirst();
                }else if(j==rc[0].length-1){
                    answer[i][j] = columns.get(1).pollFirst();
                }else{
                    answer[i][j] = rows.get(i).pollFirst();
                }
            }
        }
        return answer;
    }

    public void rotate(List<Deque<Integer>> columns, List<Deque<Integer>> rows){
        Deque<Integer> firstColumn = columns.get(0);
        Deque<Integer> endColumn = columns.get(1);
        Deque<Integer> firstRow = rows.get(0);
        Deque<Integer> endRow = rows.get(rows.size() - 1);

        // 첫 열 작업
        firstRow.addFirst(firstColumn.pollFirst());

        // 첫 행 작업
        endColumn.addFirst(firstRow.pollLast());

        //마지막 열 작업
        endRow.addLast(endColumn.pollLast());

        //마지막 행 작업
        firstColumn.addLast(endRow.pollFirst());
    }

    public void shiftRow(List<Deque<Integer>> columns, List<Deque<Integer>> rows){
        // 열은 끝 원소를 첫 원소에 넣기
        for(Deque<Integer> de : columns){
            int end = de.pollLast();
            de.addFirst(end);
        }

        // 행은 첫/끝 행 자체를 swap
        Deque<Integer> endDeque = rows.remove(rows.size() - 1);
        rows.add(0, endDeque);
    }

    public void init(int[][] rc, List<Deque<Integer>> columns, List<Deque<Integer>> rows){
        // 행, 열 댁 구성
        columns.add(new LinkedList<>());
        columns.add(new LinkedList<>());
        for(int i=0; i<rc.length; i++){
            rows.add(new LinkedList<>());
            for(int j=0; j<rc[0].length; j++){
                if(j==0){
                    columns.get(0).addLast(rc[i][j]);
                }else if(j==rc[0].length-1){
                    columns.get(1).addLast(rc[i][j]);
                }else{
                    rows.get(i).addLast(rc[i][j]);
                }
            }
        }
    }

}
