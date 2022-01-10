import javax.swing.JFrame;

public class Window extends JFrame {
        public static final int SCREEN_WIDTH = 1920;

        public Window() {
            super("Angie's bird game!");
            setSize(SCREEN_WIDTH, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            display = new Display();
            addKeyListener(display);
            add(display);
        }
        public void startGame(){
            display.startGame();
        }
        private Display display;
        public static void main(String args[]) {
            Window gameWindow = new Window();
            gameWindow.setVisible(true);
            gameWindow.startGame();
        }
    }