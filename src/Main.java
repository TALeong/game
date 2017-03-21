import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel{

    public static final int FRAMEWIDTH = 1400, FRAMEHEIGHT = 800;

    public Main() {

        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent mouseEvent) {

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
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
