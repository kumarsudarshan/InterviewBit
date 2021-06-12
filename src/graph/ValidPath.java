package graph;

import java.util.*;

/*
Valid Path
There is a rectangle with left bottom as  (0, 0) and right up as (x, y). There are N circles such that their centre’s are inside the rectangle.
Radius of each circle is R. Now we need to find out if it is possible that we can move from (0, 0) to (x, y) without touching any circle.
Note : We can move from any cell to any of its 8 adjacent neighbours and we cannot move outside the boundary of the rectangle at any point of time.


Input Format
1st argument given is an Integer x.
2nd argument given is an Integer y.
3rd argument given is an Integer N, number of circles.
4th argument given is an Integer R, radius of each circle.
5th argument given is an Array A of size N, where A[i] = x coordinate of ith circle
6th argument given is an Array B of size N, where B[i] = y coordinate of ith circle
Output Format
Return YES or NO depending on weather it is possible to reach cell (x,y) or not starting from (0,0).
Constraints
0 <= x, y, R <= 100
1 <= N <= 1000
Centre of each circle would lie within the grid
For Example
Input:
    x = 2
    y = 3
    N = 1
    R = 1
    A = [2]
    B = [3]
Output:
    NO

Explanation:
    There is NO valid path in this case

 */
public class ValidPath {
    public static void main(String[] args) {
        ArrayList<Integer> E = new ArrayList<>();
        E.add(1);
        E.add(3);
        ArrayList<Integer> F = new ArrayList<>();
        F.add(3);
        F.add(3);
        System.out.println(new ValidPath().isValidPathDFS(5, 5, 2, 1, E, F));

        E = new ArrayList<>();
        E.add(2);
        F = new ArrayList<>();
        F.add(3);
        System.out.println(new ValidPath().isValidPathDFS(2, 3, 1, 1, E, F));

        E = new ArrayList<>();
        E.add(1);
        E.add(3);
        F = new ArrayList<>();
        F.add(3);
        F.add(3);
        System.out.println(new ValidPath().isValidPathBFS(5, 5, 2, 1, E, F));

        E = new ArrayList<>();
        E.add(2);
        F = new ArrayList<>();
        F.add(3);
        System.out.println(new ValidPath().isValidPathBFS(2, 3, 1, 1, E, F));
    }


    // using BFS approach
    static int[] x = {1, 1, 1, -1, -1, -1, 0, 0};
    static int[] y = {-1, 1, 0, -1, 1, 0, 1, -1};

    public String isValidPathBFS(int A, int B, int N, int R, ArrayList<Integer> E, ArrayList<Integer> F) {
        int[][] valid = new int[A + 1][B + 1];
        for (int i = 0; i <= A; i++) {
            for (int j = 0; j <= B; j++) {
                for (int z = 0; z < N; z++) {
                    if (Math.sqrt(Math.pow(E.get(z) - i, 2) + Math.pow(F.get(z) - j, 2)) <= R)
                        valid[i][j] = -1;
                }
            }
        }
        if (valid[0][0] == -1 || valid[A][B] == -1)
            return "NO";
        //System.out.println("startng ending no problem");
        boolean[][] v = new boolean[A + 1][B + 1];
        v[0][0] = true;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(0);
        q.add(0);
        while (!q.isEmpty()) {
            int i = q.poll();
            int j = q.poll();
            for (int z = 0; z < 8; z++) { // for all directions
                int ix = i + x[z];
                int iy = j + y[z];
                //System.out.println(ix+" "+iy);
                if (!(ix < 0 || iy < 0 || ix >= A + 1 || iy >= B + 1) && !v[ix][iy] && valid[ix][iy] != -1) {
                    //System.out.println(ix+"valid"+iy);
                    if (ix == A && iy == B) {
                        return "YES";
                    }
                    q.add(ix);
                    q.add(iy);
                    v[ix][iy] = true;
                }
            }
        }
        return "NO";
    }

    // using DFS approach
    public String isValidPathDFS(int A, int B, int N, int R, ArrayList<Integer> E, ArrayList<Integer> F) {
        int[][] grid = new int[A + 1][B + 1];
        boolean[][] visited = new boolean[A + 1][B + 1];
        for (int i = 0; i < A + 1; i++) {
            for (int j = 0; j < B + 1; j++) {
                for (int k = 0; k < N; k++) {
                    if (Math.sqrt(Math.pow(E.get(k) - i, 2) + Math.pow(F.get(k) - j, 2)) <= R) {
                        grid[i][j] = -1;
                    }
                }
            }
        }
        doDFS(grid, visited, 0, 0);
        return visited[A][B] == true ? "YES" : "NO";
    }

    private void doDFS(int[][] grid, boolean[][] visited, int x, int y) {
        int n = grid.length;
        int m = grid[0].length;
        if (x == n - 1 && y == m - 1) {
            visited[x][y] = true;
            return;
        }
        visited[x][y] = true;
        int i = 0, j = 0;
        i = x - 1;
        j = y - 1;
        if (isValid(i, j, n, m) && !visited[i][j] && grid[i][j] == 0) {
            doDFS(grid, visited, i, j);
        }
        i = x - 1;
        j = y;
        if (isValid(i, j, n, m) && !visited[i][j] && grid[i][j] == 0) {
            doDFS(grid, visited, i, j);
        }
        i = x - 1;
        j = y + 1;
        if (isValid(i, j, n, m) && !visited[i][j] && grid[i][j] == 0) {
            doDFS(grid, visited, i, j);
        }
        i = x;
        j = y - 1;
        if (isValid(i, j, n, m) && !visited[i][j] && grid[i][j] == 0) {
            doDFS(grid, visited, i, j);
        }
        i = x;
        j = y + 1;
        if (isValid(i, j, n, m) && !visited[i][j] && grid[i][j] == 0) {
            doDFS(grid, visited, i, j);
        }
        i = x + 1;
        j = y - 1;
        if (isValid(i, j, n, m) && !visited[i][j] && grid[i][j] == 0) {
            doDFS(grid, visited, i, j);
        }
        i = x + 1;
        j = y;
        if (isValid(i, j, n, m) && !visited[i][j] && grid[i][j] == 0) {
            doDFS(grid, visited, i, j);
        }
        i = x + 1;
        j = y + 1;
        if (isValid(i, j, n, m) && !visited[i][j] && grid[i][j] == 0) {
            doDFS(grid, visited, i, j);
        }
    }

    private boolean isValid(int x, int y, int n, int m) {
        return (x >= 0 && x < n) && (y >= 0 && y < m);
    }
}
