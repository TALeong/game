import java.awt.*;

public class Player extends Sprite{
    private int hp;
    public Player() {
        super(new Point(Main.FRAMEWIDTH/2, Main.FRAMEHEIGHT/2), NORTH);
        setPic("player.png", NORTH);
        setSpeed(15);
        hp = 50;
    }

    public void setHp(int n) {
        hp = n;
    }
    public int getHp() {
        return hp;
    }
}
