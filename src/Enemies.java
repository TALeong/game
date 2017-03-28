import java.awt.*;
import java.util.ArrayList;

public class Enemies extends Sprite {
    private int hp;
    private ArrayList<Chaser> bullets;
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
    public ArrayList<Chaser> getBullets() {
        return bullets;
    }
    public void setBullets(ArrayList<Chaser> b) {
        bullets = b;
    }
    public void deleteBullet(Chaser s) {
        for(Chaser b: bullets) {
            if(b.equals(s)) {
                bullets.remove(b);
            }
        }
    }
}
