import java.awt.*;
import java.util.ArrayList;

public class Chaser extends Sprite {

    private int counter;

    public Chaser(Point loc, int dir) {
        super(loc, dir);
        setPic("bullet.png", EAST);
        counter = 0;
    }
    public void update(Sprite target) {
        counter++;
        int midX = target.getLoc().x + target.getPic().getWidth()/2;
        int midY = target.getLoc().y + target.getPic().getHeight()/2;
        int selfX = getLoc().x + getPic().getWidth()/2;
        int selfY = getLoc().y + getPic().getHeight()/2;
        int deg = getDirection(new Point(selfX, selfY), new Point(midX, midY));
        setDir(deg);
        super.update();
    }

    public int getCounter() {
        return counter;
    }
}
