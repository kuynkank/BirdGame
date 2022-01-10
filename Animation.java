import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Animation {
    Clock clock = Clock.systemDefaultZone();
    int currFrame;
    long lastTime = 0;
    int timeBetweenFrames = 150;
    BufferedImage frame;
    private List<BufferedImage> frameList;

    public BufferedImage getFrame(){
        return frameList.get(currFrame);
    }

    public Animation(){
        frameList = new ArrayList<BufferedImage>();
    }

    public void addFrame(BufferedImage frame){
        frameList.add(frame);
    }

    public void updateFrame(){
        if(clock.millis() > lastTime+timeBetweenFrames) {
            currFrame++;
            if (currFrame >= frameList.size()) {
                currFrame = 0;
            }
            lastTime = clock.millis();
        }
    }
}