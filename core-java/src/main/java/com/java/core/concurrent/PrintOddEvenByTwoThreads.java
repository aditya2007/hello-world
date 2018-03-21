package com.java.core.concurrent;

import java.util.stream.IntStream;

/**
 * Created by Yogananda Gowda - 212590467 on 10/28/17.
 */
public class PrintOddEvenByTwoThreads {

    public static void main(String[] args) throws InterruptedException {
        Integer[] oddNums = {1,3,5,7,9};
        Integer[] evenNums = {2,4,6,8,10};

        /*Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int idx = 0;
                while (idx < nums.length) {
                    if (nums[idx] % 2 != 0) {
                        System.out.print(nums[idx] + ",");
                    }
                    idx++;
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int idx = 0;
                while (idx < nums.length) {
                    if (nums[idx] % 2 == 0) {
                        System.out.print(nums[idx] + ",");
                    }
                    idx++;
                }
            }
        });
        t2.start();*/

        PrintOddEvenByTwoThreads printByTwoT = new PrintOddEvenByTwoThreads();
        //printByTwoT.printOddNumber();

        //printByTwoT.printEvenNumber();

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();

        Thread t1 = new Thread(() -> {
            int idx = 0;
            IntStream.range(1, 11).forEach(num -> {
                try {
                    if (num %2 != 0) {
                        printByTwoT.printOddNumber(num);
                    }
                } catch (InterruptedException ie) { }
            });
            /*try {
                printByTwoT.printOddNumberWhile();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        });
        t1.start();
        Thread.sleep(1000l);
        Thread t2 = new Thread(() -> {
            IntStream.range(1, 11).forEach(num -> {
                try {
                    if (num % 2 == 0) {
                        printByTwoT.printEvenNumber(num);
                    }
                } catch (InterruptedException ie) { }
            });
            /*try {
                printByTwoT.printEvenNumberWhile();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        });
        t2.start();

        t1.join();
        t2.join();

        /*synchronized (printByTwoT.odd) {
            printByTwoT.odd.notify();
        }
        synchronized (printByTwoT.even) {
            printByTwoT.even.notify();
        }*/

        //System.exit(0);
    }

    private final Object odd = new Object();
    private final Object even = new Object();

    public void printOddNumber(int num) throws InterruptedException {
        synchronized (this) {
            System.out.print(num + ",");
            wait(1000);
            notify();
        }
    }

    public void printEvenNumber(int num) throws InterruptedException {
        synchronized (this) {
            System.out.print(num + ",");
            wait(1000);
            notify();
        }
    }

    public void printOddNumberWhile() throws InterruptedException {
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        synchronized (this) {
            int idx = 0;
            while (idx < nums.length ) {
                if (nums[idx] % 2 != 0) {
                    System.out.print(nums[idx++] + ",");
                    notify();
                    wait();
                }
            }
        }
    }

    public void printEvenNumberWhile() throws InterruptedException {
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        synchronized (this) {
            int idx = 0;
            while (idx < nums.length) {
                if (nums[idx] % 2 == 0) {
                    System.out.print(nums[idx++] + ",");
                    notify();
                    wait();
                }
            }
        }
    }

}
