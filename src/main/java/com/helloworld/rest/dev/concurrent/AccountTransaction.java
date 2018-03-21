package com.helloworld.rest.dev.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountTransaction {
	private double balance;
	private final Object from = new Object();
	private final Object to = new Object();

	private final Object pub = new Object();
	private final Object ack = new Object();

	public AccountTransaction() {}

	public AccountTransaction(double balance) {
		this.balance = balance;
	}

	private void withdraw(double amount) {
		log.info( "[Thread Id - {}]:[Thread Name - {}] got the lock while withdraw",
				Thread.currentThread().getId(), Thread.currentThread().getName());
		try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {}
		balance -= amount;
	}

	private void deposit(double amount) {
		log.info( "[Thread Id - {}]:[Thread Name - {}] got the lock while deposit",
				Thread.currentThread().getId(), Thread.currentThread().getName());
		try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {}
		balance += amount;
	}

	public void transfer(AccountTransaction from, AccountTransaction to, double amount) {
		synchronized(from) {
			from.withdraw(amount);
			synchronized(to) {
				to.deposit(amount);
			}
		}
	}

	public void publish() throws InterruptedException {
		System.out.println("BEGIN : publish " + Thread.currentThread().getId());

		synchronized (pub) {
			System.out.println(Thread.currentThread().getId() + " publishing messages ");
			pub.wait(1000l);
			//Thread.sleep(180000l);
			System.out.println(Thread.currentThread().getId() + " publishing thread woked up ");
			pub.notify();
		}
		System.out.println("END : publish " + Thread.currentThread().getId());
	}

	public void ack() throws InterruptedException {
		System.out.println("BEGIN : ack " + Thread.currentThread().getId());

		synchronized (ack) {
			System.out.println(Thread.currentThread().getId() + " got the ack");
			ack.wait(1000l);
			//Thread.sleep(180000l);
			System.out.println(Thread.currentThread().getId() + " ack thread woked up ");
			ack.notify();
		}
		System.out.println("END : ack " + Thread.currentThread().getId());
	}
}
