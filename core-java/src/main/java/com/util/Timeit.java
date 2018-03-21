package com.util;

/**
 * Created by 212590467 on 12/10/16.
 */
public class Timeit {
    public static void code(Runnable block) {
        long start = System.nanoTime();
        try {
            block.run();
        } finally {
            long end = System.nanoTime();
            //System.out.println("Time taken(s): " + (end - start)/1.0e9);
            System.out.println("Time taken(s): " + (end - start));
        }
    }

    public static void main(String[] args) {
        code(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
