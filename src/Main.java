import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel{
    public static final int FRAMEWIDTH = 1400, FRAMEHEIGHT = 800;
    private Timer timer;
    private Player player;
    private int level, a, b;
    private Point mouse;
    private ArrayList<Sprite> obstacles;
    private ArrayList<Enemies> enemies;
    private long time;
    private int[] x, y;

    @SuppressWarnings("unchecked")
    public Main() {
        enemies = new ArrayList<Enemies>();
        obstacles = new ArrayList<Sprite>();
        time = System.currentTimeMillis();
        level = 1;
        loadLevel();
        player = new Player();
        mouse = new Point(FRAMEWIDTH/2, 0);
        x = new int[110];
        y = new int[110];

        for (int i = 0; i < 110; i++) {
            a = (int)(Math.random()*FRAMEWIDTH);
            b = (int)(Math.random()*FRAMEHEIGHT);
            x[i] = a;
            y[i] = b;
        }

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
//                        if(b.isSpawned()) {
//                            System.out.println(b.isSpawned());
//                        }
                        b.update(player);
                        if(!b.intersects(e)) {
                            b.setSpawned(false);
                        }
                        if(b.intersects(player)) {
                            player.setHp(player.getHp()-1);
                            clone.remove(b);
                        }
                        for(Enemies t: enemies) {
                            if (b.intersects(t) && (!b.isSpawned() || (System.currentTimeMillis() - b.getBirthday()) >= 1000)) {
                                t.setHp(e.getHp() - 1);
                                clone.remove(b);
//                                System.out.println(t.getHp());
                                if (t.getHp() <= 0) {
                                    enemy.remove(t);
                                }
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
                if(enemies.size()<=0){
                    level++;
                    loadLevel();
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
            enemies.add(new Enemies(new Point(900, 200), Sprite.WEST));
            enemies.add(new Enemies(new Point(300,400), Sprite.EAST));
            enemies.add(new Enemies(new Point(700,600), Sprite.EAST));

        }
        if(level == 2) {
            enemies.add(new Enemies(new Point(50, 600), Sprite.EAST));
            enemies.add(new Enemies(new Point(300,250), Sprite.SE));
            enemies.add(new Enemies(new Point(650,50), Sprite.SOUTH));
            enemies.add(new Enemies(new Point(1000, 250), Sprite.SW));
            enemies.add(new Enemies(new Point(1250,600), Sprite.WEST));


        }
        if(level == 3) {
            enemies.add(new Enemies(new Point(100, 100), Sprite.SOUTH));
            enemies.add(new Enemies(new Point(300,100), Sprite.EAST));
            enemies.add(new Enemies(new Point(900,100), Sprite.NORTH));
            enemies.add(new Enemies(new Point(100, 400), Sprite.SE));
            enemies.add(new Enemies(new Point(100,600), Sprite.EAST));
            enemies.add(new Enemies(new Point(1230,630), Sprite.NW));

        }
        if(level == 4) {
            enemies.add(new Enemies(new Point(50, 50), Sprite.SE));
            enemies.add(new Enemies(new Point(250,350), Sprite.EAST));
            enemies.add(new Enemies(new Point(50,650), Sprite.NE));
            enemies.add(new Enemies(new Point(650,550), Sprite.NORTH));
            enemies.add(new Enemies(new Point(1250,650), Sprite.NW));
            enemies.add(new Enemies(new Point(1050,350), Sprite.WEST));
            enemies.add(new Enemies(new Point(1250,50), Sprite.SW));
            enemies.add(new Enemies(new Point(650,150), Sprite.SOUTH));

        }
        if(level == 5) {
            enemies.add(new Enemies(new Point(100, 150), Sprite.WEST));
            enemies.add(new Enemies(new Point(200,530), Sprite.NW));
            enemies.add(new Enemies(new Point(400, 200), Sprite.NE));
            enemies.add(new Enemies(new Point(500,376), Sprite.EAST));
            enemies.add(new Enemies(new Point(600,600), Sprite.SE));
            enemies.add(new Enemies(new Point(800,400), Sprite.SW));
            enemies.add(new Enemies(new Point(900,12), Sprite.WEST));
            enemies.add(new Enemies(new Point(1000, 550), Sprite.NW));
            enemies.add(new Enemies(new Point(1100,70), Sprite.NORTH));

        } else {

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
        g2.setColor(Color.WHITE);
        for (int i = 0; i < 110; i++) {
            if(i%6 ==0){
                int ex[] = {x[i],x[i]-2, x[i]-8, x[i]-2, x[i], x[i]+2, x[i]+8, x[i]+2};
                int why[] = {y[i]+15,y[i]+3,y[i], y[i]-3, y[i]-15, y[i]-3, y[i], y[i]+3};
                g2.fillPolygon(ex, why, 8);
            }
            else
                g2.fillOval(x[i],y[i],4,4);
        }

        player.draw(g2);
        if(player.getHp()<=5){
            g2.setColor(Color.RED);
        }
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g2.drawString("HP: " + player.getHp(), 30, 50);
        g2.drawRect(30,70,200,30);
        g2.fillRect(30,70,player.getHp()*4,30);
        g2.setColor(Color.WHITE);
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
