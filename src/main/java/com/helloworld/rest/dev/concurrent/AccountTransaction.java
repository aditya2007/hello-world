package com.helloworld.rest.dev.concurrent;

import org.springframework.stereotype.Component;

@Component
public class AccountTransaction {
	private double balance;

	public AccountTransaction() {}

	public AccountTransaction(double balance) {
		this.balance = balance;
	}

	private void withdraw(double amount) {
		System.out.println(Thread.currentThread().getId() + "-"
				+ Thread.currentThread().getName() + " got the lock while withdraw");
		try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {}
		balance -= amount;
	}

	private void deposit(double amount) {
		System.out.println(Thread.currentThread().getId() + "-"
				+ Thread.currentThread().getName() + " got the lock while deposit");
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
