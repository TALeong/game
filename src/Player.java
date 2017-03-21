import java.awt.*;

public class Player extends Sprite{
    private boolean isDead;

    public Player() {
        super(new Point(Main.FRAMEWIDTH/2, Main.FRAMEHEIGHT/2), NORTH);
        setPic("player.png", NORTH);
    }

    public void update(){
        if (!isDead)
            super.update();
        else {
            getDead();
            setDead(true);
        }
    }

    public void setDead(boolean d){
        isDead=d;
    }
    public boolean getDead(){
        return isDead;
    }

}
