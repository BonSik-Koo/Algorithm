package baekjoon.구현;

import java.io.*;
import java.util.*;

public class 랭킹전대기열_20006 {
    static List<List<Player>> rooms;
    static int[] levels;
    static int p, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        rooms = new ArrayList<>();
        levels = new int[p];

        for(int i=0; i<p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            match(l, n);
        }

        for(List<Player> room : rooms) {
            if(room.size() == m) {
                System.out.println("Started!");
            } else {
                System.out.println("Waiting!");
            }

            Collections.sort(room);
            for(Player player : room) {
                System.out.println(player);
            }
        }
    }

    static void match(int level, String name) {
        boolean isMatched = false;
        for(int i=0; i<rooms.size(); i++) {
            List<Player> room = rooms.get(i);

            if(levels[i] - 10 <= level && level <= levels[i] + 10 && room.size() < m) {
                isMatched = true;
                room.add(new Player(level, name));
                break;
            }
        }
        if(!isMatched) {
            rooms.add(new ArrayList<>());
            rooms.get(rooms.size()-1).add(new Player(level, name));
            levels[rooms.size()-1] = level;
        }
    }

    static class Player implements Comparable<Player>{
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player player) {
            return name.compareTo(player.name);
        }

        @Override
        public String toString() {
            return level + " " + name;
        }
    }
}
