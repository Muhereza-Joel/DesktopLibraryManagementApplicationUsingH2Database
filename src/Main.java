
import mmu.lms.frames.LoginFrame;
import mmu.lms.frames.SplashScreen;

public class Main {
    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        try {
            Thread.sleep(8000);
            splashScreen.dispose();
            new LoginFrame();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}