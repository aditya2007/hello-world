package com.java.core.concurrent;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Yogananda Gowda - 212590467 on 6/5/17.
 */
public class CountDownTask implements Callable<String> {
    private final String name;
    private final CountDownLatch latch;

    public CountDownTask(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "=====In method");
        System.out.println(Thread.currentThread().getName() + "=====Waitinglatch to open");
        latch.await();
        System.out.println(Thread.currentThread().getName() + "=====Latch open now");
        char[] chars = name.toCharArray();
        Arrays.sort(chars);
        StringBuilder builder = new StringBuilder();
        for ( char c : chars) {
            builder.append(c);
        }
        System.out.println(Thread.currentThread().getName() + "=====Completedxecution ");
        return builder.toString();
    }
}
