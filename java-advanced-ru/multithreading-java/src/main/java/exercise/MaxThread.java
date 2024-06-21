package exercise;

// BEGIN

import java.util.logging.Logger;

public class MaxThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MaxThread.class.getName());
    private final int[] numbers;
    private int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + this.getName() + " started");
        max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        LOGGER.info("Thread " + this.getName() + " finished");
    }

    public int getMax() {
        return max;
    }
}
// END
