package com.java.core.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Yogananda Gowda - 212590467 on 6/5/17.
 */
public class CountdownLatchApp {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        CountDownTask task1 = new CountDownTask("java", latch);
        CountDownTask task2 = new CountDownTask("concurrent", latch);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println(Thread.currentThread().getName() + "===== submitting1");
        Future<String> future1 = executorService.submit(task1);
        System.out.println(Thread.currentThread().getName() + "===== Submitted 1...calling latch count down 1");
        latch.countDown();

        System.out.println(Thread.currentThread().getName() + "===== submitting2");
        Future<String> future2 = executorService.submit(task2);
        System.out.println(Thread.currentThread().getName() + "===== Submitted 2...calling latch count down 2");
        latch.countDown();

        try {
            System.out.println(Thread.currentThread().getName()
                    + " ===Result1= " + future1.get()
                    + " ===Result2= " + future2.get());
        } catch (InterruptedException ie) {
            System.out.println("OOPSterruptedException Exception");
        } catch ( ExecutionException ee ) {
            System.out.println("OOPSExecutionException Exception");
        }
    }
}
