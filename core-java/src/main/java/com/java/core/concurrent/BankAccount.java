package com.java.core.concurrent;

import com.java.core.concurrent.dedalock.DeadlockDetector;
import com.java.core.concurrent.dedalock.DeadlockHandlerImpl;

import java.util.concurrent.TimeUnit;

public class BankAccount {
	double balance;
	int id;

	public BankAccount() {}
	public BankAccount(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	void withdraw(double amount) {
		// Wait to simulate io like database access ...
		System.out.println(Thread.currentThread().getId() + "-"
				+ Thread.currentThread().getName() + " got the lock while withdraw");
		try {Thread.sleep(10l);} catch (InterruptedException e) {}
		balance -= amount;
		System.out.println("Balance after withdraw :: " + balance);
	}

	void deposit(double amount) {
		// Wait to simulate io like database access ...
		System.out.println(Thread.currentThread().getId() + "-"
				+ Thread.currentThread().getName() + " got the lock while deposit");
		try {Thread.sleep(10l);} catch (InterruptedException e) {}
		balance += amount;
		System.out.println("Balance after deposit :: " + balance);
	}

	void transfer(BankAccount from, BankAccount to, double amount) {
		synchronized(from) {
			from.withdraw(amount);
			synchronized(to) {
				to.deposit(amount);
			}
		}
	}
	public static void main(String[] args) {
		DeadlockDetector deadlockDetector =
				new DeadlockDetector(new DeadlockHandlerImpl(), 5, TimeUnit.SECONDS);
		deadlockDetector.start();

		final BankAccount fooAccount = new BankAccount(1, 100d);
		final BankAccount barAccount = new BankAccount(2, 500d);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				new BankAccount().transfer(fooAccount, barAccount, 10d);
			}
		},"foo");
		t1.start();

		Thread t2 =new Thread(new Runnable() {
			@Override
			public void run() {
				new BankAccount().transfer(barAccount, fooAccount, 10d);
			}
		},"bar");
		t2.start();

		/*try {
			System.out.println("Before main thread sleeping :: " + Thread.currentThread().getId());
			Thread.sleep(2000);
			System.out.println("After main thread wakeup :: " + Thread.currentThread().getId());
			if (t1.isAlive()) {
				t1.interrupt();
				System.out.println("Thread T1 got interrupted :: " + t1.isInterrupted());
			}
			if (t2.isAlive()) {
				t2.interrupt();
				System.out.println("Thread T2 got interrupted :: " + t2.isInterrupted());
			}
			System.out.println("Is thread T1 Alive after interrupt :: " + t1.isAlive());
			System.out.println("Is thread T2 Alive after interrupt :: " + t2.isAlive());
		} catch (InterruptedException e) {
			System.out.println("Is Thread T1 interrupted :: " + t1.isInterrupted());
			System.out.println("Is Thread T2 interrupted :: " + t2.isInterrupted());
			e.printStackTrace();
		}*/
	}
}
