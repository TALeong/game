import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel{
    public static final int FRAMEWIDTH = 1400, FRAMEHEIGHT = 800;
    private Timer timer;
    private Player player;
    private int level;
    private Point mouse;
    private ArrayList<Sprite> enemies, obstacles;
    public Main() {
        enemies = new ArrayList<Sprite>();
        obstacles = new ArrayList<Sprite>();
        level = 1;
        player = new Player();
        mouse = new Point(FRAMEWIDTH/2, 0);
        timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(player.getHp() > 0) {
                    player.setDir(player.getDirection(player.getCenterPoint(), mouse));
                    player.update();
                    repaint();
                }
                for(Sprite e: enemies) {
                    if(e.intersects(player)) {
                        player.setHp(player.getHp()-1);
                    }
                    e.update();
                }
                repaint();
            }
        });
        timer.start();
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("X: " + mouseEvent.getX() + " Y: " + mouseEvent.getY());
            }

            public void mousePressed(MouseEvent mouseEvent) {

            }

            public void mouseReleased(MouseEvent mouseEvent) {

            }

            public void mouseEntered(MouseEvent mouseEvent) {

            }

            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            public void mouseMoved(MouseEvent mouseEvent) {
                mouse = mouseEvent.getPoint();
            }
        });

        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent keyEvent) {

            }

            public void keyPressed(KeyEvent keyEvent) {

            }

            public void keyReleased(KeyEvent keyEvent) {

            }
        });

    }

    public void loadLevel() {
        enemies.clear();
        obstacles.clear();
        if(level == 1) {

        } else {

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.drawString("HP: " + player.getHp(), 500, 500);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Dodgeeeeeeeeeeeeeeee");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

//        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}
