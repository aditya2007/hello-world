package com.helloworld.rest.dev.concurrent;

public class ConcurrentApp {

	public static void main(String[] args) {
		//Test whether 2 threads can get a lock on the same instance monitor at given time,
		// which are locked with different Object locks

		AccountTransaction transaction = new AccountTransaction();

		new Thread(() -> {
			try {
				transaction.publish();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				transaction.ack();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
