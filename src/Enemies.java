import java.awt.*;

/**
 * Created by elisa_zhang on 3/21/17.
 */
public class Enemies extends Sprite {
    private int hp;
    public Enemies(Point loc, int dir) {
        super(loc, dir);
        setPic("enemies.png", NORTH);
        setSpeed(0);
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int n) {
        hp = n;
    }
}
