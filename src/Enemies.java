import java.awt.*;
import java.util.ArrayList;

public class Enemies extends Sprite {
    private long birthday;
    private int hp;
    private ArrayList<Chaser> bullets;
    public Enemies(Point loc, int dir) {
        super(loc, dir);
        setPic("enemies.png", NORTH);
        setSpeed(0);
        birthday = System.currentTimeMillis();
        bullets = new ArrayList<Chaser>();
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int n) {
        hp = n;
    }
    public ArrayList<Chaser> getBullets() {
        return bullets;
    }
    public void setBullets(ArrayList<Chaser> b) {
        bullets = b;
    }
    public void addBullet(Chaser c) {
        bullets.add(c);
    }
    public void removeBullet(Chaser c) {
        bullets.remove(c);
    }

    public long getBirthday() {
        return birthday;
    }
    public void setBirthday(long num) {
        birthday = num;
    }
}
