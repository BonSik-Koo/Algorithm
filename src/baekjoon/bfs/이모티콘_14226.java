package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 이모티콘_14226 {
    static final int maxSize = 2000;
    static class Node{
        int time, display, board;
        Node(int time, int display, int board){
            this.time = time;
            this.display = display;
            this.board = board;
        }
    }
    static Boolean[][] visit = new Boolean[maxSize+1][maxSize+1]; //[화면][클립 보드]

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        for(int i=0;i<=maxSize; i++){
            Arrays.fill(visit[i], false);
        }

        System.out.println(bfs(S));
    }
    private static int bfs(int S){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 1, 0));
        visit[1][0] = true;

        int result = 0 ;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.display == S){
                result = node.time;
                break;
            }

            if(node.display <= 1000 && node.board <=1000) {
                //화면 -> 클립 보드
                if (!visit[node.display][node.display]) {
                    queue.add(new Node(node.time + 1, node.display, node.display));
                    visit[node.display][node.display] = true;
                }
                //클립 보드 -> 화면
                if (node.board > 0 && !visit[node.display + node.board][node.board]) {
                    queue.add(new Node(node.time + 1, node.display + node.board, node.board));
                    visit[node.display + node.board][node.board] = true;
                }
                //화면의 이모티콘 하나 삭제
                if (node.display > 0 && !visit[node.display - 1][node.board]) {
                    queue.add(new Node(node.time + 1, node.display - 1, node.board));
                    visit[node.display - 1][node.board] = true;
                }
            }
        }

        return result;
    }
}
