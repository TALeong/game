import java.awt.*;

public class Player extends Sprite{
    public Player() {
        super(new Point(Main.FRAMEWIDTH/2, Main.FRAMEHEIGHT/2), NORTH);
        setPic("player.png", NORTH);
    }
}
