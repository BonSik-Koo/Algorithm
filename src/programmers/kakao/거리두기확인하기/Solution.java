package programmers.kakao.거리두기확인하기;

import java.util.*;
class Solution {
    char[][] room;
    boolean[][] visit;
    boolean fail;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int k=0; k<5; k++){
            String[] place = places[k];
            fail = false;

            // 대기실 초기 정보
            room = new char[5][5];
            visit = new boolean[5][5];
            List<Person> persons = new LinkedList<>();
            for(int i=0; i<5; i++){
                String p = place[i];
                for(int j=0; j<5; j++){
                    char c = p.charAt(j);
                    room[i][j] = c;
                    if(c=='P'){
                        persons.add(new Person(i, j));
                    }
                }
            }

            // 대기실 사람 마다 탐색 시작
            for(Person person: persons){
                if(fail){ //대기실 사람들 중 한명이라도 실패한 경우
                    break;
                }
                visit[person.row][person.col] = true;
                dfs(person.row, person.col, 0, ' ');
            }

            // 정답 판별
            answer[k] = (fail) ? 0 : 1;
        }

        return answer;
    }

    public void dfs(int row, int col, int count, char preCh){
        // 실패했을 경우 종료
        if(fail){
            return;
        }
        if(count >= 3){ // 길이가 초과하여 탐색할 필요가 없을 때
            return;
        }

        // 사람을 만났을 때
        if(count!=0 && room[row][col]=='P'){
            if(preCh=='P' || preCh=='O'){ //실패할 경우
                fail = true;
                return;
            }
        }

        for(int i=0; i<4; i++){
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if(newRow<0 || newRow>=5 || newCol<0 || newCol>=5 || visit[newRow][newCol]){
                continue;
            }

            visit[newRow][newCol] = true;
            dfs(newRow, newCol, count+1, room[row][col]);
            visit[newRow][newCol] = false;
        }
    }

    class Person{
        int row, col;
        public Person(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

}