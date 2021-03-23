import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BFS {
    Node[][] grid;

    public BFS(Node[][] grid) {
        this.grid = grid;
    }

    public boolean findPath() {
        Node start = findStart(grid);
        Node end = findEnd(grid);
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(start);
        grid[start.x][start.y].visited = true;
        while (!queue.isEmpty()) {
            Node current = queue.pop();
            current.searched = true;
            if (current.id == end.id) {
                return true;
            }
            queue.addAll(addChildren(current));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e) {

            }
        }
        return false;

    }

    List<Node> addChildren(Node parent) {
        LinkedList<Node> children = new LinkedList<Node>();
        if (parent.x != 0) {
            if (grid[parent.x - 1][parent.y].value != 3 && !grid[parent.x - 1][parent.y].visited
                    && grid[parent.x - 1][parent.y].value != 2) {
                children.add(grid[parent.x - 1][parent.y]);
                grid[parent.x - 1][parent.y].visited = true;
            }
        }
        if (parent.x != grid.length - 1) {
            if (grid[parent.x + 1][parent.y].value != 3 && !grid[parent.x + 1][parent.y].visited
                    && grid[parent.x + 1][parent.y].value != 2) {
                children.add(grid[parent.x + 1][parent.y]);
                grid[parent.x + 1][parent.y].visited = true;
            }
        }
        if (!(parent.y == 0)) {
            if (grid[parent.x][parent.y - 1].value != 3 && !grid[parent.x][parent.y - 1].visited
                    && grid[parent.x][parent.y - 1].value != 2) {
                children.add(grid[parent.x][parent.y - 1]);
                grid[parent.x][parent.y - 1].visited = true;
            }
        }
        if (!(parent.y == grid[0].length - 1)) {
            if (grid[parent.x][parent.y + 1].value != 3 && !grid[parent.x][parent.y + 1].visited
                    && grid[parent.x][parent.y + 1].value != 2) {
                children.add(grid[parent.x][parent.y + 1]);
                grid[parent.x][parent.y + 1].visited = true;
            }
        }
        return children;
    }

    static Node findStart(Node[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].value == 1) {
                    return grid[i][j];
                }
            }
        }

        return null;
    }

    static Node findEnd(Node[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].value == 9) {
                    return grid[i][j];
                }
            }
        }

        return null;
    }
}
