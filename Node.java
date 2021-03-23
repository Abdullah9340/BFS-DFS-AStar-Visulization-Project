public class Node {
    int x, y;
    int f, h, g, value;
    int id;
    boolean visited, searched;
    static int idCounter = 0;

    public Node(int value, int i, int j) {
        this.value = value;
        x = i;
        y = j;
        visited = false;
        searched = false;
        id = idCounter;
        idCounter++;
    }
}
