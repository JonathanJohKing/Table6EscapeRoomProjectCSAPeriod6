//Timer file
import java.util.Timer;
import java.util.TimerTask;

public class GameTimerUtil {
    int timeRemaining;  // in seconds
    private Timer timer;
    int GameTimerUtil;

    public GameTimerUtil(int timeInSeconds) {
        timeRemaining = timeInSeconds;
        timer = new Timer();
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeRemaining--;
                if (timeRemaining%10 == 0) {
                    System.out.println("Time Remaining: " + timeRemaining + " seconds");
                }
                if (timeRemaining <= 0) {
                    timer.cancel();
                    System.out.println("Time's up!");
                }
            }
        }, 0, 1000);  // Schedule to run every 1000 milliseconds (1 second)
    }
}
