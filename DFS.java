import java.util.concurrent.TimeUnit;

public class DFS {
    Node[][] grid;
    Node start, end;

    public DFS(Node[][] grid) {
        this.grid = grid;
        start = BFS.findStart(grid);
        end = BFS.findEnd(grid);
    }

    boolean pathFind() {
        return pathFind(start);
    }

    boolean pathFind(Node current) {
        current.searched = true;
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {

        }
        if (current == end) {
            return true;
        }
        if (current.x != 0) {
            if (grid[current.x - 1][current.y].value != 3 && grid[current.x - 1][current.y].value != 2
                    && !grid[current.x - 1][current.y].visited) {
                grid[current.x - 1][current.y].visited = false;
                if (pathFind(grid[current.x - 1][current.y])) {
                    return true;
                }
            }
        }
        if (current.x != grid.length - 1) {
            if (grid[current.x + 1][current.y].value != 3 && !grid[current.x + 1][current.y].visited
                    && grid[current.x + 1][current.y].value != 2) {
                grid[current.x + 1][current.y].visited = true;
                if (pathFind(grid[current.x + 1][current.y])) {
                    return true;
                }
            }
        }
        if (current.y != 0) {
            if (grid[current.x][current.y - 1].value != 3 && !grid[current.x][current.y - 1].visited
                    && grid[current.x][current.y - 1].value != 2) {
                grid[current.x][current.y - 1].visited = true;
                if (pathFind(grid[current.x][current.y - 1])) {
                    return true;
                }
            }
        }
        if (current.y != grid[0].length - 1) {
            if (grid[current.x][current.y + 1].value != 3 && !grid[current.x][current.y + 1].visited
                    && grid[current.x][current.y + 1].value != 2) {
                grid[current.x + 1][current.y].visited = true;
                if (pathFind(grid[current.x][current.y + 1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
