import java.awt.*;

public class Score {
    protected int mainScore;
    public Score(Cloud cloud, Obstacles obstacle){
        mainScore = 0;
        cloud.getCloudScore();
        obstacle.getScore();
        mainScore = cloud.cloudScore + obstacle.score;
    }
    public int getMainScore(){
        return mainScore;
    }
    public void scoreUpdate(Cloud cloud, Obstacles obstacle, int gameState) {
        if (gameState == 1) {
            cloud.getCloudScore();
            obstacle.getScore();
            mainScore = obstacle.score + cloud.cloudScore;
        }
    }
    public void drawScore(Graphics g){
        g.setColor(Color.decode("#817cc8"));
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        String mainScoreString = "SCORE: " + getMainScore();
        g.drawString(mainScoreString, 960,50);
    }
}
