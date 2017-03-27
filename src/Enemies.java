import java.awt.*;
import java.util.ArrayList;

public class Enemies extends Sprite {
    private int hp;
    private ArrayList<Sprite> bullets;
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
    public ArrayList getBullets() {
        return bullets;
    }
    public void setBullets(ArrayList<Sprite> b) {
        bullets = b;
    }
}
