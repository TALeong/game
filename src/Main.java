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
    private ArrayList<Sprite> obstacles;
    private ArrayList<Enemies> enemies;
    private long time;

    @SuppressWarnings("unchecked")
    public Main() {
        enemies = new ArrayList<Enemies>();
        obstacles = new ArrayList<Sprite>();
        time = System.currentTimeMillis();
        level = 1;
        loadLevel();
        player = new Player();
        mouse = new Point(FRAMEWIDTH/2, 0);
        timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(player.getHp() > 0) {
                    player.setDir(player.getDirection(player.getCenterPoint(), mouse));
                    player.update();
                    repaint();
                }
                for(Enemies e: enemies) {
                    ArrayList<Enemies> enemy = (ArrayList<Enemies>)enemies.clone();
                    ArrayList<Chaser> clone = (ArrayList<Chaser>)e.getBullets().clone();
                    for(Chaser b: e.getBullets()) {
//                        System.out.println(System.currentTimeMillis() - b.getBirthday());
                        b.update(player);
                        if(b.intersects(player)) {
                            player.setHp(player.getHp()-1);
                            clone.remove(b);
                        }else if(b.intersects(e) && (System.currentTimeMillis() - b.getBirthday())/1000 >= 1) {
                            e.setHp(e.getHp()-1);
//                            b.setBirthday(System.currentTimeMillis());
                            clone.remove(b);
                            System.out.println(e.getHp());
                            if(e.getHp() <= 0) {
                                enemy.remove(e);
                            }
                        }
                    }
                    e.setBullets((ArrayList<Chaser>)clone.clone());

                    if(e.intersects(player)) {
//                        player.setHp(player.getHp()-1);
                    }
//                    System.out.println((System.currentTimeMillis() - e.getBirthday()) % 5000);
                    if((System.currentTimeMillis() - e.getBirthday())% 5000 <= 40) {
                        e.addBullet(new Chaser(e.getCenterPoint(), Sprite.NORTH));
                        System.out.println("add");
                    }
                    e.update();
                    enemies = enemy;
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
            enemies.add(new Enemies(new Point(200, 200), Sprite.NORTH));
            enemies.add(new Enemies(new Point(300,400), Sprite.EAST));

        } else {

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
        player.draw(g2);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g2.drawString("HP: " + player.getHp(), 30, 50);
        for(Enemies e: enemies) {
            e.draw(g2);
            for(Chaser c: e.getBullets()) {
                c.draw(g2);
            }
        }
        for(Sprite o: obstacles) {
            o.draw(g2);
        }
        if(player.getHp()<=0){
            g2.setColor(Color.RED);
            g2.fillRect(0,0,FRAMEWIDTH, FRAMEHEIGHT);
            g2.setColor(Color.WHITE);
            g2.drawString("lol", FRAMEWIDTH/2, FRAMEHEIGHT/2);
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Dodgeeee" +
                "eeeeeeeeeeee");
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
