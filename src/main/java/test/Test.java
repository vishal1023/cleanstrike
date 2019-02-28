package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong(0);
        ExecutorService service = Executors.newFixedThreadPool(16);

        for (int i = 0; i < 100; i++) {
            service.submit(new Task(atomicLong));
        }

        Thread.sleep(2000);

        System.out.println("atomicLong = " + atomicLong);
    }

    private static class Task implements Runnable {
        private final AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.incrementAndGet();
        }
    }
}
