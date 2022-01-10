import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Obstacles {
    private BufferedImage obstacleImage;
    protected int coordX;
    private final int coordY = 200;//on the ground
    protected Rectangle obstacleRect;
    Avatar avatar = new Avatar();
    int score;
    int move = 15;

    public Obstacles() {
        Random obstacleTypeGenerator = new Random();
        int obstacleType = obstacleTypeGenerator.nextInt(3);
        score = 0;
        if(obstacleType ==0) {
            try {
                obstacleImage = ImageIO.read(Window.class.getResource("./assets/images/cactus1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(obstacleType ==1) {
            try {
                obstacleImage = ImageIO.read(Objects.requireNonNull(Window.class.getResource("./assets/images/cactus2.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                obstacleImage = ImageIO.read(Window.class.getResource("./assets/images/snail.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Random obstaclePlacementGenerator = new Random();
        coordX = obstaclePlacementGenerator.nextInt(avatar.x+1080-(avatar.x+200))+(avatar.x+200);
        obstacleRect = new Rectangle();
    }

    public Rectangle getObstacleBound() {
        return obstacleRect;
    }

    public void update(Obstacles obstacle, Avatar avatar, int gameState){
        if((obstacle.getObstacleBound().intersects(avatar.avatarBound()))) {
            avatar.setAlive(false);
        }
        if(gameState == 1 && coordX>=-10){
            // move obstacle if in playing mode
            coordX -= 15;
        }
        else {
            Random rn = new Random();
            int placement = rn.nextInt(500);
            coordX=1100 + placement;

            if (gameState == 1) {
                updateScore();
            }
            Random obstacleTypeGenerator = new Random();
            int obstacleType = obstacleTypeGenerator.nextInt(3);
            if(obstacleType ==0) {
                try {
                    obstacleImage = ImageIO.read(Window.class.getResource("./assets/images/cactus1.png"));
                    obstacleRect.width = 70;
                    obstacleRect.height = 70;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(obstacleType ==1) {
                try {
                    obstacleImage = ImageIO.read(Objects.requireNonNull(Window.class.getResource("./assets/images/cactus2.png")));
                    obstacleRect.width = 80;
                    obstacleRect.height = 90;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    obstacleImage = ImageIO.read(Window.class.getResource("./assets/images/snail.png"));
                    obstacleRect.width = 90;
                    obstacleRect.height = 60;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        obstacleRect.x = coordX;
        obstacleRect.y = coordY;
    }

    public void updateScore(){
        score+=10;
    }

    public int getScore(){
        return score;
    }

    public void drawObstacle(Graphics g){
            g.drawImage(obstacleImage, coordX, coordY, null);
    }
}