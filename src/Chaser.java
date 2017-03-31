import java.awt.*;
import java.util.ArrayList;

public class Chaser extends Sprite {
    private long birthday;

    public Chaser(Point loc, int dir) {
        super(loc, dir);
        setPic("bullet.png", EAST);
        birthday = System.currentTimeMillis();
    }
    public void update(Sprite target) {
        int midX = target.getLoc().x + target.getPic().getWidth()/2;
        int midY = target.getLoc().y + target.getPic().getHeight()/2;
        int selfX = getLoc().x + getPic().getWidth()/2;
        int selfY = getLoc().y + getPic().getHeight()/2;
        int deg = getDirection(new Point(selfX, selfY), new Point(midX, midY));
        setDir(deg);
        super.update();
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }
}
