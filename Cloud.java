import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Cloud {
    protected Rectangle cloudRect;
    protected BufferedImage cloudImage;
    protected int cloudX;
    protected int cloudY;
    protected int cloudScore;

    public Cloud(){
        try {
            cloudImage = ImageIO.read(Window.class.getResource("./assets/images/cloud.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cloudScore = 0;
        cloudRect = new Rectangle(90,40);
        Random cloudRN = new Random();
        cloudX = cloudRN.nextInt(1080-500)+500;
        cloudY = cloudRN.nextInt(200);
        cloudRect.x=cloudX;
        cloudRect.y=cloudY;
    }
    public void cloudScoreUpdate(boolean avatarBonus){
            cloudScore+=50;
    }
    public void cloudUpdate(Cloud cloud, Avatar avatar, int gameState){
        //moves the cloud offscreen if isHit returns true; else just move backwards
        if((cloud.getCloudBounds().intersects(avatar.avatarBound()))) {
            avatar.setHit(true);
            cloudScoreUpdate(true);
            Random rn = new Random();
            int placement = rn.nextInt(500);
            cloudX=3000 + placement;
        }
        if (gameState == 1 && cloudX >= -40) {
            //move cloud if in playing mode
            cloudX -= 20;
        }
        else {
            Random rnX = new Random();
            int xCloudPlacement = rnX.nextInt(500);
            cloudX = 1920 + xCloudPlacement;
            Random rnY = new Random();
            cloudY = rnY.nextInt(200);

            try {
                cloudImage = ImageIO.read(Window.class.getResource("./assets/images/cloud.png"));
                cloudRect.width = 90;
                cloudRect.height = 40;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cloudRect.x=cloudX;
        cloudRect.y=cloudY;
    }

    public Rectangle getCloudBounds(){
        return cloudRect;
    }
    public int getCloudScore(){
        return cloudScore;
    }
    public void drawCloud(Graphics g){
        g.drawImage(cloudImage, cloudX, cloudY, null);
    }
}