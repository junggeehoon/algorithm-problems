import java.util.*;

class Pro_81302 {
    
    public static final int[] dx = {1, 0, -1, 0};
    public static final int[] dy = {0, 1, 0, -1};
    
    private static class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static boolean bfs(int startX, int startY, String[] places, boolean[][] visited) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startX, startY));
        visited[startX][startY] = true;
        while (!q.isEmpty()) {
            Node point = q.poll();
            int x = point.x;
            int y = point.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // range check
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                if (visited[nx][ny]) continue;
                if (places[nx].charAt(ny) == 'P') {
                    char previous = places[x].charAt(y);
                    if (previous == 'O' || previous == 'P') {
                        return false;
                    }
                }
                
                int md = Math.abs(startX - nx) + Math.abs(startY - ny);
                
                if (md < 2 && places[nx].charAt(ny) == 'O') {
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return true;
    }
    
    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        
        for (int i = 0; i < places.length; i++) { // 2차원
            boolean[][] visited = new boolean[5][5];
            boolean flag = false;
            for (int j = 0; j < places[0].length; j++) {
                for (int k = 0; k < places[i][j].length(); k++) {
                    if (flag) break;
                    if (places[i][j].charAt(k) == 'P') {
                        if (bfs(j, k, places[i], visited)) {
                            answer[i] = 1;
                        } else {
                            answer[i] = 0;
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        return answer;
    }
}