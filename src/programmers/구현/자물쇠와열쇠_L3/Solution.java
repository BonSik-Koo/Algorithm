package programmers.구현.자물쇠와열쇠_L3;

class Solution {
    private static int MAX_SIZE = 58; // 한칸씩 겹치므로 (60-2)

    public boolean solution(int[][] key, int[][] lock) {
        int offset = key.length - 1;
        for(int x=0; x<lock.length+offset; x++) {
            for(int y=0; y<lock.length+offset; y++) {
                int[][] rotatedKey = key;

                for(int r=0; r<4; r++) { // 회전
                    // 자물쇠 배열 확장
                    int[][] extendedLock = extendLock(lock, offset);

                    // 키 회전
                    rotatedKey = rotation(rotatedKey);

                    // 키-자물쇠 매칭
                    match(extendedLock, rotatedKey, x, y);

                    // 결과 확인
                    if(verify(extendedLock, offset, lock.length)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int[][] extendLock(int[][] lock, int offset) {
        int[][] arr = new int[MAX_SIZE][MAX_SIZE]; // 배열 확장
        for(int i=0; i<lock.length; i++) {
            for(int j=0; j<lock.length; j++) {
                arr[i+offset][j+offset] = lock[i][j];
            }
        }
        return arr;
    }

    private int[][] rotation(int[][] key) {
        int[][] arr = new int[key.length][key.length];
        for(int i=0; i<key.length; i++) {
            for(int j=0; j<key.length; j++) {
                arr[i][j] = key[key.length-1-j][i]; // 90도씩 회전
            }
        }
        return arr;
    }

    private void match(int[][] arr, int[][] key, int offsetX, int offsetY) {
        for(int i=0; i<key.length; i++) {
            for(int j=0; j<key.length; j++) {
                arr[i+offsetX][j+offsetY] += key[i][j];
            }
        }
    }

    private boolean verify(int[][] arr, int offset, int size) {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(arr[i+offset][j+offset] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

}