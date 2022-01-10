import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Avatar {
    protected int speedY = 0;
    public int x=200;
    public int y=0;
    protected final int gravity = 1;
    protected final int ground = 300;
    private Animation avatarRun;
    protected Rectangle avatarRect;
    protected boolean isAlive;
    protected boolean isHit;

    public Avatar(){
        avatarRun = new Animation();
        Random avatarGenerator = new Random();
        int avatarType = avatarGenerator.nextInt(3);
        isAlive = true;
        isHit = false;
        try {
            if (avatarType == 0) {
                avatarRun.addFrame(ImageIO.read(Window.class.getResource("./assets/images/chicky-flapup.png")));
                avatarRun.addFrame(ImageIO.read(Window.class.getResource("./assets/images/chicky-flapdown.png")));
            }
            if (avatarType == 1) {
                avatarRun.addFrame(ImageIO.read(Window.class.getResource("./assets/images/ducky-flapup.png")));
                avatarRun.addFrame(ImageIO.read(Window.class.getResource("./assets/images/ducky-flapdown.png")));
            }
            if (avatarType == 2) {
                avatarRun.addFrame(ImageIO.read(Window.class.getResource("./assets/images/benguin-flapup.png")));
                avatarRun.addFrame(ImageIO.read(Window.class.getResource("./assets/images/benguin-flapdown.png")));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAlive (boolean alive){
        isAlive = alive;
    }
    public boolean getAlive(){
        return isAlive;
    }
    public void setHit(boolean hit){
        isHit = hit;
    }
    public boolean getHit(){ return isHit;}
    protected void behave() {
        if (y >= ground - avatarRun.getFrame().getHeight()) {
            speedY = 0;
            y = ground - avatarRun.getFrame().getHeight();
        } else {
            speedY += gravity;
            y += speedY;
        }
        if(y <= 0){
            speedY = 0;
            y = 0;
        }
        avatarRun.updateFrame();
    }

    protected void jump(){
        speedY = -18;
        y += speedY;
        y += speedY;
    }

    public void drawAvatar(Graphics g){
        g.drawImage(avatarRun.getFrame(), x,y,null);
    }
    public Rectangle avatarBound(){
        avatarRect = new Rectangle(x,y,80,70);
        return avatarRect;
    }
}