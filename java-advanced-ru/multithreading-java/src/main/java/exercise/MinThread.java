package exercise;

// BEGIN
import java.util.logging.Logger;

public class MinThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MinThread.class.getName());
    private final int[] numbers;
    private int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + this.getName() + " started");
        min = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        LOGGER.info("Thread " + this.getName() + " finished");
    }

    public int getMin() {
        return min;
    }
}
// END
