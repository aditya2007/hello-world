package com.helloworld.rest.dev.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountTransaction {
	private double balance;
	private final Object from = new Object();
	private final Object to = new Object();

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
}
