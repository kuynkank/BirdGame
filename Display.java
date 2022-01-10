import java.awt.*;
 import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Display extends JPanel implements Runnable, KeyListener {
    protected final int ground = 300;//height of the ground line
    protected Avatar avatar;
    private Thread thread;
    protected Obstacles obstacle;
    protected Cloud cloud;
    protected Score score;
    protected final int start = 0;
    protected final int play = 1;
    protected final int end = 2;
    protected int gameState = start;

    public Display(){
        thread = new Thread(this);
        avatar = new Avatar();
        obstacle = new Obstacles();
        cloud = new Cloud();
        score = new Score(cloud, obstacle);
        }

    public void startGame(){
        thread.start();
    }
    public void restartGame(){
        thread = new Thread(this);
        avatar = new Avatar();
        obstacle = new Obstacles();
        cloud = new Cloud();
        score = new Score(cloud, obstacle);
    }
    @Override

    public void run(){
        while(true){
            try{
                updateDisplay();
                Thread.sleep(30);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void updateDisplay(){
        avatar.behave();
        if (gameState != end) {
            obstacle.update(obstacle, avatar, gameState);
            cloud.cloudUpdate(cloud, avatar, gameState);
            score.scoreUpdate(cloud, obstacle, gameState);
        }
        if(avatar.getAlive() == false){
            gameState = end;
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameState == start){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                gameState = play;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            avatar.jump();
            if (gameState == start) {
                gameState = play;
            } else {
                if (gameState == end) {
                    gameState = start;
                    restartGame();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    public void paint(Graphics g){
        switch(gameState){
            case start:
                g.setColor(Color.decode("#90c9cb"));
                g.fillRect(0,0, Window.SCREEN_WIDTH, 540);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.drawString("Press [space] to start", 600, 200);
                avatar.drawAvatar(g);
                break;
            case play:
                g.setColor(Color.decode("#90c9cb"));
                g.fillRect(0,0, Window.SCREEN_WIDTH, 540);
                g.setColor(Color.decode("#a5c87c"));
                g.fillRect(0,ground,getWidth(),ground);
                avatar.drawAvatar(g);
                obstacle.drawObstacle(g);
                cloud.drawCloud(g);
                score.drawScore(g);
                break;
            case end:
                g.setColor(Color.BLACK);
                g.fillRect(0,0, Window.SCREEN_WIDTH, 540);
                g.setColor(Color.decode("#a5c87c"));
                g.fillRect(0,ground,getWidth(),ground);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.drawString("Game over", 500, 200);
                g.drawString("Press [space] to play again", 290, 230);
                score.drawScore(g);
                break;
        }
    }
}