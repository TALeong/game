import java.awt.*;

/**
 * Created by elisa_zhang on 3/21/17.
 */
public class Enemies extends Sprite {

    public Enemies(Point loc, int dir){
        super(loc, dir);
        setPic("enemies.png", NORTH);
        setSpeed(0);
    }

}
