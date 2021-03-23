import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Display
 */
public class Display extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static Display d;
    private Node[][] grid;
    private boolean isStart = false, isEnd = false, isErase = false;
    private static BFS b;
    private static DFS dfs;
    public static boolean searching = false;

    // grey = wall = 3
    // black = barrier = 2
    // green = start = 1
    // white = empty = 0
    // red = end = 9
    public Display(Node[][] grid) {
        this.grid = grid;
        setMap();
        setFocusable(true);
        b = new BFS(grid);
        dfs = new DFS(grid);
        setSize(648, 540);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int x = (int) Math.floor((e.getX() - 50) / 30.0), y = (int) Math.floor((e.getY() - 50) / 30.0);
                    if (x <= grid.length && y <= grid[0].length) {
                        if (!(grid[x][y].value == 3)) {
                            if (isErase) {
                                grid[x][y].value = 0;
                            } else if (isStart) {
                                isStart = false;
                                grid[x][y].value = 1;
                            } else if (isEnd) {
                                isEnd = false;
                                grid[x][y].value = 9;
                            } else {
                                grid[x][y].value = 2;
                            }
                            // repaint();
                        }

                    }
                } catch (Exception err) {

                }

            }
        });
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    isEnd = !isEnd;
                    isStart = false;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    isEnd = false;
                    isStart = !isStart;
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    isErase = !isErase;
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    b = new BFS(grid);
                    searching = true;
                }
            }
        });
    }

    public void reDraw() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        g.translate(50, 50);
        drawGrid(g, grid);
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (Exception e) {

        }
        repaint();
    }

    public void drawGrid(Graphics g, Node[][] toDraw) {
        for (int i = 0; i < toDraw.length; i++) {
            for (int j = 0; j < toDraw[i].length; j++) {
                Color c;
                if (toDraw[i][j].searched) {
                    c = Color.blue;
                } else {
                    switch (toDraw[i][j].value) {
                    case 0:
                        c = Color.WHITE;
                        break;
                    case 1:
                        c = Color.green;
                        break;
                    case 2:
                        c = Color.black;
                        break;
                    case 3:
                        c = Color.gray;
                        break;
                    case 9:
                        c = Color.red;
                        break;
                    default:
                        c = Color.white;
                        break;
                    }
                }
                g.setColor(c);
                g.fillRect(i * 30, j * 30, 30, 30);
                g.setColor(Color.black);
                g.drawRect(i * 30, j * 30, 30, 30);
            }
        }
    }

    public void setMap() {
        Node.idCounter = 0;
        Node[][] newGrid = grid;
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                if (i == 0 || i == newGrid.length - 1) {
                    newGrid[i][j] = new Node(3, i, j);
                } else if (j == 0 || j == newGrid[i].length - 1) {
                    newGrid[i][j] = new Node(3, i, j);
                } else {
                    newGrid[i][j] = new Node(0, i, j);
                }

            }
        }
        newGrid = grid;
        repaint();

    }

    public static void main(String[] args) {
        Node[][] map = new Node[12][12];
        JFrame frame = new JFrame("X");
        d = new Display(map);
        frame.add(d);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(648, 540);
        frame.setVisible(true);
        while (true) {
            if (searching) {
                b.findPath();
                searching = false;
                for (int i = 0; i < b.grid.length; i++) {
                    for (int j = 0; j < b.grid[i].length; j++) {
                        b.grid[i][j].searched = false;
                        b.grid[i][j].visited = false;
                    }
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (Exception e) {

            }
        }
    }

}